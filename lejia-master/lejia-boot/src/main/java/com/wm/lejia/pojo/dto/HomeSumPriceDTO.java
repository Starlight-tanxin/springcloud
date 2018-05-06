package com.wm.lejia.pojo.dto;

import java.util.List;

/**
 * pc首页上算价dto
 * @author Meng
 *
 */
public class HomeSumPriceDTO {
	
	private HomeDTO home;
	
	private List<HomeDetailDTO> details;

	public HomeDTO getHome() {
		return home;
	}

	public void setHome(HomeDTO home) {
		this.home = home;
	}

	public List<HomeDetailDTO> getDetails() {
		return details;
	}

	public void setDetails(List<HomeDetailDTO> details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "HomeSumPriceDTO [home=" + home + ", details=" + details + "]";
	}
	
	
}
