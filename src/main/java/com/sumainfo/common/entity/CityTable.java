package com.sumainfo.common.entity;

/**
 * 城市基础信息表
 * @author Administrator
 *
 */
public class CityTable {
	private int id;
	private int parentId;
	private String cityName;
	private String cityCode;
	private int cityId;
	
	public CityTable() {
		
	}

	public CityTable(int parentId, String cityName, String cityCode, int cityId) {
		super();
		this.parentId = parentId;
		this.cityName = cityName;
		this.cityCode = cityCode;
		this.cityId = cityId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	@Override
	public String toString() {
		return "CityTable [id=" + id + ", parentId=" + parentId + ", cityName="
				+ cityName + ", cityCode=" + cityCode + ", cityId=" + cityId
				+ "]";
	}
	
	

}
