<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>아이디 찾기 결과</title>
</head>
<body>
    <div class="text-center">
    	<br>
        <h1 class="h3 text-gray-900 mb-2">Please check your ID</h1><br><br>
        
        <c:choose>
            <c:when test="${not empty search}">
                <p class="mb-4">조회된 아이디: <strong>${search.id}</strong></p><br>
                <p>조회된 아이디로 로그인 해주세요.</p><br><br><br><br>
            </c:when>
            <c:otherwise>
                <p class="mb-4">조회된 아이디가 없습니다.</p><br>
                <p>이름과 이메일을 확인해주세요.</p><br><br><br><br>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
