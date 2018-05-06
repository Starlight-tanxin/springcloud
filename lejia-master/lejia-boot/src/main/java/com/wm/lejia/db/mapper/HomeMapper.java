package com.wm.lejia.db.mapper;

import org.apache.ibatis.annotations.Select;

import com.wm.lejia.pojo.dto.CalculationPriceDTO;
import com.wm.lejia.pojo.entity.Home;

public interface HomeMapper {
    int deleteByPrimaryKey(Integer homeId);

    int insert(Home record);

    int insertSelective(Home record);

    Home selectByPrimaryKey(Integer homeId);

    int updateByPrimaryKeySelective(Home record);

    int updateByPrimaryKey(Home record);
    
    @Select("SELECT * FROM home WHERE home_id = #{homeId}")
    CalculationPriceDTO selectById(Integer homeId);
}