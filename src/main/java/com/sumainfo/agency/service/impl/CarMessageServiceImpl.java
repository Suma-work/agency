package com.sumainfo.agency.service.impl;



import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sumainfo.agency.dao.FcvTableDao;
import com.sumainfo.agency.service.CarMessageService;
import com.sumainfo.common.entity.FcvTable;
import com.sumainfo.common.entity.SeclvlTable;
import com.sumainfo.common.until.MessageUntil;
import com.sumainfo.common.until.ToolsUntil;
import com.sumainfo.common.until.UploadPicUntil;


@Service
public class CarMessageServiceImpl implements CarMessageService {
	Logger logger = LoggerFactory.getLogger(CarMessageServiceImpl.class);
	@Autowired
	private FcvTableDao fcvTableDao;

	/**
	 * 获取一级汽车标签信息
	 */
	public MessageUntil<List<FcvTable>> getFirstLevelMessage(HttpServletRequest request){
		MessageUntil<List<FcvTable>> mu = new MessageUntil<List<FcvTable>>();
		mu.setMessageCode(ToolsUntil.MESSAGE_SUCESS);
		long  startTime = System.currentTimeMillis();
		List<FcvTable> lists = fcvTableDao.selectAll();
		long endTime = System.currentTimeMillis();
		long leftTime = endTime-startTime;
		logger.info("服务器查询数据花费的时间--------cost time out"+leftTime);
		if(lists.size()>0){
			mu.setData(lists);
		}else{
			mu.setMessageCode(ToolsUntil.MESSAGE_FAIL);
			mu.setMessageStr("数据获取失败");
		}
		
		return mu;
	}
	
	/**
	 * 获取二级汽车标签信息
	 */
	public MessageUntil<List<SeclvlTable>> selectAllById(HttpServletRequest request){
		MessageUntil<List<SeclvlTable>> mu = new MessageUntil<List<SeclvlTable>>();
		mu.setMessageCode(ToolsUntil.MESSAGE_SUCESS);
		String id = request.getParameter("id");
		List<SeclvlTable> list = fcvTableDao.selectAllById(Integer.parseInt(id));
		if(list.size()>0){
			mu.setData(list);
		}else{
			mu.setData(null);
		}
		return mu;
	}
	
	public MessageUntil<String> upload(HttpServletRequest request){
		MessageUntil<String> mu = new MessageUntil<String>();
		mu.setMessageCode(ToolsUntil.MESSAGE_SUCESS);
		String netUrl = "http://img18.3lian.com/d/file/201707/08/ea677451849f9cbc200cbb54738f1a4a.jpg";
		String urlPath = "C://Users//Administrator//Desktop//apache-tomcat-7.0.82//webapps//image//";
//				"E://tomcat//apache-tomcat-7.0.82//webapps//image//"; 
//		UploadPic.upload();
		try {
			UploadPicUntil.upLoadPicsByUrl(netUrl, urlPath);
			
			mu.setMessageStr("图片上传成功");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mu.setMessageCode(ToolsUntil.MESSAGE_FAIL);
			mu.setMessageStr("图片上传失败");
		}
		return mu;
	}

}
