package com.wm.lejia.manage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.wm.lejia.manage.db.mapper")
public class ServiceManageApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceManageApplication.class, args);
	}

}
