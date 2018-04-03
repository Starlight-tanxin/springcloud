package com.wm.lejia.feign.config;

import static java.util.concurrent.TimeUnit.SECONDS;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;
import feign.Retryer;

@Configuration
public class FeignConfig {
	
	@Bean
	public Retryer feignRetryer() {
		return new Retryer.Default(300, SECONDS.toMillis(3), 5);
	}
	
	@Bean
	Logger.Level feignLoggerLevel(){
		return Logger.Level.FULL;
	}
}
