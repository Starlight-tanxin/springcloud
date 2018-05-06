package com.wm.lejia.service;

import com.github.pagehelper.PageInfo;
import com.wm.lejia.pojo.dto.SeaManageDTO;
import com.wm.lejia.pojo.entity.Manage;
import com.wm.lejia.utils.Result;

public interface ManageService {
	
	Result<PageInfo<Manage>> listManageByCondition(SeaManageDTO dto);
	
	Result<Manage> updateManage(Manage manage);
	
	Result<Manage> addManage(Manage manage);
	
}
