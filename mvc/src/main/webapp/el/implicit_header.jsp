<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC</title>
</head>
<body>
	<h1>EL 내장 객체 - Request Header</h1>
	<hr>
	<h2>EL 사용 X</h2>
	<ul>
		<%-- request.getHeader(String name): 리퀘스트 메세지의 머릿부에 저장된 값들 중 매개 변수로 전달받은 이름의 값을 반환하는 메소드 --%>
		<li>현재 접속 중인 서버 = <%=request.getHeader("host")%></li>
		<%-- 현재 접속 중인 서버 = localhost:8000 --%>
		<li>클라이언트 브라우저의 종류 = <%=request.getHeader("user-Agent")%></li>
		<%-- 클라이언트 브라우저의 종류 = Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36 --%>
		<li>전달 가능한 문서의 종류 = <%=request.getHeaders("accept")%></li><%-- 이건 배열 객체이므로 [Ljava.lang.String;@6ad32030 의 String 객체가 반환됨 --%>
		<li>전달 가능한 문서의 종류 = <%=request.getHeaders("accept").nextElement()%></li>
		<%-- 전달 가능한 문서의 종류 = org.apache.tomcat.util.http.ValuesEnumerator@380b043 --%>
		<%-- 전달 가능한 문서의 종류 = text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7 --%>
	</ul>
	<hr>
	<h2>EL 사용</h2>
	<ul>
		<%-- EL 표현식에서 header 내장 객체 또는 headers 내장객체를 사용하여 리퀘스트 메세지의 머릿부에 저장된 값을 제공받아 출력 처리 가능 --%>
		<%-- ▶ header 내장 객체 또는 headers 내장 객체는 Map 객체로 맵 키 대신 헤더값을 구분하는 이름을 사용하여 헤더값 제공 가능 --%>
		<li>현재 접속 중인 서버 = ${header.host }</li>
		<li>클라이언트 브라우저의 종류 = ${header.user-Agent }</li><%-- [-] 는 연산자 취급됨 --%>
		<li>클라이언트 브라우저의 종류 = ${header["user-Agent"] }</li>
		<li>전달 가능한 문서의 종류 = ${headerValues.accept[0]}</li><%-- 배열의 첫번째 --%>
	</ul>
</body>
</html>