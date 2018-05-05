package com.sumainfo.common.redis;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sumainfo.common.redis.RedisUtil;

import redis.clients.jedis.Jedis;


/**
 * 连接redis并做一些数据类型的缓存
 * @author Administrator
 *
 */
public class RedisClient {

	private Logger logger = LoggerFactory.getLogger(RedisClient.class);
	
	private Jedis jedis;
    
    public RedisClient() {
        //连接redis服务器
        jedis = RedisUtil.getJedis();
    }
    
    
   
    public void resetTime(String key, int timeOut) {
    	jedis.expire(key, timeOut);//设置redis数据库中token的数据的失效时间 timeOut 秒
    }
    
    public void delStr(String key){
    	jedis.del(key);
    }
    
    /**
     * redia存String
     * @param key
     * @param value
     * @param timeOut
     */
    public void setString(String key, String value, int timeOut) {
    	jedis.set(key, value);
    	jedis.expire(key, timeOut);//设置redis数据库中token的数据的失效时间
    	logger.info("-----数据已存在redis缓存中--------------");
    }
    
    /**
     * redis取String
     * @param key
     * @param value
     * @param timeOut
     */
    public String getString(String key) {
    	return jedis.get(key);
    }
    
    /**
     * redis中插入map
     * @param key
     * @param value
     * @param timeOut
     * @return
     */
    public void setMap(String key,Map<String,String> value,int timeOut){
    	//插入map
    	jedis.hmset(key, value);
    	//设置过期时间
    	jedis.expire(key, timeOut);
    	//删除为key的redis值 
//    	jedis.del(key);
    	
    }
    
    /**
     * 获取redis中的map值
     * @param key
     * @param fileNames
     * @return
     */
    public Map<String, Object> getMap(String key,List<String> fileNames){
    	Map<String, Object> map = new HashMap<String, Object>();
		for(String fileName:fileNames){
			List<String> list = jedis.hmget(key, fileName);
			for(String value:list){
				map.put(fileName, value);
			}
			
		}
		return map;
			
    }
    
    

}
