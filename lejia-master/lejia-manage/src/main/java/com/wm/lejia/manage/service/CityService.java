package com.wm.lejia.manage.service;

import java.util.List;

import com.wm.lejia.common.pojo.dto.DefProvinceDTO;
import com.wm.lejia.common.pojo.entity.City;
import com.wm.lejia.common.pojo.entity.Province;
import com.wm.lejia.common.pojo.vo.CityVO;

public interface CityService {
	
	Province createProvince(Province province);

	City createCity(City city);

	List<DefProvinceDTO> getDefProvinceAndCity();
	
	Province getProvince(Province province);
	
	City getCity(City city);
	
	int updateCity(City city);
	
	int updateProvince(Province province);
	
	List<CityVO> listCityAndProvince();
}
