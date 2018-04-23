package com.wm.lejia.common.pojo.vo;

import java.util.List;

public class TotalPriceVO {
	private Double sumPrice;

	private Double unitSumPrice;

	private Integer provinceId;

	private Integer cityId;

	private List<PriceVO> priceItem;

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

	public List<PriceVO> getPriceItem() {
		return priceItem;
	}

	public void setPriceItem(List<PriceVO> priceItem) {
		this.priceItem = priceItem;
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

	@Override
	public String toString() {
		return "TotalPriceVO [sumPrice=" + sumPrice + ", unitSumPrice=" + unitSumPrice + ", provinceId=" + provinceId
				+ ", cityId=" + cityId + ", priceItem=" + priceItem + "]";
	}

}
