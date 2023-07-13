<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="simple" uri="http://www.itwill.xyz/mvc/custom"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC</title>
</head>
<body>
	<h1>Custom Tag - AnyAttribute AND NoBody</h1>
	<hr>
	<%-- 커스텀 태그의 속성을 생략한 경우 태그 클래스의 생성자에서 설정한 기본값을 속성값으로 사용 --%>
	<%--
	
	custom.tld 의 required 엘리먼트를 true로 하였으므로 속성 생략 시 에러 발생
	
	<simple:helloMessageTag />
	
	--%>
	
	<%-- 커스텀 태그의 속성을 사용하여 속성값을 변경한 경우 태그 클래스의 Setter 메소드를 자공으로 호출 --%>
	<%-- ▶ 커스텀 태그의 속성값을 저장하기 위한 필드의 Setter 메소드가 없는 경우 에러 발생 --%>
	<simple:helloMessageTag name="홍길동" />
	
	<simple:helloMessageTag name="좆길동" />
</body>
</html>