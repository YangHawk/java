<%@page import="xyz.itwill.dao.MyHewonDAO"%>
<%@page import="xyz.itwill.dto.MyHewon"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
List<MyHewon> hewonList = MyHewonDAO.getDAO().selectHewonList();
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
	padding: 3px;
}

.id {
	width: 100px;
}

.name {
	width: 150px;
}

.phone {
	width: 200px;
}

.email {
	width: 300px;
}

.status {
	widt: 200;
}
</style>
</head>
<body>
	<h1>게시글 목록</h1>
	<hr>
	<table>
		<tr>
			<td class="id">아이디</td>
			<td class="name">이름</td>
			<td class="phone">전화번호</td>
			<td class="email">이메일</td>
			<td class="status">공개 범위</td>
		</tr>
		<% if(hewonList.isEmpty()) { %>
				<tr>
					<td>검색된 회원 정보가 없습니다.</td>
				</tr>
			<% } else { %>
			<% for (MyHewon hewon : hewonList) { %>
			<tr>
				<td><%=hewon.getId()%></td>
				<td><%=hewon.getName()%></td>
				<td><%=hewon.getPhone()%></td>
				<td><%=hewon.getEmail()%></td>
				<td><%=hewon.getStatus()%></td>
			</tr>
			<% } %>
		<% } %>
	</table>
</body>
</html>