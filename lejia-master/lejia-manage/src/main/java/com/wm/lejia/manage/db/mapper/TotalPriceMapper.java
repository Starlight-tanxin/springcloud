package com.wm.lejia.manage.db.mapper;

import com.wm.lejia.common.pojo.entity.TotalPrice;

public interface TotalPriceMapper {
    int deleteByPrimaryKey(Integer totalPriceId);

    int insert(TotalPrice record);

    int insertSelective(TotalPrice record);

    TotalPrice selectByPrimaryKey(Integer totalPriceId);

    int updateByPrimaryKeySelective(TotalPrice record);

    int updateByPrimaryKey(TotalPrice record);
}