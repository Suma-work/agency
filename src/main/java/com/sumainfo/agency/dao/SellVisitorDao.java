package com.sumainfo.agency.dao;




import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sumainfo.common.entity.VisitMs;
import com.sumainfo.common.entity.VisitorMain;



public interface SellVisitorDao {
	
	/**
	 * 保存访客主表信息
	 * @param complaint
	 * @return
	 */
	int saveVisitorMain(VisitorMain visitorMain);
	
	/**
	 * 查询某客服接待下的接待人数
	 * @param serviceId
	 * @return
	 */
	List<VisitMs> findVisitorMainList(@Param("uid") int uid,
			                              @Param("type")String type,
			                              @Param("minPage")int minPage,
			                              @Param("maxPage")int maxPage);
	
	/**
	 * 查询条数
	 */
	int findVisitorNum(@Param("uid") int uid,@Param("type")String type);
	
	
	/**
	 * 更新数据
	 * @param id
	 * @return
	 */
	int updateVehicleOrders(@Param("id") int id);


}
