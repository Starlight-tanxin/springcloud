package com.wm.lejia.pojo.dto;

import com.wm.lejia.pojo.entity.Home;

public class HomeDTO extends UserDTO{
	
	private Integer homeId;
	
	private Integer userId;
	// 省
	private Integer provinceId;
	// 市
	private Integer cityId;
	// 地址
	private String address;
	// 面积
	private Double areaNum;
	// 房间数
	private Integer roomNum;
	// 客厅
	private Integer livingRoomNum;
	//  餐厅
	private Integer restaurantNum;
	// 厨房
	private Integer kitchenNum;
	// 卫生间
	private Integer toiletNum;
	// 阳台
	private Integer balconyNum;
	// 拆墙
	private Integer removeWallNum;
	// 砌墙
	private Integer addWallNum;
	
	public Integer getUserId() {
		return userId;
	}

	public Integer getHomeId() {
		return homeId;
	}

	public void setHomeId(Integer homeId) {
		this.homeId = homeId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getAreaNum() {
		return areaNum;
	}

	public void setAreaNum(Double areaNum) {
		this.areaNum = areaNum;
	}

	public Integer getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(Integer roomNum) {
		this.roomNum = roomNum;
	}

	public Integer getLivingRoomNum() {
		return livingRoomNum;
	}

	public void setLivingRoomNum(Integer livingRoomNum) {
		this.livingRoomNum = livingRoomNum;
	}

	public Integer getRestaurantNum() {
		return restaurantNum;
	}

	public void setRestaurantNum(Integer restaurantNum) {
		this.restaurantNum = restaurantNum;
	}

	public Integer getKitchenNum() {
		return kitchenNum;
	}

	public void setKitchenNum(Integer kitchenNum) {
		this.kitchenNum = kitchenNum;
	}

	public Integer getToiletNum() {
		return toiletNum;
	}

	public void setToiletNum(Integer toiletNum) {
		this.toiletNum = toiletNum;
	}

	public Integer getBalconyNum() {
		return balconyNum;
	}

	public void setBalconyNum(Integer balconyNum) {
		this.balconyNum = balconyNum;
	}

	public Integer getRemoveWallNum() {
		return removeWallNum;
	}

	public void setRemoveWallNum(Integer removeWallNum) {
		this.removeWallNum = removeWallNum;
	}

	public Integer getAddWallNum() {
		return addWallNum;
	}

	public void setAddWallNum(Integer addWallNum) {
		this.addWallNum = addWallNum;
	}

	@Override
	public String toString() {
		return "HomeDTO [homeId=" + homeId + ", userId=" + userId + ", provinceId=" + provinceId + ", cityId=" + cityId
				+ ", address=" + address + ", areaNum=" + areaNum + ", roomNum=" + roomNum + ", livingRoomNum="
				+ livingRoomNum + ", restaurantNum=" + restaurantNum + ", kitchenNum=" + kitchenNum + ", toiletNum="
				+ toiletNum + ", balconyNum=" + balconyNum + ", removeWallNum=" + removeWallNum + ", addWallNum="
				+ addWallNum + "]";
	}
	
	public Home DTO2Home() {
		Home home = new Home();
		home.setHomeId(homeId);
		home.setAddress(address);
		home.setAddWallNum(addWallNum);
		home.setAreaNum(areaNum);
		home.setCityId(cityId);
		home.setBalconyNum(balconyNum);
		home.setKitchenNum(kitchenNum);
		home.setLivingRoomNum(livingRoomNum);
		home.setProvinceId(provinceId);
		home.setRemoveWallNum(removeWallNum);
		home.setRestaurantNum(restaurantNum);
		home.setRoomNum(livingRoomNum);
		home.setToiletNum(toiletNum);
		home.setUserId(userId);
		return home;
	}

}
