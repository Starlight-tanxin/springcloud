package com.wm.lejia.common.pojo.vo;

import java.util.Date;

/**
 * 前段网页预约界面
 * @author tx
 *
 */
public class AppointmentVO {
	
	public Integer bannerUserId;
	
	public String bannerTitle;
	
	public Integer bannerId;
	
	public Date createdTime;
	
	public String status = "已预约";

	public Integer getBannerUserId() {
		return bannerUserId;
	}

	public void setBannerUserId(Integer bannerUserId) {
		this.bannerUserId = bannerUserId;
	}

	public String getBannerTitle() {
		return bannerTitle;
	}

	public void setBannerTitle(String bannerTitle) {
		this.bannerTitle = bannerTitle;
	}

	public Integer getBannerId() {
		return bannerId;
	}

	public void setBannerId(Integer bannerId) {
		this.bannerId = bannerId;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "AppointmentVO [bannerUserId=" + bannerUserId + ", bannerTitle=" + bannerTitle + ", bannerId=" + bannerId
				+ ", createdTime=" + createdTime + ", status=" + status + "]";
	}
	
	
}
