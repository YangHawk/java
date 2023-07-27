package xyz.itwill05.di;

import java.util.List;

import org.springframework.stereotype.Repository;

//@Component: 클래스를 스프링 컨테이너가 관리할 수 있는 Spring Bean으로 등록하는 어노테이션
//▶ 클래스의 이름을 beanName으로 자동 설정 - 클래스의 이름에서 첫 문자는 소문자로 자동 변환
//▶ @Component 어노테이션의 value 속성을 사용하여 beanName 변경 가능
//@Component("studentDAO")

//@Repository: DAO 클래스를 스프링 컨테이너가 관리할 수 있는 Spring Bean으로 등록하기 위한 어노테이션
//▶ 클래스의 이름을 beanName으로 자동 설정하지만 value 속성을 사용하여 beanName 변경 가능
@Repository(value = "studentDAO")

//@Primary: 의존성 주입을 위한 우선권을 제공하기 위한 어노테이션
//▶ 동일한 자료형의 클래스의 @Primary 어노테이션을 여러 번 사용하면 의존성 주입 실패
//@Primary
public class AnnotationStudentJdbcDAO implements StudentDAO {
	public AnnotationStudentJdbcDAO() {
		System.out.println("※※※ AnnotationStudentJdbcDAO 클래스의 기본 생성자 호출 ※※※");
	}

	@Override
	public int insertStudent(Student student) {
		System.out.println("※※※ AnnotationStudentJdbcDAO 클래스의 insertStudent(Student student) 메소드 호출 ※※※");
		return 0;
	}

	@Override
	public int updateStudent(Student student) {
		System.out.println("※※※ AnnotationStudentJdbcDAO 클래스의 updateStudent(Student student) 메소드 호출 ※※※");
		return 0;
	}

	@Override
	public int deleteStudent(int num) {
		System.out.println("※※※ AnnotationStudentJdbcDAO 클래스의 deleteStudent(int num) 메소드 호출 ※※※");
		return 0;
	}

	@Override
	public Student selectStudent(int num) {
		System.out.println("※※※ AnnotationStudentJdbcDAO 클래스의 selectStudent(int num) 메소드 호출 ※※※");
		return null;
	}

	@Override
	public List<Student> selectStudentList() {
		System.out.println("※※※ AnnotationStudentJdbcDAO 클래스의 selectStudentList() 메소드 호출 ※※※");
		return null;
	}
}
