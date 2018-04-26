package com.wm.lejia.manage.db.mapper;

import java.util.List;

import com.wm.lejia.common.pojo.dto.SeaUserDTO;
import com.wm.lejia.common.pojo.dto.UserVO;
import com.wm.lejia.common.pojo.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    List<UserVO> listUserBack(SeaUserDTO dto);
}