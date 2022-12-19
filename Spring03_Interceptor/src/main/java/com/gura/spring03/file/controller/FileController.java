package com.gura.spring03.file.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileController {
	
	@RequestMapping("/file/insertform")
	public String insertform() {
		
		return "file/insertform";
	}
	
	@RequestMapping("/file/upload")
	public String upload(String title, MultipartFile myFile, HttpServletRequest request) {
		//1. 원본 파일명
		String orgFileName=myFile.getOriginalFilename();
		//2. 파일의 크기
		long fileSize=myFile.getSize();
		
		// webapp/upload 폴더 까지의 실제 경로(서버의 파일시스템 상에서의 경로)
		String realPath=request.getServletContext().getRealPath("/upload");
		//저장할 파일의 상세 경로
		String filePath=realPath+File.separator;
		//디렉토리를 만들 파일 객체 생성
		File upload=new File(filePath);
		if(!upload.exists()) {//만일 디렉토리가 존재하지 않으면 
			upload.mkdir(); //만들어 준다.
		}
		//저장할 파일 명을 구성한다.
		String saveFileName=
				System.currentTimeMillis()+orgFileName;
		try {
			//3. 임시 폴더에 저장된 업로드된 파일을 원하는곳에 원하는 이름으로 이동시키기(전송하기)
			myFile.transferTo(new File(filePath+saveFileName));
			System.out.println(filePath+saveFileName);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "file/upload";
	}
}











