package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 *  어떤 객체를 spring 이 생성해서 관리 할지 설정( bean 설정 )
 */
@Configuration
public class JavaConfig {
	
	//이 메소드에서 리턴되는 객체를 spring 이 관리하는 bean 이 되도록한다. 
	@Bean
	public Car myCar() {
		System.out.println("myCar 메소드 호출됨");
		Car c1=new Car(); 
		return c1;
	}
}
