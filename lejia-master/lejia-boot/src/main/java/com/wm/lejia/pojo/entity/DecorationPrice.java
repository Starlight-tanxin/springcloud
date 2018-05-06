package com.wm.lejia.pojo.entity;

import java.util.Date;

public class DecorationPrice {
    private Integer decorationPriceId;

    private Integer decorationId;

    private Integer provinceId;

    private Integer cityId;

    private String region;

    private Double laborUnitPrice;

    private Double laborCalculatePrice;

    private Double materialUnionPrice;

    private Double materialCalculatePrice;

    private Date createdTime;

    private Integer createdBy;

    private Date updatedTime;

    private Integer updatedBy;

    private Integer isDeleted;
    
    private String decorationWord;

    private String unit;

    private String decorationName;

    public Integer getDecorationPriceId() {
        return decorationPriceId;
    }

    public void setDecorationPriceId(Integer decorationPriceId) {
        this.decorationPriceId = decorationPriceId;
    }

    public Integer getDecorationId() {
        return decorationId;
    }

    public void setDecorationId(Integer decorationId) {
        this.decorationId = decorationId;
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    public Double getLaborUnitPrice() {
        return laborUnitPrice;
    }

    public void setLaborUnitPrice(Double laborUnitPrice) {
        this.laborUnitPrice = laborUnitPrice;
    }

    public Double getLaborCalculatePrice() {
        return laborCalculatePrice;
    }

    public void setLaborCalculatePrice(Double laborCalculatePrice) {
        this.laborCalculatePrice = laborCalculatePrice;
    }

    public Double getMaterialUnionPrice() {
        return materialUnionPrice;
    }

    public void setMaterialUnionPrice(Double materialUnionPrice) {
        this.materialUnionPrice = materialUnionPrice;
    }

    public Double getMaterialCalculatePrice() {
        return materialCalculatePrice;
    }

    public void setMaterialCalculatePrice(Double materialCalculatePrice) {
        this.materialCalculatePrice = materialCalculatePrice;
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

	@Override
	public String toString() {
		return "DecorationPrice [decorationPriceId=" + decorationPriceId + ", decorationId=" + decorationId
				+ ", provinceId=" + provinceId + ", cityId=" + cityId + ", region=" + region + ", laborUnitPrice="
				+ laborUnitPrice + ", laborCalculatePrice=" + laborCalculatePrice + ", materialUnionPrice="
				+ materialUnionPrice + ", materialCalculatePrice=" + materialCalculatePrice + ", createdTime="
				+ createdTime + ", createdBy=" + createdBy + ", updatedTime=" + updatedTime + ", updatedBy=" + updatedBy
				+ ", isDeleted=" + isDeleted + ", decorationWord=" + decorationWord + ", unit=" + unit
				+ ", decorationName=" + decorationName + "]";
	}
    
    
}