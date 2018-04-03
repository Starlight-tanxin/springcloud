package com.wm.lejia.pojo.dto;

public class UserDTO {
	
	private String nickname;

	private String username;

	private String password;

	private String wechatOpenid;

	private String mobile;
	
	private Integer userId;

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getWechatOpenid() {
		return wechatOpenid;
	}

	public void setWechatOpenid(String wechatOpenid) {
		this.wechatOpenid = wechatOpenid;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "UserDTO [nickname=" + nickname + ", username=" + username + ", password=" + password + ", wechatOpenid="
				+ wechatOpenid + ", mobile=" + mobile + ", userId=" + userId + "]";
	}
}
