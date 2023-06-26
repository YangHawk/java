﻿<%@page import="xyz.itwill.dao.StudentDAO"%>
<%@page import="xyz.itwill.dto.StudentDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- 학생 번호를 전달받아 STUDENT 테이블에 저장된 학생 정보를 검색하여 입력 태그의 초기값으로 출력하고 변경값을 입력받기 위한 JSP 문서 --%>
<%-- ▶ [학생변경] 태그를 클릭한 경우 [updateStudent.jsp] 문서를 요청하여 이동 - 입력값(학생 정보) 전달 --%>
<%-- ▶ [학생목록] 태그를 클릭한 경우 [displayStudent.jsp] 문서를 요청하여 이동 --%>
<%
if (request.getParameter("no") == null) { //전달값이 없는 경우 - 비정상적인 요청
	response.sendError(HttpServletResponse.SC_BAD_REQUEST);
	return;
}

//전달값을 반환받아 변수에 저장
int no = Integer.parseInt(request.getParameter("no"));

//학생 번호를 전달받아 STUDENT 테이블에 저장된 학생 정보를 검색하여 DTO 객체로 반환하는 DAO 클래스의 메소드 호출
StudentDTO student = StudentDAO.getDAO().selectStudent(no);

if (student == null) { //STUDENT 테이블에 저장된 학생 정보를 검색하지 못한 경우 - 비정상적인 요청
	response.sendError(HttpServletResponse.SC_BAD_REQUEST);
	return;
}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP</title>
</head>
<body>
	<h1 align="center">학생정보 변경</h1>
	<hr>
	<form name="studentForm">
		<%-- [updateStudent.jsp] 문서를 요청할 때 학생 번호도 전달되도록 설정 --%>
		<%-- ▶ 학생번호는 변경하지 못하도록 hidden Type으로 전달하거나 입력태그의 초기값을 변경하지 못하도록 readonly 속성 사용 --%>
		<%--
		<input type="hidden" name="no" value="<%=student.getNo()%>">
		--%>
		<table align="center" border="1" cellpadding="1" cellspacing="0" width="300">
			<tr height="40">
				<th bgcolor="yellow" width="100">학생번호</th>
				<%--
				<td width="200" align="center"><%=student.getNo()%></td>
				--%>
				<td width="200" align="center"><input type="text" name="no" value="<%=student.getNo()%>" readonly="readonly"></td>
			</tr>
			<tr height="40">
				<th bgcolor="yellow" width="100">이름</th>
				<td width="200" align="center"><input type="text" name="name" value="<%=student.getName()%>"></td>
			</tr>
			<tr height="40">
				<th bgcolor="yellow" width="100">이메일</th>
				<td width="200" align="center"><input type="text" name="email" value="<%=student.getEmail()%>"></td>
			</tr>
			<tr height="40">
				<th bgcolor="yellow" width="100">전화번호</th>
				<td width="200" align="center"><input type="text" name="phone" value="<%=student.getPhone()%>"></td>
			</tr>
			<tr height="40">
				<th bgcolor="yellow" width="100">주소</th>
				<td width="200" align="center"><input type="text" name="address" value="<%=student.getAddress()%>"></td>
			</tr>
			<tr height="40">
				<td width="200" colspan="2" align="center"><input type="button" value="학생변경" onclick="submitCheck();"> <input type="reset" value="초기화"> <input type="button" value="학생목록" onclick="location.href='displayStudent.jsp';"></td>
			</tr>
		</table>
	</form>
	<script type="text/javascript">
		studentForm.name.focus();

		function submitCheck() {
			if (studentForm.name.value == "") {
				alert("이름을 입력해 주세요.");
				studentForm.name.focus();
				return;
			}

			if (studentForm.email.value == "") {
				alert("이메일을 입력해 주세요.");
				studentForm.email.focus();
				return;
			}

			var emailReg = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
			if (!emailReg.test(studentForm.email.value)) {
				alert("이메일을 형식에 맞게 입력해주세요.");
				studentForm.email.focus();
				return;
			}

			if (studentForm.phone.value == "") {
				alert("전화번호을 입력해 주세요.");
				studentForm.phone.focus();
				return;
			}

			var phoneReg = /(01[016789])-\d{3,4}-\d{4}/g;
			if (!phoneReg.test(studentForm.phone.value)) {
				alert("전화번호를 형식에 맞게 입력해주세요.");
				studentForm.phone.focus();
				return;
			}

			if (studentForm.address.value == "") {
				alert("주소을 입력해 주세요.");
				studentForm.address.focus();
				return;
			}

			studentForm.method = "post";
			studentForm.action = "updateStudent.jsp";
			studentForm.submit();
		}
	</script>
</body>
</html>