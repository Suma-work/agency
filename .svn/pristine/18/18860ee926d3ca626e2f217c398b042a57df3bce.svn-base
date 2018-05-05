package com.sumainfo.agency.service;



import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;
import com.sumainfo.common.until.MessageUntil;
public interface CustomerService {
	
	/**
	 * 认证前验证手机号
	 * @param request
	 * @return
	 */
	MessageUntil<String> identify(HttpServletRequest request);
	
	/**
	 * 注册账号
	 * @param request
	 * @return
	 */
	MessageUntil<String> register(HttpServletRequest request);
	
	/**
	 * 用户登录
	 * @param request
	 * @return
	 */
	MessageUntil<String> loginAccount(HttpServletRequest request);
	
	/**
	 * 绑定手机号
	 * @param request
	 * @return
	 */
	MessageUntil<String> bandPhone(HttpServletRequest request);
	
	/**
	 * 忘记密码
	 * @param request
	 * @return
	 */
	MessageUntil<String> forgetPassword(HttpServletRequest request);
	
	/**
	 * 用户修改密码
	 */
	MessageUntil<String> modifyPassword(HttpServletRequest request);
	
	/**
	 * 查询用户列表
	 * @param request
	 * @return
	 */
//	public MessageUntil<List<User>> findSome(HttpServletRequest request);
	
	/**
	 * 上传图像
	 * @param request
	 * @param file
	 * @return
	 */
	MessageUntil<String> uploadPic(HttpServletRequest request,MultipartFile file);
	
	
	/**
	 * 退出登录
	 * @param request
	 * @return
	 */
	MessageUntil<String> logout(HttpServletRequest request);
	
	
	/**
	 * 实名认证
	 * @param request
	 * @return
	 */
	MessageUntil<String> certification(HttpServletRequest request);
	
	/**
	 *修改昵称
	 * @param request
	 * @return
	 */
	MessageUntil<String> modifyNikeName(HttpServletRequest request);
	
	/**
	 * 修改手机号前发送验证码
	 * @param request
	 * @return
	 */
	MessageUntil<String> sendMsgToNewPhone(HttpServletRequest request);
	
	/**
	 * 已知密码修改密码
	 * @param request
	 * @return
	 */
	MessageUntil<String> knowPsModifyPs(HttpServletRequest request);
	
	/**
	 *修改手机号
	 * @param request
	 * @return
	 */
	MessageUntil<String> modifyPhone(HttpServletRequest request);
	
	
	/**
	 * 添加自己的爱车
	 * @param request
	 * @return
	 */
	MessageUntil<String> addMyVehicle(HttpServletRequest request);
	
	/**
	 * 获取爱车的信息条数
	 * @param request
	 * @return
	 */
	MessageUntil<HashMap<String, Object>> getMyVehicleList(HttpServletRequest request);
	
	/**
	 * 删除爱车信息
	 * @param request
	 * @return
	 */
	MessageUntil<String> deleteVehicle(HttpServletRequest request);
	
	
	/**
	 * 更换爱车的默认
	 * @param request
	 * @return
	 */
	MessageUntil<String> updateMyVehicleStatus(HttpServletRequest request);
	
	
	/**
	 * 点击我的到个人中心获取自己所需信息
	 * @param request
	 * @return
	 */
	MessageUntil<HashMap<String, Object>> getMyDet(HttpServletRequest request);

}
