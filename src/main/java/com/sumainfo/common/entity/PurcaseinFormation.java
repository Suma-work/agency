package com.sumainfo.common.entity;

import java.util.Date;


/**
 * 
 * @author Mafengxiang
 * 购买信息实体类
 *
 */

public class PurcaseinFormation {

	private int pid;//id
	
	private String pname;//访客姓名
	
	
	private  String pcontact;//联系方式
	
	private String  pidentity;//身份证
	
	private  Date  pbfdata;//拜访时间
	
	private  String  psjxh;//试驾型号
	
	private double pmoney;//金额
	
	private String  premark; //备注
	
	private String ptjrname;//推荐人姓名
	
	private String  ptjrcontact;//推荐人手机号
	
	private String  ptjridentity; //推荐人身份证号
	
	private String  prepared;//制单人
	
	private String pauditor; //审核人
	
	private String paudittime;//审核时间
	
	private String previewthenote;//审核时备注
	
	private String pstatus;//审核状态

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPcontact() {
		return pcontact;
	}

	public void setPcontact(String pcontact) {
		this.pcontact = pcontact;
	}

	public String getPidentity() {
		return pidentity;
	}

	public void setPidentity(String pidentity) {
		this.pidentity = pidentity;
	}

	public Date getPbfdata() {
		return pbfdata;
	}

	public void setPbfdata(Date pbfdata) {
		this.pbfdata = pbfdata;
	}

	public String getPsjxh() {
		return psjxh;
	}

	public void setPsjxh(String psjxh) {
		this.psjxh = psjxh;
	}

	public double getPmoney() {
		return pmoney;
	}

	public void setPmoney(double pmoney) {
		this.pmoney = pmoney;
	}

	public String getPremark() {
		return premark;
	}

	public void setPremark(String premark) {
		this.premark = premark;
	}

	public String getPtjrname() {
		return ptjrname;
	}

	public void setPtjrname(String ptjrname) {
		this.ptjrname = ptjrname;
	}

	public String getPtjrcontact() {
		return ptjrcontact;
	}

	public void setPtjrcontact(String ptjrcontact) {
		this.ptjrcontact = ptjrcontact;
	}

	public String getPtjridentity() {
		return ptjridentity;
	}

	public void setPtjridentity(String ptjridentity) {
		this.ptjridentity = ptjridentity;
	}

	public String getPrepared() {
		return prepared;
	}

	public void setPrepared(String prepared) {
		this.prepared = prepared;
	}

	public String getPauditor() {
		return pauditor;
	}

	public void setPauditor(String pauditor) {
		this.pauditor = pauditor;
	}

	public String getPaudittime() {
		return paudittime;
	}

	public void setPaudittime(String paudittime) {
		this.paudittime = paudittime;
	}

	public String getPreviewthenote() {
		return previewthenote;
	}

	public void setPreviewthenote(String previewthenote) {
		this.previewthenote = previewthenote;
	}

	public String getPstatus() {
		return pstatus;
	}

	public void setPstatus(String pstatus) {
		this.pstatus = pstatus;
	}

	public PurcaseinFormation(int pid, String pname, String pcontact,
			String pidentity, Date pbfdata, String psjxh, double pmoney,
			String premark, String ptjrname, String ptjrcontact,
			String ptjridentity, String prepared, String pauditor,
			String paudittime, String previewthenote, String pstatus) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.pcontact = pcontact;
		this.pidentity = pidentity;
		this.pbfdata = pbfdata;
		this.psjxh = psjxh;
		this.pmoney = pmoney;
		this.premark = premark;
		this.ptjrname = ptjrname;
		this.ptjrcontact = ptjrcontact;
		this.ptjridentity = ptjridentity;
		this.prepared = prepared;
		this.pauditor = pauditor;
		this.paudittime = paudittime;
		this.previewthenote = previewthenote;
		this.pstatus = pstatus;
	}

	public PurcaseinFormation() {
		super();
	}

	@Override
	public String toString() {
		return "PurcaseinFormation [pid=" + pid + ", pname=" + pname
				+ ", pcontact=" + pcontact + ", pidentity=" + pidentity
				+ ", pbfdata=" + pbfdata + ", psjxh=" + psjxh + ", pmoney="
				+ pmoney + ", premark=" + premark + ", ptjrname=" + ptjrname
				+ ", ptjrcontact=" + ptjrcontact + ", ptjridentity="
				+ ptjridentity + ", prepared=" + prepared + ", pauditor="
				+ pauditor + ", paudittime=" + paudittime + ", previewthenote="
				+ previewthenote + ", pstatus=" + pstatus + "]";
	}
	
	
	
} 
