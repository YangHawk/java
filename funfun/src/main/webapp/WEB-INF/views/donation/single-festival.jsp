<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<div id="preloader">
    <div class="spinner spinner-round"></div>
</div>

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
                        <img
                           src="<c:url value='/resources/upload/${festivalinfo.mainImg}' />"
                           alt="">
                     </div>
                  </div>
                  <!-- / wrapper for slides -->
               </div>
               <!-- / product-slider -->

               <!-- 영화제 포스터 아래 영화제 정보, 영화제 출품작 정보, 기대평이 출력될 영역 -->
               <ul class="nav nav-tabs" role="tablist">
                  <li class="active"><a href="#festivalinfo" role="tab"
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
                     id="festivalinfo">
                     <table>
                        <tbody>
                           <tr>
                              <c:choose>
                                 <c:when test="${(loginAccount.id == festivalinfo.accountId || loginAccount.status == 0) && festivalinfo.state != 4}">
                                    <th colspan="2" class="funding-register-fontsize">영화제 정보 
                                    <span class="pull-right"><a href="<c:url value='/donation/festival_update?idx=${festivalinfo.idx}' />"
                                          class="btn btn-sm btn-default btn-rounded no-margin"><i class="lnr lnr-pencil"></i><span>영화제 수정</span></a></span>
                                    <span class="pull-right" style="padding-right: 10px;">
                                    	<c:url var="filmRegisterUrl" value="/donation/film_register">
								        <c:param name="festivalIdx" value="${festivalinfo.idx}" />
									    </c:url>
									    <a href="${filmRegisterUrl}" class="btn btn-sm btn-default btn-rounded no-margin"><span class="lnr lnr-plus-circle"></span>영화 추가하기</a>
									</span>
                                    </th>
                                 </c:when>
                                 <c:otherwise>
                                    <th colspan="2" class="funding-register-fontsize" style="color: black;">영화제 정보</th>
                                 </c:otherwise>
                              </c:choose>
                           </tr>
                           <tr>
                              <td colspan="2"><img src="<c:url value='/resources/upload/${festivalinfo.subImg}' />" alt=""></td>
                           </tr>
                           <tr>
                              <td><span class="font-bold">영화제 기간 : </span><span>${festivalinfo.startDay} -
                                    ${festivalinfo.endDay}</span>
                              </td>
                           </tr>
                           <tr>
                              <td><span class="font-bold">영화제 개최 장소 : </span><span>${festivalinfo.address2}
                                    ${festivalinfo.address3}</span>
                              </td>
                           </tr>
                           <tr>
                              <td><span class="font-bold">영화제 주관처 : </span><span>${festivalinfo.host}</span>
                              </td>
                           </tr>
                        </tbody>
                     </table>
                  </div>
                  <!-- /festivalinfo -->
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
                           	<c:if test="${(loginAccount.id == festivalinfo.accountId || loginAccount.status == 0) and festivalinfo.state != 4}">
                           	  <tr>
                           	  	<td>
                           			<a href="<c:url value='/donation/film_update?idx=${film.idx}' />" class="btn btn-sm btn-default btn-rounded no-margin pull-right"><i class="lnr lnr-pencil"></i><span>영화 수정</span></a>
                           		</td>
                           		<td>
                           			<span class="pull-right" style="padding-right: 10px;">
									    <a href="<c:url value='/donation/film_remove?idx=${film.idx}' />" class="btn btn-sm btn-default btn-rounded no-margin">영화 삭제</a>
									</span>
                           		</td>
                           	  </tr>
                           	</c:if>
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
                           <p class="nav nav-tabs" />
                        </c:forEach>
                     </div>
                  </div>
                  <!-- /filminfo -->
                  <p>&nbsp;</p>
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
                        <!-- / review-content -->
                     <!-- / reviews -->
                  </div>
                  <!-- / reviews-tab -->
               </div>
               <!-- / tab-content -->
            </div>
            <!-- / product-content-area -->

			<div id="add-review" class="space-top-30">
			</div>
         

         </div>
         <!-- / project-content-area -->

         <div class="col-sm-6 col-md-5 product-sidebar">
            <div class="product-details">
               <table class="product-info">
                  <tr>
                     <th colspan="4"><h4>${festivalinfo.subject }</h4></th>
                  </tr>
                  <tr>
                     <td colspan="3" style="text-align: center;"><span>모인금액</span></td>
                     <td><span class="info-size">${festivalinfo.collected}원</span></td>
                  </tr>
                  <tr>
                     <td colspan="3" style="text-align: center;"><span>후원 달성률(${achievementPercentage}%)</span></td>
                     <td>
                        <div class="progress-bar">
                           <c:choose>
                              <c:when test="${achievementPercentage >= 100}">
                                 <p>!!!펀딩 목표금액 달성!!!</p>
                                 <div class="progress-fill" id="progress-fill"></div>
                              </c:when>
                              <c:otherwise>
                                 <div class="progress-fill" id="progress-fill"></div>
                              </c:otherwise>
                           </c:choose>
                        </div>
                     </td>
                  </tr>
                  <tr>
                     <c:choose>
                     <c:when test="${festivalinfo.sponsor == 0}">
                     <td colspan="3" style="text-align: center;"><span>후원자</span></td>
                     <td><span class="info-size">${festivalinfo.sponsor}</span></td>
                     </c:when>
                     <c:otherwise>
                     <td colspan="3" style="text-align: center;"><span>후원자</span></td>
                     <td><span class="info-size">${festivalinfo.sponsor}</span></td>
                  	 </c:otherwise>
                  	 </c:choose>
                  </tr>
                  <tr>
                     <td colspan="3" style="text-align: center;"><span>목표금액</span></td>
                     <td>${festivalinfo.target}원</td>
                  </tr>
                  <tr>
                     <td colspan="3" style="text-align: center;"><span>펀딩기간</span></td>
                     <td>${festivalinfo.fundingStart} 부터 ${festivalinfo.fundingEnd} 까지</td>
                  </tr>
                  <tr>
                     <td colspan="3" style="text-align: center;"><span>결제</span></td>
                     <td>${festivalinfo.fundingEnd}로부터 2일 뒤 결제진행예정</td>
                  </tr> 
                  <tr>
                  <c:if test="${festivalinfo.site != ''}">
                     <td colspan="3" style="text-align: center;"><span>영화제사이트</span></td>
                     <td>${festivalinfo.site}</td>
                  </c:if>
                  </tr>

                  <tr>
                     <td colspan="4" class="info-buttons">
                        <a id="wishA"></a>
                           
                           <a id="kakaotalk-sharing-btn" class="btn btn-rounded" href="javascript:kakaoShare();">
	  						<img src="https://developers.kakao.com/assets/img/about/logos/kakaotalksharing/kakaotalk_sharing_btn_medium.png" width="35px" height="35px" alt="카카오톡 공유 보내기 버튼" />
							</a>
                           
                        <c:choose>
                        	<c:when test="${festivalinfo.state == 1}">
 		                       <a class="btn btn-rounded btn-donation"><span class="donation-text">${festivalinfo.fundingStart } 진행 예정</span></a>
                        	</c:when>
                        	<c:when test="${festivalinfo.state == 2 }">
        		                <a href="#donationForm" class="page-scroll btn btn-rounded btn-donation"><span class="donation-text">이 영화제 후원하기</span></a>
                        	</c:when>
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

         <!-- 펀딩금액 사용자가 금액을 설정할 수 있는 입력란이 출력될 영역 -->
         <form id="donationForm" action="pay" method="POST">
         	<input type="hidden" name="festivalIdx" value="${festivalinfo.idx}">
    	 	<!-- 직접 입력한 금액 -->
    		<div class="col-sm-6 col-md-5 product-sidebar" style="overflow:scroll; height: 500px;">
        	<div class="funding-options">
            	<input type="radio" name="money" id="customMoney">
            	<div style="padding-left: 30px;">
            	<p>직접 금액 입력</p>
            	<input type="text" id="custom" name="custommoney" size="35%" height="40" placeholder="*10000 ~ 5000000원까지 입력 가능합니다." class="form-control" style="display: none;">
            	<p></p>
            	<div id="custommoneyMsg" style="color: red; display: none;">10000 ~ 5000000원의 범위 안에서 입력해주세요.</div>
            	</div>
            	<div style="padding-left: 300px;">
            	<button type="submit" id="customMoneyBtn" name="moneyBtn" style="display: none; background:#BFBFBF;" class="btn btn-rounded">후원하기</button>
        		</div>
        	</div>
        	<!-- /사용자가 금액설정 -->

 		   <p>&nbsp;</p>

		    <!-- 옵션1 -->
		    <c:if test="${festivalinfo.option1 != 0}">
            <div class="funding-options">
                <input type="radio" name="money" value="${festivalinfo.option1}" id="option1">
               <div style="padding-left: 30px;">
                <p>펀딩금액 옵션1</p>
                <div>
                    <span>${festivalinfo.option1} 원</span>
                </div>
                </div>
               <div style="padding-left: 300px;">
                <button type="submit" name="moneyBtn" style="display: none; background:#BFBFBF;" class="btn btn-rounded" data-option="option1">후원하기</button>
            	</div>
            	</div>
            	<!-- /옵션1 -->
    		</c:if>

    		<p>&nbsp;</p>

		    <!-- 옵션2 -->
    		<c:if test="${festivalinfo.option2 != 0}">
            	<div class="funding-options">
                <input type="radio" name="money" value="${festivalinfo.option2}" id="option2">
                <div style="padding-left: 30px;">
                <p>펀딩금액 옵션2</p>
                <div>
                    <span>${festivalinfo.option2} 원</span>
                </div>
                </div>
                <div style="padding-left: 300px;">
                <button type="submit" name="moneyBtn" style="display: none; background:#BFBFBF;" class="btn btn-rounded" data-option="option2">후원하기</button>
            	</div>
            	</div>
            	<!-- /옵션2 -->
    		</c:if>
    		
    		<p>&nbsp;</p>

    		<!-- 옵션3 -->
    		<c:if test="${festivalinfo.option3 != 0}">
            	<div class="funding-options">
                <input type="radio" name="money" value="${festivalinfo.option3}" id="option3">
                <div style="padding-left: 30px;">
                <p>펀딩금액 옵션3</p>
                <div>
                    <span>${festivalinfo.option3} 원</span>
                </div>
                </div>
                <div style="padding-left: 300px;">
                <button type="submit" name="moneyBtn" style="display: none; background:#BFBFBF;" class="btn btn-rounded" data-option="option3">후원하기</button>
            	</div>
            	</div>
            <!-- /옵션3 -->
    		</c:if>
    		
    		<p>&nbsp;</p>

    		<!-- 옵션4 -->
    		<c:if test="${festivalinfo.option4 != 0}">
            	<div class="funding-options">
                <input type="radio" name="money" value="${festivalinfo.option4}" id="option4">
                <div style="padding-left: 30px;">
                <p>펀딩금액 옵션4</p>
                <div>
                    <span>${festivalinfo.option4} 원</span>
                </div>
                </div>
                <div style="padding-left: 300px;">
                <button type="submit" name="moneyBtn" style="display: none; background:#BFBFBF;" class="btn btn-rounded" data-option="option4">후원하기</button>
            	</div>
            	</div>
            	<!-- /옵션4 -->
    		</c:if>
    		</div>
		</form>
      </div>
      <!-- / row -->

      
      <!-- / related-products -->
   </div>
   <!-- / container -->
</section>
<!-- / shop single-product -->

<!-- / content -->
<!-- JavaScript -->
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.easing.min.js"></script>

<!-- preloader -->
<script src="${pageContext.request.contextPath}/resources/js/preloader.js"></script>
<!-- / preloader -->

<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
	
<script type="text/javascript">
var achievementPercentage = ${achievementPercentage}; // 클라이언트 측에서 서버에서 받은 값을 저장
var progressBar = document.getElementById("progress-fill");
if(achievementPercentage >= 100){
	progressBar.style.width = "100%";	
}else{
	progressBar.style.width = achievementPercentage + "%";
}
var festivalIdx = "${param.idx}";
var wishList ='';

//AJAX 요청으로 Wish 목록을 가져온 다음 하트 버튼을 출력하는 함수
function myWishDisplay() {
    $.ajax({
        type: "GET",
        url: "<c:url value='/donation/wish_list'/>", // 실제 URL에 맞게 변경
        contentType: "application/json",
        dataType: "json",
        success: function (data) {
            wishList = data;
            
        	$("#wishA").empty();
        	
            var wishA = $("#wishA");
                  
            var wishBtn = $("<a>").attr('href', 'javascript:javascript:void(0)').addClass('view-btn');
            
            if (wishListHasFestivalIdx(festivalIdx)) {
                heartImg = $("<img>").attr("src", '<c:url value="/resources/images/heart.png" />').attr("width", "50px").attr("height", "50px");
            } else {
                heartImg = $("<img>").attr("src", '<c:url value="/resources/images/empty_heart.png" />').attr("width", "50px").attr("height", "50px");
            }
            
            wishBtn.append(heartImg);
            
            wishA.append(wishBtn);
            
            if (wishListHasFestivalIdx(festivalIdx)) {
            	wishBtn.attr("id", "heart_"+festivalIdx).attr("data-festival-idx", festivalIdx);
            } else {
            	wishBtn.attr("id", "emptyHeart_"+festivalIdx).attr("data-festival-idx", festivalIdx);
            }
            
        },
        error: function (xhr, status, error) {
            console.error("오류: " + error);
        }
    });
}

// 위시 리스트가 안에 있나요?
function wishListHasFestivalIdx(festivalIdx) {
    if (wishList.length === 0) {
        return false;
    }

    for (var i = 0; i < wishList.length; i++) {
        var wish = wishList[i];

        // 추출한 숫자와 festivalIdx 비교
        if (wish.festivalIdx == festivalIdx) {
            return true;
        }
    }
    return false;
}

//기대평 입력란을 화면에 출력하기 위한 함수
function displayexpectForm(){
	$.ajax({
		type: "get",
	    url: "<c:url value='/expect_form/'/>"+festivalIdx,
	    dataType: "json",
	    success: function (result) {
			var addReviewformDiv = $("#add-review");
			addReviewformDiv.empty();
				
			if(result.donationinfo != null && result.expectAccountId == null){
				var row = `<h4>리뷰작성하기</h4>
		            <div class="row">
		            <div class="col-sm-4 review-form">
		               <select class="form-control" id="reviewstar">
		                  <option value="5">5 Stars</option>
		                  <option value="4">4 Stars</option>
		                  <option value="3">3 Stars</option>
		                  <option value="2">2 Stars</option>
		                  <option value="1">1 Star</option>
		               </select>
		               <div id="reviewstarMsg" style="color: red; display: none;">기대되는 만큼 별을 반드시 선택해주세요</div>
		            </div>
		            <div class="col-sm-12 review-form">
		               <textarea id="reviewcontent" rows="7" class="form-control" placeholder="*리뷰를 작성해주세요" required></textarea>
		               <div id="reviewcontentMsg" style="color: red; display: none;">리뷰를 반드시 작성해주세요</div>
		               <button type="button" id="addReviewBtn" class="btn btn-submit btn-primary-filled btn-rounded">작성하기</button>
		            </div>
		         </div>`;
		         addReviewformDiv.append(row);
			}
	    },
		error: function (xhr) {
		 	 alert("에러 = " + xhr.status);
		},
	});
}
	


//기대평을 가져와 화면에 출력하기 위한 함수
function expectDisplay() {
  $.ajax({
      type: "get",
      url: "<c:url value='/expect_list/'/>" + festivalIdx,
      dataType: "json",
      success: function (result) {
    	  var reviewDiv = $("#reviews");
          reviewDiv.empty();

          for (var i = 0; i < result.length; i++) {
        	  var expect = result[i];
        	  var starRating = "";
        	  switch (expect.star) {
        	    case 5:
        	      starRating = "<span class='product-rating'><i class='fa fa-star'></i><i class='fa fa-star'></i><i class='fa fa-star'></i><i class='fa fa-star'></i><i class='fa fa-star'></i></span>";
        	      break;
        	    case 4:
        	      starRating = "<span class='product-rating'><i class='fa fa-star'></i><i class='fa fa-star'></i><i class='fa fa-star'></i><i class='fa fa-star'></i></span>";
        	      break;
        	    case 3:
        	      starRating = "<span class='product-rating'><i class='fa fa-star'></i><i class='fa fa-star'></i><i class='fa fa-star'></i></span>";
        	      break;
        	    case 2:
        	      starRating = "<span class='product-rating'><i class='fa fa-star'></i><i class='fa fa-star'></i></span>";
        	      break;
        	    case 1:
        	      starRating = "<span class='product-rating'><i class='fa fa-star'></i></span>";
        	      break;
        	    default:
        	      starRating = "<p>유효하지 않은 별점입니다.</p>";
        	  }
        	  var review = "<div class='review-content'>" +
              "<h2 class='review-title no-margin'>" + expect.accountId + "</h2>" +
              "<div class='review-stars'>" +
              starRating +
              "</div>" +
              "<p>" + expect.content + "</p>" +
              "<cite>" + expect.day + "</cite>";

            if (expect.accountId == "${loginAccount.id}") {
              review += "<span class='pull-right' style='padding-right: 10px;'>" +
                "<a class='btn btn-sm btn-default btn-rounded no-margin removeReview' data-idx='" + expect.idx + "'>삭제</a>" +
                "</span>";
            }

            review += "</div><p>&nbsp;</p>";        	      
              
          reviewDiv.append(review);
      	}
      },
      error: function (xhr) {
          alert("expectDisplay 에러 = " + xhr.status);
      },
  });
}


//선택한 라디오버튼에 따라 value를 다르게 하여 [후원하기] 전송폼이 생성되도록 작성
//DOMContentLoaded : 페이지가 완전히 로드되기 전에 자바스크립트 코드가 실행되는 것을 방지
document.addEventListener('DOMContentLoaded', function () {
	//document.querySelectorAll() : NodeList 객체를 반환
    var radioButtons = document.querySelectorAll('input[type="radio"]');
    var buttons = document.querySelectorAll('button[name="moneyBtn"]');
    var customButton = document.getElementById("customMoneyBtn");
    var input = document.querySelector('input[name="custommoney"]');

    for (var i = 0; i < radioButtons.length; i++) {
    	//addEventListener('change', callback) : input 요소의 내용이 변경되거나 select 요소에 새로운 옵션이 선택되었을 때 
	    //발생하는 change 이벤트를 감지하여 이벤트가 발생되면 등록된 콜백함수가 실행됨
        radioButtons[i].addEventListener('change', function () {
            var selectedOptionId = this.id;

            //라디오 버튼이 선택되기 전 입력란 및 [후원하기]버튼 미출력
            input.style.display = 'none';
            customButton.style.display = 'none';
            for (var j = 0; j < buttons.length; j++) {
                buttons[j].style.display = 'none';
            }

            // 선택된 라디오 버튼에 맞는 입력란 및 [후원하기]버튼 출력
            if (selectedOptionId == "customMoney") {
                input.style.display = 'block';
                customButton.style.display = 'block';
            } else {
                var correspondingButton = document.querySelector('button[name="moneyBtn"][data-option="' + selectedOptionId + '"]');
                if (correspondingButton) {
                    correspondingButton.style.display = 'block';
                }
            }
        });
    }
});

$(document).ready(function() {
	displayexpectForm();
	expectDisplay();
	myWishDisplay();

	 //기대평 삭제하기
	 $(document).on("click",".removeReview", function() {
		 var idx = $(this).data("idx");
			if (confirm("정말로 삭제하시겠습니까?")) {
				$.ajax({
					type: "get",
					url: "${pageContext.request.contextPath}/expect_remove/" + idx,
					data: { "idx": idx },
					contentType: "application/json",
					dataType: "text",
					success: function (result) {
						alert("기대평이 삭제되었습니다.");
						expectDisplay();
						displayexpectForm();
					},
					error: function (xhr) {
						alert("에러코드(게시글 삽입) = " + xhr.status);
					},
				});
			}
	});

	// [리뷰작성] 버튼 클릭 이벤트
	$(document).on("click","#addReviewBtn", function() {
	    var star = $("#reviewstar option:selected").val();
	    var content = $("#reviewcontent").val();
	
	    if (star == "") {
	        $("#reviewstarMsg").css("display", "block");
	    }
	    if (content == "") {
	        $("#reviewcontentMsg").css("display", "block");
	    }
	
	    $.ajax({
	        type: "post",
	        url: "<c:url value='/expect_add'/>",
	        contentType: "application/json",
	        data: JSON.stringify({ "star": star, "content": content, "accountId": "${loginAccount.id}", "festivalIdx": festivalIdx }),
	        dataType: "text",
	        success: function (result) {
	        	if(result == "success") {
	                //$("#add-review").hide();
	                expectDisplay();
					displayexpectForm();
	        	}
	        },
	        error: function (xhr) {
	        	console.log(result);
	            alert("에러코드(게시글 삽입) = " + xhr.status); 
	        },
	    });
	});
	
	//부모 요소에 이벤트 리스너 추가
	$("#wishA").on("click", "[id^='emptyHeart_']", function() {
	    var festivalIdx = $(this).data("festival-idx"); // data-festival-idx 속성을 통해 festivalIdx 값을 읽어옴
	
	    $.ajax({
	        type: "PUT",
	        url: "<c:url value='/donation/wish_add'/>",
	        contentType: "application/json",
	        data: JSON.stringify({"accountId": "${loginAccount.id}", "festivalIdx": festivalIdx }),
	        dataType: "text",
	        success: function(result) {
	            if(result == "success") {
	            	myWishDisplay();
	            }
	        },
	        error: function(xhr) {
	            alert("찜하기 중 오류가 발생하였습니다("+ xhr.status+")");
	        }
	    });
	});
	
	// 부모 요소에 이벤트 리스너 추가
	$("#wishA").on("click", "[id^='heart_']", function() {
	    var festivalIdx = $(this).data("festival-idx"); // data-festival-idx 속성을 통해 festivalIdx 값을 읽어옴
	
	    $.ajax({
	        type: "DELETE",
	        url: "<c:url value='/donation/wish_remove'/>",
	        contentType: "application/json",
	        data: JSON.stringify({"accountId": "${loginAccount.id}", "festivalIdx": festivalIdx }),
	        dataType: "text",
	        success: function(result) {
	            if(result == "success") {
	            	myWishDisplay();
	            }
	        },
	        error: function(xhr) {
	            alert("찜 취소 중 오류가 발생하였습니다("+ xhr.status+")");
	        }
	    });
	    
	});
	
	//[후원하기] submit 하기 전 입력값 검증 - 미구현 : 비로그인 회원이 /pay 경로 접속 시 로그인 페이지로 전환할것(Interceptor)
	$("#donationForm").submit(function(event) {
    var result = true;
    var state = ${festivalinfo.state};
    var customMoneyValue = $("#custom").val();
	var moneyPattern = /^(?:10000|[1-9]\d{4,6}|5000000)$/;
    
    if(state != 2){
    	alert("진행 예정 영화제에는 아직 후원이 불가능합니다.");
    	result = false;
    }  
    
    if ($('#customMoney').is(':checked')) {
    	$('#customMoney').attr('value', customMoneyValue);
    }
    
    if (customMoneyValue!="" && !moneyPattern.test(customMoneyValue) && !$('#customMoney').is(':checked')) {
        $("#custommoneyMsg").css("display", 'block');
    	result = false;
    }
    if(customMoneyValue == "" && $('#customMoney').is(':checked')){
    	$("#custommoneyMsg").css("display", 'block');
    	result = false;
    }
   
    return result;
});
	
Kakao.init('dbd84c3979d7583576ee37c4e94c93e7'); // 사용하려는 앱의 JavaScript 키 입력
//카카오 공유 API
	Kakao.Share.createDefaultButton({
		container: '#kakaotalk-sharing-btn',
		objectType: 'feed',
	    content: {
	      title: '${festivalinfo.subject}',
	      description: '${festivalinfo.subject} 펀딩하기',
	      imageUrl:
	        '${pageContext.request.contextPath}/resources/upload/${festivalinfo.mainImg}',
	      link: {
	        // [내 애플리케이션] > [플랫폼] 에서 등록한 사이트 도메인과 일치해야 함
	        mobileWebUrl: 'http://itwill.xyz',
	        webUrl: 'http://itwill.xyz',
	      },
	    },
	    buttons: [
	      {
	        title: '웹으로 보기',
	        link: {
	          mobileWebUrl: 'http://itwill.xyz/funfun/donation/single-festival?idx=${festivalinfo.idx}',
	          webUrl: 'http://itwill.xyz/funfun/donation/single-festival?idx=${festivalinfo.idx}',
	        },
	      },
	    ],
	 	// 카카오톡 미설치 시 카카오톡 설치 경로이동
	    installTalk: true,
	});
});	   
</script>
