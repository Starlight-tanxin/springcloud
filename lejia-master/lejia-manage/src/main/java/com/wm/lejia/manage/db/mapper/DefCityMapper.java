package com.wm.lejia.manage.db.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.wm.lejia.common.pojo.entity.DefCity;

public interface DefCityMapper {
    int deleteByPrimaryKey(Integer defCityId);

    int insert(DefCity record);

    int insertSelective(DefCity record);

    DefCity selectByPrimaryKey(Integer defCityId);

    int updateByPrimaryKeySelective(DefCity record);

    int updateByPrimaryKey(DefCity record);
    
    @Select("SELECT * FROM def_city")
    List<DefCity> listAll();
    
}