<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 영화제 펀딩 정보 상세페이지 -->
<section id="shop">
   <div class="container">
      <div class="row">

         <!-- product content area -->
         <div class="col-sm-6 col-md-7 content-area">
            <div class="product-content-area">
               <!-- 영화제 포스터 이미지가 출력될 영역 -->
               <div id="product-slider" class="carousel slide"
                  data-ride="carousel">
                  <!-- wrapper for slides -->
                  <div class="carousel-inner" role="listbox">
                     <div class="item active">
                        <img src="<c:url value='/resources/upload/${festival.mainImg}' />" alt="">
                     </div>
                  </div>
                  <!-- / wrapper for slides -->
               </div>
               <!-- / product-slider -->

               <!-- 영화제 포스터 아래 영화제 정보, 영화제 출품작 정보, 기대평이 출력될 영역 -->
               <ul class="nav nav-tabs" role="tablist">
                  <li class="active"><a href="#festival" role="tab"
                     data-toggle="tab" aria-expanded="true">영화제 정보</a></li>
                  <li class=""><a href="#filminfo" role="tab" data-toggle="tab"
                     aria-expanded="true">영화제 출품작 및 감독</a></li>
                  <li class=""><a href="#reviews" role="tab" data-toggle="tab"
                     aria-expanded="true">후원자 기대평</a></li>
               </ul>
               <!-- / nav-tabs -->

               <div class="tab-content">
                  <!-- 영화제 정보 출력 -->
                  <div role="tabpanel" class="tab-pane animated fadeIn active"
                     id="festival">
                     <table>
                        <tbody>
                           <tr>
                           	<th colspan="2" class="funding-register-fontsize" style="color: black;">영화제 정보</th>
                           </tr>
                           <tr>
                              <td colspan="2"><img src="<c:url value='/resources/upload/${festival.subImg}' />" alt=""></td>
                           </tr>
                           <tr>
                              <td><span class="font-bold">영화제 기간 : </span><span>${festival.startDay} -
                                    ${festival.endDay}</span>
                              </td>
                           </tr>
                           <tr>
                              <td><span class="font-bold">영화제 개최 장소 : </span><span>${festival.address2}
                                    ${festival.address3}</span>
                              </td>
                           </tr>
                           <tr>
                              <td><span class="font-bold">영화제 주관처 : </span><span>${festival.host}</span>
                              </td>
                           </tr>
                        </tbody>
                     </table>
                  </div>
                  <!-- /festival -->
                  <p>&nbsp;</p>
                  <p class="nav nav-tabs" />
                  <p>&nbsp;</p>

                  <!-- 영화 정보 출력 -->
                  <div role="tabpanel" class="tab-pane animated fadeIn active"
                     id="filminfo">
                     <div class="row">
                        <h4 class="funding-register-fontsize">영화제 출품작 및 감독</h4>
                        <!-- 영화 이미지를 출력할 영역 -->

                        <c:forEach items="${filminfo}" var="film">
                           <table>
                              <tr>
                                 <td colspan="2"><img src="<c:url value='/resources/upload/${film.img}'/>" width="600" alt=""></td>
                              </tr>
                              <tr>
                                 <td colspan="2"><span class="font-bold">영화 제목</span> : ${film.title}</td>
                              </tr>
                              <tr>
                                 <td colspan="2"><span class="font-bold">영화 감독 : </span>${film.director}</td>
                              </tr>
                              <tr>
                                 <td colspan="2"><span class="font-bold">영화 줄거리 : </span>${film.story}</td>
                              </tr>
                              <tr>
                                 <td colspan="2"><span class="font-bold">영화 상영일 : </span>${film.day}</td>
                              </tr>
                              <tr>
                                 <td colspan="2"><span class="font-bold">영화 상영시간 : </span>${film.time}</td>
                              </tr>
                              <tr>
                                 <td><span class="font-bold">영화 티저영상</span></td>
                              </tr>
                              <tr>
                                 <td><iframe width="600" height="338" src="${film.video }" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe></td>
                              </tr>
                           </table>
                        </c:forEach>
                     </div>
                  </div>
                  <!-- /filminfo -->
                  <p>&nbsp;</p>
                  <p class="nav nav-tabs" />
                  <p>&nbsp;</p>

                  <!-- idx
                      festivalIdx
                      accountId
                      star
                     content
                     day
                     status
                   -->
                  <!-- 기대평 출력 -->
                  <div role="tabpanel" class="tab-pane animated fadeIn active"
                     id="reviews">
                     <div class="reviews">
                        <c:forEach items="${expectinfo}" var="expect">
                           <div class="review-content">
                              <h2 class="review-title no-margin">${expect.accountId}</h2>
                              <div class="review-stars">
                                 <c:choose>
                                    <c:when test="${expect.star == 5}">
                                       <span class="product-rating"><i class="fa fa-star"></i><i
                                          class="fa fa-star"></i><i class="fa fa-star"></i> <i
                                          class="fa fa-star"></i><i class="fa fa-star"></i></span>
                                    </c:when>
                                    <c:when test="${expect.star == 4}">
                                       <span class="product-rating"><i class="fa fa-star"></i><i
                                          class="fa fa-star"></i><i class="fa fa-star"></i> <i
                                          class="fa fa-star"></i></span>
                                    </c:when>
                                    <c:when test="${expect.star == 3}">
                                       <span class="product-rating"><i class="fa fa-star"></i><i
                                          class="fa fa-star"></i><i class="fa fa-star"></i></span>
                                    </c:when>
                                    <c:when test="${expect.star == 2}">
                                       <span class="product-rating"><i class="fa fa-star"></i><i
                                          class="fa fa-star"></i></span>
                                    </c:when>
                                    <c:when test="${expect.star == 1}">
                                       <span class="product-rating"><i class="fa fa-star"></i></span>
                                    </c:when>
                                    <c:otherwise>
                                       <p>유효하지 않은 별점입니다.</p>
                                    </c:otherwise>
                                 </c:choose>
                              </div>
                              <!-- / review-stars -->
                              <p>${expect.content }</p>
                              <cite>${expect.day}</cite>
                           </div>
                        </c:forEach>
                        <!-- / review-content -->

                        <div class="space-25">&nbsp;</div>

                        <!-- / review-content -->
                     </div>
                     <!-- / reviews -->
                  </div>
                  <!-- / reviews-tab -->
               </div>
               <!-- / tab-content -->
            </div>
            <!-- / product-content-area -->

            <!-- product pagination -->
            <div class="pagination no-padding">
               <a href="#x" class="btn btn-default btn-rounded no-margin"><i
                  class="fa fa-long-arrow-left"></i><span>Previous</span></a> <a
                  href="#x" class="btn btn-default btn-rounded no-margin pull-right"><span>Next</span><i
                  class="fa fa-long-arrow-right"></i></a>
            </div>
            <!-- / product pagination -->

         </div>
         <!-- / project-content-area -->

         <div class="col-sm-6 col-md-5 product-sidebar">
            <div class="product-details">
               <table class="product-info">
                  <tr>
                     <th colspan="4"><h4>${festival.subject }</h4></th>
                  </tr>
                  <tr>
                     <td colspan="3" style="text-align: center;"><span>모인금액</span></td>
                     <td><span class="info-size">${festival.collected}원</span></td>
                  </tr>
                  <tr>
                     <td colspan="3" style="text-align: center;"><span>후원 달성률(${achievementPercentage}%)</span></td>
                     <td>
                        <div class="progress-bar">
                           <c:choose>
                              <c:when test="${achievementPercentage >= 100}">
                                 <p>!!!펀딩 목표금액 달성!!!</p>
                                 <div class="progress-fill" id="progress-fill"  style="width: 100%"></div>
                              </c:when>
                              <c:otherwise>
                                 <div class="progress-fill" id="progress-fill" style="width: ${achievementPercentage}%"></div>
                              </c:otherwise>
                           </c:choose>
                        </div>
                     </td>
                  </tr>
                  <tr>
                     <c:choose>
                     <c:when test="${festival.sponsor == 0}">
                     <td colspan="3" style="text-align: center;"><span>후원자</span></td>
                     <td><span class="info-size">${festival.sponsor}</span></td>
                     </c:when>
                     <c:otherwise>
                     <td colspan="3" style="text-align: center;"><span>후원자</span></td>
                     <td><span class="info-size">${festival.sponsor}</span></td>
                      </c:otherwise>
                      </c:choose>
                  </tr>
                  <tr>
                     <td colspan="3" style="text-align: center;"><span>목표금액</span></td>
                     <td>${festival.target}원</td>
                  </tr>
                  <tr>
                     <td colspan="3" style="text-align: center;"><span>펀딩기간</span></td>
                     <td>${festival.fundingStart} 부터 ${festival.fundingEnd} 까지</td>
                  </tr>
                  <tr>
                     <td colspan="3" style="text-align: center;"><span>결제</span></td>
                     <td>${festival.fundingEnd} 2일 뒤에 결제진행예정</td>
                  </tr>
                  <tr>
                  <c:if test="${festival.site != ''}">
                     <td colspan="3" style="text-align: center;"><span>영화제사이트</span></td>
                     <td>${festival.site}</td>
                  </c:if>
                  </tr>

                  <tr>
                     <td colspan="4" class="info-buttons">
                     <br>
                      <c:choose>
                         <c:when test="${festival.state == 0 }">
                           <!-- "수락" 버튼 -->
                     <a href="javascript:void(0)" id="acceptFestival" class="page-scroll btn btn-rounded btn-donation">
                         <span class="donation-text">수락</span>
                     </a>
                     <!-- "거부" 버튼 -->
                     <a href="javascript:void(0)" id="rejectFestival" class="page-scroll btn btn-rounded btn-donation">
                         <span class="donation-text">거부</span>
                     </a>
                  </c:when>
                          <c:when test="${festival.state == 1 }">
                             <h3 style="color: blue">후원 예정 영화제입니다.</h3>
                          </c:when>
                          <c:when test="${festival.state == 2 }">
                             <h3 style="color: blue">후원 중인 영화제입니다.</h3>
                          </c:when>
                          <c:when test="${festival.state == 3 }">
                             <h3 style="color: blue">후원 종료된 영화제입니다.</h3>
                          </c:when>
                        <c:otherwise>
                             <h3 style="color: red">승인 거부된 영화제입니다.</h3>
                        </c:otherwise>
                      </c:choose>
                     </td>
                  </tr>
               </table>
               <!-- / info-buttons -->
            </div>
            <!-- product-details -->
         </div>
         <!-- / col-sm-4 col-md-3 -->

         <p>&nbsp;</p>

         <p>&nbsp;</p>
         <c:if test="${festival.option1 != 0}">
         <div class="col-sm-6 col-md-5 product-sidebar">
            <!-- 펀딩금액 옵션3 출력될 영역 -->
            <div class="funding-options">
               <p>펀딩금액 옵션1</p>
               <div>
                  <span>${festival.option1 } 원</span>
               </div>
            </div>
            <!-- /옵션1 -->
         </div>
         </c:if>
         <p>&nbsp;</p>
         <c:if test="${festival.option2 != 0}">
         <div class="col-sm-6 col-md-5 product-sidebar">
            <!-- 펀딩금액 옵션3 출력될 영역 -->
            <div class="funding-options">
               <p>펀딩금액 옵션2</p>
               <div>
                  <span>${festival.option2 } 원</span>
               </div>
            </div>
            <!-- /옵션2 -->
         </div>
         </c:if>
         <p>&nbsp;</p>
         <c:if test="${festival.option3 != 0}">
         <div class="col-sm-6 col-md-5 product-sidebar">
            <!-- 펀딩금액 옵션3 출력될 영역 -->
            <div class="funding-options">
               <p>펀딩금액 옵션3</p>
               <div>
                  <span>${festival.option3 } 원</span>
               </div>
            </div>
            <!-- /옵션3 -->
         </div>
         </c:if>
         <p>&nbsp;</p>
         <c:if test="${festival.option4 != 0}">
         <div class="col-sm-6 col-md-5 product-sidebar">
            <!-- 펀딩금액 옵션4 출력될 영역 -->
            <div class="funding-options">
               <p>펀딩금액 옵션4</p>
               <div>
                  <span>${festival.option4 } 원</span>
               </div>
            </div>
            <!-- /옵션4 -->
         </div>
         </c:if>
         <!-- / project sidebar area -->
      </div>
      <!-- / row -->
   </div>
   <!-- / container -->
</section>
<!-- / shop single-product -->

<!-- / content -->
<!-- JavaScript -->

<script type="text/javascript">
//CSRF 토큰 관련 정보를 자바스크립트 변수에 저장
var csrfHeaderName = "${_csrf.headerName}";
var csrfTokenValue = "${_csrf.token}";

// Ajax 기능을 사용하여 요청하는 모든 웹 프로그램에게 CSRF 토큰 전달 가능
// ▶ Ajax 요청 시 beforeSend 속성을 설정할 필요 없음
$(document).ajaxSend(function(e, xhr){
	xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
});

   var achievementPercentage = ${achievementPercentage}; // 클라이언트 측에서 서버에서 받은 값을 저장
   var progressBar = document.getElementById("progress-fill");
   progressBar.style.width = achievementPercentage + "%";
   
   var idx="${festival.idx}";
   
   $(document).ready(function() {
      $("#acceptFestival").click(function() {
         $.ajax({
             type: "PUT",
             url: "<c:url value="/admin/festival_accept"/>",
             contentType: "application/json",
             data: JSON.stringify({"idx":idx}),
             dataType: "text",
             success: function(result) {
                if(result == "success") {
                alert("영화제 승인 처리되었습니다.");   
                festivalDetail(idx);
                }
             },
             error: function(xhr) {
                alert("영화제 승인 중 오류가 발생하였습니다("+ xhr.status+")");
             }
          });
      });
      
      $("#rejectFestival").click(function() {
         $.ajax({
             type: "PUT",
             url: "<c:url value="/admin/festival_reject"/>",
             contentType: "application/json",
             data: JSON.stringify({"idx":idx}),
             dataType: "text",
             success: function(result) {
                if(result == "success") {
                alert("영화제 거부 처리되었습니다.");   
                festivalDetail(idx);
                
                }
             },
             error: function(xhr) {
                alert("영화제 승인 중 오류가 발생하였습니다("+ xhr.status+")");
             }
          });
      });
   });
</script>