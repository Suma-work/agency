package com.sumainfo.common.entity;

/**
 * 轮播图表
 * @author Administrator
 *
 */
public class VehicleBanner {
	//主键id
	private int vehId;
	//某型号汽车名称
	private String carName;
	//店铺id
	private String shopId;
	//平台和其他店铺的区分 0平台 1其他
	private String clsassify;
	
	public VehicleBanner() {
		
	}

	public VehicleBanner(String carName, String shopId, String clsassify) {
		this.carName = carName;
		this.shopId = shopId;
		this.clsassify = clsassify;
	}

	public int getVehId() {
		return vehId;
	}

	public void setVehId(int vehId) {
		this.vehId = vehId;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getClsassify() {
		return clsassify;
	}

	public void setClsassify(String clsassify) {
		this.clsassify = clsassify;
	}
	
	

}
