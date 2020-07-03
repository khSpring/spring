package aopTest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
public class PerformanceAOP {

	/*
	 * aop를 위한 pom.xml설정
	 * (우선 mvnrepository 사이트 들어가서)
	 * - aspectj runtime으로 검색	(위쪽에 aspectj-version태그의 버전을 바꿔주자)(1.9.4)
	 * - aspectj weaver로 검색		(aspectj runtime과 버전을 맞추자)
	 * 
	 * -spring-aop로 검색		(5.2.7(스프링 최신버전으로 등록한 버전과 맞추자))
	 */
	
	/*
	 *  1. XML (xml 기반)
	 *  	servlet-context.xml에서 설정
	 *  	
	 *  2. @Aspect (어노테이션 기반)
	 *  
	 *  5가지 유형의 어드바이스 : Before, After, AfterReturning, AfterThrowing, Around
	 */
	
	public Object aroundLog(ProceedingJoinPoint pp) throws Throwable
	{
		String methodName = pp.getSignature().getName();
		
		System.out.println(methodName);
		
		StopWatch stopWatch = new StopWatch();
		
		stopWatch.start();
		
		Object obj = pp.proceed();
		
		stopWatch.stop();
		
//		System.out.println("around 실행시 : " + methodName + "() 메소드");
		
		System.out.println("around 실행시 : " + methodName + "() 메소드 수행에 걸린 시간 : " + stopWatch.getTotalTimeMillis() + "(ms)");
		
		return obj;
	}
	
}
