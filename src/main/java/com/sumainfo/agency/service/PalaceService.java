package com.sumainfo.agency.service;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sumainfo.common.entity.AreaTable;
import com.sumainfo.common.until.MessageUntil;

public interface PalaceService {
	
	/**
	 * 保存地区信息
	 * @param request
	 * @return
	 */
	MessageUntil<String> saveAreaMessage(HttpServletRequest request);
	
	
	/**
	 * 查询地区信息
	 * @param request
	 * @return
	 */
	MessageUntil<List<AreaTable>> findArea(HttpServletRequest request);


}
