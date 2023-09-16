<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
   <!-- header -->
    <div class="top-menu top-menu-inverse">
        <div class="container">
            <p>
                <span class="right">
                <c:choose>
                    <c:when test="${sessionScope.loginAccount.status == 1}">
                        <a href="${pageContext.request.contextPath}/donation/festival_register"><span class="lnr lnr-pencil"></span> <span>영화제 등록하기</span></a>
                        <a href="${pageContext.request.contextPath}/account/my_festival"><span>나의 영화제</span></a>
                    </c:when>
                    <c:otherwise>
                        <a><span></span></a>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${sessionScope.loginAccount.status == 0}">
                        <a href="${pageContext.request.contextPath}/admin/admin"><i class="lnr lnr-user"></i> <span>관리자페이지</span></a>
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/account/myaccount"><i class="lnr lnr-user"></i> <span>마이페이지</span></a>
                    </c:otherwise>
                </c:choose>
                <%-- 세션이 있는 경우 --%>
				<%--                
                <c:choose>
                   <c:when test="${sessionScope.loginAccount.id != null}">
                       <a href="${pageContext.request.contextPath}/account/logout"><span class="lnr lnr-exit"></span> <span>로그아웃</span></a>
                    </c:when>
                    <c:otherwise>
                       <a href="${pageContext.request.contextPath}/account/login"><i class="lnr lnr-lock"></i> <span>로그인 / 회원가입</span></a>
                   </c:otherwise>
                </c:choose>
				--%>                   
                <sec:authorize access="isAnonymous()">
                       <a href="${pageContext.request.contextPath}/account/login"><i class="lnr lnr-lock"></i> <span>로그인 / 회원가입</span></a>
				</sec:authorize>
                <sec:authorize access="isAuthenticated()">
                     <form id="logoutForm" action="<c:url value="/account/logout"/>" method="post">
				            <sec:csrfInput/>
				            <button type="submit" class="logout-button"><i class="lnr lnr-exit"></i> <span>로그아웃</span></button>
			        </form>
				</sec:authorize>
                    <a href="${pageContext.request.contextPath}/donation/wishlist"><i class="lnr lnr-cart"></i> <span>위시리스트</span></a>
                </span>
            </p>
        </div><!-- / container -->
    </div><!-- / top-menu-inverse -->
    <nav class="navbar navbar-default">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="${pageContext.request.contextPath}/"><img src="${pageContext.request.contextPath}/resources/images/mylogo.png" alt=""></a>
            </div><!-- / navbar-header -->
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="${pageContext.request.contextPath}/">Home</a></li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">영화제 후원<span class="caret"></span></a>
                        <ul class="dropdown-menu pulse animated">
						    <li><a href="${pageContext.request.contextPath}/donation/shopfull?select=all">전체 영화제</a></li>
						    <li><a href="${pageContext.request.contextPath}/donation/shopfull?select=ongoing">진행 중 영화제</a></li>
						    <li><a href="${pageContext.request.contextPath}/donation/shopfull?select=upcoming">진행 예정 영화제</a></li>
						    <li><a href="${pageContext.request.contextPath}/donation/shopfull?select=sponsor">후원자 순</a></li>
						    <li><a href="${pageContext.request.contextPath}/donation/shopfull?select=collected">금액 순 영화제</a></li>
						</ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">커뮤니티<span class="caret"></span></a>
                        <ul class="dropdown-menu pulse animated">
                            <li><a href="${pageContext.request.contextPath}/community/notice">공지사항</a></li>
                            <li><a href="${pageContext.request.contextPath}/community/qna_list">Q&A 게시판</a></li>
                            <li><a href="${pageContext.request.contextPath}/community/faq">FAQ</a></li>
                            <!-- 
                            <li role="separator" class="divider"></li>
                            <li><a href="404">404 Page</a></li>
                            <li><a href="components">Components</a></li>
                             -->
                        </ul>
                    </li>
                    <li><a href="${pageContext.request.contextPath}/about">고객센터</a></li>
                </ul>
            </div><!--/ nav-collapse -->
        </div><!-- / container -->
    </nav><!-- / navbar -->

<!-- / header -->