package com.wm.lejia.db.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.wm.lejia.common.pojo.dto.DefProvinceDTO;
import com.wm.lejia.common.pojo.entity.DefProvince;

public interface DefProvinceMapper {
    int deleteByPrimaryKey(Integer defProvinceId);

    int insert(DefProvince record);

    int insertSelective(DefProvince record);

    DefProvince selectByPrimaryKey(Integer defProvinceId);

    int updateByPrimaryKeySelective(DefProvince record);

    int updateByPrimaryKey(DefProvince record);
    
    @Select("SELECT * FROM def_province ")
    List<DefProvinceDTO> listAll();
}