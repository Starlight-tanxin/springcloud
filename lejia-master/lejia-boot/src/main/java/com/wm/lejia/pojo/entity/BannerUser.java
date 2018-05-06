package com.wm.lejia.pojo.entity;

import java.util.Date;

import com.wm.lejia.utils.StringUtils;

public class BannerUser {
	private Integer bannerUserId;

	private Integer bannerId;

	private Integer userId;

	private String nickname;

	private String mobile;

	private String wechatOpenid;

	private Integer cityId;

	private Integer provinceId;

	private Date createdTime;

	public Integer getBannerUserId() {
		return bannerUserId;
	}

	public void setBannerUserId(Integer bannerUserId) {
		this.bannerUserId = bannerUserId;
	}

	public Integer getBannerId() {
		return bannerId;
	}

	public void setBannerId(Integer bannerId) {
		this.bannerId = bannerId;
	}

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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile == null ? null : mobile.trim();
	}

	public String getWechatOpenid() {
		return wechatOpenid;
	}

	public void setWechatOpenid(String wechatOpenid) {
		this.wechatOpenid = wechatOpenid == null ? null : wechatOpenid.trim();
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

	@Override
	public String toString() {
		return "BannerUser [bannerUserId=" + bannerUserId + ", bannerId=" + bannerId + ", userId=" + userId
				+ ", nickname=" + nickname + ", mobile=" + mobile + ", wechatOpenid=" + wechatOpenid + ", cityId="
				+ cityId + ", provinceId=" + provinceId + ", createdTime=" + createdTime + "]";
	}

	public boolean isEmpty() {
		if (StringUtils.isEmptyStr(nickname) || StringUtils.isEmptyStr(mobile) || cityId == null
				|| provinceId == null) {
			return true;
		}
		return false;
	}
}