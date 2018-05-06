package com.wm.lejia.pojo.entity;

import com.wm.lejia.pojo.dto.UserDTO;

public class Home extends UserDTO{
    private Integer homeId;

    private Integer userId;

    private Integer provinceId;

    private Integer cityId;

    private String address;

    private Double areaNum;

    private Integer roomNum;

    private Integer livingRoomNum;

    private Integer restaurantNum;

    private Integer kitchenNum;

    private Integer toiletNum;

    private Integer balconyNum;

    private Integer removeWallNum;

    private Integer addWallNum;
    
    public Integer getHomeId() {
        return homeId;
    }

    public void setHomeId(Integer homeId) {
        this.homeId = homeId;
    }

    public Integer getUserId() {
        return userId;
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
        this.address = address == null ? null : address.trim();
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
	
}