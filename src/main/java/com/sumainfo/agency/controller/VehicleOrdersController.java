package com.sumainfo.agency.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.sumainfo.agency.service.VehicleOrdersService;
import com.sumainfo.common.until.ObjectToJsonUntil;
import com.sumainfo.common.until.MessageUntil;

/**
 * 预约信息（购车、维保、其他的等）
 * @author Administrator
 *
 */
@RestController
public class VehicleOrdersController {
	Logger logger = LoggerFactory.getLogger(VehicleOrdersController.class);
	@Autowired
	private VehicleOrdersService vehicleOrdersService;
	
	/**
	 * 预约买车信息查询
	 * @param request
	 * @return
	 */
	@PostMapping("/findVehicleOrderMessage.do")
	public MessageUntil<HashMap<String, Object>> findVehicleOrderMessage(HttpServletRequest request,HttpServletResponse response){
		 return vehicleOrdersService.findVehicleOrderMessage(request);

	}
	
	/**
	 * 预约买车信息保存
	 * @param request
	 * @return
	 */
	@PostMapping("/saveVehicleOrderMessage.do")
	public void saveVehicleOrderMessage(HttpServletRequest request,HttpServletResponse response){
		MessageUntil<String> res = vehicleOrdersService.saveVehicleOrder(request);
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
	 * 评论4s店
	 * @param request
	 * @return
	 */
	@PostMapping("/comment4sShop.do")
	public void comment4sShop(HttpServletRequest request,HttpServletResponse response,MultipartFile[] file){
		MessageUntil<String> res = vehicleOrdersService.comment4sShop(request,file);
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
	 * 获取店家联系人
	 * @param request
	 * @return
	 */
	@PostMapping("/findContactMs.do")
	public MessageUntil<Map<String, Object>> findContactMs(HttpServletRequest request){
		return vehicleOrdersService.findContactMs(request);
	}

	

}
