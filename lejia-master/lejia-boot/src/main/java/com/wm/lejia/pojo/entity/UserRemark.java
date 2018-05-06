package com.wm.lejia.pojo.entity;

import java.util.Date;

public class UserRemark {
    private Integer userRemarkId;

    private Integer userId;

    private String content;

    private Integer createdBy;

    private Date createdTime;

    private Integer isDeleted;

    public Integer getUserRemarkId() {
        return userRemarkId;
    }

    public void setUserRemarkId(Integer userRemarkId) {
        this.userRemarkId = userRemarkId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}