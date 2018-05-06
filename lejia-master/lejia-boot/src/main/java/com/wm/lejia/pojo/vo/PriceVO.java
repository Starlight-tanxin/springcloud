package com.wm.lejia.pojo.vo;

public class PriceVO {
	private String region;

	private String decorationWord;

	private String unit;

	private String decorationName;

	private String numStr;

	private Double price;
	
	private Double unitPrice;
	
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

	@Override
	public String toString() {
		return "PriceVO [region=" + region + ", decorationWord=" + decorationWord + ", unit=" + unit
				+ ", decorationName=" + decorationName + ", numStr=" + numStr + ", price=" + price + ", unitPrice="
				+ unitPrice + "]";
	}

	
}
