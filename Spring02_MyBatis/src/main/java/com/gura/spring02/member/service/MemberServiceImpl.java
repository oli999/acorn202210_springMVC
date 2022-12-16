package com.gura.spring02.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring02.member.dao.MemberDao;
import com.gura.spring02.member.dto.MemberDto;

//component scan 을 통해서 스프링이 관리하는 bean 이 되게 하기 위해 필요한 어노테이션
@Service
public class MemberServiceImpl implements MemberService{
	
	//핵심 의존 객체 주입 받기 
	@Autowired
	private MemberDao dao;
	
	@Override
	public void addMember(MemberDto dto) {
		dao.insert(dto);
	}

	@Override
	public void updateMember(MemberDto dto) {
		dao.update(dto);
	}

	@Override
	public void deleteMember(int num) {
		dao.delete(num);
	}

	@Override
	public void getMember(int num, ModelAndView mView) {
		//회원 한명의 정보를 얻어와서 
		MemberDto dto=dao.getData(num);
		//Controller 에서 전달한 ModelAndView 객체에 담는다. (request scope 에 담기 위해)
		mView.addObject("dto", dto);
	}

	@Override
	public void getListMember(ModelAndView mView) {
		//회원 목록을 얻어와서
		List<MemberDto> list=dao.getList();
		//Controller 에서 전달한 ModelAndView 객체에 담는다. (request scope 에 담기 위해)
		mView.addObject("list", list);
	}

}












