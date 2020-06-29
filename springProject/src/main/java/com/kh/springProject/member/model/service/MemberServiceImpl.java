package com.kh.springProject.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.springProject.member.model.dao.MemberDao;
import com.kh.springProject.member.model.vo.Member;

// @Component는 단순한 빈으로 등록하기 위한 어노테이션이었지만
// @Service는 보다 구체화 된 즉, Service의 의미를 가지는 빈 객체라는걸 보여주기 위한 어노테이션
// ("")을 통해서 빈으로 등록할 때의 이름을 지정해 줄 수 있다.
// (안적으면 클래스명의 첫글자를 소문자로 하는 이름으로 빈 객체가 등록 됨)

@Service("mService")
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	MemberDao mDao;

	@Override
	public Member loginMember(Member m) {
		// 스프링 적용 이후 Service단에서 sqlSession을 생성하지 않는다.
		// Dao에서 DI를 통해서 sqlSession을 생성한다.
		return mDao.selectMember(m);
	}

	@Override
	public int insertMember(Member m) {
		
		return mDao.insertMember(m);
	}

	@Override
	public int updateMember(Member m) {
		
		return mDao.updateMember(m);
	}

	@Override
	public int deleteMember(String id) {
		
		return mDao.deleteMember(id);
	}

}
