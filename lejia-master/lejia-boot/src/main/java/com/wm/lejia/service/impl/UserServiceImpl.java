package com.wm.lejia.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wm.lejia.constant.Constants;
import com.wm.lejia.db.mapper.TotalPriceMapper;
import com.wm.lejia.db.mapper.UserMapper;
import com.wm.lejia.db.mapper.UserRemarkMapper;
import com.wm.lejia.pojo.dto.LoginDTO;
import com.wm.lejia.pojo.dto.SeaTotalPriceDTO;
import com.wm.lejia.pojo.dto.SeaUserDTO;
import com.wm.lejia.pojo.dto.UserDTO;
import com.wm.lejia.pojo.dto.UserVO;
import com.wm.lejia.pojo.entity.TotalPrice;
import com.wm.lejia.pojo.entity.User;
import com.wm.lejia.pojo.entity.UserRemark;
import com.wm.lejia.service.UserService;
import com.wm.lejia.utils.Result;
import com.wm.lejia.utils.ResultCode;
import com.wm.lejia.utils.StringUtils;
import com.wm.lejia.utils.sign.MD5Utils;

@Service
public class UserServiceImpl implements UserService {

	private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserRemarkMapper userRemarkMapper;
	
	@Autowired
	private TotalPriceMapper totalPriceMapper;
	
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
	
	
	@Override
	public Result<PageInfo<UserVO>> listUserBackByCondition(SeaUserDTO dto) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		try {
			List<UserVO> list = userMapper.listUserBack(dto);
			PageInfo<UserVO> pageInfo = new PageInfo<>(list);
			return new Result<PageInfo<UserVO>>(pageInfo);
		} catch (Exception e) {
			//e.printStackTrace();
			log.error("UserServiceImpl   listUserBackByCondition ===> 查询数据出错",e);
		}
		return new Result<>(ResultCode.QUERY_ERROR);
	}

	@Override
	public Result<List<UserRemark>> listUserRemark(Integer userId) {
		try {
			List<UserRemark> list = userRemarkMapper.listUserRemarkByUser(userId);
			return new Result<List<UserRemark>>(list);
		} catch (Exception e) {
			//e.printStackTrace();
			log.error("UserServiceImpl   listUserRemark ===> 查询数据出错",e);
		}
		return new Result<>(ResultCode.QUERY_ERROR);
	}

	@Override
	public List<TotalPrice> listTotalPriceByCondition(SeaTotalPriceDTO dto) {
		try {
			List<TotalPrice> list = totalPriceMapper.listTotalPriceByCondition(dto);
			return list;
		} catch (Exception e) {
			// e.printStackTrace();
			log.error("UserServiceImpl   listTotalPriceByCondition ===> 查询数据出错",e);
		}
		return null;
	}

	@Override
	public Result<?> updateUser(User user) {
		user.setUpdatedTime(new Date());
		try {
			userMapper.updateByPrimaryKeySelective(user);
			return new Result<>();
		} catch (Exception e) {
			//e.printStackTrace();
			log.error("UserServiceImpl   updateUser ===> 查询数据出错",e);
		}
		return new Result<>(ResultCode.DATA_UPDATE_ERROR);
	}

	@Override
	public UserRemark addUserRemark(UserRemark userRemark) {
		userRemark.setCreatedTime(new Date());
		try {
			userRemarkMapper.insertSelective(userRemark);
			return userRemark;
		} catch (Exception e) {
			//e.printStackTrace();
			log.error("UserServiceImpl   addUserRemark ===> 添加数据失败",e);
		}
		return null;
	}

	
}
