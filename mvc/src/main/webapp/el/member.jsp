<%@page import="xyz.itwill.el.Member"%>
<%@page import="xyz.itwill.el.Car"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- Member 객체를 생성하여 Request Scope 속성값으로 저장하고 다른 웹 프로그램(JSP)으로 포워드 이동하는 JSP 문서 --%>
<%-- ▶ 요청을 처리하는 웹 프로그램(Model) --%>
<%
	Member member=new Member("홍길동" , new Car("산타페", "하얀색"));

	//request 내장 객체에 Member 객체를 속성값으로 저장 - Request Scope
	//Request Scope: 스코프 속성값을 저장한 웹 프로그램과 스레드가 이동된 웹 프로그램에서만 속성값을 객체로 반환받아 사용 가능
	request.setAttribute("member", member);
	
	//포워드 이동: 현재 웹 프로그램의 명령을 실행하는 스레드를 다른 웹 프로그램으로 이동시켜 명령을 실행하여 응답 처리하기 위한 기능
	//▶ 스레드가 이동되는 웹 프로그램은 현재 웹 프로그램의 request 객체와 response 객체를 전달받아 사용
	//request.getRequestDispatcher("member_non_el.jsp").forward(request, response);
	//▶ [member_non_el.jsp]와 [member.jsp]는 똑같은 쓰레드 / 요청 / 응답
	request.getRequestDispatcher("member_el.jsp").forward(request, response);
%>