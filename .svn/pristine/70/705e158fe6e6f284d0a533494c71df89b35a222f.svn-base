package com.sumainfo.common.until;


import org.apache.shiro.crypto.hash.SimpleHash;


/**
 * Shiro工具类
* @Title: ShiroUtils.java 
* @Package com.sumainfo.modules.sys.shiro  
* @author zhlu
* @date 2018年3月15日
 */
public class ShiroUtils {
	/**  加密算法 */
	public final static String hashAlgorithmName = "SHA-256";
	/**  循环次数 */
	public final static int hashIterations = 16;

	public static String sha256(String password, String salt) {
		return new SimpleHash(hashAlgorithmName, password, salt, hashIterations).toString();
	}

	

}
