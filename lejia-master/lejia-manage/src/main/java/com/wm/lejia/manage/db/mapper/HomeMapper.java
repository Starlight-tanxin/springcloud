package com.wm.lejia.manage.db.mapper;

import com.wm.lejia.common.pojo.entity.Home;

public interface HomeMapper {
    int deleteByPrimaryKey(Integer homeId);

    int insert(Home record);

    int insertSelective(Home record);

    Home selectByPrimaryKey(Integer homeId);

    int updateByPrimaryKeySelective(Home record);

    int updateByPrimaryKey(Home record);
}