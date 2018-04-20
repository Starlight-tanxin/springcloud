package com.wm.lejia.manage.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wm.lejia.common.pojo.dto.SeaBannerDTO;
import com.wm.lejia.manage.db.mapper.BannerMapper;
import com.wm.lejia.manage.db.mapper.BannerUserMapper;
import com.wm.lejia.manage.service.BannerService;

@Service
public class BannerServiceImpl implements BannerService {
	
	@Autowired
	private Logger log = LoggerFactory.getLogger(BannerServiceImpl.class);
	
	@Autowired
	private BannerMapper bannerMapper;
	
	@Autowired
	private BannerUserMapper bannerUserMapper;

	@Override
	public List<Object> listBanner(SeaBannerDTO dto) {
		
		return null;
	}
}
