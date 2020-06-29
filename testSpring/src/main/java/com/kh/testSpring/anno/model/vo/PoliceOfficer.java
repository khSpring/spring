package com.kh.testSpring.anno.model.vo;

import org.springframework.stereotype.Component;

@Component("policeOfficer")
public class PoliceOfficer implements Job{
	@Override
	public void jobInfo(String compName) {
		System.out.println(compName + "에서 범죄 대응 중입니다.");
		
	}

	@Override
	public void printJob() {
		System.out.println("경찰관");
		
	}
}
