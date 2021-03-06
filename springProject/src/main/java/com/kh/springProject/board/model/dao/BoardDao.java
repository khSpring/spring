package com.kh.springProject.board.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.springProject.board.model.vo.Board;
import com.kh.springProject.board.model.vo.PageInfo;
import com.kh.springProject.board.model.vo.Reply;

@Repository("bDao")
public class BoardDao {
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public int getListCount() {
		
		return sqlSessionTemplate.selectOne("boardMapper.getListCount");
	}

	public ArrayList<Board> selectList(PageInfo pi) {
		// TODO Auto-generated method stub
		
		int offset = (pi.getCurrentPage()-1)*pi.getBoardLimit();
		
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		
		return (ArrayList)sqlSessionTemplate.selectList("boardMapper.selectList", null, rowBounds);
	}

	public int insertBoard(Board b) {
		
		return sqlSessionTemplate.insert("boardMapper.insertBoard",b);
	}

	public int addReadCount(int bId) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.update("boardMapper.updateCount", bId);
	}

	public Board selectBoard(int bId) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("boardMapper.selectBoard", bId);
	}

	public int updateBoard(Board b) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.update("boardMapper.updateBoard", b);
	}

	public int deleteBoard(int bId) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.update("boardMapper.deleteBoard", bId);
	}

	public ArrayList<Board> selectTopList() {
		// TODO Auto-generated method stub
		return (ArrayList)sqlSessionTemplate.selectList("boardMapper.selectTopList");
	}

	public ArrayList<Reply> selectReplyList(int bId) {
		
		return (ArrayList)sqlSessionTemplate.selectList("boardMapper.selectReplyList", bId);
	}

	public int insertReply(Reply r) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.insert("boardMapper.insertReply", r);
	}

}
