package com.sumainfo.agency.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sumainfo.agency.dao.CustomerDao;
import com.sumainfo.agency.dao.TokenDao;
import com.sumainfo.agency.dao.VehicleMessageDao;
import com.sumainfo.agency.service.VehicleMessageService;
import com.sumainfo.common.entity.CollectMessage;
import com.sumainfo.common.entity.CollectVehicle;
import com.sumainfo.common.entity.VehiclePriceRange;
import com.sumainfo.common.until.MessageUntil;
import com.sumainfo.common.until.StringUntil;


@Service
public class VehicleMessageServiceImpl implements VehicleMessageService {
	Logger logger = LoggerFactory.getLogger(VehicleMessageServiceImpl.class);
	@Autowired
	private VehicleMessageDao vehicleMessageDao;
	@Autowired
	private TokenDao tokenDao;
	@Autowired
	private CustomerDao cusDao;
	//点击车标跳转到的车详情页 首次跳转
	@Override
	public MessageUntil<HashMap<String, Object>> findVehicleMixMessage(
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		MessageUntil<HashMap<String, Object>>  mu = 
				new MessageUntil<HashMap<String,Object>>();
		String name = null;
		//车辆品牌名称
		String bandName = request.getParameter("bandName");
		//车辆子系名称
		String carName = request.getParameter("carName");
		//分页字段
		Integer page = Integer.valueOf(request.getParameter("page"));
		int maxPage = 10;
		int minPage = 0;
		if(null != page){
			maxPage = page*10;
			minPage = (page-1)*10;
		}
		bandName = StringUntil.isEmptys(bandName);
		carName = StringUntil.isEmptys(carName);
		//判断不为null的值作为传入获取轮播图的参数且判断bandName或carName只能有一个值传入
		if(bandName !=null&&carName !=null){
			mu.setMessageCode("1");
			mu.setMessageStr("车辆品牌名称和车型名称不能同时都存在值");
			return mu;
		}
		
		if(bandName !=null){
			name = bandName;
		}else if(carName !=null){
			name = carName;
		} else {
			mu.setMessageCode("1");
			mu.setMessageStr("传入信息有误，请检查传入值");
			return mu;
		}
		try {
			//新车 车型综合详细信息
			List<HashMap<String, Object>> shopVehicleList = vehicleMessageDao.findVehicleTypeMessage(carName,bandName,maxPage,minPage);
			//获取新车车型的综合信息的总条数
			Integer vehicleNum = vehicleMessageDao.findVehicleTypeMessageNum(carName, bandName);
			//获取平台banner图
			List<HashMap<String, Object>> bannerList = vehicleMessageDao.findVehicleBanner(name,null);
			//获取新车最高或最低价格
			VehiclePriceRange newCarPricelist = vehicleMessageDao.findNewVehiclePriceRange(carName,bandName);
			//获取二手车的最高或最低价格
			VehiclePriceRange usedCarPricelist = vehicleMessageDao.findUsedVehiclePriceRange(carName, bandName);
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("shopVehicleList", shopVehicleList);
			map.put("vehicleNum", vehicleNum);
			map.put("bannerList", bannerList);
			map.put("newCarPricelist", newCarPricelist);
			map.put("usedCarPricelist", usedCarPricelist);
			mu.setData(map);
			mu.setMessageCode("0");
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("通过车型名称获取车辆信息失败");
			logger.info("通过车型名称获取车辆信息失败:"+e);
		}
			
		return mu;
	}
	
	//上滑动获取信息
	@Override
	public MessageUntil<HashMap<String, Object>> lapseLoadVehicleMessage(
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		MessageUntil<HashMap<String, Object>>  mu = 
				                               new MessageUntil<HashMap<String, Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		Integer page = Integer.valueOf(request.getParameter("page"));
		String classify = request.getParameter("classify");
		String carName = request.getParameter("carName");
		String bandName = request.getParameter("bandName");
		bandName = StringUntil.isEmptys(bandName);
		carName = StringUntil.isEmptys(carName);
	    if(bandName !=null&&carName !=null){
			mu.setMessageCode("1");
			mu.setMessageStr("车辆品牌名称和车型名称不能同时都存在值");
			return mu;
		}
	    int maxPage =10;
	    int minPage = 0;
		if(null != page){
			maxPage = page*10;
			minPage = (page-1)*10;
		}
	    try {
			if(classify.equals("0")){
				//获取新车车型综合信息
				List<HashMap<String, Object>> shopVehicleList = vehicleMessageDao.findVehicleTypeMessage(carName,bandName,maxPage,minPage);
				//获取新车车型的综合信息的总条数
				Integer vehicleNum = vehicleMessageDao.findVehicleTypeMessageNum(carName, bandName);
				map.put("shopVehicleList", shopVehicleList);
				map.put("vehicleNum", vehicleNum);
				mu.setData(map);
		    	mu.setMessageCode("0");
			}else{
				//获取二手车信息
				List<HashMap<String, Object>> usedVehicleList = vehicleMessageDao.findUsedVehicleMessage(carName, bandName, maxPage,minPage);
				Integer usedVehicleNum = vehicleMessageDao.findUsedVehicleMessageNum(carName, bandName);
				map.put("usedVehicleList", usedVehicleList);
				map.put("usedVehicleNum", usedVehicleNum);
				mu.setData(map);
		    	mu.setMessageCode("0");
			}
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
	    	mu.setMessageStr("获取数据失败");
	    	logger.info("获取车信息失败："+e);
		}
		return mu;
	}
	
	//点击二手车价格获取信息
	@Override
	public MessageUntil<HashMap<String, Object>> findUsedVehicleMix(
			HttpServletRequest request) {
		MessageUntil<HashMap<String, Object>>  mu = 
				                      new MessageUntil<HashMap<String, Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		
//		String name;
		//车辆品牌名称
		String bandName = request.getParameter("bandName");
		//车辆子系名称
		String carName = request.getParameter("carName");
		//分页字段
		Integer page = Integer.valueOf(request.getParameter("page"));
		int maxPage = 10;
		int minPage = 10;
		if(null != page){
			maxPage = page*10;
			minPage = (page-1)*10;
		}
		bandName = StringUntil.isEmptys(bandName);
		carName = StringUntil.isEmptys(carName);
	    if(bandName !=null&&carName !=null){
			mu.setMessageCode("1");
			mu.setMessageStr("车辆品牌名称和车型名称不能同时都存在值");
			return mu;
		}
	    try{
	    	List<HashMap<String, Object>> usedVehicleList = vehicleMessageDao.findUsedVehicleMessage(carName, bandName, maxPage,minPage);
	    	Integer usedVehicleNum = vehicleMessageDao.findUsedVehicleMessageNum(carName, bandName);
	    	map.put("usedVehicleList", usedVehicleList);
	    	map.put("usedVehicleNum", usedVehicleNum);
	    	mu.setData(map);
	    	mu.setMessageCode("0");
	    }catch(Exception e){
	    	mu.setMessageCode("1");
	    	mu.setMessageStr("获取数据失败");
	    	logger.info("获取二手车信息失败："+e);
	    }
		
		return mu;
	}

	//新车 跳转第二次的详情页面
	@Override
	public MessageUntil<HashMap<String, Object>> findVehicleDetMessage(
			HttpServletRequest request) {
		MessageUntil<HashMap<String, Object>> mu = new MessageUntil<HashMap<String,Object>>();
		String carName = request.getParameter("carName");
		String shopId = request.getParameter("shopId");
		String token = request.getParameter("token");
		Integer page = Integer.valueOf(request.getParameter("page"));
		int maxPage = 10;
		int minPage = 0;
		if(null !=page){
			maxPage = page*10;
			minPage = (page-1)*10;
		}
		try {
			//轮播图信息
			List<HashMap<String, Object>> picturetList = vehicleMessageDao.findVehicleBanner(carName, shopId);
			//查询车辆在某店铺的区间价格信息 
			VehiclePriceRange priceList = vehicleMessageDao.find4SVehiclePriceRange(carName,shopId);
			//查询汽车详细信息的父级表信息
			List<HashMap<String, Object>> vehicleMainList = vehicleMessageDao.findVehicleMain(carName, shopId);
			//查询汽车信息
			List<HashMap<String, Object>> vehicleList = vehicleMessageDao.findVehicleDet(carName, shopId,maxPage,minPage);
			//查询汽车信息总条数
			Integer vehicleListNum = vehicleMessageDao.findVehicleDetNum(carName, shopId);
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("picturetList", picturetList);
			map.put("priceList", priceList);
			map.put("vehicleMainList", vehicleMainList);
			map.put("vehicleList", vehicleList);
			map.put("vehicleListNum", vehicleListNum);
			//token可为空
			if(null !=token&&!token.equals("")){
				logger.info("token不存在------"+token);
				//查询客户主键的 cusId可能为空
				Integer cusId = tokenDao.selectByToken(token);
				if(null==cusId){
					mu.setMessageCode("2");
					mu.setMessageStr("token过期");
					return mu;
				}
				String uqId = (String) vehicleMainList.get(0).get("uqId");
				//获取是否收藏的状态
				Integer status = vehicleMessageDao.findCollectStatus(cusId, uqId);
				if(null==status||status==1){
					map.put("isCollect", 1);
				}else{
					map.put("isCollect", 0);
				}
			}else{
				map.put("isCollect", 1);
			}
			mu.setData(map);
			mu.setMessageCode("0");
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("查询详情页面失败");
			logger.info("查询详情页面失败:"+e);
		}
		return mu;
	}

	//二手车  第二次跳转获取二手车详情信息
	@Override
	public MessageUntil<HashMap<String, Object>> findUsedVehicleDet(
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		MessageUntil<HashMap<String, Object>> mu = new MessageUntil<HashMap<String, Object>>();
		String uId = request.getParameter("uId");
		try {
			//获取汽车的详细信息
			HashMap<String, Object> usedVehicle = vehicleMessageDao.findUsedVehicleDet(uId);
            //获取汽车的轮播图信息
			List<HashMap<String, Object>> usedBannerList = vehicleMessageDao.findUsedVehicleBannner(uId);
			//获取userMs
			Map<String, Object> userMs = cusDao.findCusByUid(uId);
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("usedVehicle", usedVehicle);
			map.put("usedBannerList", usedBannerList);
			map.put("contactMs", userMs);
			mu.setData(map);
			mu.setMessageCode("0");
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("获取二手车信息出错");
			logger.info("获取二手车信息出错"+e);
		}
		
		return mu;
	}

	    //滑动获取新车信息
		public MessageUntil<HashMap<String,Object>> findVehicleMessage(HttpServletRequest request){
			
			MessageUntil<HashMap<String,Object>> mu = new MessageUntil<HashMap<String,Object>>();
			HashMap<String, Object> map = new HashMap<String, Object>();
			String carName = request.getParameter("carName");
			String shopId = request.getParameter("shopId");
			Integer page = Integer.valueOf(request.getParameter("page"));
			int maxPage = 10;
			int minPage = 0;
			if(null != page){
				maxPage = page*10;
				minPage = (page-1)*10;
			}
			try {
				List<HashMap<String,Object>> vehicleList = vehicleMessageDao.findVehicleDet(carName,shopId,maxPage,minPage);
				Integer vehicleListNum = vehicleMessageDao.findVehicleDetNum(carName, shopId);
				map.put("vehicleList", vehicleList);
				map.put("vehicleListNum", vehicleListNum);
				mu.setData(map);
				mu.setMessageCode("0");
			} catch (Exception e) {
				// TODO: handle exception
				mu.setMessageCode("1");
				mu.setMessageStr("查询汽车的详细信息失败");
				logger.info("查询汽车的详细信息失败:"+e);
			}
			return mu;
		}
		
		//收藏车信息
		@Override
		public MessageUntil<String> collectVehicle(HttpServletRequest request) {
			// TODO Auto-generated method stub
			MessageUntil<String> mu = new MessageUntil<String>();
			String token = request.getParameter("token");
			int isCollect = Integer.valueOf(request.getParameter("isCollect"));
			String uqId = request.getParameter("uqId");
			String type = request.getParameter("type");
			if(type==null){
				mu.setMessageCode("1");
				mu.setMessageStr("传值的类型不能为空");
				return mu;
			}
			Integer cusId = null;
			try {
				 cusId = tokenDao.selectByToken(token);
			} catch (Exception e) {
				// TODO: handle exception
				logger.info("收藏汽车信息报错："+e);
			}
			//收藏表里面的classfy类型 4 是4s店的车
			if(null !=cusId){
				CollectVehicle collectVehicle =new CollectVehicle(cusId, uqId, Integer.valueOf(type), isCollect);
				try {
					vehicleMessageDao.updateCollectStatus(collectVehicle);
					mu.setMessageCode("0");
				} catch (Exception e) {
					// TODO: handle exception
					logger.info("保存收藏信息出错:"+e);
					mu.setMessageCode("1");
					mu.setMessageStr("收藏车辆信息异常");
				}
			}else{
				mu.setMessageCode("1");
				mu.setMessageStr("系统异常");
			}
			return mu;
		}
		
		/**
		 * 获取收藏车信息list
		 */
		@Override
		public MessageUntil<HashMap<String, Object>> findMyCollectVehicle(
				HttpServletRequest request) {
			// TODO Auto-generated method stub
			MessageUntil<HashMap<String, Object>> mu = new MessageUntil<HashMap<String,Object>>();
			HashMap<String, Object> map = new HashMap<String, Object>();
			String token = request.getParameter("token");
			Integer page = Integer.valueOf(request.getParameter("page"));
			Integer cusId = null;
			try {
				 cusId = tokenDao.selectByToken(token);
				 if(null==cusId){
					 mu.setMessageCode("2");
					 mu.setMessageStr("登录失效");
					 return mu;
				 }
			} catch (Exception e) {
				// TODO: handle exception
				logger.info("收藏汽车信息报错："+e);
			}
			//判断page是否存在有值
			int maxPage = 10;
			int minPage = 0;
			if(null !=page){
				maxPage = page*10;
				minPage = (page-1)*10;
			}
			
			//做查询操作
			try {
				List<CollectMessage> collectList = vehicleMessageDao.findMyCollect4sVehicle(cusId, maxPage,minPage);
				Integer maxNum = vehicleMessageDao.findMyCollect4sVehicleNum(cusId);
				map.put("collectList", collectList);
				map.put("maxNum", maxNum);
				mu.setMessageCode("0");
				mu.setData(map);
			} catch (Exception e) {
				// TODO: handle exception
				mu.setMessageCode("1");
				mu.setMessageStr("获取收藏车辆信息失败");
				logger.info("获取收藏车辆信息失败："+e);
			}
			return mu;
		}

		
		
		@Override
		public MessageUntil<HashMap<String, Object>> findHotVehicleType(
				HttpServletRequest request) {
			// TODO Auto-generated method stub
		    
			return null;
		}

	

	


}
