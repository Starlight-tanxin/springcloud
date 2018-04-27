package com.wm.lejia.feign.hystric;

import java.util.List;

import org.springframework.stereotype.Component;

import com.wm.lejia.common.pojo.dto.HomeDTO;
import com.wm.lejia.common.pojo.dto.HomeDetailDTO;
import com.wm.lejia.common.pojo.dto.LoginDTO;
import com.wm.lejia.common.pojo.dto.PriceDTO;
import com.wm.lejia.common.pojo.dto.ServicePriceCalculationPriceDTO;
import com.wm.lejia.common.pojo.dto.TotalPriceDTO;
import com.wm.lejia.common.pojo.dto.UserDTO;
import com.wm.lejia.common.pojo.entity.Banner;
import com.wm.lejia.common.pojo.entity.BannerUser;
import com.wm.lejia.common.pojo.entity.City;
import com.wm.lejia.common.pojo.entity.Province;
import com.wm.lejia.common.pojo.entity.TotalPrice;
import com.wm.lejia.common.pojo.entity.User;
import com.wm.lejia.common.pojo.entity.UserPrice;
import com.wm.lejia.common.pojo.vo.AppointmentVO;
import com.wm.lejia.common.pojo.vo.ProvinceVO;
import com.wm.lejia.common.utils.Result;
import com.wm.lejia.common.utils.ResultCode;
import com.wm.lejia.feign.client.PriceFeignClient;

@Component
public class FallbackPriceHystric implements PriceFeignClient{

	@Override
	public Result<HomeDTO> createHomeInfo(HomeDTO homeDTO) {
		return new Result<>(ResultCode.BAD_REQUEST);
	}

	@Override
	public Result<List<HomeDetailDTO>> createHomeDetail(List<HomeDetailDTO> details) {
		return new Result<>(ResultCode.BAD_REQUEST);
	}

	@Override
	public Result<TotalPriceDTO> calculationPrice(ServicePriceCalculationPriceDTO calculationPriceDTO) {
		return new Result<>(ResultCode.BAD_REQUEST);
	}

	@Override
	public Result<User> checkUser(UserDTO userDTO) {
		return new Result<>(ResultCode.BAD_REQUEST);
	}

	@Override
	public Result<User> regUser(UserDTO userDTO) {
		return new Result<>(ResultCode.BAD_REQUEST);
	}

	@Override
	public ServicePriceCalculationPriceDTO getHome(Integer homeId) {
		return null;
	}

	@Override
	public Result<TotalPrice> createTotalPrice(TotalPrice totalPrice) {
		return new Result<>(ResultCode.BAD_REQUEST);
	}

	@Override
	public Result<Boolean> createPriceItem(List<PriceDTO> list) {
		return new Result<>(ResultCode.BAD_REQUEST);
	}

	@Override
	public Result<List<TotalPrice>> listTotalPrice(Integer userId) {
		return new Result<>(ResultCode.BAD_REQUEST);
	}

	@Override
	public Result<List<UserPrice>> listPriceItem(Integer totalPriceId) {
		return new Result<>(ResultCode.BAD_REQUEST);
	}

	@Override
	public Result<User> getUserByWechatOpenid(String wechatOpenid) {
		return new Result<>(ResultCode.BAD_REQUEST);
	}

	@Override
	public Result<List<ProvinceVO>> getProvinceAndCity() {
		return new Result<>(ResultCode.BAD_REQUEST);
	}

	@Override
	public Result<Province> createProvince(Province province) {
		return new Result<>(ResultCode.BAD_REQUEST);
	}

	@Override
	public Result<City> createCity(City city) {
		return new Result<>(ResultCode.BAD_REQUEST);
	}

	@Override
	public Result<List<Banner>> listBannerByHome(Banner banner) {
		return new Result<>(ResultCode.BAD_REQUEST);
	}

	@Override
	public Result<User> login(LoginDTO dto) {
		return new Result<>(ResultCode.BAD_REQUEST);
	}

	@Override
	public Result<List<City>> listCityByHome() {
		return new Result<>(ResultCode.BAD_REQUEST);
	}

	@Override
	public Result<?> addBannerUser(BannerUser bannerUser) {
		return new Result<>(ResultCode.BAD_REQUEST);
	}

	@Override
	public Result<List<AppointmentVO>> listBannerUser(Integer userId) {
		return new Result<>(ResultCode.BAD_REQUEST);
	}

	@Override
	public Result<?> sendSMSCode(String mobile) {
		return new Result<>(ResultCode.BAD_REQUEST);
	}

	@Override
	public Result<?> verifyMsmCode(String mobile, String code) {
		return new Result<>(ResultCode.BAD_REQUEST);
	}

	
}
