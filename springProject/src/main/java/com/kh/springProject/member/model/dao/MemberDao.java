package com.kh.springProject.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.springProject.member.model.vo.Member;

@Repository("mDao")
// @Component보다 구체화된 개념
// DB와 접근하는 클래스에 부여하는 어노테이션
public class MemberDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	// pom.xml가서 오라클 및 mybatis 관련 라이브러리 dependancy들 추가하고 오자.
	// 그리고 나서 import하고 import가 잘 되는지 확인
	// 그러고 나서, root-context.xml에 datasource 등록하자.
	
	public Member selectMember(Member m) {
		
		return sqlSessionTemplate.selectOne("memberMapper.selectOne", m);
	}

	public int insertMember(Member m) {
		
		return sqlSessionTemplate.insert("memberMapper.insertMember", m);
	}

	public int updateMember(Member m) {
		
		return sqlSessionTemplate.update("memberMapper.updateMember", m);
	}

	public int deleteMember(String id) {
		
		return sqlSessionTemplate.update("memberMapper.deleteMember", id);
	}

	public int checkIdDup(String id) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("memberMapper.checkIdDup", id);
	}

}
