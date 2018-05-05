package com.sumainfo.common.entity;

import java.util.Date;

/**
 * 车辆出售信息表
 * @author Administrator
 *
 */
public class VehicleOrders {
	//主键
	private int id;
	private String orderDate;
	private String orderTime;
	private int cusId;
	private String status;
	private int uid;
	private String orderCode;
	private String viewerName;
	private String viewerPhone;
	private String shopId;
	private String carName;
	private String carDetName;
	private String keyId;
	private String classify;
	private Date createTime;

	public VehicleOrders(){
		
	}


	public VehicleOrders(String orderDate, String orderTime, int cusId, String status,int uid,
			String orderCode, String viewerName,
			String viewerPhone, String shopId, String carName,
			String carDetName, String keyId, String classify, Date createTime) {
		super();
		this.orderDate = orderDate;
		this.orderTime = orderTime;
		this.cusId = cusId;
		this.status = status;
		this.uid = uid;
		this.orderCode = orderCode;
		this.viewerName = viewerName;
		this.viewerPhone = viewerPhone;
		this.shopId = shopId;
		this.carName = carName;
		this.carDetName = carDetName;
		this.keyId = keyId;
		this.classify = classify;
		this.createTime = createTime;
	}



	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getOrderDate() {
		return orderDate;
	}


	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}


	public String getOrderTime() {
		return orderTime;
	}


	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}


	public int getCusId() {
		return cusId;
	}


	public void setCusId(int cusId) {
		this.cusId = cusId;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public int getUid() {
		return uid;
	}


	public void setUid(int uid) {
		this.uid = uid;
	}


	public String getOrderCode() {
		return orderCode;
	}


	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}


	public String getViewerName() {
		return viewerName;
	}


	public void setViewerName(String viewerName) {
		this.viewerName = viewerName;
	}


	public String getViewerPhone() {
		return viewerPhone;
	}


	public void setViewerPhone(String viewerPhone) {
		this.viewerPhone = viewerPhone;
	}


	public String getShopId() {
		return shopId;
	}


	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getCarName() {
		return carName;
	}



	public void setCarName(String carName) {
		this.carName = carName;
	}



	public String getCarDetName() {
		return carDetName;
	}



	public void setCarDetName(String carDetName) {
		this.carDetName = carDetName;
	}

	public String getKeyId() {
		return keyId;
	}


	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}


	public String getClassify() {
		return classify;
	}


	public void setClassify(String classify) {
		this.classify = classify;
	}


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	
	
}
