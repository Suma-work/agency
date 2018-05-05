package com.sumainfo.agency.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface HomePageMsDao {
	
	 //首页获取登录人所需信息
     Map<String, Object> getUsMs(@Param("token")String token);
	
	//店员获取预约信息或接待或销售信息条数
     Integer assistantOrders(@Param("uid") int uid,@Param("startDate") String startDate,@Param("endDate") String endDate,@Param("status") String status);
	
	//店员获取销售金额信息
	Double assistantAmount(@Param("uid") int uid,@Param("startDate") String startDate,@Param("endDate") String endDate);
	
	//店长获取预约信息或接待或销售信息条数
	Integer storeManangerOrders(@Param("shopId") String shopId,@Param("startDate") String startDate,@Param("endDate") String endDate,@Param("status") String status);
	
	//店长获取销售金额信息
	Double storeManangerAmount(@Param("shopId") String shopId,@Param("startDate") String startDate,@Param("endDate") String endDate);
	
	//店长获取最热销车辆和销量
	List<Map<String, Object>> storeManangerHotVehicle(@Param("shopId") String shopId,@Param("startDate") String startDate,@Param("endDate") String endDate);
	
	//店长获取最佳销售和销售金额(首页主要获取销售人名称附带获取金额信息)
	List<Map<String, Object>> storeManangerBestSeller(@Param("shopId") String shopId,@Param("startDate") String startDate,@Param("endDate") String endDate);
	
	//店长获取最热预约车型和销售预约数量
	Map<String, Object> storeManangerOrderVehicle(@Param("shopId") String shopId,@Param("startDate") String startDate,@Param("endDate") String endDate);
	
	//店长获取预约信息或接待或销售信息条数及销售人员再做排名
	List<Map<String, Object>> storeManangerOrderMs(@Param("shopId") String shopId,@Param("startDate") String startDate,@Param("endDate") String endDate,@Param("status") String status);
	
	//区域经理获取预约信息或接待或销售信息条数
	Integer regionManangerOrders(@Param("dept")int dept,@Param("startDate") String startDate,@Param("endDate") String endDate,@Param("status") String status);
	
	//区域经理获取销售金额信息
	Double regionManangerAmount(@Param("dept")int dept,@Param("startDate") String startDate,@Param("endDate") String endDate);
	
	//区域经理获取最热销车辆和销量
	List<Map<String, Object>> regionManangerHotVehicle(@Param("dept")int dept,@Param("startDate") String startDate,@Param("endDate") String endDate);
	
	//区域经理获取最佳销售和销售金额(首页主要获取销售人名称附带获取金额信息)
	List<Map<String, Object>> regionManangerBestSeller(@Param("dept")int dept,@Param("startDate") String startDate,@Param("endDate") String endDate);
	
	//区域经理获取最热预约车型和销售预约数量
	Map<String, Object> regionManangerOrderVehicle(@Param("dept") int dept,@Param("startDate") String startDate,@Param("endDate") String endDate);
	
	//区域经理获取预约信息或接待或销售信息条数及销售人员再做排名
	List<Map<String, Object>> regionManangerOrderMs(@Param("dept")int dept,@Param("startDate") String startDate,@Param("endDate") String endDate,@Param("status") String status);
	
	//集团老总获取预约信息或接待或销售信息条数
	Integer bossOrders(@Param("dept")int dept,@Param("startDate") String startDate,@Param("endDate") String endDate,@Param("status") String status);
	
	//集团老总获取销售金额信息
	Double bossAmount(@Param("dept")int dept,@Param("startDate") String startDate,@Param("endDate") String endDate);
	
	//区域经理获取最佳销售和销售金额(首页主要获取销售人名称附带获取金额信息)
	List<Map<String, Object>> bossManangerBestSeller(@Param("dept")int dept,@Param("startDate") String startDate,@Param("endDate") String endDate);
	
	//集团老总获取预约信息或接待或销售信息条数及销售人员再做排名
	List<Map<String, Object>> bossOrderMs(@Param("dept")int dept,@Param("startDate") String startDate,@Param("endDate") String endDate,@Param("status") String status);
	
	//集团老总获取最热销车辆和销量
	List<Map<String, Object>> bossHotVehicle(@Param("dept")int dept,@Param("startDate") String startDate,@Param("endDate") String endDate);
	
	//集团老总获取最热预约车型和销售预约数量
	Map<String, Object> bossManangerOrderVehicle(@Param("dept") int dept,@Param("startDate") String startDate,@Param("endDate") String endDate);
	
	
	

	

}
