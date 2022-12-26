package com.example.demo.auto;

import org.springframework.stereotype.Component;

// component scan 을 통해서 bean 이 되도록 한다.
@Component
public class Car {
	public void drive() {
		System.out.println("달려요!");
	}
}
