package com.kh.springProject.notice.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.springProject.notice.model.exception.NoticeException;
import com.kh.springProject.notice.model.service.NoticeService;
import com.kh.springProject.notice.model.vo.Notice;

@Controller
public class NoticeController {
	@Autowired
	NoticeService nService;
	
	@RequestMapping("nlist.do")
	public ModelAndView noticeList(ModelAndView mv)
	{
		ArrayList<Notice> list = nService.selectList();
		
		if(!list.isEmpty())
		{
			
			mv.addObject("list", list);
			mv.setViewName("notice/noticeListView");
		}
		else
		{
			throw new NoticeException("공지사항 목록 보기 실패!");
		}
		return mv;
	}
	
	@RequestMapping(value="ndetail.do", method=RequestMethod.GET)
	public String noticeDetail(Model model, int nId, Notice n)
	{
		n = nService.selectOne(nId);
//		System.out.println(n);
		if(n != null)
		{
			model.addAttribute("notice", n);
		}
		else
		{
			throw new NoticeException("공지사항 상세보기 실패!");
		}
		return "notice/noticeDetailView";
	}
	
	@RequestMapping(value="nWriterView.do", method=RequestMethod.GET)
	public String nWriterView()
	{
		
		return "notice/noticeWriteForm";	// noticeWriteForm.jsp만들자
	}
	
	@RequestMapping(value="ninsert.do", method=RequestMethod.POST)
	public String noticeInsert(Notice n, HttpServletRequest request,
								@RequestParam(name="uploadFile", required=false)MultipartFile file)
	{
		// pom.xml 가서 일단 multipart 인코딩 타입으로 파일을 넘겨줄 때 필요한 라이브러리부터 다운 받자.
		// 이전엔 cos.jar라고 하는 라이브러리를 썼었는데 이번엔 두가지가 필요하다.
		
		// required=false라는 속성을 주고 uploadFile이 없어도 에러가 나지 않게 처리 함
		// (반드시 필요한 값이 아니라는 얘기)
		
		if(!file.getOriginalFilename().contentEquals("")) {
			String savePath = saveFile(file, request);
			
			if(savePath != null) {	// 파일이 잘 저장된 경우
				n.setFilePath(file.getOriginalFilename());
			}
		}
		
		int result = nService.insertNotice(n);
		
		if(result > 0) {
			return "redirect:nlist.do";
		}else {
			throw new NoticeException("공지사항 등록 실패!");
		}
		
	}
	
	public String saveFile(MultipartFile file, HttpServletRequest request)
	{
		String root = request.getSession().getServletContext().getRealPath("resources");

		String savePath = root + "\\nuploadFiles";
		
		File folder = new File(savePath);
		// java.io.File로 import 하자
		
		if(!folder.exists())
		{
			folder.mkdirs();
		}
		
		// 공지글은 굳이 파일명 중복 제거는 신경쓰지 않고 게시판에서 파일명 rename하는걸 해보자!
		String filePath = folder + "\\" + file.getOriginalFilename();
		// 실제 저장 될 파일 경로 + 파일명
		
			try {
				file.transferTo(new File(filePath));
				// 이 상태로는 파일 업로드가 되지 않는다. 왜냐면 파일 제한크기에 대한 설정이 없기 때문에
				// 그래서 파일 크기 지정을 root-context.xml에서 해주자
			} catch(Exception e) {
				System.out.println("파일 전송 에러 : " + e.getMessage());
			}	

		return filePath;
	}
	
	// 만들어 놨던 noticeDetailView.jsp 파일 첨부 파일쪽 추가해 주자.
	
	
	@RequestMapping("nupView.do")
	public String noticeUpdateView(Model model, int nId)
	{
		model.addAttribute("notice", nService.selectOne(nId));
		return "notice/noticeUpdateView";
	}
	
	@RequestMapping("nupdate.do")
	public String noticeUpdate(HttpServletRequest request, Notice n, @RequestParam(value="reuploadFile", required=false)MultipartFile reuploadFile)
	{
//		System.out.println(n);
		if(reuploadFile != null)	//새로 업로드한 파일이 있다면
		{
			if(n.getFilePath() != null)
			{
				deleteFile(n.getFilePath(), request);
			}
		}
		
		//새로 업로드 된 파일은 새로 저장해 주고
		String savePath = saveFile(reuploadFile, request);
		
		if(savePath != null)	// 잘 저장 되었다면
		{
			n.setFilePath(reuploadFile.getOriginalFilename());
		}
		
		int result = nService.updateNotice(n);
		
		if(result > 0)
		{
			return "redirect:nlist.do";	
		}
		else
		{
			throw new NoticeException("공지사항 수정 실패");
		}
	}
	
	public void deleteFile(String fileName, HttpServletRequest request)
	{
		String root = request.getSession().getServletContext().getRealPath("resources");
		String savePath = root + "\\nuploadFiles";
		
		File f = new File(savePath + "\\" + fileName);
		
		if(f.exists())
		{
			f.delete();
		}
	}
	
	@RequestMapping("ndelete.do")
	public String noticeDelete(int nId, HttpServletRequest request)
	{
		Notice n = nService.selectOne(nId);
		
		// DB에서 공지사항을 지우기 전에 그 녀석이 깔아놓은 파일도 깔끔하게 지우자
		if(n.getFilePath() != null)
		{
			deleteFile(n.getFilePath(), request);
		}
		
		// 이제 DB에서의 공지사항 글을 지우자
		int result = nService.deleteNotice(nId);
		
		if(result > 0)
		{
			return "redirect:nlist.do";
		}
		else
		{
			throw new NoticeException("공지사항 삭제 실패");
		}

	}
	
	// notice가 끝이 나면 이제는 게시판을 만들러 menubar.jsp로 가자
	
	
	
	
	
	
	
	
	
	
}
