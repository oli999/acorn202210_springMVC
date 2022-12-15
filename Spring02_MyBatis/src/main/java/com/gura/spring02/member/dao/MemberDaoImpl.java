package com.gura.spring02.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gura.spring02.member.dto.MemberDto;

// component scan 을 통해서 spring 이 관리하는 bean 이 될수 있도록 어노테이션 붙여놓기
@Repository
public class MemberDaoImpl implements MemberDao{
	
	@Autowired
	private SqlSession session; // select, insert, update, delete 작업을 하기 위한 핵심 의존 객체
	
	@Override
	public void insert(MemberDto dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(MemberDto dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MemberDto getData(int num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MemberDto> getList() {
		/*
		 *  mapper's namespace => member
		 *  sql's id => getList
		 *  resultType => MemberDto
		 *  return type => List
		 */
		List<MemberDto> list=session.selectList("member.getList");
		
		return list;
	}

}









