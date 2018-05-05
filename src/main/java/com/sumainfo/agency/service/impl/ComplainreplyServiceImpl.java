package com.sumainfo.agency.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sumainfo.agency.dao.Complainreplydao;
import com.sumainfo.agency.service.ComplainreplyService;

@Service
public class ComplainreplyServiceImpl implements ComplainreplyService {

	@Autowired
	private Complainreplydao dao;
	
	
	
	
	public Complainreplydao getDao() {
		return dao;
	}




	public void setDao(Complainreplydao dao) {
		this.dao = dao;
	}




	@Override
	public Integer saveComplainreply(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return dao.saveComplainreply(params);
	}
	




	@Override
	public List<Map<String, Object>> queryComplaintablebydealScheduleAndshopId(
			Map<String, Object> params) {
		List<Map<String, Object>> result=new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> list = dao.queryComplaintablebydealScheduleAndshopId(params);

		for (Map<String, Object> map : list) {
			Map<String, Object> xianMap=new HashMap<String,Object>();
			xianMap.put("associationId", map.get("id"));
			List<Map<String,Object>>xian=dao.queryxiangbiao(xianMap);
			Map<String,Object>resultMap=new HashMap<String,Object>();
			if(xian.size()!=0){
				resultMap.put("picAddressList", xian);
			}else {
				
				resultMap.put("picAddressList",new ArrayList<>());
			}
			resultMap.putAll(map);
			result.add(resultMap);
		}
		return result;
	}




	@Override
	public Integer queryComplaintableCount(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return dao.queryComplaintableCount(params);
	}




	@Override
	public List<Map<String, Object>> queryComplaintablebydealScheduleAndshopId1(
			Map<String, Object> params) {
		List<Map<String, Object>> result=new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> list = dao.queryComplaintablebydealScheduleAndshopId1(params);

		for (Map<String, Object> map : list) {
			Map<String, Object> xianMap=new HashMap<String,Object>();
			xianMap.put("associationId", map.get("id"));
			List<Map<String,Object>>xian=dao.queryxiangbiao(xianMap);
			Map<String,Object>resultMap=new HashMap<String,Object>();
			if(xian.size()!=0){
				resultMap.put("picAddressList", xian);
			}else {
				
				resultMap.put("picAddressList",new ArrayList<>());
			}
			resultMap.putAll(map);
			result.add(resultMap);
		}
		return result;
	}




	@Override
	public Integer queryComplaintableCount1(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return dao.queryComplaintableCount1(params);
	}




	@Override
	public List<Map<String, Object>> queryCmplainreplybyparentId(
			Map<String, Object> params) {
		// TODO Auto-generated method stub
		return dao.queryCmplainreplybyparentId(params);
	}




	@Override
	public List<Map<String, Object>> queryShopcommentByShopIdAndStatus(
			Map<String, Object> params) {
		// TODO Auto-generated method stub
		return dao.queryShopcommentByShopIdAndStatus(params);
	}




	@Override
	public Integer countShopcomment(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return dao.countShopcomment(params);
	}




	@Override
	public Integer saveShop_reply(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return dao.saveShop_reply(params);
	}




	@Override
	public List<Map<String, Object>> queryShop_replyBycomid(
			Map<String, Object> params) {
		// TODO Auto-generated method stub
		return dao.queryShop_replyBycomid(params);
	}

	 
}
