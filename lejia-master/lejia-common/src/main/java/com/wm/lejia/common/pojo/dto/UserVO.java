package com.wm.lejia.common.pojo.dto;

import java.util.Date;

public class UserVO {
	private Integer userId;

	private String nickname;

	private String mobile;

	private String cityName;

	private Integer cityId;

	private Integer provinceId;

	private Date createdTime;

	private String source;

	private Double total;

	private String remarkContent;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

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

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getRemarkContent() {
		return remarkContent;
	}

	public void setRemarkContent(String remarkContent) {
		this.remarkContent = remarkContent;
	}

	@Override
	public String toString() {
		return "UserVO [userId=" + userId + ", nickname=" + nickname + ", mobile=" + mobile + ", cityName=" + cityName
				+ ", cityId=" + cityId + ", provinceId=" + provinceId + ", createdTime=" + createdTime + ", source="
				+ source + ", total=" + total + ", remarkContent=" + remarkContent + "]";
	}

}
