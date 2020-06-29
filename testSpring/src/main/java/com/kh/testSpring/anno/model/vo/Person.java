package com.kh.testSpring.anno.model.vo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
// bean 객체를 찍어낼 일반적인 클래스들(pojo:plain old java object)을 하면
// 컨테이너가 알아서 xml 설정을 내부적으로 해준다.
// 어노테이션 방법을 쓸 때 따로 id를 부여하지 않으면 클래스명을 소문자로 시작하게 하는 것으로
// id를 인식한다.
public class Person {
	// 1. Field
	@Value("홍길동")
	// 일반적인 값이 들어가야 될 때 값을 넣어주는 역할을 함
	private String name;
	
	@Autowired
	// 의존성 주입(들어가는 값이 객체일 때)이 일어나야 될 때 자동으로 검색해주는 역할을 함
	// 의존성 주입이 되는 개체는 기본적으로 @Component가 되어 있어서 bean 객체로 존재 해야 함
	@Qualifier("policeOfficer")
	private Job myJob;
	
	// 2. Constructor
	public Person() {}

	public Person(String name, Job myJob) {
		this.name = name;
		this.myJob = myJob;
	}
	
	// 3. 사용 할 메소드
	public void printPerson() {
		System.out.print(name + " : ");
		myJob.printJob();
	}
	
	// 4.getter와 setter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Job getMyJob() {
		return myJob;
	}

	public void setMyJob(Job myJob) {
		this.myJob = myJob;
	}
	
}
