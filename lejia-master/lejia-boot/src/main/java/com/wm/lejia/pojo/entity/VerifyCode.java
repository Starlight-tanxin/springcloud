package com.wm.lejia.pojo.entity;

import java.util.Date;

public class VerifyCode {
    private Integer verifyCodeId;

    private String mobile;

    private String code;

    private Date createdTime;

    public Integer getVerifyCodeId() {
        return verifyCodeId;
    }

    public void setVerifyCodeId(Integer verifyCodeId) {
        this.verifyCodeId = verifyCodeId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}