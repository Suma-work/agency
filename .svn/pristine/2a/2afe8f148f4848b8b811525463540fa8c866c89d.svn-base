package com.sumainfo.agency.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sumainfo.common.entity.Customer;
import com.sumainfo.common.entity.MyCar;
import com.sumainfo.common.entity.ThrLogin;


public interface CustomerDao {

	
	/**
	 * 注册信息
	 * @param customer
	 * @return
	 */
	int saveOrUpdate(Customer customer);
	
	/**
	 * 登录或判断用户是否注册过
	 * @param cellPhone
	 * @return
	 */
	Customer loginAccount(@Param("cellPhone")String cellPhone);
	
	/**
	 * 插入第三方信息
	 * @param thl
	 * @return
	 */
	int saveThrLogin(ThrLogin thl);
	
	/**
	 * 解除绑定
	 * @param uniqueId
	 * @return
	 */
	int deleteThr(@Param("uniqueId")String uniqueId);
	
	/**
	 * 更新密码
	 * @param cellPhone
	 * @param passWord
	 * @return
	 */
	void updateCustomer(@Param("cellPhone")String cellPhone,@Param("passWord")String passWord);
	
	/**
	 * 根据客户主键查询客户电话
	 * @param cusId
	 * @return
	 */
	String findCusById(@Param("cusId")int cusId);
	
	/**
	 * 根据客户主键查询客户名称
	 * @param cusId
	 * @return
	 */
	String selectNameById(@Param("cusId")int cusId);
	
	/**
	 * 根据电话查询客户是否被冻结或者正常
	 * @param cellPhone
	 * @param status
	 * @return
	 */
	Integer findStatusNum(@Param("cellPhone")String cellPhone,@Param("status") int status);
	
	/**
	 * 上传用户图像
	 * @param cusId
	 * @param portait
	 * @return
	 */
	int savaCusPic(@Param("cusId")int cusId,@Param("portait")String portait);
	
	/**
	 * 退出登录
	 * @param portait
	 * @return
	 */
	int logoutAccount(@Param("tokenMsg")String tokenMsg);
	
	/**
	 * 实名认证
	 * @param cusId
	 * @param realName
	 * @param idNum
	 * @return
	 */
	int certificationMessage(@Param("cusId")int cusId,@Param("realName")String realName,@Param("idNumber")String idNumber);
	
	
	/**
	 * 修改昵称
	 * @param cusId
	 * @param nickName
	 * @return
	 */
	int modifyNikeName(@Param("cusId")int cusId,@Param("nickName")String nickName);
	
	/**
	 * 修改手机号
	 * @param cusId
	 * @param nickName
	 * @return
	 */
	int modifyPhone(@Param("cusId")int cusId,@Param("cellPhone")String cellPhone);
	
	/**
	 * 查询自己绑定车的条数
	 * @param cusId
	 * @return
	 */
	Integer findMyCarNum(@Param("cusId")int cusId);
	
	/**
	 * 保存my爱车信息
	 * @param mycar
	 * @return
	 */
	int saveMyCar(MyCar myCar);
	
	/**
	 * 更新my爱车
	 * @param myCar
	 * @return
	 */
	int updateMyCar(@Param("picAdress")String picAdress,
			        @Param("carTypeName")String carTypeName,
			        @Param("carNo")String carNo,
			        @Param("recognitionNo")String recognitionNo,
			        @Param("carEngineNo")String carEngineNo,
			        @Param("id") int id);
	
	/**
	 * 查询爱车的信息
	 * @param cusId
	 * @return
	 */
	List<HashMap<String, Object>>findMyCarList(@Param("cusId")int cusId,@Param("pageMin")int pageMin,@Param("pageMax")int pageMax);
	
	/**
	 * 判断车是否重复保存
	 * @param carNo
	 * @return
	 */
	Integer judgeByCarNo(@Param("carNo")String carNo);
	
	/**
	 * 删除自己爱车记录
	 * @param id
	 * @return
	 */
	Integer deleteMyCar(@Param("id")int id);
	
	/**
	 * 更新爱车状态为默认
	 * @param id
	 * @return
	 */
	Integer updateStatusToDefault(@Param("id")int id,@Param("isDefault")String isDefault);
	
	/**
	 * 更新爱车状态
	 * @param id
	 * @return
	 */
	Integer findDefaultStatus(@Param("cusId")int cusId);
	
	/**
	 * 验证第三方是否绑定手机号
	 * @param uniqueId
	 * @return
	 */
	String identityThrLogin(@Param("uniqueId")String uniqueId);
	
	/**
	 * 查询个人的昵称、实名、电话号码、身份证、头像、性别
	 * @param cusId
	 * @return
	 */
	HashMap<String,Object> findCusMsById(@Param("cusId")int cusId);
	
	/**
	 * 查询个人的默认车中的车牌、logo
	 * @param cusId
	 * @return
	 */
	HashMap<String,Object> findMyMrCar(@Param("cusId")int cusId);
	
	/**
	 * 通过二手车的uid查询客户的信息
	 * @param uId
	 * @return
	 */
	Map<String, Object> findCusByUid(@Param("uId")String uId);
	

}
