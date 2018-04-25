package com.wm.lejia.common.pojo.dto;

import java.util.List;

public class TotalPriceDTO {
	
	private Double sumPrice;

	private Double unitSumPrice;
	
	private Integer userId;
	
	private Integer provinceId;
	
	private Integer cityId;
	
	private String source;

	private List<PriceDTO> priceItem;

	public Double getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(Double sumPrice) {
		this.sumPrice = sumPrice;
	}

	public Double getUnitSumPrice() {
		return unitSumPrice;
	}

	public void setUnitSumPrice(Double unitSumPrice) {
		this.unitSumPrice = unitSumPrice;
	}

	public List<PriceDTO> getPriceItem() {
		return priceItem;
	}

	public void setPriceItem(List<PriceDTO> priceItem) {
		this.priceItem = priceItem;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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
	
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Override
	public String toString() {
		return "TotalPriceDTO [sumPrice=" + sumPrice + ", unitSumPrice=" + unitSumPrice + ", userId=" + userId
				+ ", provinceId=" + provinceId + ", cityId=" + cityId + ", source=" + source + ", priceItem="
				+ priceItem + "]";
	}
	
}
