package com.sumainfo.agency.controller;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sumainfo.common.until.JsonResult;
import com.sumainfo.agency.service.CarMessageService;
import com.sumainfo.common.entity.FcvTable;
import com.sumainfo.common.entity.SeclvlTable;
import com.sumainfo.common.until.ObjectToJsonUntil;
import com.sumainfo.common.until.MessageUntil;
import com.sumainfo.common.until.ConvertDateTime;
/**
 * 汽车标签信息查询
 * @author Administrator
 *
 */
@RestController
public class CarMessageController {
	Logger logger = LoggerFactory.getLogger(CarMessageController.class);
	@Autowired
	private CarMessageService carMessageService;
	
	/**
	 * 获取车辆一级标签信息
	 * @param request
	 * @return
	 */
	@GetMapping("/getFirstLevelMessage.do")
	public MessageUntil<List<FcvTable>> getFirstLevelMessage(HttpServletRequest request){
		MessageUntil<List<FcvTable>> res = carMessageService.getFirstLevelMessage(request);
		return res;

	}
	
	/**
	 * 获取车辆二级标签信息
	 * @param request
	 * @param response
	 */
	@PostMapping("/selectAllById")
	public void selectAllById(HttpServletRequest request,HttpServletResponse response){
		MessageUntil<List<SeclvlTable>> res = carMessageService.selectAllById(request);
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
	
	@GetMapping("/upload.do")
	public void upload(HttpServletRequest request,HttpServletResponse response){
		MessageUntil<String> res = carMessageService.upload(request);
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
	 *	测试
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value = "/getTest", method = RequestMethod.GET)
	public JsonResult updateWrkLeave(@RequestParam Map<String, Object> params) throws Exception {
		logger.info("--->>>params:" + params);
		JsonResult jr = new JsonResult();
		try {
			System.out.println(ConvertDateTime.getCurrentTime());
			jr.put(ConvertDateTime.getCurrentTime());
		} catch (Exception e) {
			jr.putFailed("操作失败，请联系管理员。");
		}
		return jr;
	}

}
