package com.wm.lejia.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.wm.lejia.common.constant.Constants;
import com.wm.lejia.common.pojo.dto.LoginDTO;
import com.wm.lejia.common.pojo.dto.UserDTO;
import com.wm.lejia.common.pojo.entity.User;
import com.wm.lejia.common.utils.Result;
import com.wm.lejia.common.utils.ResultCode;
import com.wm.lejia.common.utils.StringUtils;
import com.wm.lejia.common.utils.sign.MD5Utils;
import com.wm.lejia.db.mapper.UserMapper;
import com.wm.lejia.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public User regUser(User user) {
		if(!StringUtils.isEmptyStr(user.getPassword())) {
			user.setPassword(MD5Utils.MD5Encode(user.getPassword(), Constants.CHARSET_NAME_UTF8));
		}
		userMapper.insertSelective(user);
		log.info("UserServiceImpl  regUser ====> " + user.toString());
		return user;
	}

	@Override
	public User checkUser(UserDTO dto) {
		List<User> list = userMapper.getUserByMobileOROpenid(dto);
		if(list == null || list.size() == 0) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public User getUser(UserDTO dto) {
		try {
			User user = userMapper.getUserByCondition(dto);
			return user;
		} catch (Exception e) {
			//e.printStackTrace();
			log.error("UserService getUser 查找数据错误",e);
		}
		return null;
	}

	@Override
	public Result<User> login(LoginDTO dto) {
		String mobile = dto.getMobile();
		UserDTO userDTO = new UserDTO();
		userDTO.setMobile(mobile);
		try {
			User user = userMapper.getUserByCondition(userDTO);
			if(ObjectUtils.isEmpty(user)) {
				user = new User();
				user.setMobile(mobile);
				// 不存在就注册
				user = regUser(user);
				return new Result<User>(user);
				//return new Result<User>(ResultCode.ACCOUNT_NO_EXIT);
			}
			return new Result<User>(user);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("UserServiceImpl login ===> 查询出错",e);
		}
		return new Result<User>(ResultCode.QUERY_ERROR);
	}
	
	
}
