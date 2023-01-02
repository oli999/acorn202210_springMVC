package com.gura.boot07;


import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeContoller {
	
	@GetMapping("/")
	public String home(HttpServletRequest request) {
		
		List<String> noticeList=new ArrayList<>();
		noticeList.add("Spring Boot 시작 입니다.");
		noticeList.add("어쩌구...");
		noticeList.add("저쩌구...");
		
		request.setAttribute("noticeList", noticeList);
		
		return "home";
	}
	
}
























