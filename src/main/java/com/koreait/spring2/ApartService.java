package com.koreait.spring2;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.koreait.spring2.vo.ApartDTO;
import com.koreait.spring2.vo.ApartmentInfo;
import com.koreait.spring2.vo.LocationCodeEntity;
import com.koreait.spring2.vo.insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UTFDataFormatException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

@Service
public class ApartService {

    @Autowired
    private ApartMapper mapper;

    public List<LocationCodeEntity> selLocationCodeList(ApartDTO param){
        return  mapper.selLocationCodeList(param);
    }
    public void saveData(ApartDTO param) {
        if(mapper.selApartmentHistory(param)!=0){
            return;
        }

        final String url="http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev";
        String decodeServiceKey="Y2UOCkD8Ilv2gViPGV33ddNTTQfRi92i8mRzUeQX+NgSiNTO3gp9hJZX4J6u8uXucMM6RdRBoGxMn6XHfsEzNA==";

        final HttpHeaders HEADERS=new HttpHeaders();
        HEADERS.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        final HttpEntity<String> entity=new HttpEntity<String>(HEADERS);
        String deal_ym=String.format("%s%02d",param.getDeal_year(),param.getDeal_month());
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("LAWD_CD",param.getEx_cd())
                .queryParam("DEAL_YMD",deal_ym)
                .queryParam("serviceKey",decodeServiceKey)
                .queryParam("numOfRows","100")
                .build(false);

        RestTemplate rest = new RestTemplate();
        rest.getMessageConverters().add(0,new StringHttpMessageConverter(Charset.forName("UTF-8")));
        ResponseEntity<String> respEntity = rest.exchange(builder.toUriString(), HttpMethod.GET,entity,String.class);
        String result = respEntity.getBody();
        System.out.println(result);

        ObjectMapper om =new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        JsonNode jsonNode=null;
        ApartmentInfo[] list=null;
        try {
            jsonNode= om.readTree(result);
            list =om.treeToValue(jsonNode.path("response").path("body").path("items").path("item"),ApartmentInfo[].class);
            System.out.println("list.length"+list.length);
        }catch (Exception e){
            e.printStackTrace();
        }

        List<LocationCodeEntity> code=selLocationCodeList(param);
        insert param2= new insert();
        param2.setIn_cd(code.get(0).getIn_cd());
        param2.setArr(list);
        mapper.saveData(param2);

    }

    public List<ApartmentInfo> selApartData(ApartDTO param) {
        return mapper.selApartment(param);
    }
}