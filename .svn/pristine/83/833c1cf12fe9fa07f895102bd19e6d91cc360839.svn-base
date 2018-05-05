package com.sumainfo.agency.service.impl;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sumainfo.agency.dao.PalaceNameDao;
import com.sumainfo.agency.service.PalaceService;
import com.sumainfo.common.area.AreaUntil;
import com.sumainfo.common.entity.AreaTable;
import com.sumainfo.common.entity.CityTable;
import com.sumainfo.common.entity.RegionTable;
import com.sumainfo.common.until.MessageUntil;
import com.sumainfo.common.until.ObjectToJsonUntil;



@Service
public class PalaceServiceImpl implements PalaceService {
	Logger logger = LoggerFactory.getLogger(PalaceServiceImpl.class);
	@Autowired
	private PalaceNameDao palaceNameDao;

	public MessageUntil<String> saveAreaMessage(HttpServletRequest request){
		MessageUntil<String> mu = new MessageUntil<String>();
		mu.setMessageCode("0");
		ArrayList<AreaTable> list = AreaUntil.provinceMessage();
		palaceNameDao.saveAreaList(list);
		return mu;
	}
	
	public MessageUntil<List<AreaTable>> findArea(HttpServletRequest request){
		MessageUntil<List<AreaTable>> mu = new MessageUntil<List<AreaTable>>();
		mu.setMessageCode("0");
		//解决前台传值乱码
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String parentName = request.getParameter("areaName");
		if(null==parentName.trim()||parentName.equals("")||parentName.equals("null")){
			parentName = null;
		}
		try {
			List<AreaTable> areaList = palaceNameDao.findArea(parentName);
			mu.setData(areaList);
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("查询数据异常");
		}
		
		return mu;
	}

}
