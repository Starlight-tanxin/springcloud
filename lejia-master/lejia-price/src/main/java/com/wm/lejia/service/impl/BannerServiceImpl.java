package com.wm.lejia.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wm.lejia.common.pojo.entity.Banner;
import com.wm.lejia.db.mapper.BannerMapper;
import com.wm.lejia.service.BannerService;

@Service
public class BannerServiceImpl implements BannerService {
	
	@Autowired
	private static Logger log = LoggerFactory.getLogger(BannerServiceImpl.class);
	
	@Autowired
	private BannerMapper bannerMapper;

	@Override
	public List<Banner> listBannerByHome(Banner banner) {
		try {
			List<Banner> list = bannerMapper.listBannerByHome(banner);
			return list;
		} catch (Exception e) {
			// e.printStackTrace();
			log.error("BannerServiceImpl listBannerByHome ===> 查询出错",e);
		}
		return null;
	}
	
	
}
