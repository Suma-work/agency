package com.sumainfo.agency.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.sumainfo.agency.dao.ComplaintManagementDao;
import com.sumainfo.agency.dao.CustomerDao;
import com.sumainfo.agency.dao.ShopMapper;
import com.sumainfo.agency.dao.TokenDao;
import com.sumainfo.agency.dao.UsedVehicleSellsDao;
import com.sumainfo.agency.service.ComplaintManagementService;
import com.sumainfo.common.distance.ComputedRange;
import com.sumainfo.common.entity.ComplaintManagement;
import com.sumainfo.common.entity.ComplaintMs;
import com.sumainfo.common.entity.PictureSave;
import com.sumainfo.common.entity.complaintReply;
import com.sumainfo.common.until.ConvertDateTime;
import com.sumainfo.common.until.JsonResult;
import com.sumainfo.common.until.MessageUntil;
import com.sumainfo.common.until.StaticConstants;
import com.sumainfo.common.until.ToolsUntil;
import com.sumainfo.common.until.UploadPicUntil;


/**
 * 店铺评论和投诉
 * @author Administrator
 *
 */
@Service
public class ComplaintManagementServiceImpl implements
		ComplaintManagementService {
	Logger logger = LoggerFactory
			.getLogger(ComplaintManagementServiceImpl.class);
	@Autowired
	private ComplaintManagementDao complaintManagementDao;
	@Autowired
	private UsedVehicleSellsDao usedVehicleSellsDao;
	@Autowired
	private ShopMapper shopMapper;
	@Autowired
	private TokenDao tokenDao;
	@Autowired
	private CustomerDao customerDao;

	//保存客户对4s店的投诉信息
	public MessageUntil<String> saveComplaintMessage(HttpServletRequest request, MultipartFile[] file) {
		MessageUntil<String> mu = new MessageUntil<String>();
        String token = request.getParameter("token");
		String shopId = request.getParameter("shopId");
		String complaintType = request.getParameter("complaintType");
		String happenedTime = request.getParameter("happenedTime");
		String reasons = request.getParameter("reasons");
		Date createTime = new Date();
		String dealSchedule = "1";//未受理
		//判断token是否失效
		Integer cusId = null;
		String shopName = null;
		String cusName = null;
		try {
			 cusId = tokenDao.selectByToken(token);
			 shopName = shopMapper.selectNameByShopId(shopId);
			 if(null==cusId){
				 mu.setMessageCode("2");
				 mu.setMessageStr("登录失效");
				 return mu;
			 }
			 cusName = customerDao.selectNameById(cusId);
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("投诉店铺异常："+e);
		}
		
		ComplaintManagement complaint = new ComplaintManagement(cusId, cusName, shopId, shopName, complaintType,happenedTime,dealSchedule, reasons, createTime);
		try {
			//保存客户投诉信息
			int results = complaintManagementDao
					.saveComplaintMessage(complaint);
			mu.setMessageCode("0");
			logger.info("插入数据返回值:" + complaint.getId());
			//如果照片存在
			if(file.length>0){
				ArrayList<PictureSave> list = new ArrayList<PictureSave>();
				if (results > 0) {
					for (int i = 0; i < file.length; i++) {
						PictureSave vp = null;
						InputStream is;
						try {
							vp = new PictureSave();
							is = file[i].getInputStream();
							String pic = UploadPicUntil.uploadPic(is,ToolsUntil.PATH_URL,file[i].getOriginalFilename());
							String url = ToolsUntil.PICTURE_URL + pic;
							vp.setPicAddress(url);
							vp.setAssociationId(String.valueOf(complaint.getId()));
							vp.setClassify("3");// 客户投诉
							vp.setCreateTime(new Date());
							list.add(vp);
						} catch (Exception e) {
							mu.setMessageCode("2");
							logger.info("图片上传失败:" + e);
						}
					}
	
					//保存图片信息
					try {
						usedVehicleSellsDao.savePictureList(list);
						mu.setMessageStr("投诉成功");
					} catch (Exception e) {
						logger.info("保存图片信息失败:" + e);
						mu.setMessageCode("1");
						mu.setMessageStr("客户信息保存出错，请稍后重试");
						//如果图片上传失败删除投诉信息
						complaintManagementDao.deleteComplaintMessage(complaint
								.getId());
					}
				}else{
					mu.setMessageCode("1");
					mu.setMessageStr("投诉信息未保存到数据库，数据库异常");
				}
			}

		} catch (Exception e) {
			mu.setMessageCode("1");
			logger.info("保存客户投诉信息报错:" + e);
		}

		return mu;
	}
	
	//查询自己的投诉的list
	@Override
	public MessageUntil<HashMap<String, Object>> findComplaintMessage(HttpServletRequest request) {
		MessageUntil<HashMap<String, Object>> mu = new MessageUntil<HashMap<String,Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		String token = request.getParameter("token");
		String page = request.getParameter("page");
		Integer cusId = null;
		Integer pageNum = 10;
		if(!page.equals("")&&null!=page){
			pageNum = pageNum*Integer.valueOf(page);
		}
		try {
			 cusId = tokenDao.selectByToken(token);
			 if(null==cusId){
				 mu.setMessageCode("2");
				 mu.setMessageStr("登录失效");
				 return mu;
			 }
			List<HashMap<String, Object>> complaintList = complaintManagementDao.findComplaintList(cusId, pageNum);
			Integer maxNum = complaintManagementDao.findComplaintListNum(cusId);
			map.put("complaintList", complaintList);
			map.put("maxNum", maxNum);
			mu.setData(map); 
			mu.setMessageCode("0");
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("查询报错失败");
			logger.info("投诉查询异常："+e);
		}
		// TODO Auto-generated method stub
		return mu;
	}
	
	
	//获取客户自己的投诉追加信息
	@Override
	public MessageUntil<HashMap<String, Object>> findComplaintDetMessage(
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		MessageUntil<HashMap<String, Object>> mu = new MessageUntil<HashMap<String,Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		String token = request.getParameter("token");
		Integer id = Integer.valueOf(request.getParameter("id"));
		Integer cusId = null;
		try {
			 cusId = tokenDao.selectByToken(token);
			 if(null==cusId){
				 mu.setMessageCode("2");
				 mu.setMessageStr("登录失效");
				 return mu;
			 }
			ComplaintMs myComplaint = complaintManagementDao.findComplaintMain(cusId, id);
			List<HashMap<String, Object>> replyList = complaintManagementDao.findMyReply(id);
			List<String> picList = complaintManagementDao.findMyComplaintPic(request.getParameter("id"));
			map.put("myComplaint", myComplaint);
			map.put("replyList", replyList);
			map.put("picList", picList);
			mu.setData(map); 
			mu.setMessageCode("0");
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("查询报错失败");
			logger.info("投诉查询异常："+e);
		}
		return mu;
	}
	
	@Override
	public MessageUntil<String> saveReply(HttpServletRequest request,MultipartFile[] files) {
		// TODO Auto-generated method stub
		MessageUntil<String> mu = new MessageUntil<String>();
		String token = request.getParameter("token");
		Integer parentId = Integer.valueOf(request.getParameter("parentId"));
		String comtent = request.getParameter("comtent");
        Integer cusId = null;
		
		try {
			cusId = tokenDao.selectByToken(token);
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("验证token登录信息异常");
			logger.info("验证token登录信息异常:"+e);
			return mu;
		}
		if(cusId ==null){
			mu.setMessageCode("2");
			mu.setMessageStr("token失效");
			return mu;
		}
		//获取文件图片
		List<String> picUrl = new ArrayList<String>();
		try {
			if(files.length>0){
				 for(MultipartFile file:files){	
					 InputStream is = file.getInputStream();
					 String pic = UploadPicUntil.uploadPic(is,ToolsUntil.PATH_URL,file.getOriginalFilename());
					 String url = ToolsUntil.PICTURE_URL+pic;
					 picUrl.add(url);
				 }
			}
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("获取图片失败");
			logger.info("获取图片失败"+e);
			return mu;
		}
		
		String pic1 =null;
		String pic2 =null;
		String pic3 =null;
		if(picUrl.size()==0){
			
		}else if(picUrl.size()==1){
			pic1= picUrl.get(0);
		}else if(picUrl.size()==2){
			pic1= picUrl.get(0);
			pic2= picUrl.get(1);
		}else{
			pic1= picUrl.get(0);
			pic2= picUrl.get(1);
			pic3= picUrl.get(2);
		}
		
		try {
			complaintReply re = new complaintReply("1", parentId, comtent, cusId, new Date(), pic1, pic2, pic3);
			complaintManagementDao.saveReply(re);
			mu.setMessageCode("0");
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("保存投诉回复信息失败");
			logger.info("保存投诉回复信息失败"+e);
		}
		return mu;
	}
	
	//解决方案的同意与否
	public MessageUntil<String> overCom(HttpServletRequest request){
		MessageUntil<String> mu = new MessageUntil<String>();
		String isRecept = request.getParameter("isRecept");
		String id = request.getParameter("id");
		Date overTime = new Date();
		try {
			complaintManagementDao.overCom(Integer.valueOf(id), isRecept, overTime);
			mu.setMessageCode("0");
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("数据处理异常");
			logger.info("数据处理异常"+e);
		}
		return mu;
	}
	
	
	public Integer getColletListCout(Map<String,Object>params){
		return complaintManagementDao.getColletListCout(params);
	}
	
	/**
	 * 获取用户的收藏的店铺
	 * @author:zhlu
	 * @date: 2018年2月27日
	 * @param params
	 * @return
	 */
	public List<Map<String,Object>> getColletList(Map<String, Object> params) {
		List<Map<String,Object>>result=new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> collerctList = complaintManagementDao.getColletList(params);
		for (Map<String, Object> map : collerctList) {
			params.put("shopId", map.get("associationId"));
			if("1".equals(params.get("classify").toString())){
				List<Map<String,Object>> shopList=complaintManagementDao.getShopList(params);
				for (Map<String, Object> map1 : shopList) {
					//获取店铺主键
					params.put("shopId", map1.get("shopId"));
					Map<String, Object>ShopComment=shopMapper.getSumShopcomment(params);
					//得到这个店铺的评价分
					if(!StringUtils.isEmpty(ShopComment.get("rate"))){
						map1.put("rate", ShopComment.get("rate"));
					}else{
						map1.put("rate",0);
					}
					//得到店铺展示图片slideshow为3
					Map<String, Object>PictureSave=shopMapper.getPicturesave(params);
					if(PictureSave !=null && PictureSave.containsKey("picAddress")&&!StringUtils.isEmpty(PictureSave.get("picAddress"))){
						map1.put("picAddress",PictureSave.get("picAddress"));
					}else{//没有就给个默认的
						map1.put("picAddress","http://www.sumainfor.com/wp-content/uploads/2017/12/logo2-1.png");
					}
					if(StringUtils.isEmpty(map.get("serveMold"))){
						map1.put("serveMold","");
					}
					result.add(map1);
				}
			}else if("2".equals(params.get("classify").toString())){
				List<Map<String,Object>> shopList=complaintManagementDao.getShopListEl(params);
				for (Map<String, Object> map1 : shopList) {
					//获取店铺主键
					params.put("shopId", map1.get("shopId"));
					//得到店铺展示图片3
					Map<String, Object>PictureSave=shopMapper.getPicturesave(params);
					if(PictureSave !=null && PictureSave.containsKey("picAddress")&&!StringUtils.isEmpty(PictureSave.get("picAddress"))){
						map1.put("picAddress",PictureSave.get("picAddress"));
					}else{//没有就给个默认的
						map1.put("picAddress","http://www.sumainfor.com/wp-content/uploads/2017/12/logo2-1.png");
					}
					
					Map<String, Object>ShopComment=shopMapper.getSumShopcomment(params);
					//得到这个店铺的评价分
					if(!StringUtils.isEmpty(ShopComment.get("rate"))){
						map1.put("rate", ShopComment.get("rate"));
					}else{
						map1.put("rate",0);
					}
					map1.put("count", ShopComment.get("count"));
					
					double distance = ComputedRange.getDistance(Double.parseDouble(map1.get("lat").toString()),Double.parseDouble(map1.get("lon").toString()),Double.parseDouble(params.get("lat").toString()),Double.parseDouble(params.get("lon").toString()));
					double distances=distance/1000;
					//用户和这个店铺的距离
					map1.put("distance",String.format("%.2f",distances));
					result.add(map1);
				}
			}
		}
		return result;
	}
	
	/**
	 * 根据用户编号获取用户评价
	 */
	public List<Map<String, Object>> getClientEva(Map<String, Object> params)throws Exception {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>>custMap=complaintManagementDao.getCustomer(params);
		for (Map<String, Object> map : custMap) {
			Map<String,Object> commentMap=new HashMap<String,Object>();
			//获取用户的编号和用户的评价分组时间
			params.put("cusId", map.get("cusId"));
			params.put("createTime", map.get("createTime").toString());
			List<Map<String,Object>>shopComment=complaintManagementDao.getShopComment(params);
			List<Map<String,Object>>comList=new ArrayList<Map<String,Object>>();
			for (Map<String, Object> map2 : shopComment) {
				params.put("shopId", map2.get("shopId"));
				params.put("comid", map2.get("comid"));
				Map<String,Object>comMap=new HashMap<String,Object>();
				Map<String,Object>shopMap=complaintManagementDao.Shop(params);
				Map<String,Object>shopRep=complaintManagementDao.getShopRep(params);
				logger.info("shopRep->>>>>>"+shopRep);
				comMap.put("cusId", map2.get("cusId"));
				comMap.put("cusName", map2.get("cusName"));
				if(!StringUtils.isEmpty(map2.get("portait"))){
					comMap.put("portait", map2.get("portait"));
				}else{
					if("0".equals(map2.get("sex").toString())){
						comMap.put("portait",StaticConstants.publicWomanPortaitUrl);
					}else{
						comMap.put("portait",StaticConstants.publicManPortaitUrl);
					}
				}
//				comMap.put("portait", map2.get("portait"));
				Double meanScore=(Double.parseDouble(map2.get("environmentRate").toString())+Double.parseDouble(map2.get("environmentRate").toString())+Double.parseDouble(map2.get("environmentRate").toString()))/3;
				comMap.put("mean",meanScore);
				
				comMap.put("shopName",shopMap.get("shopName"));
				comMap.put("comment", map2.get("comment"));
				String[] a=map2.get("imgs").toString().split(";");
//				List<Map<String,Object>>imgList=new ArrayList<Map<String,Object>>();
//				for (int i = 0; i < a.length; i++) {
//					Map<String, Object> imgs = new HashMap<>();
////				    imgs.put("img",ConstantsPos.publicQiniuUrl + "/"  + a[i]);
//				    imgs.put("imgkey",a[i]);
//				    imgList.add(imgs);
//				}
				if(a!=null && a.length>0){
					comMap.put("imgs",a);
				}else{
					comMap.put("imgs",new ArrayList<>());
				}
				
				//商家回复
				if(shopRep==null || shopRep.size()<1){
					comMap.put("shopComment","");
				}else{
					comMap.put("shopComment",shopRep.get("comment"));
				}
				//店铺类型
//				if(shopRep==null || shopRep.size()<1){
//					comMap.put("classify","");
//				}else{
//					comMap.put("classify",shopRep.get("classify"));
//				}
				comMap.put("classify",map2.get("classify"));
				comList.add(comMap);
			}
			Map<String,Object>comm=new HashMap<String,Object>();
//			String key =map.get("createTime").toString();
//			int dateDiff = ConvertDateTime.subDate(ConvertDateTime.getCurDate(), ConvertDateTime.dateFormatStrToTime(ConvertDateTime.FORMAT_ONLY_DATE_EN, key),3);
//			if(dateDiff == 0){
//				comm.put("今日", comList);
//			}else if(dateDiff == 1){
//				comm.put("昨天", comList);
//			}else{
//			comm.put(map.get("createTime").toString(), comList);
//			}
			Map<String,Object>commMap=new HashMap<String,Object>();
			commMap.put("createTime", map.get("createTime"));
			commMap.put("commMap",comList);
			result.add(commMap);
		}
		
		return result;
	}

	/**
	 * 根据token获取用户编号
	 */
	public Map<String, Object> getToken(Map<String, Object> params) {
		return complaintManagementDao.getToken(params);
	}


	/**
	 * 根据用户编号获取用户的评价总数
	 */
	public List<Map<String,Object>> getCustomerCont(Map<String, Object> params) {
		return complaintManagementDao.getCustomerCont(params);
	}











}
