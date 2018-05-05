package com.sumainfo.agency.controller;

import java.io.Serializable;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sumainfo.agency.service.impl.BankServiceImpl;
import com.sumainfo.common.until.JsonResult;

@RestController
@RequestMapping("bank")
public class BankController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	Logger log=LoggerFactory.getLogger(BankController.class);
	
	@Autowired
	BankServiceImpl bankservice;
	
	/**
	 * 获取金融服务
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年5月3日
	 */
	@ResponseBody
	@RequestMapping(value="/getBankList",method=RequestMethod.GET)
	public JsonResult getBankList(@RequestParam Map<String,Object>params){
		JsonResult result=new JsonResult();
		return result.put(bankservice.getBankList(params));
	}

}
