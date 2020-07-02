package com.kh.springProject.board.model.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.kh.springProject.board.model.vo.Board;
import com.kh.springProject.board.model.vo.PageInfo;
import com.kh.springProject.board.model.vo.Reply;

@Service
public interface BoardService {

	int getListCount();

	ArrayList<Board> selectList(PageInfo pi);

	int insertBoard(Board b);

	int addReadCount(int bId);

	Board selectBoard(int bId);

	int updateBoard(Board b);

	int deleteBoard(int bId);

	ArrayList<Board> selectTopList();

	ArrayList<Reply> selectReplyList(int bId);

	int insertReply(Reply r);

}
