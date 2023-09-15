<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
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
</style>
</head>

<body>
<!-- content -->

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
							  <span>${question.accountId}</span>
							  <span style="margin-left: 10px;">${question.day}</span>
							</p>
                            <hr>
                            <p>${question.content}</p>
                            <hr>
                            <p>첨부파일: ${question.fileData}</p>
                        </div><!-- / media-body -->
                    </div><!-- / media -->

                    <div class="post-footer">
                        <div class="pull-left">
                            <!-- 작성자에게만 보이게 -->
                            <c:if test="${loginAccount != null and loginAccount.id == question.accountId}">
	                            <a href="#" class="btn btn-xs btn-primary-filled btn-rounded">수정하기</a>
                            </c:if>
                            <c:if test="${loginAccount != null and loginAccount.id == question.accountId || loginAccount.status == 0}">
								<a href="<c:url value='/community/question/delete'/>?idx=${question.idx}" class="btn btn-xs btn-primary-filled btn-rounded">삭제하기</a>
                            </c:if>
                        </div>
                    </div><!-- / post-footer -->
                </div><!-- / blog-block -->

               <!-- post pagination -->
                <div class="pagination">
                    <button type="button" class="btn btn-direction btn-default btn-rounded" id="prevButton"><i class="fa fa-long-arrow-left"></i><span>이전글</span></button>
                     <div class="space"></div>
                    <button type="button" class="btn btn-direction btn-default btn-rounded" id="list_btn"><i class="fa fa-long-arrow-center"></i><span>목록</span></button>
                     <div class="space"></div>
                     <button type="button" class="btn btn-direction btn-default btn-rounded" id="nextButton"><span>다음글</span><i class="fa fa-long-arrow-right"></i></button>
                </div><!-- / post pagination -->
				<hr>

                <div class="comments">
                    <h4>답변</h4>
                    <ul class="media-list">
                        <li class="media">
                            <div class="media-body">
                                <div class="comment-info">
                                    <div class="comment-date">${question.answerDay}</div>
                                    <div class="comment-author">Admin</div>
                                    <br>
                                </div><!-- / comment-info -->
                                	<br>
                                	<br>
                                <div id="adminComment" class="comment">
                                	<p>${question.answerContent}</p>
                                </div><!-- / comment -->	
                                	<br>
                                	<br>
                                <div class="comment">
                                	<c:if test="${loginAccount.status == 0 and question.answerContent != null }">
	                                    <a id="modifyBtn" href="javascript:void(0)" class="btn btn-xs btn-primary-filled btn-rounded">수정하기</a>
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
    $("#comment-form2").hide(); // 답변 작성 폼을 숨김
	var isEditing = false;
	
    $("#list_btn").click(function(){
    	questionListDisplay(1, size, keyword);
    });
    
    $("#modifyBtn").click(function(){
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