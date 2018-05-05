package com.sumainfo.agency.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sumainfo.agency.service.ComplainreplyService;
import com.sumainfo.common.until.JsonResult;
import com.sumainfo.common.until.PageUtils;
import com.sumainfo.common.until.Pager;


@RestController
@RequestMapping("/ComplainreplyAction")
public class ComplainreplyAction {

	Logger log = LoggerFactory.getLogger(ComplainreplyAction.class);
	@Autowired
	private ComplainreplyService biz;


	
	/**
	 * 商家或者客户回复投诉
	 * @param params
	 */
	

	@RequestMapping(value="/saveComplainreply1",method=RequestMethod.POST)
	public JsonResult saveComplainreply(@RequestParam Map<String,Object> params) {
		log.info("params->>>>>"+params);
		JsonResult result=new JsonResult();
		
	
			
			Integer i=biz.saveComplainreply(params);
			if(i>0){
				
				result.putSuccess("操作成功！");
			}else{
				
				result.putFailed("操作失败");
			}
	
		return result;
	}
	
	
	/**
	 *按照店铺id和状态（1 OR 2）查询待受理或者已受理的投诉
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryComplaintablebydealScheduleAndshopId")
	public JsonResult queryComplaintablebydealScheduleAndshopId(@RequestParam Map<String, Object> params,Pager pager){
		
		JsonResult result=new JsonResult();
		pager.setPagerNecessary(params, pager);
		int  count=biz.queryComplaintableCount(params);
		PageUtils pageUtils = new PageUtils();
		List<Map<String,Object>>query=biz.queryComplaintablebydealScheduleAndshopId(params);
		result = pageUtils.getJsonResult(query, params, count);
		log.info("result----->>>>>"+result);
		return result;
	}
	
	/**
	 *按照店铺id和状态（3）已完成的投诉
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryComplaintablebydealScheduleAndshopId1")
	public JsonResult queryComplaintablebydealScheduleAndshopId1(@RequestParam Map<String, Object> params,Pager pager){
		
		JsonResult result=new JsonResult();
		pager.setPagerNecessary(params, pager);
		int  count=biz.queryComplaintableCount1(params);
		PageUtils pageUtils = new PageUtils();
		List<Map<String,Object>>query=biz.queryComplaintablebydealScheduleAndshopId1(params);
		result = pageUtils.getJsonResult(query, params, count);
		log.info("result----->>>>>"+result);
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/queryCmplainreplybyparentId")
	public JsonResult queryCmplainreplybyparentId(@RequestParam Map<String, Object> params) {
		log.info("params----->>>>>"+params);
		JsonResult result=new JsonResult();
		result.put(biz.queryCmplainreplybyparentId(params));
		return result;
	}

	
	/**
	    * 根据店铺id和状态id查询评价
	    * @param params
	    * @return
	    */
	@ResponseBody
	@RequestMapping(value="/queryShopcommentByShopIdAndStatus")
	public JsonResult queryShopcommentByShopIdAndStatus(@RequestParam Map<String, Object> params,Pager pager){
		
		JsonResult result=new JsonResult();
		pager.setPagerNecessary(params, pager);
		int  count=biz.countShopcomment(params);
		PageUtils pageUtils = new PageUtils();
		List<Map<String,Object>>query=biz.queryShopcommentByShopIdAndStatus(params);
		result = pageUtils.getJsonResult(query, params, count);
		log.info("result----->>>>>"+result);
		return result;
	}
	
	@RequestMapping(value="saveShopreply1",method=RequestMethod.POST)
	public JsonResult saveShopreply1(@RequestParam Map<String,Object> params) {
		log.info("result----->>>>>"+params);
		JsonResult result=new JsonResult();
		
		 Integer i =biz.saveShop_reply(params);
		
		 if(i>0){
				
				result.putSuccess("操作成功！");
			}else{
				
				result.putFailed("操作失败");
			}
		
		return result;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/queryShop_replyBycomid")
	public JsonResult queryShop_replyBycomid(@RequestParam Map<String,Object> params){
		log.info("params----->>>>>"+params);
		JsonResult result=new JsonResult();
		result.put(biz.queryShop_replyBycomid(params));
		return result;
	}
	
	
	
}
