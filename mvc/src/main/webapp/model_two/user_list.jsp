﻿<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- request 객체의 속성값으로 저장된 회원 정보를 반환받아 클라이언트에게 전달하여 응답하는 JSP 문서 --%>
<%-- => 회원정보에서 [회원이름] 태그를 클릭한 경우 [view.do] 페이지 요청 - 아이디 전달 --%>
<%-- => [회원등록] 태그를 클릭한 경우 [writeform.do] 페이지 요청 - 관리자에게만 링크 제공 --%>
<%-- => [로그아웃] 태그를 클릭한 경우 [logout.do] 페이지 요청 --%>

<%-- ※태그 라이브러리 사용※

<%
	UserinfoDTO loginUserinfo = (UserinfoDTO) session.getAttribute("loginUserinfo");

	List<UserinfoDTO> userinfoList = (List<UserinfoDTO>)request.getAttribute("userinfoList");
%>

--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>MVC</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel=stylesheet href="<c:url value="/model_two/css/user.css"/>" type="text/css">
</head>
<body bgcolor=#FFFFFF text=#000000 leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>
<br>
<table width=780 border=0 cellpadding=0 cellspacing=0>
<tr>
	<td width="20"></td>
	<td>
	  	<table width=590 border=0 cellpadding=0 cellspacing=0>
		  	<tr>
				<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>회원관리 - 회원목록</b></td>
		  	</tr>
	  	</table>  
	  	<br>
	  
	  	<table border="0" cellpadding="0" cellspacing="1" width="590" bgcolor="BBBBBB">
		  	<tr>
				<td width=190 align=center bgcolor="E6ECDE" height="22">아이디</td>
				<td width=200 align=center bgcolor="E6ECDE">이름</td>
				<td width=200 align=center bgcolor="E6ECDE">이메일</td>
		  	</tr>

			<c:forEach var="userinfo" items="${userinfoList }">
			  	<tr>
					<td width=190 align=center bgcolor="ffffff" height="20">
						${userinfo.userid }
					</td>
					<td width=200 align=center bgcolor="ffffff">
						<a href="<c:url value="/view.do"/>?userid=${userinfo.userid}" class="user">
							${userinfo.name }
						</a>
					</td>
					<td width=200 align=center bgcolor="ffffff">
						<!-- JSP 표현식(Expression)은 NULL을 [null] 문자열로 변환하여 출력 처리 -->
						<%-- => JSP 표현식(Expression)의 값이 NULL인 경우 출력되지 않도록 if 구문 사용 --%>
						<%-- 태그 라이브러리를 사용하기 때문에 이 건 필요 없다!
						<% if(userinfo.getEmail()!=null) { %>
							<%=userinfo.getEmail() %>
						<% } %>
						--%>
						${userinfo.email }
					</td>
		  		</tr>
			</c:forEach>

	  	</table>

		<br>
	  	<table border="0" cellpadding="0" cellspacing="1" width="590">
			<tr>
				<td align="right">
					<c:if test="${loginUserinfo.status == 9 }">
						<input type="button" value="회원등록" onclick="location.href='<c:url value="/writeform.do"/>';"/>
					</c:if>	
					<input type="button" value="로그아웃" onclick="location.href='<c:url value="/logout.do"/>';"/>
				</td>
			</tr>
		</table>		
	</td>
</tr>
</table>  
</body>
</html>