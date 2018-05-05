package com.sumainfo.agency.dao;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.sumainfo.common.entity.CollectMessage;
import com.sumainfo.common.entity.CollectVehicle;
import com.sumainfo.common.entity.VehicleDet;
import com.sumainfo.common.entity.VehiclePriceRange;


public interface VehicleMessageDao {
	
	
	/**
	 * 保存汽车信息附表信息
	 * @param vehicleDet
	 * @return
	 */
	int saveVehicleDet(VehicleDet vehicleDet);
	

	/**
	 * 获取新车价格区间 未用shopId做参数查询
	 * @param carName
	 * @param bandName
	 * @return
	 */
	VehiclePriceRange findNewVehiclePriceRange(@Param("carName")String carName,@Param("bandName")String bandName);
	
	/**
	 * 查询某4S店的新车的价格区间
	 * @param carName
	 * @param shopId
	 * @return
	 */
	VehiclePriceRange find4SVehiclePriceRange(@Param("carName")String carName,@Param("shopId")String shopId);
	
	/**
	 * 获取二手车的价格区间
	 * @param carName
	 * @param bandName
	 * @return
	 */
	VehiclePriceRange findUsedVehiclePriceRange(@Param("carName")String carName,@Param("bandName")String bandName);
	
	/**
	 * 获取新车车型综合信息
	 * @param carName
	 * @param shopClassify
	 * @param shopClassifys
	 * @return
	 */
	List<HashMap<String,Object>> findVehicleTypeMessage(@Param("carName")String carName,
			                                            @Param("bandName")String bandName,
			                                            @Param("maxPage")int maxPage,
			                                            @Param("minPage")int minPage);
	
	/**
	 * 获取新车车型综合信息的总条数
	 * @param carName
	 * @param bandName
	 * @return
	 */
	Integer findVehicleTypeMessageNum(@Param("carName")String carName,
                                      @Param("bandName")String bandName);
             
	
	/**
	 * 获取二手车车辆信息
	 * @param carName
	 * @param bandName
	 * @param maxNum
	 * @return
	 */
	List<HashMap<String,Object>> findUsedVehicleMessage(@Param("carName")String carName,
											            @Param("bandName")String bandName,
											            @Param("maxPage")int maxPage,
											            @Param("minPage")int minPage);
	
	/**
	 * 获取二手车信息的总条数
	 * @param carName
	 * @param bandName
	 * @return
	 */
	Integer findUsedVehicleMessageNum(@Param("carName")String carName,
                                                           @Param("bandName")String bandName);
           
	
	/**
	 * 查询车型的轮播图
	 * @param carName
	 * @param shopId
	 * @return
	 */
	List<HashMap<String,Object>> findVehicleBanner(@Param("carName")String carName,@Param("shopId")String shopId);
	
	/**
	 * 查询车辆的详细信息
	 * @param carName
	 * @param mainId
	 * @return
	 */
	List<HashMap<String,Object>> findVehicleDet(@Param("carName")String carName,
			                                    @Param("shopId")String shopId,
			                                    @Param("maxPage")int maxPage,
			                                    @Param("minPage")int minPage);
	
	/**
	 * 获取车辆详情信息的总条数
	 * @param carName
	 * @param shopId
	 * @return
	 */
	Integer findVehicleDetNum(@Param("carName")String carName,
                              @Param("shopId")String shopId);
            
	/**
	 * 查询某类车型父级表信息
	 * @param carName
	 * @param shopId
	 * @return
	 */
	List<HashMap<String,Object>> findVehicleMain(@Param("carName")String carName,@Param("shopId")String shopId);
	
	
	/**
	 * 查询二手车详细信息
	 * @param carName
	 * @param shopId
	 * @return
	 */
	HashMap<String,Object> findUsedVehicleDet(@Param("uId")String uId);
	
	/**
	 * 获取二手车轮播图
	 * @param uId
	 * @return
	 */
	List<HashMap<String,Object>> findUsedVehicleBannner(@Param("uId")String uId);
	
	/**
	 * 收藏车辆信息
	 * @param collectVehicle
	 * @return
	 */
	int updateCollectStatus(CollectVehicle collectVehicle);
	
	/**
	 * 查询收藏状态 0收藏 1未收藏
	 * @param cusId
	 * @param associationId
	 * @return
	 */
	Integer findCollectStatus(@Param("cusId")int cusId,@Param("associationId")String associationId);
	
	/**
	 * 获取收藏车辆信息
	 * @param cusId
	 * @param pageSize
	 * @return
	 */
	List<CollectMessage> findMyCollect4sVehicle(@Param("cusId")int cusId,@Param("maxPage")int maxPage,@Param("minPage")int minPage);
	
	/**
	 * 获取收藏车辆信息的总条数
	 * @param cusId
	 * @return
	 */
	Integer findMyCollect4sVehicleNum(@Param("cusId")int cusId);
	
	

}
