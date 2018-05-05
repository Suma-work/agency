package com.sumainfo.agency.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sumainfo.common.entity.CarLabelFirstLevel;
import com.sumainfo.common.entity.CarLabelSecondLevel;
import com.sumainfo.common.entity.FcvTable;
import com.sumainfo.common.entity.SeclvlTable;



public interface FcvTableDao {
	
	
	/**
	 * 查询一级汽车信息全表id数据
	 * @return
	 */
	List<Integer> selectIds();
	
	/**
	 * 查询一级汽车信息全表数据
	 * @return
	 */
	List<FcvTable> selectAll();
	
	/**
	 * 通过id查询二级汽车信息数据
	 * @param id
	 * @return
	 */
	List<SeclvlTable> selectAllById(@Param("parentid") int parentid);
	
	/**
	 * 删除一级汽车信息全表数据
	 * 
	 */
	void deleteFcvTable();
	
	/**
	 * 插入一级汽车信息数据
	 * @param arrayList
	 */
	void insertFcvTable(ArrayList<CarLabelFirstLevel> arrayList);
	
	/**
	 * 删除二级汽车信息全表数据
	 * 
	 */
	void deleteSeclvlTable();
	
	/**
	 * 插入二级汽车信息数据
	 * @param carLabelSecondLevel
	 */
	void insertSeclvlTable(ArrayList<CarLabelSecondLevel> arrayList);
	
	
	
}
