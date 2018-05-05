package com.sumainfo.agency.service.impl;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sumainfo.agency.dao.CommercialTenantUserMsDao;
import com.sumainfo.agency.dao.PurchaseinFormationDao;
import com.sumainfo.agency.service.PurchaseinFormationBiz;
import com.sumainfo.common.entity.PurcaseinFormation;

@Service
public class PurchaseinFormationBizImpl implements PurchaseinFormationBiz{

	@Autowired
	private  PurchaseinFormationDao dao;

	
	@Override
	public boolean savePurchaseinFormation(Map<String,Object>params) {
		// TODO Auto-generated method stub
		
//		dao.savePurchaseinFormation(params);
		int a=dao.savePurchaseinFormation(params);
	//	dao.updateVehicleorders(params);
		boolean result=false;
		if(a>0){
			result=true;
		}else{
			result=false;
		}
		return result;
	}


	

	@Override
	public List<Map<String, Object>> queryPurchaseinFormationByparams(Map<String,Object>params) {
		return dao.queryPurchaseinFormationBypname(params);
	}


	
	@Override
	public Integer getCount(Map<String,Object>params) {
		// TODO Auto-generated method stub
		return dao.getCount(params);
	}
	
	@Override
	public List<Map<String, Object>> queryPurchaseinFormationBypstatus(Map<String,Object>params) {
		// TODO Auto-generated method stub
		return dao.queryPurchaseinFormationBypstatus(params);
				
	}


	@Override
	public boolean updatePurchaseinFormation(String pauditor,
			String previewthenote, Integer pstatus, Integer pid,Integer pyyid) {
		// TODO Auto-generated method stub
		if(pstatus==1){
			
			dao.updateVehicleorders1(pyyid);
			
		}
		
		dao.updatePurchaseinFormation(pauditor, previewthenote, pstatus, pid);
		
		
		return true;
	}


	@Override
	public boolean updatePurchaseinFormationbyall(String psjxh, String pmoney,
			String premark, Integer pid) {
		// TODO Auto-generated method stub
		
		Integer i= dao.updatePurchaseinFormationbyall(psjxh, pmoney, premark, pid);
		
		if(i>0){
			
			return true;
		}else{
			
			return false;
		}
	
	}


	@Override
	public List<Map<String, Object>> queryObject(String shopid) {
		List<Map<String, Object>> list = null;
		Integer in= dao.querySHOP(shopid);
	
		//等于1的话查询4S店的所有车
		if(in==1){
			list=dao.queryVehiclemain(shopid);
		}else if(in==2){
			//等于2 就返回空值
			list=null;
			
		}else if(in==3){
			//等于3 就返回2手店的所有车
			list=dao.queryVsedvehicledet(shopid);
			
		}
		return list;
	}




	@Override
	public Integer count(Map<String, Object> params) {
		params.put("uid", dao.queryuserid(params));
		return dao.count(params);
	}




	@Override
	public List<Map<String, Object>> querybyViewerNameorViewerPhone(
			Map<String, Object> params) {
		// TODO Auto-generated method stub
		params.put("uid", dao.queryuserid(params));
		return dao.querybyViewerNameorViewerPhone(params);
	}




	@Override
	public Integer getCount1(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return dao.getCount1(params);
	}




	@Override
	public List<Map<String, Object>> queryPurchaseinFormationBypstatus1(
			Map<String, Object> params) {
		// TODO Auto-generated method stub
		return dao.queryPurchaseinFormationBypstatus1(params);
	}



}
