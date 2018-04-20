package com.wm.lejia.common.pojo.vo;

import java.util.Date;

public class CityVO {
	
	private Integer cityId;
	private Integer provinceId;
	private String provinceName;
	private String cityName;
	private Integer isDefault;
	private Integer isUp;
	private Date createdTime;

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

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

	public Integer getIsUp() {
		return isUp;
	}

	public void setIsUp(Integer isUp) {
		this.isUp = isUp;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	@Override
	public String toString() {
		return "CityVO [cityId=" + cityId + ", provinceId=" + provinceId + ", provinceName=" + provinceName
				+ ", cityName=" + cityName + ", isDefault=" + isDefault + ", isUp=" + isUp + ", createdTime="
				+ createdTime + "]";
	}

}
