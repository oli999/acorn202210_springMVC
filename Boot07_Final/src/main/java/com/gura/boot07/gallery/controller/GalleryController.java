package com.gura.boot07.gallery.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gura.boot07.gallery.dao.GalleryDao;
import com.gura.boot07.gallery.dto.GalleryDto;
import com.gura.boot07.gallery.service.GalleryService;



@Controller
public class GalleryController {
	
	@Autowired
	private GalleryService service;
	@Autowired 
	private GalleryDao dao;
	
	//gallery list 페이지로 이동
	@RequestMapping(value = "/gallery/list")
	public String getList(HttpServletRequest request) {
		//view 페이지에 사용될 데이터는 request 영역에 담는다.
		service.getList(request);
		
		return "gallery/list";
	}
	
	//gallery 사진 업로드 form 페이지로 이동
	@RequestMapping(value = "/gallery/upload_form")
	public String uploadForm() {
		
		return "gallery/upload_form";
	}
	
	//gallery 사진 업로드 & DB 저장
	@RequestMapping(value = "/gallery/upload")
	public String upload(GalleryDto dto, HttpServletRequest request) {
		//form 에서 dto 로 데이터 받아옴
		//dto : caption, MultipartFile image 를 가지고 있다.
		//request :  imagePath 만드는데 사용, session 영역의 id 가져오는데 사용
		service.saveImage(dto, request);
		
		return "gallery/upload";
	}
	
	//gallery 사진 업로드 form - ajax form
	@RequestMapping(value = "/gallery/ajax_form")
	public String ajaxForm() {
		
		return "gallery/ajax_form";
	}

	//gallery 사진 업로드 - ajax
	//json 으로 return 할 것
	@RequestMapping(value = "/gallery/ajax_upload")
	@ResponseBody
	public Map<String, Object> ajaxUpload(GalleryDto dto, HttpServletRequest request){		
		//form 에서 dto 로 데이터 받아옴
		//dto : MultipartFile image 를 가지고 있다.
		//request : imagePath 만드는데 사용, session 영역의 id 가져오는데 사용
		//return : { "imagePath" : "/upload/123456img_name.png" } 형식의 JSON 응답
		return service.uploadAjaxImage(dto, request);
	}
	
	//imagePath 구성 X -> dto 로 imagePath 를 받아서 DB 에 저장하기
	@RequestMapping("/gallery/insert")
	public String insert(GalleryDto dto, HttpServletRequest request) {
		//dto : caption, imagePath 가지고 있다.
		//request : dto 에 writer(id) 추가
		service.insert(dto, request);
		
		return "gallery/upload";
	}
	
	//gallery 게시글의 num 이 parameter get 방식으로 넘어온다.
	//detail 페이지
	@RequestMapping(value = "/gallery/detail", method = RequestMethod.GET)
	public ModelAndView detail(ModelAndView mView, int num) {
		//갤러리 detail 페이지에 필요한 data를 num 으로 가져와, ModelAndView 에 저장
		service.getDetail(mView, num);
		mView.setViewName("gallery/detail");
		
		return mView;
	}
	//안드로이드 앱에서 사진을 업로드하는 요청을 처리하는 메소드 
	@PostMapping("/api/gallery/insert")
	@ResponseBody
	public Map<String, Object> apiInsert(GalleryDto dto, HttpServletRequest request){
		/*
		 *  GalleryDto 에 담긴 내용을 활용해서 이미지를 파일시스템에 저장하고 이미지 정보를 DB 에도 저장한다.
		 */
		service.saveImage(dto, request);
		Map<String, Object> map=new HashMap<>();
		map.put("isSuccess", true);
		return map;
	}
	
	@GetMapping("/api/gallery/list")
	@ResponseBody
	public List<GalleryDto> apiList(HttpSession session, HttpServletResponse response, 
			HttpServletRequest request){
		System.out.println(session.getId());
		
		Cookie[] cooks=request.getCookies();
		for(Cookie tmp : cooks) {
			System.out.println(tmp.getName()+":"+tmp.getValue());
		}
		
		Cookie cook1=new Cookie("name", "kimgura");
		response.addCookie(cook1);
		
		return dao.getListAll();
	}
}











