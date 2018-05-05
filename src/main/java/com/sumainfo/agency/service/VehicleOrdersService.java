package com.sumainfo.agency.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.sumainfo.common.until.MessageUntil;

public interface VehicleOrdersService {
	
	/**
	 *查询客户预约车辆信息
	 * @param request
	 * @return
	 */
	MessageUntil<HashMap<String, Object>> findVehicleOrderMessage(HttpServletRequest request);
	
	
	/**
	 * 预约看车信息
	 * @param request
	 * @return
	 */
	MessageUntil<String> saveVehicleOrder(HttpServletRequest request);
	
	/**
	 * 获取短信的回调参数
	 * @param request
	 */
	void smsNotify();
	
	/**
	 * 预约信息中跳转评价4s
	 * @param request
	 * @return
	 */
	MessageUntil<String> comment4sShop(HttpServletRequest request,MultipartFile[] files);
	
	/**
	 * 查询联系人
	 * @param request
	 * @return
	 */
	MessageUntil<Map<String, Object>> findContactMs(HttpServletRequest request);
	


}
