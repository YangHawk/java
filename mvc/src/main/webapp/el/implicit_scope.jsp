<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//JSP 내장 객체에 따라 속성값을 저장하여 사용할 수 있는 범위를 다르게 설정 가능
	//스코프 객체 속성값의 사용 범위: Page Scope / Request Scope / Session Scope / Application Scope
	pageContext.setAttribute("pageName", "김페이"); //Page Scope: 속성값을 저장한 JSP 문서에서만 사용 가능
	request.setAttribute("requestName", "이리퀘"); //Request Scope: + 스레드가 이동된 JSP 문서 또한 사용 가능
	session.setAttribute("sessionName", "박세션"); //Session Scope: + 동일한 세션이 바인딩된 모든 JSP 문서에서 사용 가능
	application.setAttribute("applicationName", "최어플"); //Application Scope: + 동일한 WAS로 관리되는 모든 JSP 문서에서 사용 가능
	
	//JSP 내장 객체가 다른 경우 동일한 속성명을 사용하여 속성값 저장 가능
	//▶ 같은 JSP 내장 객체에 동일한 속성명을 사용하여 속성값을 저장하면 기존 속성값 대신 새로운 속성값이 저장됨 - 변경 처리
	pageContext.setAttribute("name", "김페이");
	request.setAttribute("name", "이리퀘");
	session.setAttribute("name", "박세션");
	application.setAttribute("name", "최어플");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC</title>
</head>
<body>
	<h1>EL 내장 객체 - Scope Attribute</h1>
	<hr>
	<h2>스코프 객체의 속성명이 서로 다른 경우</h2>
	<ul>
		<%-- EL 표현식에서는 스코프 객체에 상관없이 속성명을 사용하여 속성값을 제공받아 출력 처리 --%>
		<li>pageName 속성값(Page Scope) = ${pageName}</li>
		<li>requestName 속성값(Request Scope) = ${requestName}</li>
		<li>sessionName 속성값(Session Scope) = ${sessionName}</li>
		<li>applicationName 속성값(Application Scope) = ${applicationName}</li>
	</ul>
	<ul>
		<li>pageName 속성값(Page Scope) = ${name}</li>
		<li>requestName 속성값(Request Scope) = ${name}</li>
		<li>sessionName 속성값(Session Scope) = ${name}</li>
		<li>applicationName 속성값(Application Scope) = ${name}</li>
		<%-- ${name} 은 김페이만 출력됨 왜? ▶ EL 표현식은 속성값을 제공받기 위한 순서가 있다 --%>
		<%-- EL 표현식으로 속성값을 제공받기 위한 순서 --%>
		<%-- ▶ Page Scope 가 없으면 ▶ Request Scope 가 없으면 ▶ Session Scope 가 없으면 ▶ Application Scope --%>
		<%-- ▶ JSP 내장 객체(Scope 객체)에 동일한 이름의 속성명을 사용하여 속성값을 저장한 경우 먼저 검색된 객체의 속성값을 제공받아 출력 처리 --%>
		<%-- ▶ Scope 객체에 속셩명은 다르게 설정하는 것을 권장 --%>
		
		<%-- Scope 객체에 동일한 이름의 속성명을 사용하여 속성값을 저장한 경우 EL 내장 객체를 사용하여 스코프 객체의 속성값을 구분하여 출력 처리 --%>
		<%-- EL 내장 객체: pageScope / requestScope / sessionScope / applicationScope - MAP 객체 --%>
		<li>pageName 속성값(Page Scope) = ${pageScope.name}</li>
		<li>requestName 속성값(Request Scope) = ${requestScope.name}</li>
		<li>sessionName 속성값(Session Scope) = ${sessionScope.name}</li>
		<li>applicationName 속성값(Application Scope) = ${applicationScope.name}</li>
	</ul>
</body>
</html>