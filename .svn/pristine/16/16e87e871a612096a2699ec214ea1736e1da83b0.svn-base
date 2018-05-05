package com.sumainfo.agency;

import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.sumainfo.agency.service.VehicleOrdersService;
import com.sumainfo.common.until.ToolsUntil;

/**
 * 项目启动后自动加载信息
 * @author Administrator
 *
 */
@Component
public class LoadData implements CommandLineRunner{

	@Autowired
	private VehicleOrdersService impl;
	
	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
		TimerUntil(ToolsUntil.SEC);//定时任务 30秒刷新一次
	}
	
	public void TimerUntil(int sec){
		Timer timer = new Timer();
		timer.schedule(new RemindTask(),0,sec*1000);
	}

	class RemindTask extends TimerTask {
		@Override  
	    public void run() {
			//获取客户回复信息
			impl.smsNotify();
	    }  

    }

}
