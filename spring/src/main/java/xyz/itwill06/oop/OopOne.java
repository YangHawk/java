package xyz.itwill06.oop;

// OOP의 문제점: 모듈화(캡슐화)가 너무 강력하여 핵심 관심 코드와 횡단 관심 코드를 분리하여 프로그램 작성이 어려움
// ▶ 코드의 중복성이 높아 프로그램의 생산성 및 유지 보수의 효율성 ↓
public class OopOne {
	/*
	private void beforeLog() {
		System.out.println("★★★ 메소드의 명령(핵심 관심 코드)이 실행되기 전 기록될 내용 ★★★");
	}
	*/
	
	private OopLogger logger = new OopLogger();

	public void display1() {
		// 횡단 관심 코드: 프로그램 실행에 보조적인 기능을 제공하기 위한 명령
		// ▶ 로그 처리 / 권한 처리 / 트랜잭션 처리 / 예외 처리 등
		// System.out.println("★★★ 메소드의 명령(핵심 관심 코드)이 실행되기 전 기록될 내용 ★★★");
		logger.beforeLog();

		// 핵심 관심 코드: 프로그램 실행에 핵심적인 기능을 제공하는 명령 - 데이터 처리 명령
		System.out.println("※※※ OopOne 클래스의 display1() 메소드 호출 ※※※");
	}

	public void display2() {
		// System.out.println("★★★ 메소드의 명령(핵심 관심 코드)이 실행되기 전 기록될 내용 ★★★");
		logger.beforeLog();
		System.out.println("※※※ OopOne 클래스의 display2() 메소드 호출 ※※※");
	}

	public void display3() {
		// System.out.println("★★★ 메소드의 명령(핵심 관심 코드)이 실행되기 전 기록될 내용 ★★★");
		logger.beforeLog();
		System.out.println("※※※ OopOne 클래스의 display3() 메소드 호출 ※※※");
	}
}