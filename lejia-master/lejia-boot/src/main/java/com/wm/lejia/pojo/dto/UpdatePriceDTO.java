package com.wm.lejia.pojo.dto;

import java.util.List;

import com.wm.lejia.pojo.entity.DecorationPrice;

public class UpdatePriceDTO {

	private Integer provinceId;

	private Integer cityId;

	private Integer updatedBy;

	private String describe;

	List<DecorationPrice> prices;

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

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public List<DecorationPrice> getPrices() {
		return prices;
	}

	public void setPrices(List<DecorationPrice> prices) {
		this.prices = prices;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	@Override
	public String toString() {
		return "UpdatePriceDTO [provinceId=" + provinceId + ", cityId=" + cityId + ", updatedBy=" + updatedBy
				+ ", describe=" + describe + ", prices=" + prices + "]";
	}

	public boolean isEmpty() {
		if (prices == null || prices.size() <= 0 || provinceId == null || cityId == null || updatedBy == null) {
			return true;
		}
		return false;
	}
}
