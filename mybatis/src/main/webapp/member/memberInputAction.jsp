<%@page import="xyz.itwill.dao.MyMemberXMLDAO"%>
<%@page import="xyz.itwill.dto.MyMember"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	
	String id = request.getParameter("id");
	String name = request.getParameter("name");
	String phone = request.getParameter("phone");
	String email = request.getParameter("email");

	MyMember member = new MyMember();

	member.setId(id);
	member.setName(name);
	member.setPhone(phone);
	member.setEmail(email);

	try {
  	//SqlSession 객체를 이용하여 매퍼에 등록된 SQL 명령을 DBMS 서버에 전달하여 실행할 시 문제가 발생하면 PersistanceException 발생
  		MyMemberXMLDAO.getDAO().insertMyMember(member);
  
	  	response.sendRedirect("memberDisplay.jsp");
	} catch (Exception e) {
  		//SQL 명령(INSERT)이 정상인 경우 PK 제약조건을 위반할 시 예외 발생 
  		//response.sendRedirect("memberInsertForm.jsp");
  		
  		out.println("<script type = 'text/javascript'>");
  		out.println("alert('이미 사용중인 아이디를 입력하여 회원 등록이 실!패!하였습니다.');");
  		out.println("history.go(-1)");
  		out.println("</script>");
	}
%>