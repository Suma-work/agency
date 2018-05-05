package com.sumainfo.agency.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sumainfo.common.entity.PurcaseinFormation;

public interface PurchaseinFormationBiz {

	
	/**
	 * 保存购买信息
	 * @param formation
	 */
	boolean savePurchaseinFormation(Map<String,Object>params);
	
	
	
	/**
	 * 根据访客姓名或者手机号模糊查询
	 * @param params
	 * @return
	 */
	List<Map<String,Object>> queryPurchaseinFormationByparams(Map<String,Object>params);
	

	

	/**
	 *按照状态和店铺id查询数据的总数
	 * @author:Ma
	 * @date: 2018年4月12日
	 * 
	 * @return
	 */
	Integer getCount(Map<String,Object>params);
	
	
	
	/**
	 * 按照状态和店铺id查询数据
	 * @return
	 */
	
	List<Map<String,Object>> queryPurchaseinFormationBypstatus(Map<String,Object>params);
	

	/**
	 * 店长驳回审核或者通过审核
	 * @param params
	 */
	boolean updatePurchaseinFormation(String pauditor,String previewthenote,Integer pstatus,Integer pid,Integer pyyid);
	
	

	/**
	 * 驳回之后修改重新发起审核信息
	 * @param params
	 */
	boolean updatePurchaseinFormationbyall(String psjxh,String pmoney,String premark,Integer pid);
	
	
	/**
	 * 查询待审核的数据
	 * @return
	 */
	
	List<Map<String,Object>> queryObject(String shopid);
	
	
	

	/**
	 *按照总数
	 * @author:Ma
	 * @date: 2018年4月13日
	 * 
	 * @return
	 */
	Integer count(Map<String,Object>params);
	
	
	/**
	 * 查询根据访客姓名或者手机方式模糊查询
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> querybyViewerNameorViewerPhone(Map<String, Object> params);
	
	
	
	/**
	 *按照状态和店铺id查询数据的总数1
	 * @author:Ma
	 * @date: 2018年4月12日
	 * 
	 * @return
	 */
	Integer getCount1(Map<String,Object>params);
	
	
	
	/**
	 * 按照状态和店铺id查询数据
	 * @return
	 */
	
	List<Map<String,Object>> queryPurchaseinFormationBypstatus1(Map<String,Object>params);
	
	
	
}
