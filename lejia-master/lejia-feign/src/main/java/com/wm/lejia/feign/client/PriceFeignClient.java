package com.wm.lejia.feign.client;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.wm.lejia.feign.config.FeignConfig;
import com.wm.lejia.feign.hystric.FallbackPriceHystric;
import com.wm.lejia.feign.pojo.dto.CalculationPriceDTO;
import com.wm.lejia.feign.pojo.dto.HomeDTO;
import com.wm.lejia.feign.pojo.dto.HomeDetailDTO;
import com.wm.lejia.feign.pojo.dto.PriceDTO;
import com.wm.lejia.feign.pojo.dto.TotalPriceDTO;
import com.wm.lejia.feign.pojo.dto.UserDTO;
import com.wm.lejia.feign.pojo.model.City;
import com.wm.lejia.feign.pojo.model.Province;
import com.wm.lejia.feign.pojo.model.TotalPrice;
import com.wm.lejia.feign.pojo.model.User;
import com.wm.lejia.feign.pojo.vo.ProvinceVO;

import com.wm.lejia.common.utils.Result;

@FeignClient(name = "service-price", configuration = FeignConfig.class, fallback = FallbackPriceHystric.class)
public interface PriceFeignClient {

	@PostMapping(value = "/price/createHomeInfo", consumes = "application/json")
	Result<HomeDTO> createHomeInfo(@RequestBody HomeDTO homeDTO);

	@PostMapping(value = "/price/createHomeDetail", consumes = "application/json")
	Result<List<HomeDetailDTO>> createHomeDetail(@RequestBody List<HomeDetailDTO> details);

	@PostMapping(value = "/price/calculationPrice", consumes = "application/json")
	Result<TotalPriceDTO> calculationPrice(@RequestBody CalculationPriceDTO calculationPriceDTO);

	@PostMapping(value = "/user/checkUser", consumes = "application/json")
	Result<User> checkUser(@RequestBody UserDTO userDTO);

	@PostMapping(value = "/user/regUser", consumes = "application/json")
	Result<User> regUser(@RequestBody UserDTO userDTO);

	@PostMapping(value = "/price/getHome")
	CalculationPriceDTO getHome(@RequestParam(value = "homeId") Integer homeId);

	@PostMapping(value = "/price/createTotalPrice", consumes = "application/json")
	Result<TotalPrice> createTotalPrice(@RequestBody TotalPrice totalPrice);

	@PostMapping(value = "/price/createPriceItem", consumes = "application/json")
	Result<Boolean> createPriceItem(@RequestBody List<PriceDTO> list);

	@PostMapping(value = "/price/listTotalPrice")
	Result<List<TotalPrice>> listTotalPrice(@RequestParam(value = "userId") Integer userId);

	@PostMapping(value = "/price/listPriceItem")
	Result<List<com.wm.lejia.feign.pojo.model.UserPrice>> listPriceItem(@RequestParam(value = "totalPriceId") Integer totalPriceId);

	@PostMapping(value = "/user/getUserByWechatOpenid")
	Result<User> getUserByWechatOpenid(@RequestParam(value = "wechatOpenid") String wechatOpenid);
	
	@PostMapping(value = "/city/getProvinceAndCity")
	Result<List<ProvinceVO>> getProvinceAndCity();
	
	@PostMapping(value = "/city/createProvince", consumes = "application/json")
	Result<Province> createProvince(@RequestBody Province province);
	
	@PostMapping(value = "/city/createCity", consumes = "application/json")
	Result<City> createCity(@RequestBody City city);
}
