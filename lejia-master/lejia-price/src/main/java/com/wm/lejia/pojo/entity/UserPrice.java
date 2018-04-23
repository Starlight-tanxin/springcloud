package com.wm.lejia.pojo.entity;

import java.util.Date;

public class UserPrice {
    private Integer userPriceId;

    private String region;

    private String decorationWord;

    private String unit;

    private String decorationName;

    private String numStr;

    private Double price;

    private Double unitPrice;

    private Integer createdBy;

    private Date createdTime;

    private Integer isDeleted;

    private Integer totalPriceId;

    public Integer getUserPriceId() {
        return userPriceId;
    }

    public void setUserPriceId(Integer userPriceId) {
        this.userPriceId = userPriceId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    public String getDecorationWord() {
        return decorationWord;
    }

    public void setDecorationWord(String decorationWord) {
        this.decorationWord = decorationWord == null ? null : decorationWord.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public String getDecorationName() {
        return decorationName;
    }

    public void setDecorationName(String decorationName) {
        this.decorationName = decorationName == null ? null : decorationName.trim();
    }

    public String getNumStr() {
        return numStr;
    }

    public void setNumStr(String numStr) {
        this.numStr = numStr == null ? null : numStr.trim();
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

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getTotalPriceId() {
        return totalPriceId;
    }

    public void setTotalPriceId(Integer totalPriceId) {
        this.totalPriceId = totalPriceId;
    }

	@Override
	public String toString() {
		return "UserPrice [userPriceId=" + userPriceId + ", region=" + region + ", decorationWord=" + decorationWord
				+ ", unit=" + unit + ", decorationName=" + decorationName + ", numStr=" + numStr + ", price=" + price
				+ ", unitPrice=" + unitPrice + ", createdBy=" + createdBy + ", createdTime=" + createdTime
				+ ", isDeleted=" + isDeleted + ", totalPriceId=" + totalPriceId + "]";
	}
    
    
}