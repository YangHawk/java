package xyz.itwill07.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;

public class ExecutionTimeAdvice {
	/*
	
	long startTime = 0;
	long endTime = 0;

	public void startTime(JoinPoint joinPoint) {
		System.out.println("시간을 측정합니다!");
		startTime = System.currentTimeMillis();
	}

	public void endTime(JoinPoint joinPoint) {
		endTime = System.currentTimeMillis();
		String className = joinPoint.getTarget().getClass().getSimpleName();
		String methodName = joinPoint.getSignature().getName();
		System.out.println(className + " 클래스의 " + methodName + " 메소드 실행 시간 = " + (endTime - startTime)+"ms");
	}
	
	*/
	
	/*
	
	// 타겟 메소드의 명령이 실행되는 처리 시간을 계산하여 기록하기 위한 메소드 - Around Advice Method
	public Object timeWatchLog(ProceedingJoinPoint joinPoint) throws Throwable {
		// 타겟 메소드의 명령 실행 전 동작될 명령 작성
		long startTime = System.currentTimeMillis();
		Object returnValue = joinPoint.proceed();

		// 타겟 메소드의 명령 실행 후 동작될 명령 작성
		long endTime = System.currentTimeMillis();

		String className = joinPoint.getTarget().getClass().getSimpleName();
		String methodName = joinPoint.getSignature().getName();

		System.out.println(className + " 클래스의 " + methodName + "() 메소드 실행 시간 = " + (endTime - startTime)+"ms");

		return returnValue;
	}
	
	*/
	
	// 타겟 메소드의 명령이 실행되는 처리 시간을 계산하여 기록하기 위한 메소드 - Around Advice Method
		public Object timeWatchLog(ProceedingJoinPoint joinPoint) throws Throwable {
			// 타겟 메소드의 명령 실행 전 동작될 명령 작성
			// StopWatch 객체: 시간을 측정하기 위한 기능을 제공하는 객체
			StopWatch stopWatch = new StopWatch();
			
			// StopWatch.start(): 시간 측정을 시작하는 메소드
			stopWatch.start();
			
			Object returnValue = joinPoint.proceed();

			// 타겟 메소드의 명령 실행 후 동작될 명령 작성
			// StopWatch.stop(): 시간 측정을 종료하는 메소드
			stopWatch.stop();

			String className = joinPoint.getTarget().getClass().getSimpleName();
			String methodName = joinPoint.getSignature().getName();

			System.out.println(className + " 클래스의 " + methodName + "() 메소드 실행 시간 = " + (stopWatch.getTotalTimeMillis())+"ms");

			return returnValue;
		}
}
