package com.sumainfo.common.entity;


/**
 * 二级车辆信息表
 * @author DYang
 *
 */
public class SeclvlTable {
	
	//主键id
	private int secId;
	//车辆id
	private int id;
	//车辆名称
	private String name;
	//父级id
	private int parentid;

	
	public SeclvlTable() {
	}

	public SeclvlTable(int id, String name,
			           int parentid) {
		this.id = id;
		this.name = name;
		this.parentid = parentid;

	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public int getParentid() {
		return parentid;
	}

	public void setParentid(int parentid) {
		this.parentid = parentid;
	}


	public int getSecId() {
		return secId;
	}

	public void setSecId(int secId) {
		this.secId = secId;
	}


	
	

}
