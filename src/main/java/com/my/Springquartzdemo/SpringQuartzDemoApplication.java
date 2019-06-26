package com.my.Springquartzdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.my.Springquartzdemo.dao")
public class SpringQuartzDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringQuartzDemoApplication.class, args);
	}

}
