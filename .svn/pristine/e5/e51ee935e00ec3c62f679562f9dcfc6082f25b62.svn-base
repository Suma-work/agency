package com.sumainfo.agency.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sumainfo.agency.dao.CommercialTenantUserMsDao;
import com.sumainfo.agency.dao.CommercialTenantVisitorMsDao;
import com.sumainfo.agency.service.CommercialTenantVisitorMsService;
import com.sumainfo.common.entity.OrderDetMs;
import com.sumainfo.common.entity.OrderMs;
import com.sumainfo.common.entity.OrderSkipDetMs;
import com.sumainfo.common.entity.SysUserMs;
import com.sumainfo.common.until.DateUntil;
import com.sumainfo.common.until.MessageUntil;


@Service
public class CommercialTenantVisitorMsServiceImpl implements CommercialTenantVisitorMsService {
	Logger logger = LoggerFactory.getLogger(CommercialTenantVisitorMsServiceImpl.class);

	@Autowired
	private CommercialTenantUserMsDao userDao;
	@Autowired
	private CommercialTenantVisitorMsDao visitDao;
	
	//跳转到销售登记页面时查询预约信息
	@Override
	public MessageUntil<List<OrderMs>> findVisitorOrderMs(
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		MessageUntil<List<OrderMs>> mu = new MessageUntil<List<OrderMs>>();
		//首先根据前台传入的值获取客户预约信息，再根据预约信息去和返回短信数据做匹配更新预约状态
		String token = request.getParameter("token");
		String param = request.getParameter("param");
		try {
			//获取店员信息
			SysUserMs sum =  userDao.findUserDetMs(token);
			//获取该店员下，待同意的预约单
			List<OrderMs> OrderList = visitDao.getNotAgreeOrder(param, sum.getUser_id(), 0);
			if(OrderList.size()>0){
				for(OrderMs map:OrderList){
					Date date = map.getCreateTime();
					String str = DateUntil.turnStrings(date);
					Long lon = Long.valueOf(str);
					//获取上行信息中的时间，该表的时间为字符串
					List<String> checkList = visitDao.getCheckMs(map.getViewerPhone());
					if(checkList.size()<=0){
						break;
					}
					for(String s:checkList){
						Long cs = Long.valueOf(s);
						Long c = cs-lon;
						//如果回复时间小于1天，则表示预约成功 则更新表中数据
						if(c<=10000){
							visitDao.updateOrderStatus(map.getId(),"1");//把预约信息更新为已预约状态
						}
					}
					
				}
			}
			//获取已经预约的信息
			List<OrderMs> Orders = visitDao.getNotAgreeOrder(param, sum.getUser_id(), 1);
			mu.setData(Orders);
			mu.setMessageCode("0");
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("查询订单数据异常"+e);
		}
		return mu;
	}
	
	

	//登录人员获取预约的信息或接待信息
	@Override
	public MessageUntil<HashMap<String, Object>> getVoMsList(HttpServletRequest request) {
		// TODO Auto-generated method stub
		MessageUntil<HashMap<String, Object>> mu = new MessageUntil<HashMap<String, Object>>();
		HashMap<String, Object> maps = new HashMap<String, Object>();
		String token = request.getParameter("token");
		String date = request.getParameter("date");
		String page = "1";
		try {
			page = request.getParameter("page");
			if(page==null){
				page = "1";
			}
		} catch (Exception e) {
			// TODO: handle exception
			page = "1";
		}
		int minPage = (Integer.valueOf(page)-1)*10;
		int maxPage = Integer.valueOf(page)*10;
		try {
			//获取店员信息
			SysUserMs sum =  userDao.findUserDetMs(token);
			//获取该店员下，待同意的预约单
			List<OrderMs> OrderList = visitDao.getNotOrder(sum.getUser_id(), 0);
			if(OrderList.size()>0){
				for(OrderMs map:OrderList){
					Date mpDate = map.getCreateTime();
					String str = DateUntil.turnStrings(mpDate);
					Long lon = Long.valueOf(str);
					//获取上行信息中的时间，该表的时间为字符串
					List<String> checkList = visitDao.getCheckMs(map.getViewerPhone());
					if(checkList.size()<=0){
						break;
					}
					for(String s:checkList){
						Long cs = Long.valueOf(s);
						Long c = cs-lon;
						//如果回复时间小于1天，则表示预约成功 则更新表中数据
						if(c<=10000){
							visitDao.updateOrderStatus(map.getId(),"1");//把预约信息更新为已预约状态
						}
					}
					
				}
			}
			//获取除去没有预约状态下的数据
			List<OrderMs> Orders = visitDao.getCheckOrder(DateUntil.turnToDates(date), sum.getUser_id(),"0",minPage,maxPage);
			int counts = visitDao.getCheckOrderNum(DateUntil.turnToDates(date),sum.getUser_id(), "0");
			maps.put("Orders", Orders);
			maps.put("counts", counts);
			mu.setData(maps);
			mu.setMessageCode("0");
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("查询订单数据异常"+e);
		}
		return mu;
	}

	//获取预约信息（跳转到预约修改信息的查询）
	@Override
	public MessageUntil<OrderDetMs> getDetMs(HttpServletRequest request) {
		// TODO Auto-generated method stub
		MessageUntil<OrderDetMs> mu = new MessageUntil<OrderDetMs>();
		String id = request.getParameter("id");
		try {
			OrderDetMs  order = visitDao.getDetOrder(Integer.valueOf(id));
			mu.setData(order);
			mu.setMessageCode("0");
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("获取信息失败");
			logger.info("获取信息失败"+e);
		}
		return mu;
	}

	//获取店员信息
	@Override
	public MessageUntil<List<HashMap<String,Object>>> getComUserMs(HttpServletRequest request) {
		// TODO Auto-generated method stub
		MessageUntil<List<HashMap<String,Object>>> mu = new MessageUntil<List<HashMap<String,Object>>>();
		String token = request.getParameter("token");
		try {
			SysUserMs sum = userDao.findUserDetMs(token);
			List<HashMap<String,Object>> comUser = visitDao.getComUserMs(sum.getShopid());
			mu.setData(comUser);
			mu.setMessageCode("0");
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("获取信息失败");
			logger.info("获取信息失败"+e);
		}
		return mu;
	}

	//修改预约信息
	@Override
	public MessageUntil<String> modifyOrder(HttpServletRequest request) {
		// TODO Auto-generated method stub
		MessageUntil<String> mu = new MessageUntil<String>();
		String id = request.getParameter("id");
		String orderDate = request.getParameter("orderDate");
		String orderTime = request.getParameter("orderTime");
		int uid = Integer.valueOf(request.getParameter("user_id"));
//		String contactPhone = request.getParameter("contactPhone");
		try {
			visitDao.updateOrderMs(Integer.valueOf(id), orderDate, orderTime,uid);
			mu.setMessageCode("0");
			mu.setMessageStr("修改成功");
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("修改失败");
			logger.info("修改失败"+e);
		}
		return mu;
	}



	//获取预约信息（跳转到销售登记页面）
	@Override
	public MessageUntil<OrderSkipDetMs> getSkipDetMs(HttpServletRequest request) {
		// TODO Auto-generated method stub
		MessageUntil<OrderSkipDetMs> mu = new MessageUntil<OrderSkipDetMs>();
		String id = request.getParameter("id");
		try {
			OrderSkipDetMs order = visitDao.getSkipDetOrder(Integer.valueOf(id));
			mu.setData(order);
			mu.setMessageCode("0");
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("获取信息失败");
			logger.info("获取信息失败"+e);
		}
		return mu;
	}


	
}
