package com.sumainfo.agency.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sumainfo.agency.service.SellVisitorService;
import com.sumainfo.common.until.MessageUntil;

/**
 * 访客管理
 * @author Administrator
 *
 */
@RestController
public class SellVisitorController {
	Logger logger = LoggerFactory.getLogger(SellVisitorController.class);
	@Autowired
	private SellVisitorService sellVisitorService;
	
	/**
	 *保存访客信息
	 * @param request
	 * @return
	 */
	@PostMapping("/createVisitorMessage.do")
	public MessageUntil<String> createVisitorMessage(HttpServletRequest request){
		return sellVisitorService.createVisitorMessage(request);
	}
	
	/**
	 *查询访客信息
	 * @param request
	 * @return
	 */
	@PostMapping("/findVisitorMessage.do")
	public MessageUntil<Map<String, Object>> findVisitorMessage(HttpServletRequest request){
		 return sellVisitorService.findVisitorMessage(request);
	}
	
	

}
