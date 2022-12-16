package com.gura.spring02.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.gura.spring02.member.dto.MemberDto;
import com.gura.spring02.member.service.MemberService;

@Controller
public class MemberController {
	
	//필요한 핵심 의존 객체를 주입 받는다.
	@Autowired
	private MemberService service;
	
	@RequestMapping("/member/update")
	public String update(MemberDto dto) {
		//서비스를 이용해서 회원 한명의 정보 수정
		service.updateMember(dto);
		
		return "member/update";
	}
	
	@RequestMapping("/member/updateform")
	public ModelAndView updateform(int num, ModelAndView mView) {//ModelAndView 객체도 받을수 있다.
		//서비스를 이용해서 회원 한명의 정보를 ModelAndView 객체에 담는다.
		service.getMember(num, mView);
		//view page 의 정보도 담아서 
		mView.setViewName("member/updateform");
		//리턴해준다.
		return mView;
	}
	
	@RequestMapping("/member/delete")
	public String delete(int num) {// get 방식 전송 파라미터도 추출 가능  ?num=x
		//서비스를 이용해서 회원 한명의 정보 삭제
		service.deleteMember(num);
		return "redirect:/member/list";
	}
	
	@RequestMapping("/member/insert")
	public String insert(MemberDto dto) {//폼전송되는 name, addr 이 자동으로 추출되어서 MemberDto 에 담겨서 전달된다.
		//서비스를 이용해서 회원 한명의 정보 추가
		service.addMember(dto);
		return "member/insert";
	}
	
	@RequestMapping("/member/insertform")
	public String insertform() {
		// /WEB-INF/views/member/insertform.jsp 로 forward 이동해서 응답
		return "member/insertform";
	}
	
	@RequestMapping("/member/list")
	public ModelAndView getList(ModelAndView mView) {
		
		//서비스를 이용해서 회원 전체 목록을 ModelAndView 객체에 담는다.
		service.getListMember(mView);
		
		// view page 정보도 담아서 
		mView.setViewName("member/list");
		
		//리턴해 준다. 
		return mView;
	}
}













