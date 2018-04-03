package com.wm.lejia.feign.pojo.dto;

public class HomeDetailDTO {

	private Integer homeId;

	private String homeDetailType;

	private String info;

	private Integer decorationId;

	private Integer createdBy;

	public Integer getHomeId() {
		return homeId;
	}

	public void setHomeId(Integer homeId) {
		this.homeId = homeId;
	}

	public String getHomeDetailType() {
		return homeDetailType;
	}

	public void setHomeDetailType(String homeDetailType) {
		this.homeDetailType = homeDetailType;
	}

	public Integer getDecorationId() {
		return decorationId;
	}

	public void setDecorationId(Integer decorationId) {
		this.decorationId = decorationId;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public HomeDetailDTO() {
	}

	public HomeDetailDTO(Integer homeId, String homeDetailType, String info, Integer decorationId, Integer createdBy) {
		this.homeId = homeId;
		this.homeDetailType = homeDetailType;
		this.info = info;
		this.decorationId = decorationId;
		this.createdBy = createdBy;
	}

}
