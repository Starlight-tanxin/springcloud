package com.wm.lejia.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wm.lejia.utils.Result;
import com.wm.lejia.utils.ResultCode;
import com.wm.lejia.service.CityService;
import com.wm.lejia.pojo.dto.DefProvinceDTO;
import com.wm.lejia.pojo.entity.City;
import com.wm.lejia.pojo.entity.Province;
import com.wm.lejia.pojo.vo.CityVO;
import com.wm.lejia.pojo.vo.ProvinceVO;

@RestController
@RequestMapping("/manage/city")
public class CityController {

	private static Logger log = LoggerFactory.getLogger(CityController.class);

	@Autowired
	private CityService cityService;

	@PostMapping("/createProvince")
	public Result<Province> createProvince(@RequestBody Province province) {
		Province p = cityService.createProvince(province);
		if (p == null) {
			log.info("CityController ==> createProvince ===> 添加数据失败");
			return new Result<>(ResultCode.INSERT_ERROR);
		}
		return new Result<Province>(p);
	}

	@PostMapping("/createCity")
	public Result<City> createCity(@RequestBody City city) {
		City c = cityService.createCity(city);
		if (c == null) {
			log.info("CityController ==> createCity ===> 添加数据失败");
			return new Result<>(ResultCode.INSERT_ERROR);
		}
		return new Result<City>(c);
	}

	@PostMapping("/getDefProvinceAndCity")
	public Result<List<DefProvinceDTO>> getDefProvinceAndCity() {
		List<DefProvinceDTO> list = cityService.getDefProvinceAndCity();
		return new Result<>(list);
	}

	@PostMapping("/listCity")
	public Result<List<CityVO>> listCity() {
		List<CityVO> list = cityService.listCityAndProvince();
		if (list == null) {
			return new Result<>(ResultCode.QUERY_ERROR);
		}
		return new Result<List<CityVO>>(list);
	}

	@PostMapping("/getCity")
	public Result<City> getCity(@RequestBody City city) {
		City c = cityService.getCity(city);
		return new Result<City>(c);
	}

	@PostMapping("/getProvince")
	public Result<Province> getProvince(@RequestBody Province province) {
		Province p = cityService.getProvince(province);
		return new Result<Province>(p);
	}

	@PostMapping("/updateCity")
	public Result<?> updateCity(@RequestBody City city) {
		int rows = cityService.updateCity(city);
		if (rows <= 0) {
			return new Result<>(ResultCode.DATA_UPDATE_ERROR);
		}
		return new Result<>();
	}

	@PostMapping("/updateProvince")
	public Result<?> updateProvince(@RequestBody Province province) {
		int rows = cityService.updateProvince(province);
		if (rows <= 0) {
			return new Result<>(ResultCode.DATA_UPDATE_ERROR);
		}
		return new Result<>();
	}

	@PostMapping("/getProvinceAndCity")
	public Result<List<ProvinceVO>> getProvinceAndCity() {
		List<ProvinceVO> list = cityService.getCity();
		log.info("CityController   getProvinceAndCity ===> list值" + list);
		return new Result<List<ProvinceVO>>(list);
	}

	// ------------

	@PostMapping("/listCityByHome")
	public Result<List<City>> listCityByHome() {
		List<City> list = cityService.listCityByHome();
		return new Result<List<City>>(list);
	}
}
