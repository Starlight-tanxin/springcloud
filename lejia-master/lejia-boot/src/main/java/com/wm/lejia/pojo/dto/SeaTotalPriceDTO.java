package com.wm.lejia.pojo.dto;

public class SeaTotalPriceDTO {
	
	private Double totalStart;
	
	private Double totalEnd;
	
	private String createdTimeStrat;
	
	private String createdTimeEnd;
	
	private Integer userId;
	
	private String wechatOpenid;

	public Double getTotalStart() {
		return totalStart;
	}

	public void setTotalStart(Double totalStart) {
		this.totalStart = totalStart;
	}

	public Double getTotalEnd() {
		return totalEnd;
	}

	public void setTotalEnd(Double totalEnd) {
		this.totalEnd = totalEnd;
	}

	public String getCreatedTimeStrat() {
		return createdTimeStrat;
	}

	public void setCreatedTimeStrat(String createdTimeStrat) {
		this.createdTimeStrat = createdTimeStrat;
	}

	public String getCreatedTimeEnd() {
		return createdTimeEnd;
	}

	public void setCreatedTimeEnd(String createdTimeEnd) {
		this.createdTimeEnd = createdTimeEnd;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getWechatOpenid() {
		return wechatOpenid;
	}

	public void setWechatOpenid(String wechatOpenid) {
		this.wechatOpenid = wechatOpenid;
	}

	@Override
	public String toString() {
		return "SeaTotalPriceDTO [totalStart=" + totalStart + ", totalEnd=" + totalEnd + ", createdTimeStrat="
				+ createdTimeStrat + ", createdTimeEnd=" + createdTimeEnd + ", userId=" + userId + ", wechatOpenid="
				+ wechatOpenid + "]";
	}
	
	
}
