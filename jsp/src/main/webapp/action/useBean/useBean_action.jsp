<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- 회원정보를 전달받아 내장객체의 속성값으로 저장하고 [useBean_display.jsp] 문서로 포워드 이동하는 JSP 문서 - 클라이언트 요청에 대한 처리만 제공하는 JSP 문서 --%>
<%
//JSP 문서를 GET 방식으로 요청한 경우 - 비정상적인 요청
if (request.getMethod().equals("GET")) {
	response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
	return;
}

//POST 방식으로 요청하여 전달된 값에 대한 캐릭터셋 변경
request.setCharacterEncoding("utf-8");
%>

<%-- useBean Tag: JSP 문서에 Java 객체(JavaBean)를 제공하기 위한 태그 --%>
<%-- 형식) <jsp:useBean id="식별자" class="JavaBean 클래스" scope="사용 범위" /> --%>
<%-- ▶ 내장 객체에 저장된 속성값을 객체로 반환받아 제공하거나 속성값이 없을 경우 객체를 생성하여 내장 객체의 속성값으로 저장하여 제공 --%>
<%-- id 속성: useBean 태그로 제공되는 객체를 구분하기 위한 식별자를 속성값으로 설정 --%>
<%-- ▶ 내장 객체에 저장된 속성값에 대한 속성명으로 사용(두 가지 역할) --%>
<%-- class 속성: useBean 태그로 제공되는 객체의 자료형을 속성값으로 설정 --%>
<%-- scope 속성: useBean 태그로 제공되는 객체의 사용 범위(Scope)를 속성값으로 설정 --%>
<%-- ▶ page, request, session, application 중 하나를 속성값으로 설정 --%>
<%-- ▶ scope 속성을 생략한 경우 page를 기본값으로 설정 --%>
<jsp:useBean id="hewon" class="xyz.itwill.bean.Hewon" scope="request" />

<%--
//아래 명령은 useBean 태그와 동일한 명령
Hewon hewon = new Hewon();
request.setAttribute("hewon", hewon);
--%>

<%-- setProperty Tag: useBean 태그로 제공된 객체의 필드값을 변경하기 위한 태그 --%>
<%-- ▶ 객체의 필드에 대한 Setter 메소드를 호출하여 객체의 필드값 변경 --%>
<%-- 형식)<jsp:setProperty name"식별자" property="필드명" value="변경값" /> --%>
<%-- name 속성: useBean 태그로 제공된 객체의 식별자(id 속성값)을 속성값으로 설정 --%>
<%-- property 속성: 값을 변경할 필드명을 속성값으로 설정 --%>
<%-- ▶ 필드명을 기반으로 작성된 Setter 메소드 자동 호출 - 필드명으로 작성된 Setter 메소드가 없을 경우 Error --%>
<%-- value 속성: 필드에 저장될 변경값을 속성값으로 설정 - 생략 가능 --%>

<%-- <jsp:setProperty property="name" name="hewon" value="홍길똥" /> --%>
<%--
//아래 명령은 useBean 태그와 동일한 명령
hewon.setName("홍길똥");
--%>

<%-- value 속성을 생략하명 JSP 문서 요청 시 전달된 값을 반환받아 필드값 변경 --%>
<%-- ▶ JSP 문서 요청 시 전달된 값의 이름과 setProperty 태그의 property 속성값(객체 필드명)이 반드시 동일해야만 전달값을 반환받아 객체 필드값으로 변경  --%>

<%--
<jsp:setProperty property="name" name="hewon" />
<jsp:setProperty property="phone" name="hewon" />
<jsp:setProperty property="address" name="hewon" />
--%>
<%-- setProperty 태그의 property속성값을 [*]로 설정한 경우 모든 전달값을 반환받아 필드값 변경 --%>
<jsp:setProperty name="hewon" property="*" />
<%--
//아래 명령은 useBean 태그와 동일한 명령
hewon.setName(request.getParameter("name"));
hewon.setName(request.getParameter("phone"));
hewon.setName(request.getParameter("address"));
--%>

<jsp:forward page="useBean_display.jsp" />
<%--
//아래 명령은 useBean 태그와 동일한 명령
request.getRequestDispatcher("useBean_display.jsp").forward(request, response);
 --%>