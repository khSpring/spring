package com.kh.springProject.notice.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.springProject.notice.model.dao.NoticeDao;
import com.kh.springProject.notice.model.vo.Notice;

@Service("nService")
public class NoticeServiceImpl implements NoticeService{

	@Autowired
	NoticeDao nDao;
	
	@Override
	public ArrayList<Notice> selectList() {
		
		return nDao.selectList();
	}

	@Override
	public Notice selectOne(int nId) {
		// TODO Auto-generated method stub
		return nDao.selectOne(nId);
	}

	@Override
	public int insertNotice(Notice n) {

		return nDao.insertNotice(n);
	}

	@Override
	public int updateNotice(Notice n) {
		
		return nDao.updateNotice(n);
	}

	@Override
	public int deleteNotice(int nId) {

		return nDao.deleteNotice(nId);
	}

	
}
