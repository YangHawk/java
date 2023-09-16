<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 시큐리티 태그 라이브러리를 JSP 문서에 포함 - Spring Security 태그 사용 가능 --%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SPRING</title>
</head>
<body>
	<sec:authentication property="principal" var="loginUser"/>
	<h1>메인 페이지</h1>
	<hr>
	<%-- authorize: 권한을 비교하여 태그의 포함 여부를 설정하기 위한 태그 --%>
	<%-- access 속성: 권한(Role)을 속성값으로 설정 - SpEL 사용 가능 --%>
	<%-- 인증을 받지 않은 사용자인 경우 태그가 포함되도록 설정 --%>
	<sec:authorize access="isAnonymous()">
		<h3><a href="<c:url value="/loginPage"/>">로그인</a></h3>
	</sec:authorize>
	<p><a href="<c:url value="/guest/page"/>">GUSET</a></p>
	<p><a href="<c:url value="/user/page"/>">USER</a></p>
	<p><a href="<c:url value="/manager/page"/>">MANAGER</a></p>
	<p><a href="<c:url value="/admin/page"/>">ADMINISTRATOR</a></p>
	<%-- 인증된 사용자인 경우 태그가 포함되도록 설정 --%>
	<sec:authorize access="isAuthenticated()">
		<%-- 로그아웃 처리 기능을 제공하는 페이지는 반드시 form 태그를 사용하여 요청 --%>	
		<%-- ▶ CSRF 토큰을 전달하여 처리되도록 설정 --%>
		
		<h3><sec:authentication property="principal.username"/>님, 환영합니다.</h3>
		<h3><sec:authentication property="principal.userid"/>님, 영광입니다.</h3>
		<h3><sec:authentication property="principal.name"/>님, 사랑합니다.</h3>
		<h3>${loginUser.name }님, 숭배합니다.</h3>
				
		<form action="<c:url value="/logout"/>" method="post">
			<sec:csrfInput/>
			<button type="submit">로그아웃</button>
		</form>
	</sec:authorize>
	<hr>
	
	<%-- authentication: 인증된 사용자에 대한 정보(UserDetails 객체)를 제공하기 위한 태그 --%>
	<%-- ▶ 인증된 사용자에게만 필요한 정보를 제공 가능(authorize) --%>
	<%-- property 속성: 인증된 사용자에 대한 정보를 제공받기 위한 속성명(필드명)을 속성값으로 설정 --%>
	
	<sec:authorize access="isAuthenticated()">
		<%-- principal: UserDetails 인터페이스를 상속받은 User 객체가 저장된 속성  --%>
		<%-- ▶ User 객체의 username 필드: 사용자의 식별자(아이디)가 저장된 필드 --%>
		<%-- ▶ User 객체의 enabled 필드: 사용자의 활성 관련 논리값이 저장된 필드 --%>
		<%-- ▶ User 객체의 accountNonExpired 필드: 사용자의 유효 날짜 관련 논리값이 저장된 필드 --%>
		<%-- ▶ User 객체의 credentialsNonExpired 필드: 사용자의 비밀 번호 만료 기간 관련 논리값이 저장된 필드 --%>
		<%-- ▶ User 객체의 accountNonLocked 필드: 사용자의 잠금 관련 논리값이 저장된 필드 --%>
		<%-- ▶ User 객체의 grantedAuthorities 필드: 사용자의 권한 관련 정보를 요소로 가진 List 객체가 저장된 필드 --%>
		
		<%-- <h3><sec:authentication property="principal"/></h3> --%>
		<h3>${loginUser }</h3>
	</sec:authorize>	
</body>
</html>