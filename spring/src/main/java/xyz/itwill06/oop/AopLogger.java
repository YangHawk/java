package xyz.itwill06.oop;

// 횡단 관심 코드로 작성된 메소드가 선언된 클래스 - 횡단 관심 모듈: Advice 클래스

public class AopLogger {
	public void beforeLog() {
		// 횡단 관심 코드
		System.out.println("★★★ 메소드의 명령(핵심 관심 코드)이 실행되기 전 기록될 내용 ★★★");
	}
}
