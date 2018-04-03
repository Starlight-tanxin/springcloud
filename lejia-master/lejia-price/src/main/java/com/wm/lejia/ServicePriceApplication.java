package com.wm.lejia;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@MapperScan("com.wm.lejia.db.mapper")
public class ServicePriceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicePriceApplication.class, args);
	}

}
