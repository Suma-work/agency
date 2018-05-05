package com.sumainfo.common.until;

import java.util.List;

public class StringUntil extends org.apache.commons.lang.StringUtils {
	
	/**
	 * 获取6位随机数
	 * @return
	 */
	public static String getRandom(int n) {
		String num = "";
		for (int i = 0 ; i < n ; i ++) {
			num = num + String.valueOf((int) Math.floor(Math.random() * 9 + 1));
		}
		return num;
	}

	/**
	 * 转换null字符串对象为""字符串
	 * 
	 * @param str str
	 * @param String
	 */
	public static String nullToEmptyStr(String str) {
		if(StringUntil.isEmpty(str))
			return "";
		return str;
	}
	
	
	/**
	 * 转换null对象为""字符
	 * 
	 * @param str str
	 * @param String
	 */
	public static String nullToEmptyStr(Object obj) {
		return nullToEmptyStr(obj == null? null:String.valueOf(obj));
	}
	
	/**
	 * 转化sql中的转义字符
	 * 
	 * @param oriSql
	 */
	public static String converSqlEscapeChar(String oriSql) {
		return oriSql.replaceAll("%", "\\\\%").replaceAll("_", "\\\\_");
	}
	
	/**
	 * 如果字符串为空则转换成空字符，否则复串返回
	 * 
	 * @param str
	 * @return
	 */
	public static String convertNullToEmpty(String str) {
		
		if(isNotEmpty(str)) {
			return str;
		}else {
			return EMPTY;
		}
	}
	
	public static String convertNullToString(Integer i) {
		
		if (i == null) {
			return EMPTY;
		}else {
			return i.toString();
		}
	}
	
	public static String replaceDotToColon(String str) {
		
		if (StringUntil.isEmpty(str)) {
			return str;
		}
		return str.replaceAll("\\.",":");
	}
	
	public static String replaceColonToDot(String str) {
		if(StringUntil.isEmpty(str))
			return str;
		return str.replaceAll(":", ".");
	}
	
	public static boolean isIn(String target, String... strings) {
		for(String str : strings) {
			if(target == str) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isIgnoreCaseIn(String target, String...strings) {
		
		for(String str : strings) {
			if(target == str || (isNotEmpty(target) && target.equalsIgnoreCase(str))) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * TRUE:1
	 */
	public static final Character TRUE_CHARACTER = '1';
	
	/**
	 * 是否为真
	 * 
	 * @param c
	 * 字符
	 * @return 真假
	 */
	public static boolean isTrue(Character c) {
		if(TRUE_CHARACTER.equals(c)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 是否为真
	 * 
	 * @param s
	 * 字符
	 * @return 真假
	 */
	public static boolean isTrue(String s) {
		if(TRUE_CHARACTER.equals(s)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 追加双引号
	 * 
	 * @param str
	 * 字符
	 * @param追加后字符
	 */
	public static String quote(String str) {
		return str == null? null:(new StringBuilder('"')).append(str).append('"').toString();
	}
	
	/**
	 * 追加单引号
	 * 
	 * @param str
	 * 字符
	 * @return 追加后字符
	 */
	public static String singleQuote(String str) {
		return str == null ? null : (new StringBuilder("'")).append(str).append("'").toString();
	}
	
	/**
	 * 替换文本
	 * 
	 * @param text
	 * 处理文本
	 * @param placeholder
	 * 占位符
	 * @param parameters
	 * 替换参数
	 * @return 替换后文本
	 */
	public static String replace(String text, String placeholder, String...parameters) {
		if(!StringUntil.isEmpty(text) && !StringUntil.isEmpty(placeholder) && parameters != null) {
			for (String parameter : parameters) {
				text = replaceOnce(text,placeholder,parameter);
			}
		}
		return text;
	}
	
	/**
	 * 替换文本
	 * 
	 * @param text
	 * 处理文本
	 * @param placeholder
	 * 占位符
	 * @param parameters
	 * 替换参数
	 * @return 替换后本文
	 */
	public static String replace(String text, Character palceholder, String...parameters) {
		return replace(text,palceholder + StringUntil.EMPTY, parameters);
	}
	
	/**
	 * 替换文本
	 * 
	 * @param text
	 * 处理文本
	 * @param placeholder
	 * 占位符
	 * @param parameters
	 * 替换参数
	 * @return 替换后本文
	 */
	public static String replace(String text, Character placeholder, List<String> parameters) {
		return replace(text,placeholder + StringUntil.EMPTY, parameters);
	}
	
	/**
	 * 替换文本
	 * 
	 * @param text
	 * 处理文本
	 * @param placeholder
	 * 占位符
	 * @param parameters
	 * 替换参数
	 * @return 替换后本文
	 */
	public static String replace(String text, String placeholder, List<String> parameters) {
		if(parameters != null && parameters.size()>0) {
			return replace(text,placeholder,parameters.toArray(new String[parameters.size()])); 
		}
		return text;
	}
	
	public static boolean isEmpty(Object value) {
		
		if(value == null) 
			return true;
		if(isEmpty(value.toString()))
			return true;
		return false;
		
	}
	
	//判断字符是否为空字符传或者为空
	public static String isEmptys(String value){
		try {
			if(value.equals("")||value==null){
				value = null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			value = null;
		}
		return value;
		
	}
	
}
