package com.sumainfo.common.entity;

import java.util.Date;

/**
 * 存储车辆某种车辆品牌的轮播图地址表
 * @author Administrator
 *
 */
public class VehBannerPic {
	//主键id
	private int vbId;
	//关联车辆轮播表主键id
	private int vehId;
	//图片地址
	private String picAddress;
	//创建时间
	private Date createTime;
	
	public VehBannerPic() {
		
	}

	public VehBannerPic(int vehId, String picAddress, Date createTime) {
		this.vehId = vehId;
		this.picAddress = picAddress;
		this.createTime = createTime;
	}

	public int getVbId() {
		return vbId;
	}

	public void setVbId(int vbId) {
		this.vbId = vbId;
	}

	public int getVehId() {
		return vehId;
	}

	public void setVehId(int vehId) {
		this.vehId = vehId;
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
	
	

}
