package com.wm.lejia.manage.db.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.wm.lejia.common.pojo.entity.UserRemark;

public interface UserRemarkMapper {
    int deleteByPrimaryKey(Integer userRemarkId);

    int insert(UserRemark record);

    int insertSelective(UserRemark record);

    UserRemark selectByPrimaryKey(Integer userRemarkId);

    int updateByPrimaryKeySelective(UserRemark record);

    int updateByPrimaryKey(UserRemark record);
    
    @Select("SELECT * FROM user_remark WHERE 1=1 AND is_deleted = 0 AND user_id = #{userId} ORDER BY created_time DESC")
    List<UserRemark> listUserRemarkByUser(@Param("userId")Integer userId);
}