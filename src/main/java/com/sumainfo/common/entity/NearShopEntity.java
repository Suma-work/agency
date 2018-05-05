package com.sumainfo.common.entity;

/**
 * 作用于接附近店铺部分信息的实体类
 * @author Administrator
 *
 */
public class NearShopEntity {
	
	
	private String shopId;
	private String shopName;
	private String shopPicAdress;
	private double lon;
	private double lat;
	private double distance;

	
	
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopPicAdress() {
		return shopPicAdress;
	}
	public void setShopPicAdress(String shopPicAdress) {
		this.shopPicAdress = shopPicAdress;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}

	
	

}
