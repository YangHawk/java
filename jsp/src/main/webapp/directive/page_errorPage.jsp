<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="page_error.jsp"%> --%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- JSP 문서에서 page Directive의 errorPage 속성을 이용하여 JSP 문서 실행 시 예외가 발생되면 에러 페이지로 응답 처리되도록 설정 가능하나 JSP 문서마다 속성을 설정해야하는 문제점 발생 --%>
<%-- → WAS 프로그램에 의하여 에러 코드(4XX~5XX)가 전달되어 응답될 때 에러 페이지로 응답되어지도록 설정 --%>
<%-- → [web.xml] 파일에 에러 코드 대신 응답될 에러 페이지의 URL 주소를 설정 --%>
<%-- → 프로젝트에서 작성된 모든 웹 프로그램 실행 시 발생되는 에러 코드 대신 에러 페이지로 응답 --%>
<%-- 우선 순위 = errorPage 속성값 > web.xml --%>
<%
//String text = "ABCDEFG"; //문자열의 문자 갯수 = 7
String text = null; // HTTP 상태 500 – 내부 서버 오류: java.lang.NullPointerException

int su = 10 / 0; //  HTTP 상태 500 – 내부 서버 오류: java.lang.ArithmeticException - 또 다른 예외!

if (request.getMethod().equals("GET")) {
	response.sendError(HttpServletResponse.SC_BAD_REQUEST); // 400 에러 코드 전달
	return;
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
</head>
<body>
	<h1>page Directive - errorPage 속성</h1>
	<hr>
	<p>page Directive의 errorPage 속성에는 JSP 문서 실행 시 에러(예외)가 발생될 경우 클라이언트 에게 500 상태 코드 대신 에러 페이지로 응답 처리 될 웹문서의 URL 주소를 속성값으로 설정 - 클라이언트의 URL 주소는 변경되지 않음</p>
	<hr>
	<p>
		문자열의 문자 갯수 =
		<%=text.length()%></p>
</body>
</html>