package com.sumainfo.agency.dao;

import com.sumainfo.common.entity.RecBuyCar;

public interface BuyVehicleDao {
	
	/**
	 * 保存客户推荐购车信息
	 * @param complaint
	 * @return
	 */
	int saveRecBuyCar(RecBuyCar complaint);
	
	

}
