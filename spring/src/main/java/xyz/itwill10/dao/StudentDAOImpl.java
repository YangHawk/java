package xyz.itwill10.dao;

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

public class StudentDAOImpl {

}