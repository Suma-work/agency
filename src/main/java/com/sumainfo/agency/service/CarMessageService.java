package com.sumainfo.agency.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import com.sumainfo.common.entity.FcvTable;
import com.sumainfo.common.entity.SeclvlTable;
import com.sumainfo.common.until.MessageUntil;

public interface CarMessageService {
	
	/**
	 * 获取一级车辆标签信息
	 * @param request
	 * @return
	 */
	public MessageUntil<List<FcvTable>> getFirstLevelMessage(HttpServletRequest request);
	
	/**
	 * 通过父级id查询二级车辆标签信息
	 * @param request
	 * @return
	 */
	public MessageUntil<List<SeclvlTable>> selectAllById(HttpServletRequest request);
	
	/**
	 * 上传照片
	 * @param request
	 * @return
	 */
	public MessageUntil<String> upload(HttpServletRequest request);



}
