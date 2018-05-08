package com.wm.lejia.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wm.lejia.pojo.vo.CityVO;
import com.wm.lejia.db.mapper.CityMapper;
import com.wm.lejia.db.mapper.DefCityMapper;
import com.wm.lejia.db.mapper.DefProvinceMapper;
import com.wm.lejia.db.mapper.ProvinceMapper;
import com.wm.lejia.pojo.dto.DefProvinceDTO;
import com.wm.lejia.pojo.entity.City;
import com.wm.lejia.pojo.entity.DefCity;
import com.wm.lejia.pojo.entity.Province;
import com.wm.lejia.pojo.vo.ProvinceVO;
import com.wm.lejia.service.CityService;

@Service
public class CityServiceImpl implements CityService {

	private static Logger log = LoggerFactory.getLogger(CityServiceImpl.class);

	@Autowired
	private CityMapper cityMapper;

	@Autowired
	private ProvinceMapper provinceMapper;

	@Autowired
	private DefCityMapper defCityMapper;

	@Autowired
	private DefProvinceMapper defProvinceMapper;

	@Override
	public List<ProvinceVO> getCity() {
		List<ProvinceVO> voList = provinceMapper.listProvince();
		List<City> cityList = cityMapper.listCity();
		for (ProvinceVO vo : voList) {
			Integer vPid = vo.getProvinceId();
			for (City city : cityList) {
				Integer cPid = city.getProvinceId();
				if (vPid.equals(cPid)) {
					vo.getCitys().add(city);
				}
			}
		}
		return voList;
	}

	@Override
	public Province createProvince(Province province) {
		try {
			if(province.getIsDefault() != null && province.getIsDefault() == 1) {
				provinceMapper.updateDefault();
			}
			province.setCreatedTime(new Date());
			int rows = provinceMapper.insertSelective(province);
			if (rows > 0) {
				return province;
			}
		} catch (Exception e) {
			// e.printStackTrace();
			log.error("CityServiceImpl   createProvince ===> 添加数据失败", e);
		}
		return null;
	}

	@Override
	public City createCity(City city) {
		try {
			if(city.getIsDefault() != null && city.getIsDefault() == 1) {
				cityMapper.updateDefault();
			}
			city.setCreatedTime(new Date());
			int rows = cityMapper.insertSelective(city);
			if (rows > 0) {
				return city;
			}
		} catch (Exception e) {
			// e.printStackTrace();
			log.error("CityServiceImpl   createCity ===> 添加数据失败", e);
		}
		return null;
	}

	@Override
	public List<DefProvinceDTO> getDefProvinceAndCity() {
		List<DefCity> cityList = defCityMapper.listAll();
		List<DefProvinceDTO> provinceList = defProvinceMapper.listAll();
		for (DefProvinceDTO p : provinceList) {
			Integer vPid = p.getDefProvinceId();
			for (DefCity c : cityList) {
				Integer cPid = c.getDefProvinceId();
				if(vPid.equals(cPid)) {
					p.getCitys().add(c);
				}
			}
		}
		return provinceList;
	}

	@Override
	public List<City> listCityByHome() {
		try {
			List<City> list = cityMapper.listCity();
			return list;
		} catch (Exception e) {
			//e.printStackTrace();
			log.error("CityServiceImpl   listCityByHome ===> 查询数据失败",e);
		}
		return null;
	}

	
	@Override
	public Province getProvince(Province province) {
		Province p = provinceMapper.getProvince(province);
		return p;
	}

	@Override
	public City getCity(City city) {
		City c = cityMapper.getCity(city);
		return c;
	}

	@Override
	public int updateCity(City city) {
		int rows = 0;
		 try {
			if (city.getIsDefault() != null && city.getIsDefault() == 1) {
				cityMapper.updateDefault();
			}
			city.setUpdatedTime(new Date());
			rows = cityMapper.updateByPrimaryKeySelective(city);
		} catch (Exception e) {
		//	e.printStackTrace();
			log.error("CityServiceImpl updateCity ===> 修改城市信息失败",e);
		}
		return rows;
	}

	@Override
	public int updateProvince(Province province) {
		int rows = 0;
		try {
			if(province.getIsDefault() != null && province.getIsDefault() == 1) {
				provinceMapper.updateDefault();
			}
			province.setUpdatedTime(new Date());
			rows = provinceMapper.updateByPrimaryKeySelective(province);
		} catch (Exception e) {
			//e.printStackTrace();
			log.error("CityServiceImpl updateProvince ===> 修改省份信息失败",e);
		}
		return rows;
	}

	@Override
	public List<CityVO> listCityAndProvince() {
		List<CityVO> list = null;
		try {
			list = cityMapper.listCityAndProvince();
		} catch (Exception e) {
			//e.printStackTrace();
			log.error("CityServiceImpl listCityAndProvince ===> 获取城市列表失败",e);
		}
		return list;
	}
}
