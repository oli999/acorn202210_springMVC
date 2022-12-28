package com.gura.boot04.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDao {
	@Autowired SqlSession session;
	
	public List<MemberDto> getList(){
		return session.selectList("member.getList");
	}
}
