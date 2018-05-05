package com.sumainfo.agency.service;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.sumainfo.common.until.MessageUntil;

public interface ComplaintManagementService {
	
	/**
	 *保存客户投诉信息
	 * @param request
	 * @return
	 */
	MessageUntil<String> saveComplaintMessage(HttpServletRequest request,MultipartFile[] files);
	
	/**
	 * 客户查询自己的投诉信息
	 * @param request
	 * @return
	 */
	MessageUntil<HashMap<String,Object>> findComplaintMessage(HttpServletRequest request);
	
	
	/**
	 * 查询客户自己的投诉详细信息及回复的list
	 * @param request
	 * @return
	 */
	MessageUntil<HashMap<String,Object>> findComplaintDetMessage(HttpServletRequest request);
	
	/**
	 * 客户回复投诉信息
	 * @param request
	 * @param files
	 * @return
	 */
	MessageUntil<String> saveReply(HttpServletRequest request,MultipartFile[] files);
	
	/**
	 * 根据用户编号查询用户收藏的主键-》然后就可以根据主键查询信息
	 * @author:zhlu
	 * @date: 2018年3月2日
	 * @param params
	 * @return
	 */
	List<Map<String,Object>> getColletList(Map<String,Object>params);
	
	/**
	 * 根据用户编号查询用户收藏的主键-》然后就可以根据主键查询信息总数
	 * @author:zhlu
	 * @date: 2018年3月19日
	 * @param params
	 * @return
	 */
	Integer getColletListCout(Map<String,Object>params);
	
	/**
	 * 根据用户编号获取用户的评价
	 * @author:zhlu
	 * @date: 2018年3月5日
	 * @param params
	 * @return
	 * @throws Exception 
	 */
	List<Map<String,Object>> getClientEva(Map<String,Object> params) throws Exception;

	/**
	 * 根据token获取用户编号
	 * @author:zhlu
	 * @date: 2018年3月9日
	 * @param params
	 * @return
	 */
	Map<String,Object>getToken(Map<String,Object>params);
	
	/**
	 * 根据用户编号获取用户的评价总数
	 * @author:zhlu
	 * @date: 2018年3月19日
	 * @param params
	 * @return
	 */
	List<Map<String,Object>> getCustomerCont(Map<String,Object>params);
	
	/**
	 * 结束投诉
	 * @param request
	 * @return
	 */
	MessageUntil<String> overCom(HttpServletRequest request);
	
}
