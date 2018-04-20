package com.wm.lejia.common.pojo.entity;

public class DefCity {
    private Integer defCityId;

    private Integer defProvinceId;

    private String defCityName;

    public Integer getDefCityId() {
        return defCityId;
    }

    public void setDefCityId(Integer defCityId) {
        this.defCityId = defCityId;
    }

    public Integer getDefProvinceId() {
        return defProvinceId;
    }

    public void setDefProvinceId(Integer defProvinceId) {
        this.defProvinceId = defProvinceId;
    }

    public String getDefCityName() {
        return defCityName;
    }

    public void setDefCityName(String defCityName) {
        this.defCityName = defCityName == null ? null : defCityName.trim();
    }
}