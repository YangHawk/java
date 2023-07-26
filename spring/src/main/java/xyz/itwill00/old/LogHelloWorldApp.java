package xyz.itwill00.old;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogHelloWorldApp {
	// Logger 객체: 로그 이벤트를 발생시키기 위한 객체 - 기록은 로그 구현체가 알아서...
	// ▶ 로그 이벤트가 발생되면 로그 구현체가 해당 로깅 정보를 제공받아 기록
	// LoggerFactory 클래스: Logger 객체를 생성하여 제공하기 위한 클래스
	// LoggerFactory.getLogger(Class<T> clazz): 메모리에 저장된 클래스(Class 객체 - Clazz)를 전달받아
	// 해당 클래스에서 로그 이벤트를 발생시킬 수 있는 Logger 객체를 생성하여 반환하는 메소드
	private static final Logger logger = LoggerFactory.getLogger(LogHelloWorldApp.class);
	// private static final = 상수처럼!

	public static void main(String[] args) {
		// Logger.info(String message): Logger 객체로 INFO 레벨의 로그 이벤트를 발생하는 메소드 - 여러 가지...
		// ▶ 매개 변수에는 로그 구현체로 기록될 로그 메세지 전달
		logger.info("시작");
		LogHelloWorld hw = new LogHelloWorld();
		String message = hw.hello("좆길동");
		System.out.println("message  = " + message);
		logger.info("종료");
	}
	// console 창에 로그 이벤트가 기록되지 않는 이유: Root Logger에서 root 엘리먼트 내 priority 엘리먼트의 value
	// 속성값이 warn이기 때문에 warn 이상의 로그 이벤트만 기록됨

	/*
	해결법 - 1
	logger.warn("시작"); 으로 변경 - WARN 레벨 이상의 로그 이벤트만 발생하기 때문

	해결법 - 2
	log4j.xml 파일에 logger 엘리먼트 추가
	<logger name="xyz.itwill00.log">
		<level value="info" />
	</logger>
	- xyz.itwill00.log 패키지의 로그 이벤트는 INFO 레벨부터 기록되게 해주세요!
	 */
}
