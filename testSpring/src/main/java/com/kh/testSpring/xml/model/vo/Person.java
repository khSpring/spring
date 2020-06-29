package com.kh.testSpring.xml.model.vo;

public class Person {
	// 1. Field
	private String name;
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
