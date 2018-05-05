package com.sumainfo.agency.dao;


import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sumainfo.common.entity.OrderDetMs;
import com.sumainfo.common.entity.OrderMs;
import com.sumainfo.common.entity.OrderSkipDetMs;


public interface CommercialTenantVisitorMsDao {
	
	//获取预约信息
	List<OrderMs> getNotAgreeOrder(@Param("param")String param,
			                       @Param("uid")int uid,
			                       @Param("status")int status);
	
	//获取预约信息(今日，未来)
	List<OrderMs> getNotOrder(@Param("uid")int uid,@Param("status")int status);
	
	//根据时间获取(今日，未来)预约或接待信息
	List<OrderMs> getCheckOrder(@Param("orderDate")Date orderDate,
			                    @Param("uid")int uid,
			                    @Param("status")String status,
			                    @Param("minPage")int minPage,
			                    @Param("maxPage")int maxPage);
	//获取上面sql信息总数
	int getCheckOrderNum(@Param("orderDate")Date orderDate,@Param("uid")int uid, @Param("status")String status);
	
	//获取预约信息（跳转到预约修改信息的查询）
	OrderDetMs getDetOrder(@Param("id")int id);
	
	//跳转到销售界面
	OrderSkipDetMs getSkipDetOrder(@Param("id")int id);
	
	//获取某手机号的上行信息的时间
	List<String>  getCheckMs(@Param("mobile")String mobile);
	
	//更新订单状态
	int updateOrderStatus(@Param("id")int id,@Param("status")String status);
	
	//获取店员信息
	List<HashMap<String, Object>> getComUserMs(@Param("shopid")String shopid);
	
	int updateOrderMs(@Param("id")int id,
			          @Param("orderDate")String orderDate,
			          @Param("orderTime")String orderTime,
			          @Param("uid")int uid);
			         
	
	

}
