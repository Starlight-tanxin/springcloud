package com.wm.lejia.db.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.wm.lejia.common.pojo.entity.VerifyCode;

public interface VerifyCodeMapper {
    int deleteByPrimaryKey(Integer verifyCodeId);

    int insert(VerifyCode record);

    int insertSelective(VerifyCode record);

    VerifyCode selectByPrimaryKey(Integer verifyCodeId);

    int updateByPrimaryKeySelective(VerifyCode record);

    int updateByPrimaryKey(VerifyCode record);
    
    @Select("SELECT * FROM verify_code WHERE mobile = #{mobile} ORDER BY created_time DESC LIMIT 1")
    VerifyCode getVerifyCode(@Param("mobile")String mobile);
    
    @Delete("DELETE FROM verify_code WHERE mobile = #{mobile}")
    int deleteVerifyCode(@Param("mobile")String mobile);
}