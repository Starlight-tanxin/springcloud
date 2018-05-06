package com.wm.lejia.pojo.dto;

public class PriceDTO {
	private String region;

	private String decorationWord;

	private String unit;

	private String decorationName;

	private String numStr;

	private Double price;

	private Double unitPrice;

	private Integer createdBy;

	private Integer totalPriceId;

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getDecorationWord() {
		return decorationWord;
	}

	public void setDecorationWord(String decorationWord) {
		this.decorationWord = decorationWord;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getDecorationName() {
		return decorationName;
	}

	public void setDecorationName(String decorationName) {
		this.decorationName = decorationName;
	}

	public String getNumStr() {
		return numStr;
	}

	public void setNumStr(String numStr) {
		this.numStr = numStr;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getTotalPriceId() {
		return totalPriceId;
	}

	public void setTotalPriceId(Integer totalPriceId) {
		this.totalPriceId = totalPriceId;
	}

	@Override
	public String toString() {
		return "PriceDTO [region=" + region + ", decorationWord=" + decorationWord + ", unit=" + unit
				+ ", decorationName=" + decorationName + ", numStr=" + numStr + ", price=" + price + ", unitPrice="
				+ unitPrice + ", createdBy=" + createdBy + ", totalPriceId=" + totalPriceId + "]";
	}

}
