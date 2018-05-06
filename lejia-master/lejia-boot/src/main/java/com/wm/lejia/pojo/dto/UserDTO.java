package com.wm.lejia.pojo.dto;

import com.wm.lejia.pojo.entity.User;

public class UserDTO {
	
	private String nickname;

	private String username;

	private String password;

	private String wechatOpenid;

	private String mobile;
	
	private Integer userId;
	
	private Integer cityId;
	
	private Integer provinceId;
	
	private String source;

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
	
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public User DTO2User() {
		User user = new User();
		user.setUsername(username);
		user.setMobile(mobile);
		user.setWechatOpenid(wechatOpenid);
		user.setCityId(cityId);
		user.setProvinceId(provinceId);
		user.setSource(source);
		return user;
	}

	@Override
	public String toString() {
		return "UserDTO [nickname=" + nickname + ", username=" + username + ", password=" + password + ", wechatOpenid="
				+ wechatOpenid + ", mobile=" + mobile + ", userId=" + userId + "]";
	}
}
