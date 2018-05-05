package com.sumainfo.common.entity;

public class ShopDetOrders {
	private int id;
	private String orderNo;
	private String serviceName;
	private Double servicPrice;
	private int delfg;
	
	public ShopDetOrders() {
		super();
	}
	
	public ShopDetOrders(String orderNo, String serviceName, Double servicPrice) {
		super();
		this.orderNo = orderNo;
		this.serviceName = serviceName;
		this.servicPrice = servicPrice;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public Double getServicPrice() {
		return servicPrice;
	}
	public void setServicPrice(Double servicPrice) {
		this.servicPrice = servicPrice;
	}
	public int getDelfg() {
		return delfg;
	}
	public void setDelfg(int delfg) {
		this.delfg = delfg;
	}

	
}
