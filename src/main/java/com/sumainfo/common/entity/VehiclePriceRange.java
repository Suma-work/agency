package com.sumainfo.common.entity;

/**
 * 车辆价格区间
 * @author Administrator
 *
 */
public class VehiclePriceRange {
	
	private String carName;
	private Double maxPrice;
	private Double minPrice;
	
	public String getCarName() {
		return carName;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	public Double getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}
	public Double getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}
	
	

}
