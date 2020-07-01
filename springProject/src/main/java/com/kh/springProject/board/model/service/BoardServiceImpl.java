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

	@Override
	public int insertBoard(Board b) {
		
		return bDao.insertBoard(b);
	}

	@Override
	public int addReadCount(int bId) {
	
		return bDao.addReadCount(bId);
	}

	@Override
	public Board selectBoard(int bId) {
		// TODO Auto-generated method stub
		return bDao.selectBoard(bId);
	}

	@Override
	public int updateBoard(Board b) {
		
		return bDao.updateBoard(b);
	}

	@Override
	public int deleteBoard(int bId) {
		// TODO Auto-generated method stub
		return bDao.deleteBoard(bId);
	}

	@Override
	public ArrayList<Board> selectTopList() {
		// TODO Auto-generated method stub
		return bDao.selectTopList();
	}

}
