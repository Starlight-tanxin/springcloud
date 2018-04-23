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

import com.wm.lejia.common.pojo.dto.HomeDTO;
import com.wm.lejia.common.pojo.dto.HomeDetailDTO;
import com.wm.lejia.common.pojo.dto.LoginDTO;
import com.wm.lejia.common.pojo.dto.ServicePriceCalculationPriceDTO;
import com.wm.lejia.common.pojo.dto.TotalPriceDTO;
import com.wm.lejia.common.pojo.entity.Banner;
import com.wm.lejia.common.pojo.entity.City;
import com.wm.lejia.common.pojo.entity.User;
import com.wm.lejia.common.utils.Result;
import com.wm.lejia.common.utils.ResultCode;
import com.wm.lejia.common.utils.StringUtils;
import com.wm.lejia.feign.client.PriceFeignClient;
import com.wm.lejia.feign.utils.ServiceUtils;

@RestController
@RequestMapping("/lejia/home")
public class HomeController {

	private static Logger log = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private PriceFeignClient priceFeignClient;

	@PostMapping("/login")
	public Result<?> login(@RequestBody LoginDTO dto) {
		if (ObjectUtils.isEmpty(dto)) {
			return new Result<String>(ResultCode.PARAM_LOSS);
		}
		log.info("HomeController   login ===> dto : " + dto.toString());
		if (StringUtils.isEmptyStr(dto.getMobile())) {
			return new Result<String>(ResultCode.MOBILE_NO_INPUT);
		}
		if (StringUtils.isEmptyStr(dto.getMsmCode())) {
			return new Result<String>(ResultCode.CODE_NOT_EXTIST);
		}
		return priceFeignClient.login(dto);
	}

	@PostMapping("/listBannerByHome")
	public Result<?> listBannerByHome(@RequestBody Banner banner) {
		if (ObjectUtils.isEmpty(banner) || banner.getCityId() == null) {
			return new Result<String>(ResultCode.PARAM_LOSS);
		}
		return priceFeignClient.listBannerByHome(banner);
	}

	@PostMapping("/sumPrice")
	public Result<?> sumPrice(@RequestBody ServicePriceCalculationPriceDTO dto) {
		if (ObjectUtils.isEmpty(dto)) {
			return new Result<String>(ResultCode.PARAM_LOSS);
		}
		HomeDTO homeDTO = dto;
		List<HomeDetailDTO> details = dto.getDetails();
		if (ObjectUtils.isEmpty(homeDTO) || ObjectUtils.isEmpty(details)) {
			return new Result<String>(ResultCode.PARAM_LOSS, "homeDTO 或者 details 值为NULL");
		}
		if (StringUtils.isEmptyStr(homeDTO.getMobile()) || StringUtils.isEmptyStr(homeDTO.getNickname())) {
			return new Result<String>(ResultCode.PARAM_LOSS, "nickname 或者 mobile 值为NULL");
		}
		Result<User> checkUser = priceFeignClient.checkUser(homeDTO);
		User data = checkUser.getData();
		User user = null;
		if (data == null) {
			Result<User> regUser = priceFeignClient.regUser(homeDTO);
			user = regUser.getData();
		} else {
			user = data;
		}
		Integer userId = user.getUserId();
		homeDTO.setUserId(userId);
		log.info("HomeController   sumPrice ===> user : " + user.toString());
		Result<HomeDTO> createHomeInfo = priceFeignClient.createHomeInfo(homeDTO);
		if(createHomeInfo.getCode() != 200) {
			return createHomeInfo;
		}
		HomeDTO home = createHomeInfo.getData();
		Integer homeId = home.getHomeId();
		for(HomeDetailDTO detail : details) {
			detail.setHomeId(homeId);
			detail.setCreatedBy(userId);
			dto.setHomeId(homeId);
			dto.setUserId(userId);
		}	
		details = ServiceUtils.createHomeDetail(homeId, userId, details, dto);
		for(HomeDetailDTO detail : details) {
			// 选项名字
			String projectName = detail.getInfo();
			projectName = (StringUtils.isEmptyStr(projectName) ? "" : projectName.trim());
			// 区域
			String homeDetailType = detail.getHomeDetailType();
			homeDetailType = (StringUtils.isEmptyStr(homeDetailType) ? "" : homeDetailType.trim());
			// 格式化 homeid
			Integer formatHomeId = ServiceUtils.formatHomeId(projectName, homeDetailType);
			if(formatHomeId != null) {
				detail.setDecorationId(formatHomeId);
			}
		}
		Result<List<HomeDetailDTO>> createHomeDetail = priceFeignClient.createHomeDetail(details);
		if(createHomeDetail.getCode() != 200) {
			return new Result<String>(ResultCode.DATA_UPDATE_ERROR);
		}
		dto.setDetails(details);
		Result<TotalPriceDTO> calculationPrice = priceFeignClient.calculationPrice(dto);
		return calculationPrice;
	}
	
	@PostMapping("/listCityByHome")
	public Result<List<City>> listCityByHome(){
		return priceFeignClient.listCityByHome();
	}
}
