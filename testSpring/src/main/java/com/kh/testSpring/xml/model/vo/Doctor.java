package com.kh.testSpring.xml.model.vo;

public class Doctor implements Job{

	@Override
	public void jobInfo(String compName) {
		System.out.println(compName + "에서 환자 진료 중입니다.");
		
	}

	@Override
	public void printJob() {
		System.out.println("의사");
		
	}

}
