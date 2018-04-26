package com.wm.lejia.manage.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wm.lejia.common.pojo.dto.SeaTotalPriceDTO;
import com.wm.lejia.common.pojo.dto.SeaUserDTO;
import com.wm.lejia.common.pojo.dto.UserVO;
import com.wm.lejia.common.pojo.entity.TotalPrice;
import com.wm.lejia.common.pojo.entity.User;
import com.wm.lejia.common.pojo.entity.UserRemark;
import com.wm.lejia.common.utils.Result;

public interface UserService {
	
	Result<PageInfo<UserVO>> listUserBackByCondition(SeaUserDTO dto);
	
	Result<List<UserRemark>> listUserRemark(Integer userId);
	
	List<TotalPrice> listTotalPriceByCondition(SeaTotalPriceDTO dto);
	
	Result<?> updateUser(User user);
	
	UserRemark addUserRemark(UserRemark userRemark);
}
