package com.sjc.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
@EnableScheduling
@EnableAdminServer 
@SpringBootApplication
@MapperScan(basePackages = "com.sjc.app.**.mapper")
public class SjcApplication {

	public static void main(String[] args) {
		SpringApplication.run(SjcApplication.class, args);
	}

}
