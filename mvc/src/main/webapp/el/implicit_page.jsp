<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>EL 내장 객체 - PageContext</h1>
	<hr>
	<h2>EL 사용 X</h2>
	<%-- request 내장 객체 - HttpServletRequest 객체: 요청 정보를 저장한 객체 --%>
	<p>컨텍스트 경로 = <%=request.getContextPath()%></p>
	<p>요청 URI 주소 = <%=request.getRequestURI()%></p>
	<hr>
	<%-- pageContext 내장 객체 - PageContext 객체: 웹 프로그램 작성에 필요한 모든 정보를 저장한 객체 --%>
	<%-- pageContext.getRequest(): 요청 정보가 저장된 ServletRequest 객체를 반환하는 메소드 --%>
	<%-- ▶ ServletRequest 객체를 HttpServlet 객체로 형변환 가능 --%>
	<p>컨텍스트 경로 = <%=((HttpServletRequest)pageContext.getRequest()).getContextPath() %></p>
	<p>요청 URI 주소 = <%=((HttpServletRequest)pageContext.getRequest()).getRequestURI() %></p>
	<%-- ServletRequest = 부모 | HttpServletRequset = 자식 --%>
	<hr>
	<h2>El 사용</h2>
	<%-- EL 표현식에서 pageContext 내장 객체를 사용하여 JSP의 pageContext 내장 객체를 제공받아 사용 가능 --%>
	<%-- ▶ EL 표현식에서 pageContext 내장 객체로 JSP의 pageContext 내장 객체의 메소드 호출 가능 --%>
	<%--
	<p>컨텍스트 경로 = ${pageContext.getRequest().getContextPath() }</p>
	--%>
	<%-- EL 표현식에서 Getter 메소드는 필드명을 이용하여 자동 호출 가능 --%>
	<p>컨텍스트 경로 = ${pageContext.request.contextPath }</p>
	<p>요청 URI 주소 = ${pageContext.request.requestURI }</p>
</body>
</html>