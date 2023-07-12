<%@page import="xyz.itwill.el.Car"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	List<Car> carList=new ArrayList<>();

	carList.add(new Car("싼타페", "하얀색"));
	carList.add(new Car("렉서스", "검정색"));
	carList.add(new Car("스포티지", "빨간색"));
	carList.add(new Car("쏘나타", "파란색"));
	carList.add(new Car("쏘렌토", "노란색"));
	
	request.setAttribute("carList", carList);
	
	request.getRequestDispatcher("car_list_el.jsp").forward(request, response);
%>