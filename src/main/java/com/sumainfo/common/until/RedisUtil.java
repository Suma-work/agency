package com.sumainfo.common.until;

//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
/**
 * 
 * @author 
 * 此类无用，调用redis在项目的com.sumainfo.common.redis包中
 *
 */
public final class RedisUtil {
    
//    //Redis服务器直接默认为是本地服务器的
//    private static String IP  ="127.0.0.1";  //"192.168.99.109"; 
//    
//    //Redis的端口号
//    private static int PORT = 6379;
//    
//    //访问密码
////    private static String AUTH ="763061EBB2BDC5DB";// "123456";
//    
//    //可用连接实例的最大数目，默认值为8；
//    //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
//    private static int MAX_ACTIVE = 1024;
//    
//    //最大空闲数，控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
//    private static int MAX_IDLE = 200;
//    
//    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
//    private static int MAX_WAIT = 10000;
//    
//    private static int TIMEOUT = 10000;
//    
//    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
//    private static boolean TEST_ON_BORROW = true;
//    
//    private static JedisPool jedisPool = null;
//    
//    /**
//     * 初始化Redis连接池
//     */
//    static {
//        try {
//            JedisPoolConfig config = new JedisPoolConfig();
//            //连接实例的最大值
//            config.setMaxActive(MAX_ACTIVE);
//            //最大空闲值
//            config.setMaxIdle(MAX_IDLE);
//            //等待可用连接的最大时间
//            config.setMaxWait(MAX_WAIT);
//            config.setTestOnBorrow(TEST_ON_BORROW);
//            jedisPool = new JedisPool(config, IP, PORT, TIMEOUT);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    
//    /**
//     * 获取Jedis实例
//     * @return
//     */
//    public synchronized static Jedis getJedis() {
//        try {
//            if (jedisPool != null) {
//                Jedis resource = jedisPool.getResource();
//                return resource;
//            } else {
//                return null;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//    
//    /**
//     * 释放jedis资源
//     * @param jedis
//     */
//    public static void returnResource(final Jedis jedis) {
//        if (jedis != null) {
//            jedisPool.returnResource(jedis);
//        }
//    }
//    
}