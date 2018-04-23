package com.wm.lejia.db.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.wm.lejia.common.pojo.entity.UserPrice;

public interface UserPriceMapper {
    int deleteByPrimaryKey(Integer userPriceId);

    int insert(UserPrice record);

    int insertSelective(UserPrice record);

    UserPrice selectByPrimaryKey(Integer userPriceId);

    int updateByPrimaryKeySelective(UserPrice record);

    int updateByPrimaryKey(UserPrice record);
    
    Integer insertBatchPrice(List<UserPrice> list); 
    
    @Select("SELECT * FROM user_price WHERE total_price_id = #{totalPriceId}")
    List<UserPrice> listPriceByTotalPriceId(@Param("totalPriceId")Integer totalPriceId);
}