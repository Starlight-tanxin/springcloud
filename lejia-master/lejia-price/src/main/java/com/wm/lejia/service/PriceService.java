package com.wm.lejia.service;

import java.util.List;

import com.wm.lejia.common.pojo.dto.CalculationPriceDTO;
import com.wm.lejia.common.pojo.entity.Home;
import com.wm.lejia.common.pojo.entity.HomeDetail;
import com.wm.lejia.common.pojo.entity.TotalPrice;
import com.wm.lejia.common.pojo.entity.UserPrice;
import com.wm.lejia.common.pojo.vo.TotalPriceVO;

public interface PriceService {
	
	Home createHome(Home home);
	
	List<HomeDetail> createHomeDetails(List<HomeDetail> details);
	
	TotalPriceVO calculationPrice(CalculationPriceDTO dto);
	
	Home getHomeById(Integer homeId);
	
	TotalPrice createTotalPrice(TotalPrice totalPrice);
	
	Boolean createPriceItem(List<UserPrice> list);
	
	List<TotalPrice> listTotalPrice(Integer userId);
	
	List<UserPrice> listUserPrice(Integer totalPriceId);
}
