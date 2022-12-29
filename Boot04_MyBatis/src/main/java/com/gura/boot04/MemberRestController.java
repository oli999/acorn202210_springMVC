package com.gura.boot04;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gura.boot04.member.dao.MemberDao;
import com.gura.boot04.member.dao.MemberDto;

// 1. @RestController 를 이용해서 Controller 를 만들고 
@RestController
public class MemberRestController {
	
	@Autowired MemberDao dao;
	
	//2. 메소드에서 어떤 data 를 리턴하면 리턴되는 데이터가 클라이언트에게 바로 응답된다.
	// String 은 내용 그대로 응답되고,  dto, Map, List 등은 JSON 문자열로 변환되어서 응답된다.
	@GetMapping("/member/list")
	public List<MemberDto> list(){
		
		return dao.getList();
	}
}










