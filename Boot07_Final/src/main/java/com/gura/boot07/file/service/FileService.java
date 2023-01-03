package com.gura.boot07.file.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.gura.boot07.file.dto.FileDto;

public interface FileService {
	//파일 목록 얻어오기 
	public void getList(HttpServletRequest request);
	//업로드된 파일 저장하기 
	public void saveFile(FileDto dto, ModelAndView mView,
			HttpServletRequest request);
	//파일하나의 정보 얻어오기 
	public FileDto getFileData(int num);
	//파일 삭제하기
	public void deleteFile(int num, HttpServletRequest request);
}
