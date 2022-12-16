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
		/*
		 *  mapper's namespace => member
		 *  sql's id => insert
		 *  parameterType => MemberDto 
		 */
		session.insert("member.insert", dto);
	}

	@Override
	public void update(MemberDto dto) {
		/*
		 *  mapper's namespace => member
		 *  sql's id => update
		 *  parameterType => MemberDto 
		 */
		session.update("member.update", dto);
	}

	@Override
	public void delete(int num) {
		/*
		 *  mapper's namespace => member
		 *  sql's id => delete
		 *  parameterType => int 
		 */
		session.delete("member.delete", num);
	}

	@Override
	public MemberDto getData(int num) {
		/*
		 *  mapper's namespace => member
		 *  sql's id => getData
		 *  parameterType => int
		 *  
		 *  - selectOne() 메소드를 호출하면 mapper xml 의 resultType 이 바로 메소드의 return type 이 된다. 
		 *  
		 *  resultType => MemberDto
		 *  return type => MemberDto
		 */
		MemberDto dto=session.selectOne("member.getData", num);
		
		return dto;
	}

	@Override
	public List<MemberDto> getList() {
		/*
		 *  mapper's namespace => member
		 *  sql's id => getList
		 *  
		 *  - selectList() 메소들 호출하면 List 가 return type 이고  mapper xml 의 resultType 이 바로
		 *    List 의 generic type 이 된다. 
		 *  
		 *  resultType => MemberDto
		 *  return type => List
		 */
		List<MemberDto> list=session.selectList("member.getList");
		
		return list;
	}

}









