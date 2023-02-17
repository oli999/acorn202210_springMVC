package com.gura.boot07.music.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

public interface MusicService {
	public void saveFile(MultipartFile file, HttpServletRequest request);
	public void getList(ModelAndView mView, HttpSession session);
}
