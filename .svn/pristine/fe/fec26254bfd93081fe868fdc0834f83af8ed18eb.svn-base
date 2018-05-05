package com.sumainfo.agency.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sumainfo.agency.service.SellNewVehicleService;
import com.sumainfo.common.until.ObjectToJsonUntil;
import com.sumainfo.common.until.MessageUntil;

/**
 * 售车管理
 * @author Administrator
 *
 */
@RestController
public class SellNewVehicleController {
	Logger logger = LoggerFactory.getLogger(SellNewVehicleController.class);
	@Autowired
	private SellNewVehicleService sellNewVehicleService;
	
	/**
	 *售车信息
	 * @param request
	 * @return
	 */
	@PostMapping("/createVehicleMessage.do")
	public void createVehicleMessage(HttpServletRequest request,HttpServletResponse response){
		MessageUntil<String> res = sellNewVehicleService.createVehicleMessage(request);
		try {
			String resu = ObjectToJsonUntil.toJson(res);
			logger.info("resu:" + resu);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset = utf-8");
			try(PrintWriter out = response.getWriter()){
				out.write(resu);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	

}
