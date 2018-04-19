package com.wm.lejia.service;

import java.util.List;

import com.wm.lejia.pojo.entity.City;
import com.wm.lejia.pojo.entity.Province;
import com.wm.lejia.pojo.vo.ProvinceVO;

public interface CityService {
	
	List<ProvinceVO> getCity();
	
	Province createProvince(Province province);
	
	City createCity(City city);
}
