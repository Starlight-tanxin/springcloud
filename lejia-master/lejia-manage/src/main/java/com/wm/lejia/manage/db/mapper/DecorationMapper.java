package com.wm.lejia.manage.db.mapper;

import com.wm.lejia.common.pojo.entity.Decoration;

public interface DecorationMapper {
    int deleteByPrimaryKey(Integer decorationId);

    int insert(Decoration record);

    int insertSelective(Decoration record);

    Decoration selectByPrimaryKey(Integer decorationId);

    int updateByPrimaryKeySelective(Decoration record);

    int updateByPrimaryKey(Decoration record);
}