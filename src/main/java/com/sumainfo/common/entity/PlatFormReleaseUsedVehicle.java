package com.sumainfo.common.entity;

/**
 * 首页搜索框中二手车返回的数据实体类
 * @author Administrator
 *
 */
public class PlatFormReleaseUsedVehicle {
	private String shopId;
	private String picAddress;
	private String carName;
	private Double mileage;
	private String licenceTime;
	private Double sellPrice;
	private String classify;
	
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getPicAddress() {
		return picAddress;
	}
	public void setPicAddress(String picAddress) {
		this.picAddress = picAddress;
	}
	public String getCarName() {
		return carName;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	public Double getMileage() {
		return mileage;
	}
	public void setMileage(Double mileage) {
		this.mileage = mileage;
	}
	public String getLicenceTime() {
		return licenceTime;
	}
	public void setLicenceTime(String licenceTime) {
		this.licenceTime = licenceTime;
	}
	public Double getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(Double sellPrice) {
		this.sellPrice = sellPrice;
	}
	public String getClassify() {
		return classify;
	}
	public void setClassify(String classify) {
		this.classify = classify;
	}
	
	

}
