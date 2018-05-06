package com.wm.lejia.pojo.dto;

public class LoginDTO {
	private String mobile;
	
	private String msmCode;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMsmCode() {
		return msmCode;
	}

	public void setMsmCode(String msmCode) {
		this.msmCode = msmCode;
	}

	@Override
	public String toString() {
		return "LoginDTO [mobile=" + mobile + ", msmCode=" + msmCode + "]";
	}
	
	
}
