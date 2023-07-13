<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="simple" uri="http://www.itwill.xyz/mvc/custom"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC</title>
</head>
<body>
	<h1>Custom Tag - AnyAttribute And AnyBody</h1>
	<hr>
	<simple:helloBodyTag test="true">홍길동</simple:helloBodyTag>
	<simple:helloBodyTag test="false">임꺽정</simple:helloBodyTag>
	<hr>
	<%
		String name = "전우치";
		request.setAttribute("name", name);
	%>
	<simple:helloBodyTag test="true"><%=name%></simple:helloBodyTag>
	<simple:helloBodyTag test="true">${name }</simple:helloBodyTag>
	<hr>
	<%
		boolean result = true;
		request.setAttribute("result", false);
		boolean result2 = (boolean)request.getAttribute("result");
	%>
	<%--
	<simple:helloBodyTag test="&quot;<%= Boolean.parseBoolean(String.valueOf(request.getAttribute(&quot;result&quot;))) %>&quot;">장길산</simple:helloBodyTag>
	--%>
	
	<simple:helloBodyTag test="<%=result %>">장길산</simple:helloBodyTag>
	<simple:helloBodyTag test="<%=result2 %>">장길산</simple:helloBodyTag>
	<simple:helloBodyTag test="${result }">홍경래</simple:helloBodyTag>
	<%-- 커스텀 태그의 속성값에 Expression이나 EL 을 사용하고 싶은 경우 .tld 파일에서 rtexprvalue 엘리먼트 true! --%>
</body>
</html>