package com.wm.lejia.manage.db.mapper;

import com.wm.lejia.common.pojo.entity.UserPrice;

public interface UserPriceMapper {
    int deleteByPrimaryKey(Integer userPriceId);

    int insert(UserPrice record);

    int insertSelective(UserPrice record);

    UserPrice selectByPrimaryKey(Integer userPriceId);

    int updateByPrimaryKeySelective(UserPrice record);

    int updateByPrimaryKey(UserPrice record);
}