package com.sumainfo.agency.service.impl;


import java.util.ArrayList;
import java.util.List;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sumainfo.agency.dao.FcvTableDao;
import com.sumainfo.agency.service.FcvTableService;
import com.sumainfo.common.entity.CarLabelFirstLevel;
import com.sumainfo.common.entity.CarLabelSecondLevel;
import com.sumainfo.common.entity.CarLableSecRecept;
import com.sumainfo.common.until.CarMessageUntil;
import com.sumainfo.common.until.MessageUntil;
import com.sumainfo.common.until.ToolsUntil;


@Service
public class FcvTableServiceImpl implements FcvTableService {
	Logger logger = LoggerFactory.getLogger(FcvTableServiceImpl.class);
	@Autowired
	private FcvTableDao fcvTableDao;

	
	/**
	 * 插入一级车辆信息标签
	 */
	public MessageUntil<String> insertFcvList(HttpServletRequest request){
		MessageUntil<String> mu = new MessageUntil<String>();
		mu.setMessageCode(ToolsUntil.MESSAGE_SUCESS);
		//获取一级车辆标签信息
		ArrayList<CarLabelFirstLevel> arrayList = CarMessageUntil.getCarFirstLevelMessage();
		if(arrayList.size()>0){
			fcvTableDao.insertFcvTable(arrayList);
		}else{
			mu.setMessageCode(ToolsUntil.MESSAGE_EROOR);
		}

		return mu;
		
	}
	
	/**
	 *插入二级车辆信息标签 
	 */
	public MessageUntil<String> insertCarSecLevelList(HttpServletRequest request){
		MessageUntil<String> mu = new MessageUntil<String>();
		mu.setMessageCode(ToolsUntil.MESSAGE_SUCESS);
//		List<Integer> ids = fcvTableDao.selectIds();
//		logger.info("id的记录条数--------------:"+ids.size());
//		if(ids.size()<=0){
//			mu.setMessageCode(ToolsUntil.MESSAGE_FAIL);
//			mu.setMessageStr("主表无信息，数据无法录入");
//		}else{
//			for(Integer id:ids){ 
//				String idStr = String.valueOf(id);
//				logger.info("id的值-----------："+idStr);
//				List<CarLableSecRecept> carSecList = CarMessageUntil.getCarSecLevelMessage(idStr);
//				ArrayList<CarLabelSecondLevel> arrayList = new ArrayList<CarLabelSecondLevel>();
//				if(carSecList.size()>0){
//					logger.info("进入循环方法");
//					for(CarLableSecRecept carSec:carSecList){
//						CarLabelSecondLevel csl = 
//								new CarLabelSecondLevel(carSec.getId(), carSec.getName(),carSec.getInitial(), carSec.getParentid(), carSec.getDepth());
//						arrayList.add(csl);
//					}
//				fcvTableDao.insertSeclvlTable(arrayList);
//				}else{
//					logger.info("无结果集返回");
//				}
//			}
//			
//		}
		String id = request.getParameter("id");
		List<CarLableSecRecept> carSecList = CarMessageUntil.getCarSecLevelMessage(id);
		ArrayList<CarLabelSecondLevel> arrayList = new ArrayList<CarLabelSecondLevel>();
		if(carSecList.size()>0){
			for(CarLableSecRecept carSec:carSecList){
				CarLabelSecondLevel csl =
						new CarLabelSecondLevel(carSec.getId(), carSec.getName(),carSec.getInitial(), carSec.getParentid(), carSec.getDepth());
				arrayList.add(csl);
			}
			fcvTableDao.insertSeclvlTable(arrayList);
		}else{
			logger.info("返回的结果集为空，不参与插入数据");
		}
		
		
		return mu;
	}


}
