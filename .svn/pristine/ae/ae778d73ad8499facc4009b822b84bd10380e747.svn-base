package com.sumainfo.agency.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sumainfo.agency.service.QueryCountService;
import com.sumainfo.common.until.JsonResult;
import com.sumainfo.common.until.PageUtils;
import com.sumainfo.common.until.Pager;
@Service
public class QueryCountServiceImpl implements QueryCountService{

	@Autowired
	public com.sumainfo.agency.dao.QueryCount dao;
	
	@Override
	public JsonResult QueryCount(Map<String, Object> params ,Pager pager) {
		// TODO Auto-generated method stub
		JsonResult result=new JsonResult();
	    Integer inrge=dao.queryDept_id(params);
	    params.put("parent_id", inrge);
		pager.setPagerNecessary(params, pager);
		int  count=dao.querysys_deptcount(params);
		PageUtils pageUtils = new PageUtils();
		List<Map<String,Object>>query=dao.querysys_dept(params);
		result = pageUtils.getJsonResult(query, params, count);
		return result;
	}

	@Override
	public JsonResult QueryCount1(Map<String, Object> params, Pager pager) {
		// TODO Auto-generated method stub
				JsonResult result=new JsonResult();
				pager.setPagerNecessary(params, pager);
				int  count=dao.querysys_deptcount1(params);
				PageUtils pageUtils = new PageUtils();
				List<Map<String,Object>>query=dao.querysys_dept1(params);
				result = pageUtils.getJsonResult(query, params, count);
				
				return result;
	}

}
