package com.wm.lejia.common.pojo.entity;

public class DefProvince {
    private Integer defProvinceId;

    private String defProvinceName;

    public Integer getDefProvinceId() {
        return defProvinceId;
    }

    public void setDefProvinceId(Integer defProvinceId) {
        this.defProvinceId = defProvinceId;
    }

    public String getDefProvinceName() {
        return defProvinceName;
    }

    public void setDefProvinceName(String defProvinceName) {
        this.defProvinceName = defProvinceName == null ? null : defProvinceName.trim();
    }
}