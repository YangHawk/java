package xyz.itwill10.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import xyz.itwill10.dto.Student;
import xyz.itwill10.mapper.StudentMapper;

// ※SpringMvc 기능으로 웹프로그램 작성 시 Mybatis 프레임워크를 이용하여 DAO 클래스를 작성하는 방법※
// 1. DataSource 관련 라이브러리와 Mybatis 관련 라이브러리를 프로젝트에 빌드 처리 - 메이븐: pom.xml 수정
// ▶ ojdbc / spring-jdbc(spring-tx도 같이 빌드 처리됨) / mybatis / mybatis-spring
// 2. Mybatis 프레임워크의 환경 설정 파일(mybatis-config.xml) 작성 - settings 엘리먼트만 작성
// ▶ [src/main/webapp] 경로에 작성해야만 스프링 컨테이너(WebApplicationContext 객체)가 Mybatis 프레임워크의
// 환경 설정 파일을 읽어서 SqlSessionFactory 클래스를 Spring Bean으로 등록 처리
// ▶ [src/main/java] 또는 [src/main/resources] 폴더에 Mybatis 프레임워크의 환경 설정 파일 작성 가능
// 3. DataSource 관련 클래스, SqlSessionFactory 관련 클래스, SqlSession 관련 클래스를 Spring Bean으로 등록
// ▶ SpringMVC 프로그램에서 스프링 컨테이너를 초기화 처리하기 위한 Spring Bean Configuration File에서 
// bean 엘리먼트로 클래스를 Spring Bean으로 등록 - [root-context.xml] 또는 [servlet-context.xml]

// 모든 컨트롤러에서 마이바티스 - root-context.xml / 앱서블릿에서 마이바티스 - servlet-context.xml
// web.xml에서 리스너 보면 어쩌구 로더가 rootcontext d읽잔아! 약간 그런거!
// 디스패쳐 서블릿도 서블릿 어쩌구 하는데걔는 servlet-context.xml 읽ㄷ잖아! 그런거야! ㅣㅆ발! 다시 봐야 해! 좆같네!

// 4. 테이블 생성 ▷ DTO 클래스 작성 ▷ 매퍼 파일 작성 ▷ DAO 클래스 작성 - 테이블을 만들 때마다 반복 작업

// DAO 클래스(Repository 클래스): 저장 매체(DBMS)에게 행에 대한 삽입 / 변경 / 삭제 / 검색 기능을 제공하기 위한 객체를 생성하기 위한 클래스
// ▶ DAO 클래스의 메소드에서는 DBMS 서버에 SQL 명령을 전달하여 실행하고 실행 결과를 Java 객체(값)으로 반환되도록 작성
// ▶ DAO 클래스가 변경되어도 의존 관계로 설정된 Service 클래스의 영향을 최소화하기 위한 인터페이스를 상속받아 작성

// DAO 클래스는 Service 클래스의 객체로 제공되어 사용되도록 반드시 Spring Bean으로 등록
// ▶ DAO 클래스는 @Repository 어노테이션을 사용하여 Spring Bean으로 등록
// ▶ @Repository 어노테이션을 사용하면 SQL 명령으로 발생되는 예외를 Spring 관련 예외로 제공 - @Component 어노테이션을 사용하지 않는 이유!
// ▶ @Repository 어노테이션을 스프링 컨테이너가 처리하기 위하여 반드시 클래스가 작성된 패키지(base-package)를 Spring Bean Configuration File(servlet-context.xml)의
// component-scan 엘리먼트로 검색되도록 설정해 주어야 함
@Repository
// final 제한자로 작성된 필드만 초기화 처리하는 생성자를 만들어주는 어노테이션
// ▶ 생성자가 하나만 작성된 경우 생성자에 @Autowired 어노테이션을 생략할 수 있음
@RequiredArgsConstructor
public class StudentDAOImpl implements StudentDAO {
	// Mybatis 프레임워크를 사용하여 DAO 클래스를 작성할 경우 매퍼에 등록된 SQL 명령을 제공받아
	// 전달하여 실행하고 실행 결과를 Java 객체(값)으로 반환받기 위하여 SqlSession 객체가 필요
	// ▶ SqlSession 객체를 저장할 수 있는 필드를 선언하여 스프링 컨테이너가 관리하는 Spring Bean을 제공받아 의존성 주입(DI)
	// [root-context.xml]에 SqlSessionTemplate 클래스가 sqlSession이라는 이름으로 Spring Bean으로
	// 등록 되어 있는데, 이걸 갖고 와야 함! - @RequiredArgsConstructor 와 final 제한자로...
	// ▶ 매개 변수가 선언된 생성자를 작성하여 @Autowired 어노테이션을 사용하여 의존성 주입 - 순환 참조를 방지하기 위함
	private final SqlSession sqlSession;

	@Override
	public int insertStuedent(Student student) {
		return sqlSession.getMapper(StudentMapper.class).insertStudent(student);
	}

	@Override
	public List<Student> selectStudentList() {
		return sqlSession.getMapper(StudentMapper.class).selectStudentList();
	}

}