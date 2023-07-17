<%@page import="xyz.itwill.dto.Student"%>
<%@page import="xyz.itwill.dao.StudentDAO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	List<Student> studentList = StudentDAO.getDAO().selectStudentList();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MYBATIS</title>
<style type="text/css">
table {
	border: 1px solid black;
	border-collapse: collapse;
}

td {
	border: 1px solid black;
	text-align: center;
	padding: 10px;
}
</style>
</head>
<body>
	<h1>학생 목록</h1>
	<hr>
	<table>
		<tr>
			<td>학번</td>
			<td>이름</td>
			<td>이메일</td>
			<td>전화번호</td>
			<td>주소</td>
		</tr>
		<%for(Student student: studentList) { %>
		<tr>
			<td><%=student.getNo() %></td>
			<td><%=student.getName() %></td>
			<td><%=student.getEmail() %></td>
			<td><%=student.getPhone() %></td>
			<td><%=student.getAddress() %></td>
		</tr>
		<% } %>
	</table>
</body>
</html>