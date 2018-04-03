package com.wm.lejia.feign.pojo.model;

import java.util.Date;

public class User {
    private Integer userId;

    private String nickname;

    private String username;

    private String password;

    private String wechatOpenid;

    private String mobile;

    private Date createdTime;

    private Integer createdBy;

    private Date updatedTime;

    private Integer updatedBy;

    private Integer isDeleted;

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
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getWechatOpenid() {
        return wechatOpenid;
    }

    public void setWechatOpenid(String wechatOpenid) {
        this.wechatOpenid = wechatOpenid == null ? null : wechatOpenid.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
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

	@Override
	public String toString() {
		return "User [userId=" + userId + ", nickname=" + nickname + ", username=" + username + ", password=" + password
				+ ", wechatOpenid=" + wechatOpenid + ", mobile=" + mobile + ", createdTime=" + createdTime
				+ ", createdBy=" + createdBy + ", updatedTime=" + updatedTime + ", updatedBy=" + updatedBy
				+ ", isDeleted=" + isDeleted + "]";
	}
}