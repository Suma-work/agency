package com.sumainfo.agency.controller;


import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sumainfo.agency.service.PalaceService;
import com.sumainfo.common.entity.AreaTable;
import com.sumainfo.common.until.MessageUntil;

/**
 * 保存区域基础信息
 * @author Administrator
 *
 */
@RestController
public class PalaceController {
	Logger logger = LoggerFactory.getLogger(PalaceController.class);
	@Autowired
	private PalaceService palaceService;


	/**
	 * 获取地区信息
	 * @param request
	 * @param response
	 * @return
	 */
	@GetMapping("/saveAreaMessage.do")
	public MessageUntil<String> saveAreaMessage(HttpServletRequest request,HttpServletResponse response){
		MessageUntil<String> res = palaceService.saveAreaMessage(request);
		return res;

	}
	
	/**
	 * 查询城市、地区信息
	 * @param request
	 * @return
	 */
	@PostMapping("/findArea.do")
	public MessageUntil<List<AreaTable>> findArea(HttpServletRequest request){
		MessageUntil<List<AreaTable>> res = palaceService.findArea(request);
		return res;
	}

}
