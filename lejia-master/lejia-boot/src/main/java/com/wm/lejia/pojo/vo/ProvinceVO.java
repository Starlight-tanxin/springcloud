package com.wm.lejia.pojo.vo;

import java.util.ArrayList;
import java.util.List;

import com.wm.lejia.pojo.entity.City;
import com.wm.lejia.pojo.entity.Province;

public class ProvinceVO extends Province {
	private List<City> citys;

	public List<City> getCitys() {
		if (citys == null) {
			citys = new ArrayList<>();
		}
		return citys;
	}

	public void setCitys(List<City> citys) {
		this.citys = citys;
	}

	@Override
	public String toString() {
		return "ProvinceVO [citys=" + citys + "]";
	}

}
