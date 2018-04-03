package com.wm.lejia.service;

import com.wm.lejia.pojo.dto.UserDTO;
import com.wm.lejia.pojo.entity.User;

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
}
