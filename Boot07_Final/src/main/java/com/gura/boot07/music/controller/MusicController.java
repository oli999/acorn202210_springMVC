package com.gura.boot07.music.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.gura.boot07.music.service.MusicService;

@Controller
public class MusicController {
	
	@Autowired MusicService service;
	
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
	
}











