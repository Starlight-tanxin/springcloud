package com.wm.lejia.db.mapper;

import com.wm.lejia.pojo.entity.Province;

public interface ProvinceMapper {
    int deleteByPrimaryKey(Integer provinceId);

    int insert(Province record);

    int insertSelective(Province record);

    Province selectByPrimaryKey(Integer provinceId);

    int updateByPrimaryKeySelective(Province record);

    int updateByPrimaryKey(Province record);
}