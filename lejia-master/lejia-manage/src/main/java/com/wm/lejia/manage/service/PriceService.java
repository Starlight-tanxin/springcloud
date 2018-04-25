package com.wm.lejia.manage.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wm.lejia.common.pojo.dto.SeaCityDTO;
import com.wm.lejia.common.pojo.dto.SeaPriceDTO;
import com.wm.lejia.common.pojo.dto.UpdatePriceDTO;
import com.wm.lejia.common.pojo.entity.DecorationPrice;
import com.wm.lejia.common.pojo.vo.CalculationPriceVO;
import com.wm.lejia.common.pojo.vo.PriceCityVO;
import com.wm.lejia.common.utils.Result;

public interface PriceService {

	Result<List<PriceCityVO>> listCity(SeaCityDTO dto);

	Result<?> updatePriceSetting(UpdatePriceDTO dto) throws Exception;
	
	Result<List<DecorationPrice>> listPriceByCity(Integer provinceId,Integer cityId);
	
	Result<PageInfo<CalculationPriceVO>> listCalculationPrice(SeaPriceDTO dto);
}
