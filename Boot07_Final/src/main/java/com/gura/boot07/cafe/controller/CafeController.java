package com.gura.boot07.cafe.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gura.boot07.cafe.dto.CafeCommentDto;
import com.gura.boot07.cafe.dto.CafeDto;
import com.gura.boot07.cafe.service.CafeService;

@Controller
public class CafeController {
	@Autowired
	private CafeService service;
	
	@RequestMapping("/cafe/list")
	public String list(HttpServletRequest request) {
		service.getList(request);
		return "cafe/list";
	}
	
	@RequestMapping("/cafe/insertform")
	public String insertform() {
		return "cafe/insertform";
	}
	
	@RequestMapping("/cafe/insert")
	public String insert(CafeDto dto, HttpSession session) {
		//글 작성자는 세션에서 얻어낸다.
		String writer=(String)session.getAttribute("id");
		//dto 는 글의 제목과 내용만 있으므로 글작성자는 직접 넣어준다.
		dto.setWriter(writer);
		service.saveContent(dto);
		return "cafe/insert";
	}
	
	@RequestMapping("/cafe/detail")
	public String detail(HttpServletRequest request) {
		
		service.getDetail(request);
		
		return "cafe/detail";
	}
	
	@RequestMapping("/cafe/delete")
	public String delete(int num, HttpServletRequest request) {
		service.deleteContent(num, request);
		return "redirect:/cafe/list";
	}
	
	@RequestMapping("/cafe/updateform")
	public String updateForm(HttpServletRequest request) {
		service.getData(request);
		return "cafe/updateform";
	}
	
	@RequestMapping("/cafe/update")
	public String update(CafeDto dto) {
		service.updateContent(dto);
		return "cafe/update";
	}
	
	//새로운 댓글 저장 요청 처리
	@RequestMapping("/cafe/comment_insert")
	public String commentInsert(HttpServletRequest request, int ref_group) {
		
		service.saveComment(request);
	
		return "redirect:/cafe/detail?num="+ref_group;
	}
	//댓글 더보기 요청 처리
	@RequestMapping("/cafe/ajax_comment_list")
	public String commentList(HttpServletRequest request) {
		
		//테스트를 위해 시간 지연 시키기
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		service.moreCommentList(request);
		
		return "cafe/ajax_comment_list";
	}
	//댓글 삭제 요청 처리
	@RequestMapping("/cafe/comment_delete")
	@ResponseBody
	public Map<String, Object> commentDelete(HttpServletRequest request) {
		service.deleteComment(request);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("isSuccess", true);
		// {"isSuccess":true} 형식의 JSON 문자열이 응답되도록 한다. 
		return map;
	}
	//댓글 수정 요청처리 (JSON 을 응답하도록 한다)
	@RequestMapping("/cafe/comment_update")
	@ResponseBody
	public Map<String, Object> commentUpdate(CafeCommentDto dto, HttpServletRequest request){
		service.updateComment(dto);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("isSuccess", true);
		// {"isSuccess":true} 형식의 JSON 문자열이 응답되도록 한다. 
		return map;
	}
}




















