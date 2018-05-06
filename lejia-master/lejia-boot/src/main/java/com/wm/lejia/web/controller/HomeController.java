package com.wm.lejia.web.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wm.lejia.db.mapper.VerifyCodeMapper;
import com.wm.lejia.pojo.dto.CalculationPriceDTO;
import com.wm.lejia.pojo.dto.LoginDTO;
import com.wm.lejia.pojo.entity.Banner;
import com.wm.lejia.pojo.entity.BannerUser;
import com.wm.lejia.pojo.entity.City;
import com.wm.lejia.pojo.entity.Home;
import com.wm.lejia.pojo.entity.HomeDetail;
import com.wm.lejia.pojo.entity.User;
import com.wm.lejia.pojo.entity.VerifyCode;
import com.wm.lejia.pojo.vo.AppointmentVO;
import com.wm.lejia.pojo.vo.TotalPriceVO;
import com.wm.lejia.service.BannerService;
import com.wm.lejia.service.CityService;
import com.wm.lejia.service.PriceService;
import com.wm.lejia.service.UserService;
import com.wm.lejia.utils.Result;
import com.wm.lejia.utils.ResultCode;
import com.wm.lejia.utils.ServiceUtils;
import com.wm.lejia.utils.StringUtils;
import com.wm.lejia.utils.http.HttpUtils;
import com.wm.lejia.utils.sign.MD5Utils;

/**
 * 整合后的前端网站接口
 * @author TanXin
 *
 */
@RestController
@RequestMapping("/lejia/home")
public class HomeController {
	
	private static Logger log = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BannerService bannerService;
	
	@Autowired
	private PriceService priceService;
	
	@Autowired
	private CityService cityService;
	
	@Autowired
	private VerifyCodeMapper verifyCodeMapper;
	
	
	
	/** 用户名 */
	private  String uid = "189262";
	/** 密码 */
	private  String pwd = "baojun2016";
	/** 接口地址 */
	private  String api_url = "http://http.yunsms.cn/tx/";
	
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
		return userService.login(dto);
	}

	@PostMapping("/listBannerByHome")
	public Result<?> listBannerByHome(@RequestBody Banner banner) {
		if (ObjectUtils.isEmpty(banner) || banner.getCityId() == null) {
			return new Result<String>(ResultCode.PARAM_LOSS);
		}
		List<Banner> list = bannerService.listBannerByHome(banner);
		if(list == null) {
			return new Result<>(ResultCode.QUERY_ERROR);
		}
		return new Result<>(list);
	}

	@PostMapping("/sumPrice")
	public Result<?> sumPrice(@RequestBody CalculationPriceDTO dto) {
		if (ObjectUtils.isEmpty(dto)) {
			return new Result<String>(ResultCode.PARAM_LOSS);
		}
		Home home = dto;
		List<HomeDetail> details = dto.getDetails();
		if (ObjectUtils.isEmpty(home) || ObjectUtils.isEmpty(details)) {
			return new Result<String>(ResultCode.PARAM_LOSS, "home 或者 details 值为NULL");
		}
		if (StringUtils.isEmptyStr(home.getMobile()) || StringUtils.isEmptyStr(home.getNickname())) {
			return new Result<String>(ResultCode.PARAM_LOSS, "nickname 或者 mobile 值为NULL");
		}
		User user = userService.checkUser(home);
		if (ObjectUtils.isEmpty(user)) {
			user = userService.regUser(home.DTO2User());
		} 
		Integer userId = user.getUserId();
		home.setUserId(userId);
		log.info("HomeController   sumPrice ===> user : " + user.toString());
		home = priceService.createHome(home);
		if(ObjectUtils.isEmpty(home)) {
			return new Result<String>(ResultCode.INSERT_ERROR,"创建房间信息失败");
		}
		Integer homeId = home.getHomeId();
		for (HomeDetail detail : details) {
			detail.setHomeId(homeId);
			detail.setCreatedBy(userId);
			dto.setHomeId(homeId);
			dto.setUserId(userId);
		}
		details = ServiceUtils.createHomeDetail(homeId, userId, details, dto);
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
		if (list == null) {
			return new Result<String>(ResultCode.INSERT_ERROR,"创建装修详情信息失败");
		}
		dto.setDetails(details);
		TotalPriceVO vo = priceService.calculationPrice(dto);
		if(ObjectUtils.isEmpty(vo)) {
			return new Result<>(ResultCode.BAD_REQUEST,"计算价格出错");
		}
		return new Result<>(vo);
	}

	@PostMapping("/listCityByHome")
	public Result<?> listCityByHome() {
		List<City> list = cityService.listCityByHome();
		return new Result<>(list);
	}

	@PostMapping("/addBannerUser")
	public Result<?> addBannerUser(@RequestBody BannerUser bannerUser) {
		if (ObjectUtils.isEmpty(bannerUser) || bannerUser.isEmpty()) {
			return new Result<>(ResultCode.PARAM_LOSS);
		}
		try {
			Result<?> result = bannerService.addBannerUser(bannerUser);
			return result;
		} catch (Exception e) {
			// e.printStackTrace();
			log.error("BannerController   addBannerUser ===> 出现未知错误",e);
		}
		return new Result<>(ResultCode.INSERT_ERROR,"本接口出现未知错误");
	}
	
	@PostMapping("/listBannerUser")
	public Result<?> listBannerUser(Integer userId){
		if(userId == null) {
			return new Result<>(ResultCode.PARAM_LOSS);
		}
		List<AppointmentVO> list = bannerService.listBannerUser(userId);
		if(list == null) {
			return new Result<>(ResultCode.QUERY_ERROR);
		}
		return new Result<>(list);
	}
	
	
	
	
	
	@PostMapping("/sendSMSCode")
	public Result<?> sendSMSCode(String mobile) {
		if(StringUtils.isEmptyStr(mobile)) {
			return new Result<>(ResultCode.PARAM_LOSS);
		}
		Map<String, String> params = getParamsMap(mobile);
		 try {
			 String result = HttpUtils.doPost(this.api_url, params);
			 if("100".equals(result)){
					return new Result<String>();
				}
		} catch (ClientProtocolException e) {
			//e.printStackTrace();
			log.error("CodeController   sendSMSCode ===> 发送请求异常",e);
		} catch (IOException e) {
			//e.printStackTrace();
			log.error("CodeController   sendSMSCode ===> 发送请求异常",e);
		}
		return new Result<String>(ResultCode.MSM_CODE_SEND_ERROR);
	}
	
	@PostMapping("/verifyMsmCode")
	public Result<?> verifyMsmCode(String mobile, String code) {
		if (StringUtils.isEmptyStr(mobile) || StringUtils.isEmptyStr(code)) {
			return new Result<>(ResultCode.CODE_ERROR);
		}
		VerifyCode vc = verifyCodeMapper.getVerifyCode(mobile);
		if(ObjectUtils.isEmpty(vc)) {
			return new Result<>(ResultCode.CODE_ERROR);
		}
		if(code.equals(vc.getCode().trim())){
			verifyCodeMapper.deleteVerifyCode(mobile);
			return new Result<>();
		}
		return new Result<>(ResultCode.CODE_ERROR);
	}
	
	
	private Map<String, String> getParamsMap(String mobile){
		String pwd = MD5Utils.MD5Encode(this.pwd, "UTF-8");
		Map<String, String> param = new HashMap<String, String>();
		param.put("uid", this.uid);
		param.put("pwd", pwd);
		param.put("mobile", mobile);
		param.put("content", createSMSCodeBody(mobile));
		param.put("encode", "utf8");
		return param;
	}
	
	/**
	 * 新创建
	 * @author tanxin
	 * @param mobile
	 * @return
	 */
	private String createSMSCodeBody(String mobile){
		int r = (int)((Math.random()*9+1)*1000);
		String code = String.valueOf(r);
		String body = "您的验证码为"+code+"，请勿告诉他人!";
		VerifyCode vc = new VerifyCode();
		vc.setCode(code);
		vc.setMobile(mobile);
		vc.setCreatedTime(new Date());
		verifyCodeMapper.insertSelective(vc);
		return body;
	}
	
}
