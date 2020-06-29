package com.kh.springProject.board.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.springProject.board.model.exception.BoardException;
import com.kh.springProject.board.model.service.BoardService;
import com.kh.springProject.board.model.vo.Board;
import com.kh.springProject.board.model.vo.PageInfo;
import com.kh.springProject.common.Pagination;

@Controller
public class BoardController {
	
	@Autowired
	BoardService bService;
	
	@RequestMapping("blist.do")
	public ModelAndView boardList(ModelAndView mv, @RequestParam(value="page", required=false) Integer page) throws BoardException
	{
		// 마이바티스 때 했던 PageInfo나 Pagination에 대한 내용을 그대로 쓰자.
		
		int currentPage = 1;
		if(page != null)
		{
			currentPage = page;
		}
		
		int listCount = bService.getListCount();
//		System.out.println(listCount);
		
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount);
		
		ArrayList<Board> list = bService.selectList(pi);
		
		if(list != null)
		{
			mv.addObject("list", list);
			mv.addObject("pi",pi);
			mv.setViewName("board/boardListView");
		}
		else
		{
			throw new BoardException("게시글 전체 조회 실패");
		}
		return mv;
		
		
	}
}
