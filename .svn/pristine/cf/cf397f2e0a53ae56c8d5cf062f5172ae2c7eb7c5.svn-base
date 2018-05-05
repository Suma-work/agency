package com.sumainfo.common.until;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sumainfo.common.entity.CarLabelFirstLevel;
import com.sumainfo.common.entity.CarLableSecRecept;

/**
 * json转成实体对象，可根据自己需要转成对象自己进行封装
 * @author DYang
 *
 */

public class JsonToObjectUntil {
	static Logger logger = LoggerFactory.getLogger(JsonToObjectUntil.class);
	 //将json类型的字符串转成对象
	 public static List<CarLabelFirstLevel> jsonToList(String json) {
        Gson gson = new Gson();
        List<CarLabelFirstLevel> carLabelFirstLevels = gson.fromJson(json, new TypeToken<List<CarLabelFirstLevel>>() {
        }.getType());//对于不是类的情况，用这个参数给出
//        for (CarLabelFirstLevel carLabelFirstLevel : carLabelFirstLevels) {
//        	logger.info(carLabelFirstLevel.toString());
//        }
        return carLabelFirstLevels;
	 }
	 
	 //将json类型转成map
	 public static  Map<String, Object> jsonToMap(String json) {
        // TODO Auto-generated method stub
        Gson gson = new Gson();
        Map<String, Object> maps = gson.fromJson(json, new TypeToken<Map<String, Object>>() {
        }.getType());
        return maps;
	 }
	 
	 
	 public static Map<String, String> jsonToMaps(String json){
		    Gson gson = new Gson();
	        Map<String, String> maps = gson.fromJson(json, new TypeToken<Map<String, String>>() {
	        }.getType());
	        return maps;
	 }
	 
	 //将json类型转成 CarLabelFirstLevel 对象
	 public static void jsonToJavaBean(String json) {
	        Gson gson = new Gson();
	        CarLabelFirstLevel carLabelFirstLevel = gson.fromJson(json, CarLabelFirstLevel.class);//对于javabean直接给出class实例
//	        logger.info(carLabelFirstLevel.toString());
	    }
	 
	 public static List<CarLableSecRecept> jsonToJavaBeans(String json) {
	        Gson gson = new Gson();
	        List<CarLableSecRecept> carLableSecRecepts = gson.fromJson(json, new TypeToken<List<CarLableSecRecept>>() {
	        }.getType());//对于不是类的情况，用这个参数给出
//	        		gson.fromJson(json, CarLableSecRecept.class);//对于javabean直接给出class实例
//	        logger.info(carLableSecRecepts.toString());
	        return carLableSecRecepts;
	    }


}
