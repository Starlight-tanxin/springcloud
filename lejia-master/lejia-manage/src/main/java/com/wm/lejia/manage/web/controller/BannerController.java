package com.wm.lejia.manage.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.wm.lejia.common.pojo.dto.SeaBannerDTO;
import com.wm.lejia.common.pojo.dto.SeaBannerUserDTO;
import com.wm.lejia.common.pojo.entity.Banner;
import com.wm.lejia.common.pojo.vo.BannerUserVO;
import com.wm.lejia.common.pojo.vo.BannerVO;
import com.wm.lejia.common.utils.Result;
import com.wm.lejia.common.utils.ResultCode;
import com.wm.lejia.manage.service.BannerService;

@RestController
@RequestMapping("/manage/banner")
public class BannerController {
	
	private static Logger log = LoggerFactory.getLogger(BannerController.class);
	
	@Autowired
	private BannerService bannerService;
	
	@PostMapping("/listBanner")
	public Result<PageInfo<BannerVO>> listBanner(@RequestBody SeaBannerDTO dto){
		log.info("BannerController   listBanner ===> SeaBannerDTO : " + dto.toString());
		return bannerService.listBanner(dto);
	}
	
	@PostMapping("/addBanner")
	public Result<Banner> addBanner(@RequestBody Banner banner){
		Banner b = bannerService.addBanner(banner);
		if(ObjectUtils.isEmpty(b)) {
			return new Result<>(ResultCode.INSERT_ERROR);
		}
		return new Result<>(b);
	}
	
	@PostMapping("/updateBanner")
	public Result<?> updateBanner(@RequestBody Banner banner){
		int rows = bannerService.updateBanner(banner);
		if(rows <= 0) {
			return new Result<>(ResultCode.DATA_UPDATE_ERROR);
		}
		return new Result<>();
	}
	
	@PostMapping("/listBannerUser")
	public Result<PageInfo<BannerUserVO>> listBannerUser(@RequestBody SeaBannerUserDTO dto){
		return bannerService.listBannerUser(dto);
	}
	
	
}
