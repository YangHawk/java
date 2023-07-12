<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//POST 방식으로 요청하여 리퀘스크 메세지 몸체부에 저장되어 전달되는 값에 대한 캐릭터셋 변경
	request.setCharacterEncoding("utf-8");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC</title>
</head>
<body>
	<h1>EL 내장 객체 - Request Parameter</h1>
	<hr>
	<h2>EL 사용 X</h2>
	<ul>
		<li>이름 = <%=request.getParameter("name")%></li>
		<li>주소 = <%=request.getParameter("address")%></li>
		<%-- 같은 이름으로 전달된 값이 여러 개인 경우 첫번째 전달값을 반환받아 사용 가능 --%>
		<li>음식 - 1 = <%=request.getParameter("food")%></li>
		<li>음식 - 2 = <%=request.getParameter("food")%></li>
		<%-- 같은 이름으로 전달된 값이 여러 개인 경우 getParameterValues() 메소드를 호출하여 모든 전달값을 문자열 배열로 반환받아 사용 가능 --%>
		<li>음식 - 1 = <%=request.getParameterValues("food")[0]%></li>
		<li>음식 - 2 = <%=request.getParameterValues("food")[1]%></li>
	</ul>
	<hr>
	<h2>EL 사용</h2>
	<ul>
		<%-- EL 표현식에서 param 내장객체 또는 paramValues 내장객체를 사용하여 전달값을 제공받아 출력 처리 가능 --%>
		<%-- ▶ param 내장객체 또는 paramValues 내장객체는 Map 객체로 맵키 대신 전달값의 이름을 사용하여 전달값 구분 --%>
		<li>이름 = ${param.name }</li>
		<li>주소 = ${param.address }</li>
		<li>음식-1 = ${paramValues.food[0] }</li>
		<li>음식-2 = ${paramValues.food[1] }</li>
	</ul>
</body>
</html>