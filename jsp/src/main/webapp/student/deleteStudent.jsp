<%@page import="xyz.itwill.dao.StudentDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- 학생 번호를 전달받아 STUDENT 테이블에 저장된 학생 정보를 삭제하고 [displayStudent.jsp] 문서를 요청할 수 있는 URL 주소를 클라이언트에게 전달하여 응답하는 --%>
<%
//JSP 문서를 [GET] 방식으로 요청한 경우 - 비정상적인 요청
if (request.getMethod().equals("GET")) {
	//response.sendError(HttpServletResponse.SC_BAD_REQUEST);
	session.setAttribute("message", "비 정 상 적 인 방 법 페 이 지 요 청");
	response.sendRedirect("updateFormStudent.jsp"); //클라이언트에게 URL 주소 전달
	return;
}

//전달값을 반환받아 변수에 저장
int no = Integer.parseInt(request.getParameter("no"));

//학생 번호를 전달받아 STUDENT 테이블에 저장된 학생 정보를 삭제하는 DAO 클래스의 메소드 호출
int rows = StudentDAO.getDAO().deleteStudent(no);

if (rows > 0) { //삭제행이 있는 경우 - 정상적인 요청
	response.sendRedirect("displayStudent.jsp");
} else { //삭제행이 없는 경우 - 비정상적인 요청
	response.sendError(HttpServletResponse.SC_BAD_REQUEST);
}
%>