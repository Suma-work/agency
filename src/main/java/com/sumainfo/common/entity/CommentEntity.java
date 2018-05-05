package com.sumainfo.common.entity;

import java.util.Date;

/**
 * 接收数据库评论信息实体类
 * @author Administrator
 *
 */
public class CommentEntity {
	
    private int cusId;
    private String cusName;
    private int rate;
    private String comment;
    private Date createTime;
    private String picAddress;
    
    public CommentEntity(){
    	
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
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getPicAddress() {
		return picAddress;
	}

	public void setPicAddress(String picAddress) {
		this.picAddress = picAddress;
	}
    
    

}
