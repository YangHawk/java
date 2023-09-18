<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>       
<!DOCTYPE html>
<html lang="en">
<body>
<!-- my-account -->
<section id="my-account">
    <div class="container">
        <div class="row">
			<!-- 사용자 정보 -->
            <div class="col-sm-10 account-info">
                <div id="my-personal" class="account-info-content">
                    <h4>사용자 정보 <span class="pull-right"><button id="modify-account" type="button" class="btn btn-sm btn-default btn-rounded no-margin"><i class="lnr lnr-pencil"></i><span>편집</span></button></span></h4>
                    <div class="row">

                        <div class="col-xs-6 col-sm-8 col-md-10">
                            <p>이 름: <span>${account.name }</span></p>
                            <p>이메일: <span>${account.email }</span></p>
                            <p>전화번호: <span>${account.phone }</span></p>
                            <p>주소: <span>${account.address1 } ${account.address2 } ${account.address3 }</span></p>
                        </div>
                    </div><!-- / row -->
                    
                    <div id="edit-form" style="display: none;">
                    	<br>
                    	<h3>수정하세요</h3>
                    	<p> 이름 </p>
                    	<input id="name" name="name" value="${account.name }">
                    	<hr>
                    	<p> 이메일 </p>
                    	<input id="email" name="email" value="${account.email }">
                    	<hr>
                    	<p> 전화번호 </p>
                    	<input id="phone" name="phone" value="${account.phone }">
                    	<hr>
                    	<p> 주소 </p>
                    	<input id="address1" name="address1" value="${account.address1 }">
                    	<input id="address2" name="address2" value="${account.address2 }">
                    	<input id="address3" name="address3" value="${account.address3 }">
                    	<hr>
                    	<!-- 수정 버튼 -->
                    	<button id="modifyBtn" type="submit">저장</button>
                    	<button id="cancelModifyBtn" type="button">취소</button>
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
                <button id="remove-account" type="button" style="color: red;">회원 삭제</button>
            </div><!-- / account-info -->

        </div><!-- / row -->
    </div><!-- / container -->
</section>

<script type="text/javascript">
var id = "${account.id}"; // 전달받은 아이디
var idx = "${account.idx}";
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
        url: "<c:url value ='/admin/account_details'/>",
        data: {
            "id": id,
            "donationPageNum": donationPageNum,
            "questionPageNum": questionPageNum,
            "wishPageNum": wishPageNum
        },
        dataType: "json",
        success: function(result) {
            console.log(result);
            // result 객체에서 myWish, myQuestion, myDonation 데이터를 추출
            var myDonationData = result.myDonation;
            var myQuestionData = result.myQuestion;
            var myWishData = result.myWish;

            displayDonationData(myDonationData);
            displayQuestionData(myQuestionData);
            displayWishData(myWishData);
            console.log(myDonationData);
            console.log(myQuestionData);
            console.log(myWishData);

        },
        error: function(xhr) {
            alert("데이터를 불러오는 중에 오류가 발생했습니다. (에러 코드 = " + xhr.status + ")");
        }
    });
}

// 기부 데이터를 표시하는 함수
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

// 질문 데이터를 표시하는 함수
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
            "<td>" + question.count + "</td>" +
            "</tr>";
        tbody.append(row);
    }

    table.append(thead, tbody);

    $("#questionListDiv").append(table);

    questionPageNumDisplay(myQuestionData.pager);
}

// 위시 리스트 데이터를 표시하는 함수
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

        var wishA = $("<a>").attr('href', '<c:url value="/donation/single-festival?idx=' + wish.festivalIdx + '" />').addClass('product-link');

        var wishImg = $("<img>").attr("src", '<c:url value="/resources/upload/' + wish.mainImg + '" />').attr("alt", wish.subject + " 포스터");

        var shopTitleDiv = $("<div>").addClass("shop_title");

        var strongTitle = $("<h3>").addClass("product-title").text(wish.subject);

        // 요소들을 조립합니다.
        shopTitleDiv.append(strongTitle);

        donationDiv.append(donationA, donationImg, shopTitleDiv);

        griDiv.append(donationDiv);

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


$(document).ready(function() {
    getMyAccountsData(id, donationPage, questionPage, wishPage);

    $("#cancelModifyBtn").click(function() {
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

        if (name == "") {
            alert("이름을 입력해 주세요.");
            return;
        }

        if (email == "") {
            alert("이메일을 입력해 주세요.");
            return;
        }

        if (phone == "") {
            alert("전화번호를 입력해 주세요.");
            return;
        }

        if (address1 == null || address2 == null || address3 == null || address1 === "" || address2 === "" || address3 === "") {
            alert("주소를 입력해 주세요.");
            return;
        }

        $.ajax({
            type: "PUT",
            url: "<c:url value='/admin/account_modify'/>",
            contentType: "application/json",
            data: JSON.stringify({
            	"id": id,
                "name": name,
                "phone": phone,
                "email": email,
                "address1": address1,
                "address2": address2,
                "address3": address3,
                "idx": idx
            }),
            dataType: "text",
            success: function(result) {
                if (result == "success") {
                    accountDetail(id);

                }
            },
            error: function(xhr) {
                alert("회원 정보 편집 중 오류가 발생하였습니다(" + xhr.status + ")");
            }
        });
    });

    $("#remove-account").click(function() {
        if (confirm("정말로 삭제하시겠습니까?")) {

            $.ajax({
                type: "PUT",
                url: "<c:url value='/admin/account_remove?id='/>" + id,
                success: function(result) {
                    if (result == "success") {
                        accountListDisplay(page, size, keyword);
                    } else {
                        alert("회원 정보 삭제 중 오류가 발생하였습니다.");
                    }
                },
                error: function(xhr) {

                    alert("회원 정보  삭제 중 오류가 발생하였습니다(" + xhr.status + ")");
                }
            });
        }
    });

    // 영화제 tr 태그 클릭 시 상세 정보 보기로 이동
    $("#donationListDiv").on("click", "#donationTable tbody tr", function() {
        var idx = $(this).data("idx");
        festivalDetail(idx);
    });

    // 질문글 tr 태그 클릭 시 상세 정보 보기로 이동
    $("#questionListDiv").on("click", "#questionTable tbody tr", function() {
        var idx = $(this).data("idx");
        questionDetail(idx);
    });

});

</script>
</body>
</html>