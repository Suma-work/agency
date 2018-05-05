package com.sumainfo.agency.dao;




import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sumainfo.common.entity.NearShopEntity;
import com.sumainfo.common.entity.PlatFormReleaseNewVehicle;
import com.sumainfo.common.entity.PlatFormReleaseUsedVehicle;
import com.sumainfo.common.entity.PlatFormReleaseVehicle;
import com.sumainfo.common.entity.PlatFromReleaseUsedShopMs;
import com.sumainfo.common.entity.VehiclePrice;




public interface PlatFormReleaseDao {
	
	/**
	 * 查询热销新车
	 * @param carName
	 * @return
	 */
	List<PlatFormReleaseVehicle> findHotNewVehicle();
	
	/**
	 * 品质二手车
	 * @param carName
	 * @return
	 */
	List<PlatFormReleaseVehicle> findHotUsedVehicle();
	
	/**
	 * 获取店铺信息
	 * @param classify
	 * @return
	 */
	List<NearShopEntity> findNearShop(@Param("classify") String classify,
							            @Param("lat") Double lat,
							            @Param("lon") Double lon);
	
	/**
	 * 获取附近商家信息
	 * @param shopSql
	 * @return
	 */
	List<NearShopEntity> findNearShops(@Param("shopSql") String shopSql);
	
	/**
	 * 获取价格区间
	 * @param code
	 * @return
	 */
	VehiclePrice getzdMs(@Param("code") String code);
	
	/**
	 * 首页搜索框获取新车信息
	 * @param bandName
	 * @param carName
	 * @param minPrice
	 * @param maxPrice
	 * @param minPageNum
	 * @param maxPageNum
	 * @return
	 */
	List<PlatFormReleaseNewVehicle> getptNewVehicleMs(@Param("bandName") String bandName,
										              @Param("carName") String carName,
										              @Param("minPrice") Double minPrice,
										              @Param("maxPrice") Double maxPrice,
										              @Param("minPageNum") int minPageNum,
										              @Param("maxPageNum") int maxPageNum);
	
	/**
	 * 首页搜索框获取新车信息的条数
	 * @param bandName
	 * @param carName
	 * @param minPrice
	 * @param maxPrice
	 * @return
	 */
	Integer getptNewVehicleMsNum(@Param("bandName") String bandName,
					             @Param("carName") String carName,
					             @Param("minPrice") Double minPrice,
					             @Param("maxPrice") Double maxPrice);

	/**
	 * 首页搜索框获取二手车信息
	 * @param bandName
	 * @param carName
	 * @param minPrice
	 * @param maxPrice
	 * @return
	 */
	List<PlatFormReleaseUsedVehicle> getptUsedVehicleMs(@Param("bandName") String bandName,
									                    @Param("carName") String carName,
									                    @Param("minPrice") Double minPrice,
									                    @Param("maxPrice") Double maxPrice,
									                    @Param("minPageNum") int minPageNum,
									                    @Param("maxPageNum") int maxPageNum);
	
	/**
	 * 首页搜索框获取二手车信息的条数
	 * @param bandName
	 * @param carName
	 * @param minPrice
	 * @param maxPrice
	 * @return
	 */
	Integer getptUsedVehicleMsNum(@Param("bandName") String bandName,
					              @Param("carName") String carName,
					              @Param("minPrice") Double minPrice,
					              @Param("maxPrice") Double maxPrice);
	
	/**
	 * 首页搜索获取4S店铺信息不存在经纬度
	 * @param shopName
	 * @param citynm
	 * @return
	 */
	List<PlatFromReleaseUsedShopMs> getpt4SShopMsNoLat(@Param("shopName") String shopName,
			                                             @Param("citynm") String citynm,
			                                             @Param("minPageNum") int minPageNum,
							                             @Param("maxPageNum") int maxPageNum);
	
	
	/**
	 * 首页搜索获取4S店铺信息存在经纬度
	 * @param shopName
	 * @param citynm
	 * @return
	 */
	List<PlatFromReleaseUsedShopMs> getpt4SShopMs(@Param("shopName") String shopName,
			                                        @Param("citynm") String citynm,
			                                        @Param("lat") Double lat,
			                                        @Param("lon") Double lon,
			                                        @Param("minPageNum") int minPageNum,
							                        @Param("maxPageNum") int maxPageNum);
	
	/**
	 * 首页搜索获取4S店铺信息条数
	 * @param shopName
	 * @param citynm
	 * @return
	 */
	Integer getpt4SShopMsNum(@Param("shopName") String shopName,
                               @Param("citynm") String citynm);
	
	/**
	 * 首页搜索获取二手车店铺信息存在经纬度
	 * @param shopName
	 * @param citynm
	 * @return
	 */
	List<PlatFromReleaseUsedShopMs> getptUsedShopMs(@Param("shopName") String shopName,
			                                        @Param("citynm") String citynm,
			                                        @Param("lat") Double lat,
			                                        @Param("lon") Double lon,
			                                        @Param("minPageNum") int minPageNum,
							                        @Param("maxPageNum") int maxPageNum);
	
	/**
	 * 首页搜索获取二手车店铺信息不存在经纬度
	 * @param shopName
	 * @param citynm
	 * @return
	 */
	List<PlatFromReleaseUsedShopMs> getptUsedShopMsNoLat(@Param("shopName") String shopName,
			                                             @Param("citynm") String citynm,
			                                             @Param("minPageNum") int minPageNum,
							                             @Param("maxPageNum") int maxPageNum);
	
	/**
	 * 首页搜索获取二手车店铺信息条数
	 * @param shopName
	 * @param citynm
	 * @return
	 */
	Integer getptUsedShopMsNum(@Param("shopName") String shopName,
                               @Param("citynm") String citynm);
	
	/**
	 * 首页搜索获取维保店铺信息存在经纬度
	 * @param shopName
	 * @param citynm
	 * @return
	 */
	List<PlatFromReleaseUsedShopMs> getptMsShopMs(@Param("shopName") String shopName,
			                                        @Param("citynm") String citynm,
			                                        @Param("lat") Double lat,
			                                        @Param("lon") Double lon,
			                                        @Param("minPageNum") int minPageNum,
							                        @Param("maxPageNum") int maxPageNum);
	
	/**
	 * 首页搜索获取维保店铺信息不存在经纬度
	 * @param shopName
	 * @param citynm
	 * @return
	 */
	List<PlatFromReleaseUsedShopMs> getptMsShopMsNoLat(@Param("shopName") String shopName,
			                                             @Param("citynm") String citynm,
			                                             @Param("minPageNum") int minPageNum,
							                             @Param("maxPageNum") int maxPageNum);
	
	/**
	 * 首页搜索获取维保店铺信息条数
	 * @param shopName
	 * @param citynm
	 * @return
	 */
	Integer getptMsShopMsNum(@Param("shopName") String shopName,
                               @Param("citynm") String citynm);
	
	

}
