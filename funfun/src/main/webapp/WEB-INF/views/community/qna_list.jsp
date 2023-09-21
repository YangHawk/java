<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>question list</title>
 <style> 
  a{
     text-align: right;
     color: black;
  }
  table{
    border-collapse: collapse;
    width: 1000px;    
    margin-top: 100px;
    margin-bottom : 50px;
    margin-left : 350px;
    text-align: center;
  }
  td, th{
     border-bottom: 1px solid black;
     height: 50px;
  }
  th{
     font-size : 17px;
     text-align: center;
  }
  th.idx {
     width: 10%;
  }
  th.title {
     width: 60%;
  }
  th.day {
     width: 18%;
  }
  th.count {
     width: 12%;
  }
  thead{
     font-weight: 700;
  }
  .top_btn{
     font-size: 20px;
    padding: 6px 12px;
    background-color: #fff;
    border: 1px solid #ddd;
    font-weight: 600;
  }
  h1{
     text-align: center;
  }
.categories-sidebar-widget {
     max-width: 150px;
    margin-top: 150px;
    margin-left: 50px;
}
.widget-title {
    margin-top: 20px;
}
.noinBtn {
   width: 150px;
   margin-left: 370px;
   margin-bottom: 50px;
   display: block;
}
.secret-text {
    font-weight: bold;
}
#pageNumDiv {
    text-align: center; 
      margin-bottom: 50px;
      margin-left: 10px;
      margin-right: 450px;
}
.button-and-page-num {
    display: flex; /* Flexbox를 사용하여 내부 요소를 가로로 나열 */
    justify-content: space-between;
    align-items: center; /* 수직 가운데 정렬 */
}
#pageSizeSelect {
    margin-top: 10px;
}
</style>
</head>

<body>

<!-- preloader -->
<div id="preloader">
    <div class="spinner spinner-round"></div>
</div>
<!-- / preloader -->

<div id="page-header" class="question">
      <div class="container">
            <div class="page-header-content text-center">
                <div class="page-header wsub">
                    <h1 class="page-title fadeInDown animated first">Q&amp;A</h1>
                </div><!-- / page-header -->
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

      <!-- 한 번에 볼 게시글 수를 출력하는 태그 -->
          <select id="pageSizeSelect"></select>
          
      <!-- 검색 창을 출력하는 태그 -->
          <div id="searchDiv"></div>
      
      <!-- 게시글 목록을 출력하는 태그 -->
          <div id="infoListDiv"></div>
      
      <!-- 질문 등록 버튼 -->
         <div class="button-and-page-num">
               <button type="button" id="right-button" 
                class="noinBtn - btn btn-md btn-primary-filled btn-form-submit btn-rounded"
                onclick="window.location.href='/funfun/community/qna_form';">문의글 등록</button>
            
            <!-- 페이지 번호를 출력하는 태그 -->
            <div id="pageNumDiv"></div>
         </div>
                   

<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.easing.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/scrolling-nav.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/preloader.js"></script>

<script type="text/javascript">
//CSRF 토큰 관련 정보를 자바스크립트 변수에 저장
var csrfHeaderName = "${_csrf.headerName}";
var csrfTokenValue = "${_csrf.token}";

// Ajax 기능을 사용하여 요청하는 모든 웹 프로그램에게 CSRF 토큰 전달 가능
// ▶ Ajax 요청 시 beforeSend 속성을 설정할 필요 없음
$(document).ajaxSend(function(e, xhr){
   xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
});

var page = 1; // 기본 페이지 번호 설정
var size = 10; // 기본 페이지 크기 설정
var keyword = ''; // 기본 검색어 = NULL String
var infoType = ''; // 기본 검색 타입 = NULL String(account / festival / q&a / notice)

//qna 목록 출력 함수
function questionListDisplay(pageNum, pageSize, selectKeyword) {
   $("#viewSelect").show();
   $("#pageSizeSelect").show();
   $("#pageNumDiv").show();
   $("#searchDiv").show();
   page=pageNum;
   size=pageSize;
   keyword=selectKeyword;
   
    $.ajax({
        method: "GET",
        url: "<c:url value ="/community/qna_lists"/>",
        data: {"pageNum": pageNum, "pageSize": pageSize, "selectKeyword": selectKeyword},
        dataType: "json",
        success: function(result) {
            $("#infoListDiv").empty();
            
            var table = $("<table>").attr("id", "questionInfoTable");
            var thead = $("<thead>").append(
                "<tr>" +
                "<th>번호</th>" +
                "<th>제목</th>" +
                "<th>아이디</th>" +
                "<th>작성일</th>" +
                "<th>조회수</th>" +
                "<th>처리상태</th>" +
                "</tr>"
            );

            var tbody = $("<tbody>");

            if (result.questionList.length == 0) { // 검색된 게시글이 없을 때
                var row = "<tr>" +
                    "<td colspan='10'>검색된 문의글이 없습니다.</td>" +
                    "</tr>";
                tbody.append(row);
            } 
            for (var i = 0; i < result.questionList.length; i++) {
               console.log(${question.secret});
                var question = result.questionList[i];
                var accountId = question.accountId !== null ? question.accountId : "";
                var title = question.secret === 1 ? "<span class='secret-text'>[비밀글]</span> " + question.title : question.title;
                var process = question.answerContent !== null ? "<span class='completed'>답변완료</span>" : "<span class='waiting' style='color: lightgray;'>답변대기</span>";
                var row = "<tr data-idx='" + question.idx + "'>" +
                    "<td>" + question.idx + "</td>" +
                    "<td><a href=\"" + "${pageContext.request.contextPath}/community/qna_detail?idx=" + question.idx + "\">" + title + "</a></td>" +
                    "<td>" + accountId + "</td>" +
                    "<td>" + question.day + "</td>" +
                    "<td>" + question.count + "</td>" +
                    "<td>" + process + "</td>" +
                    "</tr>";
                tbody.append(row);
            }


            table.append(thead, tbody);
            
            $("#infoListDiv").append(table);
            
            var searchDiv = $("#searchDiv");
           
            // 기존 테이블 및 내용 삭제
            searchDiv.empty();

         searchDiv.append(
             "<button id='searchButton' class='btn btn-md btn-primary-filled btn-form-submit btn-rounded' style='float:right; margin-right:150px; height:48px;'>검색</button>"+
            "<input type='text' class='form-control' id='selectKeyword' placeholder='검색어를 입력하세요' style='width:250px; height:4px; float:right; margin-right:10px;'>"
            )
          
            // 페이지 번호 출력
            pageNumDisplay(result.pager, infoType);
            
            // 페이지 당 출력 갯수를 출력
           pageSizeDisplay();
        },
        
        error: function(xhr) {
            alert("문의글을 불러오는 중에 오류가 발생했습니다. (에러 코드 = " + xhr.status + ")");
        }
    });
}

//페이지 번호를 출력하는 함수
function pageNumDisplay(pager, infoType) {
    var html = "";
    if (pager.startPage > pager.blockSize) {
        html += "<a href=\"javascript:" + infoType + "ListDisplay(" + pager.prevPage + ", " + size + ", '" + keyword + "');\" class='btn btn-direction btn-default btn-rounded'><i class='fa fa-long-arrow-left'/></a>";
    } else {
        html += "<a class='btn btn-direction btn-default btn-rounded' disabled ><i class='fa fa-long-arrow-left'/></a>";
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

//QNA 상세 정보를 열람하기 위한 함수
function questionDetail(idx) {
    $.ajax({
        method: "GET",
        url: "/funfun/community/qna_detail?idx=" + idx,
        data: {"idx": idx},
        dataType: "html",
        success: function(html) {
            var questionDetailDiv = $("<div>").html(html); // HTML을 DOM 요소로 변환
            $("#infoListDiv").empty().append(questionDetailDiv); // 기존 내용 지우고 새로운 내용 삽입
        },
        error: function(xhr) {
            alert("문의글을 불러오는 중에 오류가 발생했습니다. 에러 코드 = (" + xhr.status + ")");
        }
    });
}

//검색하는 함수
function performSearch() {
    var selectKeyword = $("#selectKeyword").val();
      if (infoType == "question") {
        questionListDisplay(1, size, selectKeyword);
    } else if (infoType == "notice") {
        noticeListDisplay(1, size, selectKeyword);
    }
}

$(document).ready(function() { // JSP가 렌더링되자마자,
      $("#pageSizeSelect").hide(); 
      page = 1;
      size = 10;
      keyword ='';   
      infoType = "question";
      questionListDisplay(page, size, keyword);

      $("#pageSizeSelect").change(function() {
         var functionName = infoType + "ListDisplay";
         
          var selectedPageSize = parseInt($(this).val());
          window[functionName](page, selectedPageSize, keyword);
     });
      // 공지 사항 버튼 클릭 시
      $("#notice-info").click(function() {
         $("#viewSelect").show();
         $("#pageSizeSelect").show();
         $("#pageNumDiv").show();
         $("#searchDiv").show();
         page = 1;
         size = 10;
         keyword ='';
         infoType = "notice";
         noticeListDisplay(page, size, keyword);
          
          $("#pageSizeSelect").val(size);
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
       
       // QNA tr 태그 클릭 시 상세 정보 보기로 이동
       $("#infoListDiv").on("click", "#questionInfoTable tbody tr", function() {
           var idx = $(this).data("idx");
           window.location.href = "<c:url value='/community/qna_detail' />?idx=" + idx;
       });
});
</script>
</body>
</html>