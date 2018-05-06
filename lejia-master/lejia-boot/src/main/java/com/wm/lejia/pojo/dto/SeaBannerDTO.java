package com.wm.lejia.pojo.dto;

public class SeaBannerDTO extends PageSearchDTO{
	
	private String bannerTitle;
	
	private Integer cityId;
	
	private Integer isData;

	public String getBannerTitle() {
		return bannerTitle;
	}

	public void setBannerTitle(String bannerTitle) {
		this.bannerTitle = bannerTitle;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getIsData() {
		return isData;
	}

	public void setIsData(Integer isData) {
		this.isData = isData;
	}

	@Override
	public String toString() {
		return "SeaBannerDTO [bannerTitle=" + bannerTitle + ", cityId=" + cityId + ", isData=" + isData + "]";
	}
	
	
}
