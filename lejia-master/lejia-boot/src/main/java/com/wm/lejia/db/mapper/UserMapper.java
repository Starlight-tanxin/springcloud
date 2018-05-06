package com.wm.lejia.db.mapper;

import java.util.List;

import com.wm.lejia.pojo.dto.SeaUserDTO;
import com.wm.lejia.pojo.dto.UserVO;
import com.wm.lejia.pojo.dto.UserDTO;
import com.wm.lejia.pojo.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    /**
     * 根据手机号或者openid 获取用户信息
     * @param user
     * @return
     */
    List<User> getUserByMobileOROpenid(UserDTO dto);
    
    User getUserByCondition(UserDTO dto);
    
    List<UserVO> listUserBack(SeaUserDTO dto);
}