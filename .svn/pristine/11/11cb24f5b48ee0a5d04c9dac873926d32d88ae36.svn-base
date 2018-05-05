package com.sumainfo.common.until;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUntil {
	static Logger logger = LoggerFactory.getLogger(DateUntil.class);
	
	/**
	 * 字符转日期 
	 * 格式(yyyy-MM-dd HH:mm:ss)
	 * @param string
	 * @return
	 */
	public static Date turnDate(String string){
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 Date stringDate = null;
		try {
			stringDate = formatter.parse(string);
		} catch (ParseException e) {
			stringDate = null;
		}
		 return stringDate;
	}
	
	/**
	 * 字符转日期
	 * 格式(yyyy-MM-dd)
	 * @param string
	 * @return
	 */
	public static Date turnToDate(String string){
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		 Date stringDate = null;
		try {
			stringDate = formatter.parse(string);
		} catch (ParseException e) {
			stringDate = null;
		}
		 return stringDate;
	}
	
	/**
	 * 字符转日期
	 * 格式(yyyyMMdd)
	 * @param string
	 * @return
	 */
	public static Date turnToDates(String string){
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		 Date stringDate = null;
		try {
			stringDate = formatter.parse(string);
		} catch (ParseException e) {
			stringDate = null;
		}
		 return stringDate;
	}
	
	
	/**
	 * 日期转字符
	 * @param date
	 * @return
	 */
	public static String turnString(Date date){
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 String dateString = formatter.format(date);
		 return dateString;
	}
	
	/**
	 * 字符转日期
	 * @param date
	 * @return
	 */
	public static Date turnDates(String string){
		 SimpleDateFormat formatter = new SimpleDateFormat("yyMMddHHmm");
		 Date stringDate = null;
		try {
			stringDate = formatter.parse(string);
		} catch (ParseException e) {
			stringDate = null;
		}
		 return stringDate;
	}
	
	/**
	 * 日期转化为yyMMddHHmm的字符串
	 * @param date
	 * @return
	 */
	public static String turnStrings(Date date){
		 SimpleDateFormat formatter = new SimpleDateFormat("yyMMddHHmm");
		 String dateString = formatter.format(date);
		 return dateString;
	}
	

	
//	public static void main(String[] args) {
//		
//		System.out.println(turnStrings(turnDate("2018-03-29 17:24:42")));
//		System.out.println(Long.valueOf(turnStrings(turnDate("2018-03-29 17:24:42"))));
//		
//	}
	
	
	
	

}
