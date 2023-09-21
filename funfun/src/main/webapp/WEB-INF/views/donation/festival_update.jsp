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

<!-- 영화제 및 펀딩정보 수정 페이지 -->
<div id="page-header" class="faq">
	<div class="container">
		<div class="page-header-content text-center">
			<div class="page-header wsub">
				<h1 class="page-title fadeInDown animated first">영화제 정보 수정하기</h1>
			</div>
			<!-- / page-header -->
			<p class="slide-text fadeInUp animated second">펀딩받을 영화제 정보를 수정하세요</p>
		</div>
		<!-- / page-header-content -->
	</div>
	<!-- / container -->
</div>
<!-- / page-header -->



<section id="shop">
	<div class="container">
		<form id="festival_update" action="festival_update" method="post"
			enctype="multipart/form-data">
			<input type="hidden" name="idx" value="${festivalinfo.idx}">
			<input type="hidden" name="accountId" value="${festivalinfo.accountId}">
			<sec:csrfInput/>
			<div class="row">
				<!-- product content area -->
				<div class="col-sm-6 col-md-7 content-area">
					<div class="product-content-area">
						<!-- 영화제 포스터 이미지를 입력받기 위한 영역 -->
						<div style="color: red;">${message}</div>
						<div id="mainMultipartFileMsg" style="color: blue;">영화제
							메인 포스터 이미지를 변경하고 싶은 경우에만 첨부해주세요.</div>
						<div id="product-slider" class="carousel slide"
							data-ride="carousel">
							<!-- wrapper for slides -->
							<div class="carousel-inner" role="listbox">
								<div class="item active file-size">
									<span class="upload-film-img"><input type="file"
										name="mainMultipartFile" id="mainMultipartFile"
										accept=".jpg, .jpeg, .png" src="<c:url value='/resources/upload/${festivalinfo.mainImg}' />"></span>
								</div>
							</div>
							<!-- / wrapper for slides -->
						</div>
						<!-- / product-slider -->

						<!-- 영화제 등록 정보를 입력받기 위한 영역 -->
						<ul class="nav nav-tabs" role="tablist">
							<li class="active"><a href="#festivalinfo" role="tab"
								data-toggle="tab" aria-expanded="true">영화제 정보 수정</a></li>
						</ul>
						<!-- / nav-tabs -->

						<div class="tab-content">

							<!-- 영화제 등록 정보 입력 -->
							<div role="tabpanel" class="tab-pane animated fadeIn active"
								id="festivalinfo">
								<table>
									<tbody>
										<tr>
											<td colspan="3"><h3>영화제 정보 수정</h3></td>
										</tr>
										<tr height="30px">
											<td colspan="3">영화제 상세 이미지 첨부
												<div id="subMultipartFileMsg"
													style="color: blue;">영화제 상세 설명 이미지를 변경하고 싶은 경우에만 첨부해주세요.</div>
											</td>
										</tr>
										<tr>
											<td colspan="3" class="film-img-size" colspan="2"><span
												class="upload-film-img"><input type="file"
													name="subMultipartFile" id="subMultipartFile"
													accept=".jpg, .jpeg, .png"
													value="<c:url value='/resources/upload/${festivalinfo.subImg}' />"></span>
											</td>
										</tr>
										<tr height="50px">
											<td colspan="2">영화제 시작일</td>
											<td><input type="date" name="startDay" id="startDay"
												class="form-control" value="${festivalinfo.startDay }"></td>
										</tr>
										<tr>
											<td colspan="3"><div id="startDayMsg" style="color: red; display: none;">영화제 시작일을 반드시 입력해주세요.</div></td>
											<td colspan="3"><div id="startEndDayMsg" style="color: red; display: none;">영화제 종료일이 시작일보다 빠르게 작성되었습니다.</div></td>
										</tr>
										<tr height="50px">
											<td colspan="2">영화제 종료일</td>
											<td><input type="date" name="endDay" id="endDay"
												class="form-control" value="${festivalinfo.endDay }"></td>
										</tr>
										<tr>
											<td colspan="3"><div id="endDayMsg"
													style="color: red; display: none;">영화제 종료일을 반드시
													입력해주세요.</div></td>
										</tr>
										<tr height="50px">
											<td colspan="2">우편번호</td>
											<td><input type="text" name="address1" id="address1"
												size="7" readonly="readonly" class="form-control"
												value="${festivalinfo.address1 }"></td>
										</tr>
										<tr>
											<td colspan="3"><span id="postSearch"
												class="btn btn-default btn-rounded no-margin pull-right">우편번호
													검색</span></td>
										</tr>
										<tr>
											<td colspan="3"><div id="address1Msg"
													style="color: red; display: none;">우편번호를 반드시 입력해주세요.</div></td>
										</tr>
										<tr height="50px">
											<td colspan="2">영화제 기본주소</td>
											<td><input type="text" name="address2" id="address2"
												size="50" class="form-control"
												value="${festivalinfo.address2 }"></td>
										</tr>
										<tr>
											<td colspan="3"><div id="address2Msg"
													style="color: red; display: none;">영화제 기본주소를 반드시
													입력해주세요.</div></td>
										</tr>
										<tr height="50px">
											<td colspan="2">영화제 상세주소</td>
											<td><input type="text" name="address3" id="address3"
												size="50" class="form-control"
												value="${festivalinfo.address3 }"></td>
										</tr>
										<tr>
											<td colspan="3"><div id="address3Msg"
													style="color: red; display: none;">영화제 상세주소를 반드시
													입력해주세요.</div></td>
										</tr>
										<tr height="50px">
											<td>주관</td>
											<td colspan="2"><input type="text" name="host" id="host"
												class="form-control" value="${festivalinfo.host }"></td>
										</tr>
										<tr>
											<td colspan="3"><div id="hostMsg"
													style="color: red; display: none;">영화제 주관을 반드시
													입력해주세요.</div></td>
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
					</div>
					<!-- product-content-area -->
					<div class="pagination no-padding festival-register-btn">
						<button
							class="btn btn-default btn-rounded no-margin pull-right festival-update-submit"
							type="submit">
							<span class="lnr lnr-pencil"></span>영화제 수정하기
						</button>
					</div>
					<!-- / product pagination -->
				</div>
				<!-- /col-sm-6 col-md-7 content-area -->
				<!-- / project-content-area -->


				<!-- project sidebar area -->
				<div class="col-sm-6 col-md-5 product-sidebar">
					<div class="product-details">
						<div class="product-info">
							<table class="table-without-borders">
								<tbody>
									<tr class="info">
										<td><p class="funding-register-fontsize">영화제 제목</p></td>
										<td colspan="2"><input type="text" name="subject"
											id="subject" class="form-control festival-form-control"
											value="${festivalinfo.subject }"></td>
									</tr>
									<tr class="info">
										<td colspan="3"><div id="subjectMsg"
												style="color: red; display: none;">영화제 제목을 반드시 입력해주세요.</div></td>
									</tr>
									<tr class="info">
										<td><p class="funding-register-fontsize">목표금액(원)</p></td>
										<td colspan="2"><input type="text" name="target"
											id="target" class="form-control festival-form-control"
											value="${festivalinfo.target }"></td>
									</tr>
									<tr class="info">
										<td colspan="3"><div id="targetMsg"
												style="color: red; display: none;">펀딩 목표금액을 반드시
												입력해주세요.</div></td>
									</tr>
									<tr class="info">
										<td><p class="funding-register-fontsize">펀딩 시작일</p></td>
										<td colspan="2"><input type="date" name="fundingStart"
											id="fundingStart" class="form-control festival-form-control"
											value="${festivalinfo.fundingStart }"></td>
									</tr>
									<tr class="info">
										<td colspan="3"><div id="fundingStartMsg" style="color: red; display: none;">펀딩 시작일을 반드시 입력해주세요.</div></td>
										<td colspan="3"><div id="fundingStartEndDayMsg" style="color: red; display: none;">펀딩 종료일이 시작일보다 빠르게 작성되었습니다.</div></td>
									</tr>
									<tr class="info">
										<td><p class="funding-register-fontsize">펀딩 종료일</p></td>
										<td colspan="2"><input type="date" name="fundingEnd"
											id="fundingEnd" class="form-control festival-form-control"
											value="${festivalinfo.fundingEnd }"></td>
									</tr>
									<tr class="info">
										<td colspan="3"><div id="fundingEndMsg"
												style="color: red; display: none;">펀딩 종료일을 반드시 입력해주세요.</div></td>
									</tr>
									<tr class="info">
										<td><p class="funding-register-fontsize">영화제 사이트</p></td>
										<td colspan="2"><input type="text" name="site" id="site"
											class="form-control festival-form-control"
											value="${festivalinfo.site }"></td>
									</tr>
								</tbody>
							</table>
						</div>
						<!-- / project-info -->

					</div>
					<!-- product-details -->

				</div>
				<!-- / col-sm-4 col-md-3 -->

				<p>&nbsp;</p>

				<!-- 등록자가 펀딩금액 옵션을 설정할 수 있는 입력란이 출력될 영역 -->
				<!-- 옵션1 -->
				<div id="fundingOption1">
  					<div class="col-sm-6 col-md-5 product-sidebar">
    				<div class="funding-options">
        				<p>펀딩금액 옵션1</p>
        				<p>
            				<input type="text" name="option1" id="option1"
                   				value="${festivalinfo.option1 }"> 원
        				</p>
    				</div>
 			 		</div>
				</div>
				
				<p>&nbsp;</p>
				<!-- 옵션2 -->
				<div id="fundingOption2">
  					<div class="col-sm-6 col-md-5 product-sidebar">
    				<div class="funding-options">
     			 		<p>펀딩금액 옵션2</p>
     			 		<p>
     			   			<input type="text" name="option2" id="option2" value="${festivalinfo.option2 }"> 원
    			  		</p>
    				</div>
 			 		</div>
				</div>
				
				<div id="fundingOptionPlus2">
					<div class="col-sm-6 col-md-5 product-sidebar">
						<div class="funding-options" style="padding-left: 50%;">
							<div id="addOption">
								<span class="lnr lnr-plus-circle" style="height: 50px; width: 50px;"></span>
							</div>
						</div>
					</div>
				</div>
				
				<p>&nbsp;</p>
				<!-- 옵션3 -->
				<div id="fundingOption3">
  					<div class="col-sm-6 col-md-5 product-sidebar">
    				<div class="funding-options">
     			 		<p>펀딩금액 옵션3</p>
     			 		<p>
     			   			<input type="text" name="option3" id="option3" value="${festivalinfo.option3 }"> 원
    			  		</p>
    				</div>
 			 		</div>
				</div>
				
				<div id="fundingOptionPlus3">
					<div class="col-sm-6 col-md-5 product-sidebar">
						<div class="funding-options" style="padding-left: 50%;">
							<div id="addOption">
								<span class="lnr lnr-plus-circle" style="height: 50px; width: 50px;"></span>
							</div>
						</div>
					</div>
				</div>
				
				<p>&nbsp;</p>
				<!-- 옵션4 -->
				<div id="fundingOption4">
  					<div class="col-sm-6 col-md-5 product-sidebar">
    				<div class="funding-options">
     			 		<p>펀딩금액 옵션4</p>
     			 		<p>
     			   			<input type="text" name="option4" id="option4" value="${festivalinfo.option4 }"> 원
    			  		</p>
    				</div>
 			 		</div>
				</div>
				
				<div id="fundingOptionPlus4">
					<div class="col-sm-6 col-md-5 product-sidebar">
						<div class="funding-options" style="padding-left: 50%;">
							<div id="addOption">
								<span class="lnr lnr-plus-circle" style="height: 50px; width: 50px;"></span>
							</div>
						</div>
					</div>
				</div>
				
				<!-- / project sidebar area -->
			</div>
			<input type="hidden" name="command" value="${command}" />
			<!-- / row -->
		</form>
	</div>
	<!-- / container -->
</section>
<!-- / shop single-product -->

<!-- / content -->

<!-- JavaScript -->
<script
	src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/jquery.easing.min.js"></script>

<!-- preloader -->
<script
	src="${pageContext.request.contextPath}/resources/js/preloader.js"></script>
<!-- / preloader -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
$(document).ready(function() {
    if ($("#option2").val() === "0" && $("#option3").val() === "0" && $("#option4").val() === "0") {
      $("#fundingOption2").hide();
      $("#fundingOptionPlus2").show();
      $("#fundingOption3").hide();
      $("#fundingOptionPlus3").hide();
      $("#fundingOption4").hide();
      $("#fundingOptionPlus4").hide();
    } else if ($("#option2").val() !== "0" && $("#option3").val() === "0" && $("#option4").val() === "0") {
        $("#fundingOption2").show();
        $("#fundingOptionPlus2").hide();
        $("#fundingOption3").hide();
        $("#fundingOptionPlus3").show();
        $("#fundingOption4").hide();
        $("#fundingOptionPlus4").hide();
    } else {
        $("#fundingOption2").show();
        $("#fundingOptionPlus2").hide();
        $("#fundingOption3").show();
        $("#fundingOptionPlus3").hide();
        $("#fundingOption4").hide();
        $("#fundingOptionPlus4").show();
    }

    function toggleOption(optionNumber) {
        $(`#fundingOption${optionNumber}`).toggle();
    }

    $("#fundingOptionPlus2").click(function() {
      toggleOption(2);
      $("#fundingOptionPlus2").hide();
      $("#fundingOption2").show();
      $("#fundingOptionPlus3").show();
    });

    $("#fundingOptionPlus3").click(function() {
      toggleOption(3);
      $("#fundingOptionPlus3").hide();
      $("#fundingOption3").show();
      $("#fundingOptionPlus4").show();
    });

    $("#fundingOptionPlus4").click(function() {
      toggleOption(4);
      $("#fundingOptionPlus4").hide();
      $("#fundingOption4").show();
    });
  });


   //daum 주소 API 사용
   $("#postSearch").click(function() {
       new daum.Postcode({
           oncomplete: function(data) {
               jQuery("#address1").val(data.zonecode);
               jQuery("#address2").val(data.address);
           }
       }).open();
   });
   
   //register form 태그 submit
	//register form 태그 submit
	   $("#festival_update").submit(function() {
	      //전송 결과 : true(전송), false(미전송)
	      var optoin1 = $("#option1").val();
	      var submitResult=true;
		    
	      if($("#mainMultipartFile").val()=="") {
	         $("#mainMultipartFileMsg").css("display","block");
	         submitResult=false;
	      }else{
	         $("#mainMultipartFileMsg").css("display","none");
	      }
	   
	      if($("#subMultipartFile").val()=="") {
	         $("#subMultipartFileMsg").css("display","block");
	         submitResult=false;
	      }else{
	         $("#subMultipartFileMsg").css("display","none");
	      }
	   
	      if($("#startDay").val()=="") {
	         $("#startDayMsg").css("display","block");
	         submitResult=false;
	      }else{
	         $("#startDayMsg").css("display","none");
	      }
	   
	      if($("#endDay").val()=="") {
	         $("#endDayMsg").css("display","block");
	         submitResult=false;
	      }else{
	         $("#endDayMsg").css("display","none");
	      }
	      
	      if($("#endDay").val() < $("#startDay").val()){
	    	  $("#startEndDayMsg").css("display","block");
	          submitResult=false;
	      }else{
	    	  $("#startEndDayMsg").css("display","none");
	      }
	   
	      if($("#address1").val()=="") {
	         $("#address1Msg").css("display","block");
	         submitResult=false;
	      }else{
	         $("#address1Msg").css("display","none");
	      }
	   
	      if($("#address2").val()=="") {
	         $("#address2Msg").css("display","block");
	         submitResult=false;
	      }else{
	         $("#address2Msg").css("display","none");
	      }
	   
	      if($("#address3").val()=="") {
	         $("#address3Msg").css("display","block");
	         submitResult=false;
	      }else{
	         $("#address3Msg").css("display","none");
	      }
	   
	      if($("#host").val()=="") {
	         $("#hostMsg").css("display","block");
	         submitResult=false;
	      }else{
	         $("#hostMsg").css("display","none");
	      }
	   
	      if($("#subject").val()=="") {
	         $("#subjectMsg").css("display","block");
	         submitResult=false;
	      }else{
	         $("#subjectMsg").css("display","none");
	      }
	   
	      const targetPattern = /^(?:10000|[1-9]\d{4,7}|100000000)$/;
	      if($("#target").val()=="" || !targetPattern.test($("#target").val())) {
	         $("#targetMsg").css("display","block");
	         submitResult=false;
	      }else{
	         $("#targetMsg").css("display","none");
	      }
	   
	      if($("#fundingStart").val()=="") {
	         $("#fundingStartMsg").css("display","block");
	         submitResult=false;
	      }else{
	         $("#fundingStartMsg").css("display","none");
	      }
	   
	      if($("#fundingEnd").val()=="") {
	         $("#fundingEndMsg").css("display","block");
	         submitResult=false;
	      }else{
	         $("#fundingEndMsg").css("display","none");
	      }
	      
	      if($("#fundingStart").val() > $("#fundingEnd").val()){
	      	$("#fundingStartEndDayMsg").css("display","block");
	        submitResult=false;
	      }else{
	      	$("#fundingStartEndDayMsg").css("display","none");
	      }
	      
	      const optionPattern = /^(?:10000|[1-9]\d{4,6}|5000000)$/;
	      
	      if ($("#option1").val() == "") {
	    	  $("#option1").val(0);
	    	  $("#option1Msg").css("display", "none");
	    	  submitResult=false;
    	  } else if ($("#option1").val() == 0) {
    		  submitResult=true;
    		  if ($("#option1").val() != "0" && !optionPattern.test($("#option1").val())) {
    			  $("#option1Msg").css("display", "block");
    			  submitResult=false;
   			  } else {
   				  $("#option1Msg").css("display", "none");
			  }
   		  }
	      
	      if ($("#option2").val() == "") {
	    	  $("#option2").val(0);
	    	  $("#option1Msg").css("display", "none");
	    	  submitResult=false;
    	  } else if ($("#option2").val() == 0) {
    		  submitResult=true;
    		  if ($("#option2").val() != "0" && !optionPattern.test($("#option2").val())) {
    			  $("#option2Msg").css("display", "block");
    			  submitResult=false;
   			  } else {
   				  $("#option2Msg").css("display", "none");
			  }
   		  }
	      
	      if ($("#option3").val() == "") {
	    	  $("#option3").val(0);
	    	  $("#option3Msg").css("display", "none");
	    	  submitResult=false;
    	  } else if ($("#option3").val() == 0) {
    		  submitResult=true;
    		  if ($("#option3").val() != "0" && !optionPattern.test($("#option3").val())) {
    			  $("#option3Msg").css("display", "block");
    			  submitResult=false;
   			  } else {
   				  $("#option3Msg").css("display", "none");
			  }
   		  }
	      
	      if ($("#option4").val() == "") {
	    	  $("#option4").val(0);
	    	  $("#option4Msg").css("display", "none");
	    	  submitResult=false;
    	  } else if ($("#option4").val() == 0) {
    		  submitResult=true;
    		  if ($("#option4").val() != "0" && !optionPattern.test($("#option1").val())) {
    			  $("#option4Msg").css("display", "block");
    			  submitResult=false;
   			  } else {
   				  $("#option1Msg").css("display", "none");
			  }
   		  }
	      
	      return submitResult;
	   });
   
</script>