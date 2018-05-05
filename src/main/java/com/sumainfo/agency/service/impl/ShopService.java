package com.sumainfo.agency.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.mysql.jdbc.Constants;
import com.sumainfo.agency.dao.ComplaintManagementDao;
import com.sumainfo.agency.dao.CustomerDao;
import com.sumainfo.agency.dao.ShopMapper;
import com.sumainfo.agency.dao.TokenDao;
import com.sumainfo.common.distance.ComputedRange;
import com.sumainfo.common.entity.ShopDetOrders;
import com.sumainfo.common.entity.ShopMainOrders;
import com.sumainfo.common.until.ComUtils;
import com.sumainfo.common.until.ComparatorSort;
import com.sumainfo.common.until.JsonResult;
import com.sumainfo.common.until.MessageUntil;
import com.sumainfo.common.until.StaticConstants;

@Service
public class ShopService implements Serializable{
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(ShopService.class);
	@Autowired
	private ShopMapper shopMapper;
	@Autowired
	private TokenDao tokenInfoDao;
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private ComplaintManagementDao complaintManagementDao;
	
	public Integer shopListCout(Map<String,Object>params){
		return shopMapper.getShopCount(params);
	}
	
	/**
	 * 获取所有的店铺
	 * @author:zhlu
	 * @date: 2018年2月27日
	 * @param params
	 * @return
	 */
	public List<Map<String,Object>> getShop(Map<String, Object> params) {
		List<Map<String,Object>>result=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> shopList=shopMapper.getShopList(params);
		for (Map<String, Object> map : shopList) {
			//获取店铺主键
			params.put("shopId", map.get("shopId"));
			Map<String, Object>ShopComment=shopMapper.getSumShopcomment(params);
			//得到这个店铺的评价分
			if(!StringUtils.isEmpty(ShopComment.get("rate"))){
				map.put("rate", ShopComment.get("rate"));
			}else{
				map.put("rate",0);
			}
			//得到店铺展示图片slideshow为3
			Map<String, Object>PictureSave=shopMapper.getPicturesave(params);
			if(PictureSave !=null && PictureSave.containsKey("picAddress")&&!StringUtils.isEmpty(PictureSave.get("picAddress"))){
				map.put("picAddress",PictureSave.get("picAddress"));
			}else{//没有就给个默认的
				map.put("picAddress","http://www.sumainfor.com/wp-content/uploads/2017/12/logo2-1.png");
			}
			if(StringUtils.isEmpty(map.get("serveMold"))){
				map.put("serveMold","");
			}
			result.add(map);
		}
		return result;
	}
	
	public Map<String,Object>shopAnMap(Map<String,Object>params){
		return shopMapper.getShop(params);
	}
	
	/**
	 * 按照编号获取店铺信息
	 * @author:zhlu
	 * @date: 2018年2月27日
	 * @param params
	 * @return
	 */
	public Map<String,Object> getShopAn(Map<String, Object> params) {
		Map<String,Object>map=new HashMap<String, Object>();
		if(StringUtils.isEmpty(params.get("token"))){//没有token
			map.put("colMold",1);//未收藏
		}else{
			Map<String,Object>token=shopMapper.getToken(params);
			if(token==null){//匹配不上，返回重新登录
				return new HashMap<String, Object>();
			}else{//匹配的上，返回对应的收藏状态
				Map<String,Object>conllect=new HashMap<String,Object>();
				conllect.put("associationId",params.get("shopId"));//店铺编号
				conllect.put("classify", 1);//4S店铺
				conllect.put("cusId",token.get("userId"));//用户编号
				conllect.put("delfg",0);//废弃标志
				Map<String,Object>getConllect=shopMapper.getCollectMap(conllect);
				if(getConllect==null){//没有收藏编号
					map.put("colMold",1);//未收藏
				}else{
					map.put("colMold",getConllect.get("colMold"));//有就直接取
				}
			}
		}
		Map<String, Object>ShopMap=shopMapper.getShop(params);
		if(ShopMap !=null && ShopMap.containsKey("serveMold")&&!StringUtils.isEmpty(ShopMap.get("serveMold"))){
			map.putAll(ShopMap);
		}else{
			map.put("serveMold","");
			map.putAll(ShopMap);
		}
		Map<String, Object>ShopComment=shopMapper.getSumShopcomment(params);
		//得到这个店铺的评价分
		if(!StringUtils.isEmpty(ShopComment.get("rate"))){
//			result.put("rate", ShopComment.get("rate"));
			map.putAll(ShopComment);
		}else{
			map.put("rate",0);
			map.put("count",0);
		}
		//计算和用户之间的距离
		double distance = ComputedRange.getDistance(Double.parseDouble(ShopMap.get("lat").toString()),Double.parseDouble(ShopMap.get("lon").toString()),Double.parseDouble(params.get("lat").toString()),Double.parseDouble(params.get("lon").toString()));
		double distances=distance/1000;
		map.put("distance",String.format("%.2f",distances)+"KM");
		//获取店铺的图片
		List<Map<String, Object>>pictureList=shopMapper.getPicturesaveList(params);
		map.put("img", pictureList);
//		//获取店铺的用户评价
//		List<Map<String, Object>>ShopCommentList=shopMapper.getShopcomment(params);
//		map.put("ShopCommentList", ShopCommentList);
		//根据店铺编号获取店铺的可提供的服务数量
		List<Map<String,Object>>serveList=shopMapper.getServeListCount(params);
		map.put("serveList", serveList);
		
		//获取4S店铺活动信息
		List<Map<String,Object>>actiList=shopMapper.getActi(params);
		map.put("actiList", actiList);
		
		
		//获取店铺热销车型
		List<Map<String,Object>>shopSlhcar=new ArrayList<Map<String,Object>>();
		for (Map<String, Object> map2 : shopMapper.getShopSlhcar(params)) {
			Map<String,Object> veba=new HashMap<String,Object>();
			veba.put("bandName", map2.get("bandName"));
			veba.put("carName", map2.get("carName"));
			if(!StringUtils.isEmpty(map2.get("vehId"))){
				params.put("vehId", map2.get("vehId"));
				Map<String,Object>vePic=shopMapper.getVehba(params);
				if(vePic!=null&&"1".equals(vePic.get("isShow").toString())){
					veba.put("picAddress", vePic.get("picAddress"));
				}else{
					veba.put("picAddress","http://www.sumainfor.com/wp-content/uploads/2017/12/logo2-1.png");
				}
			}else{
				veba.put("picAddress","http://www.sumainfor.com/wp-content/uploads/2017/12/logo2-1.png");
			}
			params.put("carName", map2.get("carName"));
			Map<String,Object>veDet=shopMapper.getVeDet(params);
			veba.put("sellPrice", veDet.get("sellPrice"));
			shopSlhcar.add(veba);
		}
		map.put("shopslhcar",shopSlhcar);
		return map;
	}
	public Integer shopCommentListCout(Map<String,Object>params){
		return shopMapper.shopCommentListCout(params);
	}
	
	/**
	 * 获取店铺的评价
	 * @author:zhlu
	 * @date: 2018年3月6日
	 * @return
	 */
	public List<Map<String,Object>> shopCommentList(Map<String,Object>params){
		List<Map<String,Object>> result=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> shopCommentList=shopMapper.getShopcomment(params);
		for (Map<String, Object> map : shopCommentList) {
			Map<String,Object>shopComment=new HashMap<String,Object>();
			shopComment.put("cusId", map.get("cusId"));
			shopComment.put("cusName", map.get("cusName"));
			if(!StringUtils.isEmpty(map.get("portait"))){//如果有头像就直接获取头像
				shopComment.put("portait", map.get("portait"));
			}else{
				if(StringUtils.isEmpty(map.get("sex"))){//性别为空的时候就直接默认男头像
					shopComment.put("portait",StaticConstants.publicManPortaitUrl);
				}else{
					if("0".equals(map.get("sex").toString())){
						shopComment.put("portait",StaticConstants.publicWomanPortaitUrl);
					}else if("1".equals(map.get("sex").toString())){
						shopComment.put("portait",StaticConstants.publicManPortaitUrl);
					}
				}
			}
			Double avgScore=(Double.parseDouble(map.get("environmentRate").toString())+Double.parseDouble(map.get("environmentRate").toString())+Double.parseDouble(map.get("environmentRate").toString()))/3;
			shopComment.put("avgScore",avgScore);
			shopComment.put("comment", map.get("comment"));
			String[] a=map.get("imgs").toString().split(";");
			shopComment.put("imgs", a);
			shopComment.put("createTime", map.get("createTime"));
			params.put("comid", map.get("comid"));
			
			Map<String, Object>shopRep=shopMapper.getShopRep(params);
			//商家回复
			if(shopRep==null || shopRep.size()<1){
				shopComment.put("shopComment","");
			}else{
				shopComment.put("shopComment",shopRep.get("comment"));
			}
			result.add(shopComment);
		}
		return result;
	}
	
	/**
	 * 根据店铺编号获取店铺类型
	 * @author:zhlu
	 * @date: 2018年3月6日
	 * @param params
	 * @return
	 */
	public Map<String,Object> shop(Map<String,Object>params){
		Map<String,Object>result=new HashMap<String,Object>();
		Map<String,Object>shop=shopMapper.Shop(params);
		if(shop!=null && !StringUtils.isEmpty(shop)){
			result.put("classify",shop.get("classify"));
		}else{
			throw new IllegalArgumentException("无店铺信息");
		}
		return result;
	}
	
	public Integer shoplist(Map<String,Object>params){
		return shopMapper.getShopListElCout(params);
	}
	
	/**
	 * 获取所有的维保店铺
	 * @author:zhlu
	 * @date: 2018年2月27日
	 * @param params
	 * @return
	 */
	public List<Map<String,Object>> getShopEl(Map<String, Object> params) {
		List<Map<String,Object>>result=new ArrayList<Map<String,Object>>();
		List<Map<String,Object>> shopList=shopMapper.getShopListEl(params);
		for (Map<String, Object> map : shopList) {
			//获取店铺主键
			params.put("shopId", map.get("shopId"));
			//得到店铺展示图片3
			Map<String, Object>PictureSave=shopMapper.getPicturesave(params);
			if(PictureSave !=null && PictureSave.containsKey("picAddress")&&!StringUtils.isEmpty(PictureSave.get("picAddress"))){
				map.put("picAddress",PictureSave.get("picAddress"));
			}else{//没有就给个默认的
				map.put("picAddress","http://www.sumainfor.com/wp-content/uploads/2017/12/logo2-1.png");
			}
			
			Map<String, Object>ShopComment=shopMapper.getSumShopcomment(params);
			//得到这个店铺的评价分
			if(!StringUtils.isEmpty(ShopComment.get("rate"))){
				map.put("rate", ShopComment.get("rate"));
			}else{
				map.put("rate",0);
			}
			map.put("count", ShopComment.get("count"));
			
			double distance = ComputedRange.getDistance(Double.parseDouble(map.get("lat").toString()),Double.parseDouble(map.get("lon").toString()),Double.parseDouble(params.get("lat").toString()),Double.parseDouble(params.get("lon").toString()));
			double distances=distance/1000;
			//用户和这个店铺的距离
			map.put("distance",String.format("%.2f",distances));
			result.add(map);
		}
		//排序，根据distance字段排序，
		ComparatorSort.MyCompartor(result,params.get("sort").toString(),params.get("order").toString());
//		ComparatorSort.MyCompartor(result,params.get("sort").toString());
		return result;
	}
	
	public Map<String,Object>shopMap(Map<String,Object> params){
		return shopMapper.getShopEl(params);
	}
	
	/**
	 * 按照编号获取维保店信息
	 * @author:zhlu
	 * @date: 2018年2月27日
	 * @param params
	 * @return
	 */
	public Map<String,Object> getShopAnEl(Map<String, Object> params) {
		Map<String,Object>map=new HashMap<String, Object>();
		if(StringUtils.isEmpty(params.get("token"))){//没有token
			map.put("colMold",1);//未收藏
		}else{
			Map<String,Object>token=shopMapper.getToken(params);
			if(token==null){//匹配不上，返回重新登录
				return new HashMap<String, Object>();
			}else{//匹配的上，返回对应的收藏状态
				Map<String,Object>conllect=new HashMap<String,Object>();
				conllect.put("associationId",params.get("shopId"));//店铺编号
				conllect.put("classify", 2);//维保店
				conllect.put("cusId",token.get("userId"));//用户编号
				conllect.put("delfg",0);//废弃标志
				Map<String,Object>getConllect=shopMapper.getCollectMap(conllect);
				if(getConllect==null){//没有收藏编号
					map.put("colMold",1);//未收藏
				}else{
					map.put("colMold",getConllect.get("colMold"));//有就直接取
				}
			}
		}
		Map<String, Object>ShopMap=shopMapper.getShopEl(params);
		map.putAll(ShopMap);
		Map<String, Object>ShopComment=shopMapper.getSumShopcomment(params);
		//得到这个店铺的评价分
		if(!StringUtils.isEmpty(ShopComment.get("rate"))){
//			result.put("rate", ShopComment.get("rate"));
			map.putAll(ShopComment);
		}else{
			map.put("rate",0);
			map.put("count",0);
		}
		//计算和用户之间的距离
		double distance = ComputedRange.getDistance(Double.parseDouble(ShopMap.get("lat").toString()),Double.parseDouble(ShopMap.get("lon").toString()),Double.parseDouble(params.get("lat").toString()),Double.parseDouble(params.get("lon").toString()));
		double distances=distance/1000;
		map.put("distance",String.format("%.2f",distances)+"KM");
		//获取店铺的图片
		List<Map<String, Object>>pictureList=shopMapper.getPicturesaveList(params);
		map.put("img", pictureList);
//		//获取店铺的用户评价
//		List<Map<String, Object>>ShopCommentList=shopMapper.getShopcomment(params);
//		map.put("ShopCommentList", ShopCommentList);
		//根据店铺编号获取店铺的可提供的服务数量
		List<Map<String,Object>>serveList=shopMapper.getServeListCount(params);
		map.put("serveList", serveList);
		return map;
	}
	
	/**
	 * 获取店铺服务的预约信息
	 * @param request
	 * @return
	 */
	public MessageUntil<Map<String,Object>> getOrderShopService(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String, Object>();
		MessageUntil<Map<String,Object>> mu = new MessageUntil<Map<String,Object>>();
		String token = request.getParameter("token");
		String status = request.getParameter("status");
		String page = request.getParameter("page");
		Integer cusId = null;
		try {
			cusId = tokenInfoDao.selectByToken(token);
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("验证token登录信息异常");
			logger.info("验证token登录信息异常:"+e);
			return mu;
		}
		if(null==cusId){
			mu.setMessageCode("2");
			mu.setMessageStr("token失效");
			return mu;
		}
		try {
			List<HashMap<String,Object>> repMainList = shopMapper.findRepOrder(cusId, Integer.valueOf(status));
			Integer mainNums = shopMapper.findRepOrderMainNum(cusId, Integer.valueOf(status));
			map.put("repMainList", repMainList);
			map.put("mainNums", mainNums);
			mu.setData(map);
			mu.setMessageCode("0");
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("查询收藏维保店信息失败");
			logger.info("查询收藏维保店信息失败"+e);
		}

		return mu;
		
	}
	
	/**
	 * 取消订单
	 * @param request
	 * @return
	 */
	public MessageUntil<String> updateOrderStatus(HttpServletRequest request){
		MessageUntil<String> mu = new MessageUntil<String>();
		String token = request.getParameter("token");
		String orderNo = request.getParameter("orderNo");
		Integer cusId = null;
		try {
			cusId = tokenInfoDao.selectByToken(token);
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("验证token登录信息异常");
			logger.info("验证token登录信息异常:"+e);
			return mu;
		}
		if(null==cusId){
			mu.setMessageCode("2");
			mu.setMessageStr("token失效");
			return mu;
		}
		try {
			//3取消订单
			shopMapper.updateRepOrderStatus(orderNo, 3);
			mu.setMessageCode("0");
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("取消订单失败");
			logger.info("取消订单失败"+e);
		}
		return mu;
		
	}
	
	/**
	 * 获取预约店铺详情
	 * @param request
	 * @return
	 */
	public MessageUntil<HashMap<String, Object>> getOrderDet(HttpServletRequest request){
		MessageUntil<HashMap<String, Object>> mu = new MessageUntil<HashMap<String,Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		String token = request.getParameter("token");
		String orderNo = request.getParameter("orderNo");
		Integer cusId = null;
		try {
			cusId = tokenInfoDao.selectByToken(token);
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("验证token登录信息异常");
			logger.info("验证token登录信息异常:"+e);
			return mu;
		}
		if(null==cusId){
			mu.setMessageCode("2");
			mu.setMessageStr("token失效");
			return mu;
		}
		try {
			HashMap<String, Object> mainMsg = shopMapper.findMainOrderDet(orderNo);
			List<HashMap<String, Object>> detList = shopMapper.findDetOrderDet(orderNo);
			map.put("mainMsg", mainMsg);
			map.put("detList", detList);
			mu.setData(map);
			mu.setMessageCode("0");
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("获取店铺预约详情失败");
			logger.info("获取店铺预约详情失败"+e);
		}
		return mu;
		
	}
	
	/**
	 * 保存店铺的预约信息
	 */
	public MessageUntil<String> saveServiceOrder(HttpServletRequest request){
		MessageUntil<String> mu = new MessageUntil<String>();
		String token = request.getParameter("token");
		String shopId = request.getParameter("shopId");
		String cusName = request.getParameter("cusName");
		String phone = request.getParameter("phone");
		String orderDate = request.getParameter("orderDate");
		String orderTime = request.getParameter("orderTime");
		String orderNo = ComUtils.randomUID("svo");
		List<HashMap<String, Object>> detList = (List<HashMap<String,Object>>)request.getAttribute("detList");
		Integer cusId = null;
		try {
			cusId = tokenInfoDao.selectByToken(token);
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("验证token登录信息异常");
			logger.info("验证token登录信息异常:"+e);
			return mu;
		}
		if(null==cusId){
			mu.setMessageCode("2");
			mu.setMessageStr("token失效");
			return mu;
		}
		try {
			//1表示已经预约
			ShopMainOrders mainOrder = new ShopMainOrders(shopId, orderNo, cusId, cusName, phone, orderDate, orderTime, 1, new Date());
			shopMapper.saveShopMainOrder(mainOrder);
			ShopDetOrders shopDetOrders = null;
			ArrayList<ShopDetOrders> shopDetOrdersList = new ArrayList<ShopDetOrders>();
			for(HashMap<String, Object> om:detList){
				String serviceName = (String)om.get("serviceName");
				Double servicPrice = (Double)om.get("servicPrice");
				shopDetOrders = new ShopDetOrders(orderNo, serviceName, servicPrice);
				shopDetOrdersList.add(shopDetOrders);
			}
			shopMapper.saveShopDetOrder(shopDetOrdersList);
			mu.setMessageCode("0");
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("保存店铺预约项目失败");
			logger.info("保存店铺预约项目失败"+e);
		}
		
		return mu;
		
	}
	//再次预约
	public MessageUntil<String> orderServiceAgain(HttpServletRequest request){
		MessageUntil<String> mu = new MessageUntil<String>();
		String orderNo = request.getParameter("orderNo");
		String cusName = request.getParameter("cusName");
		String cusPhone = request.getParameter("cusPhone");
		String orderDate = request.getParameter("orderDate");
		String orderTime = request.getParameter("orderTime");
		Date createTime = new Date();
		try {
			shopMapper.orderServiceAgain(orderNo, cusName, cusPhone, orderDate, orderTime,createTime);
			mu.setMessageCode("0");
			mu.setMessageStr("预约成功");
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("预约失败");
			logger.info("预约失败"+e);
		}
		return mu;
		
	}
	
}
