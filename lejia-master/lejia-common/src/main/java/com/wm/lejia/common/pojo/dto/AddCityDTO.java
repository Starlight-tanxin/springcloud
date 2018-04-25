package com.wm.lejia.common.pojo.dto;

import com.wm.lejia.common.pojo.entity.City;
import com.wm.lejia.common.pojo.entity.Province;
import com.wm.lejia.common.utils.StringUtils;

public class AddCityDTO {

	private String provinceName;

	private String cityName;

	private Integer isDefault;

	private Integer updatedBy;

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public City DTO2City() {
		City c = new City();
		c.setCityName(cityName);
		c.setIsDefault(isDefault);
		c.setCreatedBy(updatedBy);
		return c;
	}

	public Province DTO2Province() {
		Province p = new Province();
		p.setProvinceName(provinceName);
		p.setIsDefault(isDefault);
		p.setCreatedBy(updatedBy);
		return p;
	}

	public boolean isEmpty() {
		if (StringUtils.isEmptyStr(cityName) || StringUtils.isEmptyStr(provinceName) || isDefault == null
				|| updatedBy == null) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "AddCityDTO [provinceName=" + provinceName + ", cityName=" + cityName + ", isDefault=" + isDefault
				+ ", updatedBy=" + updatedBy + "]";
	}
	
	
}
