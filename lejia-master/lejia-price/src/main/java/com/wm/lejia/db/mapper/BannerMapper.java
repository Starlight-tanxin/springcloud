package com.wm.lejia.db.mapper;

import java.util.List;

import com.wm.lejia.common.pojo.entity.Banner;

public interface BannerMapper {
    int deleteByPrimaryKey(Integer bannerId);

    int insert(Banner record);

    int insertSelective(Banner record);

    Banner selectByPrimaryKey(Integer bannerId);

    int updateByPrimaryKeySelective(Banner record);

    int updateByPrimaryKeyWithBLOBs(Banner record);

    int updateByPrimaryKey(Banner record);
    
    // tx 18/4/23
    List<Banner> listBannerByHome(Banner banner);
}