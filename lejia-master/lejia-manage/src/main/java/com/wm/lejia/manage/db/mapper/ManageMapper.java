package com.wm.lejia.manage.db.mapper;

import java.util.List;

import com.wm.lejia.common.pojo.dto.SeaManageDTO;
import com.wm.lejia.common.pojo.entity.Manage;

public interface ManageMapper {
    int deleteByPrimaryKey(Integer manageId);

    int insert(Manage record);

    int insertSelective(Manage record);

    Manage selectByPrimaryKey(Integer manageId);

    int updateByPrimaryKeySelective(Manage record);

    int updateByPrimaryKey(Manage record);
    
    List<Manage> listManageByCondition(SeaManageDTO dto);
}