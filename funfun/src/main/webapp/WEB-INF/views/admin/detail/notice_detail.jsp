<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
.right-align {
        text-align: right;
}
.space {
   height: auto;
   display: inline-block;
   margin-right: 4px;
}
.pagination {
   margin-left: 500px;
}
img {
   max-width: 500px;
    height: auto;
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
                            <h4 class="media-heading">${notice.title}</h4>
                            <hr>
                            <p class="right-align">${notice.day}</p>
                            <hr>
                            <c:if test="${not empty notice.fileData}">
							    <img src="<c:url value='/resources/upload/${notice.fileData}' />" >
							</c:if>
                            <p>${notice.content}</p>
                            <hr>
                            <c:if test="${not empty notice.fileData}">
							    <p>첨부파일: ${notice.fileData}</p>
							    <!-- 파일 다운로드 링크 -->
							    <a href="<c:url value='/resources/upload/${notice.fileData}' />" download="${notice.fileData}">첨부 파일 다운로드</a>
                             <hr>
							</c:if>
                            <!-- 수정 폼을 감싸는 div -->
                            <div id="edit-form" style="display: none;">
                            <label>수정하기</label>
                            <br><br>
                                 <div class="form-group">
                            <label for="title">제목</label>
                            <textarea id="title" name="title" class="form-control">${notice.title}</textarea>
                        </div>
                        <hr>
                        <div class="form-group">
                            <label for="content">내용</label>
                            <textarea id="nContent" name="content" class="form-control">${notice.content}</textarea>
                        </div>
                        <div class="form_group">
						    <label>파일 첨부</label>
						    <input type="file" id="uploadFile" name="uploadFile">
						     <c:if test="${not empty notice.fileData}">
							    <img src="<c:url value='/resources/upload/${notice.fileData}' />" id="fileimg" style="display: block;">
								<button id="deleteFileBtn">파일 삭제</button>
							</c:if>
						</div>
						<input type="hidden" name="idx" value="${notice.idx}">
                        <hr>

                              <!-- 수정 버튼 -->
                                 <button id="modifyBtn" type="submit">저장</button>
                                 <button id="cancelModifyBtn" type="button">취소</button>
                            </div>
                        </div><!-- / media-body -->
                    </div><!-- / media -->

                    <div class="post-footer">
                        <div class="pull-left">
                            <!-- 관리자에게만 보이게 -->
                            <a href="javascript:void(0)" id="modify-notice" class="btn btn-xs btn-primary-filled btn-rounded">수정하기</a>
                            <a href="javascript:void(0)" id="delete-notice" class="btn btn-xs btn-primary-filled btn-rounded">삭제하기</a>
                        </div>
                    </div><!-- / post-footer -->
                </div><!-- / blog-block -->

                <!-- post pagination -->
                <div class="pagination">
                <c:choose>
                   <c:when test="${preNextIdx.prevnum != 0 }">
                       <a href="<c:url value='/community/notice/notice_detail'/>?idx=${preNextIdx.prevnum}" class="btn btn-direction btn-default btn-rounded" id="prevButton"><i class="fa fa-long-arrow-left"></i><span>이전글</span></a>
                   </c:when>
                   <c:otherwise>
                      <div class="space"></div>
                   </c:otherwise>
                </c:choose>
                     <div class="space"></div>
                    <button type="button" class="btn btn-direction btn-default btn-rounded" id="list_btn"><i class="fa fa-long-arrow-center"></i><span>목록</span></button>
                     <div class="space"></div>
                <c:choose>
                   <c:when test="${preNextIdx.nextnum != 0 }">
                     <a href="<c:url value='/community/notice/notice_detail'/>?idx=${preNextIdx.nextnum}" class="btn btn-direction btn-default btn-rounded" id="nextButton"><span>다음글</span><i class="fa fa-long-arrow-right"></i></a>
                   </c:when>
                   <c:otherwise>
                    <div class="space"></div>
                   </c:otherwise>
                </c:choose>
                </div>
                <!-- / post pagination -->
           
              
            </div><!-- / col-sm-8 -->
        </div><!-- / row -->
    </section>
</div><!-- / container -->


<!-- javascript -->
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.easing.min.js"></script>

<!-- preloader -->
<script src="${pageContext.request.contextPath}/resources/js/preloader.js"></script>
<!-- / preloader -->

<script type="text/javascript">
//CSRF 토큰 관련 정보를 자바스크립트 변수에 저장
var csrfHeaderName = "${_csrf.headerName}";
var csrfTokenValue = "${_csrf.token}";

// Ajax 기능을 사용하여 요청하는 모든 웹 프로그램에게 CSRF 토큰 전달 가능
// ▶ Ajax 요청 시 beforeSend 속성을 설정할 필요 없음
$(document).ajaxSend(function(e, xhr){
	xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
});

$(document).ready(function() {
	var fileDeleted = false;
	
    $("#list_btn").click(function(){
        var listPageURL = "/funfun/community/notice";
        window.location.href = listPageURL;
    });
	
	$("#cancelModifyBtn").click(function(){
        $("#edit-form").toggle();
	});
	
    $("#modify-notice").click(function() {
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
       var idx = "${notice.idx}";
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
       } else { 
           formData.append("uploadFile", new Blob(), "placeholder");
       }
    	
       formData.append("fileDeleted", fileDeleted);
       
       $.ajax({
          type: "POST",
          url: "${pageContext.request.contextPath}/admin/notice_modify",
          data: formData,
          processData: false, // 중요: FormData 사용 시 false로 설정
          contentType: false,
          enctype: 'multipart/form-data',
          dataType: "text",
          success: function(result) {
        	 	 if(result == "success") {
      			noticeDetail(idx);
      			}
          },
          error: function(xhr) {
             alert("공지 사항 수정 중 오류가 발생하였습니다("+ xhr.status+")");
          }
       });
    });
    
	 $("#delete-notice").click(function() {
	        if (confirm("정말로 삭제하시겠습니까?")) {
	            var idx = "${notice.idx}";
	            
	            $.ajax({
	                type: "put",
	                url: "<c:url value='/admin/notice_remove?idx='/>" + idx,
	                success: function(result) {
	                    if(result == "success") {
	                    	noticeListDisplay(page, size, keyword);
	                    } else {
	                        alert("공지 사항 삭제 중 오류가 발생하였습니다.");
	                    }
	                },
	                error: function(xhr) {
	                    alert("공지 사항 삭제 중 오류가 발생하였습니다("+ xhr.status+")");
	                }
	            });
	        }
	    });
});

</script>

<!-- / javascript -->
</body>

</html>