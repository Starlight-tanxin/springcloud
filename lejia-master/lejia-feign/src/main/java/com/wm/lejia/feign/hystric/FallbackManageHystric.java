package com.wm.lejia.feign.hystric;

import java.util.List;

import org.springframework.stereotype.Component;

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
import com.wm.lejia.common.utils.ResultCode;
import com.wm.lejia.feign.client.ManageFeignClient;

@Component
public class FallbackManageHystric implements ManageFeignClient {

	@Override
	public Result<Province> createProvince(Province province) {
		return new Result<>(ResultCode.BAD_REQUEST);
	}

	@Override
	public Result<City> createCity(City city) {
		return new Result<>(ResultCode.BAD_REQUEST);
	}

	@Override
	public Result<List<DefProvinceDTO>> getDefProvinceAndCity() {
		return new Result<>(ResultCode.BAD_REQUEST);
	}

	@Override
	public Result<List<CityVO>> listCity() {
		return new Result<>(ResultCode.BAD_REQUEST);
	}

	@Override
	public Result<City> getCity(City city) {
		return new Result<>(ResultCode.BAD_REQUEST);
	}

	@Override
	public Result<Province> getProvince(Province province) {
		return new Result<>(ResultCode.BAD_REQUEST);
	}

	@Override
	public Result<?> updateCity(City city) {
		return new Result<>(ResultCode.BAD_REQUEST);
	}

	@Override
	public Result<?> updateProvince(Province province) {
		return new Result<>(ResultCode.BAD_REQUEST);
	}

	@Override
	public Result<PageInfoDTO<BannerVO>> listBanner(SeaBannerDTO dto) {
		return new Result<>(ResultCode.BAD_REQUEST);
	}

	@Override
	public Result<Banner> addBanner(Banner banner) {
		return new Result<>(ResultCode.BAD_REQUEST);
	}

	@Override
	public Result<?> updateBanner(Banner banner) {
		return new Result<>(ResultCode.BAD_REQUEST);
	}

	@Override
	public Result<PageInfoDTO<BannerUserVO>> listBannerUser(SeaBannerUserDTO dto) {
		return new Result<>(ResultCode.BAD_REQUEST);
	}

	@Override
	public Result<?> updatePriceSetting(UpdatePriceDTO dto) {
		return new Result<>(ResultCode.BAD_REQUEST);
	}

	@Override
	public Result<List<PriceCityVO>> listCity(SeaCityDTO dto) {
		return new Result<>(ResultCode.BAD_REQUEST);
	}

	@Override
	public Result<List<DecorationPrice>> listPriceByCity(Integer provinceId, Integer cityId) {
		return new Result<>(ResultCode.BAD_REQUEST);
	}

	@Override
	public Result<PageInfoDTO<CalculationPriceVO>> listCalculationPrice(SeaPriceDTO dto) {
		return new Result<>(ResultCode.BAD_REQUEST);
	}

	@Override
	public Result<PageInfoDTO<UserVO>> listUserBackByCondition(SeaUserDTO dto) {
		return new Result<>(ResultCode.BAD_REQUEST);
	}

	@Override
	public Result<List<UserRemark>> listUserRemark(Integer userId) {
		return new Result<>(ResultCode.BAD_REQUEST);
	}

	@Override
	public Result<List<TotalPrice>> listTotalPriceByCondition(SeaTotalPriceDTO dto) {
		return new Result<>(ResultCode.BAD_REQUEST);
	}

	@Override
	public Result<?> updateUser(User user) {
		return new Result<>(ResultCode.BAD_REQUEST);
	}

	@Override
	public Result<UserRemark> addUserRemark(UserRemark userRemark) {
		return new Result<>(ResultCode.BAD_REQUEST);
	}

	@Override
	public Result<PageInfoDTO<Manage>> listManageByCondition(SeaManageDTO dto) {
		return new Result<>(ResultCode.BAD_REQUEST);
	}

	@Override
	public Result<Manage> addManage(Manage manage) {
		return new Result<>(ResultCode.BAD_REQUEST);
	}

	@Override
	public Result<Manage> updateManage(Manage manage) {
		return new Result<>(ResultCode.BAD_REQUEST);
	}

}
