package com.sumainfo.agency.controller;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sumainfo.agency.service.PlatFormReleaseService;
import com.sumainfo.common.until.MessageUntil;

/**
 * 平台热销车
 * @author Administrator
 *
 */
@RestController
public class PlatFormReleaseController {
	Logger logger = LoggerFactory.getLogger(PlatFormReleaseController.class);
	@Autowired
	private PlatFormReleaseService platFormReleaseService;
	
	/**
	 * 展示热销车
	 * @param request
	 * @return
	 */
	@PostMapping("/getHotVehicleType.do")
	public MessageUntil<Map<String, Object>> getHotVehicleType(){
		return platFormReleaseService.getHotVehicleType();
	}
	
	/**
	 * 获取附近商家信息
	 * @param request
	 * @return
	 */
	@PostMapping("/getNearShop.do")
	public MessageUntil<Map<String, Object>> getNearShop(HttpServletRequest request){
		return platFormReleaseService.getNearShop(request);
		
	}
	
	/**
	 * 首页的搜索框
	 * @param request
	 * @return
	 */
	@PostMapping("/searchTab.do")
	public MessageUntil<Map<String, Object>> searchTab(HttpServletRequest request){
		return platFormReleaseService.searchTab(request);
	}
}
