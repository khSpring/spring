package com.kh.testSpring.di.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.kh.testSpring.di.model.vo.TV;
import com.kh.testSpring.di.model.vo.TvMgr;

/**
 * Servlet implementation class DependancyServlet
 */
public class DependancyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DependancyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("나 실행되니?");
		
		// 1. 일반 TV를 서블릿에서 호출하여 사용할 경우
//		LgTV tv = new LgTV();
//		tv.turnOn();
//		tv.turnOff();
//		tv.volumnUp();
//		tv.volumnDown();
//		
//		SamsungTV tv1 = new SamsungTV();
//		tv1.turnOn();
//		tv1.turnOff();
//		tv1.volumnUp();
//		tv1.volumnDown();
		
		// LgTV에서 SamsungTV로 바꿔 메소드를 실행해야 할때 번거로움이 있다.
		// 각 객체와 관련된 메소드를 실행해야 하기 때문이고 이러한 영향력을 객체 결합도(coupling)가 높다.
		// 결합도가 높을수록 유지보수에 어려움이 따른다.
		
		// 결합도를 낮추기 위해 인터페이스로 구성된 코드를 실행해 보자.(다형성)
//		TV tv = new SamsungTV();
//		tv.turnOn();
//		tv.turnOff();
//		tv.volumnUp();
//		tv.volumnDown();
		
		// 위의 방식으로 만들게 되면 편하긴 하지만 필요로 하는 객체를
		// 계속 new로 생성해 주어야 한다.
		
		// 따라서, 전달받은 값에 따라 해당하는 객체를 생성해 주는 코드를 작성해보자.
		// (BeanFactory 활용)
		
//		TV tv = (TV)BeanFactory.getBean("samsung");
//		tv.turnOn();
//		tv.turnOff();
//		tv.volumnUp();
//		tv.volumnDown();
		
		// 실제 스프링에서 제공하는 스프링 컨테이너를 활용한 객체 출력하기
		AbstractApplicationContext cnxt = new GenericXmlApplicationContext("/sample-context.xml");
		
		TV tv = (cnxt.getBean("tvmgr",TvMgr.class)).getTv();
		tv.turnOn();
		tv.turnOff();
		tv.volumnUp();
		tv.volumnDown();
		
		/*
		 * 이렇게 xml 문서를 읽게 함으로써
		 * 객체에 대한 결합도/의존성을 xml 파일에게 양도하게 됨
		 * 그래서 개발자가 더 이상 직접 new를 통한 객체 생성을 할 필요가 없어졌다.
		 * 
		 * 스프링 컨테이너(GenericXmlApplicationContext)는 자체적으로 객체의 생명주기를
		 * 관리하며 더 이상 개발자 중심의 코드가 아닌 스프링 프레임워크가 중심인
		 * 제어의 역행(IOC)기법을 구현하게 되었다.
		 */
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
