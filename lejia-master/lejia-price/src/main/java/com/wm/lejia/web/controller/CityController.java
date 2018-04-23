package com.wm.lejia.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wm.lejia.common.pojo.dto.DefProvinceDTO;
import com.wm.lejia.common.pojo.entity.City;
import com.wm.lejia.common.pojo.entity.Province;
import com.wm.lejia.common.pojo.vo.ProvinceVO;
import com.wm.lejia.service.CityService;
import com.wm.lejia.common.utils.Result;
import com.wm.lejia.common.utils.ResultCode;

@RestController
@RequestMapping("/city")
public class CityController {

	private static Logger log = LoggerFactory.getLogger(CityController.class);

	@Autowired
	private CityService cityService;
	
	@PostMapping("/getProvinceAndCity")
	public Result<List<ProvinceVO>> getProvinceAndCity(){
		List<ProvinceVO> list = cityService.getCity();
		log.info("CityController   getProvinceAndCity ===> list值" + list);
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
	
	@PostMapping("/getDefProvinceAndCity")
	public Result<List<DefProvinceDTO>> getDefProvinceAndCity(){
		List<DefProvinceDTO> list = cityService.getDefProvinceAndCity();
		return new Result<>(list);
	}

	@PostMapping("/listCityByHome")
	public Result<List<City>> listCityByHome(){
		List<City> list = cityService.listCityByHome();
		return new Result<List<City>>(list);
	}
}
