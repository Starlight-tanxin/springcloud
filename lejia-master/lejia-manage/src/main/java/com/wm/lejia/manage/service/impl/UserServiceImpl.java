package com.wm.lejia.manage.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wm.lejia.common.pojo.dto.SeaTotalPriceDTO;
import com.wm.lejia.common.pojo.dto.SeaUserDTO;
import com.wm.lejia.common.pojo.dto.UserVO;
import com.wm.lejia.common.pojo.entity.TotalPrice;
import com.wm.lejia.common.pojo.entity.User;
import com.wm.lejia.common.pojo.entity.UserRemark;
import com.wm.lejia.common.utils.Result;
import com.wm.lejia.common.utils.ResultCode;
import com.wm.lejia.manage.db.mapper.TotalPriceMapper;
import com.wm.lejia.manage.db.mapper.UserMapper;
import com.wm.lejia.manage.db.mapper.UserRemarkMapper;
import com.wm.lejia.manage.service.UserService;

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
