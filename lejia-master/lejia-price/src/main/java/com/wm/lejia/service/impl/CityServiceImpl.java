package com.wm.lejia.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wm.lejia.db.mapper.CityMapper;
import com.wm.lejia.db.mapper.DefCityMapper;
import com.wm.lejia.db.mapper.DefProvinceMapper;
import com.wm.lejia.db.mapper.ProvinceMapper;
import com.wm.lejia.common.pojo.dto.DefProvinceDTO;
import com.wm.lejia.common.pojo.entity.City;
import com.wm.lejia.common.pojo.entity.DefCity;
import com.wm.lejia.common.pojo.entity.Province;
import com.wm.lejia.common.pojo.vo.ProvinceVO;
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

}
