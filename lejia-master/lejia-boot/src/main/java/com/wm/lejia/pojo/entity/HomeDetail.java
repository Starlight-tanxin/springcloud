package com.wm.lejia.pojo.entity;

import java.util.Date;

public class HomeDetail {
    private Integer homeDetailId;

    private Integer homeId;

    private String homeDetailType;

    private Integer decorationId;

    private Date createdTime;

    private Integer createdBy;

    private Date updatedTime;

    private Integer updatedBy;

    private Integer isDeleted;
    // 不存在数据库
    private String info;

    public Integer getHomeDetailId() {
        return homeDetailId;
    }

    public void setHomeDetailId(Integer homeDetailId) {
        this.homeDetailId = homeDetailId;
    }

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
        this.homeDetailType = homeDetailType == null ? null : homeDetailType.trim();
    }

    public Integer getDecorationId() {
        return decorationId;
    }

    public void setDecorationId(Integer decorationId) {
        this.decorationId = decorationId;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	public HomeDetail() {
	}

	public HomeDetail(Integer homeId, String homeDetailType, String info, Integer decorationId, Integer createdBy) {
		this.homeId = homeId;
		this.homeDetailType = homeDetailType;
		this.info = info;
		this.decorationId = decorationId;
		this.createdBy = createdBy;
	}
}