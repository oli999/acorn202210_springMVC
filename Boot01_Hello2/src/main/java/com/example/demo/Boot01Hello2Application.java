package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.demo.auto.Car;
import com.example.demo.auto.LegacyCar;
import com.example.demo.auto.MyCar;

@SpringBootApplication
public class Boot01Hello2Application {
	//Spring Boot Application 이 시작되는 main 메소드
	public static void main(String[] args) {
		 ApplicationContext ctx=SpringApplication.run(Boot01Hello2Application.class, args);
		 // getBean( 원하는 type ) 
		 Car c1=ctx.getBean(Car.class);
		 c1.drive();
		 
		 MyCar c2=ctx.getBean(MyCar.class);
		 c2.drive();
		 
		 //resources 폴더의 config.xml 문서를 로딩해서 bean 을 생성해서 관리하기 (예전방식)
		 ApplicationContext ctx2=new ClassPathXmlApplicationContext("config.xml");
		 
		 LegacyCar c3=ctx2.getBean(LegacyCar.class);
		 c3.drive();
	}

}











