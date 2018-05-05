package com.sumainfo.agency.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sumainfo.agency.dao.CommercialTenantUserMsDao;
import com.sumainfo.agency.dao.SellVisitorDao;
import com.sumainfo.agency.service.SellVisitorService;
import com.sumainfo.common.entity.SysUserMs;
import com.sumainfo.common.entity.VisitMs;
import com.sumainfo.common.entity.VisitorMain;
import com.sumainfo.common.until.ComUtils;
import com.sumainfo.common.until.DateUntil;
import com.sumainfo.common.until.MessageUntil;




@Service
public class SellVisitorServiceImpl implements SellVisitorService {
	Logger logger = LoggerFactory.getLogger(SellVisitorServiceImpl.class);
	@Autowired
	private SellVisitorDao sellVisitorDao;
	@Autowired
	private CommercialTenantUserMsDao userDao;
	
	//保存客户拜访信息状态直接是 已到店“2”
	public MessageUntil<String> createVisitorMessage(HttpServletRequest request){
		MessageUntil<String> mu = new MessageUntil<String>();
		String id = request.getParameter("id");
		if(!id.equals("")){
			try {
				sellVisitorDao.updateVehicleOrders(Integer.valueOf(id));
				mu.setMessageCode("0");
			} catch (Exception e) {
				// TODO: handle exception
				mu.setMessageCode("1");
				mu.setMessageStr("保存数据异常");
				logger.info("保存数据异常"+e);
			}
		}else{
			String orderDate = request.getParameter("orderDate"); 
			String orderTime = request.getParameter("orderTime");
			String viewerName = request.getParameter("viewerName");
			String viewerPhone = request.getParameter("viewerPhone");
			String carName = request.getParameter("carName");
			String identityCard = request.getParameter("identityCard");
			String token = request.getParameter("token");
			try {
				//通过token获取userid
				int userId = userDao.findUserId(token);
				//通过userid获取相关的信息
				SysUserMs su = userDao.findUserMsForOrder(userId);
				String orderCode = ComUtils.randomUID("vo");
				if(su==null){
					mu.setMessageCode("1");
					mu.setMessageStr("权限分配异常，无法获取有效信息");
					return mu;
				}
				if(su.getClassify()==null){
					su.setClassify("0");
				}else if(su.getClassify().equals("1")){
					su.setClassify("2");
				}else{
					su.setClassify("1");
				}
				VisitorMain vm = new VisitorMain(orderDate, orderTime, "1", userId, orderCode, viewerName, viewerPhone, su.getShopid(), carName, su.getClassify(), new Date(), identityCard);
				sellVisitorDao.saveVisitorMain(vm);
				mu.setMessageCode("0");
			} catch (Exception e) {
				// TODO: handle exception
				mu.setMessageCode("1");
				mu.setMessageStr("保存数据异常");
				logger.info("保存数据异常"+e);
			}
		}
	   
		
		return mu;
	}

	public MessageUntil<Map<String, Object>> findVisitorMessage(HttpServletRequest request){
		MessageUntil<Map<String, Object>> mu = new MessageUntil<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		String token = request.getParameter("token");
		String page = request.getParameter("page");
		int minPage = (Integer.valueOf(page)-1)*10;
		int maxPage = Integer.valueOf(page)*10;
		try {
			int userId = userDao.findUserId(token);
//			SysUserMs su = userDao.findUserMsForOrder(userId);
			String type = request.getParameter("type");
			if(type.equals("2")){
				type=null;
			}
			List<VisitMs> VisitMs = sellVisitorDao.findVisitorMainList(userId,type,minPage,maxPage);
			int counts = sellVisitorDao.findVisitorNum(userId, type);
			map.put("VisitMs", VisitMs);
			map.put("counts", counts);
			mu.setData(map);
			mu.setMessageCode("0");
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("查询信息失败");
			logger.info("查询信息失败"+e);
		}

		return mu;
	}
	

}
