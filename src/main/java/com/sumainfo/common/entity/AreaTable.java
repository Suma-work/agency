package com.sumainfo.common.entity;

/**
 * 省市区信息表实体类
 * @author Administrator
 *
 */
public class AreaTable {
	private int id;
	private String areaId;
	private String areaName;
	private String parentName;
	private String zipCode;
	private String areaCode;
	
	public AreaTable() {
		
	}
	
	public AreaTable(String areaId, String areaName, String parentName,
			String zipCode, String areaCode) {
		this.areaId = areaId;
		this.areaName = areaName;
		this.parentName = parentName;
		this.zipCode = zipCode;
		this.areaCode = areaCode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	@Override
	public String toString() {
		return "AreaTable [id=" + id + ", areaId=" + areaId + ", areaName="
				+ areaName + ", parentName=" + parentName + ", zipCode="
				+ zipCode + ", areaCode=" + areaCode + "]";
	}
	
	

}
