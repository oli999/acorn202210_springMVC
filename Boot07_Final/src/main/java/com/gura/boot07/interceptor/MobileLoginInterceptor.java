package com.gura.boot07.interceptor;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

// bean 이 될수 있도록 어노테이션 붙이기 
@Component
public class MobileLoginInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//세션 객체의 참조값을 얻어와서 
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		//만일 로그인을 하지 않았다면
		if(id == null) {
			//인증이 되지 않았다는 에러를 응답한다.
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return false;
		}
		
		//로그인을 했다면 흐름을 이어간다.
		return true;
	}
}




