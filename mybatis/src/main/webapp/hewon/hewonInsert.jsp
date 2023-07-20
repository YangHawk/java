<%@page import="xyz.itwill.dto.MyHewon"%>
<%@page import="xyz.itwill.dao.MyHewonDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	MyHewonDAO.getDAO().insertHewon(new MyHewon("aaa", "홍길동", "010-1234-5678", "aaa@naver.com", 1));
	MyHewonDAO.getDAO().insertHewon(new MyHewon("bbb", "임꺽정", "010-1212-3434", "bbb@naver.com", 2));
	MyHewonDAO.getDAO().insertHewon(new MyHewon("ccc", "전우치", "010-7878-5656", "ccc@naver.com", 3));
	MyHewonDAO.getDAO().insertHewon(new MyHewon("ddd", "일지매", "010-9090-0909", "ddd@naver.com", 4));
	MyHewonDAO.getDAO().insertHewon(new MyHewon("eee", "장실찬", "010-9999-9999", "eee@naver.com", 1));
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
	<h3>제대로 등록되었습니다.</h3>
</body>
</html>