package com.wm.lejia.common.pojo.dto;

public class SeaPriceDTO extends PageSearchDTO {

	private String nickname;

	private String mobile;

	private String cityName;

	private Integer cityId;

	private Double priceStart;

	private Double priceEnd;

	private String createdTimeStart;

	private String createdTimeEnd;

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Double getPriceStart() {
		return priceStart;
	}

	public void setPriceStart(Double priceStart) {
		this.priceStart = priceStart;
	}

	public Double getPriceEnd() {
		return priceEnd;
	}

	public void setPriceEnd(Double priceEnd) {
		this.priceEnd = priceEnd;
	}

	public String getCreatedTimeStart() {
		return createdTimeStart;
	}

	public void setCreatedTimeStart(String createdTimeStart) {
		this.createdTimeStart = createdTimeStart;
	}

	public String getCreatedTimeEnd() {
		return createdTimeEnd;
	}

	public void setCreatedTimeEnd(String createdTimeEnd) {
		this.createdTimeEnd = createdTimeEnd;
	}

	@Override
	public String toString() {
		return "SeaPriceDTO [nickname=" + nickname + ", mobile=" + mobile + ", cityName=" + cityName + ", cityId="
				+ cityId + ", priceStart=" + priceStart + ", priceEnd=" + priceEnd + ", createdTimeStart="
				+ createdTimeStart + ", createdTimeEnd=" + createdTimeEnd + "]";
	}

}
