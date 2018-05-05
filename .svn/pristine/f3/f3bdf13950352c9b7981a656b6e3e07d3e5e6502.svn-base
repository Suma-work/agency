package com.sumainfo.agency.service;



import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sumainfo.common.until.MessageUntil;

public interface PlatFormReleaseService {
	
	//获取热销新车或品质二手车信息
	MessageUntil<Map<String, Object>> getHotVehicleType();
	
	//获取附近的4S店、维保店信息
	MessageUntil<Map<String, Object>> getNearShop(HttpServletRequest request);
	
	//搜索框中搜索所需信息
	MessageUntil<Map<String, Object>> searchTab(HttpServletRequest request);
	

	


}
