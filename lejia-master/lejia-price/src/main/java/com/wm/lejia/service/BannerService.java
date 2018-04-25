package com.wm.lejia.service;

import java.util.List;

import com.wm.lejia.common.pojo.entity.Banner;
import com.wm.lejia.common.pojo.entity.BannerUser;
import com.wm.lejia.common.pojo.vo.AppointmentVO;
import com.wm.lejia.common.utils.Result;

public interface BannerService {
	
	/**
	 * 网站前段显示banner
	 * @param banner
	 * @return
	 */
	List<Banner> listBannerByHome(Banner banner);
	
	Result<?> addBannerUser(BannerUser bannerUser);
	
	List<AppointmentVO> listBannerUser(Integer userId);

}
