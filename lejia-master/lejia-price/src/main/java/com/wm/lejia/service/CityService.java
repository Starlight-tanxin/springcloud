package com.wm.lejia.service;

import java.util.List;

import com.wm.lejia.common.pojo.dto.DefProvinceDTO;
import com.wm.lejia.common.pojo.entity.City;
import com.wm.lejia.common.pojo.entity.Province;
import com.wm.lejia.common.pojo.vo.ProvinceVO;

public interface CityService {
	
	List<ProvinceVO> getCity();
	
	Province createProvince(Province province);
	
	City createCity(City city);
	
	List<DefProvinceDTO> getDefProvinceAndCity();
}
