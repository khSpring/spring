package com.kh.springAjax.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {

	@Autowired
	Sample samp;
	//5번 할때 쓰자.
	
	

	/*
	 *  1. ServletOutputStream을 이용한 방식으로 출력용 메소드 사용
	 *  
	 */
	@RequestMapping(value="test1.do", method=RequestMethod.POST)
	public void test1Method(HttpServletResponse response, String name, int age) throws IOException
	{
		PrintWriter out = response.getWriter();
		
		if(name.equals("신사임당") && age==47)
		{
			out.print("ok");
			out.flush();
		}
		else
		{
			out.append("fail");
			out.flush();
		}
		out.close();
		
	}
	
	/*
	 *  2. @ResponseBody를 이용한 방식
	 */
	@RequestMapping(value="test2.do", method=RequestMethod.GET)
	@ResponseBody
	/*
	 *  json(JavaScript Object Notion)
	 *  자바 객체를 xml/json 타입으로 변환해서 전송하는 어노테이션으로
	 *  리턴 값을 응답 객체(response 객체)에 출력한다.
	 */
	public String tset2Method() throws UnsupportedEncodingException {
		
		JSONObject job = new JSONObject();
		job.put("no", 123);
		job.put("title", "test return json object");
		job.put("writer", URLEncoder.encode("홍길동","utf-8"));
		job.put("content", URLEncoder.encode("JSON 객체를  뷰로 리턴하는 테스트입니다.","utf-8"));
		
		return job.toJSONString();
	}
	
	
	/*
	 *  3. 2번과 비교해서 보자
	 *  @ResponseBody를 사용하지 않고 스트림을 써서 json객체 보내기
	 */
	@RequestMapping("test3.do")
	public void test3Method(HttpServletResponse response) throws IOException
	{
		System.out.println("test3.do");
		response.setContentType("application/json;charset=utf-8");
		
		JSONObject job = new JSONObject();
		job.put("no", 123);
		job.put("title", "test return json object");
		job.put("writer", "홍길동");
		job.put("content", "JSON 객체를  뷰로 리턴하는 테스트입니다.");
		
		PrintWriter out = response.getWriter();
		
		out.print(job);
		out.flush();
		out.close();
	}
	
	@RequestMapping("test4.do")
	public void test4Method(HttpServletResponse response) throws IOException
	{
		response.setContentType("application/json;charset=utf-8");
		
		ArrayList<User> list = new ArrayList();
		
		list.add(new User("u1", "p1", "홍길동", 25, "h1@kh.org", "01011112222"));
		list.add(new User("u2", "p2", "김길동", 27, "h2@kh.org", "01011112222"));
		list.add(new User("u3", "p3", "나길동", 26, "h3@kh.org", "01011112222"));
		list.add(new User("u4", "p4", "동길동", 22, "h4@kh.org", "01011112222"));
		list.add(new User("u5", "p5", "박길동", 23, "h5@kh.org", "01011112222"));
		
		// 1, ArrayList를 JSONArray에 담아주자.
		JSONArray jarr = new JSONArray();
		for(User user : list)
		{
			// 1. User 객체 하나를 JSONArray에 담아주자
			JSONObject jUser = new JSONObject();
			
			jUser.put("userId", user.getUserId());
			jUser.put("userPwd", user.getUserPwd());
			jUser.put("userName", user.getUserName());
			jUser.put("age", user.getAge());
			jUser.put("email", user.getEmail());
			jUser.put("phone", user.getPhone());
			
			// 2. JSONObject를 JSONArray에 ㅊ추가하자
			jarr.add(jUser);
		}
		
		//3, 전송을 위해 JSON객체로 담겨있는 회원 정보를 JSON배열을 JSON객체에 담아 주자
		JSONObject sendJson = new JSONObject();
		
		sendJson.put("list", jarr);
		
		PrintWriter  out = response.getWriter();
		
		out.print(sendJson);
		out.flush();
		out.close();
	}
	
	
	/*
	 *  5. JsonView를 이용한 방식
	 *  컨트롤러에서 ModelAndView를 리턴하고, 객체를 json형식으로 보내고 싶을 때 사용
	 */
	@RequestMapping("test5.do")
	public ModelAndView test5Method(ModelAndView mv, HttpServletResponse response)
	{
		// 1. Sample 클래스를 빈으로 등록하자(Servlet-context.xml에 등록)
		// 2. JsonView 라이브러리 받자(pom.xml에 등록)
		// 3. JsonView 설정하기(Servlet-context.xml에 등록)
		
		Map<String, Sample> map = new HashMap();
		map.put("samp", samp);
		
		mv.addAllObjects(map);
		
		mv.setViewName("jsonView");
		
		response.setContentType("application/json;charset=utf-8");
		
		return mv;
	}
	
	
	/*
	 *  6. JSON String형으로 넘어온 것을 변환하는 @RequestBody를 이용하는 방법
	 *  	뷰에서 넘어온 json등의 데이터를 컨트롤러에서 자바객체로 변환해주는 어노테이션이다.
	 */
	@RequestMapping(value="test6.do", method=RequestMethod.POST)
	@ResponseBody	//스트림을 안쓰고 보낼때
	public String test6Method(@RequestBody String param) throws ParseException {
		
		//뷰에서는 stringify 메소드로 Json String형으로 넘어온 상태이다.
		//@RequestBody를 통해 자바 객체인 string으로 바꿈.
		
		JSONParser parser = new JSONParser();
		JSONObject jobj = (JSONObject)parser.parse(param);
		
		String name = (String)jobj.get("name");
//		int age = (Integer)jobj.get("age");		// 이건 바로 Integer도 할 수 없다 -> Long으로 다운캐스팅을 해야 함
		
		int age = ((Long)jobj.get("age")).intValue();
		
		System.out.println(name + ", " + age);
		
		
		return "success!!!!";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

