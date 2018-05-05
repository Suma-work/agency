package com.sumainfo.agency.dao;

import org.apache.ibatis.annotations.Param;

import com.sumainfo.common.entity.Customer;
import com.sumainfo.common.entity.Token;




public interface TokenDao {
	
	/**
	 * 插入数据
	 * @param token
	 * @return
	 */
	int insertToken(Token token);
	
	/**
	 * 查询数据
	 * @param userId
	 * @return
	 */
	String selectToken(@Param("userId")int userId);
	
	/**
	 * 更新或插入数据
	 * @param tokenMsg
	 * @param otherIdetify
	 * @return
	 */
	int updateToken(Token tokenInfo);
	
	/**
	 * 11
	 * 根据tokenMsg查询id
	 * @param tokenMsg
	 * @return
	 */
	Integer selectByToken(@Param("tokenMsg")String tokenMsg);
	
	/**
	 * 删除token信息
	 * @param otherIdetify
	 */
//	void deleteToken (@Param("otherIdetify")String otherIdetify);
	
	/**
	 * 查询客户信息
	 * @param tokenMsg
	 * @return
	 */
	Customer findCustomerMs(@Param("tokenMsg")String tokenMsg);
	
}
