package com.sumainfo.common.sms;

import java.io.UnsupportedEncodingException;
import com.alibaba.fastjson.JSON;


/**
 * 
 * @author tianyh 
 * @Description:普通短信发送
 */
public class SmsSend {

	public static final String charset = "utf-8";

	public static SmsResponse SendTextCode (String msg,String params,String account,String pswd,String smsUrl) throws UnsupportedEncodingException {
		//请求地址请登录253云通讯自助通平台查看或者询问您的商务负责人获取
//		String smsVariableRequestUrl = "http://smsbj1.253.com/msg/variable/json";
		//短信内容
//		String msg = "【253云通讯】尊敬的{$var},您好,您的验证码是{$var},{$var}分钟内有效";
		//状态报告
		String report= "true";
		
		SmsRequest smsVariableRequest=new SmsRequest(account, pswd, msg, params, report);
		
        String requestJson = JSON.toJSONString(smsVariableRequest);
		
		System.out.println("before request string is: " + requestJson);
		
		String response = ChuangLanSmsUtil.sendSmsByPost(smsUrl, requestJson);
		
		System.out.println("response after request result is : " + response);
		
		SmsResponse smsVariableResponse = JSON.parseObject(response, SmsResponse.class);
		
		System.out.println("response  toString is : " + smsVariableResponse);
		return smsVariableResponse;
		
	}
	
	public static SmsPullResponse pullSms(String account,String pswd) throws UnsupportedEncodingException {

		//请求地址请登录253云通讯自助通平台查看或者询问您的商务负责人获取
		String smsPullRequestUrl = "http://smssh1.253.com/msg/pull/mo";
		//上行拉取条数
		String count = "100";
		
		SmsPullRequest smsPullRequest = new SmsPullRequest(account, pswd, count);

		String requestJson = JSON.toJSONString(smsPullRequest);

		System.out.println("before request string is: " + requestJson);

		String response = ChuangLanSmsUtil.sendSmsByPost(smsPullRequestUrl, requestJson);

		System.out.println("response after request result is : " + response);

		SmsPullResponse smsPullResponse = JSON.parseObject(response, SmsPullResponse.class);
		
		System.out.println("response  toString is : " + smsPullResponse);
		
		return smsPullResponse;
	}
		

}
