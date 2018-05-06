package com.wm.lejia.web.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.wm.lejia.pojo.dto.CalculationPriceDTO;
import com.wm.lejia.pojo.dto.SeaCityDTO;
import com.wm.lejia.pojo.dto.SeaPriceDTO;
import com.wm.lejia.pojo.dto.UpdatePriceDTO;
import com.wm.lejia.pojo.entity.DecorationPrice;
import com.wm.lejia.pojo.entity.Home;
import com.wm.lejia.pojo.entity.HomeDetail;
import com.wm.lejia.pojo.entity.TotalPrice;
import com.wm.lejia.pojo.entity.UserPrice;
import com.wm.lejia.pojo.vo.CalculationPriceVO;
import com.wm.lejia.pojo.vo.PriceCityVO;
import com.wm.lejia.pojo.vo.TotalPriceVO;
import com.wm.lejia.utils.Result;
import com.wm.lejia.utils.ResultCode;
import com.wm.lejia.service.PriceService;

@RestController
@RequestMapping("/manage/price")
public class PriceController {

	private static Logger log = LoggerFactory.getLogger(PriceController.class);

	@Autowired
	private PriceService priceService;

	@PostMapping("/updatePriceSetting")
	public Result<?> updatePriceSetting(@RequestBody UpdatePriceDTO dto) {
		try {
			Result<?> result = priceService.updatePriceSetting(dto);
			return result;
		} catch (Exception e) {
			// e.printStackTrace();
			log.error("PriceController   updatePriceSetting ===> 更新区域价格出错", e);
		}
		return new Result<>(ResultCode.SERVER_ERROR);
	}

	@PostMapping("/listCity")
	public Result<List<PriceCityVO>> listCity(@RequestBody SeaCityDTO dto) {
		Result<List<PriceCityVO>> result = priceService.listCity(dto);
		return result;
	}

	@PostMapping("/listPriceByCity")
	public Result<List<DecorationPrice>> listPriceByCity(Integer provinceId, Integer cityId) {
		Result<List<DecorationPrice>> result = priceService.listPriceByCity(provinceId, cityId);
		return result;
	}

	@PostMapping("/listCalculationPrice")
	public Result<PageInfo<CalculationPriceVO>> listCalculationPrice(@RequestBody SeaPriceDTO dto) {
		Result<PageInfo<CalculationPriceVO>> result = priceService.listCalculationPrice(dto);
		return result;
	}

	// ----------------

	@PostMapping("/createHomeInfo")
	public Result<Home> createHomeInfo(@RequestBody Home home) {
		log.info("PricecController  createHomeInfo ====>" + home.toString());
		Home h = priceService.createHome(home);
		if (h == null) {
			return new Result<Home>(ResultCode.INSERT_ERROR);
		}
		return new Result<Home>(h);
	}

	@PostMapping("/createHomeDetail")
	public Result<List<HomeDetail>> createHomeDetail(@RequestBody List<HomeDetail> details) {
		return new Result<List<HomeDetail>>(priceService.createHomeDetails(details));
	}

	@PostMapping("/calculationPrice")
	public Result<TotalPriceVO> calculationPrice(@RequestBody CalculationPriceDTO dto) {
		return new Result<TotalPriceVO>(priceService.calculationPrice(dto));
	}

	@PostMapping("/getHome")
	public Home getHome(Integer homeId) {
		return priceService.getHomeById(homeId);
	}

	@PostMapping("/createTotalPrice")
	public Result<TotalPrice> createTotalPrice(@RequestBody TotalPrice totalPrice) {
		totalPrice.setCreatedTime(new Date());
		TotalPrice tp = priceService.createTotalPrice(totalPrice);
		if (tp == null) {
			return new Result<TotalPrice>(ResultCode.INSERT_ERROR);
		}
		return new Result<TotalPrice>(tp);
	}

	@PostMapping("/createPriceItem")
	public Result<Boolean> createPriceItem(@RequestBody List<UserPrice> list) {
		Boolean b = priceService.createPriceItem(list);
		if (!b.booleanValue()) {
			return new Result<Boolean>(ResultCode.INSERT_ERROR, b);
		}
		return new Result<Boolean>(b);
	}

	@PostMapping("/listTotalPrice")
	public Result<List<TotalPrice>> listTotalPrice(Integer userId) {
		return new Result<List<TotalPrice>>(priceService.listTotalPrice(userId));
	}

	@PostMapping("/listPriceItem")
	public Result<List<UserPrice>> listPriceItem(Integer totalPriceId) {
		return new Result<List<UserPrice>>(priceService.listUserPrice(totalPriceId));
	}
}
