package com.gura.boot07.exception;

public class ForbiddenException extends RuntimeException{
	
	//생성자
	public ForbiddenException(String msg) {
		super(msg);
	}

}
