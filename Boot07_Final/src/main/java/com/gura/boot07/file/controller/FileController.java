package com.gura.boot07.file.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gura.boot07.file.dto.FileDto;
import com.gura.boot07.file.service.FileService;

@Controller
public class FileController {
	
	@Autowired
	private FileService service;
	
	@Value("${file.location}")
	private String fileLocation;
	
	@RequestMapping("/file/list")
	public String list(HttpServletRequest request) {
		
		service.getList(request);
		
		return "file/list";
	}
	
	@RequestMapping("/file/upload_form")
	public String uploadForm() {
		
		return "file/upload_form";
	}
	
	@RequestMapping("/file/upload")
	public ModelAndView upload(FileDto dto, ModelAndView mView, HttpServletRequest request) {
		service.saveFile(dto, mView, request);
		mView.setViewName("file/upload");
		return mView;
	}
	/*
	 * 컨트롤러에서 파일을 직접 다운로드 시켜주기
	 * 
	 * 1. ResponseEntity 객체에 다운로드 해줄 파일의 정보를 담고
	 * 2. ResponseEntity<InputStreamResource> 객체를 리턴해준다.
	 */
	@GetMapping("/file/download")
	public ResponseEntity<InputStreamResource> download(int num) throws UnsupportedEncodingException, FileNotFoundException {
		FileDto dto=service.getFileData(num);
		//다운로드 시켜줄 원본 파일명
		String encodedName=URLEncoder.encode(dto.getOrgFileName(), "utf-8");
		//파일명에 공백이 있는경우 파일명이 이상해지는걸 방지
		encodedName=encodedName.replaceAll("\\+"," ");
		//응답 헤더정보 구성하기 (웹브라우저에 알릴정보)
		HttpHeaders headers=new HttpHeaders();
		//파일을 다운로드 시켜 주겠다는 정보
		headers.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream"); 
		//파일의 이름 정보(웹브라우저가 해당정보를 이용해서 파일을 만들어 준다)
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="+encodedName);	
		//파일의 크기 정보
		headers.setContentLength(dto.getFileSize());
		//읽어들일 파일의 경로 구성 
		String path=fileLocation + File.separator + dto.getSaveFileName();
		//파일에서 읽어들일 스트림 객체
		InputStream is=new FileInputStream(new File(path));
		/*
		 * 미리 준비된 헤더정보와 추가 헤더정보, 파일에서 읽어들을 스트림 객체를 이용해서 
		 * ResponseEntity 객체의 참조값을 얻어낸다. 
		 */
		ResponseEntity<InputStreamResource> re=ResponseEntity.ok()
		.headers(headers)
		.header("Content-Transfer-Encoding", "binary")
		.body(new InputStreamResource(is));
		
		//ResponseEntity 객체를 리턴해주면 알아서 파일이 다운로드가 된다. 
		return re;
	}
	
	@RequestMapping("/file/delete")
	public ModelAndView delete(int num, ModelAndView mView, HttpServletRequest request) {
		service.deleteFile(num, request);
		mView.setViewName("redirect:/file/list");
		return mView;
	}
}
























