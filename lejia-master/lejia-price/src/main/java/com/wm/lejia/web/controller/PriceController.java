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

import com.wm.lejia.common.pojo.dto.CalculationPriceDTO;
import com.wm.lejia.common.pojo.entity.Home;
import com.wm.lejia.common.pojo.entity.HomeDetail;
import com.wm.lejia.common.pojo.entity.TotalPrice;
import com.wm.lejia.common.pojo.entity.UserPrice;
import com.wm.lejia.common.pojo.vo.TotalPriceVO;
import com.wm.lejia.service.PriceService;
import com.wm.lejia.common.utils.Result;
import com.wm.lejia.common.utils.ResultCode;


@RestController
@RequestMapping("/price")
public class PriceController {
	
	 private static Logger log = LoggerFactory.getLogger(PriceController.class);
	 
	 @Autowired
	 private PriceService priceService;
	 
	 @PostMapping("/createHomeInfo")
	 public Result<Home> createHomeInfo(@RequestBody Home home) {
		 log.info("PricecController  createHomeInfo ====>" + home.toString());
		 Home h =  priceService.createHome(home);
		 if(h == null) {
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
	 public Result<Boolean> createPriceItem(@RequestBody List<UserPrice> list){
		 Boolean b = priceService.createPriceItem(list);
		 if(!b.booleanValue()) {
			 return new Result<Boolean>(ResultCode.INSERT_ERROR,b);
		 }
		 return new Result<Boolean>(b);
	 }
	 
	 @PostMapping("/listTotalPrice")
	 public Result<List<TotalPrice>> listTotalPrice(Integer userId){
		 return new Result<List<TotalPrice>>(priceService.listTotalPrice(userId));
	 }
	 
	 @PostMapping("/listPriceItem")
	 public Result<List<UserPrice>> listPriceItem(Integer totalPriceId){
		 return new Result<List<UserPrice>>(priceService.listUserPrice(totalPriceId));
	 }
}
