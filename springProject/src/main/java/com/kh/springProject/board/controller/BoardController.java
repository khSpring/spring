package com.kh.springProject.board.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.kh.springProject.board.model.exception.BoardException;
import com.kh.springProject.board.model.service.BoardService;
import com.kh.springProject.board.model.vo.Board;
import com.kh.springProject.board.model.vo.PageInfo;
import com.kh.springProject.board.model.vo.Reply;
import com.kh.springProject.common.Pagination;
import com.kh.springProject.member.model.vo.Member;

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
	
	@RequestMapping("binsertView.do")
	public String boardInsertView()
	{
		return "board/boardInsertForm";	// boardInsertForm.jsp만들기
	}
	
	@RequestMapping("binsert.do")
	public String boardInsert(HttpServletRequest request, Board b,
			@RequestParam(value="uploadFile", required=false) MultipartFile file) throws BoardException {
		// NoticeController 가서 saveFile 메소드 복사해서 아래에 추가하자
		
		if(!file.getOriginalFilename().equals("")) {	// 파일이 잘 넘어온 경우
			String renameFileName = saveFile(file,request);	
			// NoticeController때와 다르게 이름을 좀 바꿀 것이다.
			
			System.out.println("오리진 파일 : " + file.getOriginalFilename());
			b.setOriginalFileName(file.getOriginalFilename());
			b.setRenameFileName(renameFileName);
		}
		
		int result = bService.insertBoard(b);
		
		if(result>0) {
			return "redirect:blist.do";
		}else {
			throw new BoardException("게시글 등록 실패!");
		}
	}
	
	// 게시글 작성이 끝났으니 게시글 상세보기를 작성하러
	
	public String saveFile(MultipartFile file, HttpServletRequest request) {
		// 파일이 저장될 경로를 설정하는 메소드
		
		String root = request.getSession().getServletContext().getRealPath("resources");

		String savePath = root + "\\buploadFiles";
		
		File folder = new File(savePath);
		// java.io.File로 import 하자
		
		if(!folder.exists())
		{
			folder.mkdirs();
		}
		
		// 공지글은 굳이 파일명 중복 제거는 신경쓰지 않고 게시판에서 파일명 rename하는걸 해보자!
//		String filePath = folder + "\\" + file.getOriginalFilename();
		// 실제 저장 될 파일 경로 + 파일명
		
		// 공지사항 때와 다르게 파일을 저장할 때 작성자가 올린 파일명 그대로가 아닌
		// 업로드 시간을 기준으로 파일명을 변경하자.
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String originFileName = file.getOriginalFilename();
		String renameFileName = 
				sdf.format(new java.sql.Date(System.currentTimeMillis()))
						+"."+originFileName.substring(originFileName.lastIndexOf(".")+1);
		
		String filePath = folder + "\\" + renameFileName;
		
			try {
				file.transferTo(new File(filePath));
				// 이 상태로는 파일 업로드가 되지 않는다. 왜냐면 파일 제한크기에 대한 설정이 없기 때문에
				// 그래서 파일 크기 지정을 root-context.xml에서 해주자
			} catch(Exception e) {
				System.out.println("파일 전송 에러 : " + e.getMessage());
			}	

		return renameFileName;
	}
	
	@RequestMapping("bdetail.do")
	public ModelAndView boardDetail(ModelAndView mv, int bId, @RequestParam("page") Integer page) throws BoardException
	{
		int currentPage = 1;
		if(page != null)
		{
			currentPage = page;
		}
		
		int result = bService.addReadCount(bId);
		
		if(result > 0)
		{
			Board board = bService.selectBoard(bId);
			System.out.println(board);
			if(board != null)
			{
				System.out.println("boardDetailView로 이동");
				mv.addObject("board", board)
				  .addObject("currentPage", currentPage)
				  .setViewName("board/boardDetailView");	//boardDetailView.jsp만들러 가자
			}
			else
			{
				throw new BoardException("게시글 조회 실패!");
			}
		}
		else
		{
			throw new BoardException("게시글 조회수 증가 실패!");
		}
		
		return mv;
	}
	
	@RequestMapping("bupView.do")
	public ModelAndView boardUpdateView(ModelAndView mv, int bId, @RequestParam("page") Integer page)
	{
		mv.addObject("board", bService.selectBoard(bId)).addObject("currentPage",page).setViewName("board/boardUpdateForm");
		
		return mv;
	}
	
	@RequestMapping("bupdate.do")
	public ModelAndView boardUpdate(ModelAndView mv, Board b, HttpServletRequest request, @RequestParam("page") Integer page,
									@RequestParam(value="reUploadFile", required=false) MultipartFile file) throws BoardException
	{
		//NoticeController때와 마찬가지로 deleteFile 메소드 내용을 써서 기존 파일을 삭제해야 한다.
		//deleteFile을 복사해 쓰자
		if(file != null)	//게시글 파일을 수정하려 한다면
		{
			if(b.getRenameFileName() != null)
			{
				deleteFile(b.getRenameFileName(), request);
			}
		}
		
		String renameFileName = saveFile(file, request);
		// 새로 수정 할 파일 저장해주고 rename된 파일명 반환 해줌
		
		if(renameFileName != null)
		{
			b.setOriginalFileName(file.getOriginalFilename());
			b.setRenameFileName(renameFileName);
		}
		
		int result = bService.updateBoard(b);
		
		if(result > 0)
		{
			mv.addObject("page", page).setViewName("redirect:blist.do");
		}
		else
		{
			throw new BoardException("게시글 수정 실패!");
		}
		return mv;
	}
	

	public void deleteFile(String fileName, HttpServletRequest request)
	{
		String root = request.getSession().getServletContext().getRealPath("resources");
		String savePath = root + "\\buploadFiles";
		
		File f = new File(savePath + "\\" + fileName);
		
		if(f.exists())
		{
			f.delete();
		}
	}
	
	@RequestMapping("bdelete.do")
	public String boardDelete(int bId, HttpServletRequest request) throws BoardException
	{
		Board b = bService.selectBoard(bId);	// 첨부된 파일을 지우기 위해 게시글 정보 가져오기
		
		if(b.getOriginalFileName() != null)	// 파일점부가 되어있는 게시글이라면
		{
			deleteFile(b.getRenameFileName(), request);
		}
		
		int result = bService.deleteBoard(bId);
		if(result > 0)
		{
			return "redirect:blist.do";
			
		}
		else
		{
			throw new BoardException("게시글 삭제 실패!");
		}
	}
	
	//	-------------------------- ajax 이후에 작성한 부분 ----------------------------
	
	/*
	 *  1. stream을 이용한 json배열 보내기
	 */
//	@RequestMapping("topList.do")
//	public void boardTopList(HttpServletResponse response) throws IOException
//	{
//		response.setContentType("application/json;charset=utf-8");
//		
//		ArrayList<Board> list = bService.selectTopList();
//		// DB로부터 조회수가 높은 순 5개를 ArrayList에 담아 오기
//		
//		JSONArray jArr = new JSONArray();
//		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		
//		for(Board b : list)
//		{
//			JSONObject jobj = new JSONObject();
//			jobj.put("bId", b.getbId());
//			jobj.put("bTitle", b.getbTitle());
//			jobj.put("bWriter", b.getbWriter());
//			jobj.put("bCreateDate", sdf.format(b.getbCreateDate()));
//			jobj.put("bCount", b.getbCount());
//			jobj.put("originalFileName", b.getOriginalFileName());
//			
//			jArr.add(jobj);
//		}
//		
//		JSONObject sendJson = new JSONObject();
//		sendJson.put("list", jArr);
//		
//		PrintWriter out = response.getWriter();
//		out.print(sendJson);
//		out.flush();
//		out.close();
//	}
	
	/*
	 *  2. Gson을 이용하는 방법	(pom.xml에 등록해서 라이브러리 다운 받자)
	 *  	
	 *  	컬렉션을 아주 쉽게 json 객체로 전송하는 방법
	 *  	Gson은 Jackson보다도 더 간단하게 처리해서 보낼 수 있다.
	 *  	*Date형에 대해서는 GsonBuilder를 이용한 처리를 할 수 있다.
	 *  
	 */
	
	@RequestMapping("topList.do")
	public void boardTopList(HttpServletResponse response) throws JsonIOException, IOException
	{
		ArrayList<Board> list = bService.selectTopList();
		response.setContentType("application/json;charset=utf-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		gson.toJson(list, response.getWriter());
		
	}
	
	// 댓글 관련 부분
	// 1. 댓글 리스트 불러오기
	@RequestMapping("rlist.do")
	public void getReplyList(HttpServletResponse response, int bId) throws JsonIOException, IOException
	{
		ArrayList<Reply> rList = bService.selectReplyList(bId);
//		System.out.println(rList);
		response.setContentType("application/json;charset=utf-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		gson.toJson(rList, response.getWriter());
	}
	
	// 2. 댓글 달기
	@RequestMapping("addReply.do")
	@ResponseBody	// 스트림 안쓰고 값 보내기
	public String addReply(Reply r, HttpSession session) throws BoardException
	{
		Member loginUser = (Member)session.getAttribute("loginUser");
		String rWriter = loginUser.getId();
		r.setrWriter(rWriter);
		
		int result = bService.insertReply(r);
		
		if(result > 0)
		{
			return "success";
		}
		else
		{
			throw new BoardException("댓글 등록 실패");
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
