package com.wm.lejia.pojo.vo;

import java.util.Date;

public class CalculationPriceVO {
	
	private Integer totalPriceId;
	
	private String nickname;
	
	private String mobile;
	
	private String source;
	
	private String cityName;
	
	private Double total;
	
	private Date createdTime;

	public Integer getTotalPriceId() {
		return totalPriceId;
	}

	public void setTotalPriceId(Integer totalPriceId) {
		this.totalPriceId = totalPriceId;
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

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	@Override
	public String toString() {
		return "CalculationPriceVO [totalPriceId=" + totalPriceId + ", nickname=" + nickname + ", mobile=" + mobile
				+ ", source=" + source + ", cityName=" + cityName + ", total=" + total + ", createdTime=" + createdTime
				+ "]";
	}
	
	
}
