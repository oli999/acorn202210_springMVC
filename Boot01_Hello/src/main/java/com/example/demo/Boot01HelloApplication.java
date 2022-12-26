package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Boot01HelloApplication {
	
	public static void main(String[] args) {
		//SpringApplication 클래스의 static 메소드 run() 을 호출하면 ApplicationContext 객체가 리턴된다. 
		ApplicationContext ctx=SpringApplication.run(Boot01HelloApplication.class, args);
		//spring 이 관리하는 bean 중에서 Car type 을 찾아서 받아오기 (객체는 한번만 생성된다)
	 	Car car1=ctx.getBean(Car.class); 
	 	Car car2=ctx.getBean(Car.class);
	 	
	 	car1.drive();
	 	car2.drive();
	}

}
