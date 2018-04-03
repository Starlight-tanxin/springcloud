package com.wm.lejia.feign.hystric;

import java.util.List;

import org.springframework.stereotype.Component;

import com.wm.lejia.feign.client.PriceFeignClient;
import com.wm.lejia.feign.pojo.dto.CalculationPriceDTO;
import com.wm.lejia.feign.pojo.dto.HomeDTO;
import com.wm.lejia.feign.pojo.dto.HomeDetailDTO;
import com.wm.lejia.feign.pojo.dto.PriceDTO;
import com.wm.lejia.feign.pojo.dto.TotalPriceDTO;
import com.wm.lejia.feign.pojo.dto.UserDTO;
import com.wm.lejia.feign.pojo.model.TotalPrice;
import com.wm.lejia.feign.pojo.model.User;
import com.wm.lejia.feign.pojo.model.UserPrice;
import com.wm.lejia.utils.Result;
import com.wm.lejia.utils.ResultCode;

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
	public Result<TotalPriceDTO> calculationPrice(CalculationPriceDTO calculationPriceDTO) {
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
	public CalculationPriceDTO getHome(Integer homeId) {
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
		// TODO Auto-generated method stub
		return null;
	}

	
}
