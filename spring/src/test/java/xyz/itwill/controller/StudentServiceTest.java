package xyz.itwill.controller;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lombok.extern.slf4j.Slf4j;
import xyz.itwill10.dto.Student;
import xyz.itwill10.service.StudentService;

@RunWith(SpringJUnit4ClassRunner.class)
// @WebAppConfiguration: 스프링 컨테이너를 ApplicationContext 객체가 아닌, WebApplicationContext 객체를 스프링 컨테이너로 사용할 수 있도록 설정하기 위한 어노테이션
@WebAppConfiguration
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
// @FixMethodOrder: 테스트 메소드의 호출 순서를 설정하기 위한 어노테이션
// value 속성: MethodSorters 자료형(Enum)의 상수 중 하나를 속성값으로 설정
// ▶ MethodSorters.DEFAULT: JUnit 프로그램의 내부 규칙에 의하여 정렬되어 메소드 호출 - 테스트 프로그램 실행 시 동일한 순서로 메소드 호출
// ▶ MethodSorters.JVM: JVM에 의해 정렬되어 메소드 호출 - 테스트 프로그램을 실행할 때마다 불규칙적인 순서로 메소드 호출
// ▶ MethodSorters.NAME_ASCENDING: 테스프 메소드의 이름을 오름차순 정렬하여 메소드 호출
@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
@Slf4j
public class StudentServiceTest {
	@Autowired //Injection 처리
	private StudentService studentService;
	
	@Test
	public void testAddStudent() {
		/*
		
		Student student = new Student();
		student.setNo(1031);
		student.setName("로빈훗");
		student.setEmail("hoothoot@naver.com");
		student.setPhone("010-5151-5151");
		student.setAddress("로빈시 로빈구");
		
		※이렇게 쓰지 말고 Student 클래스에 @Builder 어노테이션을 추가※
		
		 */
		//Student 클래스의 builder() 메소드를 호출하여 StudentBuilder 객체를 제공받아 필드 관련 메소드로 필드값을 변경하고 build() 메소드를 호출하여 Student 객체를 반환받아 사용
		Student student =Student.builder().no(1031).name("로빈훗").email("hoothoot@naver.com").phone("010-5151-5151").address("로빈시 로빈구").build();
		
		studentService.addStudent(student);
	}
	
	@Test
	public void testGetStudentList() {
		List<Student> studentList = studentService.getStudentList();

		for (Student student : studentList) {
			// DTO 클래스의 객체로 toString 메소드를 호출하여 모든 필드값을 문자열로 반환받아 출력
			log.info(student.toString());
		}
	}
}
