<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SPRING</title>
</head>
<body>
	<h1>사원 정보 확인</h1>
	<hr>
	<ul>
		<li>아이디: ${employee.id }</li>
		<li>비밀번호: ${employee.passwd }</li>
		<li>이름: ${employee.name }</li>
		<li>이메일: ${employee.email }</li>
		<li>성별: ${employee.gender }</li>
	</ul>
</body>
</html>