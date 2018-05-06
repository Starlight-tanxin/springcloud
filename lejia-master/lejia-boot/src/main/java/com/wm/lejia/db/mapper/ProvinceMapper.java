package com.wm.lejia.db.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.wm.lejia.pojo.entity.Province;
import com.wm.lejia.pojo.vo.ProvinceVO;

public interface ProvinceMapper {
    int deleteByPrimaryKey(Integer provinceId);

    int insert(Province record);

    int insertSelective(Province record);

    Province selectByPrimaryKey(Integer provinceId);

    int updateByPrimaryKeySelective(Province record);

    int updateByPrimaryKey(Province record);
    
    Province getProvince(Province province);
    
    @Select("SELECT * FROM province WHERE is_deleted = 0 AND is_up = 1")
    List<ProvinceVO> listProvince();
    
    @Update("UPDATE province SET is_default = 0")
    int updateDefault();
}