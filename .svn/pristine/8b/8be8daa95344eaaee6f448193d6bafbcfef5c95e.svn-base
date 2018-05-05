package com.sumainfo.common.until;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.DateTime;
import org.joda.time.Seconds;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.util.StringUtils;


public class Validation implements Serializable{
	private static final long serialVersionUID = 595219358310280025L;
	private final static Logger log = LoggerFactory.getLogger(Validation.class);
	
	public static void main(String[] args){
		
		@SuppressWarnings({ "unused", "serial" })
		Map<String,Object> params = new HashMap<String,Object>(){{
			put("limit", "1");
			put("start", 1);
		}};
		StringBuffer errMsg = new StringBuffer();
		log.debug("errMsg:{}", errMsg);
		System.out.println("-------->"+isMobile("15525656859"));
	}
	public static Boolean checkBlank(Map<String,Object> params, String... fields){
		Boolean result = true;
		for(String field: fields){
			log.debug("contains:{}", params.containsKey(field) );
			if (!params.containsKey(field)){
				result = false;
				break;
			}
			if (params.get(field) == null){
				result = false;
				break;
			}
			if (StringUtils.isEmpty(params.get(field))){
				result = false;
				break;
			}
		}
		return result;
	}
	public static Boolean checkNull(Map<String,Object> params, StringBuffer errMsg, MessageSource messageSource, String... fields){
		Boolean result = true;
		for(String field: fields){
//			log.debug("contains:{}", params.containsKey(field) );
			String desc = messageSource.getMessage(field, null, null);
			if (!params.containsKey(field)){
				errMsg.append(desc + " 必填 ; ");
				result = false;
				continue;
			}
			if (params.get(field) == null||params.get(field).equals("null")){
				errMsg.append(desc + " 不能为空; ");
				result = false;
				continue;
			}
		}
		return result;
	}
	public static Boolean checkBlank(Map<String,Object> params, StringBuffer errMsg, MessageSource messageSource, String... fields){
		Boolean result = true;
		for(String field: fields){
//			log.debug("contains:{}", params.containsKey(field) );
//			String desc = messageSource.getMessage(field, null, null);
			String desc =field;
			if (!params.containsKey(field)){
				errMsg.append(desc + " 必填 ; ");
				result = false;
				continue;
			}
			if (params.get(field) == null||params.get(field).equals("null")){
				errMsg.append(desc + " 不能为空; ");
				result = false;
				continue;
			}
			if (StringUtils.isEmpty(params.get(field))){
				errMsg.append(desc + " 不能为空字符; ");
				result = false;
				continue;
			}
		}
		return result;
	}
	
	 
	public static Boolean checkInteger(Map<String,Object> params, StringBuffer errMsg, MessageSource messageSource, String... fields){
		Boolean result = true;
		for(String field: fields){
			if (!params.containsKey(field)){
				continue;
			}
			String desc = messageSource.getMessage(field, null, null);
			try {
		        String value = params.get(field).toString();
		        Integer.parseInt(value);
		    } catch (NumberFormatException e) {
		    	result = false;
		    	errMsg.append(desc + " 非数字; ");
		    }
		}
		return result;
	}
	public static Boolean checkInteger(Map<String,Object> params, StringBuffer errMsg, MessageSource messageSource, String field, int min, int max){
		Boolean result = true;
		String desc = messageSource.getMessage(field, null, null);
		if (!params.containsKey(field)){
			errMsg.append(desc + " 不能为空; ");
			result = false;
		}
		if (params.get(field) == null){
			errMsg.append(desc + " 不能为空; ");
			result = false;
		}
		
		try{
			Integer value = Integer.parseInt(params.get(field).toString());
			if (value < min || value > max){
				errMsg.append(desc + "必须满足范围: (" + min + "," + max + ")");
				result = false;
			}
		}catch(Exception e){
			errMsg.append(desc + "必须满足范围: (" + min + "," + max + ")");
			result =false;
		}
		return result;
	}
	
	public static Boolean checkLength(Map<String,Object> params, StringBuffer errMsg, MessageSource messageSource, String field, int min, int max){
		Boolean result = true;
		String desc = messageSource.getMessage(field, null, null);
		if (!params.containsKey(field)){
			errMsg.append(desc + " 不能为空; ");
			result = false;
		}
		if (params.get(field) == null){
			errMsg.append(desc + " 不能为空; ");
			result = false;
		}
		
		String value = params.get(field).toString();
		if (value.length() < min || value.length() > max){
			errMsg.append(desc + " 长度必须满足范围: [" + min + "," + max + "]");
			result = false;
		}
		return result;
	}
	
	public static Boolean checkTimeAfterNow(Map<String, Object> params, StringBuffer errMsg, MessageSource messageSource,
			String field) throws Exception {
		Boolean result = true;
		String desc = messageSource.getMessage(field, null, null);
		if (!params.containsKey(field)) {
			errMsg.append(desc + " 不能为空; ");
			result = false;
		}
		if (params.get(field) == null) {
			errMsg.append(desc + " 不能为空; ");
			result = false;
		}

		String value = params.get(field).toString();
		String pattern = "^([\\d]{4}-[\\d]{1,2}-[\\d]{1,2} [\\d]{1,2}:[\\d]{1,2})$";
		// 创建 Pattern 对象
		Pattern r = Pattern.compile(pattern);

		// 现在创建 matcher 对象
		Matcher m = r.matcher(value);
		if (!m.find()) {
			errMsg.append(desc + " 时间格式为: [" + ConvertDateTime.getCurrentTimeNow2() + "]");
			result = false;
			return result;
		}
		Seconds seconds = Seconds.secondsBetween(
				ConvertDateTime.dateFormatStrToTime(ConvertDateTime.FORMAT_YMDHM_DATETIME_EN, value), 
				ConvertDateTime.getCurTime());
		if(seconds.getSeconds() > 0){
			errMsg.append(desc + " 必须大于当前时间");
			result = false;
		}
		return result;
	}
	/**
	 * 考试开始时间必须是明天以后
	 * @param params
	 * @param errMsg
	 * @param messageSource
	 * @param field
	 * @return
	 * @throws Exception
	 */
	public static Boolean checkTimeAfterCurrentDate(Map<String, Object> params, StringBuffer errMsg, MessageSource messageSource,
			String field) throws Exception {
		Boolean result = true;
		String desc = messageSource.getMessage(field, null, null);
		if (!params.containsKey(field)) {
			errMsg.append(desc + " 不能为空; ");
			result = false;
		}
		if (params.get(field) == null) {
			errMsg.append(desc + " 不能为空; ");
			result = false;
		}

		String value = params.get(field).toString();
		String pattern = "^([\\d]{4}-[\\d]{1,2}-[\\d]{1,2} [\\d]{1,2}:[\\d]{1,2})$";
		// 创建 Pattern 对象
		Pattern r = Pattern.compile(pattern);

		// 现在创建 matcher 对象
		Matcher m = r.matcher(value);
		if (!m.find()) {
			errMsg.append(desc + " 时间格式为: [" + ConvertDateTime.getCurrentTimeNow2() + "]");
			result = false;
			return result;
		}
		int dateDiff = ConvertDateTime.subDate(
				ConvertDateTime.dateFormatStrToTime(ConvertDateTime.FORMAT_YMDHM_DATETIME_EN, value), 
				ConvertDateTime.addDateTime(ConvertDateTime.getCurDate(),1,4),
				4);
		log.debug("dateDiff:{}", dateDiff);
		if(dateDiff >= 0){
			errMsg.append(desc + "只能创建明天以后的考场");
			result = false;
		}
		return result;
	}
	
	public static Boolean checkTimeFromTo(Map<String, Object> params, StringBuffer errMsg, MessageSource messageSource,
			String field1, String field2) throws Exception {
		Boolean result = true;
		String desc1 = messageSource.getMessage(field1, null, null);
		String desc2 = messageSource.getMessage(field2, null, null);
		String value1 = params.get(field1).toString();
		String value2 = params.get(field2).toString();
		Seconds seconds = Seconds.secondsBetween(
				ConvertDateTime.dateFormatStrToTime(ConvertDateTime.FORMAT_YMDHM_DATETIME_EN, value1), 
				ConvertDateTime.dateFormatStrToTime(ConvertDateTime.FORMAT_YMDHM_DATETIME_EN, value2));
		if(seconds.getSeconds() <= 0){
			errMsg.append(desc2 + " 必须大于 " + desc1 );
			result = false;
		}
		return result;
	}
	
	public static Boolean checkDate(Map<String, Object> params, StringBuffer errMsg, MessageSource messageSource,
			 String field)throws Exception{
		Boolean result = true;
		String desc = messageSource.getMessage(field, null, null);
		String datestr = params.get(field).toString();
		DateTimeFormatter df = DateTimeFormat.forPattern(ConvertDateTime.SERIAL_YMDHM_DATETIME);
		try{
			DateTime dateFormatTime = DateTime.parse( datestr, df);
			System.out.println(datestr );
			System.out.println(dateFormatTime);
		}catch(Exception ex){
			result = false;
			errMsg.append(desc + " 格式错误");  
		}       
		  
		return result;
	}
	
	
	
	
	/**
     * 正则表达式：验证用户名
     */
    public static final String REGEX_USERNAME = "^[a-zA-Z]\\w{5,20}$";
 
    /**
     * 正则表达式：验证密码
     */
    public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,20}$";
 
    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
 
    /**
     * 正则表达式：验证邮箱
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
 
    /**
     * 正则表达式：验证汉字
     */
    public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";
 
    /**
     * 正则表达式：验证身份证
     */
    public static final String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)";
 
    /**
     * 正则表达式：验证URL
     */
    public static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
 
    /**
     * 正则表达式：验证IP地址
     */
    public static final String REGEX_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
 
    /**
     * 校验用户名
     * 
     * @param username
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isUsername(String username) {
        return Pattern.matches(REGEX_USERNAME, username);
    }
 
    /**
     * 校验密码
     * 
     * @param password
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isPassword(String password) {
        return Pattern.matches(REGEX_PASSWORD, password);
    }
 
    /**
     * 校验手机号
     * 
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }
 
    /**
     * 校验邮箱
     * 
     * @param email
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }
 
    /**
     * 校验汉字
     * 
     * @param chinese
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isChinese(String chinese) {
        return Pattern.matches(REGEX_CHINESE, chinese);
    }
 
    /**
     * 校验身份证
     * 
     * @param idCard
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isIDCard(String idCard) {
        return Pattern.matches(REGEX_ID_CARD, idCard);
    }
 
    /**
     * 校验URL
     * 
     * @param url
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isUrl(String url) {
        return Pattern.matches(REGEX_URL, url);
    }
 
    /**
     * 校验IP地址
     * 
     * @param ipAddr
     * @return
     */
    public static boolean isIPAddr(String ipAddr) {
        return Pattern.matches(REGEX_IP_ADDR, ipAddr);
    }
    /**
	 * 判断值是否为空
	 * @param value
	 * @return
	 */
	public static String checkBlank(Object value){
		if(StringUtils.isEmpty(value)){
			return "";
		}else{
			return value.toString();
		}
	}
}

