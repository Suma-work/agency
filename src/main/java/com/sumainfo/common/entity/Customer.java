package com.sumainfo.common.entity;

import java.util.Date;

/**
 * 客户基础信息表
 * @author DYang
 *
 */
public class Customer {
	//客户表主键
	private int cusId;
	//昵称
	private String nickName;
	//真实名称
	private String realName;
	//身份证号
	private String idNumber;
	//登录密码
	private String passWord;
	//手机号码
	private String cellPhone;
	//修改时间
	private Date modifyTime;
	//创建时间
	private Date createTime;
	
	private int count; 

	public Customer(){
		
	}
	
	public Customer(String nickName, String realName,
			String idNumber, String passWord, String cellPhone,
			Date modifyTime, Date createTime) {
		this.nickName = nickName;
		this.realName = realName;
		this.idNumber = idNumber;
		this.passWord = passWord;
		this.cellPhone = cellPhone;
		this.modifyTime = modifyTime;
		this.createTime = createTime;
	}
	
	public Customer(String nickName,
			String passWord, String cellPhone,
			Date modifyTime, Date createTime) {
		this.nickName = nickName;
		this.passWord = passWord;
		this.cellPhone = cellPhone;
		this.modifyTime = modifyTime;
		this.createTime = createTime;
	}
	
	public int getCusId() {
		return cusId;
	}
	public void setCusId(int cusId) {
		this.cusId = cusId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getCellPhone() {
		return cellPhone;
	}
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	@Override
	public String toString() {
		return "Customer [cusId=" + cusId + ", nickName=" + nickName
				+ ", realName=" + realName + ", idNumber=" + idNumber
				+ ", passWord=" + passWord + ", cellPhone=" + cellPhone
				+ ", modifyTime=" + modifyTime + ", createTime=" + createTime
				+ "]";
	}
	

}
