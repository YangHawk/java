package xyz.itwill08.dao;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StudentApp {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("08_dao.xml");
		StudentService service = context.getBean("studentService", StudentService.class);
		System.out.println("========================================");
		/*
			※ 데이터 무결성을 위하여 한번만 실행 후 주석 처리
			Student newStudent = new Student();
			newStudent.setNo(32);
			newStudent.setName("홍경래");
			newStudent.setEmail("sibalsibal@naver.com");
			newStudent.setPhone("010-6868-4949");
			newStudent.setAddress("지옥");
			service.addStudent(newStudent);
		*/
		
		/*
			Student searchStudent= service.getStudent(32);
			System.out.println(searchStudent); //toString() 메소드 자동 호출하여 필드값을 반환받아 출력
			searchStudent.setName("로빈훗");
			searchStudent.setAddress("천국");
			service.modifyStudent(searchStudent);
		*/
		
		/*
			service.removeStudent(32);
		*/
		
		List<Student> studentList = service.getStudentList();
		for (Student student : studentList) {
			System.out.println("학번 = " + student.getNo() + ", 이름 = " + student.getName() + ", 이메일 = "
					+ student.getEmail() + ", 전화번호 = " + student.getPhone() + ", 주소 = " + student.getAddress());
		}
		System.out.println("========================================");
		((ClassPathXmlApplicationContext) context).close();
	}
}