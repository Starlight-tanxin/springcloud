package com.wm.lejia.pojo.vo;

import java.util.Date;

/**
 * 管理端价格管理 城市列表页面 vo
 * 
 * @author Meng
 *
 */
public class PriceCityVO {
	
	private Integer cityId;
	private Integer provinceId;
	private String cityName;
	private Integer manageId;
	private String nickname;
	private Date createdTime;
	private Integer isUp;
	private String describe;

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

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Integer getManageId() {
		return manageId;
	}

	public void setManageId(Integer manageId) {
		this.manageId = manageId;
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

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	@Override
	public String toString() {
		return "PriceCityVO [cityId=" + cityId + ", provinceId=" + provinceId + ", cityName=" + cityName + ", manageId="
				+ manageId + ", nickname=" + nickname + ", createdTime=" + createdTime + ", isUp=" + isUp
				+ ", describe=" + describe + "]";
	}

}
