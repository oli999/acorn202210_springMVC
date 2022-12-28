package com.gura.boot03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 *  Spring Boot Web 은 내장된 tomcat 서버가 있다. 
 *  그래서 따로 tomcat 서버를 잡을 필요가 없다. 
 */
@SpringBootApplication
public class Boot03ControllerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Boot03ControllerApplication.class, args);
	}

}
