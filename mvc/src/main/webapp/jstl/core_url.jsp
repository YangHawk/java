<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC</title>
</head>
<body>
	<h1>Core - URL 관리 태그</h1>
	<hr>
	<%-- 웹 자원의 경로를 상대 경로로 표현하여 제공 --%>
	<%-- 상대 경로: 요청 웹 프로그램의 경로를 기준으로 웹 자원의 경로를 표현하는 방법 --%>
	<%-- 문제점: MVC 디자인 패턴을 이용한 JSP Model-2 방식의 웹 프로그램 작성 시 요청 웹 프로그램(Controller)의 경로와 응답 웹 프로그램(View)의 경로가 다른 경우 404 에러 발생 --%>
	<%-- 해결법: 웹 자원의 경로를 정대 경로로 표현하여 제공 --%>

	<img src="images/Tulips.jpg" width="300">

	<%-- 절대 경로: 최상위 디렉토리(Server Root Directory)를 기준으로 웹 자원의 경로를 표현하는 방법 --%>
	<%-- 문제점: 컨텍스트의 이름을 변경할 경우 컨텍스트 경로가 변경되어 404 에러 발생 --%>	
	<%-- 해결법: 컨텍스트 경로를 제공받아 웹 자원을 절대 경로로 표현하여 제공 --%>
	<img src="/mvc/jstl/images/Tulips.jpg" width="300">
	<%-- Server Root Directory ↔ Context Root Directory--%>	

	<%-- request.getContextPath() 메소드를 호출하여 컨텍스트 경로를 반환받아 절대 경로로 표현 --%>	
	<img src="<%=request.getContextPath() %>/jstl/images/Tulips.jpg" width="300">

	<%-- EL 표현식에서 pageContext 내장 객체를 사용하여 컨텍스트 경로를 제공받아 절대 경로로 표현 --%>
	<img src="${pageContext.request.contextPath }/jstl/images/Tulips.jpg" width="300">

	<%-- url 태그: 컨텍스트 경로가 포함된 웹 자원의 절대 경로를 제공하는 태그 --%>	
	<%-- value 속성: 컨텍스트 경로를 제외한 웹 자원의 절대 경로를 속성값으로 설정 --%>	
	<img src="<c:url value="/jstl/images/Tulips.jpg"/>" width="300">
</body>
</html>