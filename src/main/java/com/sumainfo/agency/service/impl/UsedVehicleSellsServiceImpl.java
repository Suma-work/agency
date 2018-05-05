package com.sumainfo.agency.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sumainfo.agency.dao.TokenDao;
import com.sumainfo.agency.dao.UsedVehicleSellsDao;
import com.sumainfo.agency.service.UsedVehicleSellsService;
import com.sumainfo.common.entity.UsedVehicleSells;
import com.sumainfo.common.entity.PictureSave;
import com.sumainfo.common.until.ComUtils;
import com.sumainfo.common.until.DateUntil;
import com.sumainfo.common.until.MessageUntil;
import com.sumainfo.common.until.StringUntil;
import com.sumainfo.common.until.ToolsUntil;
import com.sumainfo.common.until.UploadPicUntil;

@Service
public class UsedVehicleSellsServiceImpl implements UsedVehicleSellsService {
	Logger logger = LoggerFactory.getLogger(UsedVehicleSellsServiceImpl.class);
	@Autowired
	private UsedVehicleSellsDao usedVehicleSellsDao;
	@Autowired
	private TokenDao tokenDao;

	/**
	 * 保存二手车车辆信息
	 * 
	 * @throws IOException
	 */
	public MessageUntil<String> usedVehicleSellsMessage(
			HttpServletRequest request, MultipartFile[] file) {
		MessageUntil<String> mu = new MessageUntil<String>();
		mu.setMessageCode("0");
		// 汽车品牌名称
		String bandName = request.getParameter("bandName");
		// 汽车车型名称
		String carName = request.getParameter("carName");
		// 汽车的具体名称
		String carDetName = request.getParameter("carDetName");
		// 汽车的售价
		Double sellPrice = Double.valueOf(request.getParameter("sellPrice"));
		// 汽车原本定位价格
		Double orginPrice = null;
		// 发动机
		String carEngine = null;
		// 变速箱
		String gearbox = null;
		// 车体结构
		String carType = null;
		// 车型等级
		String rank = null;
		// 看车城市
		String carLocation = request.getParameter("carLocation");
		// 行驶公里数 万公里
		Double mileage = Double.valueOf(request.getParameter("mileage"));
		// 是否上牌
		String isLicence = request.getParameter("isLicence");
		// 上牌时间 yyyy-MM-dd HH:mm:ss
		Date licenceTime = DateUntil.turnDate(request
				.getParameter("licenceTime"));
		// 区分字段 0个人 1二手车机构
		String classify = request.getParameter("classify");
		// 是否急售 默认为0否
		String isShort = request.getParameter("isShort");
		// 是否陪看 默认0否
		String isCompany = request.getParameter("isCompany");
		// 详情介绍 富文本需要自己解析
		String produce = request.getParameter("produce");
		// 生成的唯一主键
		String uId = ComUtils.randomUID("used");
		// 下标
		String index = request.getParameter("index");
		index = StringUntil.isEmptys(index);
		Integer indexNum = null;
		if (null != index) {
			indexNum = Integer.valueOf(index);
		}

		// 用token查询出cusId存入到shopId里面 shopName自己给默认值 个人卖家
		String token = request.getParameter("token");
		Integer cusId = null;
		try {
			cusId = tokenDao.selectByToken(token);
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("登录信息异常，无法进行发布二手车操作");
			logger.info("登录信息异常，发布二手车信息失败:" + e);
			return mu;
		}
		if (null == cusId) {
			mu.setMessageCode("1");
			mu.setMessageStr("token失效，不能发布二手车信息");
			return mu;
		}
		String shopId = String.valueOf(cusId);
		String shopName = "个人卖家";
		ArrayList<PictureSave> list = new ArrayList<PictureSave>();
		List<String> picList = new ArrayList<String>();
		for (int i = 0; i < file.length; i++) {
			PictureSave vp = null;
			InputStream is;
			try {
				vp = new PictureSave();
				is = file[i].getInputStream();
				String pic = UploadPicUntil.uploadPic(is, ToolsUntil.PATH_URL,
						file[i].getOriginalFilename());
				String url = ToolsUntil.PICTURE_URL + pic;
				vp.setPicAddress(url);
				vp.setAssociationId(uId);
				vp.setClassify("2");// 二手车
				vp.setCreateTime(new Date());
				if (indexNum != null) {
					if (i >= indexNum) {
						// 详情图片
						vp.setSlideshow("4");
						picList.add(url);
					} else if (i == 0) {
						// 展示图片
						vp.setSlideshow("2");
					} else {
						// 轮播图
						vp.setSlideshow("1");
					}
				} else {
					if (i == 0) {
						vp.setSlideshow("2");
					} else {
						vp.setSlideshow("1");
					}
				}
				vp.setDelfg(0);
				list.add(vp);
			} catch (Exception e) {
				mu.setMessageCode("2");
				logger.info("图片上传失败:" + e);
			}
		}
		try {
			produce=URLDecoder.decode(produce, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (picList.size() > 0) {
			produce = produce.replaceAll("<\\s*img src=\"","--kk--kk-l-l--kk--kk--< img src=\"");
			logger.info("二手车富文本存储之前：" + produce);
			for (String str : picList) {
				produce = produce.replaceFirst("--kk--kk-l-l--kk--kk--< img src=\".*?\"\\s*/?\\s*>", "< img src=\""+str+"\">");
			}
			logger.info("二手车富文本存储之后: " + produce);
		}
		UsedVehicleSells usedVehicle = null;
		String isSell = null;
		if (request.getParameter("type").equals("1")) {
			// 是否发布 默认为0否 正常状态下是立即发布
			isSell = "0";
			usedVehicle = new UsedVehicleSells(bandName, carName, carDetName,
					sellPrice, orginPrice, carEngine, gearbox, carType, rank,
					isLicence, shopId, shopName, carLocation, mileage,
					licenceTime, classify, isSell, isShort, isCompany, uId,
					produce, new Date());
		} else {
			isSell = "1";
			usedVehicle = new UsedVehicleSells(bandName, carName, carDetName,
					sellPrice, orginPrice, carEngine, gearbox, carType, rank,
					isLicence, shopId, shopName, carLocation, mileage,
					licenceTime, classify, isSell, isShort, isCompany, uId,
					produce, new Date());
		}
		try {
			usedVehicleSellsDao.saveUsedVehicleMessage(usedVehicle);
			
		} catch (Exception e) {
			mu.setMessageCode("2");
			logger.info("保存失败:" + e);
		}

		try {
			usedVehicleSellsDao.savePictureList(list);
		} catch (Exception e) {
			logger.info("保存图片信息失败:" + e);
			mu.setMessageCode("2");
			mu.setMessageStr("出售车辆信息保存出错，请稍后重试");
			usedVehicleSellsDao
					.deleteUsedVehicleMessage(usedVehicle.getOldId());
		}

		return mu;

	}

	@Override
	public MessageUntil<HashMap<String, Object>> findMyUsedVehicleMessage(
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		MessageUntil<HashMap<String, Object>> mu = new MessageUntil<HashMap<String, Object>>();
		String token = request.getParameter("token");
		String isSell = request.getParameter("isSell");

		Integer cusId = null;
		try {
			cusId = tokenDao.selectByToken(token);
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("查询自己二手出错信息：" + e);
			mu.setMessageStr("查询自己二手出错信息：" + e);
			mu.setMessageCode("1");
			return mu;
		}
		// 判断cusId是否存在
		if (cusId == null) {
			mu.setMessageStr("token过期");
			mu.setMessageCode("2");
			return mu;
		}
		String pages = StringUntil.isEmptys(request.getParameter("page"));
		Integer page = null;
		// 判断pages是否为null
		if (pages == null) {
			page = 1;
		} else {
			page = Integer.valueOf(pages);
		}
		int pageNum = 10;
		int maxPage = page * pageNum;
		int minPage = (page - 1) * pageNum;
		try {
			List<HashMap<String, Object>> myUsedVehicleList = usedVehicleSellsDao
					.findMyUsedVehicle(String.valueOf(cusId), isSell, maxPage,
							minPage);
			Integer maxNum = usedVehicleSellsDao.findMyUsedVehicleNum(
					String.valueOf(cusId), isSell);
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("myUsedVehicleList", myUsedVehicleList);
			map.put("maxNum", maxNum);
			mu.setData(map);
			mu.setMessageCode("0");
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("获取发布车辆信息失败：" + e);
			logger.info("获取发布车辆信息失败：" + e);
		}
		return mu;
	}

	/**
	 * 更新发布状态
	 */
	@Override
	public MessageUntil<String> retMyUsedVehicle(HttpServletRequest request) {
		// TODO Auto-generated method stub
		MessageUntil<String> mu = new MessageUntil<String>();
		String token = request.getParameter("token");
		String isSell = request.getParameter("isSell");
		String uId = request.getParameter("uId");
		Integer cusId = null;
		try {
			cusId = tokenDao.selectByToken(token);

		} catch (Exception e) {
			// TODO: handle exception
			logger.info("下架二手车失败：" + e);
			mu.setMessageStr("下架二手车失败：" + e);
			mu.setMessageCode("1");
			return mu;
		}
		// 判断cusId是否存在
		if (cusId == null) {
			mu.setMessageStr("token过期");
			mu.setMessageCode("2");
			return mu;
		}
		try {
			usedVehicleSellsDao.upDateMyUsedVehicle(String.valueOf(cusId),
					isSell, uId);
			mu.setMessageCode("0");
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("更新发布状态失败：" + e);
			mu.setMessageStr("更新发布状态失败：" + e);
			mu.setMessageCode("1");
		}
		return mu;
	}

	@Override
	public MessageUntil<String> deleteMyUsedVehicle(HttpServletRequest request) {
		// TODO Auto-generated method stub
		MessageUntil<String> mu = new MessageUntil<String>();
		String token = request.getParameter("token");
		String uId = request.getParameter("uId");
		Integer cusId = null;
		try {
			cusId = tokenDao.selectByToken(token);

		} catch (Exception e) {
			// TODO: handle exception
			logger.info("下架二手车失败：" + e);
			mu.setMessageStr("下架二手车失败：" + e);
			mu.setMessageCode("1");
			return mu;
		}
		// 判断cusId是否存在
		if (cusId == null) {
			mu.setMessageStr("token过期");
			mu.setMessageCode("2");
			return mu;
		}
		try {
			// !!!服务器图片的未做物理删除
			usedVehicleSellsDao.deleteMyUsedVehicle(String.valueOf(cusId), uId);
			usedVehicleSellsDao.deleteMyUsedVehiclePic(uId);
			mu.setMessageCode("0");
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("删除发布信息失败" + e);
			logger.info("删除发布信息失败" + e);
		}
		return mu;
	}

}
