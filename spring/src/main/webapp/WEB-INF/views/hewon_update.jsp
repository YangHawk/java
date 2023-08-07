<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>SPRING</title>
</head>
<body>
	<h1>회원 정보 변경</h1>
	<hr>
	<form action="hewon_update" method="post">
		<%-- 회원 정보를 변경하기 위하여 hidden 타입의 입력 태그로 아이디 전달 --%>
		<%-- ▶ 아이디를 입력 태그의 초기값으로 설정하여 read-only 속성을 사용하여 전달 가능 --%>
		<%-- <input type="hidden" name="id" value="${hewon.id }"> --%>
		<%-- 아이디 이제 전달할 필요 없어! 히든타입이나 리드온리 안써도 되는거야! --%>
		<table>
			<tr>
				<td>아이디</td>
				<%-- <td><input type="text" name="id" value="${hewon.id }" readonly="readonly"></td> --%>
				<td>${hewon.id}</td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" value="${hewon.name }"></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="text" name="email" value="${hewon.email }"></td>
			</tr>
			<tr>
				<td colspan="2"><button type="submit">회원 변경</button></td>
			</tr>
		</table>
	</form>
	<p style="color: red;">${message }</p>
</body>
</html>