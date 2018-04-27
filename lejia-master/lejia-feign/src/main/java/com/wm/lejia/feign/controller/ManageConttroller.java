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
import com.wm.lejia.common.pojo.dto.SeaBannerDTO;
import com.wm.lejia.common.pojo.dto.SeaBannerUserDTO;
import com.wm.lejia.common.pojo.dto.SeaCityDTO;
import com.wm.lejia.common.pojo.dto.SeaManageDTO;
import com.wm.lejia.common.pojo.dto.SeaPriceDTO;
import com.wm.lejia.common.pojo.dto.SeaTotalPriceDTO;
import com.wm.lejia.common.pojo.dto.SeaUserDTO;
import com.wm.lejia.common.pojo.dto.UpdatePriceDTO;
import com.wm.lejia.common.pojo.entity.Banner;
import com.wm.lejia.common.pojo.entity.City;
import com.wm.lejia.common.pojo.entity.Manage;
import com.wm.lejia.common.pojo.entity.Province;
import com.wm.lejia.common.pojo.entity.User;
import com.wm.lejia.common.pojo.entity.UserRemark;
import com.wm.lejia.common.pojo.vo.CityVO;
import com.wm.lejia.common.utils.Result;
import com.wm.lejia.common.utils.ResultCode;
import com.wm.lejia.common.utils.StringUtils;
import com.wm.lejia.common.utils.sign.MD5Utils;
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
			if (province.getIsDeleted() != 0 || province.getIsUp() != 1) {
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
		if (createProvince.getCode() != 200) {
			return createProvince;
		}
		p = createProvince.getData();
		c.setProvinceId(p.getProvinceId());
		Result<City> createCity = manageFeignClient.createCity(c);
		return createCity;
	}

	@PostMapping("/listBanner")
	public Result<?> listBanner(@RequestBody SeaBannerDTO dto) {
		return manageFeignClient.listBanner(dto);
	}

	@PostMapping("/addBanner")
	public Result<?> addBanner(@RequestBody Banner banner) {
		if (ObjectUtils.isEmpty(banner) || banner.isEmpty()) {
			return new Result<>(ResultCode.PARAM_LOSS);
		}
		banner.setCreatedBy(banner.getUpdatedBy());
		banner.setUpdatedBy(null);
		return manageFeignClient.addBanner(banner);
	}

	@PostMapping("/updateBanner")
	public Result<?> updateBanner(@RequestBody Banner banner) {
		if (ObjectUtils.isEmpty(banner) || banner.getBannerId() == null) {
			return new Result<>(ResultCode.PARAM_LOSS);
		}
		return manageFeignClient.updateBanner(banner);
	}

	@PostMapping("/upBanner")
	public Result<?> upBanner(@RequestBody Banner banner) {
		if (ObjectUtils.isEmpty(banner) || banner.getBannerId() == null || banner.getUpdatedBy() == null) {
			return new Result<>(ResultCode.PARAM_LOSS);
		}
		banner.setIsUp(1);
		return manageFeignClient.updateBanner(banner);
	}

	@PostMapping("/dowmBanner")
	public Result<?> dowmBanner(@RequestBody Banner banner) {
		if (ObjectUtils.isEmpty(banner) || banner.getBannerId() == null || banner.getUpdatedBy() == null) {
			return new Result<>(ResultCode.PARAM_LOSS);
		}
		banner.setIsUp(0);
		return manageFeignClient.updateBanner(banner);
	}

	@PostMapping("/listBannerUser")
	public Result<?> listBannerUser(@RequestBody SeaBannerUserDTO dto) {
		return manageFeignClient.listBannerUser(dto);
	}

	@PostMapping("/updatePriceSetting")
	public Result<?> updatePriceSetting(@RequestBody UpdatePriceDTO dto) {
		if (ObjectUtils.isEmpty(dto) || dto.isEmpty()) {
			return new Result<>(ResultCode.PARAM_LOSS);
		}
		return manageFeignClient.updatePriceSetting(dto);
	}

	@PostMapping("/listCityPrice")
	public Result<?> listCity(@RequestBody SeaCityDTO dto) {
		return manageFeignClient.listCity(dto);
	}

	@PostMapping("/listCalculationPrice")
	public Result<?> listCalculationPrice(@RequestBody SeaPriceDTO dto) {
		return manageFeignClient.listCalculationPrice(dto);
	}

	@PostMapping("/listPriceByCity")
	public Result<?> listPriceByCity(Integer provinceId, Integer cityId) {
		if (provinceId == null || cityId == null) {
			return new Result<>(ResultCode.PARAM_LOSS);
		}
		return manageFeignClient.listPriceByCity(provinceId, cityId);
	}

	@PostMapping("/manage/user/listUserBackByCondition")
	public Result<?> listUserBackByCondition(@RequestBody SeaUserDTO dto) {
		return manageFeignClient.listUserBackByCondition(dto);
	}

	@PostMapping("/listUserRemark")
	public Result<?> listUserRemark(Integer userId) {
		if (userId == null) {
			return new Result<>(ResultCode.PARAM_LOSS);
		}
		return manageFeignClient.listUserRemark(userId);
	}

	@PostMapping("/listTotalPriceByCondition")
	public Result<?> listTotalPriceByCondition(@RequestBody SeaTotalPriceDTO dto) {
		if (ObjectUtils.isEmpty(dto) || dto.getUserId() == null) {
			return new Result<>(ResultCode.PARAM_LOSS);
		}
		return manageFeignClient.listTotalPriceByCondition(dto);
	}

	@PostMapping("/updateUser")
	public Result<?> updateUser(@RequestBody User user){
		if(ObjectUtils.isEmpty(user) || user.getUserId() == null || user.getUpdatedBy() == null) {
			return new Result<>(ResultCode.PARAM_LOSS);
		}
		return manageFeignClient.updateUser(user);
	}

	@PostMapping("/addUserRemark")
	public Result<?> addUserRemark(@RequestBody UserRemark userRemark) {
		if (ObjectUtils.isEmpty(userRemark) || userRemark.getUserId() == null
				|| StringUtils.isEmptyStr(userRemark.getContent()) || userRemark.getCreatedBy() == null) {
			return new Result<>(ResultCode.PARAM_LOSS);
		}
		return manageFeignClient.addUserRemark(userRemark);
	}

	@PostMapping("/listManageByCondition")
	public Result<?> listManageByCondition(@RequestBody SeaManageDTO dto) {
		return manageFeignClient.listManageByCondition(dto);
	}

	@PostMapping("/updateManage")
	public Result<?> updateManage(@RequestBody Manage manage) {
		if (ObjectUtils.isEmpty(manage) || manage.getManageId() == null || manage.getUpdatedBy() == null) {
			return new Result<>(ResultCode.PARAM_LOSS);
		}
		String pwd = manage.getPassword();
		if (!StringUtils.isEmptyStr(pwd)) {
			manage.setPassword(MD5Utils.MD5Encode(pwd, "UTF-8"));
		}
		return manageFeignClient.updateManage(manage);
	}

	@PostMapping("/addManage")
	public Result<?> addManage(@RequestBody Manage manage) {
		if (ObjectUtils.isEmpty(manage) || StringUtils.isEmptyStr(manage.getUsername())
				|| StringUtils.isEmptyStr(manage.getPassword()) || manage.getUpdatedBy() == null) {
			return new Result<>(ResultCode.PARAM_LOSS);
		}
		String pwd = manage.getPassword();
		if (!StringUtils.isEmptyStr(pwd)) {
			manage.setPassword(MD5Utils.MD5Encode(pwd, "UTF-8"));
		}
		return manageFeignClient.updateManage(manage);
	}

}
