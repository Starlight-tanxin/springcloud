package com.wm.lejia.feign.client;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.wm.lejia.common.pojo.dto.DefProvinceDTO;
import com.wm.lejia.common.pojo.dto.PageInfoDTO;
import com.wm.lejia.common.pojo.dto.SeaBannerDTO;
import com.wm.lejia.common.pojo.dto.SeaBannerUserDTO;
import com.wm.lejia.common.pojo.dto.SeaCityDTO;
import com.wm.lejia.common.pojo.dto.SeaManageDTO;
import com.wm.lejia.common.pojo.dto.SeaPriceDTO;
import com.wm.lejia.common.pojo.dto.SeaTotalPriceDTO;
import com.wm.lejia.common.pojo.dto.SeaUserDTO;
import com.wm.lejia.common.pojo.dto.UpdatePriceDTO;
import com.wm.lejia.common.pojo.dto.UserVO;
import com.wm.lejia.common.pojo.entity.Banner;
import com.wm.lejia.common.pojo.entity.City;
import com.wm.lejia.common.pojo.entity.DecorationPrice;
import com.wm.lejia.common.pojo.entity.Manage;
import com.wm.lejia.common.pojo.entity.Province;
import com.wm.lejia.common.pojo.entity.TotalPrice;
import com.wm.lejia.common.pojo.entity.User;
import com.wm.lejia.common.pojo.entity.UserRemark;
import com.wm.lejia.common.pojo.vo.BannerUserVO;
import com.wm.lejia.common.pojo.vo.BannerVO;
import com.wm.lejia.common.pojo.vo.CalculationPriceVO;
import com.wm.lejia.common.pojo.vo.CityVO;
import com.wm.lejia.common.pojo.vo.PriceCityVO;
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
	
	@PostMapping(value = "/manage/banner/listBanner", consumes = "application/json")
	Result<PageInfoDTO<BannerVO>> listBanner(@RequestBody SeaBannerDTO dto);
	
	@PostMapping(value = "/manage/banner/addBanner", consumes = "application/json")
	Result<Banner> addBanner(@RequestBody Banner banner);
	
	@PostMapping(value = "/manage/banner/updateBanner", consumes = "application/json")
	Result<?> updateBanner(@RequestBody Banner banner);
	
	@PostMapping(value = "/manage/banner/listBannerUser", consumes = "application/json")
	Result<PageInfoDTO<BannerUserVO>> listBannerUser(@RequestBody SeaBannerUserDTO dto);
	
	@PostMapping(value = "/manage/price/updatePriceSetting", consumes = "application/json")
	Result<?> updatePriceSetting(@RequestBody UpdatePriceDTO dto);
	
	@PostMapping(value = "/manage/price/listCity", consumes = "application/json")
	Result<List<PriceCityVO>> listCity(@RequestBody SeaCityDTO dto);
	
	@PostMapping(value = "/manage/price/listPriceByCity")
	Result<List<DecorationPrice>> listPriceByCity(@RequestParam("provinceId") Integer provinceId,@RequestParam("cityId") Integer cityId);

	@PostMapping(value = "/manage/price/listCalculationPrice", consumes = "application/json")
	Result<PageInfoDTO<CalculationPriceVO>> listCalculationPrice(@RequestBody SeaPriceDTO dto);
	
	@PostMapping(value = "/manage/user/listUserBackByCondition", consumes = "application/json")
	Result<PageInfoDTO<UserVO>> listUserBackByCondition(@RequestBody SeaUserDTO dto);
	
	@PostMapping(value = "/manage/user/listUserRemark")
	Result<List<UserRemark>> listUserRemark(@RequestParam("userId")Integer userId);
	
	@PostMapping(value = "/manage/user/listTotalPriceByCondition", consumes = "application/json")
	Result<List<TotalPrice>> listTotalPriceByCondition(@RequestBody SeaTotalPriceDTO dto);
	
	@PostMapping(value = "/manage/user/updateUser", consumes = "application/json")
	Result<?> updateUser(@RequestBody User user);
	
	@PostMapping(value = "/manage/user/addUserRemark", consumes = "application/json")
	Result<UserRemark> addUserRemark(@RequestBody UserRemark userRemark);
	
	@PostMapping(value = "/manage/manage/listManageByCondition", consumes = "application/json")
	Result<PageInfoDTO<Manage>> listManageByCondition(@RequestBody SeaManageDTO dto);
	
	@PostMapping(value = "/manage/manage/addManage", consumes = "application/json")
	Result<Manage> addManage(@RequestBody Manage manage);
	
	@PostMapping(value = "/manage/manage/updateManage", consumes = "application/json")
	Result<Manage> updateManage(@RequestBody Manage manage);
}
