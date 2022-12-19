package com.gura.spring03.users.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UsersController {
	//로그인 폼 요청 처리
	@RequestMapping("/users/loginform")
	public String loginform() {
		return "users/loginform";
	}
	//로그인 요청 처리
	@RequestMapping("/users/login")
	public String login(String id, HttpSession session){
		session.setAttribute("id", id);
		return "users/login";
	}
	//로그아웃 요청 처리
	@RequestMapping("/users/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "users/logout";
	}
	//개인정보 보기 요청처리
	@RequestMapping("/users/info")
	public String info() {
		return "users/info";
	}
}











