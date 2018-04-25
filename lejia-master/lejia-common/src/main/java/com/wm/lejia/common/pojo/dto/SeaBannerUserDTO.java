package com.wm.lejia.common.pojo.dto;

public class SeaBannerUserDTO extends PageSearchDTO{
	private String nickname;
	
	private String mobile;
	
	private String bannerTitle;
	
	private String cityName;
	
	private Integer cityId;

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

	public String getBannerTitle() {
		return bannerTitle;
	}

	public void setBannerTitle(String bannerTitle) {
		this.bannerTitle = bannerTitle;
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

	@Override
	public String toString() {
		return "SeaBannerUserDTO [nickname=" + nickname + ", mobile=" + mobile + ", bannerTitle=" + bannerTitle
				+ ", cityName=" + cityName + ", cityId=" + cityId + "]";
	}
	
	
}
