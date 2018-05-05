package com.sumainfo.agency.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sumainfo.common.entity.CommentEntity;
import com.sumainfo.common.entity.ShopComment;

public interface MaintenanceShopDao {

	
	/**
	 * 保存店铺评论信息
	 * @param comment
	 * @return
	 */
	int saveShopComment(ShopComment comment);
	
	
	/**
	 * 查询店铺所有评论信息
	 * @param shopId
	 * @return
	 */
	List<CommentEntity> findShopComment(@Param("shopId") int shopId,@Param("classify") String classify,@Param("sort") String sort);
	
	/**
	 * 查询店铺评论的总条数及评分
	 * @param shopId
	 * @return
	 */
	HashMap<String,String> commentResult(@Param("shopId") int shopId,@Param("classify") String classify);
	
	/**
	 * 通过服务名查找主键id
	 * @param parent
	 * @param svcnm
	 * @return
	 */
	Integer findKeyIdByServiceName(@Param("parent") int parent,@Param("svcnm") String svcnm);
	
	/**
	 * 获取商店的服务名称
	 * @param shopId
	 * @return
	 */
	Map<String, String> findServeceByShopId(@Param("shopId") String shopId);
	
	/**
	 * 通过父级id和店铺id查询服务信息
	 * @param parent
	 * @return
	 */
	List<HashMap<String, Object>> findServiceByParentId(@Param("parent") int parent,@Param("shopId") String shopId);
	
	/**
	 * 查询条数
	 * @param parent
	 * @return
	 */
	Integer findServiceNumsByParentId(@Param("parent") int parent,@Param("shopId") String shopId);
	
}
