package com.wm.lejia.manage.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.wm.lejia.common.pojo.dto.SeaTotalPriceDTO;
import com.wm.lejia.common.pojo.dto.SeaUserDTO;
import com.wm.lejia.common.pojo.dto.UserVO;
import com.wm.lejia.common.pojo.entity.TotalPrice;
import com.wm.lejia.common.pojo.entity.User;
import com.wm.lejia.common.pojo.entity.UserRemark;
import com.wm.lejia.common.utils.Result;
import com.wm.lejia.common.utils.ResultCode;
import com.wm.lejia.manage.service.UserService;

@RestController
@RequestMapping("/manage/user")
public class UserController {

	private static Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@PostMapping("/listUserBackByCondition")
	public Result<PageInfo<UserVO>> listUserBackByCondition(@RequestBody SeaUserDTO dto) {
		Result<PageInfo<UserVO>> result = userService.listUserBackByCondition(dto);
		return result;
	}

	@PostMapping("/listUserRemark")
	public Result<List<UserRemark>> listUserRemark(Integer userId) {
		Result<List<UserRemark>> result = userService.listUserRemark(userId);
		return result;
	}

	@PostMapping("/listTotalPriceByCondition")
	public Result<List<TotalPrice>> listTotalPriceByCondition(@RequestBody SeaTotalPriceDTO dto) {
		log.info("UserController   listTotalPriceByCondition ===> dto :" + dto.toString());
		List<TotalPrice> list = userService.listTotalPriceByCondition(dto);
		if (ObjectUtils.isEmpty(list)) {
			return new Result<>(ResultCode.QUERY_ERROR);
		}
		return new Result<>(list);
	}

	@PostMapping("/updateUser")
	public Result<?> updateUser(@RequestBody User user) {
		Result<?> result = userService.updateUser(user);
		return result;
	}

	@PostMapping("/addUserRemark")
	public Result<UserRemark> addUserRemark(@RequestBody UserRemark userRemark) {
		UserRemark ur = userService.addUserRemark(userRemark);
		if (ObjectUtils.isEmpty(ur)) {
			return new Result<>(ResultCode.INSERT_ERROR);
		}
		return new Result<UserRemark>(ur);
	}
}
