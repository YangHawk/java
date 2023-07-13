<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC</title>
</head>
<body>
	<h1>Core - 흐름 제어 태그</h1>
	<hr>
	<c:set var="choice" value="1" />

	<%-- choose 태그: 조건에 대한 결과에 따라 태그 내용의 포함 여부를 선택하기 위한 상위 태그 --%>
	<%-- ▶ 하위 태그를 사용하여 태그 내용 포함 여부 선택 --%>
	<%-- ▶ 하위 태그: when 태그(1개 이상) / otherwise 태그(0개 또는 1개) --%>
	<%-- ▶ choose 태그에 하위 태그를 제외한 다른 코드가 존재할 경우 에러 발생 - JSP 주석은 예외 --%>
	<c:choose>
		<%-- when 태그: 조건에 대한 결과에 따라 태그 내용 포함 여부를 선택하는 태그 - if 태그와 비슷 --%>
		<%-- test 속성: false | true 를 속성값으로 설정 - 태그 속성값으로 EL 사용 가능 --%>
		<%-- ▶ test 속성값이 [true]인 경우 태그 내용을 포함하고 choose 태그를 종료 --%>
		<c:when test="${choice == 1 }">
			<p>수성으로 이동합니다.</p>
		</c:when>
		<c:when test="${choice == 2 }">
			<p>금성으로 이동합니다.</p>
		</c:when>
		<c:when test="${choice == 3 }">
			<p>화성으로 이동합니다.</p>
		</c:when>
		<c:when test="${choice == 4 }">
			<p>목성으로 이동합니다.</p>
		</c:when>
		<c:when test="${choice == 5 }">
			<p>토성으로 이동합니다.</p>
		</c:when>
		<%-- otherwise 태그: when 태그의 모든 조건이 거짓인 경우 태그 내용을 무조건 포함시키기 위한 태그 --%>
		<c:otherwise>
			<p>지구로 이동합니다.</p>
		</c:otherwise>
	</c:choose>
	<hr>

	<c:set var="num" value="10" />
	<c:choose>
		<c:when test="${num mod 2 ne 0 }">${num } = 홀수</c:when>
		<c:otherwise>${num } = 짝수</c:otherwise>
	</c:choose>
</body>
</html>