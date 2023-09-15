<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

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
                <p class="slide-text fadeInUp animated second">${loginAccount.id } 님의 정보입니다.</p>
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
                            <p>이 름: <span>${loginAccount.name }</span></p>
                            <p>이메일: <span>${loginAccount.email }</span></p>
                            <p>전화번호: <span>${loginAccount.phone }</span></p>
                            <p>주소: <span>${loginAccount.address1 } ${loginAccount.address2 } ${loginAccount.address3 }</span></p>
                        </div>

                    </div><!-- / row -->
                    
                    <div id="edit-form" style="display: none;">
                    	<br>
                    	<h3>수정하세요</h3>
                    	<p> 이름 </p>
                    	<input id="aName" name="aName" value="${loginAccount.name }">
                    	<hr>
                    	<p> 이메일 </p>
                    	<input id="aEmail" name="aEmail" value="${loginAccount.email }">
                    	<hr>
                    	<p> 전화번호 </p>
                    	<input id="aPhone" name="aPhone" value="${loginAccount.phone }">
                    	<hr>
                    	<p> 주소 </p>
                    	<input id="aAddress1" name="aAddress1" value="${loginAccount.address1 }">
                    	<input id="aAddress2" name="aAddress2" value="${loginAccount.address2 }">
                    	<input id="aAddress3" name="aAddress3" value="${loginAccount.address3 }">
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
                    	<input type="text" id="currentPassword" name="currentPassword" value="">
                    	<hr>
                    	<p> 새 비밀번호 </p>
                    	<input type="password" id="newPassword" name="newPassword" value="">
                    	<hr>
                    	<p> 새 비밀번호 확인 </p>
                    	<input type="password" id="confirmPassword" name="confirmPassword" value="">
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

<!-- scrolling-nav -->
<script src="${pageContext.request.contextPath}/resources/js/scrolling-nav.js"></script>
<!-- / scrolling-nav -->

<!-- preloader -->
<script src="${pageContext.request.contextPath}/resources/js/preloader.js"></script>
<!-- / preloader -->

<script type="text/javascript">
	var id = "${loginAccount.id}"; // 전달받은 아이디
	var donationPage = 1;
	var questionPage = 1;
	var wishPage = 1;
	
	function getMyAccountsData(accountId, donationPageNum, questionPageNum, wishPageNum) {

		id = accountId;
		questionPage = questionPageNum;
		donationPage = donationPageNum;
		wishPage = wishPage;
		
	    $.ajax({
	        method: "GET",
	        url: "<c:url value ='/account_detail'/>",
	        data: {
	            "id": id,
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
	        "<th>환불 계좌</th>" +
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
	        var row = "<tr data-idx='" + donation.idx + "'>" +
	            "<td>" + donation.idx + "</td>" +
	            "<td>" + donation.subject + "</td>" +
	            "<td>" + donation.money + "</td>" +
	            "<td>" + donation.day + "</td>" +
	            "<td>" + donation.payDay + "</td>" +
	            "<td>" + donation.payType + "</td>" +
	            "<td>" + donation.refundBank + donation.refundAccount + "</td>" +
	            "</tr>";
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
			 html += "<a href=\"javascript:getMyAccountsData('" + id + "', " + pager.prevPage + ", " + questionPage + ", " + wishPage + ");\" class='btn btn-direction btn-default btn-rounded'><i class='fa fa-long-arrow-left'/></a>";
		 } else {
			 html += "<a class='btn btn-direction btn-default btn-rounded' disabled><i class='fa fa-long-arrow-left'/></a>";
		 }
	
		 for (var i = pager.startPage; i <= pager.endPage; i++) {
		     if (pager.pageNum != i) {
		         html += "<a class='btn btn-direction btn-default btn-rounded' href=\"javascript:getMyAccountsData('" + id + "', " + i + ", " + questionPage + ", " + wishPage + ");\">" + i + "</a>";
		     } else {
		         html += "<a class='btn btn-direction btn-default btn-rounded' disabled>" + i + "</a>";
		     }
		 }
	
		 if (pager.endPage != pager.totalPage) {
		     html += "<a href=\"javascript:getMyAccountsData('" + id + "', " + pager.nextPage + ", " + questionPage + ", " + wishPage + ");\" class='btn btn-direction btn-default btn-rounded'><i class='fa fa-long-arrow-right'/></a>";
		 } else {
		     html += "<a class='btn btn-direction btn-default btn-rounded' disabled><i class='fa fa-long-arrow-right'/></a>";
		 }
	
		 $("#donationPageNumDiv").html(html);
	}
	
	function questionPageNumDisplay(pager) {
	 var html = "";
	 if (pager.startPage > pager.blockSize) {
	     html += "<a href=\"javascript:getMyAccountsData('" + id + "', " + donationPage + ", " + pager.prevPage + ", " + wishPage + ");\" class='btn btn-direction btn-default btn-rounded'><i class='fa fa-long-arrow-left'/></a>";
	 } else {
		 html += "<a class='btn btn-direction btn-default btn-rounded' disabled><i class='fa fa-long-arrow-left'/></a>";
	 }
	
	 for (var i = pager.startPage; i <= pager.endPage; i++) {
	     if (pager.pageNum != i) {
	         html += "<a class='btn btn-direction btn-default btn-rounded' href=\"javascript:getMyAccountsData('" + id + "', " + donationPage + ", " + i + ", " + wishPage + ");\">" + i + "</a>";
	     } else {
	         html += "<a class='btn btn-direction btn-default btn-rounded' disabled>" + i + "</a>";
	     }
	 }
	
	 if (pager.endPage != pager.totalPage) {
	     html += "<a href=\"javascript:getMyAccountsData('" + id + "', " + donationPage + ", " + pager.nextPage + ", " + wishPage + ");\" class='btn btn-direction btn-default btn-rounded'><i class='fa fa-long-arrow-right'/></a>";
	 } else {
	     html += "<a class='btn btn-direction btn-default btn-rounded' disabled><i class='fa fa-long-arrow-right'/></a>";
	 }
	
	 	$("#questionPageNumDiv").html(html);
	}
	
	function wishPageNumDisplay(pager) {
		 var html = "";
		 if (pager.startPage > pager.blockSize) {
		     html += "<a href=\"javascript:getMyAccountsData('" + id + "', " + donationPage + ", " + questionPage + ", " + pager.prevPage + ");\" class='btn btn-direction btn-default btn-rounded'><i class='fa fa-long-arrow-left'/></a>";
		 } else {
			 html += "<a class='btn btn-direction btn-default btn-rounded' disabled><i class='fa fa-long-arrow-left'/></a>";
		 }
	
		 for (var i = pager.startPage; i <= pager.endPage; i++) {
		     if (pager.pageNum != i) {
		         html += "<a class='btn btn-direction btn-default btn-rounded' href=\"javascript:getMyAccountsData('" + id + "', " + donationPage + ", " + questionPage + ", " + i + ");\">" + i + "</a>";
		     } else {
		         html += "<a class='btn btn-direction btn-default btn-rounded' disabled>" + i + "</a>";
		     }
		 }
	
		 if (pager.endPage != pager.totalPage) {
		     html += "<a href=\"javascript:getMyAccountsData('" + id + "', " + donationPage + ", " + questionPage + ", " + pager.nextPage + ");\" class='btn btn-direction btn-default btn-rounded'><i class='fa fa-long-arrow-right'/></a>";
		 } else {
		     html += "<a class='btn btn-direction btn-default btn-rounded' disabled><i class='fa fa-long-arrow-right'/></a>";
		 }
	
		 $("#wishPageNumDiv").html(html);
	}
	
	
	$(document).ready(function () {
		
		getMyAccountsData(id, donationPage, questionPage, wishPage);
		
		$("#cancelModifyBtn").click(function(){
	        $("#edit-form").toggle();
		});
		
	    $("#modify-account").click(function() {
	        // 수정 폼을 표시하거나 숨기기
	        $("#edit-form").toggle();
	    });
		
		$("#modifyBtn").click(function() {
	    	var aName = $("#aName").val();
	    	var aEmail = $("#aEmail").val();
	    	var aPhone = $("#aPhone").val();
	    	var aAddress1 = $("#aAddress1").val();
	    	var aAddress2 = $("#aAddress2").val();
	    	var aAddress3 = $("#aAddress3").val();
	    	
	    	if(aName == "") {
	    		alert("이름을 입력해 주세요.");
	    		return;
	    	}
	    	
	    	if(aEmail == "") {
	    		alert("이메일을 입력해 주세요.");
	    		return;
	    	}
	    	
	    	if(aPhone == "") {
	    		alert("전화번호를 입력해 주세요.");
	    		return;
	    	}
	    	
	    	if (aAddress1 == null || aAddress2 == null || aAddress3 == null || aAddress1 === "" || aAddress2 === "" || aAddress3 === "") {
	    	    alert("주소를 입력해 주세요.");
	    	    return;
	    	}
	    	
	    	$.ajax({
	    		type: "PUT",
	    		url: "<c:url value="/account_modify"/>",
	    		contentType: "application/json",
	    		data: JSON.stringify({"id":id, "name":aName, "email":aEmail, "phone":aPhone, "address1":aAddress1, "address2":aAddress2, "address3":aAddress3, }),
	    		dataType: "text",
	    		success: function(result) {
	    			
	    			if(result == "success") {
	    				console.log(result);
	    				window.location.href = "<c:url value='/account/myaccount'/>";
	    			}
	    		},
	    		error: function(xhr) {
	    			alert("회원 정보 편집 중 오류가 발생하였습니다("+ xhr.status+")");
	    		}
	    	});
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
			/*if(currentPassword == "") {
				alert("현재 비밀번호를 입력해주세요.");
				return;
			}
			
			if(newPassword == "") {
				alert("새 비밀번호를 입력해주세요.");
				return;
			} 
			
			if(confirmPassword == "") {
				alert("새 비밀번호 확인을 입력해주세요.");
				return;
			}
			
			if(newPassword !== confirmPassword ) {
				alert("새 비밀번호와 새 비밀번호 확인이 일치하지 않습니다.");
				return;
			}*/
			
			// PUT 요청을 서버로 보냄
	        $.ajax({
	            type: "PUT",
	            url: "<c:url value='/changePassword'/>", // 변경할 비밀번호 엔드포인트 URL로 변경해야 합니다.
	            contentType: "application/json",
	            data: JSON.stringify({
	            	"id": id,
	            	"currentPassword": currentPassword,
	                "newPassword": newPassword,
	             	"confirmPassword": confirmPassword
	            }),
	            dataType: "text",
	            success: function(result) {
	            	if(result == "success") {
	            		console.log(result);
	            		window.location.href = "<c:url value='/account/myaccount'/>";	   		       
		                alert("비밀번호가 성공적으로 변경되었습니다.");
	            	} else if(result == "error1") {
	            		console.log(result);
	            	} else if(result == "error2") {
	            		console.log(result);
	            	} else if(result == "error3") {
	            		console.log(result);
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
	                type: "DELETE",
	                url: "<c:url value='/account_remove?id='/>" + id,
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
<!-- / javascript -->
</body>

</html>