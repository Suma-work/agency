package com.sumainfo.agency.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sumainfo.common.entity.ContactUserMs;
import com.sumainfo.common.entity.SmsReturn;
import com.sumainfo.common.entity.VehicleOrders;

public interface VehicleOrdersDao {

	
	/**
	 * 查询车辆预约信息
	 * @param userId
	 * @return
	 */
	List<VehicleOrders> findVehicleOrderMessage(@Param("cusId")int cusId,@Param("status")String status);
	
	/**
	 * 保存订单信息
	 * @param vehicleOrder
	 * @return
	 */
	int saveVehicleOrder(VehicleOrders vehicleOrder);
	
	/**
	 * 更新订单数据
	 * @param viewerPhone
	 * @return
	 */
	int updateVehicleOrder(@Param("viewerPhone")String viewerPhone);
	
	/**
	 * 保存上行信息
	 * @param smsList
	 * @return
	 */
	int saveSmsBackMessage(List<SmsReturn> smsList);
	
	/**
	 * 查询约车信息
	 * @param viewerPhone
	 * @param status
	 * @param states
	 * @return
	 */
	List<HashMap<String, Object>> findOrderVehicles(@Param("cusId")int cusId,
			                                        @Param("viewerPhone")String viewerPhone,
			                                        @Param("status")String status,
			                                        @Param("states")String states);
	
	/**
	 * 查询约车信息条数
	 * @param viewerPhone
	 * @param status
	 * @param states
	 * @return
	 */
	Integer findOrderVehiclesNum(@Param("cusId")int cusId,
								 @Param("viewerPhone")String viewerPhone,
					             @Param("status")String status,
					             @Param("states")String states);
	
	/**
	 * 获取联系人信息
	 * @param shopId
	 * @return
	 */
	List<ContactUserMs> findContactMs(@Param("shopid")String shopid);
}
