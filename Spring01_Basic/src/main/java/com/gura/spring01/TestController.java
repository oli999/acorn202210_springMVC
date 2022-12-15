package com.gura.spring01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
	
	@RequestMapping("/move/test")
	public String test() {
		/*
		 * Controller 메소드에서 return type 을 String 으로 설정한후
		 * 문자열을 리턴해주면 해당 문자열은 view page (jsp) 페이지의 위치가 된다.
		 * 즉 해당 jsp 페이지로 자동 forward 이동되어서 응답하게 된다.
		 */
		
		// /WEB-INF/views/move/test.jsp 페이지로 forward 이동 
		return "move/test";
	}
	
	@RequestMapping("/move/update")
	public String update() {
		//무언가 수정을 했다고 가정하자
		System.out.println("무언가 수정했습니다.");
		//클라이언트에게 새로운 경로로 요청을 다시하라고 강요하기(redirect 이동)
		
		// "redirect: 리다일렉트 이동할 절대경로(context path는 쓰지 않는다)
		return "redirect:/move/test";
	}
	
	@RequestMapping("/move/fortune")
	public ModelAndView fortune() {
		//Model 과 view page 의 위치를 동시에 담을수 있는 ModelAndView 객체 생성
		ModelAndView mView=new ModelAndView();
		
		//view page 에 전달할 모델(데이터) 라고 가정하자 
		String fortuneToday="동쪽으로 가면 귀인을 만나요!";
		
		// HttpServletRequest 객체에 담는 대신  .addObject(key, value) 형태로 ModelAndView 객체에 담으면 된다.
		mView.addObject("fortuneToday", fortuneToday);
		//view page 의 위치를 담는다.
		mView.setViewName("move/fortune");
		
		//리턴해주기 
		return mView;
	}
}









