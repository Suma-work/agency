package com.sumainfo.agency.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.sumainfo.common.until.MessageUntil;

public interface UsedVehicleSellsService {
	
	/**
	 *保存需出售的二手车车辆信息
	 * @param request
	 * @return
	 */
	MessageUntil<String> usedVehicleSellsMessage(HttpServletRequest request, MultipartFile[] files);
	
	
	/**
	 * 查询自己发布的二手车信息
	 * @param request
	 * @return
	 */
	MessageUntil<HashMap<String, Object>> findMyUsedVehicleMessage(HttpServletRequest request);
	
	/**
	 * 下架自己的二手车信息
	 * @param request
	 * @return
	 */
	MessageUntil<String> retMyUsedVehicle(HttpServletRequest request);
	
	/**
	 * 删除发布的二手车信息
	 * @param request
	 * @return
	 */
	MessageUntil<String> deleteMyUsedVehicle(HttpServletRequest request);
	
	


}
