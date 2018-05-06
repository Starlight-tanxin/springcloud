package com.wm.lejia.db.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.wm.lejia.pojo.dto.SeaManageDTO;
import com.wm.lejia.pojo.entity.Manage;

public interface ManageMapper {
    int deleteByPrimaryKey(Integer manageId);

    int insert(Manage record);

    int insertSelective(Manage record);

    Manage selectByPrimaryKey(Integer manageId);

    int updateByPrimaryKeySelective(Manage record);

    int updateByPrimaryKey(Manage record);
    
    List<Manage> listManageByCondition(SeaManageDTO dto);
    
    @Select("SELECT * FROM manage WHERE 1=1 AND username = #{username}")
    Manage getManageByUsername(@Param("username")String username);
}