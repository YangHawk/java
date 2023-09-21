<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html lang="en">
<style>
#pageNumDiv {
    text-align: center;
      margin-bottom: 50px;
      margin-left: 400px;
}
</style>
<body>

<!-- preloader -->
<div id="preloader">
    <div class="spinner spinner-round"></div>
</div>
<!-- / preloader -->

<div id="page-header" class="shop-full">
<img src="${pageContext.request.contextPath}/resources/images/shopfull_background.jpg" alt="">
    <div class="container">
        <div class="page-header-content text-center">
            <div class="page-header wsub">
                <h1 class="page-title fadeInDown animated first">영화제</h1>
            </div><!-- / page-header -->
        </div><!-- / page-header-content -->
    </div><!-- / container -->
</div><!-- / page-header -->

<!-- shop 3col -->
<section id="shop">
    <div class="container">
        <div class="row">
            <div class="col-sm-12 content-area">
                <p class="shop-results">
                    <span class="pull-right">
                        <select id="viewSelect" class="selectpicker">
                         <option value="all">전체 영화제</option>
                         <option value="ongoing">진행 중 영화제</option>
                         <option value="upcoming">진행 예정 영화제</option>
                         <option value="sponsor">후원자 순 영화제</option>
                         <option value="collected">금액 순 영화제</option>
                        </select>
                    </span>
                  <span class="pull-left">
                     <select id="pageSizeSelect"></select>
                  </span>                    
                   </p>
                   <div id="grid" class="row"></div><!-- / row -->
            </div><!-- / content-area -->
        </div><!-- / row -->
    </div><!-- / container -->
</section>
<!-- / shop 3col -->
               <div id="searchDiv"></div>
            
                <div id="pageNumDiv"></div>

<!-- / content -->

<!-- javascript -->
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.easing.min.js"></script>

<!-- shuffle grid-resizer -->
<script src="${pageContext.request.contextPath}/resources/js/jquery.shuffle.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/custom.js"></script>
<!-- / shuffle grid-resizer -->

<!-- preloader -->
<script src="${pageContext.request.contextPath}/resources/js/preloader.js"></script>
<!-- / preloader -->
<script type="text/javascript">
//CSRF 토큰 관련 정보를 자바스크립트 변수에 저장
var csrfHeaderName = "${_csrf.headerName}";
var csrfTokenValue = "${_csrf.token}";

// Ajax 기능을 사용하여 요청하는 모든 웹 프로그램에게 CSRF 토큰 전달 가능
// ▶ Ajax 요청 시 beforeSend 속성을 설정할 필요 없음
$(document).ajaxSend(function(e, xhr){
   xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
});

var loginId = null;

<sec:authorize access="isAuthenticated()">
   var loginId="<sec:authentication property="principal.id"/>";
   
   var loginIdElement = document.createElement("textarea");
	loginIdElement.innerHTML = loginId;
	var loginIdDecoded = loginIdElement.value;
</sec:authorize>

var page = 1;
var size = 12;
var keyword = '';
var view = "all";
var select = "${select}";
var wishList ='';
//영화제 리스트 출력 함수
function festivalListDisplay(pageNum, pageSize, selectKeyword, viewType) {
   page=pageNum;
   size=pageSize;
   keyword=selectKeyword;
   view=viewType;
   
    $.ajax({
        method: "GET",
        url: "<c:url value ="/donation/festival_list"/>",
        data: ({"pageNum": pageNum, "pageSize": pageSize, "selectKeyword": selectKeyword, "viewType":viewType}),
        dataType: "json",
        async: false,
        success: function(result) {
           
           $("#grid").empty();
               var grid = $("#grid");
               
               if (result.festivalList.length == 0) { // 검색된 게시글이 없을 때
                   var noResultMessage = $("<p>").text("검색된 영화제가 없습니다.");
                   grid.append(noResultMessage);
               } 
                     
               for (var i = 0; i < result.festivalList.length; i++) {
                   var festival = result.festivalList[i];
                   
                   var festivalDiv = $("<div>").addClass("col-xs-6 col-md-3 product card");
                   
                   // "javascript:festivalDetail(festival.idx);"와 같은 JavaScript 함수 호출로 변경
                   var aFestivalDetail = $("<a>").attr('href', '<c:url value="/donation/single-festival?idx=' + festival.idx+ '" />').addClass('product-link');
                   
                   // 이미지 경로를 직접 지정
                   var imgThumbnail = $("<img>").attr("src", '<c:url value="/resources/upload/' + festival.mainImg + '" />').attr("alt", festival.subject + " 포스터").addClass('shop-mainImg card-img-top').attr("height","100px");
                   
                   var hoverDiv = $("<div>").addClass("product-hover-tools");
                   
                   // "javascript:festivalWish(festival.idx);"와 같은 JavaScript 함수 호출로 변경
                   var hoverA = $("<a>").attr('href', 'javascript:javascript:void(0)').addClass('view-btn');
                   
                   // wishList 내에 festival.idx가 있는지 확인
                   if (wishListHasFestivalIdx(festival.idx)) {
                       heartImg = $("<img>").attr("src", '<c:url value="/resources/images/heart.png" />').attr("width", "50px").attr("height", "50px");
                   } else {
                       heartImg = $("<img>").attr("src", '<c:url value="/resources/images/empty_heart.png" />').attr("width", "50px").attr("height", "50px");
                   }
                   
                   var shopTitleDiv = $("<div>").addClass("shop_title card-body");
                   var strongTitle = $("<h3>").addClass("product-title card-title").text(festival.subject);
                   var percentComplete = $("<h6>").addClass("product-price card-text").text("달성률 "+ festival.percentage +"%");
                   
                   // 요소들을 조립합니다.
                   hoverA.append(heartImg);
                   hoverDiv.append(hoverA);
                   if (wishListHasFestivalIdx(festival.idx)) {
                      hoverDiv.attr("id", "heart_"+festival.idx).attr("data-festival-idx", festival.idx);
                   } else {
                      hoverDiv.attr("id", "emptyHeart_"+festival.idx).attr("data-festival-idx", festival.idx);
                   }
                   shopTitleDiv.append(strongTitle, percentComplete);
                   festivalDiv.append(aFestivalDetail, imgThumbnail, hoverDiv, shopTitleDiv);
                   
                   grid.append(festivalDiv);
               }
               
               var shuffleDiv = $("<div>").addClass('col-xs-6 col-md-3 shuffle_sizer');
               
               grid.append(shuffleDiv)
           
           var searchDiv = $("#searchDiv");
            
            // 기존 테이블 및 내용 삭제
            searchDiv.empty();

            searchDiv.append(
                    "<button id='searchButton' class='btn btn-md btn-primary-filled btn-form-submit btn-rounded' style='float:right; margin-right:200px; height:48px;'>검색</button>"+
                    "<input type='text' class='form-control' id='selectKeyword' placeholder='검색어를 입력하세요' style='width:250px; height:4px; float:right; margin-right:10px; margin-left:50px;'>"
                    )
          
            // 페이지 번호 출력
           pageNumDisplay(result.pager);
            
            // 페이지 당 출력 갯수를 출력
           pageSizeDisplay();
            
           viewSelectDisplay();
        },
        error: function(xhr) {
            alert("영화제 정보를 불러오는 중에 오류가 발생했습니다. (에러 코드 = " + xhr.status + ")");
        }
      
    });
}

//페이지 번호를 출력하는 함수
function pageNumDisplay(pager) {
    var html = "";
    if (pager.startPage > pager.blockSize) {
        html += "<a href=\"javascript:festivalListDisplay(" + pager.prevPage + ", " + size + ", '" + keyword + "', '"+ view +"');\" class='btn btn-direction btn-default btn-rounded'><i class='fa fa-long-arrow-left'/></a>";
    } else {
        html += "<a class='btn btn-direction btn-default btn-rounded' disabled><i class='fa fa-long-arrow-left'/></a>";
    }

    for (var i = pager.startPage; i <= pager.endPage; i++) {
        if (pager.pageNum != i) {
            html += "<a class='btn btn-direction btn-default btn-rounded' href=\"javascript:festivalListDisplay(" + i + ", " + size + ", '" + keyword + "', '"+ view +"');\">" + i + "</a>";
        } else {
            html += "<a class='btn btn-default btn-rounded btn-active' disabled>" + i + "</a>";
        }
    }

    if (pager.endPage != pager.totalPage) {
//         html += "<a href=\"javascript:festivalListDisplay(" + pager.prevPage + ", " + size + ", '" + keyword + "', '"+ view +"');\" class='btn btn-direction btn-default btn-rounded'><i class='fa fa-long-arrow-right'/></a>";
    } else {
        html += "<a class='btn btn-direction btn-default btn-rounded' disabled><i class='fa fa-long-arrow-right'/></a>";
    }

    $("#pageNumDiv").html(html);
}

//페이지 당 출력 갯수를 설정하는 함수
function pageSizeDisplay() {
    // 페이지 크기 선택
    var pageSizeSelect = $("#pageSizeSelect");

    pageSizeSelect.empty(); // 기존 옵션 초기화

    var pageSizeOptions = [12, 24, 36, 48];
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

function viewSelectDisplay() {
    var viewSelect = $("#viewSelect");

    viewSelect.empty();

    var viewSelectOptions = ["all", "ongoing", "upcoming", "sponsor", "collected"];
    var viewSelectTexts = ["전체 영화제", "진행 중 영화제", "진행 예정 영화제", "후원자 순 영화제", "금액 순 영화제"];
    for (var i = 0; i < viewSelectOptions.length; i++) { // "length" 올바르게 수정
        var optionValue = viewSelectOptions[i];
        var optionText = viewSelectTexts[i];

        var option = $("<option>").val(optionValue).text(optionText);

        if (optionValue === view) {
            option.prop("selected", true);
        }
        viewSelect.append(option);
    }
}

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

//AJAX 요청으로 Wish 목록을 가져오는 함수
function getWishList() {
    $.ajax({
        type: "GET",
        url: "<c:url value='/donation/wish_list'/>", // 실제 URL에 맞게 변경
        contentType: "application/json",
        dataType: "json",
        async: false,
        success: function (data) {
            wishList = data;
        },
        error: function (xhr, status, error) {
            console.error("오류: " + error);
        }
    });
}


//검색하는 함수
function performSearch() {
   var selectKeyword = $("#selectKeyword").val();
    festivalListDisplay(1, size, selectKeyword, view);
}

$(document).ready(function() {
   getWishList();
   
   if(select === "all") {
      view = "all";
   } else if(select === "ongoing") {
      view = "ongoing";
   } else if(select === "upcoming") {
      view = "upcoming";
   } else if(select === "sponsor") {
      view = "sponsor";
   } else if(select === "collected") {
      view = "collected";
   } else {
      view = "all";
   }
   
   document.getElementById("grid").style.overflow = "visible";
   
    festivalListDisplay(page, size, keyword, view);
    
    $("#pageSizeSelect").val(size);
   
   // 한 페이지 당 출력 갯수를 변경햇을 시
   $("#pageSizeSelect").change(function() {
      var selectedPageSize = parseInt($(this).val());
      festivalListDisplay(page, selectedPageSize, keyword, view);
   });
   
   // 부모 요소에 이벤트 리스너 추가
   $("#grid").on("click", "[id^='emptyHeart_']", function() {
       var festivalIdx = $(this).data("festival-idx"); // data-festival-idx 속성을 통해 festivalIdx 값을 읽어옴

       $.ajax({
           type: "PUT",
           url: "<c:url value='/donation/wish_add'/>",
           contentType: "application/json",
           data: JSON.stringify({"accountId": loginIdDecoded, "festivalIdx": festivalIdx }),
           dataType: "text",
           async: false,
           success: function(result) {
               if(result == "success") {
                   getWishList();
                   festivalListDisplay(page, size, keyword, view);
               }
           },
           error: function(xhr) {
               alert("찜하기 중 오류가 발생하였습니다("+ xhr.status+")");
           }
       });
   });

   // 부모 요소에 이벤트 리스너 추가
   $("#grid").on("click", "[id^='heart_']", function() {
       var festivalIdx = $(this).data("festival-idx"); // data-festival-idx 속성을 통해 festivalIdx 값을 읽어옴

       $.ajax({
           type: "DELETE",
           url: "<c:url value='/donation/wish_remove'/>",
           contentType: "application/json",
           data: JSON.stringify({"accountId": loginIdDecoded, "festivalIdx": festivalIdx }),
           dataType: "text",
           async: false,
           success: function(result) {
               if(result == "success") {
                   getWishList();
                   festivalListDisplay(page, size, keyword, view);
               }
           },
           error: function(xhr) {
               alert("찜 취소 중 오류가 발생하였습니다("+ xhr.status+")");
           }
       });
   });
   
   // select 요소 변경 이벤트 리스너
   $("#viewSelect").change(function() {
       var selectedView = $(this).val();
       
       if (selectedView === "all") {
           view = "all";
       } else if (selectedView === "ongoing") {
           view = "ongoing";
       } else if (selectedView === "upcoming") {
           view = "upcoming";
       } else if (selectedView === "sponsor") {
           view = "sponsor";
       } else if (selectedView === "collected") {
           view = "collected";
       } else {
          view = "all";   
       }
       festivalListDisplay(page, size, keyword, view);
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
});
</script>

<!-- / javascript -->
</body>

</html>