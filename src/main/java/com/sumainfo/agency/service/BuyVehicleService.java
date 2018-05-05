package com.sumainfo.agency.service;



import javax.servlet.http.HttpServletRequest;

import com.sumainfo.common.until.MessageUntil;

public interface BuyVehicleService {
	
	/**
	 *保存推荐购买车辆信息
	 * @param request
	 * @return
	 */
	MessageUntil<String> saveRecBuyVehicle(HttpServletRequest request);
	
	


}
