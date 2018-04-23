package com.wm.lejia.common.pojo.vo;

import java.util.List;

public class TotalPriceVO {
	private Double sumPrice;
	
	private Double unitSumPrice;
	
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
	
	
}
