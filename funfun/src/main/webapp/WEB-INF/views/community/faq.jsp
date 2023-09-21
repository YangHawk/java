<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
.col-sm-5 {
   width: 430px;
}
.space-left{
   margin-right: 200px;
}
body {
  min-height: 100vh; /* 최소한의 높이를 창의 높이(100vh)로 유지 */
}
</style>

</head>

<body>

<!-- preloader -->
<div id="preloader">
    <div class="spinner spinner-round"></div>
</div>
<!-- / preloader -->


<div id="page-header" class="faq">
   <div class="container">
       <div class="page-header-content text-center">
           <div class="page-header wsub">
               <h1 class="page-title fadeInDown animated first">FAQ</h1>
           </div><!-- / page-header -->
           <p class="slide-text fadeInUp animated second">Frequently Asked Questions<br>(자주 묻는 질문)</p>
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

<section id="about">
    <div class="container">
       <div class="text-center"><h3 class="space-left space-bottom-2x">자주 묻는 질문</h3></div>
        <div class="row">
        
            <div class="col-sm-5 ">
                <div class="panel-group tabbed">
                  <div class="panel">
                       <div class="panel-heading faq-panel-heading" >
                           <a class="panel-title collapsed" data-toggle="collapse" href="#panel1">회원가입은 어떻게 하나요?</a>
                       </div>
                       <div id="panel1" class="panel-collapse collapse">
                           <div class="panel-body text-gray">
                            우측 상단 회원가입 버튼을 이용해 주시면 됩니다.
                           </div>
                       </div>
                    </div><!-- / panel -->
                </div><!-- / panel-group -->

                <div class="panel-group tabbed">
                    <div class="panel">
                       <div class="panel-heading faq-panel-heading">
                           <a class="panel-title collapsed" data-toggle="collapse" href="#panel2">아이디or비밀번호를 분실했는데 어떻게 하나요?</a>
                       </div>
                       <div id="panel2" class="panel-collapse collapse">
                           <div class="panel-body text-gray">
                           로그인 페이지 하단의 아이디 및 비밀번호 찾기를 클릭하시면 됩니다.
                           </div>
                       </div>
                    </div><!-- / panel -->
                </div><!-- / panel-group -->

                <div class="panel-group tabbed">
                    <div class="panel">
                       <div class="panel-heading faq-panel-heading">
                           <a class="panel-title collapsed" data-toggle="collapse" href="#panel3">영화제가 뭔가요?</a>
                       </div>
                       <div id="panel3" class="panel-collapse collapse">
                           <div class="panel-body text-gray">
                           영화제는 영화를 상영하는 행사를 말합니다.
                           </div>
                       </div>
                    </div><!-- / panel -->
                </div><!-- / panel-group -->

                <div class="panel-group tabbed">
                    <div class="panel">
                       <div class="panel-heading faq-panel-heading">
                           <a class="panel-title collapsed" data-toggle="collapse" href="#panel4">'♡' 버튼은 뭔가요?</a>
                       </div>
                       <div id="panel4" class="panel-collapse collapse">
                           <div class="panel-body text-gray">
                            관심 있는 영화제의 '♡' 버튼을 클릭하면 우측 상단의 위시리스트에서 확인이 가능합니다.
                           </div>
                       </div>
                    </div><!-- / panel -->
                </div><!-- / panel-group -->

                <div class="panel-group tabbed">
                    <div class="panel">
                       <div class="panel-heading faq-panel-heading">
                           <a class="panel-title collapsed" data-toggle="collapse" href="#panel5">비회원도 후원 가능한가요?</a>
                       </div>
                       <div id="panel5" class="panel-collapse collapse">
                           <div class="panel-body text-gray">
                            비회원이실 경우 후원이 불가합니다. 간단한 회원가입 과정을 거치신 뒤 후원 부탁드립니다.
                           </div>
                       </div>
                    </div><!-- / panel -->
                </div><!-- / panel-group -->
            </div><!-- / accordions-left -->

            <div class="col-sm-5">

                <div class="panel-group tabbed">
                    <div class="panel">
                       <div class="panel-heading faq-panel-heading">
                           <a class="panel-title collapsed" data-toggle="collapse" href="#panel6">펀딩 신청은 어떻게 하나요?</a>
                       </div>
                       <div id="panel6" class="panel-collapse collapse">
                           <div class="panel-body text-gray">
                            진행중인 영화제 페이지에서 영화제의 정보, 출품작 및 감독 정보 등을 확인할 수 있습니다.
                            후원을 원하시는 영화제 상세 페이지의 '이 영화제 후원하기' 버튼을 누르시면 됩니다.
                           </div>
                       </div>
                    </div><!-- / panel -->
                </div><!-- / panel-group -->

                <div class="panel-group tabbed">
                    <div class="panel">
                       <div class="panel-heading faq-panel-heading">
                           <a class="panel-title collapsed" data-toggle="collapse" href="#panel7">펀딩 신청 후 취소할 수 있나요?</a>
                       </div>
                       <div id="panel7" class="panel-collapse collapse">
                     <div class="panel-body text-gray">
                            펀딩이 완료되기 전 취소하실 수 있으며, 환불은 다음과 같은 방법으로 진행됩니다.
                            <br><br>
                            •신용카드 결제의 경우
                      일반적으로 당사의 취소 처리가 완료되고 4~5일 후 카드사의 취소가 확인됩니다. (체크카드 동일)<br>
                      펀딩 취소 시점과 해당 카드사의 환불 처리기준에 따라 취소금액의 환급방법과 환급일은 다소 차이가 있을 수 있습니다.<br><br>
                           </div>
                       </div>
                    </div><!-- / panel -->
                </div><!-- / panel-group -->

                <div class="panel-group tabbed">
                    <div class="panel">
                       <div class="panel-heading faq-panel-heading">
                           <a class="panel-title collapsed" data-toggle="collapse" href="#panel8">참여한 펀딩은 어떻게 확인하나요?</a>
                       </div>
                       <div id="panel8" class="panel-collapse collapse">
                           <div class="panel-body text-gray">
                            우측 상단의 '마이페이지'에서 후원 내역을 확인할 수 있습니다.
                           </div>
                       </div>
                    </div><!-- / panel -->
                </div><!-- / panel-group -->

                <div class="panel-group tabbed">
                    <div class="panel">
                       <div class="panel-heading faq-panel-heading">
                           <a class="panel-title collapsed" data-toggle="collapse" href="#panel9">회원 탈퇴는 어떻게 하나요?</a>
                       </div>
                       <div id="panel9" class="panel-collapse collapse">
                           <div class="panel-body text-gray">
                            우측 상단 마이페이지 → 제일 하단에 보이는 '회원 탈퇴' 버튼을 누르면 탈퇴가 가능합니다.
                           </div>
                       </div>
                    </div><!-- / panel -->
                </div><!-- / panel-group -->

                <div class="panel-group tabbed">
                    <div class="panel">
                       <div class="panel-heading faq-panel-heading">
                           <a class="panel-title collapsed" data-toggle="collapse" href="#panel10">회원 정보 변경은 어떻게 하나요?</a>
                       </div>
                       <div id="panel10" class="panel-collapse collapse">
                           <div class="panel-body text-gray">
                            우측 상단 마이페이지에서 전화번호, 이메일, 주소 등의 정보를 수정할 수 있습니다.
                           </div>
                       </div>
                    </div><!-- / panel -->
                </div><!-- / panel-group -->

            </div><!-- col-sm-5 -->
        </div><!-- / row -->
    </div><!-- / container -->
</section>
<!-- / about -->

<!-- / content -->

<!-- javascript -->
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.easing.min.js"></script>

<!-- sliders -->
<script src="js/owl.carousel.min.js"></script>

<!-- / sliders -->

<!-- preloader -->
<script src="${pageContext.request.contextPath}/resources/js/preloader.js"></script>
<!-- / preloader -->

<!-- / javascript -->
</body>

</html>