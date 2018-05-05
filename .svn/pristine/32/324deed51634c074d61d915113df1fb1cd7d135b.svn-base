package com.sumainfo.agency.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sumainfo.agency.service.FcvTableService;
import com.sumainfo.common.until.ObjectToJsonUntil;
import com.sumainfo.common.until.MessageUntil;

/**
 * 从阿里云上面获取汽车标签的请求接口，将车辆标签信息存入项目数据库
 * @author Administrator
 *
 */
@RestController
public class FcvTableController {
	Logger logger = LoggerFactory.getLogger(FcvTableController.class);
	@Autowired
	private FcvTableService fcvTableService;
	
	/**
	 * 从远程接口地址获取车辆一级标签信息
	 * @param request
	 * @return
	 */
	@PostMapping("/insertCarFirstLevel")
	public void insertCarFirstLevel(HttpServletRequest request,HttpServletResponse response){
		MessageUntil<String> res = fcvTableService.insertFcvList(request);
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
	
	/**
	 * 从远程接口地址获取车辆二级标签信息
	 * @param request
	 * @return
	 */
	@PostMapping("/insertCarSecLevel")
	public void insertCarSecLevel(HttpServletRequest request,HttpServletResponse response){
		MessageUntil<String> res = fcvTableService.insertCarSecLevelList(request);
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
