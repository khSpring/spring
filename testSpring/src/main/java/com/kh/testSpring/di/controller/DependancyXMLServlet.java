package com.kh.testSpring.di.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.kh.testSpring.xml.model.vo.Person;

/**
 * Servlet implementation class DependancyXMLServlet
 */
public class DependancyXMLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DependancyXMLServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 객체 의존성이란, 객체를 생성할 때 new를 통한
		 * 새로운 객체를 생성하는 것 뿐만 아니라,
		 * 다른 곳으로부터 객체의 정보를 전달 받는 것(초기화)를
		 * 의미한다.
		 * 
		 * 1. 생성자를 통한 의존성 주입
		 * 		Person p = new Person("홍길동","개발자");
		 * 2. Setter를 통한 의존성 주입
		 * 		p.setName("고길동");
		 * 		p.setJob("개발자");
		 */
		
		// xml부터 만들자 -> xml-di-context.xml 생성
		ApplicationContext cntx = new GenericXmlApplicationContext("/xml-di-context.xml");
		
		Person p1 = (Person)cntx.getBean("person1");
		p1.printPerson();
		p1.getMyJob().jobInfo("서울대 병원");
		
		Person p2 = (Person)cntx.getBean("person2");
		p2.printPerson();
		p2.getMyJob().jobInfo("자택");
		
		// ApplicationContext가 bean에 등록 된 bean객체들을
		// 싱글톤으로 관리하는지 확인해 보자.
		Person p3 = (Person)cntx.getBean("person1");
		Person p4 = (Person)cntx.getBean("person2");
		
		System.out.println("p1 == p3 ? " + (p1 == p3));
		System.out.println("p2 == p4 ? " + (p2 == p4));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
