package com.gura.boot07.music.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public interface MusicService {
	public void saveFile(MultipartFile file, HttpServletRequest request);
}
