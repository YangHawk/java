<%@page import="xyz.itwill.dao.MyHewonDAO"%>
<%@page import="xyz.itwill.dto.MyHewon"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	MyHewon hewon = new MyHewon();
	
	hewon.setId("xxx");
	hewon.setName("씨발놈");
	
	MyHewonDAO.getDAO().updateDynamicHewon(hewon);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MYBATIS</title>
</head>
<body>
	<h1>회원 정보 변경</h1>
	<hr>
	<h3>회원 정보를 성공적으로 변경!</h3>
</body>
</html>