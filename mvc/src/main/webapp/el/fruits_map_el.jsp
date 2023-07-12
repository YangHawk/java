<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC</title>
</head>
<body>
	<h1>EL - Map</h1>
	<hr>
	<p>과일 - 1 = ${fruitsMap.one}</p>
	<p>과일 - 2 = ${fruitsMap.two}</p>
	<p>과일 - 3 = ${fruitsMap.three}</p>
	<hr>
	<p>제일 좋아하는 과일 = ${fruitsMap.two}</p><%-- 제일 좋아하는 과일 = 참외 --%>
	<p>제일 좋아하는 과일 = ${fruitsMap["two"]}</p><%-- 제일 좋아하는 과일 = 참외 --%>
	<p>과일 번호 = ${choice}</p><%-- 과일 번호 = two --%>
	<p>제일 좋아하는 과일 = ${fruitsMap.choice}</p><%-- 제일 좋아하는 과일 = --%>
	<%-- 스코프 객체의 속성값이 Map 객체인 경우 맵 키를 사용하여 맵값을 제공받아 출력 처리 --%>
	<%-- ▶ 맵 키로 제공되는 맵 값이 없는 경우 EL 실행 X --%>
	<p>제일 좋아하는 과일 = ${fruitsMap[choice]}</p><%-- 제일 좋아하는 과일 = 참외 --%>
	<%-- 스코프 객체의 속성값이 Map 객체인 경우 EL 표현식에서 [] 연산자를 사용하여 다른 스코프 객체의 속성값을 제공받아 맵 키로 사용 가능 --%>
</body>
</html>