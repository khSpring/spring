package com.kh.testSpring.di.model.vo;

public class BeanFactory {
	// 빈 펙토리는 스프링 컨테이너로 bean 객체들을 관리하는 녀석이다.
	// 개발자가 원하는 객체를 선택하여 생성해 줄수 있는 로직이 구성되어 있다.
	public static Object getBean(String beanName) {
		TV tv = null;
		if(beanName.equals("samsung")) {
			tv = new SamsungTV();
		}else {
			tv = new LgTV();
		}
		return tv;
	}
}
