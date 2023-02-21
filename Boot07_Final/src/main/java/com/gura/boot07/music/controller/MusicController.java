package com.gura.boot07.music.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gura.boot07.music.dao.MusicDao;
import com.gura.boot07.music.dto.MusicDto;
import com.gura.boot07.music.service.MusicService;
import com.gura.boot07.users.dao.UsersDao;
import com.gura.boot07.users.dto.UsersDto;

@Controller
public class MusicController {
	
	@Autowired MusicService service;
	@Autowired UsersDao usersDao;
	@Autowired MusicDao musicDao;
	
	@RequestMapping("/music/login")
	@ResponseBody
	public Map<String, Object> login(UsersDto dto, HttpSession session){
		Map<String, Object> map=new HashMap<>();
		boolean isValid=false;
		//입력한 아이디를 이용해서 DB 에서 정보를 읽어온다. 
		UsersDto resultDto=usersDao.getData(dto.getId());
		//만일 실제로 존재하는 아이디라면 
		if(resultDto != null) {
			//입력한 비밀번호와 DB 에 저장된 암호화된 비밀번호를 비교해서 일치 여부를 얻어낸다.
			isValid = BCrypt.checkpw(dto.getPwd(), resultDto.getPwd());
		}
		//만일 비밀번호도 일치한다면
		if(isValid) {
			//로그인 처리를 한다. 
			session.setAttribute("id", dto.getId());
			//아이디도 담는다.
			map.put("id", dto.getId());
		}
		//로그인 성공여부를 담는다. 
		map.put("isSuccess", isValid);
		System.out.println(dto.getId()+"|"+dto.getPwd()+"|"+isValid);
		return map;
	}
	
	
	//로그인 체크
	@RequestMapping("/music/logincheck")
	@ResponseBody
	public Map<String, Object> loginCheck(HttpSession session){
		String id=(String)session.getAttribute("id");
		Map<String, Object> map=new HashMap<>();
		if(id==null) {
			map.put("isLogin", false);
		}else {
			map.put("isLogin", true);
			map.put("id", id);
		}
		return map;
	}
	
	//음악파일 업로드 폼 요청 처리 
	@RequestMapping("/music/insertform")
	public String insertForm() {
		
		return "music/insertform";
	}
	//음악파일 업로드 요청처리 
	@RequestMapping("/music/insert")
	public String insert(MultipartFile file, HttpServletRequest request) {
		
		service.saveFile(file, request);
		
		return "redirect:/music/list";		
	}
	
	//음악 파일 목록 요청처리
	@RequestMapping("/music/list")
	public ModelAndView list(ModelAndView mView, HttpSession session) {
		
		service.getList(mView, session);
		
		mView.setViewName("music/list");
		return mView;
	}

	@RequestMapping("/api/music/list")
	@ResponseBody
	public List<MusicDto> list2(HttpSession session){
		//로그인된 아이디를 읽어온다
		String id=(String)session.getAttribute("id");
		//해당 사용자가 업로드한 음악 파일 목록을 읽어와서
		List<MusicDto> list=musicDao.getList(id);
		//ResponseBody 로 응답한다(json 문자열 응답)
		return list;
	}
	
	
	/*
	 * 	get 방식 파라미터로 전달되는 num 에 해당하는 음악 하나의 정보를 json 형식의 문자열로 응답하는 컨트롤러 메소드
	 * {"writer":"xxx", "title":"xxx", "saveFileName":"xxx", .... } 
	 */
	@RequestMapping("/music/detail")
	@ResponseBody
	public MusicDto checkDetail(int num, HttpServletRequest request) {
		
		return service.getDetail(num);
	}
	
	@GetMapping("/music/delete")
	public String checkDelete(int num, HttpServletRequest request) {
		
		service.deleteFile(num, request);
		
		return "redirect:/music/list";
	}
	
}











