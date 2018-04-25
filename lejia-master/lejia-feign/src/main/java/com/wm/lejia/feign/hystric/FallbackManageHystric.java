package com.wm.lejia.feign.hystric;

import java.util.List;

import org.springframework.stereotype.Component;

import com.wm.lejia.common.pojo.dto.DefProvinceDTO;
import com.wm.lejia.common.pojo.entity.City;
import com.wm.lejia.common.pojo.entity.Province;
import com.wm.lejia.common.pojo.vo.CityVO;
import com.wm.lejia.common.utils.Result;
import com.wm.lejia.feign.client.ManageFeignClient;

@Component
public class FallbackManageHystric implements ManageFeignClient {

	@Override
	public Result<Province> createProvince(Province province) {
		return null;
	}

	@Override
	public Result<City> createCity(City city) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<List<DefProvinceDTO>> getDefProvinceAndCity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<List<CityVO>> listCity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<City> getCity(City city) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<Province> getProvince(Province province) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<?> updateCity(City city) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result<?> updateProvince(Province province) {
		// TODO Auto-generated method stub
		return null;
	}

}
