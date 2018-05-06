package com.wm.lejia.manage.db.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.wm.lejia.common.pojo.dto.SeaBannerUserDTO;
import com.wm.lejia.common.pojo.entity.BannerUser;
import com.wm.lejia.common.pojo.vo.BannerUserVO;
import com.wm.lejia.common.pojo.vo.AppointmentVO;

public interface BannerUserMapper {
	int deleteByPrimaryKey(Integer bannerUserId);

	int insert(BannerUser record);

	int insertSelective(BannerUser record);

	BannerUser selectByPrimaryKey(Integer bannerUserId);

	int updateByPrimaryKeySelective(BannerUser record);

	int updateByPrimaryKey(BannerUser record);

	List<BannerUserVO> listBannerUser(SeaBannerUserDTO dto);

    @Select("SELECT bu.banner_user_id AS bannerUserId,bu.banner_id AS bannerId,b.banner_title AS bannerTitle,bu.created_time AS createdTime FROM banner_user bu"
			+ " LEFT OUTER JOIN banner b ON b.banner_id = bu.banner_id"
			+ " WHERE 1=1 AND bu.user_id = #{userId} ORDER BY bu.created_time DESC")
	List<AppointmentVO> listBannerUserByUser(@Param("userId") Integer userId);
}