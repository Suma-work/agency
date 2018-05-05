package com.sumainfo.common.until;


import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sumainfo.common.entity.CarLabelFirstLevel;
import com.sumainfo.common.entity.CarLableSecRecept;



public class CarMessageUntil {
	public final static String APPCODE = "4631720941e7499399d0e1cc36f1261a";
	public static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
	static Logger logger = LoggerFactory.getLogger(CarMessageUntil.class);
	public static ArrayList<CarLabelFirstLevel>  getCarFirstLevelMessage(){
		String host = "http://jisucxdq.market.alicloudapi.com";
	    String path = "/car/brand";
	    String method = "GET";
//	    String appcode = "4631720941e7499399d0e1cc36f1261a";
	    Map<String, String> headers = new HashMap<String, String>();
	    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
	    headers.put("Authorization", "APPCODE " + APPCODE);
	    Map<String, String> querys = new HashMap<String, String>();
	    ArrayList<CarLabelFirstLevel> arrayList = new ArrayList<CarLabelFirstLevel>();
	    try {
	    	/**
	    	* 重要提示如下:
	    	* HttpUtils请从
	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
	    	* 下载
	    	*
	    	* 相应的依赖请参照
	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
	    	*/
	    	HttpResponse response = HttpCarUntil.doGet(host, path, method, headers, querys);
	    	//获取response的body
	    	String str = ObjectToJsonUntil.toJson(JsonToObjectUntil.jsonToMap(EntityUtils.toString(response.getEntity())).get("result")) ;
	    	logger.info(str);
	    	List<CarLabelFirstLevel> list = JsonToObjectUntil.jsonToList(str);
	    	
	    	arrayList = (ArrayList<CarLabelFirstLevel>)list;
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    return arrayList;
	}
	
	public static List<CarLableSecRecept> getCarSecLevelMessage(String id) {
	    String host = "http://jisucxdq.market.alicloudapi.com";
	    String path = "/car/carlist";
	    String method = "GET";
//	    String appcode = "你自己的AppCode";
	    Map<String, String> headers = new HashMap<String, String>();
	    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
	    headers.put("Authorization", "APPCODE " + APPCODE);
	    Map<String, String> querys = new HashMap<String, String>();
	    querys.put("parentid",id);
	    List<CarLableSecRecept>  carLableSecRecepts = new ArrayList<CarLableSecRecept>();
	   
	    try {
	    	/**
	    	* 重要提示如下:
	    	* HttpUtils请从
	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
	    	* 下载
	    	*
	    	* 相应的依赖请参照
	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
	    	*/
	    	HttpResponse response = HttpCarUntil.doGet(host, path, method, headers, querys);
    		String content = readInputStream(response.getEntity().getContent());
			logger.info("显示结果：---------------------："+content);
			Map<String,Object> map = JsonToObjectUntil.jsonToMap(content);
			Object results = map.get("result");
			String str = ObjectToJsonUntil.toJson(results);
    		carLableSecRecepts = JsonToObjectUntil.jsonToJavaBeans(str);    
    		logger.info("显示carLableSecRecepts:"+carLableSecRecepts.toString());
	    	return carLableSecRecepts;
	    } catch (Exception e) {
	    	logger.info("返回车辆标签数据异常");
	    	return carLableSecRecepts;
	    }
	}
	
	/**
	 * 将流转成字符串
	 * @param is
	 * @return
	 */
	 public static String readInputStream(InputStream is){  
	        try {  
	            ByteArrayOutputStream baos=new ByteArrayOutputStream();  
	            int length=0;  
	            byte[] buffer=new byte[1024];  
	            while((length=is.read(buffer))!=-1){  
	                baos.write(buffer, 0, length);  
	            }  
	            is.close();  
	            baos.close();  
	            return baos.toString();  
	        } catch (Exception e) {  
	            logger.info("处理流信息异常");
	            return null;  
	        }  
	    }  
	
}
