package com.gura.boot07.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.gura.boot07.interceptor.LoginInterceptor;
import com.gura.boot07.interceptor.MobileLoginInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	//로그인 인터셉터 주입 받기
	@Autowired LoginInterceptor loginInterceptor;
	@Autowired MobileLoginInterceptor mLoginInterceptor;
	
	//인터셉터 동작하도록 등록하기
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//웹브라우저의 요청에 대해 개입할 인터셉터 등록
		registry.addInterceptor(loginInterceptor)
		.addPathPatterns("/users/*","/gallery/*","/cafe/*","/file/*","/music/*")
		.excludePathPatterns("/users/signup_form", "/users/signup", "/users/loginform", "/users/login",
				"/gallery/list", "/gallery/detail",
				"/cafe/list","/cafe/detail","/cafe/ajax_comment_list",
				"/file/list","/file/download",
				"/music/login","/music/logincheck");
		
		//모바일 요청에 대해 개입할 인터셉터 등록
		registry.addInterceptor(mLoginInterceptor)
		.addPathPatterns("/api/gallery/*", "/api/music/*", "/api/gallery/insert")
		.excludePathPatterns("/api/gallery/list");
		
		
	}
	// resources 폴더안에 있는 자원을 spring 컨트롤러를 거치지 않고 응답되도록 설정
	// webapp 안에 resources 폴더를 만들어야 한다.
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
}











