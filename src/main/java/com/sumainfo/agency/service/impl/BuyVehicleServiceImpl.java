package com.sumainfo.agency.service.impl;




import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sumainfo.agency.dao.BuyVehicleDao;
import com.sumainfo.agency.dao.CustomerDao;
import com.sumainfo.agency.dao.TokenDao;
import com.sumainfo.agency.service.BuyVehicleService;
import com.sumainfo.common.entity.RecBuyCar;
import com.sumainfo.common.until.MessageUntil;




@Service
public class BuyVehicleServiceImpl implements BuyVehicleService {
	Logger logger = LoggerFactory.getLogger(BuyVehicleServiceImpl.class);
	@Autowired
	private BuyVehicleDao buyVehicleDao;
	@Autowired
	private TokenDao tokenInfoDao;
	@Autowired
	private CustomerDao customerDao;
	
	/**
	 * 保存推荐的购车信息
	 */
	public MessageUntil<String> saveRecBuyVehicle(HttpServletRequest request) {
		// TODO Auto-generated method stub
		MessageUntil<String> mu = new MessageUntil<String>();
		String token = request.getParameter("token");
		String buyerName = request.getParameter("buyerName").trim();
		String buyerPhone = request.getParameter("buyerPhone").trim();
		Date createTime = new Date();
		Integer cusId = null;
		try {
			cusId = tokenInfoDao.selectByToken(token);
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("验证token登录信息异常");
			logger.info("验证token登录信息异常:"+e);
			return mu;
		}
		if(null==cusId){
			mu.setMessageCode("2");
			mu.setMessageStr("token失效");
			return mu;
		}
		
		try {
			String phone = customerDao.findCusById(cusId);
			RecBuyCar recBuyCar = new RecBuyCar(phone, buyerName, buyerPhone, createTime);
			buyVehicleDao.saveRecBuyCar(recBuyCar);
			mu.setMessageCode("0");
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageStr("推荐购车信息保存异常");
			mu.setMessageCode("1");
			logger.info("推荐购车信息保存异常:"+e);
		}
		return mu;
	}

	
	

}
