package com.wm.lejia.manage.db.mapper;

import com.wm.lejia.common.pojo.entity.HomeDetail;

public interface HomeDetailMapper {
    int deleteByPrimaryKey(Integer homeDetailId);

    int insert(HomeDetail record);

    int insertSelective(HomeDetail record);

    HomeDetail selectByPrimaryKey(Integer homeDetailId);

    int updateByPrimaryKeySelective(HomeDetail record);

    int updateByPrimaryKey(HomeDetail record);
}