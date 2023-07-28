package xyz.itwill06.oop;

// 핵심 관심 모듈: 핵심 관심 코드로 작성된 메소드가 선언된 클래스
// ▶ 횡단 관심 코드가 삽입될 메소드는 인터페이스르 상속받아 작성
public class AopOne implements Aop {
	@Override
	public void display1() {
		// 핵심 관심 코드
		System.out.println("※※※ AopOne 클래스의 display1() 메소드 호출 ※※※");
	}

	@Override
	public void display2() {
		System.out.println("※※※ AopOne 클래스의 display2() 메소드 호출 ※※※");
	}

	@Override
	public void display3() {
		System.out.println("※※※ AopOne 클래스의 display3() 메소드 호출 ※※※");
	}
}
