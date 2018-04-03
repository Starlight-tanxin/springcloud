package com.wm.lejia.db.mapper;

import com.wm.lejia.pojo.entity.Banner;

public interface BannerMapper {
    int deleteByPrimaryKey(Integer bannerId);

    int insert(Banner record);

    int insertSelective(Banner record);

    Banner selectByPrimaryKey(Integer bannerId);

    int updateByPrimaryKeySelective(Banner record);

    int updateByPrimaryKeyWithBLOBs(Banner record);

    int updateByPrimaryKey(Banner record);
}