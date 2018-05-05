package com.sumainfo.common.entity;

import java.util.Date;

public class ThrLogin {
	private int id;
	private String classify;
    private String cusPhone;
    private String uniqueId;
    private Date createTime;
    
	public ThrLogin() {
		super();
	}
	
	public ThrLogin(String classify, String cusPhone, String uniqueId,
			Date createTime) {
		super();
		this.classify = classify;
		this.cusPhone = cusPhone;
		this.uniqueId = uniqueId;
		this.createTime = createTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}

	public String getCusPhone() {
		return cusPhone;
	}

	public void setCusPhone(String cusPhone) {
		this.cusPhone = cusPhone;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
    
	
    

}
