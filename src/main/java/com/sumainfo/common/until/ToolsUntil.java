package com.sumainfo.common.until;

public class ToolsUntil {
	//返回的code指示信息  0 正常 1异常 2失败
	public final static String MESSAGE_SUCESS = "0";
	public final static String MESSAGE_EROOR = "1";
	public final static String MESSAGE_FAIL = "2";
	//登录信息提示
	public final static String MESSAGE_LOGIN_SUCESS_MESSAGE = "登陆成功";
	public final static String MESSAGE_LOGIN_EROOR_MESSAGE = "登录出错";
	public final static String MESSAGE_LOGIN_FAIL_MESSAGE = "登录失败";
	//注册信息提示
	public final static String MESSAGE_REGISTER_SUCESS_MESSAGE = "注册成功";
	public final static String MESSAGE_REGISTER_EROOR_MESSAGE = "注册出错";
	public final static String MESSAGE_REGISTER_FAIL_MESSAGE = "注册失败";
	
	//token保存的时效期
	public final static int EXPIRETIME_FOR_RELOGON = 30*24*3600;//多久未操作用户需要重新登录的时间    600小时
	
	//网络访问图片的地址
	public final static String PICTURE_URL = "http://52.80.16.16:8080/image/";
	//服务器上储存图片的地址"C://Users//Administrator//Desktop//tomcat//apache-tomcat-8.5.24-windows-x64//apache-tomcat-8.5.24//webapps//image//"
//	public final static String PATH_URL = "C://Users//Administrator//Desktop//tomcat//apache-tomcat-8.5.24-windows-x64//apache-tomcat-8.5.24//webapps//image//";
	public final static String PATH_URL = "D:\\testPicture\\";
	//短信的发送地址
	public final static String VARIABLE_URL = "http://smsbj1.253.com/msg/variable/json"; 
	//变量短信 验证用
	public final static String VARIABLE_MSG = "【253云通讯】尊敬的{$var},您好,您的验证码是{$var},{$var}分钟内有效";
	//行业短信的账户
	public final static String VARIABLE_ACCOUNT = "N8063643";
	//行业短信的密码
	public final static String VARIABLE_PS = "OIav4qYpb";
	//营销短信
	public final static String PULL_MSG = "【优捷德】尊敬的{$var},您好!请您确认是否预约看车,《回复任意信息》预约看车，如不预约看车，请您忽略此信息，回复TD退订";
	//营销短信的账户
	public final static String MARKET_ACCOUNT = "M4546114";
	//营销短信的密码
	public final static String MARKET_PS = "T3W2Z4VaKfc0cf";
	
	//定时器任务设置秒数
	public final static int SEC = 30*10000;//30*10000秒刷新一次
	
	

}
