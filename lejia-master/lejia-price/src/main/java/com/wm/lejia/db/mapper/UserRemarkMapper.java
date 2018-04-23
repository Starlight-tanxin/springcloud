package com.wm.lejia.db.mapper;

import com.wm.lejia.common.pojo.entity.UserRemark;

public interface UserRemarkMapper {
    int deleteByPrimaryKey(Integer userRemarkId);

    int insert(UserRemark record);

    int insertSelective(UserRemark record);

    UserRemark selectByPrimaryKey(Integer userRemarkId);

    int updateByPrimaryKeySelective(UserRemark record);

    int updateByPrimaryKey(UserRemark record);
}