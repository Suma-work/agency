package com.sumainfo.agency.service.impl;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sumainfo.agency.dao.CustomerDao;
import com.sumainfo.agency.dao.TokenDao;
import com.sumainfo.agency.service.CustomerService;
import com.sumainfo.common.entity.Customer;
import com.sumainfo.common.entity.MyCar;
import com.sumainfo.common.entity.ThrLogin;
import com.sumainfo.common.entity.Token;
import com.sumainfo.common.redis.RedisClient;
import com.sumainfo.common.sms.SmsResponse;
import com.sumainfo.common.sms.SmsSend;
import com.sumainfo.common.until.Md5Until;
import com.sumainfo.common.until.MessageUntil;
import com.sumainfo.common.until.StringUntil;
import com.sumainfo.common.until.ToolsUntil;
import com.sumainfo.common.until.UploadPicUntil;

@Service
public class CustomerServiceImpl implements CustomerService {
	Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private TokenDao tokenInfoDao;

	/**
	 * 已知原密码修改密码 code值 1一般错误 2token失效
	 */
	public MessageUntil<String> knowPsModifyPs(HttpServletRequest request) {
		MessageUntil<String> mu = new MessageUntil<String>();
		String token = request.getParameter("token");
		String oldPs = request.getParameter("oldPs");
		String newPs = request.getParameter("newPs");
		try {
			oldPs = Md5Until.encryPassWord(oldPs);
			newPs = Md5Until.encryPassWord(newPs);
			Customer cus = tokenInfoDao.findCustomerMs(token);
			if (cus == null) {
				mu.setMessageCode("1");
				return mu;
			}
			if (cus.getPassWord().equals(oldPs)) {
				customerDao.updateCustomer(cus.getCellPhone(), newPs);
				mu.setMessageCode("0");
			} else {
				mu.setMessageCode("1");
				mu.setMessageStr("原密码输入错误，修改密码失败");
			}

		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
		}
		return mu;
	}

	/**
	 * 用户注册前的认证手机号 需要token
	 */
	public MessageUntil<String> identify(HttpServletRequest request) {
		MessageUntil<String> mu = new MessageUntil<String>();
		mu.setMessageCode("0");
		String cellPhone = request.getParameter("cellPhone");
		// 判断用户是否注册过
		Customer customer = customerDao.loginAccount(cellPhone);
		if (null != customer) {
			mu.setMessageCode("1");
			mu.setMessageStr("您已经注册,请不要重复注册操作");
		} else {
			// 获取6位随机数
			String identifyCode = StringUntil.getRandom(6);
			try {
				// 参数组
				String params = cellPhone + ",用户," + identifyCode + ",3";
				// 发送短信到注册的手机
				SmsResponse response = SmsSend.SendTextCode(
						ToolsUntil.VARIABLE_MSG, params,ToolsUntil.VARIABLE_ACCOUNT,ToolsUntil.VARIABLE_PS,ToolsUntil.VARIABLE_URL);
				boolean flag = response.getFailNum().equals("0") ? true : false;
				if (flag) {
					// 发送验证后的信息把值存入redis中
					RedisClient redisClient = new RedisClient();
					String token = Md5Until.encryPassWord(cellPhone
							+ System.currentTimeMillis());
					redisClient.setString(token, identifyCode, 180);// 设置的token有效时间为3分钟
					logger.info("token的值是：" + token);
					mu.setData(token);
				} else {
					mu.setMessageCode("1");
					mu.setMessageStr("发送验证码失败，请稍后重试");
				}
			} catch (Exception e) {
				mu.setMessageCode("2");
				mu.setMessageStr("发送异常" + e);
			}

		}

		return mu;

	}

	/**
	 * 注册用户
	 */
	public MessageUntil<String> register(HttpServletRequest request) {
		MessageUntil<String> mu = new MessageUntil<String>();
		mu.setMessageCode("0");
		String cellPhone = request.getParameter("cellPhone");
		String token = request.getParameter("token");
		String mdCode = request.getParameter("mdCode");
		String mdPassWord = Md5Until.encryPassWord(request
				.getParameter("passWord"));
		RedisClient redisClient = new RedisClient();
		if (null == redisClient.getString(token)) {
			mu.setMessageCode("1");
			mu.setMessageStr("验证码失效，请重新获取验证码");
			return mu;
		}
		if (!mdCode.equals(redisClient.getString(token))) {
			logger.info("前台传过来的验证码：" + mdCode + "--,--" + "后台redis存的验证码："
					+ redisClient.getString(token));
			mu.setMessageCode("1");
			mu.setMessageStr("验证码错误，请填写正确的验证码");
		} else {
			String nickName = "匿名车神" + StringUntil.getRandom(4);
			Customer customer = new Customer(nickName, mdPassWord, cellPhone,
					new Date(), new Date());
			try {
				int num = customerDao.saveOrUpdate(customer);
				redisClient.delStr(token);// 注册成功后立刻删除token
				if (num <= 0) {
					mu.setMessageCode("2");
					mu.setMessageStr("注册失败，请稍后重试");
				}

			} catch (Exception e) {
				mu.setMessageCode("2");
				mu.setMessageStr("注册异常，请稍后重试");
				logger.info("登录异常错误：" + e);
			}
		}

		return mu;
	}

	/**
	 * 用户登录
	 */
	public MessageUntil<String> loginAccount(HttpServletRequest request) {
		MessageUntil<String> mu = new MessageUntil<String>();
		String classify = request.getParameter("classify");
		// 第三方登录 1qq 2微信 3微博 0手机号
		if (Integer.valueOf(classify) > 0) {
			String uniqueId = request.getParameter("uniqueId");
			if (thrLogin(uniqueId)) {
				String phone = customerDao.identityThrLogin(uniqueId);
				// 判断客户账户是否被冻结
				if (customerDao.findStatusNum(phone, 1) > 0) {
					mu.setMessageCode("1");
					mu.setMessageStr("户账冻结,请联系管理员");
					return mu;
				}
				Customer customer = customerDao.loginAccount(phone);
				// 用户登录token直接存入数据库，此处不在redis中做缓存
				String token = Md5Until.encryPassWord(phone
						+ System.currentTimeMillis());
				Token tokens = new Token(customer.getCusId(), token);
				tokenInfoDao.updateToken(tokens);
				mu.setData(token);
				mu.setMessageCode("0");
			} else {
				mu.setMessageCode("4");
				mu.setMessageStr("未绑定手机号,无法登录");
			}
		} else {
			String cellPhone = request.getParameter("cellPhone");
			String mdPassWord = Md5Until.encryPassWord(request
					.getParameter("passWord"));
			// 查询数据是否存在
			try {
				Customer customer = customerDao.loginAccount(cellPhone);
				if (null != customer.getCellPhone()) {
					// 判断客户账户是否被冻结
					if (customerDao.findStatusNum(customer.getCellPhone(), 1) > 0) {
						mu.setMessageCode("1");
						mu.setMessageStr("户账冻结,请联系管理员");
						return mu;
					}
					logger.info("数据库查询的密码:" + customer.getPassWord()
							+ "前台传入的密码:" + mdPassWord);
					if (!customer.getPassWord().equals(mdPassWord)) {
						mu.setMessageCode("1");
						mu.setMessageStr("密码错误");
					} else {
						// 用户登录token直接存入数据库，此处不在redis中做缓存
						String token = Md5Until.encryPassWord(cellPhone
								+ System.currentTimeMillis());
						Token tokens = new Token(customer.getCusId(), token);
						tokenInfoDao.updateToken(tokens);
						mu.setData(token);
						mu.setMessageCode("0");
					}
				} else {
					mu.setMessageCode("1");
					mu.setMessageStr("您的账号未注册，请进行账号注册");
				}

			} catch (Exception e) {
				mu.setMessageCode("2");
				mu.setMessageStr("查询数据出错" + e);
				logger.info("查询数据出错" + e);
			}
		}
		return mu;
	}

	// 绑定手机号 绑定成功后直接登录
	public MessageUntil<String> bandPhone(HttpServletRequest request) {
		MessageUntil<String> mu = new MessageUntil<String>();
		// 首先判断该手机号是否存在或者未锁
		String token = request.getParameter("token");
		// TODO Auto-generated method stub
		Integer cusId = null;
		try {
			cusId = tokenInfoDao.selectByToken(token);
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("验证token登录信息异常");
			logger.info("验证token登录信息异常:" + e);
			return mu;
		}
		if (null == cusId) {
			mu.setMessageCode("2");
			mu.setMessageStr("token失效");
			return mu;
		}
		String phone = customerDao.findCusById(cusId);
		String type = request.getParameter("type");
		// 0绑定 1解绑
		if (type.equals("1")) {
			String uniqueId = request.getParameter("uniqueId");
			try {
				customerDao.deleteThr(uniqueId);
				mu.setMessageCode("0");
			} catch (Exception e) {
				// TODO: handle exception
				mu.setMessageCode("1");
				mu.setMessageStr("解除绑定失败");
				logger.info("解除绑定失败" + e);
			}
		} else {
			if (phone == null) {
				mu.setMessageCode("1");
				mu.setMessageCode("手机号未注册，请您先用手机注册账户");
				return mu;
			} else {
				String classify = request.getParameter("classify");
				String uniqueId = request.getParameter("uniqueId");
				ThrLogin thl = new ThrLogin(classify, phone, uniqueId,
						new Date());
				try {
					customerDao.saveThrLogin(thl);
					mu.setMessageCode("0");
				} catch (Exception e) {
					// TODO: handle exception
					mu.setMessageCode("1");
					mu.setMessageStr("绑定信息失败");
					logger.info("绑定信息失败" + e);
					return mu;
				}

			}
		}

		return mu;

	}

	/**
	 * 修改密码
	 */
	public MessageUntil<String> modifyPassword(HttpServletRequest request) {
		Boolean flag = false;
		MessageUntil<String> mu = new MessageUntil<String>();
		String cellPhone = request.getParameter("cellPhone");
		String token = request.getParameter("token");
		String mdCode = request.getParameter("mdCode");
		String mdPassWord = Md5Until.encryPassWord(request
				.getParameter("passWord"));
		RedisClient redisClient = new RedisClient();
		if (null != redisClient.getString(token)) {// 三分中内验证token是否存在
			if (mdCode.equals(redisClient.getString(token))) {// 判断验证码输入是否正确
				flag = true;
			} else {
				mu.setMessageCode("1");
				mu.setMessageStr("验证码错误，请填写正确的验证码");
			}

		} else {
			mu.setMessageCode("1");
			mu.setMessageStr("验证码失效，请重新获取验证码");
		}
		if (flag) {
			customerDao.updateCustomer(cellPhone, mdPassWord);
			mu.setMessageCode("0");
			redisClient.delStr(token);// 验证成功立刻删除token
		}
		return mu;
	}

	/**
	 * 忘记密码
	 */
	public MessageUntil<String> forgetPassword(HttpServletRequest request) {
		MessageUntil<String> mu = new MessageUntil<String>();
		String cellPhone = request.getParameter("cellPhone");
		// 判断用户是否注册过
		Customer customer = customerDao.loginAccount(cellPhone);
		if (null == customer) {
			mu.setMessageCode("1");
			mu.setMessageStr("您还未注册,请先注册");
		} else {
			// 获取6位随机数
			String identifyCode = StringUntil.getRandom(6);
			try {
				// 参数组
				String params = cellPhone + ",用户," + identifyCode + ",3";
				// 发送短信到注册的手机
				SmsResponse response = SmsSend.SendTextCode(
						ToolsUntil.VARIABLE_MSG, params,ToolsUntil.VARIABLE_ACCOUNT,ToolsUntil.VARIABLE_PS,ToolsUntil.VARIABLE_URL);
				boolean flag = response.getFailNum().equals("0") ? true : false;
				if (flag) {
					RedisClient redisClient = new RedisClient();
					String token = Md5Until.encryPassWord(cellPhone
							+ System.currentTimeMillis());
					redisClient.setString(token, identifyCode, 180);// 存储token三分钟的验证时间，如果三分钟未做下步操作token过期
					logger.info("修改密码token值：" + token);
					mu.setData(token);
					mu.setMessageCode("0");
				}
			} catch (Exception e) {
				mu.setMessageCode("1");
				mu.setMessageStr("发送验证码失败，请稍后重试");
			}

		}
		return mu;
	}

	/**
	 * 上传图像
	 */
	@Override
	public MessageUntil<String> uploadPic(HttpServletRequest request,
			MultipartFile file) {
		MessageUntil<String> mu = new MessageUntil<String>();
		String token = request.getParameter("token");
		// TODO Auto-generated method stub
		Integer cusId = null;
		try {
			cusId = tokenInfoDao.selectByToken(token);
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("验证token登录信息异常");
			logger.info("验证token登录信息异常:" + e);
			return mu;
		}
		if (null == cusId) {
			mu.setMessageCode("2");
			mu.setMessageStr("token失效");
			return mu;
		}

		if (null != file) {
			InputStream is;
			try {
				is = file.getInputStream();
				String pic = UploadPicUntil.uploadPic(is, ToolsUntil.PATH_URL,
						file.getOriginalFilename());
				String url = ToolsUntil.PICTURE_URL + pic;
				logger.info("图片地址" + url);
				customerDao.savaCusPic(cusId, url);
				mu.setMessageCode("0");
				mu.setData(url);
			} catch (Exception e) {
				// TODO: handle exception
				logger.info("上传图片失败:" + e);
				mu.setMessageCode("1");
				mu.setMessageStr("上传图片失败");
			}
		} else {
			mu.setMessageCode("1");
			mu.setMessageStr("上传的图片为空");
		}
		return mu;
	}

	// 退出登录
	@Override
	public MessageUntil<String> logout(HttpServletRequest request) {
		// TODO Auto-generated method stub
		MessageUntil<String> mu = new MessageUntil<String>();
		String token = request.getParameter("token");
		Integer cusId = null;
		try {
			cusId = tokenInfoDao.selectByToken(token);
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("验证token登录信息异常");
			logger.info("验证token登录信息异常:" + e);
			return mu;
		}
		// 退出登录操作如果查询出的cusId为null，直接默认退出
		if (null == cusId) {
			mu.setMessageCode("0");
		} else {
			try {
				customerDao.logoutAccount(token);
				mu.setMessageCode("0");
			} catch (Exception e) {
				// TODO: handle exception
				logger.info("退出登录异常：" + e);
				mu.setMessageCode("退出登录异常");
				mu.setMessageCode("1");
			}

		}
		return mu;
	}

	// 实名认证
	public MessageUntil<String> certification(HttpServletRequest request) {
		MessageUntil<String> mu = new MessageUntil<String>();
		String token = request.getParameter("token");
		String realName = request.getParameter("realName");
		String idNum = request.getParameter("idNum");
		Integer cusId = null;
		try {
			cusId = tokenInfoDao.selectByToken(token);
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("验证token登录信息异常");
			logger.info("验证token登录信息异常:" + e);
			return mu;
		}
		if (cusId == null) {
			mu.setMessageCode("2");
			mu.setMessageStr("token失效");
			return mu;
		}
		try {
			customerDao.certificationMessage(cusId, realName, idNum);
			mu.setMessageCode("0");
		} catch (Exception e) {
			logger.info("实名认证失败：" + e);
			mu.setMessageCode("实名认证失败");
			mu.setMessageCode("1");
		}
		return mu;
	}

	// 修改昵称
	public MessageUntil<String> modifyNikeName(HttpServletRequest request) {
		MessageUntil<String> mu = new MessageUntil<String>();
		String token = request.getParameter("token");
		String nickName = request.getParameter("nickName");
		Integer cusId = null;
		try {
			cusId = tokenInfoDao.selectByToken(token);
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("验证token登录信息异常");
			logger.info("验证token登录信息异常:" + e);
			return mu;
		}
		if (cusId == null) {
			mu.setMessageCode("2");
			mu.setMessageStr("token失效");
			return mu;
		}
		try {
			customerDao.modifyNikeName(cusId, nickName);
			mu.setMessageCode("0");
		} catch (Exception e) {
			logger.info("修改昵称失败：" + e);
			mu.setMessageCode("修改昵称失败");
			mu.setMessageCode("1");
		}
		return mu;
	}

	// 修改手机号前发送验证码到新手机号上
	public MessageUntil<String> sendMsgToNewPhone(HttpServletRequest request) {
		MessageUntil<String> mu = new MessageUntil<String>();
		String phone = request.getParameter("phone");
		// 获取6位随机数
		String identifyCode = StringUntil.getRandom(6);
		// 参数组
		String params = phone + ",用户," + identifyCode + ",3";
		// 加密验证码
		String mdCode = Md5Until.encryPassWord(identifyCode
				+ System.currentTimeMillis());
		// 发送短信到注册的手机
		SmsResponse response;
		boolean flag = false;
		try {
			response = SmsSend.SendTextCode(ToolsUntil.VARIABLE_MSG, params,ToolsUntil.VARIABLE_ACCOUNT,ToolsUntil.VARIABLE_PS,ToolsUntil.VARIABLE_URL);
			flag = response.getFailNum().equals("0") ? true : false;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (flag) {
			RedisClient redisClient = new RedisClient();
			redisClient.setString(identifyCode, mdCode, 180);// 存储token三分钟的验证时间，如果三分钟未做下步操作token过期
			logger.info("修改密码token值：" + identifyCode);
			mu.setMessageCode("0");
			mu.setData(mdCode);
		} else {
			mu.setMessageCode("1");
			mu.setMessageStr("短信发送失败");
		}
		return mu;

	}

	// 修改手机号
	public MessageUntil<String> modifyPhone(HttpServletRequest request) {
		MessageUntil<String> mu = new MessageUntil<String>();
		String token = request.getParameter("token");
		String phone = request.getParameter("phone");
		String mdCode = request.getParameter("mdCode");
		String msgNum = request.getParameter("msgNum");
		// 判断验证码是否正确或失效
		RedisClient redisClient = new RedisClient();
		if (null == redisClient.getString(msgNum)) {
			mu.setMessageCode("3");
			mu.setMessageStr("验证码输入错误");
			return mu;
		} else {
			if (!mdCode.equals(redisClient.getString(msgNum))) {
				mu.setMessageCode("2");
				mu.setMessageStr("验证码失效");
				return mu;
			}
		}
		Integer cusId = null;
		try {
			cusId = tokenInfoDao.selectByToken(token);
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("验证token登录信息异常");
			logger.info("验证token登录信息异常:" + e);
			return mu;
		}
		if (cusId == null) {
			mu.setMessageCode("2");
			mu.setMessageStr("token失效");
			return mu;
		}
		try {
			customerDao.modifyPhone(cusId, phone);
			mu.setMessageCode("0");
		} catch (Exception e) {
			logger.info("修改手机号失败：" + e);
			mu.setMessageCode("修改手机号失败");
			mu.setMessageCode("1");
		}
		return mu;
	}

	// 保存爱车信息
	public MessageUntil<String> addMyVehicle(HttpServletRequest request) {
		MessageUntil<String> mu = new MessageUntil<String>();
		String token = request.getParameter("token");
		String vehiclePicUrl = request.getParameter("vehiclePicUrl");
		String carTypeName = request.getParameter("carTypeName");
		String carNo = request.getParameter("carNo");
		String recognitionNo = request.getParameter("recognitionNo");
		String carEngineNo = request.getParameter("carEngineNo");
		String id = request.getParameter("id");
		Integer cusId = null;

		try {
			cusId = tokenInfoDao.selectByToken(token);
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("验证token登录信息异常");
			logger.info("验证token登录信息异常:" + e);
			return mu;
		}
		if (cusId == null) {
			mu.setMessageCode("2");
			mu.setMessageStr("token失效");
			return mu;
		}

		try {
			MyCar myCar = null;
			if (id != null) {
				customerDao.updateMyCar(vehiclePicUrl, carTypeName, carNo,
						recognitionNo, carEngineNo, Integer.valueOf(id));
			} else {
				// 判断爱车是否重复保存
				if (customerDao.judgeByCarNo(carNo) > 0) {
					mu.setMessageCode("1");
					mu.setMessageStr("单辆车不能重复保存");
					return mu;
				}
				Integer num = customerDao.findMyCarNum(cusId);
				if (0 == num) {
					myCar = new MyCar(cusId, vehiclePicUrl, carTypeName, carNo,
							new Date(), "1", recognitionNo, carEngineNo);
					customerDao.saveMyCar(myCar);
				} else {
					myCar = new MyCar(cusId, vehiclePicUrl, carTypeName, carNo,
							new Date(), "0", recognitionNo, carEngineNo);
					customerDao.saveMyCar(myCar);
				}
			}
			mu.setMessageCode("0");

		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("保存爱车信息失败");
			logger.info("保存爱车信息失败:" + e);
		}
		return mu;
	}

	// 查询爱车信息
	public MessageUntil<HashMap<String, Object>> getMyVehicleList(
			HttpServletRequest request) {
		MessageUntil<HashMap<String, Object>> mu = new MessageUntil<HashMap<String, Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		String token = request.getParameter("token");
		String page = request.getParameter("page");
		Integer pageNum = 10;
		Integer pageMin = 0;
		Integer pageMax = 0;
		if (null != page) {
			pageMax = pageNum * Integer.valueOf(page);
			pageMin = (Integer.valueOf(page) - 1) * pageNum;
		}
		Integer cusId = null;
		try {
			cusId = tokenInfoDao.selectByToken(token);
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("验证token登录信息异常");
			logger.info("验证token登录信息异常:" + e);
			return mu;
		}
		if (cusId == null) {
			mu.setMessageCode("2");
			mu.setMessageStr("token失效");
			return mu;
		}
		try {
			List<HashMap<String, Object>> myVehicleList = customerDao
					.findMyCarList(cusId, pageMin, pageMax);
			Integer nums = customerDao.findMyCarNum(cusId);
			map.put("myVehicleList", myVehicleList);
			map.put("nums", nums);
			mu.setData(map);
			mu.setMessageCode("0");
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("查询爱车信息失败");
			logger.info("查询爱车信息失败:" + e);
		}
		return mu;
	}

	// 删除爱车信息
	public MessageUntil<String> deleteVehicle(HttpServletRequest request) {
		MessageUntil<String> mu = new MessageUntil<String>();
		String token = request.getParameter("token");
		Integer id = Integer.valueOf(request.getParameter("id"));
		Integer cusId = null;
		try {
			cusId = tokenInfoDao.selectByToken(token);
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("验证token登录信息异常");
			logger.info("验证token登录信息异常:" + e);
			return mu;
		}
		if (cusId == null) {
			mu.setMessageCode("2");
			mu.setMessageStr("token失效");
			return mu;
		}
		try {
			customerDao.deleteMyCar(id);
			mu.setMessageCode("0");
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("删除爱车信息失败");
			logger.info("删除爱车信息失败:" + e);
		}
		return mu;
	}

	@Override
	public MessageUntil<String> updateMyVehicleStatus(HttpServletRequest request) {
		// TODO Auto-generated method stub
		MessageUntil<String> mu = new MessageUntil<String>();
		String token = request.getParameter("token");
		Integer id = Integer.valueOf(request.getParameter("id"));
		Integer cusId = null;
		try {
			cusId = tokenInfoDao.selectByToken(token);
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("验证token登录信息异常");
			logger.info("验证token登录信息异常:" + e);
			return mu;
		}
		if (cusId == null) {
			mu.setMessageCode("2");
			mu.setMessageStr("token失效");
			return mu;
		}
		// 查询是否存在已有默认值
		Integer OrId = null;
		try {
			OrId = customerDao.findDefaultStatus(cusId);
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("爱车信息表数据异常");
			return mu;
		}

		try {
			customerDao.updateStatusToDefault(OrId, "0");
			customerDao.updateStatusToDefault(id, "1");
			mu.setMessageCode("0");
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("更该爱车默认失败");
			logger.info("更该爱车默认失败:" + e);
		}

		return mu;
	}

	// 验证第三方是否绑定过
	private boolean thrLogin(String uniqueId) {
		// 查询登录时是否绑定过手机号
		boolean flag = false;
		String phone = null;
		try {
			phone = customerDao.identityThrLogin(uniqueId);
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("判断第三方登录异常" + e);
		}
		if (null != phone) {
			flag = true;
		}

		return flag;

	}

	@Override
	public MessageUntil<HashMap<String, Object>> getMyDet(
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		MessageUntil<HashMap<String, Object>> mu = new MessageUntil<HashMap<String, Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		String token = request.getParameter("token");
		Integer cusId = null;
		try {
			cusId = tokenInfoDao.selectByToken(token);
		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("验证token登录信息异常");
			logger.info("验证token登录信息异常:" + e);
			return mu;
		}
		if (cusId == null) {
			mu.setMessageCode("2");
			mu.setMessageStr("token失效");
			return mu;
		}
		try {
			// 查询自己的信息
			HashMap<String, Object> myPersonalMs = customerDao
					.findCusMsById(cusId);
			// 查询默认的爱车信息
			HashMap<String, Object> myCarMs = customerDao.findMyMrCar(cusId);
			map.put("myPersonalMs", myPersonalMs);
			map.put("myCarMs", myCarMs);
			mu.setData(map);
			mu.setMessageCode("0");

		} catch (Exception e) {
			// TODO: handle exception
			mu.setMessageCode("1");
			mu.setMessageStr("获取个人信息失败");
			logger.info("获取个人信息失败" + e);
		}

		return mu;
	}

}
