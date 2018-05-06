package com.wm.lejia.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wm.lejia.pojo.dto.SeaBannerDTO;
import com.wm.lejia.pojo.dto.SeaBannerUserDTO;
import com.wm.lejia.pojo.vo.BannerUserVO;
import com.wm.lejia.pojo.vo.BannerVO;
import com.wm.lejia.pojo.entity.Banner;
import com.wm.lejia.pojo.entity.BannerUser;
import com.wm.lejia.pojo.vo.AppointmentVO;
import com.wm.lejia.utils.Result;

public interface BannerService {

	/**
	 * 网站前段显示banner
	 * 
	 * @param banner
	 * @return
	 */
	List<Banner> listBannerByHome(Banner banner);

	Result<?> addBannerUser(BannerUser bannerUser);

	List<AppointmentVO> listBannerUser(Integer userId);
	
	/**
	 * 后台管理
	 * @param dto
	 * @return
	 */
	Result<PageInfo<BannerVO>> listBanner(SeaBannerDTO dto);

	int updateBanner(Banner banner);

	Banner addBanner(Banner banner);

	Result<PageInfo<BannerUserVO>> listBannerUser(SeaBannerUserDTO dto);

}
