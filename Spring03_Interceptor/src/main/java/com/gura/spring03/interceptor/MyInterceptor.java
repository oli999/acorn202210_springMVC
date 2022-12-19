package com.gura.spring03.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


/*
 *   [ 인터 셉터 만들기 ]
 *   
 *   1. HandlerInterceptor 인터페이스를 구현해서 만든다.
 *   2. servlet-context.xml 에 bean 설정을하고  interceptor 목록에 등록을하고 맵핑을 해준다.
 */
public class MyInterceptor implements HandlerInterceptor{
	
	//Controller 실행 이전에 호출되는 메소드 
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("이전!");
		/*
		 *  true 를 리턴하면 계속 흐름을 이어가고 false 를 리턴하면 이어가지 않는다. 
		 */
		return true;
	}
	
	//Constroller 실행 이후에 호출되는 메소드 
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("이후!");
	}
	//응답된 이후에 호출되는 메소드 
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("응답이후!");
	}

}



