<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
<!-- preloader -->
<div id="preloader">
    <div class="spinner spinner-round"></div>
</div>
<!-- / preloader -->
<!-- content -->
<!-- my-account -->
<section id="shop">
    <div class="container">
    		<span ></span>
            <div class="col-sm-2 account-sidebar">
                <p><a href="javascript:void(0)" id="account-info">회원 관리</a></p>
                <p><a href="javascript:void(0)" id="festival-info">영화제 관리</a></p>
                <p><a href="javascript:void(0)" id="question-info">질문글 관리</a></p>
                <p><a href="javascript:void(0)" id="notice-info">공지사항 관리</a></p>
                <p><a href="javascript:void(0)" id="page-manage">페이지 관리</a></p>
            </div><!-- / account-sidebar -->
	        <div class="row">
	            <div class="col-sm-12 content-area">
	                <!-- 페이지 당 출력 갯수를 출력하는 태그 -->
	                <select id="pageSizeSelect" ></select>
	
		            <!-- 버튼 추가 -->
		            <div id="viewSelect" class="nav nav-pills text-right"></div>
		            
					<br>	            
	
	                <!-- 게시글 목록을 출력하는 태그 -->
	                <div id="infoListDiv"></div>
	
	                <!-- 페이지 번호를 출력하는 태그 -->
	                <div id="pageNumDiv"></div>
	                
	                <!-- 검색 창을 출력하는 태그 -->
	                <div id="searchDiv"></div>
	            </div>
        </div><!-- / row -->
    </div><!-- / container -->
</section>

<!-- / my-account -->
<!-- / content -->
<!-- javascript -->
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.easing.min.js"></script>
<!-- shuffle grid-resizer -->
<script src="${pageContext.request.contextPath}/resources/js/jquery.shuffle.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/custom.js"></script>

<script src="${pageContext.request.contextPath}/resources/js/scrolling-nav.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/preloader.js"></script>

<script type="text/javascript">
var page = 1; // 기본 페이지 번호 설정
var size = 20; // 기본 페이지 크기 설정
var keyword = ''; // 기본 검색어 = NULL String
var infoType = ''; // 기본 검색 타입 = NULL String(account / festival / q&a / notice)
var viewType = 'list'; // 기본 보기 설정 = 목록으로 보기

// 회원 정보 리스트 출력 함수
function accountListDisplay(pageNum, pageSize, selectKeyword) {
	$("#viewSelect").show();
	$("#pageSizeSelect").show();
	$("#pageNumDiv").show();
	$("#searchDiv").show();
   page=pageNum;
   size=pageSize;
   keyword=selectKeyword;
   
    $.ajax({
        method: "GET",
        url: "<c:url value ="/admin/account_list"/>",
        data: {"pageNum": pageNum, "pageSize": pageSize, "selectKeyword": selectKeyword},
        dataType: "json",
        success: function(result) {
            $("#infoListDiv").empty();
            
            var table = $("<table>").attr("id", "accountInfoTable");
            var thead = $("<thead>").append(
                "<tr>" +
                "<th>회원번호</th>" +
                "<th>아이디</th>" +
                "<th>이름</th>" +
                "<th>전화번호</th>" +
                "<th>이메일</th>" +
                "<th>성별</th>" +
                "<th>생년월일</th>" +
                "<th>주소</th>" +
                "<th>등급</th>" +
                "<th>가입일</th>" +
                "</tr>"
            );

            var tbody = $("<tbody>");

            if (result.accountList.length == 0) { // 검색된 게시글이 없을 때
                var row = "<tr>" +
                    "<td colspan='10'>검색된 회원 정보가 없습니다.</td>" +
                    "</tr>";
                tbody.append(row);
            } 
            for (var i = 0; i < result.accountList.length; i++) {
                var account = result.accountList[i];
                var row = "<tr data-id='" + account.id + "'>" +
                    "<td>" + account.idx + "</td>" +
                    "<td>" + account.id + "</td>" +
                    "<td>" + account.name + "</td>" +
                    "<td>" + account.phone + "</td>" +
                    "<td>" + account.email + "</td>" +
                    "<td>" + account.gender + "</td>" +
                    "<td>" + account.birth + "</td>" +
                    "<td>" + account.address1 + " " + account.address2 + " " + account.address3 + "</td>" +
                    "<td>" + account.status + "</td>" +
                    "<td>" + account.subDay + "</td>" +
                    "</tr>";
                tbody.append(row);
           }

            table.append(thead, tbody);
            
            $("#infoListDiv").append(table);
            
            var searchDiv = $("#searchDiv");
           
            // 기존 테이블 및 내용 삭제
            searchDiv.empty();

        	searchDiv.append(
        			"<br>"+
            		"<input type='text' class='form-control' id='selectKeyword' placeholder='검색어를 입력하세요'>"+
					"<br>"+        			
             		"<button id='searchButton'>검색</button>"
            )
          
            // 페이지 번호 출력
            pageNumDisplay(result.pager, infoType);
            
            // 페이지 당 출력 갯수를 출력
           pageSizeDisplay();
            
           viewSelect(infoType)
        },
        
        error: function(xhr) {
            alert("회원 정보를 불러오는 중에 오류가 발생했습니다. (에러 코드 = " + xhr.status + ")");
        }
    });
}

// 영화제 리스트 출력 함수
function festivalListDisplay(pageNum, pageSize, selectKeyword) {
	$("#viewSelect").show();
	$("#pageSizeSelect").show();
	$("#pageNumDiv").show();
	$("#searchDiv").show();
   page=pageNum;
   size=pageSize;
   keyword=selectKeyword;
   
    $.ajax({
        method: "GET",
        url: "<c:url value ="/admin/festival_list"/>",
        data: {"pageNum": pageNum, "pageSize": pageSize, "selectKeyword": selectKeyword},
        dataType: "json",
        success: function(result) {
           
           $("#infoListDiv").empty();
           
           // 썸네일로 보기를 눌렀을 때
           if (viewType === "thumbnail") {
        	   var gridDiv = $("<div>").attr("id", "grid").addClass("row"); // 클래스 추가
        	   
        	   if (result.festivalList.length == 0) { // 검색된 게시글이 없을 때
        	       var noResultMessage = $("<p>").text("검색된 영화제가 없습니다.");
        	       gridDiv.append(noResultMessage);
        	   } 
	        	   
       	       for (var i = 0; i < result.festivalList.length; i++) {
       	           var festival = result.festivalList[i];
       	           
       	           var festivalDiv = $("<div>").addClass("col-xs-6 col-md-3 product");
       	           
       	           var aFestivalDetail = $("<a>").attr('href', 'javascript:festivalDetail(' + festival.idx + ');').addClass('product-link');
       	           
       	           var imgThumbnail = $("<img>").attr("src", '<c:url value="/resources/upload/' + festival.mainImg + '" />').attr("alt", festival.subject + " 포스터").addClass('card-img-top').attr('style','height: 300px !important');
       	           
       	           var shopTitleDiv = $("<div>").addClass("shop_title card-body");
       	           
       	           
       	           var strongTitle = $("<h3>").addClass("product-title card-title").text(festival.subject);
       	           var percentComplete = $("<h6>").addClass("product-price").text("달성률 "+ festival.percentage +"%");
       	           
       	           // 요소들을 조립합니다.
       	           shopTitleDiv.append(strongTitle, percentComplete);
       	           festivalDiv.append(aFestivalDetail, imgThumbnail, shopTitleDiv);
       	           
       	           gridDiv.append(festivalDiv); // 요소를 infoListDiv에 추가합니다.
       	       }
       	       
       	       $("#infoListDiv").append(gridDiv); // 요소를 infoListDiv에 추가합니다.
             
             // 목록으로 보기를 눌렀을 때
            } else if(viewType === "list") {
               var table = $("<table>").attr("id", "festivalInfoTable");
                var thead = $("<thead>").append(
                      "<tr>" +
                       "<th>영화제 번호</th>" +
                       "<th>영화제 제목</th>" +
                       "<th>영화제 시작일</th>" +
                       "<th>영화제 종료일</th>" +
                       "<th>펀딩 시작일</th>" +
                       "<th>펀딩 종료일</th>" +
                       "<th>등록 상태</th>" +
                       "<th>달성율</th>" +
                       "<th>후원 금액</th>" +
                       "<th>후원자 수</th>" +
                       "</tr>"
                );
   
                var tbody = $("<tbody>");
   
                if (result.festivalList.length == 0) { // 검색된 게시글이 없을 때
                    var row = "<tr>" +
                       "<td colspan='10'>검색된 영화제가 없습니다.</td>" +
                       "</tr>";
                    tbody.append(row);
                }
                for (var i = 0; i < result.festivalList.length; i++) {
                    var festival = result.festivalList[i];
                    var row = "<tr data-idx='" + festival.idx + "'>";
                    
                    if (festival.state == 0) {
                        row += "<td>" + festival.idx + "</td>";
                    } else if (festival.state == 1) {
                        row += "<td style='background-color: #87CEEB;'>" + festival.idx + "</td>";
                    } else if (festival.state == 2) {
                        row += "<td style='background-color: green;'>" + festival.idx + "</td>";
                    } else if (festival.state == 3) {
                        row += "<td style='background-color: #D3D3D3;'>" + festival.idx + "</td>";
                    } else if (festival.state == 4) {
                        row += "<td style='background-color: #D3D3D3;'>" + festival.idx + "</td>";
                    } else {
                        row += "<td style='background-color: gray;'>" + festival.idx + "</td>";
                    }
                    
                    row += "<td>" + festival.subject + "</td>" +
                       	   "<td>" + festival.startDay + "</td>" +
                           "<td>" + festival.endDay + "</td>" +
                           "<td>" + festival.fundingStart + "</td>" +
                           "<td>" + festival.fundingEnd + "</td>";
                           
                    if (festival.state == 0) {
                        row += "<td>승인 전</td>";
                    } else if (festival.state == 1) {
                        row += "<td style='background-color: #87CEEB;'>후원 예정</td>";
                    } else if (festival.state == 2) {
                        row += "<td style='background-color: green;'>후원 중</td>";
                    } else if (festival.state == 3) {
                        row += "<td style='background-color: #D3D3D3;'>후원 종료</td>";
                    } else if (festival.state == 3){
                        row += "<td style='background-color: red;'>후원 거부</td>";
                    } else {
                        row += "<td style='background-color: gray;'>삭제</td>";
                    }
                           
                    row += "<td>" + festival.percentage + "%</td>" +
                           "<td>" + festival.collected + "원</td>" +
                           "<td>" + festival.sponsor + "명</td>" +
                           "</tr>";
                   tbody.append(row);
               }
               
                table.append(thead, tbody);
               
                $("#infoListDiv").append(table);
           }

           var searchDiv = $("#searchDiv");
            
            // 기존 테이블 및 내용 삭제
            searchDiv.empty();

            searchDiv.append(
            		"<br>"+
        		 	"<input type='text' class='form-control' id='selectKeyword' placeholder='검색어를 입력하세요'>"+
				 	"<br>"+        			
          		 	"<button id='searchButton'>검색</button>"
            )
          
            // 페이지 번호 출력
            pageNumDisplay(result.pager, infoType);
            
            // 페이지 당 출력 갯수를 출력
           pageSizeDisplay();
            
           viewSelect(infoType)
        },
        error: function(xhr) {
            alert("영화제 정보를 불러오는 중에 오류가 발생했습니다. (에러 코드 = " + xhr.status + ")");
        }
      
    });
}

// 질문글 목록 출력 함수
function questionListDisplay(pageNum, pageSize, selectKeyword) {
	$("#viewSelect").hide();
	$("#pageSizeSelect").show();
	$("#pageNumDiv").show();
	$("#searchDiv").show();
   page=pageNum;
   size=pageSize;
   keyword=selectKeyword;
   
    $.ajax({
        method: "GET",
        url: "<c:url value ="/admin/question_list"/>",
        data: {"pageNum": pageNum, "pageSize": pageSize, "selectKeyword": selectKeyword},
        dataType: "json",
        success: function(result) {
            $("#infoListDiv").empty();
            
            var table = $("<table>").attr("id", "questionInfoTable");
            var thead = $("<thead>").append(
                "<tr>" +
                "<th>질문글번호</th>" +
                "<th>아이디</th>" +
                "<th>제목</th>" +
                "<th>작성일</th>" +
                "<th>답변일</th>" +
                "</tr>"
            );

            var tbody = $("<tbody>");

            if (result.questionList.length == 0) { // 검색된 게시글이 없을 때
                var row = "<tr>" +
                    "<td colspan='10'>검색된 Q&A가 없습니다.</td>" +
                    "</tr>";
                tbody.append(row);
            } 
            for (var i = 0; i < result.questionList.length; i++) {
                var question = result.questionList[i];
                var row = "<tr data-idx='" + question.idx + "'>" +
                    "<td>" + question.idx + "</td>" +
                    "<td>" + question.accountId + "</td>" +
                    "<td>" + question.title + "</td>" +
                    "<td>" + question.day  + "</td>" +
                    "<td></td>" +
                    "</tr>";
                tbody.append(row);
                if(question.answerContent != null && question.answerContent != '') {
                   var row2 = "<tr data-idx='" + question.idx + "' style='background : orange'>" +
                    "<td></td>" +
                    "<td></td>" +
                    "<td> ↳답변: " + question.title + "</td>" +
                    "<td></td>" +
                    "<td>" + question.answerDay + "</td>" +
                    "</tr>";
                tbody.append(row2);
                }
           }

            table.append(thead, tbody);
            
            $("#infoListDiv").append(table);
            
            var searchDiv = $("#searchDiv");
           
            // 기존 테이블 및 내용 삭제
            searchDiv.empty();

            searchDiv.append(
            		"<br>"+
        		 	"<input type='text' class='form-control' id='selectKeyword' placeholder='검색어를 입력하세요'>"+
				 	"<br>"+        			
          		 	"<button id='searchButton'>검색</button>"
            )
          
            // 페이지 번호 출력
            pageNumDisplay(result.pager, infoType);
            
            // 페이지 당 출력 갯수를 출력
           pageSizeDisplay();
            
           viewSelect(infoType)
        },
        
        error: function(xhr) {
            alert("Q&A 정보를 불러오는 중에 오류가 발생했습니다. (에러 코드 = " + xhr.status + ")");
        }
    });
}

//공지 사항 목록 출력 함수
function noticeListDisplay(pageNum, pageSize, selectKeyword) {
	$("#viewSelect").show();
	$("#pageSizeSelect").show();
	$("#pageNumDiv").show();
	$("#searchDiv").show();
   page=pageNum;
   size=pageSize;
   keyword=selectKeyword;
   
    $.ajax({
        method: "GET",
        url: "<c:url value ="/admin/notice_list"/>",
        data: {"pageNum": pageNum, "pageSize": pageSize, "selectKeyword": selectKeyword},
        dataType: "json",
        success: function(result) {
           	$("#infoListDiv").empty();
            
            var table = $("<table>").attr("id", "noticeInfoTable");
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

            if (result.noticeList.length == 0) { // 검색된 게시글이 없을 때
                var row = "<tr>" +
                    "<td colspan='10'>검색된 공지 사항이 없습니다.</td>" +
                    "</tr>";
                tbody.append(row);
            } 
            for (var i = 0; i < result.noticeList.length; i++) {
                var notice = result.noticeList[i];
            	var noticeFile = notice.fileData !== null ? notice.fileData : "";
                var row = "<tr data-idx='" + notice.idx + "'>" +
                    "<td>" + notice.idx + "</td>" +
                    "<td>" + notice.title + "</td>" +
                    "<td>" + notice.content + "</td>" +
                    "<td>" + notice.day + "</td>" +
                    "<td>" + noticeFile + "</td>" +
                    "<td>" + notice.count+ "</td>" +
                    "</tr>";
                tbody.append(row);
           }

            table.append(thead, tbody);
            
            $("#infoListDiv").append(table);
            
            var searchDiv = $("#searchDiv");
           
            // 기존 테이블 및 내용 삭제
            searchDiv.empty();

            searchDiv.append(
            		"<br>"+
        		 	"<input type='text' class='form-control' id='selectKeyword' placeholder='검색어를 입력하세요'>"+
				 	"<br>"+        			
          		 	"<button id='searchButton'>검색</button>"
            )
          
            // 페이지 번호 출력
            pageNumDisplay(result.pager, infoType);
            
            // 페이지 당 출력 갯수를 출력
            pageSizeDisplay();
            
            viewSelect(infoType)
        },
        
        error: function(xhr) {
            alert("공지 사항을 불러오는 중에 오류가 발생했습니다. (에러 코드 = " + xhr.status + ")");
        }
    });
}

// 페이지 관리 목록 출력 함수
function pageManageDisplay() {
	   $("#viewSelect").hide();
	   $("#pageSizeSelect").hide();
	   $("#pageNumDiv").hide();
	   $("#searchDiv").hide();
	   
	   $.ajax({
	        method: "GET",
	        url: "<c:url value='/admin/page_manage'/>",
	        dataType: "html",
	        success: function(html) {
	            var pageManageDiv = $("<div>").html(html); // HTML을 DOM 요소로 변환
	            $("#infoListDiv").empty().append(pageManageDiv); // 기존 내용 지우고 새로운 내용 삽입
	        },
	        error: function(xhr) {
	            alert("페이지 관리를 불러오는 중에 오류가 발생했습니다. 에러 코드 = (" + xhr.status + ")");
	        }
	    });
}

// 영화제 보기 시 [썸네일 / 목록] 보기 다른 경우 [등록하기]
function viewSelect(infoType) {
	var viewSelectDiv = $("#viewSelect");
	viewSelectDiv.empty();
	
	if (infoType === 'account') {
		viewSelectDiv.append('<button id="addAdmin" class="btn btn-primary">관리자 등록하기</button>');
	} else if(infoType === 'festival') {
		viewSelectDiv.append(
		        '<button id="thumbnailViewButton" class="btn btn-primary">썸네일</button>' +
		        '<button id="listViewButton" class="btn btn-primary">목록</button>'
		    );
	} else if(infoType === 'notice') {
		viewSelectDiv.append('<button id="addNotice" class="btn btn-primary">공지사항 등록하기</button>');
	}
	
}

// 페이지 번호를 출력하는 함수
function pageNumDisplay(pager, infoType) {
    var html = "";
    if (pager.startPage > pager.blockSize) {
        html += "<a href=\"javascript:" + infoType + "ListDisplay(" + pager.prevPage + ", " + size + ", '" + keyword + "');\" class='btn btn-direction btn-default btn-rounded'><i class='fa fa-long-arrow-left'/></a>";
    } else {
        html += "<a class='btn btn-direction btn-default btn-rounded' disabled><i class='fa fa-long-arrow-left'/></a>";
    }

    for (var i = pager.startPage; i <= pager.endPage; i++) {
        if (pager.pageNum != i) {
            html += "<a class='btn btn-direction btn-default btn-rounded' href=\"javascript:" + infoType + "ListDisplay(" + i + ", " + size + ", '" + keyword + "');\">" + i + "</a>";
        } else {
            html += "<a class='btn btn-direction btn-default btn-rounded' disabled>" + i + "</a>";
        }
    }

    if (pager.endPage != pager.totalPage) {
        html += "<a href=\"javascript:" + infoType + "ListDisplay(" + pager.prevPage + ", " + size + ", '" + keyword + "');\" class='btn btn-direction btn-default btn-rounded'><i class='fa fa-long-arrow-right'/></a>";
    } else {
        html += "<a class='btn btn-direction btn-default btn-rounded' disabled><i class='fa fa-long-arrow-right'/></a>";
    }

    $("#pageNumDiv").html(html);
}

// 페이지 당 출력 갯수를 설정하는 함수
function pageSizeDisplay() {
    // 페이지 크기 선택
    var pageSizeSelect = $("#pageSizeSelect");

    pageSizeSelect.empty(); // 기존 옵션 초기화

    var pageSizeOptions = [10, 20, 50, 100];
    for (var i = 0; i < pageSizeOptions.length; i++) {
        var optionValue = pageSizeOptions[i];
        var optionText = optionValue + "개씩 보기";

        var option = $("<option>").val(optionValue).text(optionText);

        if (optionValue === size) {
            option.prop("selected", true);
        }
        pageSizeSelect.append(option);
    }
}

// 회원 상세 정보를 열람하기 위한 함수
function accountDetail(id) {
	$("#viewSelect").hide();
	$("#pageSizeSelect").hide();
	$("#pageNumDiv").hide();
	$("#searchDiv").hide();
    $.ajax({
        method: "GET",
        url: "<c:url value='/admin/account_detail'/>",
        data: {"id": id},
        dataType: "html",
        success: function(html) {
            var accountDetailDiv = $("<div>").html(html); // HTML을 DOM 요소로 변환
            $("#infoListDiv").empty().append(accountDetailDiv); // 기존 내용 지우고 새로운 내용 삽입
        },
        error: function(xhr) {
            alert("상세 정보를 불러오는 중에 오류가 발생했습니다. 에러 코드 = (" + xhr.status + ")");
        }
    });
}

// 영화제 상세 정보를 열람하기 위한 함수
function festivalDetail(idx) {
	$("#viewSelect").hide();
	$("#pageSizeSelect").hide();
	$("#pageNumDiv").hide();
	$("#searchDiv").hide();
    $.ajax({
        method: "GET",
        url: "<c:url value='/admin/festival_detail'/>",
        data: {"idx": idx},
        dataType: "html",
        success: function(html) {
            var festivalDetailDiv = $("<div>").html(html); // HTML을 DOM 요소로 변환
            $("#infoListDiv").empty().append(festivalDetailDiv); // 기존 내용 지우고 새로운 내용 삽입
        },
        error: function(xhr) {
            alert("상세 정보를 불러오는 중에 오류가 발생했습니다. 에러 코드 = (" + xhr.status + ")");
        }
    });
}

// 질문글 상세 정보를 열람하기 위한 함수
function questionDetail(idx) {
	$("#viewSelect").hide();
	$("#pageSizeSelect").hide();
	$("#pageNumDiv").hide();
	$("#searchDiv").hide();
    $.ajax({
        method: "GET",
        url: "<c:url value='/admin/question_detail'/>",
        data: {"idx": idx},
        dataType: "html",
        success: function(html) {
            var questionDetailDiv = $("<div>").html(html); // HTML을 DOM 요소로 변환
            $("#infoListDiv").empty().append(questionDetailDiv); // 기존 내용 지우고 새로운 내용 삽입
        },
        error: function(xhr) {
            alert("질문글을 불러오는 중에 오류가 발생했습니다. 에러 코드 = (" + xhr.status + ")");
        }
    });
}

// 공지 사항 상세 정보를 열람하기 위한 함수
function noticeDetail(idx) {
	$("#viewSelect").hide();
	$("#pageSizeSelect").hide();
	$("#pageNumDiv").hide();
	$("#searchDiv").hide();
    $.ajax({
        method: "GET",
        url: "<c:url value='/admin/notice_detail'/>",
        data: {"idx": idx},
        dataType: "html",
        success: function(html) {
            var noticeDetailDiv = $("<div>").html(html); // HTML을 DOM 요소로 변환
            $("#infoListDiv").empty().append(noticeDetailDiv); // 기존 내용 지우고 새로운 내용 삽입
        },
        error: function(xhr) {
            alert("공지 사항을 불러오는 중에 오류가 발생했습니다. 에러 코드 = (" + xhr.status + ")");
        }
    });
}

//새로운 관리자 계정 생성 시 사용하는 함수
function addAdmin() {
	$("#viewSelect").hide();
	$("#pageSizeSelect").hide();
	$("#pageNumDiv").hide();
	$("#searchDiv").hide();
	$.ajax({
        method: "GET",
        url: "<c:url value='/admin/account_add'/>",
        dataType: "html",
        success: function(html) {
            var adminAddDiv = $("<div>").html(html); // HTML을 DOM 요소로 변환
            $("#infoListDiv").empty().append(adminAddDiv); // 기존 내용 지우고 새로운 내용 삽입
        },
        error: function(xhr) {
            alert("관리자 계성 생성 중 오류가 발생하였습니다. 에러 코드 = (" + xhr.status + ")");
        }
    });
}

//새로운 공지 사항 작성 시 사용하는 함수
function addNotice() {
	$("#viewSelect").hide();
	$("#pageSizeSelect").hide();
	$("#pageNumDiv").hide();
	$("#searchDiv").hide();
	$.ajax({
        method: "GET",
        url: "<c:url value='/admin/notice_add'/>",
        dataType: "html",
        success: function(html) {
            var adminAddDiv = $("<div>").html(html); // HTML을 DOM 요소로 변환
            $("#infoListDiv").empty().append(adminAddDiv); // 기존 내용 지우고 새로운 내용 삽입
        },
        error: function(xhr) {
            alert("공지 사항 작성 중 오류가 발생하였습니다. 에러 코드 = (" + xhr.status + ")");
        }
    });
}

// 검색하는 함수
function performSearch() {
    var selectKeyword = $("#selectKeyword").val();
    if (infoType == "account") {
        accountListDisplay(1, size, selectKeyword);
    } else if (infoType == "festival") {
        festivalListDisplay(1, size, selectKeyword);
    } else if (infoType == "question") {
        questionListDisplay(1, size, selectKeyword);
    } else if (infoType == "notice") {
        noticeListDisplay(1, size, selectKeyword);
    }
}

$(document).ready(function() {
	$("#pageSizeSelect").hide();
   	$("#viewSelect").hide();
   
    // 회원 관리 버튼 클릭 시
    $("#account-info").click(function() {
	   	$("#viewSelect").show();
 	    $("#pageSizeSelect").show();
	    $("#pageNumDiv").show();
	    $("#searchDiv").show();
        page = 1;
        size = 20;
        keyword ='';
        infoType = "account";
        accountListDisplay(page, size, keyword);
       
        $("#pageSizeSelect").val(size);
   });
    
   // 영화제 관리 버튼 클릭 시
   $("#festival-info").click(function() {
	   $("#viewSelect").show();
	   $("#pageSizeSelect").show();
	   $("#pageNumDiv").show();
	   $("#searchDiv").show();
       page = 1;
       size = 20;
       keyword ='';
       infoType = "festival";
       viewType = "list";
       festivalListDisplay(page, size, keyword);
       
       $("#pageSizeSelect").val(size);
   });
   
   // 질문글 관리 버튼 클릭 시
   $("#question-info").click(function() {
	   $("#viewSelect").show();
	   $("#pageSizeSelect").show();
	   $("#pageNumDiv").show();
	   $("#searchDiv").show();
       page = 1;
       size = 20;
       keyword ='';
       infoType = "question";
       questionListDisplay(page, size, keyword);
       
       $("#pageSizeSelect").val(size);
   });
   
   // 공지 사항 버튼 클릭 시
   $("#notice-info").click(function() {
	   $("#viewSelect").show();
	   $("#pageSizeSelect").show();
	   $("#pageNumDiv").show();
	   $("#searchDiv").show();
       page = 1;
       size = 20;
       keyword ='';
       infoType = "notice";
       noticeListDisplay(page, size, keyword);
       
       $("#pageSizeSelect").val(size);
   });
   
   // 페이지 관리 버튼 클릭 시
   $("#page-manage").click(function() {
	   pageManageDisplay();
   });
   
   // 한 페이지 당 출력 갯수를 변경햇을 시
   $("#pageSizeSelect").change(function() {
	   var functionName = infoType + "ListDisplay";
	   var selectedPageSize = parseInt($(this).val());
	    
	    // 동적으로 함수 호출
	   window[functionName](page, selectedPageSize, keyword);
	});
   
   // 썸네일로 보기 버튼 클릭 시
   $("#viewSelect").on("click", "#thumbnailViewButton", function() {
        viewType = "thumbnail";
        festivalListDisplay(page, size, keyword);
    });

   // 목록으로 보기 버튼 클릭 시
   $("#viewSelect").on("click", "#listViewButton", function() {
        viewType = "list";
        festivalListDisplay(page, size, keyword);
    });
   
   $("#viewSelect").on("click", "#addAdmin", function() {
	   addAdmin();
   });
   
	$("#viewSelect").on("click", "#addNotice", function() {
	   addNotice();
   });
   
   // 검색 버튼 클릭 시
   $("#searchDiv").on("click", "#searchButton", function() {
        performSearch();
    });

    // Enter 키를 눌렀을 때 검색 버튼 클릭과 동일한 기능 실행
    $(document).on("keydown", "#selectKeyword", function(event) {
        if (event.key === "Enter") {
            performSearch();
        }
    });
    
    // 회원 tr 태그 클릭 시 상세 정보 보기로 이동
    $("#infoListDiv").on("click", "#accountInfoTable tbody tr", function() {
        var id = $(this).data("id");
        accountDetail(id);
    });
    
    // 영화제 tr 태그 클릭 시 상세 정보 보기로 이동
    $("#infoListDiv").on("click", "#festivalInfoTable tbody tr", function() {
        var idx = $(this).data("idx");
        festivalDetail(idx);
    });
    
    // 질문글 tr 태그 클릭 시 상세 정보 보기로 이동
    $("#infoListDiv").on("click", "#questionInfoTable tbody tr", function() {
        var idx = $(this).data("idx");
        questionDetail(idx);
    });
    
    // 공지사항 tr 태그 클릭 시 상세 정보 보기로 이동
    $("#infoListDiv").on("click", "#noticeInfoTable tbody tr", function() {
        var idx = $(this).data("idx");
        noticeDetail(idx);
    });
});
</script>
<!-- / javascript -->
</body>

</html>