package xyz.itwill.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// Spring Security: SpringMVC 기반 프로그램의 인증과 인가 기능을 지원하는 보안 프레임워크

// ▶ 인증(Authentication): 로그인 사용자가 맞는 지를 확인하는 절차
// ▶ ▶ 인증을 정상적으로 수행하기 위하여 사용자를 식별할 수 있는 정보가 필요 - Credential(ID / PASSWD)

// ▶ 인가(Authorization - 권한 부여): 인증된 사용자가 요청된 자원에 접근 가능한 가를 결정하는 절차
// ▶ ▶ 인증 처리 후 실행이 될 수 있도록 - 권한은 일반적으로 역할(Role) 형태로 부여됨

// Spring Security는 인증과 인가를 위하여 Principal 객체를 아이디로 Credential 객체를 비밀 번호로 사용하는 Credential 기반 인증 방식 사용

// Spring Security를 SpringMVC 프로그램에 적용하여 제공받을 수 있는 기능
// ▶ 다양한 형태(폼 로그인 인증 / 토큰 기반 인증 / OAuth2 기반 인증 / LDAP 인증)의 사용자 인증 기능을 제공
// ▶ 프로그램 사용자의 역할(Role)에 따른 권한 레벨 적용 가능
// ▶ 프로그램에서 제공하는 자원에 대한 접근 제어 가능
// ▶ 민감한 데이터를 암호화 가능
// ▶ SSL 적용 가능
// ▶ 일반적으로 알려진 웹 보안 공격 차단 가능

// Spring Security를 SpringMVC 프로그램에 적용하는 방법
// 1. spring-security-web / spring-security-core / spring-security-config / spring-security-taglibs 라이브러리를 프로젝트에 빌드 처리 - 메이븐: pom.xml
// ▶ 모든 라이브러리 버전을 동일하게 설정하면 Spring 프레임워크 관련 라이브러리의 버전을 고려하여 빌드 처리 
// 2. [web.xml] 파일에 Spring Security 기능을 제공하는 Filter 클래스를 Filter로 등록하고 Filter가 실행되기 위한 URL 주소를 매핑 처리
// 3. [web.xml] 파일에 Spring Security 기능의 필터가 사용하기 위한 정보를 제공하는 Spring Bean Configuration File 설정 - ContextLoaderListener 클래스가 읽을 수 있도록 파일 경로 지정
// 4. Spring Security 관련 Spring Bean Configuration File 작성
// ▶ Spring Security 관련 필터가 동작되기 위한 정보를 Security 네임 스페이스를 추가하여 spring-security.xsd 파일의 엘리먼트를 사용하여 제공

// Spring Security Filter의 종류
// 1. SecurityContextPersistenceFilter: SecurityContextRepository에서 SecurityContext를 가져오거나 생성하는 필터
// 2. LogoutFilter: 로그아웃 요청을 처리하는 필터
// 3. UsernamePasswordAuthenticationFilter: 아이디와 비밀번호를 사용하는 Form 기반 유저 인증을 처리하는 필터
// ▶ Authentication 객체를 만들고 AuthenticationManager에게 인증 처리 위임
// ▶ AuthenticationManager는 실질적인 인증에 대한 검증 단계를 총괄하는 AuthenticationProvider에게 인증 처리를 위임 - UserDetailService와 같은 서비스를 사용해서 인증 가능
// 4. ConcurrentSessionFilter: 동시 세션과 관련된 필터 - 이중 로그인 방지
// 5. RememberMeAuthenticationFilter: 세션이 사라지거나 만료 되더라도 쿠키 또는 DB를 사용하여 저장된 토큰을 기반으로 인증 처리하는 필터
// 6. AnonymousAuthenticationFilter: 사용자 정보가 인증되지 않았다면 익명 사용자 토큰을 반환하는 필터
// 7. SessionManagementFilter: 로그인 후 Session과 관련된 작업을 처리하는 필터
// 8. ExceptionTranslationFilter: 필터 체인 내에서 발생되는 인증 및 인가 관련 예외를 처리하는 필터
// 9. FilterSecurityInterceptor: 권한 부여와 관련한 결정을 AccessDecisionManager에게 위임해 권한 부여 결정 및 접근 제어를 처리하는 필터
// 10. HeaderWriterFilter: Request의 HTTP 헤더를 검사해 Header를 추가하거나 빼주는 필터
// 11. CorsFilter: 허가된 사이트나 클라이언트의 요청인지 검사하는 필터
// 12. CsrfFilter: CSRF Token을 사용하여 CSRF 공격을 막아주는 기능을 제공하는 필터

@Controller
public class HomeController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "home";
	}

	@RequestMapping(value = "/user/page", method = RequestMethod.GET)
	public String userPage() {
		return "user_page";
	}

	@RequestMapping(value = "/manager/page", method = RequestMethod.GET)
	public String managerPage() {
		return "manager_page";
	}

	@RequestMapping(value = "/admin/page", method = RequestMethod.GET)
	public String adminPage() {
		return "admin_page";
	}
}