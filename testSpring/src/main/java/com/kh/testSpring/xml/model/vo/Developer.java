package com.kh.testSpring.xml.model.vo;

public class Developer implements Job{

	@Override
	public void jobInfo(String compName) {
		System.out.println(compName + "에서 개발 업무를 수행 중입니다.");
		
	}

	@Override
	public void printJob() {
		System.out.println("개발자");
		
	}

}
