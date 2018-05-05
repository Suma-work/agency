package com.sumainfo.common.entity;

/**
 * 汽车表附表
 * @author Administrator
 *
 */
public class VehicleDet {
	//主键id
	private int carId;
	//主表主键id（关联id）
	private int mainId;
	//汽车名称
	private String carName;
	//目前售价
	private Double sellPrice;
	//原始售价
	private Double orginPrice;
	//发动机
	private String carEngine;
	//变速箱
	private String gearbox;
	//车体结构
	private String carType;
	//是否上牌  0否 1是 默认为0
	private String isLicence;
	//新旧车区分 0新车1旧车 默认0
	private String classify;
	//店铺类型   1 4S店   2 维保店  3二手车机构店铺 4个人二手车
	private String shopClassify;
	
	public VehicleDet() {
	
	}

	public VehicleDet(String carName, Double sellPrice,
			Double orginPrice, String carEngine, String gearbox,
			String carType, String isLicence,int mainId,String classify,
			String shopClassify) {
		this.carName = carName;
		this.sellPrice = sellPrice;
		this.orginPrice = orginPrice;
		this.carEngine = carEngine;
		this.gearbox = gearbox;
		this.carType = carType;
		this.isLicence = isLicence;
		this.mainId = mainId;
		this.classify = classify;
		this.shopClassify = shopClassify;
	}

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public int getMainId() {
		return mainId;
	}

	public void setMainId(int mainId) {
		this.mainId = mainId;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public Double getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(Double sellPrice) {
		this.sellPrice = sellPrice;
	}

	public Double getOrginPrice() {
		return orginPrice;
	}

	public void setOrginPrice(Double orginPrice) {
		this.orginPrice = orginPrice;
	}

	public String getCarEngine() {
		return carEngine;
	}

	public void setCarEngine(String carEngine) {
		this.carEngine = carEngine;
	}

	public String getGearbox() {
		return gearbox;
	}

	public void setGearbox(String gearbox) {
		this.gearbox = gearbox;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public String getIsLicence() {
		return isLicence;
	}

	public void setIsLicence(String isLicence) {
		this.isLicence = isLicence;
	}

	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}

	public String getShopClassify() {
		return shopClassify;
	}

	public void setShopClassify(String shopClassify) {
		this.shopClassify = shopClassify;
	}
	
	

}
