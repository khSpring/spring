package com.kh.testSpring.di.model.vo;

public class SamsungTV implements TV{

	@Override
	public void turnOn() {
		System.out.println("SamsungTV 전원이 켜집니다!");
		
	}

	@Override
	public void turnOff() {
		System.out.println("SamsungTV 전원이 꺼집니다!");
		
	}

	@Override
	public void volumnUp() {
		System.out.println("SamsungTV 소리를 키웁니다...");
		
	}

	@Override
	public void volumnDown() {
		System.out.println("SamsungTV 소리를 줄입니다...");
		
	}

}
