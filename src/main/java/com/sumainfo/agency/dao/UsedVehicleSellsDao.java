package com.sumainfo.agency.dao;

import java.util.ArrayList;


import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sumainfo.common.entity.UsedVehicleSells;
import com.sumainfo.common.entity.PictureSave;



public interface UsedVehicleSellsDao {
	
	
	/**
	 * 保存二手车出售信息表
	 * @return
	 */
	int saveUsedVehicleMessage(UsedVehicleSells usedVehicle);
	
	int updateUsedVehicleMessage(@Param("num") int num,@Param("produce")String produce);
	
	/**
	 * 保存图片信息
	 * @param list
	 * @return
	 */
	int savePictureList(ArrayList<PictureSave> list);
	
	/**
	 * 删除二手车信息
	 * @param num
	 */
	void deleteUsedVehicleMessage(@Param("num") int num);
	
	/**
	 * 查询自己发布的二手车信息
	 * @param shopId
	 * @return
	 */
	List<HashMap<String, Object>> findMyUsedVehicle(@Param("shopId") String shopId,
													@Param("isSell") String isSell,
													@Param("maxPage") int maxPage,
													@Param("minPage") int minPage);

	
	/**
	 * 查询自己发布的二手车信息的总条数
	 * @param shopId
	 * @param isSell
	 * @return
	 */
    Integer findMyUsedVehicleNum(@Param("shopId") String shopId,
    		                     @Param("isSell") String isSell);
    
    /**
     * 更新自己发布车辆信息的状态
     * @param shopId
     * @param isSell
     * @param uId
     * @return
     */
    int upDateMyUsedVehicle(@Param("shopId") String shopId,
                            @Param("isSell") String isSell,
                            @Param("uId") String uId);
	
    /**
     * 删除发布的二手车信息
     * @param shopId
     * @param uId
     * @return
     */
	int deleteMyUsedVehicle(@Param("shopId") String shopId,
                            @Param("uId") String uId);
	
	/**
	 * 删除图片信息
	 * @param associationId
	 * @return
	 */
	int deleteMyUsedVehiclePic(@Param("associationId") String associationId);
	
}
