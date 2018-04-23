package com.wm.lejia.db.mapper;

import com.wm.lejia.common.pojo.entity.BannerUser;

public interface BannerUserMapper {
    int deleteByPrimaryKey(Integer bannerUserId);

    int insert(BannerUser record);

    int insertSelective(BannerUser record);

    BannerUser selectByPrimaryKey(Integer bannerUserId);

    int updateByPrimaryKeySelective(BannerUser record);

    int updateByPrimaryKey(BannerUser record);
}