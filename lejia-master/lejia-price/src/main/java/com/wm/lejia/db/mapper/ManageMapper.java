package com.wm.lejia.db.mapper;

import com.wm.lejia.pojo.entity.Manage;

public interface ManageMapper {
    int deleteByPrimaryKey(Integer manageId);

    int insert(Manage record);

    int insertSelective(Manage record);

    Manage selectByPrimaryKey(Integer manageId);

    int updateByPrimaryKeySelective(Manage record);

    int updateByPrimaryKey(Manage record);
}