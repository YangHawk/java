<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>notice list</title>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
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
   margin-bottom: 50px;
   display: block;
}
#pageNumDiv {
    text-align: center;
   	margin-bottom: 50px;
   	margin-left: 100px;
}
.button-and-page-num {
    display: flex; /* Flexbox를 사용하여 내부 요소를 가로로 나열 */
    justify-content: center;
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

<div id="page-header" class="notice">
        <div class="container">
            <div class="page-header-content text-center">
                <div class="page-header wsub">
                    <h1 class="page-title fadeInDown animated first">공지사항</h1>
                </div><!-- / page-header -->
            </div><!-- / page-header-content -->
        </div><!-- / container -->
    </div><!-- / page-header -->

<!-- 사이드바 -->
   <div class="col-sm-4 col-md-3 sidebar-area">
      <div class="categories-sidebar-widget widget no-border">
         <h5 class="widget-title">커뮤니티</h5>
         <p class="product-category">
            <a href="javascript:void(0)" id="notice-info">공지사항</a> <span class="pull-right"></span>
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

			    <!-- 공지사항 등록 버튼 -->
			<div class="button-and-page-num">
				<sec:authorize access="hasRole('ROLE_ADMIN')">
			        <button type="button" id="right-button" 
			        class="noinBtn - btn btn-md btn-primary-filled btn-form-submit btn-rounded"
			        onclick="window.location.href='/funfun/community/notice/form';">공지사항 등록</button>
			    </sec:authorize>
			    
			    <!-- 페이지 번호를 출력하는 태그 -->
			    <div id="pageNumDiv"></div>
			</div>

                
   

<!-- 
<form action="">
<button type="submit"></button>
</form>
 -->
 
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.easing.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/scrolling-nav.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/preloader.js"></script>

<script type="text/javascript">
var page = 1; // 기본 페이지 번호 설정
var size = 10; // 기본 페이지 크기 설정
var keyword = ''; // 기본 검색어 = NULL String
var infoType = ''; // 기본 검색 타입 = NULL String(account / festival / q&a / notice)

//공지 사항 목록 출력 함수
function noticeListDisplay(pageNum, pageSize, selectKeyword) {
	$("#pageSizeSelect").show();
	$("#pageNumDiv").show();
	$("#searchDiv").show();
   page=pageNum;
   size=pageSize;
   keyword=selectKeyword;
   
    $.ajax({
        method: "GET",
        url: "<c:url value='/community/notice_list'/>",
        data: {"pageNum": pageNum, "pageSize": pageSize, "selectKeyword": selectKeyword},
        dataType: "json",
        success: function(result) {
            $("#infoListDiv").empty();
            
            var table = $("<table>").attr("id", "noticeInfoTable");
            var thead = $("<thead>").append(
                "<tr>" +
                "<th>번호</th>" +
                "<th>제목</th>" +
                "<th>작성일</th>" +
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
                var row = "<tr data-idx='" + notice.idx + "'>" +
                    "<td>" + notice.idx + "</td>" +
                    "<td><a href=\"<c:url value='/community/notice/notice_detail' />?idx=" + notice.idx + "\">" + 
                    		notice.title + "</a></td>" +
                    "<td>" + notice.day + "</td>" +
                    "<td>" + notice.count + "</td>" +
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
            alert("공지 사항을 불러오는 중에 오류가 발생했습니다. (에러 코드 = " + xhr.status + ")");
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

//공지 사항 상세 정보를 열람하기 위한 함수
function noticeDetail(idx) {
    $.ajax({
        method: "GET",
        url: "/funfun/community/notice/notice_detail?idx=" + idx,
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
      infoType = "notice";
      noticeListDisplay(page, size, keyword);

      $("#pageSizeSelect").change(function() {
    	  var functionName = infoType + "ListDisplay";
    	  
          var selectedPageSize = parseInt($(this).val());
          window[functionName](page, selectedPageSize, keyword);
     });
	   // 공지 사항 버튼 클릭 시
	   $("#notice-info").click(function() {
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
	    
	 // 공지사항 tr 태그 클릭 시 상세 정보 보기로 이동
	    $("#infoListDiv").on("click", "#noticeInfoTable tbody tr", function() {
	        var idx = $(this).data("idx");
	        window.location.href = "<c:url value='/community/notice/notice_detail' />?idx=" + idx;
	    });


});
</script>
</body>
</html>