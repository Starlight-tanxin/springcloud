package com.wm.lejia.common.pojo.dto;

import java.util.List;

import com.wm.lejia.common.pojo.dto.HomeDetailDTO;

public class ServicePriceCalculationPriceDTO extends HomeDTO{
	
	List<HomeDetailDTO> details;

	public List<HomeDetailDTO> getDetails() {
		return details;
	}

	public void setDetails(List<HomeDetailDTO> details) {
		this.details = details;
	}
	
	
}
