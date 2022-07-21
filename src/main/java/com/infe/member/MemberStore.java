package com.infe.member;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberStore {
	
	@Autowired
	SqlSession sqlSession;
	
	public int enrollMember(Member member) {
		return sqlSession.insert("memberMapper.enrollMember", member);
	}

	public Member loginMember(Member mOne) {
		return sqlSession.selectOne("memberMapper.loginMember", mOne);
	}

}
