package com.wm.lejia.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wm.lejia.pojo.dto.UserDTO;
import com.wm.lejia.pojo.entity.User;
import com.wm.lejia.service.UserService;
import com.wm.lejia.utils.Result;

@RestController
@RequestMapping("/user")
public class UserController {

	private static Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@PostMapping("/regUser")
	public Result<User> regUser(@RequestBody User user) {
		log.info("UserController regUser");
		return new Result<User>(userService.regUser(user));
	}

	@PostMapping("/checkUser")
	public Result<User> checkUser(@RequestBody UserDTO dto) {
		log.info("UserController checkUser");
		return new Result<User>(userService.checkUser(dto));
	}
	
	@PostMapping("/getUserByUserId")
	public Result<User> getUserByUserId(Integer userId){
		UserDTO dto = new UserDTO();
		dto.setUserId(userId);
		User user = userService.getUser(dto);
		return new Result<User>(user);
	}
	
	@PostMapping("/getUserByWechatOpenid")
	public Result<User> getUserByWechatOpenid(String wechatOpenid){
		UserDTO dto = new UserDTO();
		dto.setWechatOpenid(wechatOpenid);
		User user = userService.getUser(dto);
		return new Result<User>(user);
	}
}
