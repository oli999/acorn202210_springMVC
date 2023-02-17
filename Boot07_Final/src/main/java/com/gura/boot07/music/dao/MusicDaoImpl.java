package com.gura.boot07.music.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gura.boot07.music.dto.MusicDto;

@Repository
public class MusicDaoImpl implements MusicDao{
	
	@Autowired SqlSession session;
	
	@Override
	public void insert(MusicDto dto) {
		/*
		 *  mapper's namespace => music
		 *  sql's id => insert
		 *  parameter type => MusicDto 
		 */
		session.insert("music.insert", dto);
	}

	@Override
	public List<MusicDto> getList() {
		
		return session.selectList("music.getList");
	}

}














