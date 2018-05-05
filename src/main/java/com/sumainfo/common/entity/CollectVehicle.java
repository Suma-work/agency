package com.sumainfo.common.entity;

public class CollectVehicle {
	private int colId;
	private int cusId;
	private String associationId;
	private int classify;
	private int colMold;
	private int delfg;
	private int count;
	
	
	public CollectVehicle() {
		super();
	}
	
	public CollectVehicle(int cusId, String associationId, int classify,int colMold) {
		super();
		this.cusId = cusId;
		this.associationId = associationId;
		this.classify = classify;
		this.colMold = colMold;
	}



	public int getColId() {
		return colId;
	}
	public void setColId(int colId) {
		this.colId = colId;
	}
	public int getCusId() {
		return cusId;
	}
	public void setCusId(int cusId) {
		this.cusId = cusId;
	}
	public String getAssociationId() {
		return associationId;
	}
	public void setAssociationId(String associationId) {
		this.associationId = associationId;
	}
	public int getClassify() {
		return classify;
	}
	public void setClassify(int classify) {
		this.classify = classify;
	}
	public int getColMold() {
		return colMold;
	}
	public void setColMold(int colMold) {
		this.colMold = colMold;
	}
	public int getDelfg() {
		return delfg;
	}
	public void setDelfg(int delfg) {
		this.delfg = delfg;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	
	

}
