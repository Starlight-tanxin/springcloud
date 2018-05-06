package com.wm.lejia.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wm.lejia.pojo.dto.SeaCityDTO;
import com.wm.lejia.pojo.dto.SeaPriceDTO;
import com.wm.lejia.pojo.dto.UpdatePriceDTO;
import com.wm.lejia.pojo.entity.DecorationPrice;
import com.wm.lejia.pojo.vo.CalculationPriceVO;
import com.wm.lejia.pojo.vo.PriceCityVO;
import com.wm.lejia.utils.Result;
import com.wm.lejia.pojo.dto.CalculationPriceDTO;
import com.wm.lejia.pojo.entity.Home;
import com.wm.lejia.pojo.entity.HomeDetail;
import com.wm.lejia.pojo.entity.TotalPrice;
import com.wm.lejia.pojo.entity.UserPrice;
import com.wm.lejia.pojo.vo.TotalPriceVO;

public interface PriceService {
	
	Home createHome(Home home);
	
	List<HomeDetail> createHomeDetails(List<HomeDetail> details);
	
	TotalPriceVO calculationPrice(CalculationPriceDTO dto);
	
	Home getHomeById(Integer homeId);
	
	TotalPrice createTotalPrice(TotalPrice totalPrice);
	
	Boolean createPriceItem(List<UserPrice> list);
	
	List<TotalPrice> listTotalPrice(Integer userId);
	
	List<UserPrice> listUserPrice(Integer totalPriceId);
	
	/** 后台管理系统的serive  */
	Result<List<PriceCityVO>> listCity(SeaCityDTO dto);

	Result<?> updatePriceSetting(UpdatePriceDTO dto) throws Exception;
	
	Result<List<DecorationPrice>> listPriceByCity(Integer provinceId,Integer cityId);
	
	Result<PageInfo<CalculationPriceVO>> listCalculationPrice(SeaPriceDTO dto);
	
	/**
	 * 18/5/3
	 * 获取计算价格的dto
	 * @param homeId 
	 * @return
	 */
	CalculationPriceDTO getCalculationPriceById(Integer homeId);
}
