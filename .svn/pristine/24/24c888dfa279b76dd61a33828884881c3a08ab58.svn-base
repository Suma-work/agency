package com.sumainfo.agency.controller;


import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sumainfo.agency.service.CommercialTenantVisitorMsService;
import com.sumainfo.common.entity.OrderDetMs;
import com.sumainfo.common.entity.OrderMs;
import com.sumainfo.common.entity.OrderSkipDetMs;
import com.sumainfo.common.until.MessageUntil;
/**
 *
 * @author Administrator
 *
 */
@RestController
public class CommercialTenantVisitorMsController {
	Logger logger = LoggerFactory.getLogger(CommercialTenantVisitorMsController.class);
	@Autowired
	private CommercialTenantVisitorMsService ctvService;
	
	//跳转到销售登记页面时查询预约信息
	@PostMapping("getOrderMs.do")
	public MessageUntil<List<OrderMs>> getOrderMs(HttpServletRequest request){
		return ctvService.findVisitorOrderMs(request);

	}
	
	//获取预约信息（跳转到预约修改信息的查询）
	@PostMapping("getDetMs.do")
	public MessageUntil<OrderDetMs> getDetMs(HttpServletRequest request){
		return ctvService.getDetMs(request);
	}
	
	//登录人员获取预约的信息
	@PostMapping("getVoMsList.do")
	public MessageUntil<HashMap<String, Object>> getVoMsList(HttpServletRequest request){
		return ctvService.getVoMsList(request);

	}
	
	//获取店员信息
	@PostMapping("getComUserMs.do")
	public MessageUntil<List<HashMap<String,Object>>> getComUserMs (HttpServletRequest request){
		return ctvService.getComUserMs(request);
	}
	
	//修改预约信息
	@PostMapping("modifyOrder.do")
	public MessageUntil<String> modifyOrder(HttpServletRequest request){
		return ctvService.modifyOrder(request);
	}
	
	//获取预约信息（跳转到销售登记页面）
	@PostMapping("getSkipDetMs.do")
	public MessageUntil<OrderSkipDetMs> getSkipDetMs(HttpServletRequest request){
		return ctvService.getSkipDetMs(request);
	}
	
	
	
	

}
