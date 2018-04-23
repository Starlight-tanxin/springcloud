package com.wm.lejia.db.mapper;

import java.util.List;
import java.util.Map;

import com.wm.lejia.common.pojo.entity.DecorationPrice;

public interface DecorationPriceMapper {
    int deleteByPrimaryKey(Integer decorationPriceId);

    int insert(DecorationPrice record);

    int insertSelective(DecorationPrice record);

    DecorationPrice selectByPrimaryKey(Integer decorationPriceId);

    int updateByPrimaryKeySelective(DecorationPrice record);

    int updateByPrimaryKey(DecorationPrice record);
    
    /**
     * 
     * 获取地区价格
     * @param condition 条件
     * @return
     */
    List<DecorationPrice> getDecorationPriceByCondition(Map<String, Object> condition);
    
}