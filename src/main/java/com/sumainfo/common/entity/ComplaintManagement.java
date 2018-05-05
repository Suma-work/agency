package com.sumainfo.common.entity;

import java.util.Date;

/**
 * 投诉管理表
 * @author Administrator
 *
 */
public class ComplaintManagement {
	//主键id
	private int id;
	//客户id
	private int cusId;
	//客户名称
	private String cusName;
	//店id
	private String shopId;
	//店名
	private String shopName;
	//投诉类型
	private String complaintType;
	//事件发生日期
	private String happenedTime;
	//处理进度
	private String dealSchedule;
	//投诉原因
	private String reasons;
	//创建时间（投诉时间）
	private Date createTime;
	//投诉结束时间
	private Date overTime;
	//是否同意解决方案 1同意 2否
	private String isRecept;
	
	

	
	public ComplaintManagement() {
		
	}
	
	public ComplaintManagement(int cusId, String cusName, String shopId,
			String shopName, String complaintType,String happenedTime,String dealSchedule,
			String reasons, Date createTime) {
		this.cusId = cusId;
		this.cusName = cusName;
		this.shopId = shopId;
		this.shopName = shopName;
		this.complaintType = complaintType;
		this.happenedTime = happenedTime;
		this.dealSchedule = dealSchedule;
		this.reasons = reasons;
		this.createTime = createTime;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCusId() {
		return cusId;
	}
	public void setCusId(int cusId) {
		this.cusId = cusId;
	}
	public String getCusName() {
		return cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
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
	public String getComplaintType() {
		return complaintType;
	}
	
	public String getHappenedTime() {
		return happenedTime;
	}

	public void setHappenedTime(String happenedTime) {
		this.happenedTime = happenedTime;
	}

	public void setComplaintType(String complaintType) {
		this.complaintType = complaintType;
	}
	public String getDealSchedule() {
		return dealSchedule;
	}
	public void setDealSchedule(String dealSchedule) {
		this.dealSchedule = dealSchedule;
	}
	public String getReasons() {
		return reasons;
	}
	public void setReasons(String reasons) {
		this.reasons = reasons;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getOverTime() {
		return overTime;
	}

	public void setOverTime(Date overTime) {
		this.overTime = overTime;
	}

	public String getIsRecept() {
		return isRecept;
	}

	public void setIsRecept(String isRecept) {
		this.isRecept = isRecept;
	}
	
	
	

}
