package com.sumainfo.agency.dao;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.sumainfo.common.entity.AreaTable;


public interface PalaceNameDao {
	
	/**
	 * 保存地区信息
	 * @param list
	 * @return
	 */
	int saveAreaList(ArrayList<AreaTable> list);
	
	
	/**
	 * 查询城市地址信息
	 * @param areaName
	 * @return
	 */
	List<AreaTable> findArea(@Param("parentName") String parentName);
	
	

}
