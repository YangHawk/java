<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SPRING</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<c:url value="/login" var="loginUrl" />
<body>
	<h1>로 그 인 페 이 지</h1>
	<hr>
	<form action="${loginUrl }" method="post" id="loginForm">
		<table>
			<tr>
				<td>아이디</td>
				<td><input type="text" name="userid" id="userid"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="passwd" id="passwd"></td>
			</tr>
			<tr>
				<td colspan="2"><button type="submit">로그인</button></td>
			</tr>
		</table>
		<%-- CSRF 공격을 방어하기 위하여 CSRF Token을 Hidden Type으로 북따딱--%>
		<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }">
	</form>
</body>
</html>