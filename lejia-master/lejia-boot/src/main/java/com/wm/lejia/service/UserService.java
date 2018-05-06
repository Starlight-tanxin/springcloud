package com.wm.lejia.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wm.lejia.pojo.dto.SeaTotalPriceDTO;
import com.wm.lejia.pojo.dto.SeaUserDTO;
import com.wm.lejia.pojo.dto.UserVO;
import com.wm.lejia.pojo.entity.TotalPrice;
import com.wm.lejia.pojo.entity.UserRemark;
import com.wm.lejia.pojo.dto.LoginDTO;
import com.wm.lejia.pojo.dto.UserDTO;
import com.wm.lejia.pojo.entity.User;
import com.wm.lejia.utils.Result;

public interface UserService {
	
	/**
	 * 注册
	 * @param user
	 * @return user 注册后的user对象
	 */
	User regUser(User user);
	
	/**
	 * 检测user是否存在
	 * @param dto 条件
	 * @return user对象 或 null
	 */
	User checkUser(UserDTO dto);

	
	User getUser(UserDTO dto);
	
	
	Result<User> login(LoginDTO dto);
	
	
	/** 管理系统接口 */
	Result<PageInfo<UserVO>> listUserBackByCondition(SeaUserDTO dto);
	
	Result<List<UserRemark>> listUserRemark(Integer userId);
	
	List<TotalPrice> listTotalPriceByCondition(SeaTotalPriceDTO dto);
	
	Result<?> updateUser(User user);
	
	UserRemark addUserRemark(UserRemark userRemark);
}
