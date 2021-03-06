package com.wm.lejia.pojo.entity;

import java.util.Date;

import com.wm.lejia.utils.StringUtils;

public class Banner {
	private Integer bannerId;

	private String bannerTitle;

	private String bannerImg;

	private Integer idx;

	private String type;

	private Integer provinceId;

	private Integer cityId;

	private Date createdTime;

	private Integer createdBy;

	private Date updatedTime;

	private Integer updatedBy;

	private Integer isDeleted;

	private Integer isData;

	private Integer showType;

	private String applyPage;

	private String urlStr;

	private Integer isUp;

	private String bannerDetail;

	public Integer getBannerId() {
		return bannerId;
	}

	public void setBannerId(Integer bannerId) {
		this.bannerId = bannerId;
	}

	public String getBannerTitle() {
		return bannerTitle;
	}

	public void setBannerTitle(String bannerTitle) {
		this.bannerTitle = bannerTitle == null ? null : bannerTitle.trim();
	}

	public String getBannerImg() {
		return bannerImg;
	}

	public void setBannerImg(String bannerImg) {
		this.bannerImg = bannerImg == null ? null : bannerImg.trim();
	}

	public Integer getIdx() {
		return idx;
	}

	public void setIdx(Integer idx) {
		this.idx = idx;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
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

	public Integer getIsData() {
		return isData;
	}

	public void setIsData(Integer isData) {
		this.isData = isData;
	}

	public Integer getShowType() {
		return showType;
	}

	public void setShowType(Integer showType) {
		this.showType = showType;
	}

	public String getApplyPage() {
		return applyPage;
	}

	public void setApplyPage(String applyPage) {
		this.applyPage = applyPage == null ? null : applyPage.trim();
	}

	public String getUrlStr() {
		return urlStr;
	}

	public void setUrlStr(String urlStr) {
		this.urlStr = urlStr == null ? null : urlStr.trim();
	}

	public Integer getIsUp() {
		return isUp;
	}

	public void setIsUp(Integer isUp) {
		this.isUp = isUp;
	}

	public String getBannerDetail() {
		return bannerDetail;
	}

	public void setBannerDetail(String bannerDetail) {
		this.bannerDetail = bannerDetail == null ? null : bannerDetail.trim();
	}

	public boolean isEmpty() {
		if (StringUtils.isEmptyStr(applyPage) || StringUtils.isEmptyStr(bannerTitle)
				|| StringUtils.isEmptyStr(bannerImg) || updatedBy == null) {
			return true;
		}
		return false;
	}
}