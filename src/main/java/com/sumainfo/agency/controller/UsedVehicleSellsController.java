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
import org.springframework.web.multipart.MultipartFile;
import com.sumainfo.agency.service.UsedVehicleSellsService;
import com.sumainfo.common.until.ObjectToJsonUntil;
import com.sumainfo.common.until.MessageUntil;

/**
 * 二手车交易
 * @author Administrator
 *
 */
@RestController
public class UsedVehicleSellsController {
	Logger logger = LoggerFactory.getLogger(UsedVehicleSellsController.class);
	@Autowired
	private UsedVehicleSellsService usedVehicleSellsService;
	
	/**
	 * 二手车信息发布需要带token
	 * @param request
	 * @return
	 */
	@PostMapping("/usedVehicleSellsMessage.do")
	public void findVehicleOrderMessage(HttpServletRequest request,HttpServletResponse response,MultipartFile[] file){
		MessageUntil<String> res = usedVehicleSellsService.usedVehicleSellsMessage(request, file);
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
	 * 查询发布的二手车信息 带token
	 * @param request
	 * @return
	 */
	@PostMapping("/findMyUsedVehicle.do")
	public MessageUntil<HashMap<String, Object>> findMyUsedVehicle(HttpServletRequest request){
		return usedVehicleSellsService.findMyUsedVehicleMessage(request);
	}
	
	/**
	 * 更该发布的二手车状态 带token
	 * @param request
	 * @return
	 */
	@PostMapping("/upDateMyUsedVehicle.do")
	public MessageUntil<String> upDateMyUsedVehicle(HttpServletRequest request){
		return usedVehicleSellsService.retMyUsedVehicle(request);
	}
	
	/**
	 * 删除发布的二手车信息 带token
	 * @param request
	 * @return
	 */
	@PostMapping("/deleteMyUsedVehicle.do")
	public MessageUntil<String> deleteMyUsedVehicle(HttpServletRequest request){
		return usedVehicleSellsService.deleteMyUsedVehicle(request);
	}
	

}
