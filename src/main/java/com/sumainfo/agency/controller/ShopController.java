package com.sumainfo.agency.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.sumainfo.agency.service.impl.ShopService;
import com.sumainfo.common.until.JsonResult;
import com.sumainfo.common.until.MessageUntil;
import com.sumainfo.common.until.PageUtils;
import com.sumainfo.common.until.Pager;
import com.sumainfo.common.until.Validation;

@RestController
@RequestMapping("shop")//主索引
public class ShopController implements Serializable{
	
	private static final long serialVersionUID = 1L;
	Logger log = LoggerFactory.getLogger(ShopController.class);
	
	@Autowired
	ShopService shopService;
	@Autowired
	MessageSource messageSource;
	/**
	 *	获取所有的店铺
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value = "/getShopList", method = RequestMethod.GET)
	public JsonResult getShopList(@RequestParam Map<String, Object> params,Pager pager) throws Exception {
		//赋值评论分类为4S店的
//		params.put("shopCommentType", 1);
		//赋值图片的类型是店铺的
		params.put("picturesaveType",4);
		//赋值展示图片为2
		params.put("slideshow",2);
		//服务项目类型为1的
//		params.put("relevanceMold",1);
		//评论分类为4S店的,服务项目类型为1的
		params.put("typeShop", 1);
		log.info("---------------------------"+pager);
		JsonResult jsonResult=new JsonResult();
		Integer cout=shopService.shopListCout(params);
		pager.setPagerNecessary(params, pager);
		PageUtils pageUtils = new PageUtils();
		if(0==cout){
//			return jsonResult.put("暂无店铺信息", "405");
			return pageUtils.getJsonResult(new ArrayList<Map<String,Object>>(), params,0);
		}
		List<Map<String,Object>>shopList=shopService.getShop(params);
		jsonResult = pageUtils.getJsonResult(shopList, params, cout);
		return jsonResult;
	}
	
	/**
	 *	根据编号和经纬度获取4s店信息
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value = "/getShopAn", method = RequestMethod.GET)
	public JsonResult getShopAn(@RequestParam Map<String, Object> params){
		log.info("--->>>params:" + params);
		JsonResult result = new JsonResult();
		StringBuffer errMsg = new StringBuffer();
		if (!Validation.checkBlank(params, errMsg, messageSource, "shopId" ,"lon", "lat")) {
			throw new IllegalArgumentException(errMsg.toString());
		}
		//赋值评论分类为4s店
//		params.put("shopCommentType",1);
		//赋值图片的类型4s店
		params.put("picturesaveType",4);
		params.put("typeShop", 1);
		
		//不获取展示图片
		final List ids = new ArrayList();
        ids.add(0);
        ids.add(1);
		params.put("ids", ids);
		//获取可服务的项目
		final List parentlist = new ArrayList();
		parentlist.add(1);
		parentlist.add(2);
		parentlist.add(3);
		parentlist.add(4);
		parentlist.add(5);
		params.put("parentlist", parentlist);
		Map<String,Object>shopAn=shopService.shopAnMap(params);
		if(shopAn==null || shopAn.isEmpty()){
//			return result.put("暂无数据", "405");
			return result.put(new HashMap<String,Object>());
		}
		Map<String,Object>getShopAn=shopService.getShopAn(params);
		if(getShopAn==null || getShopAn.size()<1){
			result.putLogin("token失效！");
		}else{
			result.put(getShopAn);
		}
		return result;
	}
	
	/**
	 * 获取根据编号店铺的评价
	 * @author:zhlu
	 * @date: 2018年3月6日
	 * @param params
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getShopComment",method=RequestMethod.GET)
	public JsonResult getShopComment(@RequestParam Map<String,Object>params,Pager pager){
		log.info("params->>>>>>>>"+params);
		StringBuffer errMsg = new StringBuffer();
		if (!Validation.checkBlank(params, errMsg, messageSource, "shopId")) {
			throw new IllegalArgumentException(errMsg.toString());
		}
		Map<String,Object>shop=shopService.shop(params);
		pager.setPagerNecessary(params, pager);
		PageUtils pageUtils = new PageUtils();
		if(shop!=null && !StringUtils.isEmpty(shop)){
			if("1".equals(shop.get("classify").toString())){
				params.put("shopCommentType",1);
			}else{
				params.put("shopCommentType",2);
			}
		}else{
//			throw new IllegalArgumentException("无店铺信息");
			return pageUtils.getJsonResult(new ArrayList<Map<String,Object>>(), params, 0);
		}
//		params.put("shopCommentType", 1);
		Integer cout=shopService.shopCommentListCout(params);
		List<Map<String,Object>>shopList=shopService.shopCommentList(params);
		JsonResult jsonResult = pageUtils.getJsonResult(shopList, params,cout);
		return jsonResult;
	}
	
	/**
	 * 获取维保店铺列表
	 * @author:zhlu
	 * @date: 2018年3月1日
	 * @param params
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getShopEl",method=RequestMethod.GET)
	public JsonResult getShopEl(@RequestParam Map<String, Object>params,Pager pager){
		log.info("->>>>>params:"+params);
		StringBuffer errMsg = new StringBuffer();
		JsonResult jsonResult=new JsonResult(); 
		if (!Validation.checkBlank(params, errMsg, messageSource, "order" ,"lon", "lat")) {
			throw new IllegalArgumentException(errMsg.toString());
		}
		//赋值评论分类为维保店铺
//		params.put("shopCommentType",2);
		//赋值图片的类型是店铺的
		params.put("picturesaveType",5);
		//赋值展示图片2
		params.put("slideshow",2);
		//根据那个字段排序  1距离  2评价分
		if("1".equals(params.get("order").toString())){
			params.put("sort","distance");
		}else{
			params.put("sort","rate");
		}
		
		//赋值评论分类为维保店铺,车的类型是维保的
		params.put("typeShop",2);
		pager.setPagerNecessary(params, pager);
		Integer shopElCout=shopService.shoplist(params);
		PageUtils pageUtils = new PageUtils();
		if(0==shopElCout){
//			return jsonResult.put("无店铺信息", "405");
			return pageUtils.getJsonResult(new ArrayList<Map<String,Object>>(), params, shopElCout);
		}
		List<Map<String,Object>>shopList=shopService.getShopEl(params);
		jsonResult = pageUtils.getJsonResult(shopList, params, shopElCout);
		return jsonResult;
	}
	
	/**
	 *	根据编号和经纬度获取維保店信息
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value = "/getShopAnEl", method = RequestMethod.GET)
	public JsonResult getShopAnEl(@RequestParam Map<String, Object> params){
		log.info("--->>>params:" + params);
		JsonResult result = new JsonResult();
		StringBuffer errMsg = new StringBuffer();
		if (!Validation.checkBlank(params, errMsg, messageSource, "shopId" ,"lon", "lat")) {
			throw new IllegalArgumentException(errMsg.toString());
		}
		//赋值评论分类为维保店
		params.put("shopCommentType",2);
		//赋值图片的类型是维保店
		params.put("picturesaveType",5);
		
		//不获取展示图片
		final List ids = new ArrayList();
        ids.add(0);
        ids.add(1);
		params.put("ids", ids);
		
		//获取可服务的项目
		final List parentlist = new ArrayList();
		parentlist.add(1);
		parentlist.add(2);
		parentlist.add(3);
		parentlist.add(4);
		parentlist.add(5);
		params.put("parentlist", parentlist);
		
		Map<String,Object>shopMap=shopService.shopMap(params);
		if(shopMap ==null || shopMap.isEmpty()){
//			return result.put("暂无店铺信息", "405");
			return result.put(new HashMap<String,Object>());
		}
		
		Map<String,Object>getShopAnEl=shopService.getShopAnEl(params);
		if(getShopAnEl==null || getShopAnEl.size()<1){
			result.putLogin("token失效！");
		}else{
			result.put(getShopAnEl);
		}
		return result;
	}
	
	/**
	 * 获取店铺服务的预约信息
	 * @param request
	 * @return
	 */
	@PostMapping("/getOrderShopService.do")
	public MessageUntil<Map<String,Object>> getOrderShopService(HttpServletRequest request){
		return shopService.getOrderShopService(request);
	}
	
	/**
	 * 取消订单
	 * @param request
	 * @return
	 */
	@PostMapping("/concleOrder.do")
	public MessageUntil<String> updateOrderStatus(HttpServletRequest request){
		return shopService.updateOrderStatus(request);
	}
	
	/**
	 * 获取预约店铺详情
	 * @param request
	 * @return
	 */
	@PostMapping("/getOrderDet.do")
	public MessageUntil<HashMap<String, Object>> getOrderDet(HttpServletRequest request){
		return shopService.getOrderDet(request);
	}
	
	/**
	 * 保存店铺预约信息
	 * @param request
	 * @return
	 */
	@PostMapping("/saveServiceOrder.do")
	public MessageUntil<String> saveServiceOrder(HttpServletRequest request){
		return shopService.saveServiceOrder(request);
	}
	
	/**
	 * 再次预约
	 * @param request
	 * @return
	 */
	@PostMapping("/orderServiceAgain.do")
	public MessageUntil<String> orderServiceAgain(HttpServletRequest request){
		return shopService.orderServiceAgain(request);
	}
	
}
