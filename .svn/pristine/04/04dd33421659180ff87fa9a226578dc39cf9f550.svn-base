package com.sumainfo.agency.controller;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sumainfo.agency.service.BuyVehicleService;
import com.sumainfo.common.until.ObjectToJsonUntil;
import com.sumainfo.common.until.MessageUntil;
/**
 * 汽车标签信息查询
 * @author Administrator
 *
 */
@RestController
public class BuyVehicleController {
	Logger logger = LoggerFactory.getLogger(BuyVehicleController.class);
	@Autowired
	private BuyVehicleService buyVehicleService;
	
	/**
	 * 推荐购车
	 * @param request
	 * @return
	 */
	@PostMapping("/saveRecBuyVehicle.do")
	public void saveRecBuyVehicle(HttpServletRequest request,HttpServletResponse response){
		MessageUntil<String> res = buyVehicleService.saveRecBuyVehicle(request);
		try {
			String resu = ObjectToJsonUntil.toJson(res);
			logger.info("resu:" + resu);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json;charset = utf-8");
			try(PrintWriter out = response.getWriter()){
				out.write(resu);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	

}
