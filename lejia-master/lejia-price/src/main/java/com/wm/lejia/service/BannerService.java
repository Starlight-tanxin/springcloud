package com.wm.lejia.service;

import java.util.List;

import com.wm.lejia.common.pojo.entity.Banner;

public interface BannerService {
	
	/**
	 * 网站前段显示banner
	 * @param banner
	 * @return
	 */
	List<Banner> listBannerByHome(Banner banner);

}
