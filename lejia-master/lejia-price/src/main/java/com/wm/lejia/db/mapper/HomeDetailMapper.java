package com.wm.lejia.db.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.wm.lejia.common.pojo.entity.HomeDetail;

public interface HomeDetailMapper {
    int deleteByPrimaryKey(Integer homeDetailId);

    int insert(HomeDetail record);

    int insertSelective(HomeDetail record);

    HomeDetail selectByPrimaryKey(Integer homeDetailId);

    int updateByPrimaryKeySelective(HomeDetail record);

    int updateByPrimaryKey(HomeDetail record);
    
    @Select("SELECT COUNT(*) FROM home_detail WHERE home_id = #{homeId} AND home_detail_type = #{detail} AND decoration_id != 27")
    Integer countDetailNumByHome(@Param("homeId")Integer homeId,@Param("detail")String detail);
}