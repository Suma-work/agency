package com.sumainfo.agency;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;



@SpringBootApplication
@MapperScan("com.sumainfo.agency.dao")
public class AgencyApplication extends SpringBootServletInitializer {

	
	  
	
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        return builder.sources(AgencyApplication.class);
    }

	public static void main(String[] args) {
		SpringApplication.run(AgencyApplication.class, args);
	}
}