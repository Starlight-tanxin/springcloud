package com.wm.lejia.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wm.lejia.common.pojo.entity.Banner;
import com.wm.lejia.common.pojo.entity.BannerUser;
import com.wm.lejia.common.pojo.vo.AppointmentVO;
import com.wm.lejia.common.utils.Result;
import com.wm.lejia.common.utils.ResultCode;
import com.wm.lejia.service.BannerService;

@RestController
@RequestMapping("/banner")
public class BannerController {
	
	private static Logger log = LoggerFactory.getLogger(BannerController.class);
	
	@Autowired
	private BannerService bannerService;
	
	@PostMapping("/listBannerByHome")
	public Result<List<Banner>> listBannerByHome(@RequestBody Banner banner){
		List<Banner> list = bannerService.listBannerByHome(banner);
		log.info("BannerController listBannerByHome ===> bannerList :" + list);
		return new Result<List<Banner>>(list);
	}
	
	@PostMapping("/addBannerUser")
	public Result<?> addBannerUser(@RequestBody BannerUser bannerUser){
		try {
			Result<?> result = bannerService.addBannerUser(bannerUser);
			return result;
		} catch (Exception e) {
			// e.printStackTrace();
			log.error("BannerController   addBannerUser ===> 出现未知错误",e);
		}
		return new Result<>(ResultCode.SERVER_ERROR,"本接口出现未知错误");
	}
	
	@PostMapping("/listBannerUser")
	public Result<List<AppointmentVO>> listBannerUser(Integer userId){
		List<AppointmentVO> list = bannerService.listBannerUser(userId);
		if(list == null) {
			return new Result<>(ResultCode.QUERY_ERROR);
		}
		return new Result<>(list);
	} 
}
