package com.kh.springProject.notice.model.service;

import java.util.ArrayList;

import com.kh.springProject.notice.model.vo.Notice;

public interface NoticeService {

	ArrayList<Notice> selectList();

	Notice selectOne(int nId);

	int insertNotice(Notice n);

	int updateNotice(Notice n);

	int deleteNotice(int nId);

	
}
