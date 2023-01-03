package com.gura.boot07;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:custom.properties") //resources/custom.properties 로딩하기 
public class Boot07FinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(Boot07FinalApplication.class, args);
	}

}
