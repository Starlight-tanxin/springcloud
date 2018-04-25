package com.wm.lejia.manage.db.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.wm.lejia.common.pojo.entity.DecorationPrice;

public interface DecorationPriceMapper {
    int deleteByPrimaryKey(Integer decorationPriceId);

    int insert(DecorationPrice record);

    int insertSelective(DecorationPrice record);

    DecorationPrice selectByPrimaryKey(Integer decorationPriceId);

    int updateByPrimaryKeySelective(DecorationPrice record);

    int updateByPrimaryKey(DecorationPrice record);
    
    /**
     * 软删除原本的价格配置
     * @param provinceId
     * @param cityId
     * @param updatedBy
     * @return
     */
    @Update("UPDATE decoration_price SET is_deleted = 1,updated_by = #{updatedBy},updated_time = NOW() WHERE province_id = #{provinceId} AND city_id = #{cityId}")
    int updateIsDeletedByCity(@Param("provinceId")Integer provinceId,@Param("cityId")Integer cityId,@Param("updatedBy")Integer updatedBy);
    
    /**
     * 批量插入新的价格配置
     * @param list
     * @return
     */
    Integer insertBatchPrice(List<DecorationPrice> list);
    
    
    List<DecorationPrice> getDecorationPriceByCondition(Map<String, Object> condition);
    
}