package com.wm.lejia.common.pojo.entity;

import java.util.Date;

public class TotalPrice {
    private Integer totalPriceId;

    private Date createdTime;

    private Integer createdBy;

    private Double total;

    private Double unitTotal;

    private Integer userId;

    private Integer source;

    private Integer cityId;

    private Integer provinceId;

    public Integer getTotalPriceId() {
        return totalPriceId;
    }

    public void setTotalPriceId(Integer totalPriceId) {
        this.totalPriceId = totalPriceId;
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

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getUnitTotal() {
        return unitTotal;
    }

    public void setUnitTotal(Double unitTotal) {
        this.unitTotal = unitTotal;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }
}