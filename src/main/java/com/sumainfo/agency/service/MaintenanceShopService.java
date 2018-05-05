package com.sumainfo.agency.service;


import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.sumainfo.common.entity.CommentEntity;
import com.sumainfo.common.until.MessageUntil;
public interface MaintenanceShopService {
	
	/**
	 * 维保店铺评论
	 * @param request
	 * @return
	 */
	MessageUntil<String> saveCommentShop(HttpServletRequest request,List<MultipartFile> files);
	
	/**
	 * 查询店铺下所有评论
	 * @param request
	 * @return
	 */
	MessageUntil<List<CommentEntity>> findShopComments(HttpServletRequest request);
	
	
	/**
	 * 获取服务项目的详细信息
	 * @param request
	 * @return
	 */
	MessageUntil<HashMap<String, Object>> getServiceDet(HttpServletRequest request);
	


}
