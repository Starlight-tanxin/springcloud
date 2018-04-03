package com.wm.lejia.feign.pojo.dto;

import java.util.List;

public class CalculationPriceDTO extends HomeDTO{
	
	List<HomeDetailDTO> details;

	public List<HomeDetailDTO> getDetails() {
		return details;
	}

	public void setDetails(List<HomeDetailDTO> details) {
		this.details = details;
	}
	
	
}
