package com.algebra.drools;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@MapperScan("com.sky.bluesky.mapper")
public class DroolsApplication extends SpringBootServletInitializer {

	static Logger logger = LoggerFactory.getLogger(DroolsApplication.class);

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DroolsApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(DroolsApplication.class, args);
	}
}
