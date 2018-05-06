package com.wm.lejia.pojo.vo;

import java.util.Date;

public class BannerVO {
	
	private Integer bannerId;
	
	private String bannerTitle;
	
	private String provinceName;
	
	private String cityName;
	
	private String applyPage;
	
	private String nickname;
	
	private Date createdTime;
	
	private Integer isUp;

	public Integer getBannerId() {
		return bannerId;
	}

	public void setBannerId(Integer bannerId) {
		this.bannerId = bannerId;
	}

	public String getBannerTitle() {
		return bannerTitle;
	}

	public void setBannerTitle(String bannerTitle) {
		this.bannerTitle = bannerTitle;
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

	public String getApplyPage() {
		return applyPage;
	}

	public void setApplyPage(String applyPage) {
		this.applyPage = applyPage;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Integer getIsUp() {
		return isUp;
	}

	public void setIsUp(Integer isUp) {
		this.isUp = isUp;
	}
	
	
}
