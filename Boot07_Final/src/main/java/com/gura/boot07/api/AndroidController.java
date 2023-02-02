package com.gura.boot07.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 *  안드로이드의 app 의 요청을 처리할 컨트롤러
 */

@Controller
public class AndroidController {
	/*
	 *  JSON 문자열 응답하기
	 *  
	 *  1. @ResponseBody 어노테이션
	 *  2. Map 혹은 List 혹은 Dto 를 리턴하면 자동으로 JSON 문자열로 변환 되어서 응답된다. 
	 */
	@RequestMapping("/api/send")
	@ResponseBody
	public Map<String, Object> send(String msg){
		
		System.out.println(msg);
		Map<String, Object> map=new HashMap<>();
		map.put("isSuccess",true);
		map.put("response","hello client!");
		map.put("num", 1);
		
		return map;
	}
	
	@RequestMapping("/api/list")
	@ResponseBody
	public List<String> list(int pageNum){
		List<String> names=new ArrayList<>();
		names.add("김구라");
		names.add("해골");
		names.add("원숭이");
		return names;
	}
	
	@RequestMapping("/api/logincheck")
	@ResponseBody
	public Map<String, Object> logincheck(HttpSession session){
		System.out.println("세션 아이디:"+session.getId());
		Map<String, Object> map=new HashMap<>();
		String id=(String)session.getAttribute("id");
		
		if(id == null) {
			map.put("isLogin", false);
			System.out.println("로그인중이 아님요");
		}else {
			map.put("isLogin", true);
			map.put("id", id);
			System.out.println(id+" 로그인중...");
		}
		return map;
	}
	@RequestMapping("/api/login")
	@ResponseBody
	public Map<String, Object> login(String id, String pwd, HttpSession session){
		
		System.out.println(id+"|"+pwd);
		Map<String, Object> map=new HashMap<>();
		if(id.equals("gura") && pwd.equals("1234")) {
			map.put("isSuccess", true);
			map.put("id", id);
			session.setAttribute("id", id);
		}else {
			map.put("isSuccess", false);
		}
		return map;
	}
	@RequestMapping("/api/logout")
	@ResponseBody
	public Map<String, Object> logout(HttpSession session){
		session.invalidate();
		Map<String, Object> map=new HashMap<>();
		map.put("isSuccess", true);
		return map;
	}
}


















