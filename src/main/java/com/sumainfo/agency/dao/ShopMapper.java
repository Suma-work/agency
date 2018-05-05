package com.sumainfo.agency.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.sumainfo.common.entity.ShopComment;
import com.sumainfo.common.entity.ShopDetOrders;
import com.sumainfo.common.entity.ShopMainOrders;

@Mapper
public interface ShopMapper {

	/**
	 * 获取所有4S店铺
	 * @author:zhlu
	 * @date: 2018年2月27日
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getShopList(Map<String, Object> params);
	
	/**
	 * 店铺的总数
	 * @author:zhlu
	 * @date: 2018年3月19日
	 * @param params
	 * @return
	 */
	Integer getShopCount(Map<String,Object>params);
	
	/**
	 * 根据编号获取店铺类型
	 * @author:zhlu
	 * @date: 2018年3月6日
	 * @param params
	 * @return
	 */
	Map<String,Object>Shop(Map<String,Object> params);
	
	/**
	 * 获取4S店的热销车型
	 * @author:zhlu
	 * @date: 2018年3月12日
	 * @param params
	 * @return
	 */
	List<Map<String,Object>>getShopSlhcar(Map<String,Object>params);
	
	/**
	 * 获取热销车型的图片
	 * @author:zhlu
	 * @date: 2018年3月19日
	 * @param params
	 * @return
	 */
	Map<String,Object>getVehba(Map<String,Object>params);
	
	/**
	 * 获取热销车型的起价
	 * @author:zhlu
	 * @date: 2018年3月19日
	 * @param params
	 * @return
	 */
	Map<String,Object>getVeDet(Map<String,Object>params);
	
	/**
	 * 获取所有维保的店铺
	 * @author:zhlu
	 * @date: 2018年2月27日
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getShopListEl(Map<String, Object> params);
	
	/**
	 * 获取所有维保的店铺总数
	 * @author:zhlu
	 * @date: 2018年3月19日
	 * @param params
	 * @return
	 */
	Integer getShopListElCout(Map<String,Object>params);
	
	/**
	 * 根据编号获取4S店铺
	 * @author:zhlu
	 * @date: 2018年2月27日
	 * @param params
	 * @return
	 */
	Map<String, Object> getShop(Map<String, Object> params);
	
	/**
	 * 根据编号获取维保店
	 * @author:zhlu
	 * @date: 2018年2月27日
	 * @param params
	 * @return
	 */
	Map<String, Object> getShopEl(Map<String, Object> params);
	
	/**
	 * 获取店铺的第一条图片
	 * @author:zhlu
	 * @date: 2018年2月27日
	 * @param params
	 * @return
	 */
	Map<String, Object> getPicturesave(Map<String, Object> params);
	
	
	/**
	 * 获取店铺的总评价的平均分
	 * @author:zhlu
	 * @date: 2018年2月27日
	 * @param params
	 * @return
	 */
	Map<String, Object> getSumShopcomment(Map<String, Object> params);
	
	
	/**
	 * 获取店铺的所以图片 轮播图和店内实景
	 * @author:zhlu
	 * @date: 2018年2月27日
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getPicturesaveList(Map<String, Object> params);
	
	/**
	 * 获取店铺用户评价的平均分
	 * @author:zhlu
	 * @date: 2018年2月27日
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getShopcomment(Map<String, Object> params);
	
	/**
	 * 获取根据编号店铺的评价的总数
	 * @author:zhlu
	 * @date: 2018年3月19日
	 * @param params
	 * @return
	 */
	Integer shopCommentListCout(Map<String,Object>params);
	
	/**
	 * 根据店铺编号获取店铺可提供的服务
	 * @author:zhlu
	 * @date: 2018年2月28日
	 * @param params
	 * @return
	 */
	List<Map<String,Object>> getServeListCount(Map<String,Object>params);
	
	/**
	 * 根据店铺编号获取店铺活动
	 * @author:zhlu
	 * @date: 2018年3月1日
	 * @param params
	 * @return
	 */
	List<Map<String,Object>> getActi(Map<String,Object> params);
	
	/**
	 * 获取店铺评价的商家回复
	 * @author:zhlu
	 * @date: 2018年3月19日
	 * @param params
	 * @return
	 */
	Map<String,Object>getShopRep(Map<String,Object>params);
	
	/**
	 * 保存店铺评论
	 * @param comments
	 * @return
	 */
	int saveShopComments(ShopComment comments);
	
	/**
	 * 根据shopId查询店铺名称
	 * @param shopId
	 * @return
	 */
	String selectNameByShopId(@Param("shopId") String shopId);
	
	/**
	 * 根据客户id查询自己的维保店订单信息及附表条数
	 * @param cusId
	 * @return
	 */
	List<HashMap<String,Object>> findRepOrder(@Param("cusId")int cusId,@Param("status")int status);
	
	/**
	 * 根据客户id查询自己的维保店订单信息的总条数 分页处理
	 * @param cusId
	 * @param status
	 * @return
	 */
	Integer findRepOrderMainNum (@Param("cusId")int cusId,@Param("status")int status);
	
	/**
	 * 查询订单附表条数
	 * @param orderNo
	 * @return
	 */
	Integer findRepOrderDetNum(@Param("orderNo")String orderNo);
	
	/**
	 * update订单状态
	 * @param orderNo
	 * @return
	 */
	Integer updateRepOrderStatus(@Param("orderNo")String orderNo,@Param("status")int status);
	
	/**
	 * 获取订单主表的详情
	 * @param orderNo
	 * @return
	 */
	HashMap<String,Object> findMainOrderDet(@Param("orderNo")String orderNo);
	
	/**
	 * 获取订单附表详情
	 * @param orderNo
	 * @return
	 */
	List<HashMap<String,Object>> findDetOrderDet(@Param("orderNo")String orderNo);
	
	/**
	 * 保存店铺预约的主表信息
	 * @param order
	 * @return
	 */
	int saveShopMainOrder(ShopMainOrders order);
	
	/**
	 * 保存预约明细
	 * @param arrayList
	 * @return
	 */
	int saveShopDetOrder(ArrayList<ShopDetOrders> arrayList);
	
	int orderServiceAgain(@Param("orderNo")String orderNo,
							@Param("cusName")String cusName,
							@Param("cusPhone")String cusPhone,
							@Param("orderDate")String orderDate,
							@Param("orderTime")String orderTime,
							@Param("createTime")Date createTime
							);
	
	/**
	 * 获取收藏的店铺
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月28日
	 */
	Map<String,Object>getCollectMap(Map<String,Object>params);
	
	/**
	 * 判断token
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author zhlu
	* @date 2018年4月28日
	 */
	Map<String,Object>getToken(Map<String,Object>params);
}
