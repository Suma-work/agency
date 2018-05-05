package com.sumainfo.agency.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sumainfo.agency.service.VehicleMessageService;
import com.sumainfo.common.until.ObjectToJsonUntil;
import com.sumainfo.common.until.MessageUntil;
/**
 * 汽车标签信息查询
 * @author Administrator
 *
 */
@RestController
public class VehicleMessageController {
	Logger logger = LoggerFactory.getLogger(VehicleMessageController.class);
	@Autowired
	private VehicleMessageService vehicleMessageService;
	
	
	  /**
	    * 车辆标签 (点击车标跳转)查询汽车详情
	    * @param request
	    * @param response
	    */
	   @PostMapping("/findVehicleMixMessage.do")
	   public  MessageUntil<HashMap<String, Object>> findVehicleMixMessage(HttpServletRequest request,HttpServletResponse response){
		   MessageUntil<HashMap<String, Object>> res = 
				   vehicleMessageService.findVehicleMixMessage(request);
		   return res;
		   
	   }
	   /**
	    * 下滑获取车辆信息
	    * @param request
	    * @param response
	    */
	   @PostMapping("/lapseLoadVehicleMessage.do")
	   public void lapseLoadVehicleMessage(HttpServletRequest request,HttpServletResponse response){
		   MessageUntil<HashMap<String, Object>> res = 
				   vehicleMessageService.lapseLoadVehicleMessage(request);
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
				logger.info("下滑获取车辆信息查询报错:"+e);
			}
		   
	   }
	   
	   /**
	    *点击页面  获取二手车信息(第一次跳转的汽车详情页面)
	    * @param request
	    * @param response
	    */
	   @PostMapping("/findUsedVehicleMix.do")
	   public void findUsedVehicleMix(HttpServletRequest request,HttpServletResponse response){
		   MessageUntil<HashMap<String, Object>> res = 
				   vehicleMessageService.findUsedVehicleMix(request);
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
				logger.info("点击页面获取二手车信息:"+e);
			}
		   
	   }
	   
	   /**
	    * 第二次跳转 新车 查询详情页面信息
	    * @param request
	    * @param response
	    */
	   @PostMapping("/findVehicleDetMessage.do")
	   public MessageUntil<HashMap<String, Object>> findVehicleDetMessage(HttpServletRequest request,HttpServletResponse response){
		   MessageUntil<HashMap<String, Object>> res = 
				                       vehicleMessageService.findVehicleDetMessage(request);
		   return res;
	   }
	   
	   /**
	    * 第二次跳转获取二手单车信息
	    * @param request
	    * @param response
	    */
	   @PostMapping("/usedSingleVehicle.do")
	   public void usedSingleVehicle(HttpServletRequest request,HttpServletResponse response){
		   MessageUntil<HashMap<String, Object>> res = 
				        vehicleMessageService.findUsedVehicleDet(request);
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
				logger.info("点击页面获取二手车信息:"+e);
			}
		   
	   }  
	
	/**
	 * 滑动获取新车详细信息
	 * @param request
	 * @param response
	 */
	@PostMapping("/findVehicleMessage.do")
	public MessageUntil<HashMap<String, Object>> findVehicleMessage(HttpServletRequest request,HttpServletResponse response){
		MessageUntil<HashMap<String, Object>> res = vehicleMessageService.findVehicleMessage(request);
		return res;

	}
	
	
	/**
	 * 是否收藏车辆信息 需要传token
	 * @param request
	 * @param response
	 * @return
	 */
	@PostMapping("/isCollect.do")
	public MessageUntil<String> isCollect(HttpServletRequest request){
		return vehicleMessageService.collectVehicle(request);
	}
	
	
	/**
	 * 查询收藏的车辆信息
	 * @param request
	 * @return
	 */
	@PostMapping("/findMyCollectVehicle.do")
	public MessageUntil<HashMap<String, Object>> findMyCollectVehicle(HttpServletRequest request){
		return vehicleMessageService.findMyCollectVehicle(request);
	}

	
   
	
   
  
  
   
  

}
