package com.wm.lejia.feign.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wm.lejia.common.pojo.dto.AddCityDTO;
import com.wm.lejia.common.pojo.dto.DefProvinceDTO;
import com.wm.lejia.common.pojo.entity.City;
import com.wm.lejia.common.pojo.entity.Province;
import com.wm.lejia.common.pojo.vo.CityVO;
import com.wm.lejia.common.utils.Result;
import com.wm.lejia.common.utils.ResultCode;
import com.wm.lejia.feign.client.ManageFeignClient;

@RestController
@RequestMapping("/lejia/manage")
public class ManageConttroller {

	private static Logger log = LoggerFactory.getLogger(ManageConttroller.class);

	@Autowired
	private ManageFeignClient manageFeignClient;

	@PostMapping("/listCity")
	public Result<?> listCity() {
		Result<List<CityVO>> listCityResult = manageFeignClient.listCity();
		return listCityResult;
	}

	@PostMapping("/getDefProvinceAndCity")
	public Result<?> getDefProvinceAndCity() {
		Result<List<DefProvinceDTO>> defResult = manageFeignClient.getDefProvinceAndCity();
		return defResult;
	}

	@PostMapping("/upCity")
	public Result<?> upCity(@RequestBody City city) {
		if (ObjectUtils.isEmpty(city) || city.getCityId() == null || city.getUpdatedBy() == null) {
			return new Result<>(ResultCode.PARAM_LOSS);
		}
		// 上架
		city.setIsUp(1);
		Result<?> updateCityResult = manageFeignClient.updateCity(city);
		return updateCityResult;
	}

	@PostMapping("/downCity")
	public Result<?> downCity(@RequestBody City city) {
		if (ObjectUtils.isEmpty(city) || city.getCityId() == null || city.getUpdatedBy() == null) {
			return new Result<>(ResultCode.PARAM_LOSS);
		}
		// 下架
		city.setIsUp(0);
		Result<?> updateCityResult = manageFeignClient.updateCity(city);
		return updateCityResult;
	}
	
	@PostMapping("/addCity")
	public Result<?> addCity(@RequestBody AddCityDTO dto) {
		if (ObjectUtils.isEmpty(dto) || dto.isEmpty()) {
			return new Result<>(ResultCode.PARAM_LOSS);
		}
		log.info("ManageConttroller   addCity ===> AddCityDTO : " + dto.toString());
		// true 代表没有 city
		boolean a = true;
		City c = dto.DTO2City();
		Result<City> getCity = manageFeignClient.getCity(c);
		City city = getCity.getData();
		// 不为null 为重复添加的数据
		if (!ObjectUtils.isEmpty(city)) {
			a = false;
			// 从软删除 或者下架状态回复
			if (city.getIsDeleted() != 0 || city.getIsUp() != 1) {
				city.setIsDeleted(0);
				city.setIsUp(1);
				city.setIsDeleted(dto.getIsDefault());
				city.setCreatedBy(dto.getUpdatedBy());
				manageFeignClient.updateCity(city);
			}
			return new Result<>();
		}
		Province p = dto.DTO2Province();
		Result<Province> getProvince = manageFeignClient.getProvince(p);
		Province province = getProvince.getData();
		if (!ObjectUtils.isEmpty(province)) {
			if(province.getIsDeleted() != 0 || province.getIsUp() != 1) {
				province.setIsUp(1);
				province.setIsDeleted(0);
				province.setIsDefault(dto.getIsDefault());
				province.setUpdatedBy(dto.getUpdatedBy());
				manageFeignClient.updateProvince(province);
				if (a) {
					c.setProvinceId(p.getProvinceId());
					Result<City> createCity = manageFeignClient.createCity(c);
					return createCity;
				}
			}
		}
		Result<Province> createProvince = manageFeignClient.createProvince(province);
		if(createProvince.getCode() != 200) {
			return createProvince;
		}
		p = createProvince.getData();
		c.setProvinceId(p.getProvinceId());
		Result<City> createCity = manageFeignClient.createCity(c);
		return createCity;
	}
}
