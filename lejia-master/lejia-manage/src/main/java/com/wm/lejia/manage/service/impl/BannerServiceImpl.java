package com.wm.lejia.manage.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wm.lejia.common.pojo.dto.SeaBannerDTO;
import com.wm.lejia.common.pojo.dto.SeaBannerUserDTO;
import com.wm.lejia.common.pojo.entity.Banner;
import com.wm.lejia.common.pojo.vo.BannerUserVO;
import com.wm.lejia.common.pojo.vo.BannerVO;
import com.wm.lejia.common.utils.Result;
import com.wm.lejia.common.utils.ResultCode;
import com.wm.lejia.manage.db.mapper.BannerMapper;
import com.wm.lejia.manage.db.mapper.BannerUserMapper;
import com.wm.lejia.manage.service.BannerService;

@Service
public class BannerServiceImpl implements BannerService {
	
	private static Logger log = LoggerFactory.getLogger(BannerServiceImpl.class);
	
	@Autowired
	private BannerMapper bannerMapper;
	
	@Autowired
	private BannerUserMapper bannerUserMapper;

	@Override
	public Result<PageInfo<BannerVO>> listBanner(SeaBannerDTO dto) {
		try {
			PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
			List<BannerVO> list = bannerMapper.listBannerBack(dto);
			PageInfo<BannerVO> pageInfo = new PageInfo<BannerVO>(list);
			return new Result<>(pageInfo);
		} catch (Exception e) {
			//e.printStackTrace();
			log.error("BannerServiceImpl   listBanner ===> 查询出错",e);
		}
		return new Result<>(ResultCode.QUERY_ERROR);
	}

	@Override
	public int updateBanner(Banner banner) {
		int rows = 0;
		try {
			banner.setUpdatedTime(new Date());
			rows = bannerMapper.updateByPrimaryKeySelective(banner);
			return rows;
		} catch (Exception e) {
			// e.printStackTrace();
			log.error("BannerServiceImpl   updateBanner ===> 修改数据失败",e);
		}
		return rows;
	}

	@Override
	public Banner addBanner(Banner banner) {
		try {
			banner.setCreatedTime(new Date());
			int rows = bannerMapper.insertSelective(banner);
			if(rows > 0) {
				return banner;
			}
		} catch (Exception e) {
			// e.printStackTrace();
			log.error("BannerServiceImpl   updateBanner ===> 修改添加失败",e);
		}
		return null;
	}

	@Override
	public Result<PageInfo<BannerUserVO>> listBannerUser(SeaBannerUserDTO dto) {
		try {
			PageHelper.startPage(dto.getPageNum(), dto.getPageSize());
			List<BannerUserVO> list = bannerUserMapper.listBannerUser(dto);
			PageInfo<BannerUserVO> pageInfo = new PageInfo<>(list);
			return new Result<>(pageInfo);
		} catch (Exception e) {
			//e.printStackTrace();
			log.error("BannerServiceImpl   listBannerUser ===> 查询出错",e);
		}
		return null;
	}


}
