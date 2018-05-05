package com.sumainfo.agency.service.impl;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sumainfo.agency.dao.MaintenanceShopDao;
import com.sumainfo.agency.dao.TokenDao;
import com.sumainfo.agency.service.MaintenanceShopService;
import com.sumainfo.common.entity.CommentEntity;
import com.sumainfo.common.entity.ShopComment;
import com.sumainfo.common.until.MessageUntil;



@Service
public class MaintenanceShopServiceImpl implements MaintenanceShopService {
	Logger logger = LoggerFactory.getLogger(MaintenanceShopServiceImpl.class);
	@Autowired
	private MaintenanceShopDao maintenanceShopDao;
	@Autowired
	private TokenDao tokenInfoDao;
	
	public MessageUntil<String> saveCommentShop(HttpServletRequest request,List<MultipartFile> files){
		MessageUntil<String> mu = new MessageUntil<String>();
		mu.setMessageCode("0");
		int cusId = Integer.parseInt(request.getParameter("cusId"));
	    String cusName = request.getParameter("cusName");	
	    int environmentRate = Integer.parseInt(request.getParameter("environmentRate"));
	    int serviceRate = Integer.parseInt(request.getParameter("serviceRate"));
	    int skillRate = Integer.parseInt(request.getParameter("skillRate"));
	    String comment = request.getParameter("comment");
	    int shopId = Integer.parseInt(request.getParameter("shopId"));
	    Date createTime = new Date();
	    try {
//	    	ShopComment comments = 
//					new ShopComment(cusId, cusName, environmentRate, serviceRate, skillRate, comment, createTime, shopId,"2");
//			maintenanceShopDao.saveShopComment(comments);
			mu.setMessageStr("评论成功");
		} catch (Exception e) {
			mu.setMessageCode("2");
			logger.info("保存评论出错:"+e);
		}
		
		return mu;
	}
	
	public MessageUntil<List<CommentEntity>> findShopComments(HttpServletRequest request){
		MessageUntil<List<CommentEntity>> mu = new MessageUntil<List<CommentEntity>>();
		mu.setMessageCode("0");
		int shopId = Integer.parseInt(request.getParameter("shopId"));
		String classify = "2";
		String sort = "2";
		try {
			List<CommentEntity> list = maintenanceShopDao.findShopComment(shopId,classify,sort);
			HashMap<String, String> map = maintenanceShopDao.commentResult(shopId,"2");
			mu.setData(list);
			mu.setObject(map);
		} catch (Exception e) {
			mu.setMessageCode("2");
			logger.info("查询店铺信息出错:"+e);
		}
		
		return mu;
	}

	@Override
	public MessageUntil<HashMap<String, Object>> getServiceDet(
			HttpServletRequest request) {
		MessageUntil<HashMap<String, Object>> mu = new MessageUntil<HashMap<String,Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		String shopId = request.getParameter("shopId");
		String token = request.getParameter("token");
		Integer cusId = null;
		try {
			cusId = tokenInfoDao.selectByToken(token);
			if(cusId ==null){
				mu.setMessageCode("2");
				mu.setMessageStr("token失效");
				return mu;
			}
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("验证token登录信息异常");
			logger.info("验证token登录信息异常:"+e);
			return mu;
		}
		String jpsp = null;//request.getParameter("jpsp")
		String byfw = null;//request.getParameter("byfw")
		String ltfw = null;//request.getParameter("ltfw")
		String gzfw = null;//request.getParameter("gzfw")
		String azfw = null;//request.getParameter("azfw")
	    try {
	    	//获取店铺服务信息
	    	Map<String, String> servceMap = maintenanceShopDao.findServeceByShopId(shopId);
	    	if(servceMap !=null){
	    		jpsp = servceMap.get("jpsp");
	    		byfw = servceMap.get("byfw");
	    		ltfw = servceMap.get("ltfw");
	    		gzfw = servceMap.get("gzfw");
	    		azfw = servceMap.get("azfw");
	    	}else{
	    		mu.setMessageCode("1");
				mu.setMessageStr("该店铺无服务信息");
	    		return mu;
	    	}
		} catch (Exception e) {
			// TODO: handle exception
    		mu.setMessageCode("1");
			mu.setMessageStr("获取店铺服务信息失败");
    		return mu;
		}
		
		//查询对应表中的主键id
		Integer jpspId = 0;
		Integer byfwId = 0;
		Integer ltfwId = 0;
		Integer gzfwId = 0;
		Integer azfwId = 0;
		try {
			jpspId = maintenanceShopDao.findKeyIdByServiceName(0, jpsp);
			byfwId = maintenanceShopDao.findKeyIdByServiceName(0, byfw);
			ltfwId = maintenanceShopDao.findKeyIdByServiceName(0, ltfw);
			gzfwId = maintenanceShopDao.findKeyIdByServiceName(0, gzfw);
			azfwId = maintenanceShopDao.findKeyIdByServiceName(0, azfw);
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			logger.info("获取服务数据失败"+e);
			return mu;
		}
		//查询服务信息
		List<HashMap<String, Object>> jpspName =null;
		List<HashMap<String, Object>> byfwName =null;
		List<HashMap<String, Object>> ltfwName=null;
		List<HashMap<String, Object>> gzfwName=null;
		List<HashMap<String, Object>> azfwName=null;
		Integer jxspNum=0;
		Integer byfwNum=0;
		Integer ltfwNum=0;
		Integer gzfwNum=0;
		Integer azfwNum=0;
		if(jpspId!=null&&jpspId!=0){
			try {
				jpspName = maintenanceShopDao.findServiceByParentId(jpspId, shopId);
				jxspNum = maintenanceShopDao.findServiceNumsByParentId(jpspId, shopId);
			} catch (Exception e) {
				// TODO: handle exception
				mu.setMessageCode("1");
				logger.info("获取服务数据失败"+e);
				return mu;
			}

		}
		
		if(byfwId!=null&&byfwId!=0){
			try {
				byfwName = maintenanceShopDao.findServiceByParentId(byfwId, shopId);
				byfwNum = maintenanceShopDao.findServiceNumsByParentId(byfwId, shopId);
			} catch (Exception e) {
				// TODO: handle exception
				mu.setMessageCode("1");
				logger.info("获取服务数据失败"+e);
				return mu;
			}

		}
		
		if(ltfwId!=null&&ltfwId!=0){
			try {
				ltfwName = maintenanceShopDao.findServiceByParentId(ltfwId, shopId);
				ltfwNum = maintenanceShopDao.findServiceNumsByParentId(ltfwId, shopId);
			} catch (Exception e) {
				// TODO: handle exception
				mu.setMessageCode("1");
				logger.info("获取服务数据失败"+e);
				return mu;
			}

		}
		
		if(gzfwId!=null&&gzfwId!=0){
			try {
				gzfwName = maintenanceShopDao.findServiceByParentId(gzfwId, shopId);
				gzfwNum = maintenanceShopDao.findServiceNumsByParentId(gzfwId, shopId);
			} catch (Exception e) {
				// TODO: handle exception
				mu.setMessageCode("1");
				logger.info("获取服务数据失败"+e);
				return mu;
			}

		}
		
		if(azfwId!=null&&azfwId!=0){
			try {
				azfwName = maintenanceShopDao.findServiceByParentId(azfwId, shopId);
				azfwNum = maintenanceShopDao.findServiceNumsByParentId(azfwId, shopId);
			} catch (Exception e) {
				// TODO: handle exception
				mu.setMessageCode("1");
				logger.info("获取服务数据失败"+e);
				return mu;
			}

		}
		map.put("jpspName", jpspName);
		map.put("jpspNum", jxspNum);
		map.put("byfwName", byfwName);
		map.put("byfwNum", byfwNum);
		map.put("ltfwName", ltfwName);
		map.put("ltfwNum", ltfwNum);
		map.put("gzfwName", gzfwName);
		map.put("gzfwNum", gzfwNum);
		map.put("azfwName", azfwName);
		map.put("azfwNum", azfwNum);
		mu.setData(map);	
		mu.setMessageCode("0");
		
		return mu;
	}

//	private HashMap<String,Object> findData(Integer id,String str){
//		HashMap<String,Object> map = new HashMap<String, Object>();
//		if(id!=null&&id!=0){
//			try {
//				List<HashMap<String, Object>> Name = maintenanceShopDao.findServiceByParentId(id, str);
//				Integer num = maintenanceShopDao.findServiceByParentIdNums(id, str);
//				map.put("name", Name);
//				map.put("num", num);
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//
//		}		
//		return map;
//		
//	}
	
	

}
