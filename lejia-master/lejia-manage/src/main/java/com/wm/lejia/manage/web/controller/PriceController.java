package com.wm.lejia.manage.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.wm.lejia.common.pojo.dto.SeaCityDTO;
import com.wm.lejia.common.pojo.dto.SeaPriceDTO;
import com.wm.lejia.common.pojo.dto.UpdatePriceDTO;
import com.wm.lejia.common.pojo.entity.DecorationPrice;
import com.wm.lejia.common.pojo.vo.CalculationPriceVO;
import com.wm.lejia.common.pojo.vo.PriceCityVO;
import com.wm.lejia.common.utils.Result;
import com.wm.lejia.common.utils.ResultCode;
import com.wm.lejia.manage.service.PriceService;

@RestController
@RequestMapping("/manage/price")
public class PriceController {

	private static Logger log = LoggerFactory.getLogger(PriceController.class);
	
	@Autowired
	private PriceService priceService;

	
	@PostMapping("/updatePriceSetting")
	public Result<?> updatePriceSetting(@RequestBody UpdatePriceDTO dto){
		try {
			Result<?> result = priceService.updatePriceSetting(dto);
			return result;
		} catch (Exception e) {
			//e.printStackTrace();
			log.error("PriceController   updatePriceSetting ===> 更新区域价格出错",e);
		}
		return new Result<>(ResultCode.SERVER_ERROR);
	}
	
	@PostMapping("/listCity")
	public Result<List<PriceCityVO>> listCity(@RequestBody SeaCityDTO dto){
		Result<List<PriceCityVO>> result = priceService.listCity(dto);
		return result;
	}
	
	@PostMapping("/listPriceByCity")
	public Result<List<DecorationPrice>> listPriceByCity(Integer provinceId,Integer cityId){
		Result<List<DecorationPrice>> result = priceService.listPriceByCity(provinceId, cityId);
		return result;
	}
	
	@PostMapping("/listCalculationPrice")
	public Result<PageInfo<CalculationPriceVO>> listCalculationPrice(@RequestBody SeaPriceDTO dto){
		Result<PageInfo<CalculationPriceVO>> result = priceService.listCalculationPrice(dto);
		return result;
	}
}
