package com.wm.lejia.db.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.wm.lejia.pojo.dto.SeaCityDTO;
import com.wm.lejia.pojo.vo.CityVO;
import com.wm.lejia.pojo.vo.PriceCityVO;
import com.wm.lejia.pojo.entity.City;

public interface CityMapper {
    int deleteByPrimaryKey(Integer cityId);

    int insert(City record);

    int insertSelective(City record);

    City selectByPrimaryKey(Integer cityId);

    int updateByPrimaryKeySelective(City record);

    int updateByPrimaryKey(City record);
    
    @Select("SELECT * FROM city WHERE is_deleted = 0 AND is_up = 1")
    List<City> listCity();
    
    
    City getCity(City city);

	@Select("SELECT c.city_id AS cityId,p.province_id AS provinceId,p.province_name AS provinceName,c.city_name AS cityName,"
			+ " c.is_default AS isDefault,c.is_up AS isUp,c.created_time AS createdTime"
			+ " FROM city c LEFT OUTER JOIN province p ON c.province_id = p.province_id WHERE 1=1 AND c.is_deleted = 0")
	List<CityVO> listCityAndProvince();
	
	@Update("UPDATE city SET is_default = 0")
	int updateDefault();
	
	List<PriceCityVO> listPriceCity(SeaCityDTO dto);
}