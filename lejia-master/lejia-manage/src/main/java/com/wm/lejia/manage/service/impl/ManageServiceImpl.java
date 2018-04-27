package com.wm.lejia.manage.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wm.lejia.common.pojo.dto.SeaManageDTO;
import com.wm.lejia.common.pojo.entity.Manage;
import com.wm.lejia.common.utils.Result;
import com.wm.lejia.common.utils.ResultCode;
import com.wm.lejia.manage.db.mapper.ManageMapper;
import com.wm.lejia.manage.service.ManageService;

@Service
public class ManageServiceImpl implements ManageService {
	
	private static Logger log = LoggerFactory.getLogger(ManageServiceImpl.class);
	
	@Autowired
	private ManageMapper manageMapper;

	@Override
	public Result<PageInfo<Manage>> listManageByCondition(SeaManageDTO dto) {
		PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
		try {
			List<Manage> list = manageMapper.listManageByCondition(dto);
			PageInfo<Manage> pageInfo = new PageInfo<>(list);
			return new Result<>(pageInfo);
		} catch (Exception e) {
			//e.printStackTrace();
			log.error("ManageServiceImpl   listManageByCondition ===> 查询数据出错",e);
		}
		return new Result<>(ResultCode.QUERY_ERROR);
	}

	@Override
	public Result<Manage> updateManage(Manage manage) {
		try {
			manage.setUpdatedTime(new Date());
			manageMapper.updateByPrimaryKeySelective(manage);
		} catch (Exception e) {
			//e.printStackTrace();
			log.error("ManageServiceImpl   updateManage ===> 更新数据出错",e);
		}
		return new Result<>(ResultCode.DATA_UPDATE_ERROR);
	}

	@Override
	public Result<Manage> addManage(Manage manage) {
		try {
			String username = manage.getUsername();
			Manage m = manageMapper.getManageByUsername(username);
			if(!ObjectUtils.isEmpty(m)) {
				return new Result<>(ResultCode.ACCOUNT_Y_EXIST);
			}
			manage.setCreatedTime(new Date());
			manageMapper.insertSelective(manage);
		} catch (Exception e) {
			//e.printStackTrace();
			log.error("ManageServiceImpl   listManageByCondition ===> 添加数据出错",e);
		}
		return new Result<>(ResultCode.QUERY_ERROR);
	}
}
