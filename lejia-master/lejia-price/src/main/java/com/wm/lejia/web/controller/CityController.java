package com.wm.lejia.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wm.lejia.pojo.entity.City;
import com.wm.lejia.pojo.entity.Province;
import com.wm.lejia.pojo.vo.ProvinceVO;
import com.wm.lejia.service.CityService;
import com.wm.lejia.utils.Result;
import com.wm.lejia.utils.ResultCode;

@RestController
@RequestMapping("/city")
public class CityController {

	private static Logger log = LoggerFactory.getLogger(CityController.class);

	@Autowired
	private CityService cityService;
	
	@PostMapping("/getProvinceAndCity")
	public Result<List<ProvinceVO>> getProvinceAndCity(){
		List<ProvinceVO> list = cityService.getCity();
		log.info("CityController getProvinceAndCity ===> list值" + list);
		return new Result<List<ProvinceVO>>(list);
	}
	
	@PostMapping("/createProvince")
	public Result<Province> createProvince(@RequestBody Province province){
		Province p = cityService.createProvince(province);
		if(p == null) {
			return new Result<>(ResultCode.INSERT_ERROR);
		}
		return new Result<Province>(p);
	}
	
	@PostMapping("/createCity")
	public Result<City> createCity(@RequestBody City city){
		City c = cityService.createCity(city);
		if(c == null) {
			return new Result<>(ResultCode.INSERT_ERROR);
		}
		return new Result<City>(c);
	}

}
