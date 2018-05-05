package com.sumainfo.agency.service.impl;




import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sumainfo.agency.dao.SellNewVehicleDao;
import com.sumainfo.agency.service.SellNewVehicleService;
import com.sumainfo.common.entity.NewVehicleSells;
import com.sumainfo.common.until.MessageUntil;




@Service
public class SellNewVehicleServiceImpl implements SellNewVehicleService {
	Logger logger = LoggerFactory.getLogger(SellNewVehicleServiceImpl.class);
	@Autowired
	private SellNewVehicleDao sellNewVehicleDao;
	
	
	public MessageUntil<String> createVehicleMessage(HttpServletRequest request){
		MessageUntil<String> mu = new MessageUntil<String>();
		int cusId = Integer.parseInt(request.getParameter("cusId"));
		String cusName =request.getParameter("cusName");
		String cusPhone =request.getParameter("cusPhone");
		String cusIdentityCard =request.getParameter("cusIdentityCard");
		String carBrand =request.getParameter("carBrand");
		String carType =request.getParameter("carType");
		String carRecver =request.getParameter("carRecver");
		String amounts =request.getParameter("amounts");
		String remark =request.getParameter("remark");
		String referrer =request.getParameter("referrer");
		String referrerPhone =request.getParameter("referrerPhone");
		String referrerIdentityCard =request.getParameter("referrerIdentityCard");
		Date createTime = new Date();
		NewVehicleSells newVehicle = 
				new NewVehicleSells(cusId, cusName, cusPhone, cusIdentityCard, carBrand, carType, carRecver, amounts, remark, referrer, referrerPhone, referrerIdentityCard, createTime);
		try {
			sellNewVehicleDao.saveVehicleMessage(newVehicle);
			mu.setMessageCode("0");
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("2");
		}
		
		return mu;
	}

	

}
