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

<!-- 영화제 및 펀딩정보 등록페이지 2 -->
<!-- Controller로 해당 영화제의 idx 전달받기? -->
<div id="page-header" class="faq">
        <div class="container">
            <div class="page-header-content text-center">
                <div class="page-header wsub">
                    <h1 class="page-title fadeInDown animated first">영화제 등록하기</h1>
                </div><!-- / page-header -->
                <p class="slide-text fadeInUp animated second">
                <div class="register-step">
					<span>영화제 정보 등록&nbsp;&nbsp;&nbsp;</span><span class="step-icon">▶</span><span style="color: red;">&nbsp;&nbsp;&nbsp;출품 영화 등록</span>
				</div>
                <p class="slide-text fadeInUp animated second">영화제에 출품되는 대표적인 영화 정보를 입력하세요</p>
            </div><!-- / page-header-content -->
        </div><!-- / container -->
    </div><!-- / page-header -->



<section id="shop">
   <div class="container">
	<div class="row">
      <form id="film-register" action="film_register" method="post" enctype="multipart/form-data">
      		<input type="hidden" name="accountId" value="<sec:authentication property="principal.id"/>">
      		<input type="hidden" name = "festivalIdx" value="${festivalIdx }" id="festivalIdxInput">
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
                        data-toggle="tab" aria-expanded="true">출품 영화 정보 등록하기</a></li>
                  </ul>
                  <!-- / nav-tabs -->
                  	<div class="pagination no-padding festival-register-btn">
                  		<button class="btn btn-default btn-rounded no-margin pull-right film-register-end" type="button">영화 등록 마치기</button>
               		</div>

                  <div class="tab-content">

                     <!-- 영화제 출품 영화 등록 정보 입력 -->
                     <div role="tabpanel" class="tab-pane animated fadeIn active" id="festivalinfo">
                        <table>
                           <tbody>
                              <tr>
                                 <td colspan="3"><h3>출품 영화 정보 등록하기</h3></td>
                              </tr>
                              <tr>
                                 <td colspan="3">영화이미지 첨부<div id="multipartFileMsg" style="color: red; display: none;">영화 상세 설명 이미지를 반드시 입력해주세요.</div></td>
                              </tr>
                              <tr>
                                 <td colspan="3" class="film-img-size" colspan="2">
                                    <span class="upload-film-img">
                                       <input type="file" name="multipartFile" id="multipartFile">
                                    </span>
                                 </td>
                              </tr>
                              <tr height="50px">
                                 <td colspan="2" >영화 제목</td>
                                 <td><input type="text" name="title" id="title" class="form-control"></td>
                              </tr>
                              <tr>
                                 <td colspan="3"><div id="titleMsg" style="color: red; display: none;">영화 제목을 반드시 입력해주세요.</div></td>
                              </tr>
                              <tr height="50px">
                                 <td colspan="2">영화 감독</td>
                                 <td><input type="text" name="director" id="director" class="form-control"></td>
                              </tr>
                              <tr>
                                 <td colspan="3"><div id="directorMsg" style="color: red; display: none;">영화 감독을 반드시 입력해주세요.</div></td>
                              </tr>
                              <tr height="50px">
                                 <td colspan="2">영화 줄거리</td>
                                 <td><textarea name="story" id="story" rows="5" cols="50" class="form-control"></textarea></td>
                              </tr>
                              <tr>
                                 <td colspan="3"><div id="storyMsg" style="color: red; display: none;">영화 줄거리를 1000글자 이내로 반드시 입력해주세요.</div></td>
                              </tr>
                              <tr height="50px">
                                 <td colspan="2">영화 상영일</td>
                                 <td><input type="date" name="day" id="day" class="form-control"></td>
                              </tr>
                              <tr>
                                 <td colspan="3"><div id="dayMsg" style="color: red; display: none;">영화 상영일을 반드시 입력해주세요.</div></td>
                              </tr>
                              <tr height="50px">
                                 <td colspan="2">영화 상영시간</td>
                                 <td><input type="time" name="time" id="time" class="form-control"></td>
                              </tr>
                              <tr>
                                 <td colspan="3"><div id="timeMsg" style="color: red; display: none;">영화 상영 시간을 반드시 입력해주세요.</div></td>
                              </tr>
                              <tr height="50px">
                                 <td colspan="2">영화 티저 링크</td>
                                 <td><input type="text" name="video" id="video" class="form-control"></td>
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
                  <button class="btn btn-default btn-rounded no-margin pull-right film-register-submit" type="submit"><span class="lnr lnr-pencil"></span>영화 등록하기</button>
               </div>
               <!-- / product pagination -->
            </div>
            <!-- /col-sm-6 col-md-7 content-area -->
            <!-- / project-content-area -->
            <input type="hidden" name="command" value="${command}" />
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

var festivalIdx = "${param.festivalIdx}";
$(document).ready(function() {
	
	var message = "${message}";
    if (message !== "") {
        alert(message);
    }
	
	// 영화 등록 마치기 버튼 클릭 이벤트 등록 
    $(".film-register-end").click(function() {
    	var shouldEndRegister = "";

        if ($("#multipartFile").val() != "" || $("#title").val() != "" || $("#director").val() != "" || $("#story").val() != "" ||
			$("#day").val() != "" || $("#time").val() != ""|| $("#video").val() != "") {
				shouldEndRegister = confirm("현재 입력된 영화정보는 저장되지 않습니다. 영화 등록을 마치시겠습니까?");
            } else {
				shouldEndRegister = confirm("영화 등록을 마치시겠습니까?");
			}
        
        if (shouldEndRegister) {
        	var command = '${command}';
        	if(command.includes("account/my_festival") || command.includes("donation/single-festival")){
        		window.location.href = command;
        	}else{
				window.location.href = '${pageContext.request.contextPath}/';
        	}
		}
	});
	
	//register form 태그 submit
	$("#film-register").submit(function() {
   		//전송 결과 : true(전송), false(미전송)
		var result=true;

		if($("#multipartFile").val()=="") {
	      	$("#multipartFileMsg").css("display","block");
	      	result=false;
   		}else{
	      	$("#multipartFileMsg").css("display","none");
   		}

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

	    const storyPattern= /^[\s\S]{1,5000}$/;
        if($("#story").val()==""||!storyPattern.test($("#story").val())) {
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