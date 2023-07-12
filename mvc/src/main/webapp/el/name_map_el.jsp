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
	<p>이름 = ${nameMap.first.name } ${nameMap.second.name }</p>
	<%-- 이름이 출력이 안되는 이유: .은 첫번째 표현식과 두번째 표현식을 연결하는 것이기 때문! --%>
	<%-- 스코프 객체의 속성값이 Map 객체인 경우 맵 키를 사용하여 맵 값을 제공받아 출력 처리 --%>
	<%-- ▶ EL 표현식으로 사용하기 부적절한 문자가 포함된 맵 키인 경우 . 연산자로 제공받아 출력 처리 불가 --%>
	<%-- ▶ . 연산자 대신 [] 연산자를 사용하여 맵 값을 제공받아 출력 처리 가능 --%>
	<p>이름 = ${nameMap["first.name"] } ${nameMap["second.name"] }</p>
</body>
</html>