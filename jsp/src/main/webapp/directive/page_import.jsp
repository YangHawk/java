<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ page language="java" contentType="text/xml; charset=UTF-8" pageEncoding="UTF-8"%> --%>
<%-- page 디렉티브에서 다른 속성값이 저장된 동일한 속성을 여러 번 선언할 경우 에러 발생 --%>
<%
//java.util.List<String> nameList = new java.util.ArrayList<>();
List<String> nameList = new ArrayList<>();
nameList.add("홍길동");
nameList.add("임꺽정");
nameList.add("전우치");
nameList.add("일지매");
nameList.add("장길산");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
</head>
<body>
	<h1>page Directive - import 속성</h1>
	<hr>
	<p>page Directive의 import 속성에는 JSP 문서에서 사용할 클래스 또는 인터페이스를 속성값으로 설정 - 클래스 또는 인터페이스를 패키지를 사용하여 명확하게 표현하여 제공하기 위한 사용</p>
	<hr>
	<UL>
		<%
		for (String name : nameList) {
		%>
		<li><%=name%></li>
		<%
		}
		%>
	</UL>
</body>
</html>