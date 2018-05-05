package com.sumainfo.agency.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sumainfo.common.entity.PurcaseinFormation;


/**
 * 
 * @author Mfx
 *
 */
public interface PurchaseinFormationDao {

	
	/**
	 * 保存购买信息
	 * @param params
	 */
	Integer savePurchaseinFormation(Map<String,Object>params);
	
	/**
	 * 修改状态
	 * @param params
	 */
	//Integer updateVehicleorders(Map<String,Object>params);
	

	/**
	 * 修改状态
	 * @param params
	 */
	void updateVehicleorders1(Integer pyyid);
	
	
	/**
	 * 根据访客姓名或者手机方式模糊查询
	 * @param params
	 * @return
	 */
	List<Map<String,Object>> queryPurchaseinFormationBypname(Map<String,Object>params);

	
	/**
	 *按照状态和店铺id查询数据的总数
	 * @author:Ma
	 * @date: 2018年4月12日
	 * 
	 * @return
	 */
	Integer getCount(Map<String,Object>params);
	

	/**
	 * 查询待审核的数据
	 * @return
	 */
	
	List<Map<String,Object>> queryPurchaseinFormationBypstatus(Map<String,Object>params);
	
	/**
	 * 店长驳回审核或者通过审核
	 * @param params
	 */
	void updatePurchaseinFormation(@Param("pauditor")String pauditor,@Param("previewthenote")String previewthenote,@Param("pstatus")Integer pstatus,@Param("pid")Integer pid);
	
	

	/**
	 * 驳回之后修改重新发起审核信息
	 * @param params
	 */
	Integer updatePurchaseinFormationbyall(@Param("psjxh")String psjxh,@Param("pmoney")String pmoney,@Param("premark")String premark,@Param("pid")Integer pid);
	
	/**
	 * 审核成功后修改状态
	 * @param pyyid
	 */
	void updateVehiclesetStatus(@Param("pyyid")Integer pyyid);
	
	
	
	/**
	 * 根据店铺id查询店铺否为四S点或者是二手店
	 * @param shopid
	 * @return
	 */
	Integer querySHOP(@Param("shopid")String shopid);
	

	/**
	 * 根据店铺id查询4S店车型
	 * @param shopid
	 * @return
	 */
	List<Map<String,Object>> queryVehiclemain(@Param("shopid")String shopid);
	
	
	
	/**
	 * 根据店铺id查询二手店
	 * @param shopid
	 * @return
	 */
	List<Map<String,Object>> queryVsedvehicledet(@Param("shopid")String shopid);
	
	
	
	
	
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
	
	
	int queryuserid(Map<String, Object> params);
	
	
	
	
	
	
	
	
	
	
	
	/**
	 *按照状态和店铺id查询数据的总数1
	 * @author:Ma
	 * @date: 2018年4月12日
	 * 
	 * @return
	 */
	Integer getCount1(Map<String,Object>params);
	

	/**
	 * 查询待审核的数据1
	 * @return
	 */
	
	List<Map<String,Object>> queryPurchaseinFormationBypstatus1(Map<String,Object>params);
	
	
	
	
	
	
}
