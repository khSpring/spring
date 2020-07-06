package aopTest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
//	component-scan은 aop Test까지락고 이미 했으니 따로 설정할 필요x
@Aspect
// servlet-context.xml에서 <aop:aspectj-autoproxy/>를 추가해 두었기 때문에
// Aspect 클래스로 등록 됨
public class LoggingAspect {
	
//	@Before("execution(public * com.kh.springProject..*ServiceImpl.*(..))")
//	public void before(JoinPoint joinPoint)
//	{
//		String signatureString = joinPoint.getSignature().getName();
//		
//		System.out.println("@Before [ " + signatureString + " ] 메소드 실행 전 수행");
//		
//		for(Object arg : joinPoint.getArgs())
//		{
//			System.out.println("@Before [ " + signatureString + " ] 아규먼트" + arg);
//		}
//	}
	
//	@AfterReturning(pointcut="execution(public * com.kh.springProject..*ServiceImpl.*(..))", returning="ret")
//	public void before(JoinPoint joinPoint, Object ret)
//	{
//		String signatureString = joinPoint.getSignature().getName();
//		
//		for(Object arg : joinPoint.getArgs())
//		{
//			System.out.println("@AfterReturning [ " + signatureString + " ] 아규먼트" + arg);
//		}
//		System.out.println("@AfterReturning [ " + signatureString + " ] 메소드 실행 후 실행");
//		System.out.println("@AfterReturning [ " + signatureString + " ] 리턴값 = " + ret);
//	}
	
	@AfterThrowing(pointcut="execution(public * com.kh.springProject..*Controller.*(..))", throwing="ex")
	public void before(JoinPoint joinPoint, Throwable ex)
	{
		String signatureString = joinPoint.getSignature().getName();
		
		for(Object arg : joinPoint.getArgs())
		{
			System.out.println("@AfterThrowing [ " + signatureString + " ] 아규먼트" + arg);
		}
		System.out.println("@AfterThrowing [ " + signatureString + " ] 메소드 실행 후 실행");
		System.out.println("@AfterThrowing [ " + signatureString + " ] 예외 = " + ex);
	}
}
