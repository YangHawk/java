<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!-- preloader -->
<div id="preloader">
    <div class="spinner spinner-round"></div>
</div>
<!-- / preloader -->
<!-- content -->

<section id="pay_completion">
    <div class="container">
        <div class="text-wrap text-center">
            <div class="page-header text-center space-top-30">
                <h1><i class="lnr lnr-warning"></i>결제가 완료</h1>
            </div><!-- / page-header -->
            <p class="large-p">${message }</p>
            <p class="space-top-2x"><a href="${pageContext.request.contextPath}/" class="btn btn-default-filled btn-rounded"><i class="fa fa-long-arrow-left"></i><span>Back to Homepage</span></a></p>
        </div><!-- / text-wrap -->
    </div><!-- / container -->
</section>

<!-- / content -->

<!-- javascript -->
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.easing.min.js"></script>

<!-- scrolling-nav -->
<script src="${pageContext.request.contextPath}/resources/js/scrolling-nav.js"></script>
<!-- / scrolling-nav -->

<!-- preloader -->
<script src="${pageContext.request.contextPath}/resources/js/preloader.js"></script>
<!-- / preloader -->
