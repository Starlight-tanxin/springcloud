package com.wm.lejia.pojo.dto;

public class SeaCityDTO {
	private Integer cityId;

	private String cityName;

	private Integer isUp;

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Integer getIsUp() {
		return isUp;
	}

	public void setIsUp(Integer isUp) {
		this.isUp = isUp;
	}

	@Override
	public String toString() {
		return "SeaCityDTO [cityId=" + cityId + ", cityName=" + cityName + ", isUp=" + isUp + "]";
	}

}
