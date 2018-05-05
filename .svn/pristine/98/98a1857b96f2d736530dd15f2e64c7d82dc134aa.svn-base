package com.sumainfo.agency.controller;

import java.util.List;
import java.util.Map;

import lombok.extern.log4j.Log4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sumainfo.agency.service.QueryCountService;
import com.sumainfo.common.until.JsonResult;
import com.sumainfo.common.until.PageUtils;
import com.sumainfo.common.until.Pager;

@RestController
@RequestMapping("/QueryCountAction1")
public class QueryCountAction {

	Logger log = LoggerFactory.getLogger(QueryCountAction.class);
	
	@Autowired
	private QueryCountService biz;
	
	@ResponseBody
	@RequestMapping(value="/QueryCount1",method=RequestMethod.GET)
	public JsonResult QueryCount(@RequestParam Map<String, Object> params ,Pager pager) {
		// TODO Auto-generated method stub
		log.info("params-------->"+params);
		return biz.QueryCount(params, pager);
		
	}


	@ResponseBody
	@RequestMapping(value="/QueryCount2",method=RequestMethod.GET)
	public JsonResult QueryCount2(@RequestParam Map<String, Object> params ,Pager pager) {
		// TODO Auto-generated method stub
		log.info("params-------->"+params);
		return biz.QueryCount1(params, pager);
		
	}
	
	
}
