package com.wm.lejia.db.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.wm.lejia.common.pojo.entity.Province;
import com.wm.lejia.common.pojo.vo.ProvinceVO;

public interface ProvinceMapper {
    int deleteByPrimaryKey(Integer provinceId);

    int insert(Province record);

    int insertSelective(Province record);

    Province selectByPrimaryKey(Integer provinceId);

    int updateByPrimaryKeySelective(Province record);

    int updateByPrimaryKey(Province record);
    
    @Select("SELECT * FROM province WHERE is_deleted = 0 AND is_up = 1")
    List<ProvinceVO> listProvince();
}