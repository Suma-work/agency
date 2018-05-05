package com.sumainfo.agency.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sumainfo.agency.service.CommercialTenantUserMsService;
import com.sumainfo.common.until.MessageUntil;
/**
 *用户端登录
 * @author Administrator
 *
 */
@RestController
public class CommercialTenantUserMsController {
	Logger logger = LoggerFactory.getLogger(CommercialTenantUserMsController.class);
	@Autowired
	private CommercialTenantUserMsService ctuService;
	
	@PostMapping("userLogin.do")
	public MessageUntil<HashMap<String, Object>> userLogin(HttpServletRequest request){
		return ctuService.userLogin(request);

	}
	
	@PostMapping("modifyPassWord.do")
	public MessageUntil<String> modifyPassWord(HttpServletRequest request){
		return ctuService.modifyPassWord(request);

	}
	
	@PostMapping("modifyPsIdentify.do")
	public MessageUntil<Map<String, Object>> modifyPsIdentify(HttpServletRequest request){
		return ctuService.modifyPsIdentify(request);

	}
	
	@PostMapping("getPlatBanner.do")
	public MessageUntil<List<HashMap<String, Object>>> getPlatBanner(HttpServletRequest request){
		return ctuService.getPlatBanner(request);
	}
	
	@PostMapping("homePageFirstPartMs.do")
	public MessageUntil<HashMap<String, Object>> homePageFirstPartMs(HttpServletRequest request){
		return ctuService.homePageFirstPartMs(request);
	}
	
	@PostMapping("homePagelastPartMs.do")
	public MessageUntil<Map<String, Object>> homePagelastPartMs(HttpServletRequest request){
		return ctuService.homePagelastPartMs(request);
	}
	
	@PostMapping("orderRank.do")
	public MessageUntil<Map<String, Object>> orderRank(HttpServletRequest request){
		return ctuService.orderRank(request);
	}
	


}
