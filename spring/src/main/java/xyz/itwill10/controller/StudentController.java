package xyz.itwill10.controller;

import org.springframework.stereotype.Controller;

// ※SpringMVC 기능을 사용하여 웹프로그램을 작성하는 방법※
// 테이블 ▷ DTO 클래스 ▷ DAO 클래스(mybatis) ▷ Service 클래스 ▷ Controller 클래스(요청 처리 메소드)
// ▷ 테스트 프로그램 작성(JUnit) - 단위 프로그램(모듈) 테스트 ▷ HTML 문서를 JSP 문서로 변환 ▷ 통합 프로그램 테스트 - 브라우저를 이용

// 1. STUDENT 테이블 이용 / 2. xyz.itwill10.dto 패키지에 Student.java 클래스(DTO) 생성 / 3. xyz.itwill10.dao 패키지에 DAO 
// DAO 만들기 전 인터페이스! 근데 mybatis는 매퍼 파일 필요! 근데 그 전에 SQLSESSION 팩토리 필요 근데 그거 할려면 mybatis-config 필요
@Controller
public class StudentController {
	
}
