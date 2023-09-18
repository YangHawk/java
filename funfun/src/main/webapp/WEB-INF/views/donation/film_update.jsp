<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!-- preloader -->
<!-- 스피너 -->
<div id="preloader">
    <div class="spinner spinner-round"></div>
</div>
<!-- preloader -->

<!-- 영화정보 수정 페이지 -->
<div id="page-header" class="faq">
        <div class="container">
            <div class="page-header-content text-center">
                <div class="page-header wsub">
                    <h1 class="page-title fadeInDown animated first">영화 정보 수정하기</h1>
                </div><!-- / page-header -->
                <p class="slide-text fadeInUp animated second">
                <p class="slide-text fadeInUp animated second">영화제에 출품되는 대표적인 영화 정보를 수정하세요</p>
            </div><!-- / page-header-content -->
        </div><!-- / container -->
    </div><!-- / page-header -->



<section id="shop">
   <div class="container">
	<div class="row">
      <form id="film_update" action="film_update" method="post" enctype="multipart/form-data">
      <input type="hidden" name="festivalIdx" value="${filminfo.festivalIdx }">
      <input type="hidden" name="idx" value="${filminfo.idx }">
      <input type="hidden" name="accountId" value="${filminfo.accountId}">
      <sec:csrfInput/>
            <!-- product content area -->
            <div class="col-sm-3"></div>
            <div class="col-sm-6 col-md-7 content-area">
               <div class="product-content-area">
                  </div>
                  <!-- / product-slider -->

                  <!-- 영화제 출품 영화 등록 정보를 입력받기 위한 영역 -->
                  <ul class="nav nav-tabs" role="tablist">
                     <li class="active"><a href="#festivalinfo" role="tab"
                        data-toggle="tab" aria-expanded="true">출품 영화 정보 수정하기</a></li>
                  </ul>
                  <!-- / nav-tabs -->

                  <div class="tab-content">

                     <!-- 영화제 출품 영화 등록 정보 입력 -->
                     <div role="tabpanel" class="tab-pane animated fadeIn active" id="festivalinfo">
                        <table>
                           <tbody>
                              <tr>
                                 <td colspan="3"><h3>출품 영화 정보 수정하기</h3></td>
                              </tr>
                              <tr>
                                 <td colspan="3">영화이미지 첨부<div id="multipartFileMsg" style="color: blue;">영화 상세 설명 이미지를 변경하고 싶은 경우에만 첨부해주세요.</div></td>
                              </tr>
                              <tr>
                                 <td colspan="3" class="film-img-size" colspan="2">
                                    <span class="upload-film-img">
                                       <input type="file" name="multipartFile" id="multipartFile" src="<c:url value='/resources/upload/${filminfo.img}' />">
                                    </span>
                                 </td>
                              </tr>
                              <tr height="50px">
                                 <td colspan="2" >영화 제목</td>
                                 <td><input type="text" name="title" id="title" class="form-control" value="${filminfo.title }"></td>
                              </tr>
                              <tr>
                                 <td colspan="3"><div id="titleMsg" style="color: red; display: none;">영화 제목을 반드시 입력해주세요.</div></td>
                              </tr>
                              <tr height="50px">
                                 <td colspan="2">영화 감독</td>
                                 <td><input type="text" name="director" id="director" class="form-control" value="${filminfo.director }"></td>
                              </tr>
                              <tr>
                                 <td colspan="3"><div id="directorMsg" style="color: red; display: none;">영화 감독을 반드시 입력해주세요.</div></td>
                              </tr>
                              <tr height="50px">
                                 <td colspan="2">영화 줄거리</td>
                                 <td><textarea name="story" id="story" rows="5" cols="50" class="form-control">${filminfo.story }</textarea></td>
                              </tr>
                              <tr>
                                 <td colspan="3"><div id="storyMsg" style="color: red; display: none;">영화 줄거리를 1000자 이내로반드시 입력해주세요.</div></td>
                              </tr>
                              <tr height="50px">
                                 <td colspan="2">영화 상영일</td>
                                 <td><input type="date" name="day" id="day" class="form-control" value="${filminfo.day }"></td>
                              </tr>
                              <tr>
                                 <td colspan="3"><div id="dayMsg" style="color: red; display: none;">영화 상영일을 반드시 입력해주세요.</div></td>
                              </tr>
                              <tr height="50px">
                                 <td colspan="2">영화 상영시간</td>
                                 <td><input type="time" name="time" id="time" class="form-control" value="${filminfo.time }"></td>
                              </tr>
                              <tr>
                                 <td colspan="3"><div id="timeMsg" style="color: red; display: none;">영화 상영 시간을 반드시 입력해주세요.</div></td>
                              </tr>
                              <tr height="50px">
                                 <td colspan="2">영화 티저 링크</td>
                                 <td><input type="text" name="video" id="video" class="form-control" value="${filminfo.video }"></td>
                              </tr>
                           </tbody>
                        </table>
                     </div>
                     <!-- /festivalinfo -->
                     <p>&nbsp;</p>
                     <p class="nav nav-tabs" />
                     <p>&nbsp;</p>
                  </div>
                  <!-- /tab-content -->
               <!-- product-content-area -->
               <div class="pagination no-padding festival-register-btn">
                  <button class="btn btn-default btn-rounded no-margin pull-right film-register-submit" type="submit"><span class="lnr lnr-pencil"></span>영화 수정하기</button>
               </div>
               <input type="hidden" name="command" value="${command}" />
               <!-- / product pagination -->
            </div>
            <!-- /col-sm-6 col-md-7 content-area -->
            <!-- / project-content-area -->
			</form>
		</div>
        <!-- / row -->
   	</div>
   	<!-- / container -->
</section>
<!-- / shop single-product -->
			
 
<!-- / content -->

<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.easing.min.js"></script>

<!-- preloader -->
<script src="${pageContext.request.contextPath}/resources/js/preloader.js"></script>
<!-- / preloader -->

<!-- JavaScript -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	//register form 태그 submit
	$("#film-register").submit(function() {
   		//전송 결과 : true(전송), false(미전송)
		var result=true;

	    if($("#title").val()=="") {
	      	$("#titleMsg").css("display","block");
	      	result=false;
   		}else{
	      	$("#titleMsg").css("display","none");
   			
   		}

	    if($("#director").val()=="") {
     		$("#directorMsg").css("display","block");
     		result=false;
   		}else{
     		$("#directorMsg").css("display","none");
   		}

	    const storyPattern = /^[A-Za-z가-힣]{1,1000}$/;
   		if($("#story").val()=="" || !storyPattern.test($("#story").val())) {
      		$("#storyMsg").css("display","block");
      		result=false;
   		}else{
      		$("#storyMsg").css("display","none");
   		}

   		if($("#day").val()=="") {
      		$("#dayMsg").css("display","block");
      		result=false;
   		}else{
      		$("#dayMsg").css("display","none");
   		}

		if($("#time").val()=="") {
      		$("#timeMsg").css("display","block");
      		result=false;
   		}else{
      		$("#timeMsg").css("display","none");
   		}
		
		
		//유튜브 api 사용하여 업그레이드시킬것
		//const youtubeUrl = /(http: |https:)?(\/\/)?(www\.)?(youtube.com|youtu.be)\/(watch|embed)?(\?v=|\/)?(\S+)?/g
				
		
   		return result;
	});
});
</script>