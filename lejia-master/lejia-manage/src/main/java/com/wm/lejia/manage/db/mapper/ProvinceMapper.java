package com.wm.lejia.manage.db.mapper;

import org.apache.ibatis.annotations.Update;

import com.wm.lejia.common.pojo.entity.Province;

public interface ProvinceMapper {
    int deleteByPrimaryKey(Integer provinceId);

    int insert(Province record);

    int insertSelective(Province record);

    Province selectByPrimaryKey(Integer provinceId);

    int updateByPrimaryKeySelective(Province record);

    int updateByPrimaryKey(Province record);
    
    Province getProvince(Province province);
    
    @Update("UPDATE province SET is_default = 0")
    int updateDefault();
}