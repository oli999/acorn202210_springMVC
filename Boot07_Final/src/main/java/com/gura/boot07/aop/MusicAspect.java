package com.gura.boot07.aop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gura.boot07.exception.ForbiddenException;
import com.gura.boot07.music.dao.MusicDao;

@Aspect
@Component
public class MusicAspect {
	
	@Autowired MusicDao dao;
	
	/*
	 *  com.gura.boot07.music.controller.MusicController 클래스의
	 *  check 로 시작되는 모든 메소드가 point cut 이 된다.
	 *  return type 은 * 로 되어 있기때문에 어떤 type 이든 가능하다  
	 */
	@Around("execution(* com.gura.boot07.music.controller.MusicController.check*(..))")
	public Object checkWriter(ProceedingJoinPoint joinPoint) throws Throwable  {
		//메소드에 전달된 인자들 목록을 얻어내기
		Object[] args=joinPoint.getArgs();
		
		int num=0;
		HttpServletRequest request=null;
		
		//반복문 돌면서 원하는 type 의 값을 찾아서 작업한다.
		for(Object tmp:args) {
			//만일 Integer type 이면
			if(tmp instanceof Integer) {
				//casting 해서 담는다.
				num=(int)tmp;
			}
			if(tmp instanceof HttpServletRequest) {
				request=(HttpServletRequest)tmp;
			}
		}
		// mp3 파일의 소유자 알아내기 
		String writer=dao.getData(num).getWriter();
		// 로그인된 아이디
		HttpSession session=request.getSession();
		String id=(String)session.getAttribute("id");
		System.out.println("writer:"+writer);
		System.out.println("id:"+id);
		// 만일 mp3 파일의 소유자와 로그인된 아이디가 다르다면  
		if(!writer.equals(id)) {
			//예외를 발생시켜서 ExceptionController 에서 처리되도록 한다.
			//throw new RuntimeException("오잉?");
			throw new ForbiddenException("오잉?");
		}
		//aspect 가 적용된 메소드가 호출 되기 직전에 할 작업은 여기서 한다. 
		Object obj=joinPoint.proceed();
		// aspect 가 적용된 메소드 수행하기 
		return obj;
	}
}










