package com.sumainfo.agency.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sumainfo.agency.dao.CommercialTenantUserMsDao;
import com.sumainfo.agency.dao.HomePageMsDao;
import com.sumainfo.agency.service.CommercialTenantUserMsService;
import com.sumainfo.common.entity.ComTenUser;
import com.sumainfo.common.entity.UserToken;
import com.sumainfo.common.redis.RedisClient;
import com.sumainfo.common.sms.SmsResponse;
import com.sumainfo.common.sms.SmsSend;
import com.sumainfo.common.until.Md5Until;
import com.sumainfo.common.until.MessageUntil;
import com.sumainfo.common.until.ShiroUtils;
import com.sumainfo.common.until.StringUntil;
import com.sumainfo.common.until.ToolsUntil;


@Service
public class CommercialTenantUserMsServiceImpl implements CommercialTenantUserMsService {
	Logger logger = LoggerFactory.getLogger(CommercialTenantUserMsServiceImpl.class);

	@Autowired
	private CommercialTenantUserMsDao userDao;
	@Autowired
	private HomePageMsDao hpmDao;
	//user登录
	@Override
	public MessageUntil<HashMap<String, Object>> userLogin(HttpServletRequest request) {
		// TODO Auto-generated method stub
		MessageUntil<HashMap<String, Object>> mu = new MessageUntil<HashMap<String,Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		//userName 账户的唯一性值
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		
		try {
			ComTenUser user = userDao.findUserMs(userName);
			if(user==null){
				mu.setMessageCode("1");
				mu.setMessageStr("此账号不存在");
				return mu;
			}
			if(user.getStatus()!=1){
				mu.setMessageCode("1");
				mu.setMessageStr("此账号被冻结");
				return mu;
			}
			//1平台管理员 2集团老总 3大区经理 4商家-店长 5商家-店员
			int role = userDao.findUserRole(user.getUser_id());
			if(role<=0){
				mu.setMessageCode("1");
				mu.setMessageStr("获取角色失败");
				return mu;
			}
			String shaPs = ShiroUtils.sha256(passWord, user.getSalt());
			if(shaPs.equals(user.getPassWord())){
				String str = shaPs+ user.getSalt();
				String token = Md5Until.encryPassWord(str+System.currentTimeMillis());
				UserToken userToken = new UserToken(user.getUser_id(), token);
				userDao.updateUserToken(userToken);
				//去掉登录密码
				user.setPassWord(null);
				//1平台管理员 2集团老总 3大区经理 4商家-店长 5商家-店员
				map.put("role", role);
				map.put("user", user);
				map.put("token", token);
				mu.setData(map);
				mu.setMessageCode("0");
			}else{
				mu.setMessageCode("1");
				mu.setMessageStr("密码错误");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("登录失败");
			logger.info("登录失败"+e);
		}
		return mu;
	}
	
	
	@Override
	public MessageUntil<String> modifyPassWord(HttpServletRequest request) {
		// TODO Auto-generated method stub
		MessageUntil<String> mu = new MessageUntil<String>();
		//1修改密码 2忘记密码
		String type = request.getParameter("type");
		if(type.equals("1")){
			String token = request.getParameter("token");
			//原密码
			String orinPs = request.getParameter("orinPs");
			//新密码
			String passWord = request.getParameter("passWord");
			//查询userId
			int userId = userDao.findUserId(token);
			Map<String, Object> map = userDao.identifyPs(userId);
			String salt = (String)map.get("salt");
			String password = (String)map.get("password");
			String identifyPs = ShiroUtils.sha256(orinPs,salt);
			String modifyPs = ShiroUtils.sha256(passWord,salt);
			if(password.equals(identifyPs)){
				userDao.updatePsById(userId,modifyPs);
				mu.setMessageCode("0");
			}else{
				mu.setMessageCode("1");
				mu.setMessageStr("原始密码错误,修改密码失败");
			}
		}else{
			RedisClient redisClient = new RedisClient();
			String token = request.getParameter("token");
			logger.info("返回的token"+token);
			if(null==redisClient.getString(token)){
				mu.setMessageCode("1");
				mu.setMessageStr("验证码失效，请稍后重试");
				return mu;
			}else{
				String identifyCode = request.getParameter("identifyCode");
				if(identifyCode.equals(redisClient.getString(token))){
				   Integer userId = Integer.valueOf(request.getParameter("userId"));
				   String password = request.getParameter("passWord");
				   Map<String, Object> map = userDao.identifyPs(userId);
				   String salt = (String)map.get("salt");
				   String modifyPs = ShiroUtils.sha256(password, salt);	   		   
				   userDao.updatePsById(userId,modifyPs);
				   mu.setMessageCode("0");
				}else{
					mu.setMessageCode("1");
					mu.setMessageStr("验证码错误");
					return mu;
				}
			}
			
		}
		
		
		return mu;
	}


	@Override
	public MessageUntil<Map<String, Object>> modifyPsIdentify(HttpServletRequest request) {
		// TODO Auto-generated method stub
		MessageUntil<Map<String, Object>> mu = new MessageUntil<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		String mobile = request.getParameter("phone");
//		String username = request.getParameter("userName");
		int count = userDao.identifyPhone(mobile);
		if(count>0){
			//发送短信
			//获取6位随机数
			String identifyCode = StringUntil.getRandom(6);
			try {
				//参数组																
				String params = mobile+",用户,"+identifyCode+",3";
				//发送短信到注册的手机
				SmsResponse response = SmsSend.SendTextCode(ToolsUntil.VARIABLE_MSG,params,ToolsUntil.VARIABLE_ACCOUNT,ToolsUntil.VARIABLE_PS,ToolsUntil.VARIABLE_URL);
				boolean flag = response.getFailNum().equals("0")?true:false;
				if(flag) {
					//发送验证后的信息把值存入redis中
					RedisClient redisClient = new RedisClient();
					String token = 
					        Md5Until.encryPassWord(mobile+System.currentTimeMillis());
					redisClient.setString(token,identifyCode, 180);//设置的token有效时间为3分钟	
					logger.info("token的值是："+token);
					mu.setMessageCode("0");
					map.put("token", token);
					map.put("userId", count);
					mu.setData(map);
					logger.info("验证码"+identifyCode);
				}else {
					mu.setMessageCode("1");
					mu.setMessageStr("发送验证码失败，请稍后重试");
				}
			}catch(Exception e) {
				mu.setMessageCode("2");
				mu.setMessageStr("发送异常"+e);
			}
		}else{
			mu.setMessageCode("1");
			mu.setMessageStr("输入信息有误，不能修改密码");
		}
		return mu;
	}
	
	public MessageUntil<List<HashMap<String, Object>>> getPlatBanner(HttpServletRequest request){
		 MessageUntil<List<HashMap<String, Object>>> mu = new MessageUntil<List<HashMap<String, Object>>>();
		 
		try {
			List<HashMap<String, Object>> banner = userDao.getPlatBanner();
			mu.setData(banner);
			mu.setMessageCode("0");
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			logger.info("查询轮播图失败"+e);
		}
		
		return mu;
		
	}


	/**
	 * 获取商户端首页展示的上半部分信息
	 */
	@Override
	public MessageUntil<HashMap<String, Object>> homePageFirstPartMs(HttpServletRequest request) {
		MessageUntil<HashMap<String, Object>> mu = new MessageUntil<HashMap<String,Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		String token = request.getParameter("token");
		String role = request.getParameter("role");
		//日期格式 20180909
//		Date startDate = DateUntil.turnToDates(request.getParameter("startDate"));
//		Date endDate = DateUntil.turnToDates(request.getParameter("endDate"));
		
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		Integer uid;
		Integer deptId;
		String shopId;
		try {
			Map<String, Object> mp = hpmDao.getUsMs(token);
			uid =  ((Long)mp.get("user_id")).intValue();
			deptId = ((Long)mp.get("dept_id")).intValue();
			shopId = (String)mp.get("shopid");
			if(uid==null){
				mu.setMessageCode("1");
				mu.setMessageStr("无该用户信息");
				return mu;
			}
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("获取用户信息失败");
			logger.info("获取用户信息失败"+e);
			return mu;
		}
		
		//1平台管理员 2集团老总 3大区经理 4商家-店长 5商家-店员
		//预约数
		Integer orders = null;
		//接待数
		Integer recepts = null;
		//成交数
		Integer sells = null;
		//成交金额
		Double amount = null;
		//最佳销售和金额
		List<Map<String, Object>> bestSells = null;
		//冠军车型和销量
		List<Map<String, Object>> chainpions = null;
		try {
			if(role.equals("2")){
				if(deptId==null){
					mu.setMessageCode("1");
					mu.setMessageStr("无该用户关联的集团");
					return mu;
				}
				orders = hpmDao.bossOrders(deptId, startDate, endDate, "1");
				recepts = hpmDao.bossOrders(deptId, startDate, endDate, "2");
				sells = hpmDao.bossOrders(deptId, startDate, endDate, "4");
				amount = hpmDao.bossAmount(deptId, startDate, endDate);
				chainpions = hpmDao.bossHotVehicle(deptId, startDate, endDate);
				map.put("chainpions", chainpions);
				bestSells = hpmDao.bossManangerBestSeller(deptId, startDate, endDate);
				map.put("bestSells", bestSells);
				
			}else if (role.equals("3")){
				if(deptId==null){
					mu.setMessageCode("1");
					mu.setMessageStr("无该用户关联的集团");
					return mu;
				}
				orders = hpmDao.regionManangerOrders(deptId, startDate, endDate, "1");
				recepts = hpmDao.regionManangerOrders(deptId, startDate, endDate, "2");
				sells = hpmDao.regionManangerOrders(deptId, startDate, endDate, "4");
				amount = hpmDao.regionManangerAmount(deptId, startDate, endDate);
				chainpions = hpmDao.regionManangerHotVehicle(deptId, startDate, endDate);
				//冠军车型和销售销售量
				map.put("chainpions", chainpions);
				bestSells = hpmDao.regionManangerBestSeller(deptId, startDate, endDate);
				map.put("bestSells", bestSells);
			}else if (role.equals("4")){
				if(shopId==null){
					mu.setMessageCode("1");
					mu.setMessageStr("无该用户关联的店铺");
					return mu;
				}
				orders = hpmDao.storeManangerOrders(shopId, startDate, endDate, "1");
				recepts = hpmDao.storeManangerOrders(shopId, startDate, endDate, "2");	
				sells = hpmDao.storeManangerOrders(shopId, startDate, endDate, "4");
				amount = hpmDao.storeManangerAmount(shopId, startDate, endDate);
				chainpions = hpmDao.storeManangerHotVehicle(shopId, startDate, endDate);
				//冠军车型和销售销售量
				map.put("chainpions", chainpions);
				bestSells = hpmDao.storeManangerBestSeller(shopId, startDate,endDate);
				map.put("bestSells", bestSells);
			}else if (role.equals("5")){
				//预约
				orders = hpmDao.assistantOrders(uid, startDate, endDate, "1");
				//接待
				recepts = hpmDao.assistantOrders(uid, startDate, endDate, "2");
				//成交
				sells = hpmDao.assistantOrders(uid, startDate, endDate, "4");
				//成交金额
				amount = hpmDao.assistantAmount(uid, startDate, endDate);

			}else{
				mu.setMessageCode("1");
				mu.setMessageStr("无查询权限");
				return mu;
			}
			if(orders==null){
				orders = 0;
			}
			
			if(recepts==null){
				recepts = 0;			
			}
			
			if(sells==null){
				sells = 0;
			}
			
			if(amount==null){
				amount = 0d;
			}
			map.put("orders", orders);
			map.put("recepts", recepts);
			map.put("sells", sells);
			map.put("amount", amount);
			mu.setData(map);
			mu.setMessageCode("0");
			
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("获取首页信息失败");
			logger.info("获取首页信息失败"+e);
		}
		
		// TODO Auto-generated method stub
		return mu;
	}


	//获取预约做多车型和数量
	@Override
	public MessageUntil<Map<String, Object>> homePagelastPartMs(HttpServletRequest request) {
		MessageUntil<Map<String, Object>> mu = new MessageUntil<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		String token = request.getParameter("token");
		String role = request.getParameter("role");
		//日期格式 20180909
//		Date startDate = DateUntil.turnToDates(request.getParameter("startDate"));
//		Date endDate = DateUntil.turnToDates(request.getParameter("endDate"));	
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		// TODO Auto-generated method stub
		Integer uid;
		Integer deptId;
		String shopId;
		try {
			Map<String, Object> mp = hpmDao.getUsMs(token);
			uid =  ((Long)mp.get("user_id")).intValue();
			deptId = ((Long)mp.get("dept_id")).intValue();
			shopId = (String)mp.get("shopid");
			if(uid==null){
				mu.setMessageCode("1");
				mu.setMessageStr("无该用户信息");
				return mu;
			}
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("获取用户信息失败");
			logger.info("获取用户信息失败"+e);
			return mu;
		}
		
		try {
			if(role.equals("2")){
				if(deptId==null){
					mu.setMessageCode("1");
					mu.setMessageStr("无该用户关联的集团");
					return mu;
				}
				map = hpmDao.bossManangerOrderVehicle(deptId, startDate, endDate);
			}else if(role.equals("3")){
				if(deptId==null){
					mu.setMessageCode("1");
					mu.setMessageStr("无该用户关联的集团");
					return mu;
				}
				map = hpmDao.regionManangerOrderVehicle(deptId, startDate, endDate);
				
			}else if(role.equals("4")){
				if(shopId==null){
					mu.setMessageCode("1");
					mu.setMessageStr("无该用户关联的店铺");
					return mu;
				}
				map = hpmDao.storeManangerOrderVehicle(shopId, startDate, endDate);
			}else{
				mu.setMessageCode("1");
				mu.setMessageStr("无查询权限");
				return mu;
			}
			
			mu.setData(map);
			mu.setMessageCode("0");
			
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("获取信息失败");
			logger.info("获取信息失败"+e);
		}
		
		return mu;
	}


	// 获取预约、接待、销售后的排名
	@Override
	public MessageUntil<Map<String, Object>> orderRank(HttpServletRequest request) {
		// TODO Auto-generated method stub
		MessageUntil<Map<String, Object>> mu = new MessageUntil<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		String token = request.getParameter("token");
		String role = request.getParameter("role");
		String type = request.getParameter("type");
		//日期格式 20180909
//		Date startDate = DateUntil.turnToDates(request.getParameter("startDate"));
//		Date endDate = DateUntil.turnToDates(request.getParameter("endDate"));
		
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		//user的id
		Integer uid;
		//user的部门id
		Integer deptId;
		//user的店铺id
		String shopId;
		try {
			Map<String, Object> mp = hpmDao.getUsMs(token);
			uid =  ((Long)mp.get("user_id")).intValue();
			deptId = ((Long)mp.get("dept_id")).intValue();
			shopId = (String)mp.get("shopid");
			if(uid==null){
				mu.setMessageCode("1");
				mu.setMessageStr("无该用户信息");
				return mu;
			}
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("获取用户信息失败");
			logger.info("获取用户信息失败"+e);
			return mu;
		}
		//已预约的信息、已接待的信息、已接待的信息
		List<Map<String, Object>> msList = null;
		try {
			if(role.equals("2")){
				if(deptId==null){
					mu.setMessageCode("1");
					mu.setMessageStr("无该用户关联的集团");
					return mu;
				}
				msList = hpmDao.bossOrderMs(deptId, startDate, endDate, type);
			}else if(role.equals("3")){
				if(deptId==null){
					mu.setMessageCode("1");
					mu.setMessageStr("无该用户关联的集团");
					return mu;
				}
				msList = hpmDao.regionManangerOrderMs(deptId, startDate, endDate,type);
			}else if(role.equals("4")){
				if(shopId==null){
					mu.setMessageCode("1");
					mu.setMessageStr("无该用户关联的店铺");
					return mu;
				}
				msList = hpmDao.storeManangerOrderMs(shopId, startDate, endDate,type);
			}else{
				mu.setMessageCode("1");
				mu.setMessageStr("无查询权限");
				return mu;
			}
			map.put("msList", msList);
			mu.setData(map);
			mu.setMessageCode("0");
			
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("获取信息失败");
			logger.info("获取信息失败"+e);
		}
		
		return mu;
	}



	
}
