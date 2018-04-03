package com.wm.lejia.db.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.wm.lejia.pojo.entity.TotalPrice;

public interface TotalPriceMapper {
	int deleteByPrimaryKey(Integer totalPriceId);

	int insert(TotalPrice record);

	int insertSelective(TotalPrice record);

	TotalPrice selectByPrimaryKey(Integer totalPriceId);

	int updateByPrimaryKeySelective(TotalPrice record);

	int updateByPrimaryKey(TotalPrice record);

	@Select("SELECT `total_price_id`, `created_time`, `created_by`, `total`, `user_id` FROM total_price"
			+ " WHERE `user_id` = #{userId} ORDER BY `created_time` DESC")
	List<TotalPrice> listTotalPriceByUserId(@Param("userId") Integer userId);
}