package com.wm.lejia.manage.service;

import java.util.List;

import com.wm.lejia.common.pojo.dto.SeaBannerDTO;
import com.wm.lejia.common.pojo.vo.BannerVO;

public interface BannerService {
	
	List<BannerVO> listBanner(SeaBannerDTO dto);
}
