package com.gura.boot07.gallery.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gura.boot07.gallery.dto.GalleryDto;
import com.gura.boot07.gallery.service.GalleryService;



@Controller
public class GalleryController {
	
	@Autowired
	private GalleryService service;

	@Value("${file.location}")
	private String fileLocation;
	
	//프로필 이미지 요청에 대한 응답을 할 메소드를 따로 만들어야 한다.
	//이미지 데이터가 응답된어야 한다
	//웹브라우저에게 이미지 데이터를 응답한다고 알려야 한다.
	//응답할 이미지의 이름은 그때 그때 다르다.
	
	/*
	 *  이 컨트롤러 메소드에서 응답한 byte[] 배열을 클라이언트에게 응답하는 방법
	 *  1. @ResponseBody
	 *  2. byte[] 배열 리턴 
	 *  
	 *  응답된 byte[] 배열에 있는 데이터를 이미지 데이터로 클라이언트 웹브라우저가 인식하게 하는 방법
	 *  produces = MediaType.IMAGE_JPEG_VALUE 
	 */
	@GetMapping(
		value="/gallery/images/{imageName}",
		produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_GIF_VALUE}
	)
	@ResponseBody
	public byte[] galleryImage(@PathVariable("imageName") String imageName) throws IOException {
		
		String absolutePath=fileLocation+File.separator+imageName;
		//파일에서 읽어들일 InputStream
		InputStream is=new FileInputStream(absolutePath);
		// 이미지 데이터(byte) 를 읽어서 배열에 담아서 클라이언트에게 응답한다.
		return IOUtils.toByteArray(is);
	}	
	
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
		//return : { "imagePath" : "123456img_name.png" } 형식의 JSON 응답
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
	
}
