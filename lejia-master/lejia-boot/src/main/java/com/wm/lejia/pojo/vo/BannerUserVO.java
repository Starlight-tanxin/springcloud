package com.wm.lejia.pojo.vo;

public class BannerUserVO {
	private Integer bannerUserId;

	private Integer bannerId;

	private Integer userId;

	private String nickname;

	private String mobile;

	private String bannerTitle;

	private String cityName;

	private String cityId;

	private String applyPage;

	private String remark;

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
		this.nickname = nickname;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getBannerTitle() {
		return bannerTitle;
	}

	public void setBannerTitle(String bannerTitle) {
		this.bannerTitle = bannerTitle;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getApplyPage() {
		return applyPage;
	}

	public void setApplyPage(String applyPage) {
		this.applyPage = applyPage;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "BannerUserVO [bannerUserId=" + bannerUserId + ", bannerId=" + bannerId + ", userId=" + userId
				+ ", nickname=" + nickname + ", mobile=" + mobile + ", bannerTitle=" + bannerTitle + ", cityName="
				+ cityName + ", cityId=" + cityId + ", applyPage=" + applyPage + ", remark=" + remark + "]";
	}

}
