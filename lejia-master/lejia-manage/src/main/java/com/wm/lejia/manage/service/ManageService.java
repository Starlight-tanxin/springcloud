package com.wm.lejia.manage.service;

import com.github.pagehelper.PageInfo;
import com.wm.lejia.common.pojo.dto.SeaManageDTO;
import com.wm.lejia.common.pojo.entity.Manage;
import com.wm.lejia.common.utils.Result;

public interface ManageService {
	
	Result<PageInfo<Manage>> listManageByCondition(SeaManageDTO dto);
	
	Result<Manage> updateManage(Manage manage);
	
	Result<Manage> addManage(Manage manage);
	
}
