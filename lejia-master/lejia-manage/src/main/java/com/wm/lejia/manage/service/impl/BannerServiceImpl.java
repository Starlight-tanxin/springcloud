package com.wm.lejia.manage.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wm.lejia.common.pojo.dto.SeaBannerDTO;
import com.wm.lejia.common.pojo.vo.BannerVO;
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
	public List<BannerVO> listBanner(SeaBannerDTO dto) {
		try {
			List<BannerVO> list = bannerMapper.listBannerBack(dto);
			if(list == null || list.size() == 0) {
				return null;
			}
			return list;
		} catch (Exception e) {
			//e.printStackTrace();
			log.error("BannerServiceImpl listBanner ===> 查询出错",e);
		}
		return null;
	}
}
