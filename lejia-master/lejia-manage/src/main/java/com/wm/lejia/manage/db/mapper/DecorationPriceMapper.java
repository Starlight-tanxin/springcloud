package com.wm.lejia.manage.db.mapper;

import com.wm.lejia.common.pojo.entity.DecorationPrice;

public interface DecorationPriceMapper {
    int deleteByPrimaryKey(Integer decorationPriceId);

    int insert(DecorationPrice record);

    int insertSelective(DecorationPrice record);

    DecorationPrice selectByPrimaryKey(Integer decorationPriceId);

    int updateByPrimaryKeySelective(DecorationPrice record);

    int updateByPrimaryKey(DecorationPrice record);
}