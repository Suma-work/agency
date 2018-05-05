package com.sumainfo.agency.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sumainfo.common.entity.ComTenUser;
import com.sumainfo.common.entity.SysUserMs;
import com.sumainfo.common.entity.UserToken;

public interface CommercialTenantUserMsDao {
	
	/**
	 * 保存客户推荐购车信息
	 * @param userName
	 * @return ComTenUser
	 */
	ComTenUser findUserMs(@Param("userName") String userName);
	
	/**
	 * 存token
	 * @param userToken
	 * @return
	 */
	int updateUserToken(UserToken userToken);
	
	/**
	 * 查询userId
	 * @param token
	 * @return
	 */
	int findUserId(@Param("token") String token);
	
	/**
	 * 查询原密码和盐值
	 * @param user_id
	 * @return
	 */
	Map<String, Object> identifyPs(@Param("user_id") int user_id);
	
	/**
	 * 通过id修改密码
	 * @param user_id
	 * @return
	 */
	int updatePsById(@Param("user_id") int user_id,@Param("password")String password);
	
	/**
	 * 查询是否存在该手机号的账户
	 * @param username
	 * @param mobile
	 * @return
	 */
	int identifyPhone(@Param("mobile")String mobile);
	
	/**
	 * 获取首页的轮播图
	 * @return
	 */
    List<HashMap<String, Object>> getPlatBanner();
    
    /**
     * 查找用户角色
     * @param user_id
     * @return
     */
    int findUserRole(@Param("user_id") int user_id);
    
    /**
     * 查找拜访客户中查询用户所需的信息
     * @param user_id
     * @return
     */
    SysUserMs findUserMsForOrder(@Param("user_id") int user_id);
    
    /**
     * 根据token去获取sysuser信息
     * @param token
     * @return
     */
	SysUserMs findUserDetMs(@Param("token") String token);
	

}
