package com.sumainfo.agency.service;


import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sumainfo.common.entity.OrderDetMs;
import com.sumainfo.common.entity.OrderMs;
import com.sumainfo.common.entity.OrderSkipDetMs;
import com.sumainfo.common.until.MessageUntil;

/**
 * 所有商户端对访客的信息的处理（查询、删除、更该、新增）全部放到此类中处理
 *
 * @author Administrator
 *
 */

public interface CommercialTenantVisitorMsService {
	
	//商户获取已预约的信息
	MessageUntil<List<OrderMs>> findVisitorOrderMs(HttpServletRequest request);
	
	//获取预约信息（未来、今日的预约信息）
	MessageUntil<HashMap<String, Object>> getVoMsList(HttpServletRequest request);
	
	//获取预约信息（跳转到修改页面）
	MessageUntil<OrderDetMs> getDetMs(HttpServletRequest request);
	
	//获取预约信息（跳转到销售登记页面）
	MessageUntil<OrderSkipDetMs> getSkipDetMs(HttpServletRequest request);
	
	//获取店员信息
	MessageUntil<List<HashMap<String,Object>>> getComUserMs(HttpServletRequest request);
	
	//修改预约信息
	MessageUntil<String> modifyOrder(HttpServletRequest request);
	
	


}
