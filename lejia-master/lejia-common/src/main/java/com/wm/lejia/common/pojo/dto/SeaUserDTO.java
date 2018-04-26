package com.wm.lejia.common.pojo.dto;

public class SeaUserDTO extends PageSearchDTO {

	private String nickname;

	private String mobile;

	private String cityName;

	private Integer cityId;

	private String source;

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

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Override
	public String toString() {
		return "SeaUserDTO [nickname=" + nickname + ", mobile=" + mobile + ", cityName=" + cityName + ", cityId="
				+ cityId + ", source=" + source + "]";
	}

}
