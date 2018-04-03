package com.wm.lejia.pojo.entity;

import java.util.Date;

public class Decoration {
    private Integer decorationId;

    private String decorationWord;

    private String unit;

    private String decorationName;

    private Date createdTime;

    private Integer createdBy;

    private Date updatedTime;

    private Integer updatedBy;

    private Integer isDeleted;

    public Integer getDecorationId() {
        return decorationId;
    }

    public void setDecorationId(Integer decorationId) {
        this.decorationId = decorationId;
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
}