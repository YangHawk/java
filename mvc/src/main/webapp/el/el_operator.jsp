<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC</title>
</head>
<body>
<h1>EL Operator</h1>
<hr>
	<%-- EL 연산자: EL 표현식에서 사용하는 연산자 - 산술 / 비교 / 논리 등 --%>
	<%request.setAttribute("num1", 20);%>
	<%request.setAttribute("num2", 10);%>
	<div>num1=${num1 }</div>
	<div>num2=${num2 }</div>
	<hr>
	<div>num1 * num 2 = ${num1 * num2 }</div>
	<div>num1 / num 2 = ${num1 / num2 }</div>
	<div>num1 div num 2 = ${num1 div num2 }</div>
	<div>num1 % num 2 = ${num1 % num2 }</div>
	<div>num1 mod num 2 = ${num1 mod num2 }</div>
	<div>num1 + num 2 = ${num1 + num2 }</div>
	<div>num1 - num 2 = ${num1 - num2 }</div>
	<hr>
	<div>num &gt; num2 = ${num1 > num2 }</div>
	<div>num gt num2 = ${num1 gt num2 }</div>
	<div>num &lt; num2 = ${num1 < num2 }</div>
	<div>num lt num2 = ${num1 lt num2 }</div>
	<div>num &gt;= num2 = ${num1 >= num2 }</div>
	<div>num ge num2 = ${num1 ge num2 }</div>
	<div>num &lt;= num2 = ${num1 <= num2 }</div>
	<div>num le num2 = ${num1 le num2 }</div>
	<div>num == num2 = ${num1 == num2 }</div>
	<div>num eq num2 = ${num1 eq num2 }</div>
	<div>num != num2 = ${num1 != num2 }</div>
	<div>num ne num2 = ${num1 ne num2 }</div>
	<hr>
	<%request.setAttribute("su", 15); %>
	<div>su = ${su }</div>
	<hr>
	<div>num1 &gt; su && num2 &lt; su = ${num1 > su && num2 < su }</div>
	<div>num1 gt su and num2 lt su = ${num1 gt su and num2 lt su }</div>
	<div>num1 &lt; su || num2 &gt; su = ${num1 < su || num2 > su }</div>
	<div>num1 lt su or num2 gt su = ${num1 lt su or num2 gt su }</div>
	<hr>
	<%
		Object object = null;
		request.setAttribute("object", object);
		
		String string = "";
		request.setAttribute("string", string);
		
		String[] array = {};
		request.setAttribute("array", array);
		
		List<String> list = new ArrayList<>();
		request.setAttribute("list", list);
	%>
	<%-- empty 연산자: EL 표현식으로 제공되는 Scope 객체의 속성값이 없거나 속성값이 Java 객체 및 Map 객체인 경우 저장값이 없으면 [true] / 저장값이 있으면 [false] 반환하는 연산자 --%>
	<div>object Empty = ${empty(object) }</div>
	<div>string Empty = ${empty(string) }</div>
	<div>array Empty = ${empty(array) }</div>
	<div>list Empty = ${empty(list) }</div>
</body>
</html>