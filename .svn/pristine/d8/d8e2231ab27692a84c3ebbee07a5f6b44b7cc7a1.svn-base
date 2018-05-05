package com.sumainfo.agency.service;

import java.util.List;
import java.util.Map;

public interface ComplainreplyService {


	/**
	 * 商家或者客户回复投诉
	 * @param params
	 */
   public Integer  saveComplainreply(Map<String, Object> params);



   /**
    * 按时店铺id和状态查询投诉表
    * @param params
    * @return
    */
   public List<Map<String,Object>> queryComplaintablebydealScheduleAndshopId(Map<String, Object> params);

   /**
    * 查询总记录数
    * @param params
    * @return
    */
   Integer queryComplaintableCount(Map<String, Object> params);
   
   /**
    * 按时店铺id和状态查询投诉表
    * @param params
    * @return
    */
   public List<Map<String,Object>> queryComplaintablebydealScheduleAndshopId1(Map<String, Object> params);

   /**
    * 查询总记录数
    * @param params
    * @return
    */
   Integer queryComplaintableCount1(Map<String, Object> params);
   
   
   
   /**
    * 按投诉表id查询回复表
    * @param params
    * @return
    */
   List<Map<String, Object>> queryCmplainreplybyparentId(Map<String, Object> params);
   
   
   /**
    * 根据店铺id和状态id查询评价
    * @param params
    * @return
    */
   List<Map<String, Object>> queryShopcommentByShopIdAndStatus(Map<String, Object> params);
   /**
    * 根据店铺id和状态id查询评价总记录数
    * @param params
    * @return
    */
   Integer countShopcomment(Map<String, Object> params);
   

   /**
    * 回复评论
    * @param params
    * @return
    */
   Integer saveShop_reply(Map<String, Object> params);
   
   /**
    * 按照评论id 查询回复
    * @param params
    * @return
    */
   List<Map<String, Object>> queryShop_replyBycomid(Map<String, Object> params);
}
