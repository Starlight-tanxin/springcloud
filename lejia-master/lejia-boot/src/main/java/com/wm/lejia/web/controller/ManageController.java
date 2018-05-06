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

import com.github.pagehelper.PageInfo;
import com.wm.lejia.pojo.dto.AddCityDTO;
import com.wm.lejia.pojo.dto.DefProvinceDTO;
import com.wm.lejia.pojo.dto.SeaBannerDTO;
import com.wm.lejia.pojo.dto.SeaBannerUserDTO;
import com.wm.lejia.pojo.dto.SeaCityDTO;
import com.wm.lejia.pojo.dto.SeaManageDTO;
import com.wm.lejia.pojo.dto.SeaPriceDTO;
import com.wm.lejia.pojo.dto.SeaTotalPriceDTO;
import com.wm.lejia.pojo.dto.SeaUserDTO;
import com.wm.lejia.pojo.dto.UpdatePriceDTO;
import com.wm.lejia.pojo.dto.UserVO;
import com.wm.lejia.pojo.entity.Banner;
import com.wm.lejia.pojo.entity.City;
import com.wm.lejia.pojo.entity.DecorationPrice;
import com.wm.lejia.pojo.entity.Manage;
import com.wm.lejia.pojo.entity.Province;
import com.wm.lejia.pojo.entity.TotalPrice;
import com.wm.lejia.pojo.entity.User;
import com.wm.lejia.pojo.entity.UserRemark;
import com.wm.lejia.pojo.vo.BannerUserVO;
import com.wm.lejia.pojo.vo.BannerVO;
import com.wm.lejia.pojo.vo.CalculationPriceVO;
import com.wm.lejia.pojo.vo.CityVO;
import com.wm.lejia.pojo.vo.PriceCityVO;
import com.wm.lejia.pojo.vo.ProvinceVO;
import com.wm.lejia.service.BannerService;
import com.wm.lejia.service.CityService;
import com.wm.lejia.service.ManageService;
import com.wm.lejia.service.PriceService;
import com.wm.lejia.service.UserService;
import com.wm.lejia.utils.Result;
import com.wm.lejia.utils.ResultCode;
import com.wm.lejia.utils.StringUtils;
import com.wm.lejia.utils.sign.MD5Utils;

@RestController
@RequestMapping("/lejia/manage")
public class ManageController {

	private static Logger log = LoggerFactory.getLogger(ManageController.class);

	@Autowired
	private ManageService manageService;
	
	@Autowired
	private CityService cityService;
	
	@Autowired
	private BannerService bannerService;
	
	@Autowired
	private PriceService priceService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/listCity")
	public Result<List<CityVO>> listCity() {
		List<CityVO> list = cityService.listCityAndProvince();
		if (list == null) {
			return new Result<>(ResultCode.QUERY_ERROR);
		}
		return new Result<List<CityVO>>(list);
	}
	
	@PostMapping("/getDefProvinceAndCity")
	public Result<List<DefProvinceDTO>> getDefProvinceAndCity() {
		List<DefProvinceDTO> list = cityService.getDefProvinceAndCity();
		return new Result<>(list);
	}

	@PostMapping("/upCity")
	public Result<?> upCity(@RequestBody City city) {
		if (ObjectUtils.isEmpty(city) || city.getCityId() == null || city.getUpdatedBy() == null) {
			return new Result<>(ResultCode.PARAM_LOSS);
		}
		// 上架
		city.setIsUp(1);
		int rows = cityService.updateCity(city);
		if (rows <= 0) {
			return new Result<>(ResultCode.DATA_UPDATE_ERROR);
		}
		return new Result<>();
	}

	@PostMapping("/downCity")
	public Result<?> downCity(@RequestBody City city) {
		if (ObjectUtils.isEmpty(city) || city.getCityId() == null || city.getUpdatedBy() == null) {
			return new Result<>(ResultCode.PARAM_LOSS);
		}
		// 下架
		city.setIsUp(0);
		int rows = cityService.updateCity(city);
		if (rows <= 0) {
			return new Result<>(ResultCode.DATA_UPDATE_ERROR);
		}
		return new Result<>();
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
		City city = cityService.getCity(c);
		// 不为null 为重复添加的数据
		if (!ObjectUtils.isEmpty(city)) {
			a = false;
			// 从软删除 或者下架状态回复
			if (city.getIsDeleted() != 0 || city.getIsUp() != 1) {
				city.setIsDeleted(0);
				city.setIsUp(1);
				city.setIsDeleted(dto.getIsDefault());
				city.setCreatedBy(dto.getUpdatedBy());
				cityService.updateCity(city);
			}
			return new Result<>();
		}
		Province p = dto.DTO2Province();
		Province province = cityService.getProvince(p);
		if (!ObjectUtils.isEmpty(province)) {
			if (province.getIsDeleted() != 0 || province.getIsUp() != 1) {
				province.setIsUp(1);
				province.setIsDeleted(0);
				province.setIsDefault(dto.getIsDefault());
				province.setUpdatedBy(dto.getUpdatedBy());
				cityService.updateProvince(province);
				if (a) {
					c.setProvinceId(p.getProvinceId());
					c = cityService.createCity(c);
					if (c.getCityId() == null) {
						return new Result<>(ResultCode.DATA_UPDATE_ERROR);
					}
					return new Result<>();
				}
			}
		}
		p  = cityService.createProvince(p);
		if (province.getProvinceId() == null) {
			return new Result<>(ResultCode.INSERT_ERROR,"添加省份信息失败");
		}
		c.setProvinceId(p.getProvinceId());
		c = cityService.createCity(c);
		if (c.getCityId() == null) {
			return new Result<>(ResultCode.INSERT_ERROR,"添加城市信息失败");
		}
		return new Result<>();
	}


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

	@PostMapping("/listBanner")
	public Result<PageInfo<BannerVO>> listBanner(@RequestBody SeaBannerDTO dto){
		log.info("BannerController   listBanner ===> SeaBannerDTO : " + dto.toString());
		return bannerService.listBanner(dto);
	}
	
	@PostMapping("/addBanner")
	public Result<Banner> addBanner(@RequestBody Banner banner){
		if (ObjectUtils.isEmpty(banner) || banner.isEmpty()) {
			return new Result<>(ResultCode.PARAM_LOSS);
		}
		Banner b = bannerService.addBanner(banner);
		if(ObjectUtils.isEmpty(b)) {
			return new Result<>(ResultCode.INSERT_ERROR);
		}
		return new Result<>(b);
	}
	
	@PostMapping("/updateBanner")
	public Result<?> updateBanner(@RequestBody Banner banner){
		if (ObjectUtils.isEmpty(banner) || banner.getBannerId() == null) {
			return new Result<>(ResultCode.PARAM_LOSS);
		}
		int rows = bannerService.updateBanner(banner);
		if(rows <= 0) {
			return new Result<>(ResultCode.DATA_UPDATE_ERROR);
		}
		return new Result<>();
	}
	
	
	@PostMapping("/upBanner")
	public Result<?> upBanner(@RequestBody Banner banner) {
		if (ObjectUtils.isEmpty(banner) || banner.getBannerId() == null || banner.getUpdatedBy() == null) {
			return new Result<>(ResultCode.PARAM_LOSS);
		}
		banner.setIsUp(1);
		int rows = bannerService.updateBanner(banner);
		if(rows <= 0) {
			return new Result<>(ResultCode.DATA_UPDATE_ERROR);
		}
		return new Result<>();
	}

	@PostMapping("/dowmBanner")
	public Result<?> dowmBanner(@RequestBody Banner banner) {
		if (ObjectUtils.isEmpty(banner) || banner.getBannerId() == null || banner.getUpdatedBy() == null) {
			return new Result<>(ResultCode.PARAM_LOSS);
		}
		banner.setIsUp(0);
		int rows = bannerService.updateBanner(banner);
		if(rows <= 0) {
			return new Result<>(ResultCode.DATA_UPDATE_ERROR);
		}
		return new Result<>();
	}
	
	@PostMapping("/listBannerUser")
	public Result<PageInfo<BannerUserVO>> listBannerUser(@RequestBody SeaBannerUserDTO dto){
		return bannerService.listBannerUser(dto);
	}
	
	@PostMapping("/updatePriceSetting")
	public Result<?> updatePriceSetting(@RequestBody UpdatePriceDTO dto) {
		if (ObjectUtils.isEmpty(dto) || dto.isEmpty()) {
			return new Result<>(ResultCode.PARAM_LOSS);
		}
		try {
			Result<?> result = priceService.updatePriceSetting(dto);
			return result;
		} catch (Exception e) {
			// e.printStackTrace();
			log.error("PriceController   updatePriceSetting ===> 更新区域价格出错", e);
		}
		return new Result<>(ResultCode.SERVER_ERROR);
	}
	
	@PostMapping("/listCityPrice")
	public Result<List<PriceCityVO>> listCity(@RequestBody SeaCityDTO dto) {
		Result<List<PriceCityVO>> result = priceService.listCity(dto);
		return result;
	}
	
	@PostMapping("/listCalculationPrice")
	public Result<PageInfo<CalculationPriceVO>> listCalculationPrice(@RequestBody SeaPriceDTO dto) {
		Result<PageInfo<CalculationPriceVO>> result = priceService.listCalculationPrice(dto);
		return result;
	}
	
	@PostMapping("/listPriceByCity")
	public Result<List<DecorationPrice>> listPriceByCity(Integer provinceId, Integer cityId) {
		if (provinceId == null || cityId == null) {
			return new Result<>(ResultCode.PARAM_LOSS);
		}
		Result<List<DecorationPrice>> result = priceService.listPriceByCity(provinceId, cityId);
		return result;
	}
	
	@PostMapping("/listUserBackByCondition")
	public Result<PageInfo<UserVO>> listUserBackByCondition(@RequestBody SeaUserDTO dto) {
		Result<PageInfo<UserVO>> result = userService.listUserBackByCondition(dto);
		return result;
	}
	
	@PostMapping("/listUserRemark")
	public Result<List<UserRemark>> listUserRemark(Integer userId) {
		if (userId == null) {
			return new Result<>(ResultCode.PARAM_LOSS);
		}
		Result<List<UserRemark>> result = userService.listUserRemark(userId);
		return result;
	}
	
	@PostMapping("/listTotalPriceByCondition")
	public Result<List<TotalPrice>> listTotalPriceByCondition(@RequestBody SeaTotalPriceDTO dto) {
		if (ObjectUtils.isEmpty(dto) || dto.getUserId() == null) {
			return new Result<>(ResultCode.PARAM_LOSS);
		}
		log.info("UserController   listTotalPriceByCondition ===> dto :" + dto.toString());
		List<TotalPrice> list = userService.listTotalPriceByCondition(dto);
		if (ObjectUtils.isEmpty(list)) {
			return new Result<>(ResultCode.QUERY_ERROR);
		}
		return new Result<>(list);
	}
	
	@PostMapping("/updateUser")
	public Result<?> updateUser(@RequestBody User user) {
		if(ObjectUtils.isEmpty(user) || user.getUserId() == null || user.getUpdatedBy() == null) {
			return new Result<>(ResultCode.PARAM_LOSS);
		}
		Result<?> result = userService.updateUser(user);
		return result;
	}
	
	@PostMapping("/addUserRemark")
	public Result<UserRemark> addUserRemark(@RequestBody UserRemark userRemark) {
		if (ObjectUtils.isEmpty(userRemark) || userRemark.getUserId() == null
				|| StringUtils.isEmptyStr(userRemark.getContent()) || userRemark.getCreatedBy() == null) {
			return new Result<>(ResultCode.PARAM_LOSS);
		}
		UserRemark ur = userService.addUserRemark(userRemark);
		if (ObjectUtils.isEmpty(ur)) {
			return new Result<>(ResultCode.INSERT_ERROR);
		}
		return new Result<UserRemark>(ur);
	}
	
	@PostMapping("/listManageByCondition")
	public Result<PageInfo<Manage>> listManageByCondition(@RequestBody SeaManageDTO dto) {
		log.info("ManageController   listManageByCondition ===> dto : " + dto.toString());
		Result<PageInfo<Manage>> result = manageService.listManageByCondition(dto);
		return result;
	}

	@PostMapping("/updateManage")
	public Result<Manage> updateManage(@RequestBody Manage manage) {
		if (ObjectUtils.isEmpty(manage) || manage.getManageId() == null || manage.getUpdatedBy() == null) {
			return new Result<>(ResultCode.PARAM_LOSS);
		}
		String pwd = manage.getPassword();
		if (!StringUtils.isEmptyStr(pwd)) {
			manage.setPassword(MD5Utils.MD5Encode(pwd, "UTF-8"));
		}
		Result<Manage> result = manageService.updateManage(manage);
		return result;
	}

	@PostMapping("/addManage")
	public Result<Manage> addManage(@RequestBody Manage manage) {
		if (ObjectUtils.isEmpty(manage) || StringUtils.isEmptyStr(manage.getUsername())
				|| StringUtils.isEmptyStr(manage.getPassword()) || manage.getUpdatedBy() == null) {
			return new Result<>(ResultCode.PARAM_LOSS);
		}
		String pwd = manage.getPassword();
		if (!StringUtils.isEmptyStr(pwd)) {
			manage.setPassword(MD5Utils.MD5Encode(pwd, "UTF-8"));
		}
		Result<Manage> result = manageService.addManage(manage);
		return result;
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

}
