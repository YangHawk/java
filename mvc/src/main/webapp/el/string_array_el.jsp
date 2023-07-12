<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC</title>
</head>
<body>
	<h1>EL - Array</h1>
	<hr>
	<%--
	<p>nameArray = ${nameArray}</p>
	--%>
	<%-- nameArray = [Ljava.lang.String;@482ab5df --%>
	<ul>
		<%--
		<li>${nameArray.0 }</li>
		--%>
		<%-- 스코프 객체의 속성값이 배열인 경우 배열 요소값을 제공받아 출력하기 위해 첨자 사용 --%>
		<%-- ▶ EL 표현식에서 . 연산자로 첨자를 사용하여 표현 불가 --%>
		<%-- ▶ 500 에러 발생: ELException(안에 ParseException) 발생 --%>
		<%-- ▶ EL 표현식에서는 . 연산자 대신 [] 연산자를 사용--%>
		<li>${nameArray["0"] }</li>
		<%-- 첨자는 "" 기호 생략 가능 --%>
		<li>${nameArray[1] }</li>
		<li>${nameArray[2] }</li>
		<li>${nameArray[3] }</li>
		<li>${nameArray[4] }</li>
		<%-- EL 표현식에서 배열의 첨자가 범위를 벗어난 경우 EL 실행 X - 값 출력 X --%>
		<li>${nameArray[5] }</li>
	</ul>
</body>
</html>