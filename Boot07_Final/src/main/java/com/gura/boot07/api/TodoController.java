package com.gura.boot07.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * 	spring 5.0 부터 추가된 RestController 어노테이션을 붙이면
 * 
 *  json 문자열을 응답할때 @ResponseBody 를 생략할수 있다. 
 *  
 */
@RestController
public class TodoController {
	
	@Autowired
	private TodoDao dao;
	
	//할일 추가 요청 처리
	@PostMapping("/api/todo/insert")
	public Map<String, Object> insert(String content){
		//할일을 TodoDto 에 담고 
		TodoDto dto=new TodoDto();
		dto.setContent(content);
		//TodoDao 를 이용해서 DB 에 저장하고 
		dao.insert(dto);
		//결과를 json 문자열로 응답하기
		Map<String, Object> map=new HashMap<>();
		map.put("isSuccess", true);
		return map;  // {"isSuccess":true} 형식의 json 문자열이 응답된다. 
	}
	//할일 목록 요청 처리 
	@GetMapping("/api/todo/list")
	public List<TodoDto> list(){
		
		return dao.getList(); // [{"num":x, "content":"xxx", "regdate":"xxx"},{},{},...] 형식의 json  
	}
	
	//할일 삭제 요청 처리
	@PostMapping("/api/todo/delete")
	public Map<String, Object> delete(int num){
		//TodoDao 를 이용해서 삭제
		dao.delete(num);
		
		//결과를 json 문자열로 응답하기
		Map<String, Object> map=new HashMap<>();
		map.put("isSuccess", true);
		return map;  // {"isSuccess":true} 형식의 json 문자열이 응답된다. 
	}
}















