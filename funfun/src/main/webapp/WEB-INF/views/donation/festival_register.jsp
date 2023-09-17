<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%> 
<!-- 영화제 및 펀딩정보 등록페이지 1 -->
<div id="page-header" class="faq">
        <div class="container">
            <div class="page-header-content text-center">
                <div class="page-header wsub">
                    <h1 class="page-title fadeInDown animated first">영화제 등록하기</h1>
                </div><!-- / page-header -->
                <p class="slide-text fadeInUp animated second">펀딩받을 영화제 정보를 입력하세요</p>
            </div><!-- / page-header-content -->
        </div><!-- / container -->
    </div><!-- / page-header -->



<section id="shop">
   <div class="container">
      <form id="register" action="festival_register" method="post"
         enctype="multipart/form-data">
         <input type="hidden" name="accountId" value="<sec:authentication property="principal.id"/>">
         <sec:csrfInput/>
         <div class="row">
            <!-- product content area -->
            <div class="col-sm-6 col-md-7 content-area">
               <div class="product-content-area">
                  <!-- 영화제 포스터 이미지를 입력받기 위한 영역 -->
                  <div style="color: red;">${message}</div>
                  <div id="mainMultipartFileMsg" style="color: red; display: none;">영화제
                     메인 포스터 이미지를 반드시 입력해주세요.</div>
                  <div id="product-slider" class="carousel slide"
                     data-ride="carousel">
                     <!-- wrapper for slides -->
                     <div class="carousel-inner" role="listbox">
                        <div class="item active file-size">
                           <span class="upload-film-img"><input type="file" name="mainMultipartFile" id="mainMultipartFile" accept=".jpg, .jpeg, .png"></span>
                        </div>
                     </div>
                     <!-- / wrapper for slides -->
                  </div>
                  <!-- / product-slider -->

                  <!-- 영화제 등록 정보를 입력받기 위한 영역 -->
                  <ul class="nav nav-tabs" role="tablist">
                     <li class="active"><a href="#festivalinfo" role="tab"
                        data-toggle="tab" aria-expanded="true">영화제 정보 등록하기</a></li>
                  </ul>
                  <!-- / nav-tabs -->

                  <div class="tab-content">

                     <!-- 영화제 등록 정보 입력 -->
                     <div role="tabpanel" class="tab-pane animated fadeIn active"
                        id="festivalinfo">
                        <table>
                           <tbody>
                              <tr>
                                 <td colspan="3"><h3>영화제 정보 등록하기</h3></td>
                              </tr>
                              <tr height="30px">
                                 <td colspan="3">영화제 상세 이미지 첨부
                                    <div id="subMultipartFileMsg" style="color: red; display: none;">영화제 상세 설명 이미지를 반드시 입력해주세요.</div>
                                 </td>
                              </tr>
                              <tr>
                                 <td colspan="3" class="film-img-size" colspan="2">
                                    <span class="upload-film-img"><input type="file" name="subMultipartFile" id="subMultipartFile" accept=".jpg, .jpeg, .png"></span>
                                 </td>
                              </tr>
                              <tr height="50px">
                                 <td colspan="2">영화제 시작일</td>
                                 <td><input type="date" name="startDay" id="startDay" class="form-control" value="${festivalinfo.startDay }"></td>
                              </tr>
                              <tr>
                                 <td colspan="3"><div id="startDayMsg" style="color: red; display: none;">영화제 시작일을 반드시 입력해주세요.</div></td>
                                 <td colspan="3"><div id="startEndDayMsg" style="color: red; display: none;">영화제 종료일이 시작일보다 빠르게 작성되었습니다.</div></td>
                              </tr>
                              <tr height="50px">
                                 <td colspan="2">영화제 종료일</td>
                                 <td><input type="date" name="endDay" id="endDay" class="form-control" value="${festivalinfo.endDay }"></td>
                              </tr>
                              <tr>
                                 <td colspan="3"><div id="endDayMsg" style="color: red; display: none;">영화제 종료일을 반드시 입력해주세요.</div></td>
                                 <td colspan="3"><div id="startEndDayMsg" style="color: red; display: none;">영화제 종료일이 시작일보다 빠르게 작성되었습니다.</div></td>
                              </tr>
                              <tr height="50px">
                                 <td colspan="2">우편번호</td>
                                 <td><input type="text" name="address1" id="address1" size="7" readonly="readonly" class="form-control" value="${festivalinfo.address1 }"></td>
                              </tr>
                              <tr>
                                 <td colspan="3"><span id="postSearch" class="btn btn-default btn-rounded no-margin pull-right">우편번호 검색</span></td>
                              </tr>
                              <tr>
                                 <td colspan="3"><div id="address1Msg" style="color: red; display: none;">우편번호를 반드시 입력해주세요.</div></td>
                              </tr>
                              <tr height="50px">
                                 <td colspan="2">영화제 기본주소</td>
                                 <td><input type="text" name="address2" id="address2" size="50" class="form-control" value="${festivalinfo.address2 }"></td>
                              </tr>
                              <tr>
                                 <td colspan="3"><div id="address2Msg" style="color: red; display: none;">영화제 기본주소를 반드시 입력해주세요.</div></td>
                              </tr>
                              <tr height="50px">
                                 <td colspan="2">영화제 상세주소</td>
                                 <td><input type="text" name="address3" id="address3" size="50" class="form-control" value="${festivalinfo.address3 }"></td>
                              </tr>
                              <tr>
                                 <td colspan="3"><div id="address3Msg" style="color: red; display: none;">영화제 상세주소를 반드시 입력해주세요.</div></td>
                              </tr>
                              <tr height="50px">
                                 <td>주관</td>
                                 <td colspan="2"><input type="text" name="host" id="host" class="form-control" value="${festivalinfo.host }"></td>
                              </tr>
                              <tr>
                                 <td colspan="3"><div id="hostMsg" style="color: red; display: none;">영화제 주관을 반드시 입력해주세요.</div></td>
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
                  <button class="btn btn-default btn-rounded no-margin pull-right festival-register-submit" type="submit">
                     <span class="lnr lnr-pencil"></span>영화제 등록하기
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
                           <td colspan="2"><input type="text" name="subject" id="subject" class="form-control festival-form-control" value="${festivalinfo.subject }"></td>
                        </tr>
                        <tr class="info">
                           <td colspan="3"><div id="subjectMsg" style="color: red; display: none;">영화제 제목을 반드시 입력해주세요.</div></td>
                        </tr>
                        <tr class="info">
                           <td><p class="funding-register-fontsize">목표금액(원)</p></td>
                           <td colspan="2"><input type="text" name="target" id="target" class="form-control festival-form-control"  value=""></td>
                        </tr>
                        <tr class="info">
                           <td colspan="3"><div id="targetMsg" style="color: red; display: none;">펀딩 목표금액을 10,000원 ~ 100,000,000원 이내로만 반드시 입력해주세요.</div></td>
                        </tr>
                        <tr class="info">
                           <td><p class="funding-register-fontsize">펀딩 시작일</p></td>
                           <td colspan="2"><input type="date" name="fundingStart" id="fundingStart" class="form-control festival-form-control"></td>
                        </tr>
                        <tr class="info">
                           <td colspan="3"><div id="fundingStartEndDayMsg" style="color: red; display: none;">펀딩 종료일이 시작일보다 빠르게 작성되었습니다.</div></td>
                           <td colspan="3"><div id="fundingStartMsg" style="color: red; display: none;">펀딩 시작일을 반드시 입력해주세요.</div></td>
                        </tr>
                        <tr class="info">
                           <td><p class="funding-register-fontsize">펀딩 종료일</p></td>
                           <td colspan="2"><input type="date" name="fundingEnd" id="fundingEnd" class="form-control festival-form-control"></td>
                        </tr>
                        <tr class="info">
                        </tr>
                        <tr class="info">
                           <td colspan="3"><div id="fundingEndMsg" style="color: red; display: none;">펀딩 종료일을 반드시 입력해주세요.</div></td>
                        </tr>
                        <tr class="info">
                           <td><p class="funding-register-fontsize">영화제 사이트</p></td>
                           <td colspan="2"><input type="text" name="site" id="site" class="form-control festival-form-control"></td>
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
            <div class="col-sm-6 col-md-5 product-sidebar">
               <!-- 펀딩금액 옵션1 출력될 영역 -->
               <div class="funding-options">
               <div style="padding-left: 30px;">
                  <p>펀딩금액 옵션1</p>
                  <p>
                     <input type="text" name="option1" id="option1"> 원
                  </p>
               </div>
                  <div style="color: red; display: none;" id="option1Msg">10000~5000000 까지만 입력 가능합니다.</div>
               </div>
               <!-- /옵션1 -->
            </div>
            <p>&nbsp;</p>

            <div id="fundingOptionPlus">
               <div class="col-sm-6 col-md-5 product-sidebar">
                  <div class="funding-options" id="addOption" style="padding-left: 50%;">
                     <div>
                        <span class="lnr lnr-plus-circle" style="height: 100px; width: 100px;"></span>
                     </div>
                  </div>
               </div>
            </div>
            <!-- / project sidebar area -->
         </div>
         <!-- / row -->
      </form>
   </div>
   <!-- / container -->
</section>
<!-- / shop single-product -->

<!-- / content -->

<!-- JavaScript -->
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
   src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
   // + 버튼을 클릭할 때 펀딩 옵션 입력란이 하나씩 출력되도록 작성한 자바스크립트
   const fundingOptionPlus = document.getElementById("fundingOptionPlus");
   let optionCount = 2;
   
   fundingOptionPlus.addEventListener("click", (event) => {
         if (event.target.id === "addOption" && optionCount <= 4) {
             const optionadd = document.createElement("div");
             optionadd.className = "col-sm-6 col-md-5 product-sidebar";
             const optionContent = `
                   <div class="funding-options">
                   <div style="padding-left: 30px;">
                       <p>펀딩금액 옵션\${optionCount}</p>
                       <p><input type="text" name="option\${optionCount}" id="option\${optionCount}"> 원</p>
                   </div>
                   <div style="color: red; display: none;" id="option\${optionCount}Msg">10000~5000000 까지만 입력 가능합니다.</div>
                   </div>
                   <p>&nbsp;</p>    
               `;
             optionadd.innerHTML = optionContent;
             fundingOptionPlus.parentElement.insertBefore(optionadd, fundingOptionPlus);
             optionCount++;
             
           if(optionCount > 4) {
              fundingOptionPlus.style.display = "none";
           }
         }
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
   
$(document).ready(function() {
   //register form 태그 submit
   $("#register").submit(function() {
      //전송 결과 : true(전송), false(미전송)
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
           $("#option1").val("0");
           $("#option1Msg").css("display", "none");
       } else {
           if ($("#option1").val() != "0" && !optionPattern.test($("#option1").val())) {
               $("#option1Msg").css("display", "block");
               return false;
           } else {
               $("#option1Msg").css("display", "none");
           }
       }


      if (typeof($("#option2").val()) != "undefined") {
         if ($("#option2").val() !== "0" && !optionPattern.test($("#option2").val())) {
             $("#option2Msg").css("display", "block");
             return false;
         } else {
             $("#option2Msg").css("display", "none");
         }
     }

   
      if (typeof($("#option38").val()) != "undefined") {
         if ($("#option3").val() !== "0" && !optionPattern.test($("#option3").val())) {
             $("#option3Msg").css("display", "block");
             return false;
         } else {
             $("#option3Msg").css("display", "none");
         }
     }

   
      if (typeof($("#option4").val()) != "undefined") {
         if ($("#option4").val() !== "0" && !optionPattern.test($("#option4").val())) {
             $("#option4Msg").css("display", "block");
             return false;
         } else {
             $("#option4Msg").css("display", "none");
         }
     }

      return submitResult;
   });
});
   
</script>