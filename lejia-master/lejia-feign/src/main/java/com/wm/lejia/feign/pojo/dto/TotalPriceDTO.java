package com.wm.lejia.feign.pojo.dto;

import java.util.List;

public class TotalPriceDTO {
	
	private Double sumPrice;

	private Double unitSumPrice;
	
	private Integer userId;

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
	
}
