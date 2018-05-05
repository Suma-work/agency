package com.sumainfo.agency.controller;


import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sumainfo.agency.service.CustomerService;
import com.sumainfo.common.until.ObjectToJsonUntil;
import com.sumainfo.common.until.MessageUntil;

/**
 * 客户管理
 * @author Administrator
 *
 */
@RestController
public class CustomerController {
	Logger logger = LoggerFactory.getLogger(CustomerController.class);
	@Autowired
	CustomerService customerService;
	
	
	/**
	 * 已知密码修改密码
	 * @param request
	 * @return
	 */
	@PostMapping("/knowPsModifyPs.do")
	public MessageUntil<String> knowPsModifyPs(HttpServletRequest request){
		return customerService.knowPsModifyPs(request);
	}
	
	/**
	 * 登录账号
	 * @param request
	 * @return
	 */
	@PostMapping("/login.do")
	public void register(HttpServletRequest request,HttpServletResponse response){
		MessageUntil<String> res = customerService.loginAccount(request);
		try {
			String resu = ObjectToJsonUntil.toJson(res);
			logger.info("resu:" + resu);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset = utf-8");
			try(PrintWriter out = response.getWriter()){
				out.write(resu);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	/**
	 * 绑定和解绑
	 * @param request
	 * @return
	 */
	@PostMapping("/bandPhone.do")
	public MessageUntil<String> bandPhone(HttpServletRequest request){
		return customerService.bandPhone(request);
	}
	
	
	/**
	 * 注册前验证手机号
	 * @param request
	 * @param response
	 */
	@PostMapping("/identify.do")
	public void identify(HttpServletRequest request,HttpServletResponse response){
		MessageUntil<String> res = customerService.identify(request);
		try {
			String resu = ObjectToJsonUntil.toJson(res);
			logger.info("resu:" + resu);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset = utf-8");
			try(PrintWriter out = response.getWriter()){
				out.write(resu);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * 注册账号
	 * @param request
	 * @return
	 */
	@PostMapping("/register.do")
	public void loginUser(HttpServletRequest request,HttpServletResponse response){
		MessageUntil<String> res = customerService.register(request);
		try {
			String resu = ObjectToJsonUntil.toJson(res);
			logger.info("resu:" + resu);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset = utf-8");
			try(PrintWriter out = response.getWriter()){
				out.write(resu);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	/**
	 * 忘记密码
	 * 
	 */
	@PostMapping("/forgetPassword.do")
	public void forgetPassword(HttpServletRequest request,HttpServletResponse response){
		MessageUntil<String> res = customerService.forgetPassword(request);
		try {
			String resu = ObjectToJsonUntil.toJson(res);
			logger.info("resu:" + resu);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset = utf-8");
			try(PrintWriter out = response.getWriter()){
				out.write(resu);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * 修改密码
	 * @param request
	 * @param response
	 */
	@PostMapping("/modifyPassword.do")
	public void modifyPassword(HttpServletRequest request,HttpServletResponse response){
		MessageUntil<String> res = customerService.modifyPassword(request);
		try {
			String resu = ObjectToJsonUntil.toJson(res);
			logger.info("resu:" + resu);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset = utf-8");
			try(PrintWriter out = response.getWriter()){
				out.write(resu);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * 更换图像信息 需要token
	 * @param request
	 * @param file
	 * @return
	 */
	@PostMapping("/uploadPic.do")
	public MessageUntil<String> uploadPic(HttpServletRequest request,MultipartFile file){
		return customerService.uploadPic(request,file);
		
	}
	
	/**
	 * 登出 需要token
	 * @param request
	 * @return
	 */
	@PostMapping("/logout.do")
	public MessageUntil<String> logout(HttpServletRequest request){
		return customerService.logout(request);
		
	}
	
	/**
	 * 实名认证 需要token
	 * @param request
	 * @return
	 */
	@PostMapping("/certification.do")
	public MessageUntil<String> certification(HttpServletRequest request){
		return customerService.certification(request);
		
	}
	
	/**
	 * 修改昵称 需要token
	 * @param request
	 * @return
	 */
	@PostMapping("/modifyNikeName.do")
	public MessageUntil<String> modifyNikeName(HttpServletRequest request){
		return customerService.modifyNikeName(request);
		
	}
	
	/**
	 * 发送验证码到新手机上
	 * @param request
	 * @return
	 */
	@PostMapping("/sendMsgToNewPhone.do")
	public MessageUntil<String> sendMsgToNewPhone (HttpServletRequest request){
		return customerService.sendMsgToNewPhone(request);
	}
	
	/**
	 * 修改手机号 需要token
	 * @param request
	 * @return
	 */
	@PostMapping("/modifyPhone.do")
	public MessageUntil<String> modifyPhone(HttpServletRequest request){
		return customerService.modifyPhone(request);
		
	}
	
	/**
	 * 添加爱车信息
	 * @param request
	 * @return
	 */
	@PostMapping("/addMyVehicle.do")
	public MessageUntil<String> addMyVehicle(HttpServletRequest request){
		return customerService.addMyVehicle(request);
	}
	
	/**
	 * 查询爱车信息
	 * @param request
	 * @return
	 */
	@PostMapping("/getMyVehicleList.do")
	public MessageUntil<HashMap<String, Object>> getMyVehicleList(HttpServletRequest request){
		return customerService.getMyVehicleList(request);
	}
	
	
	/**
	 * 删除爱车信息
	 * @param request
	 * @return
	 */
	@PostMapping("/deleteVehicle.do")
	public MessageUntil<String> deleteVehicle(HttpServletRequest request){
		return customerService.deleteVehicle(request);
		
	}
	
	/**
	 * 更该默认爱车
	 * @param request
	 * @return
	 */
	@PostMapping("/updateMyVehicleStatus.do")
	public MessageUntil<String> updateMyVehicleStatus (HttpServletRequest request){
		return customerService.updateMyVehicleStatus(request);
	}
	
	/**
	 * 获取自己的个人信息
	 * @param request
	 * @return
	 */
	@PostMapping("/getMyDet.do")
	public MessageUntil<HashMap<String, Object>> getMyDet(HttpServletRequest request){
		return customerService.getMyDet(request);
	}
	

}
