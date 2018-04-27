package com.wm.lejia.manage.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.wm.lejia.common.pojo.dto.SeaManageDTO;
import com.wm.lejia.common.pojo.entity.Manage;
import com.wm.lejia.common.utils.Result;
import com.wm.lejia.manage.service.ManageService;

@RestController
@RequestMapping("/manage/manage")
public class ManageController {

	private static Logger log = LoggerFactory.getLogger(ManageController.class);

	@Autowired
	private ManageService manageService;

	@PostMapping("/listManageByCondition")
	public Result<PageInfo<Manage>> listManageByCondition(@RequestBody SeaManageDTO dto) {
		log.info("ManageController   listManageByCondition ===> dto : " + dto.toString());
		Result<PageInfo<Manage>> result = manageService.listManageByCondition(dto);
		return result;
	}

	@PostMapping("/updateManage")
	public Result<Manage> updateManage(@RequestBody Manage manage) {
		Result<Manage> result = manageService.updateManage(manage);
		return result;
	}

	@PostMapping("/addManage")
	public Result<Manage> addManage(@RequestBody Manage manage) {
		Result<Manage> result = manageService.addManage(manage);
		return result;
	}
}
