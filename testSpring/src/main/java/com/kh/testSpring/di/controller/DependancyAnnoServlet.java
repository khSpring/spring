package com.kh.testSpring.di.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.kh.testSpring.anno.model.vo.Person;

/**
 * Servlet implementation class DependancyAnnoServlet
 */
public class DependancyAnnoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DependancyAnnoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ApplicationContext cntx = new GenericXmlApplicationContext("/anno-di-context.xml");
		
		Person p = (Person)cntx.getBean("person");
		// 기본적으로 @Component로 등록한 Bean들은 클래스명의 첫글자가 소문자로 바뀌면 id로 등록이 된다.
		p.printPerson();
		p.getMyJob().jobInfo("BClass");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
