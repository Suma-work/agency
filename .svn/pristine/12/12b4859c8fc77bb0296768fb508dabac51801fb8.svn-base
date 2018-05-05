package com.sumainfo.agency.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.sumainfo.common.until.MessageUntil;

/**
 * 所有商户端对用户信息的处理（查询、删除、更该、新增）全部放到此类中处理
 *
 * @author Administrator
 *
 */

public interface CommercialTenantUserMsService {
	//user登录
	//商户端登录
	MessageUntil<HashMap<String, Object>> userLogin(HttpServletRequest request);
	
	//忘记密码或修改密码
	MessageUntil<String> modifyPassWord(HttpServletRequest request);
	
	/**
	 * 发送短信
	 * @param request
	 * @return
	 */
	MessageUntil<Map<String, Object>> modifyPsIdentify(HttpServletRequest request);
	
	//获取首页的轮播图
	MessageUntil<List<HashMap<String, Object>>> getPlatBanner(HttpServletRequest request);
	
	/**
	 * 登录人获取首页上半部分信息
	 * @param request
	 * @return
	 */
	MessageUntil<HashMap<String, Object>> homePageFirstPartMs(HttpServletRequest request);
	
	/**
	 * 获取预约做多车型和数量
	 * @param request
	 * @return
	 */
	MessageUntil<Map<String, Object>> homePagelastPartMs(HttpServletRequest request);
	
	/**
	 * 获取预约、接待、销售后的排名
	 * @param request
	 * @return
	 */
	MessageUntil<Map<String, Object>> orderRank(HttpServletRequest request);
	
	
	

	
	


}
