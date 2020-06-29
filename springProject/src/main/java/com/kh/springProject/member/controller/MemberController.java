package com.kh.springProject.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.kh.springProject.member.model.exception.MemberException;
import com.kh.springProject.member.model.service.MemberService;
import com.kh.springProject.member.model.vo.Member;

@SessionAttributes("loginUser")	// 7번 할 때 달아 주자!
// Model에 loginUser라는 키 값으로 객체가 추가되면 자동으로 그 객체를 세션에 추가하라는 어노테이션
// (클래스 위에 추가해야 함)
@Controller
// Component와 다르지 않으나 Controller 타입의 어노테이션을 붙여주면 스프링이 Controller로 인식
public class MemberController {
//	private MemberService mService = new MemberService();
	// 예전에는 위와 같이 했었지만..
	
	@Autowired
	private MemberService mService;
	// 스프링부터는 변수 선언까지만 하고 어노테이션을 붙인다.
	
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	// 회원 가입할 때 암호화 처리 후에 작성하자.(스프링 시큐리티 라이브러리도 깔고)
	
	
	/*
	 * @RequestMapping(value="login.do", method = RequestMethod.POST)
	 * 
	 * @RequestMapping 타입의 어노테이션을 붙여줌으로써 handlerMapping 등록
	 * 
	 * *@RequestMapping의 속성
	 * 여러 개의 속성을 명시할 때는 "value="를 명시해야 되고
	 * value만 명시해야 되는 경우 바로 @RequestMapping("login.do")처럼 생략 가능(비추!)
	 * 
	 * 그러나 post방식으로 넘어온 걸 받으려면,
	 * (value="",method="")과 같이 매핑 조건을 부여하고 전달하는 method방식을 지정해 줘야 된다.
	 */
	
	// 파라미터를 전송 받는 방법
	/*
	 * 1. HttpServletRequest를 통해 받기(기존 jsp/servlet때의 방식)
	 * 		메소드의 매개변수로 HttpServletRequest를 작성하면 메소드 실행 시
	 * 		스프링 컨테이너가 자동으로객체를 인자로 주입해 준다.
	 */
//	@RequestMapping(value="login.do", method=RequestMethod.POST)
//	public String memberLogin(HttpServletRequest request) {
//		String id = request.getParameter("id");
//		String pwd = request.getParameter("pwd");
//		
//		System.out.println("ID : " + id);
//		System.out.println("PWD : " + pwd);
//		
//		return "home";
//	}
	
	/*
	 *  2. @RequestParam 어노테이션 방식
	 *  	스프링에서는 조금 더 간단하게 파라미터를 받아올 수 잇는 방법을 제공해 준다.
	 *  	HttpServlet과 비슷하게 request 객체를 이용하여 데이터를 전손하는 방법
	 *  
	 *  	파라미터 value 속성에 없는 값이 넘어오는 경우 ""이기 때문에 에러는 안남
	 *   	이 때 기본적으로 설정한 defaultValue는 적용되지 않음 -> ""값이 넘어오기 때문
	 *   	하지만 required를 false로 설정한 경우는 값이 넘어오게 되면 defaultValue가 적용됨
	 *   	(기본적으로는 required가 true로 되어 있음(따로 안적으면))
	 */
//	@RequestMapping(value="login.do", method = RequestMethod.POST)
//	public String memberLogin(@RequestParam(value="id", required=false) String id,
//							  @RequestParam("pwd") String pwd) {
//		System.out.println("ID : " + id);
//		System.out.println("PWD : " + pwd);
//		
//		return "home";
//	}
	
	/*
	 * 3. @RequestParam 어노테이션 생략
	 * 		위의 어노테이션을 생략해도 파라미터 값을 가져와서 바로 매개변수엥 저장할 수 있다.
	 * 		(단, 매개변수를 view에서 넘겨준 name과 동일(★★★)하게 해야 자동으로 값이 주입된다.)
	 * 
	 * 만약, 없는 값일 경우에는 null 값이 입력됨(그냥 빈 값)
	 */
//	@RequestMapping(value="login.do", method = RequestMethod.POST)
//	public String memberLogin(String id, String pwd) {
//		System.out.println("ID : " + id);
//		System.out.println("PWD : " + pwd);
//		
//		return "home";
//	}
	
	/*
	 * 4. @ModelAttribute를 이용한 값 전달 방법
	 * 		요청 파라미터가 많은 경우 객체 타입으로 넘겨 받게 된다.
	 * 		--> 기본생성자와 setter를 이용한 주입 방식이기 때문에 둘 중 하나라도 없으면 에러가 발생
	 * 			(기본생성자를 주석해서 확인해 보자)
	 * 		이를 커맨드 방식이라고도 하는데
	 * 		스프링 컨테이너가 기본 생성자를 통해 Member객체를 생성하고
	 * 		setter 메소드로 파라미터의 값을 대입해 변경 후에
	 * 		현재 이 메소드를 호출할 때 인자로 전달하여 호출하는 방식으로 주입을 해준다.
	 * 		(주의! : 반드시 name속성의 값과 필드명이 동일, setter작성하는 규칙에 맞게 setter 이름 지정)
	 */
	
//	@RequestMapping(value="login.do", method=RequestMethod.POST)
//	public String memberLogin(@ModelAttribute Member m) {
//		System.out.println("ID : " + m.getId());
//		System.out.println("PWD : " + m.getPwd());
//		
//		return "home";
//	}
	
	/*
	 * 5. 위의 @ModelAttribute 어노테이션 생략하고 객체로 바로 전달 받는 방법
	 * 		Model
	 * 		: 전달 받는 것에 대한게 아니라 요청 처리 후 전달 하고자 하는 데이터가 있을 경우에 대한 방법
	 * 		Model을 사용하게 되면 뷰로 전달하고자 하는 데이터를 맵 형식(key, value)로 담을 수 있다.
	 * 		scope는 request이다. 서블릿에서 사용하던 requestScope라고 생각하면 됨
	 */
//	@RequestMapping(value="login.do", method=RequestMethod.POST)
//	public String memberLogin(Member m, HttpSession session, Model model) {
//		System.out.println("ID : " + m.getId());
//		System.out.println("PWD : " + m.getPwd());
//		
//		Member loginUser = mService.loginMember(m);
//		System.out.println(m.getId() + " : " + m.getPwd());
//		
//		if(loginUser != null) {
//			session.setAttribute("loginUser", loginUser);
//			return "home";
//		}else {
//			model.addAttribute("msg","로그인 실패!!");
//			
//			return "common/errorPage";
//		}
//	}
	
	/*
	 * 6. ModelAndView 객체를 사용하는 방법
	 * 		위에서 Model은 전달하고자 하는 데이터를 맵 형식으로 담는 공간이라고 한다면
	 * 		String 반환 값은 requestDispatcher처럼 forward할 뷰 페이지 정보를 담은 객체라고 할 수 있었다.
	 * 		ModelAndView는 이러한 Model과 뷰의 정보를 같이 담을 수 있는 객체이다.
	 */
	
//	@RequestMapping(value="login.do", method=RequestMethod.POST)
//	public ModelAndView memberLogin(Member m, ModelAndView mv, HttpSession session) {
//		Member loginUser = mService.loginMember(m);
//		
//		if(loginUser != null) {
//			session.setAttribute("loginUser", loginUser);
//			mv.setViewName("home");
//		}else {
//			mv.addObject("msg","로그인 실패!!");
//			mv.setViewName("common/errorPage");
//		}
//		return mv;
//	}
	
	// 로그아웃 서블릿용 메소드
//	@RequestMapping(value="logout.do", method=RequestMethod.GET)
//		public String logout(HttpSession session) {
//			session.invalidate();
//			
//			return "home";
//		}
	


 	/*
 	 * 7_1. session에 저장할 때 @SessionAttributes 사용하기
 	 * 		(session 매개변수 안쓰고 해보기)
 	 * 7_2. 에러 페이지를 직접 선택하지 않고 공용 에러페이지를 등록 후에 에러만 발생시키기
 	 */
	
//	@RequestMapping(value="login.do", method=RequestMethod.POST)
//	public String memberLogin(Member m, Model model) {
//		Member loginUser = mService.loginMember(m);
//		
//		if(loginUser != null) {
//			model.addAttribute("loginUser",loginUser);
//			
//			return "home";
//		}else {
//			throw new MemberException("로그인 실패!!");
//			// 예외를 발생시켜서 에러페이지로 넘어갈 껀데
//			// 우선 예외 클래스는 RuntimeException을 상속받자
//			// (따로 예외처리를 귀찮게 해줄 필요가 없다.)
//			// 그리고 나면 예외가 발생햇을 때 common에 있는 errorPage에서
//			// 처리를 할 수 있도록 web.xml에 공용 에러 페이지를 등록하자.
//			// web.xml로 ㄱㄱ
//		}
//	}
	
	// 7_3. 그런데.. 이렇게 @SessionAttribute("loginUser")를 클래스 위에
	// 		추가했더니 기존의 로그아웃(session.invalidate())가 작동되지 않는다
	//		로그아웃을 새로 만들어보자
	// 로그아웃 서블릿용 메소드
		@RequestMapping(value="logout.do", method=RequestMethod.GET)
			public String logout(SessionStatus status) {
//				session.invalidate();
				status.setComplete();
				
				/*
				 * SessionStatus에서 제공하는 setComplete를 쓰는 이유는
				 * @SessionAttribute("loginUser")를 썼을 때 setComplete메소드를
				 * 실행하기 전까지 동일한 세션에서는 계속 사용할 수 있는 상태가 된다.
				 * (즉, invalidate()가 작동하지 않음)
				 */
			
				return "home";
			}
			// 로그아웃까지 다 되는거 확인이 되면 이제 회원가입을 위해 menubar.jsp로 가서
			// 로그인 후 보여질 태그 수정하고
			// 회원가입 버튼 a태그의 href 경로를 바꾸고 시작하자.
		
		// ----------------- 로그인/로그아웃 처리가 끝나면 이제 회원가입을 해보자 ----------------------
		// 회원가입 뷰 페이지로 이동시키는 컨트롤러 메소드 추가
		@RequestMapping("enrollview.do")
		public String enrollview() {
			return "member/memberJoin";
		}
		
		@RequestMapping("minsert.do")
		public String memberInsert(Member m, Model model, @RequestParam("post") String post
									, @RequestParam("address1") String address1
									, @RequestParam("address2") String address2)
		{
			System.out.println(m);
			// 1. 화면단에서 넘어온 데이터 중 한글이 있으면 깨져서 보이는 것을 확인할 수 있다.
			// 		즉, utf-8로 인코딩 하는 필터가 필요한데 web.xml에 필터를 등록하고 오자.
			
			// 2_1. bCrypt로 암호화 처리 하기
			//		양방향 해쉬 함수로 암호화된 메서지를 수학적인 연산을 통해 암호화된 메세지인 다이제스트를 생성한다.
			//		원본 메세지로 암호화된 메서지를 구하는 것은 쉽지만,
			//		암호화된 메세지로 원본 메세지를 구할 수 없어야 한다는 것이 단방향 암호화 처리.
			
			//		원본 메세지를 구할 수 있는 것은 양방향이라하고
			//		
			
			//		bCrypt : 해쉬 함수를 통한 암호화된 값 + 솔팅 기법(salting) + 다이제스트를 생성하는 시간 설정
			
			//		암호화된 값을 변수에다 기록하자!
			String encPwd = bcryptPasswordEncoder.encode(m.getPwd());
			// 2_2. 위의 메소드(encode)를 제공하는 클래스를 쓰기 위해 pom.xml에 가서 3가지 라이브러리들을
			//		dependancy로 추가하자.
			// 2_3. spring-security.xml을 만들고 bean 설정을 만들자.
			
			System.out.println(encPwd);
			
			// setter를 통해 Member 객체의 pwd 값 변경
			m.setPwd(encPwd);

			// 주소 값들을 ','를 구분자로 하여 합쳐서 Member객체 m에 넣자
			m.setAddress(post + "," + address1 + "," + address2);
			
			// 이제 서비스로 넘기자
			int result = mService.insertMember(m);
			
			if(result > 0)
			{
				return "home";
			}
			else
			{
				throw new MemberException("회원 가입 실패");
			}

		}
		
		// 암호화 처리 후 로그인 부분
		@RequestMapping(value="login.do", method=RequestMethod.POST)
		public String memberLogin(Member m, Model model) {
			
			/*
			 *  matches() 메소드를 통해 암호화 되어 있는 DB값과
			 *  사용자가 입력한 비빌번호를 비교할 수 있다.
			 */
			
			Member loginUser = mService.loginMember(m);
			
			if(bcryptPasswordEncoder.matches(m.getPwd(), loginUser.getPwd())) {
				model.addAttribute("loginUser",loginUser);
				
				return "home";
			}else {
				throw new MemberException("로그인 실패!!");
				// 예외를 발생시켜서 에러페이지로 넘어갈 껀데
				// 우선 예외 클래스는 RuntimeException을 상속받자
				// (따로 예외처리를 귀찮게 해줄 필요가 없다.)
				// 그리고 나면 예외가 발생햇을 때 common에 있는 errorPage에서
				// 처리를 할 수 있도록 web.xml에 공용 에러 페이지를 등록하자.
				// web.xml로 ㄱㄱ
			}
		}
		
		// 위에까지 해서 로그인/회원가입/로그아웃은 완벽히 끝남
		@RequestMapping("myinfo.do")
		public String myInfoView() {
			return "member/mypage";		// mypage.jsp 만들자
		}
		
		@RequestMapping("mupdate.do")
		public String memberUpdate(Member m, Model model,
									@RequestParam("post") String post,
									@RequestParam("address1") String address1,
									@RequestParam("address2") String address2)
		{
			m.setAddress(post + "," + address1 + "," + address2);
			
			int result = mService.updateMember(m);
			
			if(result > 0)
			{
				model.addAttribute("loginUser", m);
			}
			else
			{
				throw new MemberException("회원 정보수정 실패!!");
			}
			return "home";
		}
		
		//회원 탈퇴
		@RequestMapping("mdelete.do")
		public String memberDelete(String id)
		{
			int result = mService.deleteMember(id);
			
			if(result > 0)
			{
				
				return "redirect:logout.do";	//sendRedirect
			}
			else
			{
				throw new MemberException("탈퇴 실패!");
			}
		}
		
}
