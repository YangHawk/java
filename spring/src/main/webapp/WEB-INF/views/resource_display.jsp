<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SPRING</title>
<link href="<c:url value = "/css/style.css"/>" type="text/css" rel="stylesheet">
</head>
<body>
	<h1>리소스 파일</h1>
	<hr>
	<%-- 리소스 파일(Resource File): 클라이언트가 웹문서(HTML 문서)를 해석하여 실행하기 위하여 필요한 정보를 제공하기 위하여 웹서버에 저장된 파일 --%>
	<%-- ▶ CSS 파일, JavaScript Source 파일, 멀티미디어 파일 등 --%>
	<%-- ▶ 웹문서(HTML 문서)를 해석할 때 리소스 파일이 없는 경우 404 에러 코드 발생 - 비정상적인 응답 결과 출력 --%>
	<%-- ※문제점: 리소스 파일을 WEB-INF 폴더에 저장할 경우 클라이언트는 리소스 파일을 요청할 수 없음 --%>
	<%-- WEB-INF 폴더: 웹서버에서 실행되는 프로그램(서블릿)에서만 접근 가능한 폴더 - 클라이언트에게는 은닉화 처리된 폴더 --%>
	<%-- <img src="/spring/WEB-INF/views/Koala.jpg" width="300"> --%>

	<%-- ※문제점: SpringMVC 프로그램에서는 클라이언트의 모든 요청을 Front Controller(서블릿)를 이용하여 처리될 수 있도록 url-pattern 설정 --%>
	<%-- ▶ 클라이언트가 서버의 리소스 파일을 요청할 경우 Front Controller가 요청 URL 주소를 분석하여 요청 처리 메소드를 호출하지만, --%>
	<%-- 리소스 파일에 대한 요청 처리 메소드가 매핑된 요청 처리 메소드가 없으므로 404 에러 코드 발생  --%>
	<%-- <img src="/spring/Koala.jpg" width="300"> --%>

	<%-- ※해결법: 클라이언트가 리소스 파일을 요청할 경우 Front Controller를 이용하지 않고 / Front Controller가 요청 처리 메소드를 호출하지 않고
	 직접 응답되도록 처리 - Spring Bean Configuration File(servlet-context.xml) 변경 --%>

	<%-- servlet-context.xml 파일의 resources 엘리먼트의 location 속성값으로 설정된 폴더 및 하위 폴더에 리소스 파일을 저장하여 mapping 속성값으로 설정된 URL 주소 형식으로  --%>
	<%-- <img src="/spring/resources/Koala.jpg" width="300"> --%>
	<img src="/spring/images/Koala.jpg" width="300">

	<%-- 프로젝트에 대한 컨텍스트 이름이 변경될 경우 컨텍스트 경로가 변경될 수 있으므로 리소스 파일에 대한 컨텍스트 경로는 직접 설정하지 않고 Front Controller에게 객체로 제공받아 사용 --%>
	<%-- EL의 pageContext 내장 객체를 사용하여 컨텍스트 경로를 제공받아 사용  --%>
	<img src="${pageContext.request.contextPath }/images/Koala.jpg"	width="400">

	<%-- core 태그 라이브러리의 url 태그를 사용하여 컨텍스트 경로를 제공받아 사용 --%>
	<img src="<c:url value="/images/Koala.jpg"/>" width="500">
	
	<%-- srping 태그 라이브러리의 url 태그를 사용하여 컨텍스트 경로를 제공받아 사용 --%>
	<img src="<spring:url value="/images/Koala.jpg"/>" width="600">
</body>
</html>