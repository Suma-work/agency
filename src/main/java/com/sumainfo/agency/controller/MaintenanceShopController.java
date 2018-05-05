package com.sumainfo.agency.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sumainfo.agency.service.MaintenanceShopService;
import com.sumainfo.common.entity.CommentEntity;
import com.sumainfo.common.until.ObjectToJsonUntil;
import com.sumainfo.common.until.MessageUntil;

/**
 * 店铺评分（4S店、维保店、其他汽车店）
 * @author Administrator
 *
 */
@RestController
public class MaintenanceShopController {
	Logger logger = LoggerFactory.getLogger(MaintenanceShopController.class);
	@Autowired
	private MaintenanceShopService maintenanceShopService;
	
	/**
	 * 评分信息保存
	 * @param request
	 * @return
	 */
	@PostMapping("/saveComment.do")
	public void saveComment(HttpServletRequest request,HttpServletResponse response,List<MultipartFile> file){
		MessageUntil<String> res = maintenanceShopService.saveCommentShop(request,file);
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
//		return res;
	}
	
	/**
	 * 评分信息查询
	 * @param request
	 * @return
	 */
	@PostMapping("/shopComment.do")
	public void shopComment(HttpServletRequest request,HttpServletResponse response){
		MessageUntil<List<CommentEntity>> res = maintenanceShopService.findShopComments(request);
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
	 * 获取服务信息
	 * @param request
	 * @return
	 */
	@PostMapping("/getServiceDet.do")
	public MessageUntil<HashMap<String, Object>> getServiceDet(HttpServletRequest request){
		  return maintenanceShopService.getServiceDet(request);
	}
	
	

}
