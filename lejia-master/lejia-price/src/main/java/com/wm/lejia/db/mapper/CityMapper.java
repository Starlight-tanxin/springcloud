package com.wm.lejia.db.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.wm.lejia.common.pojo.entity.City;

public interface CityMapper {
    int deleteByPrimaryKey(Integer cityId);

    int insert(City record);

    int insertSelective(City record);

    City selectByPrimaryKey(Integer cityId);

    int updateByPrimaryKeySelective(City record);

    int updateByPrimaryKey(City record);
    
    @Select("SELECT * FROM city WHERE is_deleted = 0 AND is_up = 1")
    List<City> listCity();
    
}