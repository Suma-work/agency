package com.sumainfo.agency.service.impl;


import java.io.InputStream;
import java.io.UnsupportedEncodingException;
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
import org.springframework.web.multipart.MultipartFile;
import com.sumainfo.agency.dao.CustomerDao;
import com.sumainfo.agency.dao.ShopMapper;
import com.sumainfo.agency.dao.TokenDao;
import com.sumainfo.agency.dao.VehicleOrdersDao;
import com.sumainfo.agency.service.VehicleOrdersService;
import com.sumainfo.common.entity.ContactUserMs;
import com.sumainfo.common.entity.Customer;
import com.sumainfo.common.entity.ShopComment;
import com.sumainfo.common.entity.SmsReturn;
import com.sumainfo.common.entity.VehicleOrders;
import com.sumainfo.common.sms.SmsPullResponse;
import com.sumainfo.common.sms.SmsResponse;
import com.sumainfo.common.sms.SmsSend;
import com.sumainfo.common.sms.SmsPullResponse.Result;
import com.sumainfo.common.until.ComUtils;
import com.sumainfo.common.until.MessageUntil;
import com.sumainfo.common.until.StringUntil;
import com.sumainfo.common.until.ToolsUntil;
import com.sumainfo.common.until.UploadPicUntil;



@Service
public class VehicleOrdersServiceImpl implements VehicleOrdersService {
	Logger logger = LoggerFactory.getLogger(VehicleOrdersServiceImpl.class);
	@Autowired
	private VehicleOrdersDao vehicleOrdersDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private TokenDao tokenDao;
	@Autowired
	private ShopMapper shopDao;
	
	//查询预约信息
	public MessageUntil<HashMap<String, Object>> findVehicleOrderMessage(HttpServletRequest request){
		MessageUntil<HashMap<String, Object>> mu = new MessageUntil<HashMap<String, Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		//类型分类 0默认自己 1他人
        String classify = request.getParameter("classify");
        //token
        String token = request.getParameter("token");
        //分页
        String page = request.getParameter("page");
        //0 待同意 1已预约 2已看车 3已取消 4已购买
        String status = request.getParameter("status");
        String phone;
        Integer cusId;
        try {
        	cusId = tokenDao.selectByToken(token);
        	if(null==cusId){
             	mu.setMessageCode("2");
     			mu.setMessageStr("token失效");
     			return mu;
             }
        	phone = customerDao.findCusById(cusId);
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("查询预约数据异常");
			logger.info("查询预约数据异常"+e);
			return mu;
		}
        
        if(classify.equals("0")){ 
        	//0查询自己预约的信息
        	try {
        		List<HashMap<String, Object>> orderList = vehicleOrdersDao.findOrderVehicles(cusId,phone, status, null);
        		Integer counts = vehicleOrdersDao.findOrderVehiclesNum(cusId,phone, status, null);
        		mu.setMessageCode("0");
        		map.put("orderList", orderList);
        		map.put("counts", counts);
        		mu.setData(map);
			} catch (Exception e) {
				// TODO: handle exception
				mu.setMessageCode("1");
				mu.setMessageStr("查询预约失败");
				logger.info("查询预约失败"+e);
			}
        	
        }else{
        	//查询替别人预约的信息
        	try {
        		List<HashMap<String, Object>> orderList = vehicleOrdersDao.findOrderVehicles(cusId,phone, status, "1");
        		Integer counts = vehicleOrdersDao.findOrderVehiclesNum(cusId,phone, status, "1");
        		mu.setMessageCode("0");
        		map.put("orderList", orderList);
        		map.put("counts", counts);
        		mu.setData(map);
			} catch (Exception e) {
				// TODO: handle exception
				mu.setMessageCode("1");
				mu.setMessageStr("查询预约失败");
				logger.info("查询预约失败"+e);
			}
        }
        
       
		return mu;
	}

	
	//预约看车信息插入
	@Override
	public MessageUntil<String> saveVehicleOrder(
			HttpServletRequest request) {
		boolean flag = false;
		// TODO Auto-generated method stub
		MessageUntil<String> mu = new MessageUntil<String>();
		
		//看车日期
		String orderDate = request.getParameter("orderDate");
		//看车时间
		String orderTime = request.getParameter("orderTime");
		//汽车的品牌+车型名称
		String carName = request.getParameter("carName");
		//汽车的具体名称
		String carDetName = request.getParameter("carDetName");
		//联系人主键id
		int uid = Integer.valueOf(request.getParameter("uid"));
		//看车人名称
		String viewerName = request.getParameter("viewerName");
		//看车人手机号
		String viewerPhone = request.getParameter("viewerPhone");
		//店铺id
		String shopId = request.getParameter("shopId");
		//车辆表的唯一id
		String keyId = request.getParameter("keyId");
		//区分车的所属店铺 0 个人 1 机构 2 4s店
		String classify = request.getParameter("classify");
		//订单编码
		String orderCode = ComUtils.randomUID("vo");
		//获取token
		String token = request.getParameter("token");
		//创建时间
		Date createTime = new Date();
		String status = "0";
		//客户的id是根据token查询的
		Integer cusId = null;
		try {
			cusId = tokenDao.selectByToken(token);
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("查询错误信息："+e);
		}
		//判断是否是客户端本人操作预约 
		if(null !=cusId){
			if(customerDao.findCusById(cusId).equals(viewerPhone)){
				status = "1";
			}
		}else{
			mu.setMessageStr("获取客户信息错误，预约失败");
			mu.setMessageCode("1");
			return mu;
		}
		
		VehicleOrders order = 
				new VehicleOrders(orderDate, orderTime, cusId,status,uid, orderCode, viewerName, viewerPhone, shopId, carName, carDetName, keyId, classify, createTime);
		try {
			vehicleOrdersDao.saveVehicleOrder(order);
			//若是客户端本人操作不发送短信
			if(status.equals("0")){
				flag=true;
			}
			
			mu.setMessageCode("0");
		} catch (Exception e) {
			// TODO: handle exceptionr
			flag =false;
			mu.setMessageCode("1");
			mu.setMessageStr("保存订单信息错误");
			logger.info("插入订单信息报错："+e);
		}
		//发送短信
		if(flag){
			//获取6位随机数
			String identifyCode = StringUntil.getRandom(6);
			//参数组																
			String params = viewerPhone+",客户";
			//发送短信到注册的手机
			SmsResponse response;
			try {
				response = SmsSend.SendTextCode(ToolsUntil.PULL_MSG,params,ToolsUntil.MARKET_ACCOUNT,ToolsUntil.MARKET_PS,ToolsUntil.VARIABLE_URL);
				boolean flags = response.getFailNum().equals("0")?true:false;
				
			} catch (UnsupportedEncodingException e) {
				logger.info("短信发送失败："+e);
			}
			
		}

        return mu;
	}

	@Override
	public void smsNotify() {
		// TODO Auto-generated method stub
	    try {
	    	//获取上行数据（用户用手机回复的短信信息）
	    	SmsPullResponse smsPullResponse = SmsSend.pullSms(ToolsUntil.MARKET_ACCOUNT,ToolsUntil.MARKET_PS);
			List<Result> list = smsPullResponse.getResult();
			List<SmsReturn> smsList = new ArrayList<SmsReturn>();
			if(list.size()>0){
				for(Result result:list){
					//上行时间、平台通道码、上行号码、运营商通道码、上行内容
					SmsReturn sms = 
						new SmsReturn(result.getMoTime(), result.getSpCode(), result.getMobile(), result.getDestCode(), result.getMessageContent());
					smsList.add(sms);
				}
//				logger.info("list的长度:"+smsList.size()+",回复"+smsList.get(0).getMobile());
				try {
					if(smsList.size()>0){
						vehicleOrdersDao.saveSmsBackMessage(smsList);
					}
					
				} catch (Exception e) {
					logger.info("插入数据失败："+e);
				}
				
			}
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			logger.info("获取上行信息失败:"+e);
		}
		
	}


	//评论4s店
	@Override
	public MessageUntil<String> comment4sShop(HttpServletRequest request,MultipartFile[] files) {
		// TODO Auto-generated method stub
		MessageUntil<String> mu = new MessageUntil<String>();
		String token = request.getParameter("token");
		String classify = request.getParameter("classify");
		String shopId = request.getParameter("shopId");
		String environmentRate = request.getParameter("environmentRate");
		String serviceRate = request.getParameter("serviceRate");
		String skillRate = request.getParameter("skillRate");
		String comment = request.getParameter("comment");
		//判断是否是有关4S店的评论
		if(!classify.equals("2")){
			mu.setMessageCode("3");
			mu.setMessageStr("不能评价非4S店的店铺");
			return mu;
		}
        
        //判断环境评分、技能评分、服务评分是否为空值
        int enRate = 0;
        int seRate = 0;
        int skRate = 0;
        if(environmentRate!=null&&!environmentRate.equals("")){
        	enRate = Integer.valueOf(environmentRate);
        }
        if(serviceRate!=null&&!serviceRate.equals("")){
        	seRate = Integer.valueOf(serviceRate);
        }
        if(skillRate!=null&&!skillRate.equals("")){
        	skRate = Integer.valueOf(skillRate);
        }
        
    	//判断token值是否失效
        Integer cusId;
        String name;
        try {
        	cusId = tokenDao.selectByToken(token);
        	if(null==cusId){
             	mu.setMessageCode("2");
     			mu.setMessageStr("token失效");
     			return mu;
             }
        	String phone = customerDao.findCusById(cusId);
        	Customer cus = customerDao.loginAccount(phone);
        	name = cus.getNickName();
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("评价4S店异常");
			logger.info("评价4S店异常"+e);
			return mu;
		}
        //获取图图片地址
        String[] picAd = new String[3];
    	for(int i=0;i<files.length;i++){
			InputStream is;
			try{
				is = files[i].getInputStream();
			    String pic = UploadPicUntil.uploadPic(is,ToolsUntil.PATH_URL,files[i].getOriginalFilename());
			    String url = ToolsUntil.PICTURE_URL+pic;
			    picAd[i] = url;
			}catch (Exception e) {
				mu.setMessageCode("2");
				logger.info("图片上传失败:"+e);
				return mu;
			}
		 }
    	String picAd1= null;
    	String picAd2= null;
    	String picAd3= null;
    	if(null!=picAd[0]){
    		picAd1 = picAd[0];
    	}
    	if(null!=picAd[1]){
    		picAd2 = picAd[1];
    	}
    	if(null!=picAd[2]){
    		picAd3 = picAd[2];
    	}
        
    	//其中的 1标识评价的是4s店
        ShopComment sc = 
        		new ShopComment(cusId, name, enRate, seRate, skRate, comment, new Date(), shopId, "1", picAd1, picAd2,picAd3);
        try {
			shopDao.saveShopComments(sc);
			mu.setMessageCode("0");
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("保存评价4s店失败"+e);
			logger.info("保存评价4s店失败"+e);
		}
		return mu;
	}


	@Override
	public MessageUntil<Map<String, Object>> findContactMs(HttpServletRequest request) {
		// TODO Auto-generated method stub
		MessageUntil<Map<String, Object>> mu = new MessageUntil<Map<String,Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		String shopId = request.getParameter("shopId");
		List<ContactUserMs> cuList = vehicleOrdersDao.findContactMs(shopId);
		map.put("cuList", cuList);
		mu.setData(map);
		mu.setMessageCode("0");
		return mu;
	}

	
	

}
