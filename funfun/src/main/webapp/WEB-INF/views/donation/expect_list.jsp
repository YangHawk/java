<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="expectListAjax">
<c:forEach items="${expectinfo}" var="expect">
	<div class="review-content">
		<h2 class="review-title no-margin">${expect.accountId}</h2>
            <div class="review-stars">
                <c:choose>
                   <c:when test="${expect.star == 5}">
                        <span class="product-rating"><i class="fa fa-star"></i><i
                           class="fa fa-star"></i><i class="fa fa-star"></i><i
                           class="fa fa-star"></i><i class="fa fa-star"></i></span>
                   </c:when>
                   <c:when test="${expect.star == 4}">
                   		<span class="product-rating"><i class="fa fa-star"></i><i
                           class="fa fa-star"></i><i class="fa fa-star"></i><i
                           class="fa fa-star"></i></span>
                   </c:when>
                   <c:when test="${expect.star == 3}">
                       <span class="product-rating"><i class="fa fa-star"></i><i
                           class="fa fa-star"></i><i class="fa fa-star"></i></span>
                   </c:when>
                   <c:when test="${expect.star == 2}">
                       <span class="product-rating"><i class="fa fa-star"></i><i
                       	class="fa fa-star"></i></span>
                   </c:when>
                   <c:when test="${expect.star == 1}">
                       <span class="product-rating"><i class="fa fa-star"></i></span>
                   </c:when>
                   <c:otherwise>
                       <p>유효하지 않은 별점입니다.</p>
                   </c:otherwise>
                 </c:choose>
                  </div>
                  <!-- / review-stars -->
                  <p>${expect.content }</p>
                  <cite>${expect.day}</cite>
               </div>
               <p>&nbsp;</p>
	</c:forEach>
</div>