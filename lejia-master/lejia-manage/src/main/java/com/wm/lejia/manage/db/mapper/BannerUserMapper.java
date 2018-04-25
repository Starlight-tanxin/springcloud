package com.wm.lejia.manage.db.mapper;

import java.util.List;

import com.wm.lejia.common.pojo.dto.SeaBannerUserDTO;
import com.wm.lejia.common.pojo.entity.BannerUser;
import com.wm.lejia.common.pojo.vo.BannerUserVO;

public interface BannerUserMapper {
    int deleteByPrimaryKey(Integer bannerUserId);

    int insert(BannerUser record);

    int insertSelective(BannerUser record);

    BannerUser selectByPrimaryKey(Integer bannerUserId);

    int updateByPrimaryKeySelective(BannerUser record);

    int updateByPrimaryKey(BannerUser record);
    
    List<BannerUserVO> listBannerUser(SeaBannerUserDTO dto);
}