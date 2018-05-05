package com.sumainfo.agency.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sumainfo.agency.dao.PlatFormReleaseDao;
import com.sumainfo.agency.service.PlatFormReleaseService;
import com.sumainfo.common.entity.NearShopEntity;
import com.sumainfo.common.entity.PlatFormReleaseNewVehicle;
import com.sumainfo.common.entity.PlatFormReleaseUsedVehicle;
import com.sumainfo.common.entity.PlatFormReleaseVehicle;
import com.sumainfo.common.entity.PlatFromReleaseUsedShopMs;
import com.sumainfo.common.entity.VehiclePrice;
import com.sumainfo.common.until.MessageUntil;
import com.sumainfo.common.until.SqlUntil;
import com.sumainfo.common.until.StringUntil;

@Service
public class PlatFormReleaseServiceImpl implements PlatFormReleaseService {
	Logger logger = LoggerFactory.getLogger(PlatFormReleaseServiceImpl.class);
	@Autowired
	private PlatFormReleaseDao platFormReleaseDao;

	@Override
	public MessageUntil<Map<String, Object>> getHotVehicleType() {
		// TODO Auto-generated method stub
		MessageUntil<Map<String, Object>> mu = new MessageUntil<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			List<PlatFormReleaseVehicle> newHot = platFormReleaseDao
					.findHotNewVehicle();
			List<PlatFormReleaseVehicle> usedHot = platFormReleaseDao
					.findHotUsedVehicle();
			if(newHot != null){
				try {
					if(StringUntil.isEmptys(newHot.get(0).getCarName())==null){
						map.put("newHot", null);
					}else{
						map.put("newHot", newHot);
					}
				} catch (Exception e) {
					// TODO: handle exception
					map.put("newHot", null);
				}
				
			}else{
				map.put("newHot", newHot);
			}
			if(usedHot != null){
				try {
					if(StringUntil.isEmptys(usedHot.get(0).getCarName())==null){
						map.put("usedHot", null);
					}else{
						map.put("usedHot", usedHot);
					}
				} catch (Exception e) {
					// TODO: handle exception
					map.put("usedHot", null);
				}
				
			}else{
			    map.put("usedHot", null);
			}
			mu.setData(map);
			mu.setMessageCode("0");
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("获取热销车失败");
			logger.info("获取热销车失败" + e);
		}
		return mu;
	}

	@Override
	public MessageUntil<Map<String, Object>> getNearShop(
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		MessageUntil<Map<String, Object>> mu = new MessageUntil<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		String lat = request.getParameter("lat");// 纬度
		String lon = request.getParameter("lon");// 经度
		if (lat == null || lon == null) {
			// 返回报错信息
			mu.setMessageCode("1");
			mu.setMessageStr("经纬度获取失败，无法获取附近商家信息");
			return mu;
		}
		// 查询所有的维保店信息 1 4s店、2 维保店 默认分页值放在xml文件里面了
		try {
			// 4s店
			String sql1 = SqlUntil.getNearShop(Double.valueOf(lat),
					Double.valueOf(lon), "1");
			List<NearShopEntity> vehicleShopList = platFormReleaseDao
					.findNearShops(sql1);
			// platFormReleaseDao.findNearShop("1",Double.valueOf(lat),Double.valueOf(lon));
			// 维保店
			String sql2 = SqlUntil.getNearShop(Double.valueOf(lat),
					Double.valueOf(lon), "2");
			List<NearShopEntity> maintenanceShopList = platFormReleaseDao
					.findNearShops(sql2);
			// platFormReleaseDao.findNearShop("2",Double.valueOf(lat),Double.valueOf(lon));
			map.put("vehicleShopList", vehicleShopList);
			map.put("maintenanceShopList", maintenanceShopList);
			mu.setData(map);
			mu.setMessageCode("0");
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("获取附近商家信息失败");
			logger.info("获取附近商家信息失败" + e);
		}

		return mu;
	}

	@Override
	public MessageUntil<Map<String, Object>> searchTab(
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		MessageUntil<Map<String, Object>> mu = new MessageUntil<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		// 分页
		String page = request.getParameter("page");
		Integer minPageNum = (Integer.valueOf(page) - 1) * 10;
		Integer maxPageNum = Integer.valueOf(page) * 10;
		// type类型 1 车品牌 2 店铺
		String type = request.getParameter("type");
		type = StringUntil.isEmptys(type);
		if (null == type) {
			mu.setMessageCode("1");
			mu.setMessageStr("请选择类型后，在做查询");
			return mu;
		}
		// 查询车辆信息
		if (type.equals("1")) {
			String bandName = request.getParameter("bandName");
			String vehicleType = request.getParameter("vehicleType");
			String priceType = request.getParameter("priceType");
			String carName = request.getParameter("carName");
			if (null == bandName || null == vehicleType || null == priceType) {
				mu.setMessageCode("1");
				mu.setMessageStr("传入数据异常，查询失败");
				return mu;
			}
			// 查询价格区间
			VehiclePrice vehiclePrice = null;
			try {
				vehiclePrice = platFormReleaseDao.getzdMs(priceType);
			} catch (Exception e) {
				// TODO: handle exception
				mu.setMessageCode("1");
				mu.setMessageStr("传入价格区间数据异常");
				logger.info("传入价格区间数据异常" + e);
				return mu;
			}
			double minPrice = vehiclePrice.getMinPrice();
			double maxPrice = vehiclePrice.getMaxPrice();
			if (priceType.equals("9")) {
				maxPrice = 10000d;
			}
			// 新车查询
			if (vehicleType.equals("1")) {
				List<PlatFormReleaseNewVehicle> newVehicleList = platFormReleaseDao
						.getptNewVehicleMs(bandName, carName, minPrice,
								maxPrice, minPageNum, maxPageNum);
				Integer num = platFormReleaseDao.getptNewVehicleMsNum(bandName,
						carName, minPrice, maxPrice);
				map.put("newVehicleList", newVehicleList);
				map.put("num", num);
				mu.setMessageCode("0");
				mu.setData(map);
			} else {
				// 二手车查询
				try {
					List<PlatFormReleaseUsedVehicle> usedVehicleList = platFormReleaseDao
							.getptUsedVehicleMs(bandName, carName, minPrice,
									maxPrice, minPageNum, maxPageNum);
					Integer num = platFormReleaseDao.getptUsedVehicleMsNum(
							bandName, carName, minPrice, maxPrice);
					map.put("usedVehicleList", usedVehicleList);
					map.put("num", num);
					mu.setMessageCode("0");
					mu.setData(map);
				} catch (Exception e) {
					// TODO: handle exception
					mu.setMessageCode("0");
					mu.setMessageStr("获取二手车信息失败");
					logger.info("获取二手车信息失败" + e);
				}
			}
		} else {
			// 店铺
			String shopType = request.getParameter("shopType");
			String shopName = request.getParameter("shopName");
			String citynm = request.getParameter("citynm");
			String lat = request.getParameter("lat");
			String lon = request.getParameter("lon");
			boolean flag = false;
			shopType = StringUntil.isEmptys(shopType);
			if (shopType == null) {
				mu.setMessageCode("1");
				mu.setMessageStr("店铺类型未选择,查询失败");
				return mu;
			}
			if (lon != null && lat != null) {
				flag = true;
			}

			if (shopType.equals("1")) {
				// 4s店
				try {
					List<PlatFromReleaseUsedShopMs> shop4sList = null;
					if (flag) {
						shop4sList = platFormReleaseDao
								.getpt4SShopMs(shopName, citynm,
										Double.parseDouble(lat),
										Double.parseDouble(lat), minPageNum,
										maxPageNum);
					} else {
						shop4sList = platFormReleaseDao.getpt4SShopMsNoLat(
								shopName, citynm, minPageNum, maxPageNum);
					}
					Integer num = platFormReleaseDao.getpt4SShopMsNum(shopName,
							citynm);
					map.put("shop4sList", shop4sList);
					map.put("num", num);
					mu.setMessageCode("0");
					mu.setData(map);
				} catch (Exception e) {
					// TODO: handle exception
					mu.setMessageCode("1");
					mu.setMessageStr("获取4s店铺信息失败");
					logger.info("获取4s店铺信息失败" + e);
				}

			} else if (shopType.equals("2")) {
				// 二手车店 无评论信息可能查询错误
				try {
					List<PlatFromReleaseUsedShopMs> shopUsedList = null;
					if (flag) {
						shopUsedList = platFormReleaseDao
								.getptUsedShopMs(shopName, citynm,
										Double.parseDouble(lat),
										Double.parseDouble(lat), minPageNum,
										maxPageNum);
					} else {
						shopUsedList = platFormReleaseDao.getptUsedShopMsNoLat(
								shopName, citynm, minPageNum, maxPageNum);
					}
					Integer num = platFormReleaseDao.getptUsedShopMsNum(
							shopName, citynm);
					map.put("shopUsedList", shopUsedList);
					map.put("num", num);
					mu.setMessageCode("0");
					mu.setData(map);
				} catch (Exception e) {
					// TODO: handle exception
					mu.setMessageCode("1");
					mu.setMessageStr("获取二手车店铺信息失败");
					logger.info("获取二手车店铺信息失败" + e);
				}
			} else {
				// 维保店
				try {
					List<PlatFromReleaseUsedShopMs> shopMsList = null;
					if (flag) {
						shopMsList = platFormReleaseDao
								.getptMsShopMs(shopName, citynm,
										Double.parseDouble(lat),
										Double.parseDouble(lat), minPageNum,
										maxPageNum);
					} else {
						shopMsList = platFormReleaseDao.getptMsShopMsNoLat(
								shopName, citynm, minPageNum, maxPageNum);
					}
					Integer num = platFormReleaseDao.getptMsShopMsNum(shopName,
							citynm);
					map.put("shopMsList", shopMsList);
					map.put("num", num);
					mu.setMessageCode("0");
					mu.setData(map);
				} catch (Exception e) {
					// TODO: handle exception
					mu.setMessageCode("1");
					mu.setMessageStr("获取维保店铺信息失败");
					logger.info("获取维保店铺信息失败" + e);
				}

			}
		}

		return mu;
	}

}
