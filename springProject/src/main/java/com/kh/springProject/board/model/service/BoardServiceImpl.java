package com.kh.springProject.board.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.springProject.board.model.dao.BoardDao;
import com.kh.springProject.board.model.vo.Board;
import com.kh.springProject.board.model.vo.PageInfo;

@Service("bService")
public class BoardServiceImpl implements BoardService{

	@Autowired
	BoardDao bDao;
	
	@Override
	public int getListCount() {
		
		return bDao.getListCount();
	}

	@Override
	public ArrayList<Board> selectList(PageInfo pi) {
		// TODO Auto-generated method stub
		return bDao.selectList(pi);
	}

}
