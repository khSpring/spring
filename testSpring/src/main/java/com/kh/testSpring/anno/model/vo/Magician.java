package com.kh.testSpring.anno.model.vo;

import org.springframework.stereotype.Component;

@Component("magician")
public class Magician implements Job{

	@Override
	public void jobInfo(String compName) {
		System.out.println(compName + "에서 마술 공연 중입니다.");
		
	}

	@Override
	public void printJob() {
		System.out.println("마술사");
		
	}

}
