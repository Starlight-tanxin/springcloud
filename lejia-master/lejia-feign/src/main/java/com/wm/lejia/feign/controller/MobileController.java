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
import com.wm.lejia.common.pojo.dto.PriceDTO;
import com.wm.lejia.common.pojo.dto.ServicePriceCalculationPriceDTO;
import com.wm.lejia.common.pojo.dto.TotalPriceDTO;
import com.wm.lejia.common.pojo.dto.UserDTO;
import com.wm.lejia.common.pojo.entity.TotalPrice;
import com.wm.lejia.common.pojo.entity.User;
import com.wm.lejia.common.pojo.entity.UserPrice;
import com.wm.lejia.common.utils.Result;
import com.wm.lejia.common.utils.ResultCode;
import com.wm.lejia.common.utils.StringUtils;
import com.wm.lejia.feign.client.PriceFeignClient;
import com.wm.lejia.feign.utils.ServiceUtils;

@RestController
@RequestMapping("/lejia/mobile")
public class MobileController {
	
	private static Logger log = LoggerFactory.getLogger(MobileController.class);
	
	@Autowired
	private PriceFeignClient priceFeignClient;
	
	@PostMapping("/createHome")
	public Result<?> createHome(@RequestBody HomeDTO dto) {
		if (ObjectUtils.isEmpty(dto)) {
			return new Result<String>(ResultCode.PARAM_LOSS);
		}
		UserDTO userDTO = dto;
		log.info("UserDTO ====> " + dto.toString());
		if (ObjectUtils.isEmpty(userDTO)) {
			return new Result<String>(ResultCode.PARAM_LOSS);
		}
		if(StringUtils.isEmptyStr(userDTO.getMobile()) || StringUtils.isEmptyStr(userDTO.getNickname()) || StringUtils.isEmptyStr(userDTO.getWechatOpenid())) {
			return new Result<String>(ResultCode.PARAM_LOSS);
		}
		Result<User> checkUser = priceFeignClient.checkUser(userDTO);
		User data = checkUser.getData();
		User user = null;
		if(ObjectUtils.isEmpty(data)) {
			Result<User> regUser = priceFeignClient.regUser(userDTO);
			user =  regUser.getData();
		} else {
			user =  data;
		}
		Integer userId = user.getUserId();
		dto.setUserId(userId);
		log.info("MobileController   createHome ===> user : " + user.toString());
		Result<HomeDTO> createHomeInfo = priceFeignClient.createHomeInfo(dto);
		return createHomeInfo;
	}
	
	@PostMapping("/sumPrice")
	public Result<?> sumPrice(@RequestBody List<HomeDetailDTO> details) {
		if(ObjectUtils.isEmpty(details)) {
			return new Result<String>(ResultCode.PARAM_LOSS);
		}
		Integer homeId = details.get(0).getHomeId();
		Integer createdBy = details.get(0).getCreatedBy();
		ServicePriceCalculationPriceDTO calculationPriceDTO = priceFeignClient.getHome(homeId);
		if(ObjectUtils.isEmpty(calculationPriceDTO)) {
			return new Result<String>(ResultCode.DATA_NOT_EXIST);
		}
		details = ServiceUtils.createHomeDetail(homeId, createdBy, details, calculationPriceDTO);
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
		calculationPriceDTO.setDetails(details);
		Result<TotalPriceDTO> calculationPrice = priceFeignClient.calculationPrice(calculationPriceDTO);
		return calculationPrice;
	}
	
	@PostMapping("/saveCalculationPrice")
	public Result<?> saveCalculationPrice(@RequestBody TotalPriceDTO totalPriceDTO){
		if(ObjectUtils.isEmpty(totalPriceDTO) || ObjectUtils.isEmpty(totalPriceDTO.getPriceItem()) || totalPriceDTO.getUserId() == null) {
			return new Result<String>(ResultCode.PARAM_LOSS);
		}
		Integer userId = totalPriceDTO.getUserId();
		// 创建条目
		TotalPrice totalPrice = new TotalPrice();
		totalPrice.setUnitTotal(totalPriceDTO.getUnitSumPrice());
		totalPrice.setTotal(totalPriceDTO.getSumPrice());
		totalPrice.setUserId(userId);
		totalPrice.setCreatedBy(userId);
		totalPrice.setCityId(totalPriceDTO.getCityId());
		totalPrice.setSource(totalPriceDTO.getSource());
		totalPrice.setProvinceId(totalPriceDTO.getProvinceId());
		// 创建
		Result<TotalPrice> createTotalPrice = priceFeignClient.createTotalPrice(totalPrice);
		if(createTotalPrice.getCode() != 200) {
			return createTotalPrice;
		}
		TotalPrice tp = createTotalPrice.getData();
		List<PriceDTO> priceDTOs = totalPriceDTO.getPriceItem();
		for (PriceDTO pDTO : priceDTOs) {
			pDTO.setCreatedBy(userId);
			pDTO.setTotalPriceId(tp.getTotalPriceId());
		}
		Result<Boolean> createPriceItemResult = priceFeignClient.createPriceItem(priceDTOs);
		return createPriceItemResult;
	}
	
	@PostMapping("/listTotalPrice")
	public Result<?> listTotalPrice(Integer userId,String wechatOpenid){
		if(userId == null && StringUtils.isEmptyStr(wechatOpenid)) {
			return new Result<String>(ResultCode.PARAM_LOSS);
		}
		if(userId == null) {
			Result<User> userResult = priceFeignClient.getUserByWechatOpenid(wechatOpenid);
			if(userResult.getCode() != 200) {
				return userResult;
			}
			User u = userResult.getData();
			if(!ObjectUtils.isEmpty(u)) 
				userId = u.getUserId();
		}
		Result<List<TotalPrice>> listTotalPriceResult = priceFeignClient.listTotalPrice(userId);
		return listTotalPriceResult;
	}
	
	@PostMapping("/listPriceItem")
	public Result<?> listPriceItem(Integer totalPriceId){
		if(totalPriceId == null) {
			return new Result<String>(ResultCode.PARAM_LOSS);
		}
		Result<List<UserPrice>> listPriceItemResult = priceFeignClient.listPriceItem(totalPriceId);
		return listPriceItemResult;
	}
	
	@PostMapping("/getProvinceAndCity")
	public Result<?> getProvinceAndCity(){
		return priceFeignClient.getProvinceAndCity();
	}
}
