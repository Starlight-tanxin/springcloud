package com.wm.lejia.feign.client;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.wm.lejia.common.pojo.dto.DefProvinceDTO;
import com.wm.lejia.common.pojo.entity.City;
import com.wm.lejia.common.pojo.entity.Province;
import com.wm.lejia.common.pojo.vo.CityVO;
import com.wm.lejia.common.utils.Result;
import com.wm.lejia.feign.config.FeignConfig;
import com.wm.lejia.feign.hystric.FallbackManageHystric;

@FeignClient(name = "manage-price", configuration = FeignConfig.class, fallback = FallbackManageHystric.class)
public interface ManageFeignClient {
	
	@PostMapping(value = "/manage/city/createProvince", consumes = "application/json")
	Result<Province> createProvince(@RequestBody Province province);
	
	@PostMapping(value = "/manage/city/createCity", consumes = "application/json")
	Result<City> createCity(@RequestBody City city);
	
	@PostMapping(value = "/manage/city/getDefProvinceAndCity")
	Result<List<DefProvinceDTO>> getDefProvinceAndCity();
	
	@PostMapping(value = "/manage/city/listCity")
	Result<List<CityVO>> listCity();
	
	@PostMapping(value = "/manage/city/getCity", consumes = "application/json")
	Result<City> getCity(@RequestBody City city);
	
	@PostMapping(value = "/manage/city/getProvince", consumes = "application/json")
	Result<Province> getProvince(@RequestBody Province province);
	
	@PostMapping(value = "/manage/city/updateCity", consumes = "application/json")
	Result<?> updateCity(@RequestBody City city);
	
	@PostMapping(value = "/manage/city/updateProvince", consumes = "application/json")
	Result<?> updateProvince(@RequestBody Province province);
}
