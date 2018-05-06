package com.wm.lejia.pojo.dto;

public class SeaManageDTO extends PageSearchDTO {
	private String username;

	private String nickname;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		return "SeaManageDTO [username=" + username + ", nickname=" + nickname + "]";
	}

}
