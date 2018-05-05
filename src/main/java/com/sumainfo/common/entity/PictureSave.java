package com.sumainfo.common.entity;

import java.util.Date;

/**
 * 图片表
 * @author Administrator
 *
 */
public class PictureSave {
	//主键id
	private int id;
	//分类 2二手车、3客户投诉、4店铺
	private String classify;
	//关联表唯一id
	private String associationId;
	//图片地址
	private String picAddress;
	//创建时间
	private Date createTime;
	//0普通图片  1轮播图  2展示图片(一张)
	private String slideshow;
	//逻辑删除 0否1是
	private int delfg;
	
	
	public PictureSave(){
		
	}


	public PictureSave(String classify, String associationId,
			String picAddress, Date createTime, String slideshow, int delfg) {
		super();
		this.classify = classify;
		this.associationId = associationId;
		this.picAddress = picAddress;
		this.createTime = createTime;
		this.slideshow = slideshow;
		this.delfg = delfg;
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


	public String getAssociationId() {
		return associationId;
	}


	public void setAssociationId(String associationId) {
		this.associationId = associationId;
	}


	public String getPicAddress() {
		return picAddress;
	}


	public void setPicAddress(String picAddress) {
		this.picAddress = picAddress;
	}


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public String getSlideshow() {
		return slideshow;
	}


	public void setSlideshow(String slideshow) {
		this.slideshow = slideshow;
	}


	public int getDelfg() {
		return delfg;
	}


	public void setDelfg(int delfg) {
		this.delfg = delfg;
	}
	
	
	

}
