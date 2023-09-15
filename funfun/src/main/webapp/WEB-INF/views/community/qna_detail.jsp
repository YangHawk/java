<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>question detail</title>
<style>
.categories-sidebar-widget {
    max-width: 150px;
    margin-top: 150px;
    margin-left: 50px;
}
.col-sm-8 {
	margin-top: 20px;
}
.comment-form {
	margin-bottom: 50px;
}
.space {
	height: auto;
	display: inline-block;
	margin-right: 4px;
}
.pagination {
	margin-left: 500px;
}
.anbtn {
	width: 10px;
	display: block;
}
img {
   max-width: 500px;
    height: auto;
}
</style>
</head>

<body>

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

<div class="container">
    <!-- blog content + sidebar -->
    <section id="blog">
        <div class="row">
            <!-- blog content area -->
            <div class="col-sm-8">
                <div class="post-content-area">
                    <div class="media">
                        <div class="media-body">
                            <h4 class="media-heading">${question.title}</h4>
                            <hr>
                            <p class="right-align">
								<span>작성자 : ${question.accountId}</span>
								<span style="margin-left: 10px;">&nbsp; ${question.day}</span>
							</p>
                            <hr>
                            <p>${question.content}</p>
                            <hr>
                            <c:if test="${not empty question.fileData}">
                            <p>첨부파일: ${question.fileData}</p>
                             	<a href="<c:url value='/resources/upload/${question.fileData}' />" download="${question.fileData}">첨부 파일 다운로드</a>
                            	<hr>
							</c:if>
                        </div><!-- / media-body -->
                    </div><!-- / media -->


					<!-- 수정 폼을 감싸는 div -->
                    <div id="edit-form" style="display: none;">
                    <label>수정하기</label>
                    <br><br>
                    <div class="form-group">
                    	<label for="title">제목</label>
                    	<textarea id="title" name="title" class="form-control">${question.title}</textarea>
                    </div>
                    <hr>
                    <div class="form-group">
                    	<label for="nContent">내용</label>
                    	<textarea id="nContent" name="content" class="form-control">${question.content}</textarea>
                    </div>
                    <div class="form_group">
						<label>파일 첨부</label>
						<input type="file" id="uploadFile" name="uploadFile">
					 	<c:if test="${not empty question.fileData}">
						    <img src="<c:url value='/resources/upload/${question.fileData}' />" id="fileimg" style="display: block;">
							<button id="deleteFileBtn">파일 삭제</button>
						</c:if>
					</div>
						<input type="hidden" name="idx" value="${question.idx}">
                    <hr>
                      <!-- 수정 버튼 -->
                        <button id="modifyBtn" type="submit">저장</button>
                        <button id="cancelModifyBtn" type="button">취소</button>
                    </div>

                    <div class="post-footer">
                        <div class="pull-left">
                            <!-- 작성자에게만 보이게 -->
                            <c:if test="${loginAccount != null and loginAccount.id == question.accountId}">
	                            <a href="javascript:void(0)" id="modify-question" class="btn btn-xs btn-primary-filled btn-rounded">수정하기</a>
                            </c:if>
                            <c:if test="${loginAccount != null and loginAccount.id == question.accountId || loginAccount.status == 0}">
								<a href="<c:url value='/community/question/delete'/>?idx=${question.idx}" class="btn btn-xs btn-primary-filled btn-rounded">삭제하기</a>
                            </c:if>
                        </div>
                    </div><!-- / post-footer -->
                </div><!-- / blog-block -->

               <!-- post pagination -->
                <div class="pagination"><!-- 이전글이 없는 경우 이전글 버튼 생략 -->
                   <c:choose>
                   		<c:when test="${preNextIdx.prevnum != 0}">
                          <a href="<c:url value='/community/qna_detail'/>?idx=${preNextIdx.prevnum}" class="btn btn-direction btn-default btn-rounded" id="prevButton"><i class="fa fa-long-arrow-left"></i><span>이전글</span></a>
                        </c:when>
						<c:otherwise>
                        	<div class="space"></div>
                    	</c:otherwise>
                    </c:choose>
                     <div class="space"></div>
                     	<button type="button" class="btn btn-direction btn-default btn-rounded" id="list_btn"><i class="fa fa-long-arrow-center"></i><span>목록</span></button>
                     <div class="space"></div>
                    <c:choose>
                       <c:when test="${preNextIdx.nextnum != 0}">
                           <a href="<c:url value='/community/qna_detail'/>?idx=${preNextIdx.nextnum}" class="btn btn-direction btn-default btn-rounded" id="nextButton"><span>다음글</span><i class="fa fa-long-arrow-right"></i></a>
                        </c:when>
                    </c:choose>
                </div><!-- / post pagination -->
				<br>
				
                <div class="comments">
                	<div class="comment-title">
                		<c:if test="${question.answerContent != null }">
		                    <h4>답변</h4>
		                    <hr>
                   	 	</c:if>
                    </div>
                    
                    <ul class="media-list">
                        <li class="media">
                            <div class="media-body">
                                <div class="comment-info">
                                	<c:if test="${question.answerContent != null }">
	                                    <div class="comment-date">Admin &nbsp; ${question.answerDay}</div>
	                                    <hr>
                                	</c:if>
                                </div><!-- / comment-info -->
                                
                                <div id="adminComment" class="comment">
                                	<c:if test="${question.answerContent != null }">
	                                	<p>${question.answerContent}</p>
	                                	<hr>
                                	</c:if>
                                </div><!-- / comment -->	
                                <br>
                                
                                <div class="comment">
                                	<c:if test="${loginAccount.status == 0 and question.answerContent != null }">
	                                    <a id="modify-answer" href="javascript:void(0)" class="btn btn-xs btn-primary-filled btn-rounded">수정하기</a>
	                            		<a href="<c:url value='/community/question/delete_by_admin'/>?idx=${question.idx}" class="btn btn-xs btn-primary-filled btn-rounded">삭제하기</a>
                            		</c:if>
                                </div><!-- / comment -->

                            </div><!-- / parent media-body -->
                        </li><!-- / media -->
                    </ul><!-- / media-list -->

                    <!-- comment form -->
                    <c:if test="${loginAccount.status == 0 and question.answerContent == null }">
	                    <div id="comment-form">
	                        <form id="commentForm" data-toggle="validator">
	                            <h4 class="space-left">답변 작성</h4>
	                            <div class="row">
	                                <div class="col-sm-12">
	                                    <div class="form-group">
	                                        <textarea id="ansmessage" class="form-control" rows="5" placeholder="답변을 입력해주세요." required></textarea>
	                                    </div>
	                                </div>
	                                    <button type="submit" id="form-submit" class="anbtn-btn btn-primary-filled btn-form-submit btn-rounded">답변 등록</button>
	                            </div><!-- / row -->
	                        </form>
	                    </div>
                    </c:if>
					<c:if test="${loginAccount.status == 0 and question.answerContent != null }">                    	
                    	<div id="comment-form2">
	                        <form id="commentForm2" data-toggle="validator">
	                            <h4 class="space-left">답변 수정</h4>
	                            <div class="row">
	                               
	                                <div class="col-sm-12">
	                                    <div class="form-group">
	                                        <textarea id="ansmessage2" class="form-control" rows="5" required>${question.answerContent }</textarea>
	                                    </div>
	                                </div>
	                                    <button type="submit" id="form-submit2" class="anbtn-btn btn-primary-filled btn-form-submit btn-rounded">답변 등록</button>
	                            </div><!-- / row -->
	                        </form>
	                    </div>
                    </c:if>
                    <!-- / comment form -->
                </div><!-- / comments -->
            </div> <!-- / col-sm-8 -->
        </div><!-- / row -->
    </section>
</div><!-- / container -->
<!-- / content -->

<!-- javascript -->
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.easing.min.js"></script>


<script type="text/javascript">
$(document).ready(function() {
	 var fileDeleted = false; 
	 
	 $("#cancelModifyBtn").click(function(){
	        $("#edit-form").toggle();
	   });
	
	// "수정하기" 버튼 클릭 시
	 $("#modify-question").click(function() {
	        // 수정 폼을 표시하거나 숨기기
	        $("#edit-form").toggle();
	    });
	
	// 파일 삭제 버튼을 누른 경우
	    $("#deleteFileBtn").click(function() {
	    	var uploadFileInput = $("#uploadFile");
	        // 파일 삭제 버튼을 누르면 파일 입력 필드를 초기화
	        uploadFileInput.val("");
	    	$("#fileimg").css("display", "none");
	    	$("#deleteFileBtn").css("display", "none");
	    	fileDeleted = true;
	    });
	    
	 //수정 버튼 클릭 시
	    $("#modifyBtn").click(function() {
	       var title=$("#title").val();
	       var content=$("#nContent").val();
	       var idx = "${question.idx}";
	       var uploadFile = $("#uploadFile")[0].files[0];
	       
	       if(title == "") {
	          alert("제목을 입력해 주세요.");
	          return;
	       }
	       
	       if(content == "") {
	          alert("내용을 입력해 주세요.");
	          return;
	       }
	       var formData = new FormData();
	       formData.append("title", title);
	       formData.append("content", content);
	       formData.append("idx", idx);
	       
	    	
	       if (uploadFile) {
	    	      formData.append("uploadFile", uploadFile);
	    	      fileDeleted = false;
	       }else {
	    	      // 파일이 선택되지 않은 경우 빈 파일 객체를 추가
	    	      formData.append("uploadFile", new Blob(), "placeholder");
	       }
	       
	       formData.append("fileDeleted", fileDeleted);
	       $.ajax({
	          type: "POST",
	          url: "${pageContext.request.contextPath}/community/question_modify",
	          data: formData,
	          processData: false, // 중요: FormData 사용 시 false로 설정
	          contentType: false,
	          enctype: 'multipart/form-data',
	          success: function(result) {
	 	    	    window.location.reload();
	          },
	          error: function(xhr) {
	             alert("글 수정 중 오류가 발생하였습니다("+ xhr.status+")");
	          }
	       });
	    });
	 
    $("#comment-form2").hide(); // 답변 작성 폼을 숨김
	var isEditing = false;
	
    $("#list_btn").click(function(){
        var listPageURL = "/funfun/community/qna_list";
        window.location.href = listPageURL;
    });
    
    $("#modify-answer").click(function(){
    	if (!isEditing) { // 수정 모드가 아닌 경우
            $("#comment-form2").show(); // 답변 작성 폼을 표시
            isEditing = true; // 수정 모드로 설정
        } else { // 수정 모드인 경우
            $("#comment-form2").hide(); // 답변 작성 폼을 숨김
            isEditing = false; // 수정 모드 해제
        }
    });
    
    
    
    // 댓글 폼을 서버로 제출하는 이벤트 처리
    $("#commentForm").submit(function(event) {
    	$("#adminComment").empty();
    	
        event.preventDefault(); // 기본 제출 동작 방지
        
        var commentContent = $("#ansmessage").val();
        
     // 질문의 idx 값을 가져오기
        var questionIdx = ${question.idx};

        // 서버로 댓글 데이터를 전송하고 데이터베이스에 저장
        $.ajax({
            type: "PUT",
            contentType: 'application/json',
            url: "/funfun/admin/question_modify_by_admin", // 서버 측 엔드포인트 설정
            data: JSON.stringify({ "idx": questionIdx, "answerContent": commentContent }), // 댓글 내용과 질문의 idx 전송
            dataType: "text",
            success: function(response) {
                // 서버에서 성공적으로 응답을 받았을 때
                if (response=="success") {
	            	console.log(response);
                    // 새 댓글을 화면에 동적으로 추가
                    var newComment = $("<div>").text(commentContent);
                    $("#adminComment").append(newComment);
                    $("#ansmessage").val(""); // 입력 필드 초기화
                    $("#comment-form").hide();
                    location.reload();
                } else {
	            	console.log(response);
                    alert("댓글 등록에 실패했습니다.");
                }
            },
            error: function(xhr) {
                	console.log({ "idx": questionIdx, "answerContent": commentContent });
                alert("댓글 등록 중 오류가 발생했습니다.");
            }
        });
    }); 
    
 // 댓글 폼을 서버로 제출하는 이벤트 처리
    $("#commentForm2").submit(function(event) {
    	$("#adminComment").empty();
    	
        event.preventDefault(); // 기본 제출 동작 방지
        
        var commentContent = $("#ansmessage2").val();
        
     // 질문의 idx 값을 가져오기
        var questionIdx = ${question.idx};

        // 서버로 댓글 데이터를 전송하고 데이터베이스에 저장
        $.ajax({
            type: "PUT",
            contentType: 'application/json',
            url: "/funfun/admin/question_modify_by_admin", // 서버 측 엔드포인트 설정
            data: JSON.stringify({ "idx": questionIdx, "answerContent": commentContent }), // 댓글 내용과 질문의 idx 전송
            dataType: "text",
            success: function(response) {
                // 서버에서 성공적으로 응답을 받았을 때
                if (response=="success") {
	            	console.log(response);
                    // 새 댓글을 화면에 동적으로 추가
                    var newComment = $("<div>").text(commentContent);
                    $("#adminComment").append(newComment);
                    $("#comment-form2").hide();
                } else {
	            	console.log(response);
                    alert("댓글 등록에 실패했습니다.");
                }
            },
            error: function(xhr) {
                	console.log({ "idx": questionIdx, "answerContent": commentContent });
                alert("댓글 등록 중 오류가 발생했습니다.");
            }
        });
    });
});
</script>


<!-- preloader -->
<script src="${pageContext.request.contextPath}/resources/js/preloader.js"></script>
<!-- / preloader -->



<!-- / javascript -->
</body>

</html>