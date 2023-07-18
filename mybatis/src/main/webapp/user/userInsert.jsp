<%@page import="xyz.itwill.dto.MyUser"%>
<%@page import="xyz.itwill.dao.MyUserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
	MyUserDAO.getDAO().insertMyUser(new MyUser("abc","홍길동"));
	MyUserDAO.getDAO().insertMyUser(new MyUser("xyz","임꺽정"));
	MyUserDAO.getDAO().insertMyUser(new MyUser("opq","전우치"));
%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MYBATIS</title>
</head>
<body>
	<h1>회원 등록</h1>
	<hr>
	<h3>회원 정보가 성공적으로 삽입되었습니다.</h3>
</body>
</html>