package com.wm.lejia.manage.service;

import com.github.pagehelper.PageInfo;
import com.wm.lejia.common.pojo.dto.SeaBannerDTO;
import com.wm.lejia.common.pojo.dto.SeaBannerUserDTO;
import com.wm.lejia.common.pojo.entity.Banner;
import com.wm.lejia.common.pojo.vo.BannerUserVO;
import com.wm.lejia.common.pojo.vo.BannerVO;
import com.wm.lejia.common.utils.Result;

public interface BannerService {
	
	Result<PageInfo<BannerVO>> listBanner(SeaBannerDTO dto);
	
	int updateBanner(Banner banner);
	
	Banner addBanner(Banner banner);
	
	Result<PageInfo<BannerUserVO>> listBannerUser(SeaBannerUserDTO dto);
}
