package com.sumainfo.agency.service;



import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.sumainfo.common.until.MessageUntil;

public interface VehicleMessageService {

	/**
	 * 查询车辆信息
	 * @param request
	 * @return
	 */
	MessageUntil<HashMap<String,Object>> findVehicleMessage(HttpServletRequest request);
	
	/**
	 * 根据汽车的车型名称、店铺类型有关店铺评论及车辆信息（综合信息的返回）
	 * @param request
	 * @return
	 */
	MessageUntil<HashMap<String, Object>> findVehicleMixMessage(HttpServletRequest request);
	
	/**
	 * 向下滑动加载汽车信息
	 * @param request
	 * @return
	 */
	MessageUntil<HashMap<String, Object>> lapseLoadVehicleMessage(HttpServletRequest request);
	
	/**
	 * 获取二手车信息
	 * @param request
	 * @return
	 */
	MessageUntil<HashMap<String, Object>> findUsedVehicleMix(HttpServletRequest request);
	
	/**
	 * 获取单个二手车详细信息
	 * @param request
	 * @return
	 */
	MessageUntil<HashMap<String, Object>> findUsedVehicleDet(HttpServletRequest request);
	
	
	/**
	 * 查询汽车详情页面信息
	 * @param request
	 * @return
	 */
	MessageUntil<HashMap<String, Object>> findVehicleDetMessage(HttpServletRequest request);
	
	/**
	 * 收藏车
	 * @param request
	 * @return
	 */
	MessageUntil<String> collectVehicle(HttpServletRequest request);
	
		
	/**
	 * 查询热销车型
	 * @param request
	 * @return
	 */
	MessageUntil<HashMap<String, Object>> findHotVehicleType(HttpServletRequest request);
	
	/**
	 * 查询自己关注的车信息
	 * @param request
	 * @return
	 */
	MessageUntil<HashMap<String, Object>> findMyCollectVehicle(HttpServletRequest request);


}
