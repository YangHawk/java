<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<sec:authentication property="principal" var="pinfo"/>
<body>
<!-- preloader -->
<div id="preloader">
    <div class="spinner spinner-round"></div>
</div>
<!-- / preloader -->

 <div id="page-header" class="my-account">
        <div class="container">
            <div class="page-header-content text-center">
                <div class="page-header wsub">
                    <h1 class="page-title fadeInDown animated first">마이페이지</h1>
                </div><!-- / page-header -->
                <p class="slide-text fadeInUp animated second">${pinfo.id }[${pinfo.name}] 님의 정보입니다.</p>
            </div><!-- / page-header-content -->
        </div><!-- / container -->
    </div><!-- / page-header -->
<!-- content -->

<!-- my-account -->
<section id="my-account">
    <div class="container">
        <div class="row">

            <div class="col-sm-2 account-sidebar">
                <p><a href="#my-personal" class="page-scroll">사용자 정보</a></p>
                <p><a href="#my-donations" class="page-scroll">후원 내역</a></p>
                <p><a href="#my-orders" class="page-scroll">QnA 내역</a></p>
                <p><a href="#my-wishs" class="page-scroll">위시리스트</a></p>
                <p><a href="#changepw" class="page-scroll">비밀번호 변경</a></p>
            </div><!-- / account-sidebar -->


         <!-- 사용자 정보 -->
            <div class="col-sm-10 account-info">
                <div id="my-personal" class="account-info-content">
                    <h4>사용자 정보 <span class="pull-right"><button id="modify-account" type="button" class="btn btn-sm btn-default btn-rounded no-margin"><i class="lnr lnr-pencil"></i><span>편집</span></button></span></h4>
                    <div class="row">

                        <div class="col-xs-6 col-sm-8 col-md-10">
                            <p>이 름: <span>${pinfo.name}</span></p>
                            <p>전화번호: <span>${pinfo.phone }</span></p>
                            <p>생년월일: <span>${fn:substring(pinfo.birth, 0, 4) }년 ${fn:substring(pinfo.birth, 5, 7) }월 ${fn:substring(pinfo.birth, 8, 10) }일</span></p>
                            <c:choose>
                               <c:when test="${pinfo.gender == 0 }">
                                  <p>성별: <span> 남자 </span></p>
                               </c:when>
                               <c:otherwise>
                                  <p>성별: <span> 여자 </span></p>
                               </c:otherwise>
                            </c:choose>
                            <p>이메일: <span>${pinfo.email }</span></p>
                            <p>주소: <span>${pinfo.address1 } ${pinfo.address2 } ${pinfo.address3 }</span></p>
                        </div>

                    </div><!-- / row -->
                    
                    <div id="edit-form" class="edit-form" style="display: none;">
                       <br>
                       <h3>수정하세요</h3>
                       <p> 이름 </p>
                       <input id="name" name="name" value="${pinfo.name }" style="min-height: 35px; width:150px; font-size:15px;">
                       <div id="nameError" class="text-danger" style="display: none;">
                        이름은 한글로 6자리까지 입력가능합니다.
                     </div>
                       <hr>
                       <p> 전화번호 </p>
                       <input id="phone" name="phone" value="${pinfo.phone }" placeholder="010________"
                     style="min-height: 35px; width:150px; font-size:15px;">
                  <div id="phoneError" class="text-danger" style="display: none;">
                     전화번호는 숫자로만 입력해주세요.
                  </div>
                       <hr>
                       <p> 이메일 </p>
                       <input id="email" name="email" value="${pinfo.email }" style="min-height: 35px; width:300px; font-size:15px;">
                       <!-- 이메일 중복체크 버튼 -->
						        <button type="button" id="check-email-button" class="btn btn-primary">이메일 중복체크</button>
                       <div id="emailError" class="text-danger" style="display: none;">
                     				이메일은 @를 포함하여 입력해주세요.
	                  </div>
	                  <div id="emailDuplicationMessage" class="text-danger" style="display: none;">
							        이메일 중복체크를 실행해주세요.
						    </div>
                       <hr>
                       <label>성별</label>
                     <div>
                     <br>
                     <label class="radio-inline" style="width: 30%">
                        <input type="radio" name="gender" value="0" ${pinfo.gender == 0 ? 'checked' : ''}>남자            
                     </label>
                     <label class="radio-inline" style="width: 30%">
                        <input type="radio" name="gender" value="1" ${pinfo.gender == 1 ? 'checked' : ''}>여자
                     </label>
                     <div id="genderError" class="text-danger" style="display: none;">
                        성별을 선택해주세요.
                     </div>
                  </div>
                  <br><br>
                  <p> 생년월일 </p>                       
                       <input type="date" class="form-control" id="birth" name="birth" value="${fn:substring(pinfo.birth, 0, 10) }" style="width: 40%">
                       <div id="birthError" class="text-danger" style="display: none;">
                        생일을 입력해주세요.
                  </div>
                       <hr>
                       <p> 주소 </p>
                       <input id="address1" name="address1" value="${pinfo.address1 }" style="min-height: 35px; width:150px; font-size:15px;">
                       &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                       <button type="button" id="search-address-button" class="btn btn-primary">주소 검색</button>
                       <br>
                       <input id="address2" name="address2" value="${pinfo.address2 }" style="min-height: 35px; width:300px; font-size:15px;">
                       <br>
                       <input id="address3" name="address3" value="${pinfo.address3 }" style="min-height: 35px; width:300px; font-size:15px;">
                       <div id="addressError" class="text-danger" style="display: none;">
                     주소를 검색하여 입력해주세요.
                  </div>
                       <br>
                       <hr>
                       <!-- 수정 버튼 -->
                       <button id="modifyBtn" type="submit">저장</button>
                       <button id="cancelModifyBtn" type="button">취소</button>
                      </div>
                </div><!-- / personal-info -->
                
                <!-- 비밀번호 변경 -->
                <div id="changepw" class="account-info-content">
                    <h4>비밀번호 변경 <span class="pull-right"><button id="modify-password" type="button" class="btn btn-sm btn-default btn-rounded no-margin"><i class="lnr lnr-pencil"></i><span>편집</span></button></span></h4>
                    <div class="row">
               
               <div class="col-xs-6 col-sm-8 col-md-10">
                   
                        
               </div>
                    </div><!-- / row -->
                    
                  <div id="passwordChangeform" style="display: none;">
                       <br>
                       <h3>수정하세요</h3>
                       <p> 현재 비밀번호 </p>
                       <input type="text" id="currentPassword" name="currentPassword" value="" style="min-height: 35px; width:300px; font-size:16px;">
                       <hr>
                       <p> 새 비밀번호 </p>
                       <input type="password" id="newPassword" name="newPassword" value="" style="min-height: 35px; width:300px; font-size:16px;">
                       <hr>
                       <p> 새 비밀번호 확인 </p>
                       <input type="password" id="confirmPassword" name="confirmPassword" value="" style="min-height: 35px; width:300px; font-size:16px;"> 
                       <hr>
                       <!-- 수정 버튼 -->
                       <button id="modifypasswordBtn" type="submit">저장</button>
                       <button id="cancelModifypasswordBtn" type="button">취소</button>
                      </div> 
                </div><!-- / personal-info -->
                
                
                <div id="my-donations" class="account-info-content">
                    <h4>후원 내역 </h4>
                    <p class="space-bottom"></p>
                    <div id="donationListDiv"></div>
                    <div id="donationPageNumDiv"></div>
                </div><!-- / shipping-info -->


                <div id="my-orders" class="account-info-content">
                    <h4>QnA 내역 </h4>
                    <div id="questionListDiv"></div>
                    <div id="questionPageNumDiv"></div>
                </div><!-- / my-orders -->

            <div id="my-wishs" class="account-info-content">
                    <h4>위시리스트 </h4>
                    <div id="wishListDiv"></div>
                    <div id="wishPageNumDiv"></div>
                </div><!-- / my-orders -->
                
                
                 <br>
                <button id="remove-account" type="button" style="color: red;">회원 탈퇴</button>
            </div><!-- / account-info -->

        </div><!-- / row -->
    </div><!-- / container -->
</section>

<!-- / my-account -->

<!-- / content -->

<!-- javascript -->
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.easing.min.js"></script>

<!-- inputmask 라이브러리 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.inputmask/5.0.6/jquery.inputmask.min.js"></script>
<!-- scrolling-nav -->
<script src="${pageContext.request.contextPath}/resources/js/scrolling-nav.js"></script>
<!-- / scrolling-nav -->

<!-- preloader -->
<script src="${pageContext.request.contextPath}/resources/js/preloader.js"></script>
<!-- / preloader -->
<!-- inputmask 스크립트 -->
   <script>
       $(document).ready(function() {
           $('#phone').inputmask("999-9999-9999", { "placeholder": "010-____-____" });
       });
   </script>


<script type="text/javascript">
   //CSRF 토큰 관련 정보를 자바스트립트 변수에 저장
   var csrfHeaderName="${_csrf.headerName}";
   var csrfTokenValue="${_csrf.token}";
   
   var loginIdx="<sec:authentication property="principal.idx"/>";
   
   var loginId="<sec:authentication property="principal.id"/>";
   
   var loginIdElement = document.createElement("textarea");
   loginIdElement.innerHTML = loginId;
   var loginIdDecoded = loginIdElement.value;
      
   //ajaxSend() 메소드를 호출하여 페이지에서 Ajax 기능으로 요청하는 모든 웹프로그램에게 CSRF 토큰 전달
   // => Ajax 요청시 beforeSend 속성을 설정 불필요
   $(document).ajaxSend(function(e, xhr) {
      xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
   });

   var donationPage = 1;
   var questionPage = 1;
   var wishPage = 1;
   
   function getMyAccountsData(accountId, donationPageNum, questionPageNum, wishPageNum) {
      loginIdDecoded = accountId;
      questionPage = questionPageNum;
      donationPage = donationPageNum;
      wishPage = wishPage;
      
       $.ajax({
           method: "GET",
           url: "<c:url value ='/account_detail'/>",
           data: {
               "id": loginIdDecoded,
               "donationPageNum": donationPageNum,
               "questionPageNum": questionPageNum,
               "wishPageNum": wishPageNum
           },
           dataType: "json",
           success: function (result) {
               // result 객체에서 myWish, myQuestion, myDonation 데이터를 추출
               var myDonationData = result.myDonation;
               var myQuestionData = result.myQuestion;
               var myWishData = result.myWish;
   
               displayDonationData(myDonationData);
               displayQuestionData(myQuestionData);
               displayWishData(myWishData);
               
           },
           error: function (xhr) {
               alert("데이터를 불러오는 중에 오류가 발생했습니다. (에러 코드 = " + xhr.status + ")");
           }
       });
   }
   
   //예시: 기부 데이터를 표시하는 함수
   function displayDonationData(myDonationData) {
      $("#donationListDiv").empty();
       
       var table = $("<table>").attr("id", "donationTable");
       var thead = $("<thead>").append(
           "<tr>" +
           "<th>번호</th>" +
           "<th>제목</th>" +
           "<th>후원금</th>" +
           "<th>결제일</th>" +
           "<th>후원일</th>" +
           "<th>결제 수단</th>" +
           "<th>결제 상태</th>" +
           "<th></th>" +
           "</tr>"
       );
   
       var tbody = $("<tbody>");
   
       if (myDonationData.donationList.length == 0) { // 검색된 게시글이 없을 때
           var row = "<tr>" +
               "<td colspan='10'>후원 내역이 없습니다. 어서 후원하세요!</td>" +
               "</tr>";
           tbody.append(row);
       }
       
       for (var i = 0; i < myDonationData.donationList.length; i++) {
           var donation = myDonationData.donationList[i];
           var payTypeText = ""; //payType을 표시할 변수
           var stateText = ""; //state를 표시할 변수
           
           switch (donation.payType) {
              case 0:
                 payTypeText = "신용카드";
                 break;
              case 1:
                 payTypeText = "카카오페이";
                 break;
              case 2:
                 payTypeText = "네이버페이";
                 break;
              default:
                 payTypeText = "알 수 없음"; //다른 값이 들어올 경우 처리
           }
           
           switch (donation.state) {
              case 0:
                 stateText = "결제 취소";
                 break;
              case 1:
                 stateText = "결제 완료";
                 break;
              case 2:
                 stateText = "결제 전";
                 break;
              default:
                 stateText = "알 수 없음"; //다른 값이 들어올 경우 처리
           }
           
           var row ="";
           if(donation.state == 2){
              row ="<tr data-idx='" + donation.idx + "'>" +
               "<td>" + donation.idx + "</td>" +
               "<td>" + donation.subject + "</td>" +
               "<td>" + donation.money + "</td>" +
               "<td>" + donation.day + "</td>" +
               "<td>" + donation.payDay + "</td>" +
               "<td>" + payTypeText + "</td>"+
               "<td>" + stateText + "</td>"+
              "<td><a href='${pageContext.request.contextPath}/donation/pay_completion?idx="+donation.idx+"&festivalIdx="+donation.festivalIdx+"' class='btn btn-sm btn-default btn-rounded no-margin payBtn'><span>결제</span></a></td></tr>";
           }else{
              row ="<tr data-idx='" + donation.idx + "'>" +
               "<td>" + donation.idx + "</td>" +
               "<td>" + donation.subject + "</td>" +
               "<td>" + donation.money + "</td>" +
               "<td>" + donation.day + "</td>" +
               "<td>" + donation.payDay + "</td>" +
               "<td>" + payTypeText + "</td>"+
               "<td>" + stateText + "</td>"+
              "<td></td></tr>";
           }
           tbody.append(row);
         }
   
       table.append(thead, tbody);
       
       $("#donationListDiv").append(table);
       
       donationPageNumDisplay(myDonationData.pager);
   }
   
   //예시: 질문 데이터를 표시하는 함수
   function displayQuestionData(myQuestionData) {
      $("#questionListDiv").empty();
       
       var table = $("<table>").attr("id", "questionTable");
       var thead = $("<thead>").append(
           "<tr>" +
           "<th>번호</th>" +
           "<th>제목</th>" +
           "<th>내용</th>" +
           "<th>작성일</th>" +
           "<th>첨부파일</th>" +
           "<th>조회수</th>" +
           "</tr>"
       );
   
       var tbody = $("<tbody>");
   
       if (myQuestionData.questionList.length == 0) { // 검색된 게시글이 없을 때
           var row = "<tr>" +
               "<td colspan='10'>질문 사항이 없습니다. 어서 질문하세요!</td>" +
               "</tr>";
           tbody.append(row);
       }
       
       for (var i = 0; i < myQuestionData.questionList.length; i++) {
           var question = myQuestionData.questionList[i];
           var row = "<tr data-idx='" + question.idx + "'>" +
               "<td>" + question.idx + "</td>" +
               "<td>" + question.title + "</td>" +
               "<td>" + question.content + "</td>" +
               "<td>" + question.day + "</td>" +
               "<td>" + question.fileData + "</td>" +
               "<td>" + question.count+ "</td>" +
               "</tr>";
           tbody.append(row);
      }
   
       table.append(thead, tbody);
       
       $("#questionListDiv").append(table);
       
       questionPageNumDisplay(myQuestionData.pager);
   }
   
   // 예시: 위시 리스트 데이터를 표시하는 함수
   function displayWishData(myWishData) {
      $("#wishListDiv").empty();
       
      var griDiv = $("<div>").attr("id", "gri").addClass("row"); // 클래스 추가
      
      if (myWishData.wishList.length == 0) { // 검색된 게시글이 없을 때
          var noResultMessage = $("<p>").text("찜목록이 없습니다. 어서 찜하세요!");
          griDiv.append(noResultMessage);
      } 
            
      for (var i = 0; i < myWishData.wishList.length; i++) {
          var wish = myWishData.wishList[i];
          
          var wishDiv = $("<div>").addClass("col-xs-6 col-md-3 product");
          
          var wishA = $("<a>").attr('href', 'javascript:festivalDetail(' + wish.festivalIdx + ');').addClass('product-link');
          
          var wishImg = $("<img>").attr("src", '<c:url value="/resources/upload/' + wish.mainImg + '" />').attr("alt", wish.subject + " 포스터");
          
          var shopTitleDiv = $("<div>").addClass("shop_title");
          
          var strongTitle = $("<h3>").addClass("product-title").text(wish.subject);
          
          // 요소들을 조립합니다.
          shopTitleDiv.append(strongTitle);
          
          wishDiv.append(wishA, wishImg, shopTitleDiv);
          
          griDiv.append(wishDiv);
          
         }
          
       $("#wishListDiv").append(griDiv);
       
       wishPageNumDisplay(myWishData.pager);
   }
   
   function donationPageNumDisplay(pager) {
       var html = "";
       if (pager.startPage > pager.blockSize) {
          html += "<a href=\"javascript:getMyAccountsData('" + loginIdDecoded + "', " + pager.prevPage + ", " + questionPage + ", " + wishPage + ");\" class='btn btn-direction btn-default btn-rounded'><i class='fa fa-long-arrow-left'/></a>";
       } else {
          html += "<a class='btn btn-direction btn-default btn-rounded' disabled><i class='fa fa-long-arrow-left'/></a>";
       }
   
       for (var i = pager.startPage; i <= pager.endPage; i++) {
           if (pager.pageNum != i) {
               html += "<a class='btn btn-direction btn-default btn-rounded' href=\"javascript:getMyAccountsData('" + loginIdDecoded + "', " + i + ", " + questionPage + ", " + wishPage + ");\">" + i + "</a>";
           } else {
               html += "<a class='btn btn-direction btn-default btn-rounded' disabled>" + i + "</a>";
           }
       }
   
       if (pager.endPage != pager.totalPage) {
           html += "<a href=\"javascript:getMyAccountsData('" + loginIdDecoded + "', " + pager.nextPage + ", " + questionPage + ", " + wishPage + ");\" class='btn btn-direction btn-default btn-rounded'><i class='fa fa-long-arrow-right'/></a>";
       } else {
           html += "<a class='btn btn-direction btn-default btn-rounded' disabled><i class='fa fa-long-arrow-right'/></a>";
       }
   
       $("#donationPageNumDiv").html(html);
   }
   
   function questionPageNumDisplay(pager) {
    var html = "";
    if (pager.startPage > pager.blockSize) {
        html += "<a href=\"javascript:getMyAccountsData('" + loginIdDecoded + "', " + donationPage + ", " + pager.prevPage + ", " + wishPage + ");\" class='btn btn-direction btn-default btn-rounded'><i class='fa fa-long-arrow-left'/></a>";
    } else {
       html += "<a class='btn btn-direction btn-default btn-rounded' disabled><i class='fa fa-long-arrow-left'/></a>";
    }
   
    for (var i = pager.startPage; i <= pager.endPage; i++) {
        if (pager.pageNum != i) {
            html += "<a class='btn btn-direction btn-default btn-rounded' href=\"javascript:getMyAccountsData('" + loginIdDecoded + "', " + donationPage + ", " + i + ", " + wishPage + ");\">" + i + "</a>";
        } else {
            html += "<a class='btn btn-direction btn-default btn-rounded' disabled>" + i + "</a>";
        }
    }
   
    if (pager.endPage != pager.totalPage) {
        html += "<a href=\"javascript:getMyAccountsData('" + loginIdDecoded + "', " + donationPage + ", " + pager.nextPage + ", " + wishPage + ");\" class='btn btn-direction btn-default btn-rounded'><i class='fa fa-long-arrow-right'/></a>";
    } else {
        html += "<a class='btn btn-direction btn-default btn-rounded' disabled><i class='fa fa-long-arrow-right'/></a>";
    }
   
       $("#questionPageNumDiv").html(html);
   }
   
   function wishPageNumDisplay(pager) {
       var html = "";
       if (pager.startPage > pager.blockSize) {
           html += "<a href=\"javascript:getMyAccountsData('" + loginIdDecoded + "', " + donationPage + ", " + questionPage + ", " + pager.prevPage + ");\" class='btn btn-direction btn-default btn-rounded'><i class='fa fa-long-arrow-left'/></a>";
       } else {
          html += "<a class='btn btn-direction btn-default btn-rounded' disabled><i class='fa fa-long-arrow-left'/></a>";
       }
   
       for (var i = pager.startPage; i <= pager.endPage; i++) {
           if (pager.pageNum != i) {
               html += "<a class='btn btn-direction btn-default btn-rounded' href=\"javascript:getMyAccountsData('" + loginIdDecoded + "', " + donationPage + ", " + questionPage + ", " + i + ");\">" + i + "</a>";
           } else {
               html += "<a class='btn btn-direction btn-default btn-rounded' disabled>" + i + "</a>";
           }
       }
   
       if (pager.endPage != pager.totalPage) {
           html += "<a href=\"javascript:getMyAccountsData('" + loginIdDecoded + "', " + donationPage + ", " + questionPage + ", " + pager.nextPage + ");\" class='btn btn-direction btn-default btn-rounded'><i class='fa fa-long-arrow-right'/></a>";
       } else {
           html += "<a class='btn btn-direction btn-default btn-rounded' disabled><i class='fa fa-long-arrow-right'/></a>";
       }
   
       $("#wishPageNumDiv").html(html);
   }
   

	// 이메일 중복체크 버튼 이벤트 핸들러
	$('#check-email-button').click(function () {
	    var emailValue = $('#email').val();

	    // 클라이언트에서 서버로 이메일 중복 체크 요청을 보냅니다.
	    $.ajax({
	        type: 'GET',
	        url: "<c:url value='/checkEmail'/>",
	        data: { email: emailValue },
	        success: function (response) {
	            if (response.available) {
	                alert('사용 가능한 이메일입니다.');
	                document.getElementById('emailDuplicationMessage').style.display = 'none';

	                // 이메일 중복 체크 성공 시, 중복 체크 버튼 비활성화
	                $('#check-email-button').prop('disabled', true);
	            } else {
	                alert('이미 사용중인 이메일입니다.');
	                document.getElementById('emailDuplicationMessage').style.display = 'block';
	            }
	        },
	        error: function () {
	            alert('서버 오류가 발생하였습니다.');
	        }
	    });
	});

   
   $(document).ready(function () {
      getMyAccountsData(loginIdDecoded, donationPage, questionPage, wishPage);
      
      $("#cancelModifyBtn").click(function(){
           $("#edit-form").toggle();
      });
      
       $("#modify-account").click(function() {
           // 수정 폼을 표시하거나 숨기기
           $("#edit-form").toggle();
       });
      
      $("#modifyBtn").click(function() {
          var name = $("#name").val();
          var email = $("#email").val();
          var phone = $("#phone").val();
          var address1 = $("#address1").val();
          var address2 = $("#address2").val();
          var address3 = $("#address3").val();
          var birth = $("#birth").val();
          var gender = $("input[name='gender']:checked").val();
          
          var namePattern = /^[가-힣]{2,6}$/;
          var emailPattern = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
          var phonePattern = /^\d{3}-\d{4}-\d{4}$/;
          var isValid = true;
          
       	  // 이메일 중복 체크 문구가 띄워져 있는지 확인
          if ($("#emailDuplicationMessage").is(":visible")) {
              alert("이메일 중복 체크를 실행해주세요.");
              event.preventDefault(); // 저장 이벤트 취소
              return;
          }

          if(!namePattern.test(name)) {
             document.getElementById('nameError').style.display = 'block';
             $("#name").focus();
             event.preventDefault();
             isValid = false;
          } else {
             document.getElementById('nameError').style.display = 'none';
          }
          
           if(!emailPattern.test(email)) {
             document.getElementById('emailError').style.display = 'block';
             $("#email").focus();
             event.preventDefault();
             isValid = false;
          } else {
             document.getElementById('emailError').style.display = 'none';
          }
          
          
          if(!phonePattern.test(phone)) {
             document.getElementById('phoneError').style.display = 'block';
             $("#phone").focus();
             event.preventDefault();
             isValid = false;
          } else {
             document.getElementById('phoneError').style.display = 'none';
          }
          
          if (address1 == null || address2 == null || address3 == null || address1 === "" || address2 === "" || address3 === "") {
              document.getElementById('addressError').style.display = 'block';
              event.preventDefault();
              isValid = false;
          } else {
             document.getElementById('addressError').style.display = 'none';
          }
          
          if(birth == "" || birth == null) {
             document.getElementById('birthError').style.display = 'block';
              event.preventDefault();
              isValid = false;
          } else{
             document.getElementById('birthError').style.display = 'none';
          }
          
          if(!gender) {
             document.getElementById('genderError').style.display = 'block';
             event.preventDefault();
              isValid = false;
          } else {
             document.getElementById('genderError').style.display = 'none';
          }
          
          if(isValid) {
          $.ajax({
             type: "PUT",
             url: "<c:url value="/account_modify"/>",
             contentType: "application/json",
             data: JSON.stringify({"id":loginIdDecoded, "name":name, "email":email, "gender":gender, "birth":birth, "phone":phone, "address1":address1, "address2":address2, "address3":address3, "idx":loginIdx}),
             dataType: "text",
             success: function(result) {
                if(result == "success") {
                   alert("회원 정보가 성공적으로 변경되었습니다. 다시 로그인 해주세요.");
                   window.location.href = "<c:url value='/account/login'/>";
                } else {
                   alert("회원 정보가 실패적으로 변경되었습니다. 다시 로그인 해주세요.");
                }
             },
             error: function(xhr) {
                alert("회원 정보 편집 중 오류가 발생하였습니다("+ xhr.status+")");
             }
          });
        }
    });
      
         //비밀번호 수정
            $('#modify-password').click(function() {
               $('#passwordChangeform').toggle();
            });   
         
         $('#cancelModifypasswordBtn').click(function(){
            $('#passwordChangeform').hide();
         });
         
         $('#modifypasswordBtn').click(function() {
            var currentPassword = $('#currentPassword').val();
               var newPassword = $('#newPassword').val();
               var confirmPassword = $('#confirmPassword').val();
               
               if(currentPassword == "" || currentPassword == null || newPassword == "" || newPassword == null || confirmPassword == "" || confirmPassword == null) {
                   alert("비밀번호를 입력하여 주세요.");
                   return;
                }
               
               // PUT 요청을 서버로 보냄
                 $.ajax({
                     type: "PUT",
                     url: "<c:url value='/changePassword'/>", // 변경할 비밀번호 엔드포인트 URL로 변경해야 합니다.
                     contentType: "application/json",
                     data: JSON.stringify({
                        "id":loginIdDecoded,
                        "currentPassword": currentPassword,
                         "newPassword": newPassword,
                         "confirmPassword": confirmPassword,
                        "idx":loginIdx
                     }),
                     dataType: "text",
                     success: function(result) {
                        if(result == "success") {
                           window.location.href = "<c:url value='/account/login'/>";                   
                            alert("비밀번호가 성공적으로 변경되었습니다. 다시 로그인 해주세요.");
                        } else {
                           alert("오류: "+result);
                        }
                         // 비밀번호 변경 성공 시 처리
                     },
                     error: function(xhr) {
                         // 오류 발생 시 처리
                      alert("비밀번호 변경 중 오류가 발생하였습니다. ("+ xhr.status+")");
                         $("#resultMessage").text("비밀번호 변경 중 오류가 발생하였습니다.");
                     }
                 });
             });
               
          $("#remove-account").click(function() {
               if (confirm("정말로 삭제하시겠습니까?")) {
                   
                   $.ajax({
                       type: "PUT",
                       url: "<c:url value='/account_remove?id='/>" + loginIdDecoded,
                       success: function(result) {
                          
                           if(result == "success") {
                               window.location.href = "<c:url value='/'/>";
                           } else {
                               alert("회원 정보 삭제 중 오류가 발생하였습니다.");
                           }
                       },
                       error: function(xhr) {
                           alert("회원 정보  삭제 중 오류가 발생하였습니다("+ xhr.status+")");
                       }
                   });
               }
           });
      
   });

</script>
<script>
	//이메일 중복체크 및 아이디 유효성 관련 검증 코드!!!
	document.addEventListener('DOMContentLoaded', function () {
	    var emailInput = document.getElementById('email');
	    var checkEmailButton = document.getElementById('check-email-button');
	    var emailError = document.getElementById('emailError');
	    var emailDuplicationMessage = document.getElementById('emailDuplicationMessage');
	
	    // 이메일 입력 필드의 값이 변경될 때 이벤트 리스너 등록
	    	emailInput.addEventListener('input', function () {
	        var emailValue = emailInput.value;
	        var emailPattern = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	
	        if (emailPattern.test(emailValue)) {
	        	emailError.style.display = 'none';
	        	emailDuplicationMessage.style.display = 'block'; // 이메일 중복 체크 메시지 띄우기
	            checkEmailButton.disabled = false; // 이메일이 유효하면 버튼 활성화
	        } else {
	        	emailError.style.display = 'block';
	        	emailDuplicationMessage.style.display = 'none'; // 이메일 중복 체크 메시지 숨기기
	            checkEmailButton.disabled = true; // 이메일이 유효하지 않으면 버튼 비활성화
	            event.preventDefault(); 
	        }
	    });
	});

</script>

<!-- 주소 API 호출과 이벤트 핸들링 -->
   <script>
       document.getElementById('search-address-button').addEventListener('click', function () {
            new daum.Postcode({
               oncomplete: function (data) {
                   document.getElementById('address1').value = data.zonecode; // 우편번호 입력
                   document.getElementById('address2').value = data.roadAddress; // 도로명 주소 입력
                   document.getElementById('address3').focus();
                  
               }
           }).open();
       });
   </script>
   <!-- 다음 우편번호 서비스 스크립트 로딩 -->
   <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
   
<!-- / javascript -->
</body>

</html>