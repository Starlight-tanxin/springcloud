package com.wm.lejia.db.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.wm.lejia.pojo.entity.City;

public interface CityMapper {
    int deleteByPrimaryKey(Integer cityId);

    int insert(City record);

    int insertSelective(City record);

    City selectByPrimaryKey(Integer cityId);

    int updateByPrimaryKeySelective(City record);

    int updateByPrimaryKey(City record);
    
    @Select("SELECT * FROM city WHERE is_deleted = 0")
    List<City> listCity();
}