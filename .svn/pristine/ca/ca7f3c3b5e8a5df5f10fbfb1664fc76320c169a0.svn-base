package com.sumainfo.agency.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sumainfo.agency.service.PurchaseinFormationBiz;
import com.sumainfo.common.entity.PurcaseinFormation;
import com.sumainfo.common.until.JsonResult;
import com.sumainfo.common.until.PageUtils;
import com.sumainfo.common.until.Pager;

@RestController
@RequestMapping("/PurchaseinFormationAction")
public class PurchaseinFormationAction {


	Logger log = LoggerFactory.getLogger(PurchaseinFormationAction.class);
	@Autowired
	private PurchaseinFormationBiz biz;
	
	/**
	 * 保存购买信息
	 * @param params
	 * 
	 */
	@RequestMapping("/savePurchaseinFormation")
	public JsonResult savePurchaseinFormation(@RequestParam Map<String,Object> params) {
		log.info("params->>>>>"+params);
		JsonResult result=new JsonResult();
		boolean re=biz.savePurchaseinFormation(params);
		if(re){
			result.putSuccess("操作成功！");
		}else{
			result.putFailed("操作失败");
		}
		return result;
	}
	
	/**
	 * 根据访客姓名或者手机方式模糊查询
	 * @param params
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryPurchaseinFormationByparams")
	public JsonResult queryPurchaseinFormationByparams(@RequestParam Map<String,Object> params){
		log.info("params----->>>>>"+params);
		JsonResult result=new JsonResult();
		result.put(biz.queryPurchaseinFormationByparams(params));
		return result;
	}
	
	
	/**
	 * 查询待审核的数据
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryPurchaseinFormationBypstatus",method=RequestMethod.POST)
	public JsonResult queryPurchaseinFormationBypstatus(@RequestParam Map<String, Object> params,Pager pager){
		
		JsonResult result=new JsonResult();
		pager.setPagerNecessary(params, pager);
		int  count=biz.getCount(params);
		PageUtils pageUtils = new PageUtils();
		List<Map<String,Object>>query=biz.queryPurchaseinFormationBypstatus(params);
		result = pageUtils.getJsonResult(query, params, count);
		log.info("result----->>>>>"+result);
		return result;
	}
	
	
	
	/**
	 * 员工查询状态的的数据
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryPurchaseinFormationBypstatusandprepared",method=RequestMethod.POST)
	public JsonResult queryPurchaseinFormationBypstatusandprepared(@RequestParam Map<String, Object> params,Pager pager){
		
		JsonResult result=new JsonResult();
		pager.setPagerNecessary(params, pager);
		int  count=biz.getCount1(params);
		PageUtils pageUtils = new PageUtils();
		List<Map<String,Object>>query=biz.queryPurchaseinFormationBypstatus1(params);
		result = pageUtils.getJsonResult(query, params, count);
		log.info("result----->>>>>"+result);
		return result;
	}
	
	/**
	 * 查询待审核的数据
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/querybyViewerNameorViewerPhone",method=RequestMethod.POST)
	public JsonResult querybyViewerNameorViewerPhone(@RequestParam Map<String, Object> params,Pager pager){
		JsonResult result=new JsonResult();
		pager.setPagerNecessary(params, pager);
		int  count=biz.count(params);
		PageUtils pageUtils = new PageUtils();
		List<Map<String,Object>>query=biz.querybyViewerNameorViewerPhone(params);
		result = pageUtils.getJsonResult(query, params, count);
		return result;
	}
	
	/**
	 * 店长驳回审核或者通过审核
	 * 
	 */
	@RequestMapping("/updatePurchaseinFormation")
	@ResponseBody
	private JsonResult updatePurchaseinFormation(String pauditor,
			String previewthenote, Integer pstatus, Integer pid,Integer pyyid) {
		//log.info("params->>>>>"+params);
		JsonResult result=new JsonResult();
		boolean re=biz.updatePurchaseinFormation(pauditor, previewthenote, pstatus, pid,pyyid);
		if(re){
			result.putSuccess("操作成功！");
		}else{
			result.putFailed("操作失败");
		}
		return result;
	}
	
	
	/**
	 * 驳回之后修改重新发起审核信息
	 * 
	 */
	@RequestMapping("/updatePurchaseinFormationbyall")
	@ResponseBody
	private JsonResult updatePurchaseinFormationbyall(String psjxh, String pmoney,
			String premark, Integer pid) {
		log.info("psjxh->>>>>"+psjxh);
		JsonResult result=new JsonResult();
		boolean re=biz.updatePurchaseinFormationbyall(psjxh, pmoney, premark, pid);
		if(re){
			result.putSuccess("操作成功！");
		}else{
			result.putFailed("操作失败");
		}
		return result;
	}
	
	
	/**
	 * 根据店铺id查询二手店的车或者4S点的车
	 * @param shopid
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryObject")
	public JsonResult queryObject(String shopid){
		log.info("params----->>>>>"+shopid);
		JsonResult result=new JsonResult();
		result.put(biz.queryObject(shopid));
		return result;
	}
	
	
}
