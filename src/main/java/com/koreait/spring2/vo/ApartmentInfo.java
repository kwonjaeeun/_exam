package com.koreait.spring2.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ApartmentInfo {

    private int i_ai;

    @JsonProperty("법정동")
    private String dong;
    @JsonProperty("지번")
    private String jibun;
    @JsonProperty("아파트")
    private String apartment_name;
    @JsonProperty("거래금액")
    private int deal_amount;
    @JsonProperty("건축년도")
    private String build_year;
    @JsonProperty("년")
    private String deal_year;
    @JsonProperty("월")
    private String deal_month;
    @JsonProperty("일")
    private String deal_day;
    @JsonProperty("전용면적")
    private float area_for_exclusive_use;
    @JsonProperty("층")
    private int flr;

    private int in_cd;

    public void setDeal_amount(String str){
        this.deal_amount=Integer.parseInt(str.trim().replace(",",""));
    }
    public void setFlr(String str){
        this.flr=Integer.parseInt(str);
    }
    public void setArea_for_exclusive_use(String str){
        this.area_for_exclusive_use=Float.parseFloat(str);
    }
}
