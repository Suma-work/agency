package com.sumainfo.common.entity;

/**
 * 接收阿里云二级车辆标签信息实体类
 * @author Administrator
 *
 */
public class CarLableSecRecept{
	//车辆id
	private int id;
	//车辆名称
	private String name;
	private String fullname;
	//初始级
	private String initial;
	private String price;
	//父级id
	private int parentid;
	//
	private String depth;
	private Object carlist;
	

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


	public String getFullname() {
		return fullname;
	}


	public void setFullname(String fullname) {
		this.fullname = fullname;
	}


	public String getInitial() {
		return initial;
	}


	public void setInitial(String initial) {
		this.initial = initial;
	}


	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
	}


	public int getParentid() {
		return parentid;
	}


	public void setParentid(int parentid) {
		this.parentid = parentid;
	}


	public String getDepth() {
		return depth;
	}


	public void setDepth(String depth) {
		this.depth = depth;
	}

	public Object getCarlist() {
		return carlist;
	}


	public void setCarlist(Object carlist) {
		this.carlist = carlist;
	}


	@Override
	public String toString() {
		return "CarLableSecRecept [id=" + id + ", name=" + name + ", fullname="
				+ fullname + ", initial=" + initial + ", price=" + price
				+ ", parentid=" + parentid + ", depth=" + depth + ", carlist="
				+ carlist + "]";
	}


}
