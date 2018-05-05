package com.sumainfo.agency.dao;



import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sumainfo.common.entity.ComplaintManagement;
import com.sumainfo.common.entity.ComplaintMs;
import com.sumainfo.common.entity.complaintReply;



public interface ComplaintManagementDao {
	
	/**
	 * 保存客户投诉信息
	 * @param complaint
	 * @return
	 */
	int saveComplaintMessage(ComplaintManagement complaint);
	
	/**
	 * 查询客户自己的投诉列表信息
	 * @param cusId
	 * @param pageNum
	 * @return
	 */
	List<HashMap<String, Object>> findComplaintList(@Param("cusId") int cusId,@Param("pageNum")int pageNum);
	
	/**
	 * 获取客户自己投诉的某一个投诉信息
	 * @param cusId
	 * @param id
	 * @return
	 */
	ComplaintMs findComplaintMain(@Param("cusId") int cusId,@Param("id")int id);
	
	/**
	 * 查询客户自己投诉列表信息条数
	 * @param cusId
	 * @return
	 */
	Integer findComplaintListNum(@Param("cusId") int cusId);
	
	/**
	 * 查询客户自己投诉时上传的图片
	 * @param associationId
	 * @return
	 */
	List<String> findMyComplaintPic(@Param("associationId") String associationId);
	
	/**
	 * 查询投诉回复信息
	 * @param parentId
	 * @return
	 */
	List<HashMap<String, Object>> findMyReply(@Param("parentId") int parentId);
	
	/**
	 * 删除投诉信息
	 * @param results
	 */
	void deleteComplaintMessage(@Param("results")int results);
	
	int overCom(@Param("id")int id,@Param("isRecept")String isRecept,@Param("overTime")Date overTime);
	
	/**
	 * 保存客户回复的投诉信息
	 * @param reply
	 * @return
	 */
	int saveReply(complaintReply reply);
	
	/**
	 * 获取所有4S店铺
	 * @author:zhlu
	 * @date: 2018年2月27日
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getShopList(Map<String, Object> params);
	
	/**
	 * 获取所有维保的店铺
	 * @author:zhlu
	 * @date: 2018年2月27日
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getShopListEl(Map<String, Object> params);
	
	/**
	 * 根据token获取用户编号
	 * @author:zhlu
	 * @date: 2018年3月9日
	 * @param params
	 * @return
	 */
	Map<String,Object>getToken(Map<String,Object>params);
	
	/**
	 * 根据编号查询用户评价日期
	 * @author:zhlu
	 * @date: 2018年3月5日
	 * @param params
	 * @return
	 */
	List<Map<String,Object>> getCustomer(Map<String,Object> params);
	
	/**
	 * 根据用户编号获取用户的评价总数
	 * @author:zhlu
	 * @date: 2018年3月19日
	 * @param params
	 * @return
	 */
	List<Map<String,Object>> getCustomerCont(Map<String,Object>params);
	
	/**
	 * 获取评价信息
	 * @author:zhlu
	 * @date: 2018年3月5日
	 * @param params
	 * @return
	 */
	List<Map<String,Object>> getShopComment(Map<String,Object> params);
	
	/**
	 * 根据用户编号查询用户收藏的主键-》然后就可以根据主键查询信息
	 * @author:zhlu
	 * @date: 2018年3月2日
	 * @param params
	 * @return
	 */
	List<Map<String,Object>> getColletList(Map<String,Object>params);
	
	/**
	 * 根据用户编号查询用户收藏的主键-》然后就可以根据主键查询信息总数
	 * @author:zhlu
	 * @date: 2018年3月19日
	 * @param params
	 * @return
	 */
	Integer getColletListCout(Map<String,Object>params);
	
	/**
	 * 根据用户编号获取用户的评价
	 * @author:zhlu
	 * @date: 2018年3月5日
	 * @param params
	 * @return
	 */
	List<Map<String,Object>> getClientEva(Map<String,Object> params);
	
	/**
	 * 获取店铺信息
	 * @author:zhlu
	 * @date: 2018年3月19日
	 * @param params
	 * @return
	 */
	Map<String,Object>Shop(Map<String,Object> params);

	/**
	 * 获取商家回复
	 * @author:zhlu
	 * @date: 2018年3月19日
	 * @param params
	 * @return
	 */
	Map<String,Object>getShopRep(Map<String,Object>params);
}
