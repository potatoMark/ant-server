package com.framework;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableCaching
@MapperScan("com.framework.modules.*.dao")
public class AntFrameworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(AntFrameworkApplication.class, args);
	}

}
