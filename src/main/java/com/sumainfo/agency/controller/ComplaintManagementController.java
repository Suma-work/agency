package com.sumainfo.agency.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sumainfo.agency.service.ComplaintManagementService;
import com.sumainfo.common.until.JsonResult;
import com.sumainfo.common.until.ObjectToJsonUntil;
import com.sumainfo.common.until.MessageUntil;
import com.sumainfo.common.until.PageUtils;
import com.sumainfo.common.until.Pager;
import com.sumainfo.common.until.Validation;

/**
 * 客户投诉管理
 * @author Administrator
 *
 */
@RestController
public class ComplaintManagementController {
	Logger logger = LoggerFactory.getLogger(ComplaintManagementController.class);
	@Autowired
	private ComplaintManagementService complaintManagementService;
	
	@Autowired
	MessageSource messageSource;
	
	/**
	 *保存客户投诉信息
	 * @param request
	 * @return
	 */
	@PostMapping("/saveComplaintMessage.do")
	public void saveComplaintMessage(HttpServletRequest request,HttpServletResponse response,MultipartFile[] file){
		MessageUntil<String> res = complaintManagementService.saveComplaintMessage(request,file);
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
	 * 查询客户自己投诉的list 需要有token
	 * @param request
	 * @return
	 */
	@PostMapping("/findCompaintList.do")
	public MessageUntil<HashMap<String, Object>> findComplaintMessage(HttpServletRequest request){
		return complaintManagementService.findComplaintMessage(request);
		
	}
	
	/**
	 * 查询客户自己投诉回复信息 需要有token
	 * @param request
	 * @return
	 */
	@PostMapping("/findComplaintMessageDet.do")
	public MessageUntil<HashMap<String, Object>> findComplaintMessageDet(HttpServletRequest request){
		return complaintManagementService.findComplaintDetMessage(request);
		
	}
	
	/**
	 * 保存客户自己的回复内容
	 * @param request
	 * @param files
	 * @return
	 */
	@PostMapping("/saveReply.do")
	public MessageUntil<String> saveReply(HttpServletRequest request,MultipartFile[] files){
		return complaintManagementService.saveReply(request, files);
		
	}
	
	/**
	 * 解决方案的同意与否
	 * @param request
	 * @return
	 */
	@PostMapping("/overCom.do")
	public MessageUntil<String> overCom(HttpServletRequest request){
		return complaintManagementService.overCom(request);
	}
	
	/**
	 * 获取用户的收藏的店铺
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value = "/getCollctShop", method = RequestMethod.GET)
	public JsonResult getCollctShop(@RequestParam Map<String, Object> params,Pager pager){
		logger.info("--->>>params:" + params);
		JsonResult result = new JsonResult();
		StringBuffer errMsg = new StringBuffer();
		if (!Validation.checkBlank(params, errMsg, messageSource, "tokenMsg","classify")) {
			throw new IllegalArgumentException(errMsg.toString());
		}
		Map<String,Object>token=complaintManagementService.getToken(params);
		if(token==null || token .isEmpty()){
			return result.put("无token","405");
		}
		
		//获取4S店的
		if("1".equals(params.get("classify").toString())){
			//赋值收藏分类为4S店的
			params.put("CollctCla", 1);
			//赋值评论分类为4S店的
			params.put("shopCommentType", 1);
			//赋值图片的类型是店铺的
			params.put("picturesaveType",4);
			//赋值展示图片为2
			params.put("slideshow",2);
			//服务项目类型为1的
			params.put("relevanceMold",1);
		}else if("2".equals(params.get("classify").toString())){//获取维保店铺
			//赋值收藏分类为4S店的
			params.put("CollctCla", 2);
			//赋值评论分类为维保店铺
			params.put("shopCommentType", 2);
			//赋值图片的类型是店铺的
			params.put("picturesaveType",4);
			//赋值展示图片2
			params.put("slideshow",2);
			//根据那个字段排序
			params.put("sort","distance");
		}
		//赋值类型为已经收藏的图片类型
		params.put("colMold", 0);
		params.put("cusId",token.get("userId"));
		pager.setPagerNecessary(params, pager);
		PageUtils pageUtils = new PageUtils();
		Integer ColletCout=complaintManagementService.getColletListCout(params);
		if(ColletCout==0){
//			return result.put("暂无收藏数据","405");
			return pageUtils.getJsonResult(new ArrayList<Map<String,Object>>(), params, ColletCout);
		}
		List<Map<String,Object>>Collet=complaintManagementService.getColletList(params);
		result = pageUtils.getJsonResult(Collet, params,ColletCout);
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/getClientEva", method = RequestMethod.GET)
	public JsonResult getClientEva(@RequestParam Map<String,Object>params,Pager pager) throws Exception{
		logger.info("params->>>>>>>>"+params);
		JsonResult result=new JsonResult();
		StringBuffer errMsg = new StringBuffer();
		if (!Validation.checkBlank(params, errMsg, messageSource, "tokenMsg")) {
			throw new IllegalArgumentException(errMsg.toString());
		}
		Map<String,Object>token=complaintManagementService.getToken(params);
		if(token==null || token.isEmpty()){
			return result.put("无token", "405");
		}
		params.put("cusId",token.get("userId"));
		pager.setPagerNecessary(params, pager);
		Integer clientListCout=complaintManagementService.getCustomerCont(params).size();
		PageUtils pageUtils = new PageUtils();
		if(clientListCout==0){
//			return result.put("无用户信息", "405");
			return pageUtils.getJsonResult(new ArrayList<Map<String,Object>>(), params, clientListCout);
		}
		List<Map<String,Object>>clientList=complaintManagementService.getClientEva(params);
		result=pageUtils.getJsonResult(clientList, params, clientListCout);
		return result;
	}
	

}
