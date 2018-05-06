package com.wm.lejia;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wm.lejia.db.mapper")
public class LejiaBootApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(LejiaBootApplication.class, args);
	}
}
