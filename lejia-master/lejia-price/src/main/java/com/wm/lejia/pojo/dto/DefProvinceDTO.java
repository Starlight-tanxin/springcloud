package com.wm.lejia.pojo.dto;

import java.util.ArrayList;
import java.util.List;

import com.wm.lejia.pojo.entity.DefCity;
import com.wm.lejia.pojo.entity.DefProvince;

public class DefProvinceDTO extends DefProvince{
	
	private List<DefCity> citys;
	
	public List<DefCity> getCitys() {
		if (citys == null) {
			citys = new ArrayList<>();
		}
		return citys;
	}

	public void setCitys(List<DefCity> citys) {
		this.citys = citys;
	}

	@Override
	public String toString() {
		return "ProvinceVO [citys=" + citys + "]";
	}
}
