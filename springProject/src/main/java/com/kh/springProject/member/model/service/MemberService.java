package com.kh.springProject.member.model.service;

import com.kh.springProject.member.model.vo.Member;

public interface MemberService {
	Member loginMember(Member m);

	int insertMember(Member m);

	int updateMember(Member m);

	int deleteMember(String id);
}
