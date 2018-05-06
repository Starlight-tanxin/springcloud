package com.wm.lejia.pojo.dto;

import java.util.List;

import com.wm.lejia.pojo.entity.Home;
import com.wm.lejia.pojo.entity.HomeDetail;

public class CalculationPriceDTO extends Home{
	
	List<HomeDetail> details;

	public List<HomeDetail> getDetails() {
		return details;
	}

	public void setDetails(List<HomeDetail> details) {
		this.details = details;
	}
	
	
}
