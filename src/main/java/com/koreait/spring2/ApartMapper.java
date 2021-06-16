package com.koreait.spring2;

import com.koreait.spring2.vo.ApartDTO;
import com.koreait.spring2.vo.ApartmentInfo;
import com.koreait.spring2.vo.LocationCodeEntity;
import com.koreait.spring2.vo.insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface ApartMapper {
  List<LocationCodeEntity> selLocationCodeList(ApartDTO param);
  void saveData(insert param);
  List<ApartmentInfo> selApartment(ApartDTO param);
  int selApartmentHistory(ApartDTO param);
}
