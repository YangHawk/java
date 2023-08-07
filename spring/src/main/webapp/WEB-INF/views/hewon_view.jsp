<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>SPRING</title>
</head>
<body>
	<h1>회원 정보</h1>
	<hr>
	<ul>
		<li>아이디 = ${hewon.id }</li>
		<li>이름 = ${hewon.name }</li>
		<li>이메일 = ${hewon.email}</li>
	</ul>
	<%-- 회원 정보 변경 페이지를 요청할 수 있는 링크 제공 - 아이디 전달 --%>
	<%-- <button type="button" onclick="location.href='hewon_update?id=${hewon.id}'">회원 정보 변경</button> --%>
	<%-- @SessionAttributes 어노테이션을 사용하면 아이디를 전달하지 않아도 회원 정보 변경 페이지에서 회원 정보가 저장된 속성값을 제공받아 사용 가능므로 아이디 전달 X!  --%>
	<button type="button" onclick="location.href='hewon_update';">회원 정보 변경</button>
</body>
</html>