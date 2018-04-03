package com.wm.lejia.db.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.wm.lejia.pojo.entity.Decoration;

public interface DecorationMapper {
    int deleteByPrimaryKey(Integer decorationId);

    int insert(Decoration record);

    int insertSelective(Decoration record);

    Decoration selectByPrimaryKey(Integer decorationId);

    int updateByPrimaryKeySelective(Decoration record);

    int updateByPrimaryKey(Decoration record);
    
    @Select("SELECT decoration_word,decoration_name,unit FROM decoration WHERE decoration_id = #{decorationId}")
    Decoration getDecorationWordAndNameAndUnint(@Param("decorationId")Integer decorationId);
    
    List<Decoration> listDecorationWordAndNameAndUnint(@Param("set") Set<Integer> set);
}