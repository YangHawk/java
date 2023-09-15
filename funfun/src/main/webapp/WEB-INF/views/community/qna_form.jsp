<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<style> 
.categories-sidebar-widget {
     max-width: 150px;
    margin-top: 150px;
    margin-left: 50px;
}
</style>
</head>
<body>
<!-- preloader -->
<div id="preloader">
    <div class="spinner spinner-round"></div>
</div>
<!-- / preloader -->

   <div id="page-header" class="question">
        <div class="container">
            <div class="page-header-content text-center">
                <div class="page-header wsub">
                    <h1 class="page-title fadeInDown animated first">문의글 등록</h1>
                </div><!-- / page-header -->
            </div><!-- / page-header-content -->
        </div><!-- / container -->
    </div><!-- / page-header -->


<!-- 사이드바 -->
   <div class="col-sm-4 col-md-3 sidebar-area">
      <div class="categories-sidebar-widget widget no-border">
         <h5 class="widget-title">커뮤니티</h5>
         <p class="product-category">
            <a href="${pageContext.request.contextPath}/community/notice">공지사항</a> <span class="pull-right"></span>
         </p>
         <p class="product-category">
            <a href="${pageContext.request.contextPath}/community/faq">FAQ</a> <span class="pull-right"></span>
         </p>
         <p class="product-category">
            <a href="${pageContext.request.contextPath}/community/qna_list">Q&amp;A</a> <span class="pull-right"></span>
         </p>
      </div>
   </div>
<!-- 사이드바 -->

<section id="contact">
    <div class="container">
        <div class="row">
             <div class="col-sm-6 form-container">
                <!-- contact form -->
                <div id="contact-form-1">
                    <form id="contactForm" data-toggle="validator" action="${pageContext.request.contextPath}/community/qna_form" method="post" enctype="multipart/form-data">
                        <div class="form-group">
                         <label for="title">제목</label>
                            <input name="title" type="text" class="form-control" id="title" placeholder="제목을 입력해주세요" required>
                            <div class="help-block with-errors"></div>
                        </div>
                        
                        <div class="form-group">
	                        <label for="message">내용</label>
	                        <textarea name="content" id="message" class="form-control" rows="15" placeholder="내용을 입력해주세요" required></textarea>
	                        <div class="help-block with-errors"></div>
                        </div>
                        <div class="form-group">
						    <label>파일 첨부</label>
						    <input type="file" id="fileData" name="uploadFile">
						    <div class="help-block with-errors"></div>
						</div>
                        <div class="form-check form-check-inline mt-3">
						    <input class="form-check-input" type="checkbox" name="secretBox" id="secretBox">
						    <label class="form-check-label">비밀글</label>
						</div>
						<br>
	                    <button type="submit" id="qnaBtn" class="btn btn-md btn-primary-filled btn-form-submit btn-rounded">등록하기</button>
                        <div id="msgSubmit" class="h3 text-center hidden"></div>
                        <div class="clearfix"></div>
                    </form>
                </div><!-- / contact form -->
            </div><!-- / form-container -->
        </div><!-- / row -->
    </div><!-- / container -->
</section>

<script>
    
$(document).ready(function(){
	$("#qnaBtn").click(function() {
	    // 폼의 action URL 생성 (JSP에서 생성하고 데이터를 전달)
	    var actionUrl = '<c:url value="/community/qna_form"/>';
	    if ($("#secretBox").is(":checked")) {
	        actionUrl = '<c:url value="/community/qna_form_secret"/>';
	    }
	    // 폼의 action 속성 설정
	    $("#contactForm").attr("action", actionUrl);

	    // 폼 제출
	    $("#contactForm").submit();
	});
});
</script>
<!-- javascript -->
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.easing.min.js"></script>

<!-- contact-form -->
<script src="js/validator.min.js" type="${pageContext.request.contextPath}/text/javascript"></script>
<script src="js/form-scripts.js" type="${pageContext.request.contextPath}/text/javascript"></script>
<!-- / contact-form -->

<!-- preloader -->
<script src="${pageContext.request.contextPath}/js/preloader.js"></script>
<!-- / preloader -->

<!-- / javascript -->
</body>

</html>