package com.sumainfo.common.entity;

import java.util.Date;

/**
 * 访客信息主表
 * @author Administrator
 *
 */
public class VisitorMain {
	//主键id
	private int id;
	//拜访日期或预约时间段
	private String orderDate;
	//到访时间段或预约时间段
	private String orderTime;
	//app手机段的客户id
	private int cusId;
	// 0 待同意 1已预约 2已看车 3已取消 4已购买
	private String status;
	//销售人员id
    private int uid;
	//预约单号
	private String orderCode;
	//访客名称或预约人名称
	private String viewerName;
	//访客电话或预约人电话
	private String viewerPhone;
	//店铺id
	private String shopId;
	//汽车品牌名称
	private String carName;
	//汽车的具体名称 感觉没什么用
	private String carDetName;
	//车辆表信息id
	private String keyId;
	//区分 0 个人 1 机构 2 4s店
	private String classify;
	//创建时间
	private Date createTime;
	//访客身份证
	private String identityCard;
	
	
	public VisitorMain() {
		super();
	}
	
	public VisitorMain(String orderDate, String orderTime, String status,
			int uid, String orderCode,
			String viewerName, String viewerPhone, String shopId,
			String carName, String classify, Date createTime,
			String identityCard) {
		super();
		this.orderDate = orderDate;
		this.orderTime = orderTime;
		this.status = status;
		this.uid = uid;
		this.orderCode = orderCode;
		this.viewerName = viewerName;
		this.viewerPhone = viewerPhone;
		this.shopId = shopId;
		this.carName = carName;
		this.classify = classify;
		this.createTime = createTime;
		this.identityCard = identityCard;
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
	public String getIdentityCard() {
		return identityCard;
	}
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	
	
	

}
