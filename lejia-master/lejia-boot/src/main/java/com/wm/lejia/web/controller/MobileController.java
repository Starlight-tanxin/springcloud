package com.wm.lejia.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wm.lejia.pojo.dto.CalculationPriceDTO;
import com.wm.lejia.pojo.dto.HomeDTO;
import com.wm.lejia.pojo.dto.TotalPriceDTO;
import com.wm.lejia.pojo.dto.UserDTO;
import com.wm.lejia.pojo.entity.Home;
import com.wm.lejia.pojo.entity.HomeDetail;
import com.wm.lejia.pojo.entity.TotalPrice;
import com.wm.lejia.pojo.entity.User;
import com.wm.lejia.pojo.entity.UserPrice;
import com.wm.lejia.pojo.vo.ProvinceVO;
import com.wm.lejia.pojo.vo.TotalPriceVO;
import com.wm.lejia.service.CityService;
import com.wm.lejia.service.PriceService;
import com.wm.lejia.service.UserService;
import com.wm.lejia.utils.Result;
import com.wm.lejia.utils.ResultCode;
import com.wm.lejia.utils.ServiceUtils;
import com.wm.lejia.utils.StringUtils;

/**
 * 整合后的 小程序接口
 * 
 * @author TanXin
 *
 */
@RestController
@RequestMapping("/lejia/mobile")
public class MobileController {

	private static Logger log = LoggerFactory.getLogger(MobileController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private PriceService priceService;

	@Autowired
	private CityService cityService;

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
		if (StringUtils.isEmptyStr(userDTO.getMobile()) || StringUtils.isEmptyStr(userDTO.getNickname())
				|| StringUtils.isEmptyStr(userDTO.getWechatOpenid())) {
			return new Result<String>(ResultCode.PARAM_LOSS);
		}
		User user = userService.checkUser(userDTO);
		if (ObjectUtils.isEmpty(user)) {
			user = userDTO.DTO2User();
			userService.regUser(user);
		}
		Integer userId = user.getUserId();
		dto.setUserId(userId);
		Home home = dto.DTO2Home();
		home = priceService.createHome(home);
		if (ObjectUtils.isEmpty(home)) {
			return new Result<>(ResultCode.INSERT_ERROR);
		}
		return new Result<>(home);
	}

	@PostMapping("/sumPrice")
	public Result<?> sumPrice(@RequestBody List<HomeDetail> details) {
		if (ObjectUtils.isEmpty(details)) {
			return new Result<String>(ResultCode.PARAM_LOSS);
		}
		Integer homeId = details.get(0).getHomeId();
		Integer createdBy = details.get(0).getCreatedBy();
		CalculationPriceDTO calculationPriceDTO = priceService.getCalculationPriceById(homeId);
		if (ObjectUtils.isEmpty(calculationPriceDTO)) {
			return new Result<String>(ResultCode.DATA_NOT_EXIST);
		}
		details = ServiceUtils.createHomeDetail(homeId, createdBy, details, calculationPriceDTO);
		for (HomeDetail detail : details) {
			// 选项名字
			String projectName = detail.getInfo();
			projectName = (StringUtils.isEmptyStr(projectName) ? "" : projectName.trim());
			// 区域
			String homeDetailType = detail.getHomeDetailType();
			homeDetailType = (StringUtils.isEmptyStr(homeDetailType) ? "" : homeDetailType.trim());
			// 格式化 homeid
			Integer formatHomeId = ServiceUtils.formatHomeId(projectName, homeDetailType);
			if (formatHomeId != null) {
				detail.setDecorationId(formatHomeId);
			}
		}
		List<HomeDetail> list = priceService.createHomeDetails(details);
		if (ObjectUtils.isEmpty(list)) {
			return new Result<>(ResultCode.INSERT_ERROR);
		}
		calculationPriceDTO.setDetails(details);
		TotalPriceVO vo = priceService.calculationPrice(calculationPriceDTO);
		return new Result<>(vo);
	}

	@PostMapping("/saveCalculationPrice")
	public Result<?> saveCalculationPrice(@RequestBody TotalPriceDTO totalPriceDTO) {
		if (ObjectUtils.isEmpty(totalPriceDTO) || ObjectUtils.isEmpty(totalPriceDTO.getPriceItem())
				|| totalPriceDTO.getUserId() == null) {
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
		totalPrice = priceService.createTotalPrice(totalPrice);
		if (ObjectUtils.isEmpty(totalPrice) || totalPrice.getTotalPriceId() == null) {
			return new Result<String>(ResultCode.INSERT_ERROR, "保存总价格失败");
		}
		List<UserPrice> priceDTOs = totalPriceDTO.getPriceItem();
		for (UserPrice up : priceDTOs) {
			up.setCreatedBy(userId);
			up.setTotalPriceId(totalPrice.getTotalPriceId());
		}
		Boolean b = priceService.createPriceItem(priceDTOs);
		if (!b.booleanValue()) {
			return new Result<>(ResultCode.INSERT_ERROR, "保存价格条目失败");
		}
		return new Result<>();
	}

	@PostMapping("/listTotalPrice")
	public Result<?> listTotalPrice(Integer userId, String wechatOpenid) {
		if (userId == null && StringUtils.isEmptyStr(wechatOpenid)) {
			return new Result<String>(ResultCode.PARAM_LOSS);
		}
		if (userId == null) {
			UserDTO dto = new UserDTO();
			dto.setWechatOpenid(wechatOpenid);
			User user = userService.getUser(dto);
			if (ObjectUtils.isEmpty(user)) {
				return new Result<String>(ResultCode.ACCOUNT_NO_EXIT,
						"无法根据wechatOpenid找到这个哦用户 : wechatOpenid :" + wechatOpenid);
			}
			userId = user.getUserId();
		}
		List<TotalPrice> list = priceService.listTotalPrice(userId);
		return new Result<>(list);
	}

	@PostMapping("/listPriceItem")
	public Result<?> listPriceItem(Integer totalPriceId) {
		if (totalPriceId == null) {
			return new Result<String>(ResultCode.PARAM_LOSS);
		}
		List<UserPrice> list = priceService.listUserPrice(totalPriceId);
		return new Result<>(list);
	}

	@PostMapping("/getProvinceAndCity")
	public Result<?> getProvinceAndCity() {
		List<ProvinceVO> list = cityService.getCity();
		return new Result<>(list);
	}

}
