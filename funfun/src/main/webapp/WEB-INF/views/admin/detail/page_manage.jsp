<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
<!-- my-account -->
<section id="my-account">
    <div class="container">
        <div class="row">
         <!-- 사용자 정보 -->
            <div class="col-sm-10 account-info">
                <div id="my-donations" class="account-info-content">
                    <h4>마감 임박 영화제 설정<span class="pull-right"><button id="modify-main" type="button" class="btn btn-sm btn-default btn-rounded no-margin"><i class="lnr lnr-pencil"></i><span>수정</span></button></span></h4>
                    <div id="indexMainDiv"></div>
                    <hr>
                    <div id="mainListDiv"></div>
                    <button id="submit-modify-main" type="submit" class="btn btn-primary btn-rounded no-margin pull-right">저장</button>&nbsp;&nbsp;
                      <button id="cancel-modify-main" type="button" class="btn btn-primary btn-rounded no-margin pull-right">취소</button>
                    <div id="mainPageNumDiv"></div>
                </div><!-- / shipping-info -->


                <div id="my-orders" class="account-info-content">
                    <h4>기대평 많은 순 영화제 설정<span class="pull-right"><button id="modify-expect" type="button" class="btn btn-sm btn-default btn-rounded no-margin"><i class="lnr lnr-pencil"></i><span>수정</span></button></span></h4>
                    <div id="indexExpectDiv"></div>
                    <hr>
                    <div id="expectListDiv"></div>
                      <button id="submit-modify-expect" type="submit" class="btn btn-primary btn-rounded no-margin pull-right">저장</button>&nbsp;&nbsp;
                      <button id="cancel-modify-expect" type="button" class="btn btn-primary btn-rounded no-margin pull-right">취소</button>
                    <div id="expectPageNumDiv"></div>
                </div><!-- / my-orders -->

                <br>
            </div><!-- / account-info -->

        </div><!-- / row -->
    </div><!-- / container -->
</section>

<script type="text/javascript">
   var mainPage = 1;
   var expectPage = 1;

   var checkedMainFestivalList = [];
   var checkedExpectFestivalList = [];
    
   // 현재 선택되어 있는 영화제를 출력하는 함수
   function displaySelectedData() {
       $.ajax({
           method: "GET",
           url: "<c:url value ='/admin/selected_list'/>",
           dataType: "json",
           success: function (result) {
              
              $("#indexMainDiv").empty();
              $("#mainListDiv").empty();
              $("#indexExpectDiv").empty();
              $("#expectListDiv").empty();
                 $("#mainPageNumDiv").empty();
                 $("#expectPageNumDiv").empty();
              
             var gridMainDiv = $("<div>").attr("id", "gridMain").addClass("row"); // 클래스 추가
             var gridExpectDiv = $("<div>").attr("id", "gridExpect").addClass("row"); // 클래스 추가
             
             if (result.indexMainFestival.length == 0) { // 검색된 게시글이 없을 때
                 var noResultMessage = $("<p>").text("현재 선택된 영화제가 없습니다.");
                 gridMainDiv.append(noResultMessage);
             } 
             
             if (result.indexExpectFestival.length == 0) { // 검색된 게시글이 없을 때
                 var noResultMessage = $("<p>").text("현재 선택된 영화제가 없습니다.");
                 gridExpectDiv.append(noResultMessage);
             }
                   
             for (var i = 0; i < result.indexMainFestival.length; i++) {
                var selected = result.indexMainFestival[i];
                 
                 var selectedDiv = $("<div>").addClass("col-xs-6 col-md-3 product card");
                 
                 var selectedA = $("<a>").attr('href', 'javascript:festivalDetail(' + selected.idx + ');').addClass('product-link');
                 
                 var selectedImg = $("<img>").attr("src", '<c:url value="/resources/upload/' + selected.mainImg + '" />').attr("alt", selected.subject + " 포스터").addClass('card-img-top').attr('style','height: 300px !important');
                 
                 var shopTitleDiv = $("<div>").addClass("shop_title card-body");
                 
                 var strongTitle = $("<h3>").addClass("product-title card-title").text(selected.subject);
                 
                 // 요소들을 조립합니다.
                 shopTitleDiv.append(strongTitle);
                 
                 selectedDiv.append(selectedA, selectedImg, shopTitleDiv);
                 
                 gridMainDiv.append(selectedDiv);
                 
                 checkedMainFestivalList.push(selected.idx);
              }
             
             for (var i = 0; i < result.indexExpectFestival.length; i++) {
                var selected = result.indexExpectFestival[i];
                 
                 var selectedDiv = $("<div>").addClass("col-xs-6 col-md-3 product card");
                 
                 var selectedA = $("<a>").attr('href', 'javascript:festivalDetail(' + selected.idx + ');').addClass('product-link');
                 
                 var selectedImg = $("<img>").attr("src", '<c:url value="/resources/upload/' + selected.mainImg + '" />').attr("alt", selected.subject + " 포스터").addClass('card-img-top').attr('style','height: 300px !important');
                 
                 var shopTitleDiv = $("<div>").addClass("shop_title card-body");
                 
                 var strongTitle = $("<h3>").addClass("product-title card-title").text(selected.subject);
                 
                 // 요소들을 조립합니다.
                 shopTitleDiv.append(strongTitle);
                 
                 selectedDiv.append(selectedA, selectedImg, shopTitleDiv);
                 
                 gridExpectDiv.append(selectedDiv);
                 
                 checkedExpectFestivalList.push(selected.idx);
             }
             
             $("#indexMainDiv").append(gridMainDiv);
             $("#indexExpectDiv").append(gridExpectDiv);
             
           },
           error: function (xhr) {
               alert("데이터를 불러오는 중에 오류가 발생했습니다. (에러 코드 = " + xhr.status + ")");
           }
       });
   }
   
   function displayMainList(mainPageNum) {
      mainPage = mainPageNum;
      
       $.ajax({
           method: "GET",
           url: "<c:url value ='/admin/main_list'/>",
           data: {
               "mainPageNum": mainPageNum,
           },
           dataType: "json",
           success: function (result) {
              $("#mainListDiv").empty();
              
             var gridMainDiv = $("<div>").attr("id", "gridMainList").addClass("row"); // 클래스 추가
             
             if (result.festivalList.length == 0) { // 검색된 게시글이 없을 때
                 var noResultMessage = $("<p>").text("검색된 영화제가 없습니다.");
                 gridMainDiv.append(noResultMessage);
             } 
             
             for (var i = 0; i < result.festivalList.length; i++) {
                var selected = result.festivalList[i];
                 
                 var selectedDiv = $("<div>").addClass("col-xs-6 col-md-3 product card");
                 
                   var checkSpan = $("<span>").addClass("pull-right");
                 
                   // 체크박스 생성
                 var checkbox = $("<input>").attr("style", "position: relative; z-index: 0; zoom: 2.0;").attr("type", "checkbox").attr("name", "selectedMainFestivals").val(selected.idx);

                 if (checkedMainFestivalList.includes(selected.idx)) {
                     checkbox.prop("checked", true); // 선택된 영화제는 체크됨
                 } else {
                     checkbox.prop("checked", false); // 선택되지 않은 영화제는 체크 해제됨
                 }
                 
                 var selectedA = $("<a>").attr("style", "position: relative;").attr('href', 'javascript:festivalDetail(' + selected.idx + ');').addClass('product-link');
                 
                 var selectedImg = $("<img>").attr("src", '<c:url value="/resources/upload/' + selected.mainImg + '" />').attr("alt", selected.subject + " 포스터").addClass('card-img-top').attr('style','height: 300px !important');
                 
                 var shopTitleDiv = $("<div>").addClass("shop_title card-body");
                 
                 var strongTitle = $("<h3>").addClass("product-title card-title").text(selected.subject);
                 
                 // 요소들을 조립합니다.
                 shopTitleDiv.append(strongTitle);
                 
                 checkSpan.append(checkbox);
                 
                 selectedDiv.append(checkSpan, selectedA, selectedImg, shopTitleDiv);
                 
                 gridMainDiv.append(selectedDiv);
              }
             $("#mainListDiv").append(gridMainDiv);
             
             mainPageNumDisplay(result.pager);

             $("#submit-modify-main").show();
             $("#cancel-modify-main").show();
             
             console.log(checkedMainFestivalList);
           },
           error: function (xhr) {
               alert("데이터를 불러오는 중에 오류가 발생했습니다. (에러 코드 = " + xhr.status + ")");
           }
       });
   }
   
   function displayExpectList(expectPageNum) {
      expectPage = expectPageNum;
      
       $.ajax({
           method: "GET",
           url: "<c:url value ='/admin/expect_list'/>",
           data: {
               "expectPageNum": expectPageNum,
           },
           dataType: "json",
           success: function (result) {
            $("#expectListDiv").empty();
              
             var gridMainDiv = $("<div>").attr("id", "gridExpectList").addClass("row"); // 클래스 추가
             
             if (result.festivalList.length == 0) { // 검색된 게시글이 없을 때
                 var noResultMessage = $("<p>").text("검색된 영화제가 없습니다.");
                 gridMainDiv.append(noResultMessage);
             } 
             
             for (var i = 0; i < result.festivalList.length; i++) {
                var selected = result.festivalList[i];
                 
                 var selectedDiv = $("<div>").addClass("col-xs-6 col-md-3 product card");
                 
                   var checkSpan = $("<span>").addClass("pull-right");
                   
                   // 체크박스 생성
                 var checkbox = $("<input>").attr("style", "position: relative; z-index: 0; zoom: 2.0;").attr("type", "checkbox").attr("name", "selectedExpectFestivals").val(selected.idx);

                 if (checkedExpectFestivalList.includes(selected.idx)) {
                     checkbox.prop("checked", true); // 선택된 영화제는 체크됨
                 } else {
                     checkbox.prop("checked", false); // 선택되지 않은 영화제는 체크 해제됨
                 }
                 
                 var selectedA = $("<a>").attr("style", "position: relative; z-index: 0;").attr('href', 'javascript:festivalDetail(' + selected.idx + ');').addClass('product-link');
                 
                 var selectedImg = $("<img>").attr("src", '<c:url value="/resources/upload/' + selected.mainImg + '" />').attr("alt", selected.subject + " 포스터").addClass('card-img-top').attr('style','height: 300px !important');
                 
                 var shopTitleDiv = $("<div>").addClass("shop_title card-body");
                 
                 var strongTitle = $("<h3>").addClass("product-title card-title").text(selected.subject);
                 
                 // 요소들을 조립합니다.
                 shopTitleDiv.append(strongTitle);
                 
                 checkSpan.append(checkbox);
                 
                 selectedDiv.append(checkSpan, selectedA, selectedImg, shopTitleDiv);
                 
                 gridMainDiv.append(selectedDiv);
              }
             $("#expectListDiv").append(gridMainDiv);
             
             expectPageNumDisplay(result.pager);
             
             $("#submit-modify-expect").show();
             $("#cancel-modify-expect").show();
             console.log(checkedExpectFestivalList);
           },
           error: function (xhr) {
               alert("데이터를 불러오는 중에 오류가 발생했습니다. (에러 코드 = " + xhr.status + ")");
           }
       });
   }
   
   function mainPageNumDisplay(pager) {
       var html = "";
       if (pager.startPage > pager.blockSize) {
          html += "<a href=\"javascript:displayMainList(" + pager.prevPage + ");\" class='btn btn-direction btn-default btn-rounded'><i class='fa fa-long-arrow-left'/></a>";
       } else {
          html += "<a class='btn btn-direction btn-default btn-rounded' disabled><i class='fa fa-long-arrow-left'/></a>";
       }
   
       for (var i = pager.startPage; i <= pager.endPage; i++) {
           if (pager.pageNum != i) {
               html += "<a class='btn btn-direction btn-default btn-rounded' href=\"javascript:displayMainList(" + i + ");\">" + i + "</a>";
           } else {
               html += "<a class='btn btn-direction btn-default btn-rounded' disabled>" + i + "</a>";
           }
       }
   
       if (pager.endPage != pager.totalPage) {
           html += "<a href=\"javascript:displayMainList(" + pager.nextPage + ");\" class='btn btn-direction btn-default btn-rounded'><i class='fa fa-long-arrow-right'/></a>";
       } else {
           html += "<a class='btn btn-direction btn-default btn-rounded' disabled><i class='fa fa-long-arrow-right'/></a>";
       }
   
       $("#mainPageNumDiv").html(html);
   }
   
   function expectPageNumDisplay(pager) {
    var html = "";
    if (pager.startPage > pager.blockSize) {
        html += "<a href=\"javascript:displayExpectList(" + pager.prevPage + ");\" class='btn btn-direction btn-default btn-rounded'><i class='fa fa-long-arrow-left'/></a>";
    } else {
       html += "<a class='btn btn-direction btn-default btn-rounded' disabled><i class='fa fa-long-arrow-left'/></a>";
    }
   
    for (var i = pager.startPage; i <= pager.endPage; i++) {
        if (pager.pageNum != i) {
            html += "<a class='btn btn-direction btn-default btn-rounded' href=\"javascript:displayExpectList(" + i + ");\">" + i + "</a>";
        } else {
            html += "<a class='btn btn-direction btn-default btn-rounded' disabled>" + i + "</a>";
        }
    }
   
    if (pager.endPage != pager.totalPage) {
        html += "<a href=\"javascript:displayExpectList(" + pager.nextPage + ");\" class='btn btn-direction btn-default btn-rounded'><i class='fa fa-long-arrow-right'/></a>";
    } else {
        html += "<a class='btn btn-direction btn-default btn-rounded' disabled><i class='fa fa-long-arrow-right'/></a>";
    }
   
       $("#expectPageNumDiv").html(html);
   }
   
   $(document).ready(function() {
      $("#submit-modify-main").hide();
           $("#cancel-modify-main").hide();
      $("#submit-modify-expect").hide();
           $("#cancel-modify-expect").hide();
           
      displaySelectedData();
      
       $("#modify-main").click(function() {
          $("#submit-modify-main").show();
              $("#cancel-modify-main").show();
          displayMainList(mainPage);
       });
      
       $("#modify-expect").click(function() {
          $("#submit-modify-expect").show();
              $("#cancel-modify-expect").show();
          displayExpectList(expectPage);
       });
       
       $("#submit-modify-main").click(function(){
           $.ajax({
               type: "PUT",
               url: "<c:url value='/admin/main_modify'/>",
               contentType: "application/json",
               data: JSON.stringify({ 
                   "checkedMainFestivalList": checkedMainFestivalList
               }),
               dataType: "text",
               success: function(result) {
                  console.log(checkedMainFestivalList);
                   if (result == "success") {
                      console.log(checkedMainFestivalList);
                       $("#submit-modify-main").hide();
                       $("#cancel-modify-main").hide();
                       $("#submit-modify-expect").hide();
                       $("#cancel-modify-expect").hide();
                       
                       displaySelectedData();
                   }
               },
               error: function(xhr) {
                  console.log(checkedMainFestivalList);
                   alert("메인 페이지 편집 중 오류가 발생하였습니다("+ xhr.status+")");
               }
           });
       });

      $("#submit-modify-expect").click(function(){
         $.ajax({
             type: "PUT",
             url: "<c:url value="/admin/expect_modify"/>",
             contentType: "application/json",
             data: JSON.stringify({ 
                   "checkedExpectFestivalList": checkedExpectFestivalList
               }),
             dataType: "text",
             success: function(result) {
                if(result == "success") {
                   
                   $("#submit-modify-main").hide();
                        $("#cancel-modify-main").hide();
                   $("#submit-modify-expect").hide();
                        $("#cancel-modify-expect").hide();
                        
                   displaySelectedData();
                }
             },
             error: function(xhr) {
                alert("메인 페이지 편집 중 오류가 발생하였습니다("+ xhr.status+")");
             }
         });
       });
      

       $(document).on('change', "input[name='selectedMainFestivals']", function () {
            var value = parseInt($(this).val());
            if ($(this).is(':checked')) {
                checkedMainFestivalList.push(value);
            } else {
                checkedMainFestivalList = checkedMainFestivalList.filter(item => item !== value);
            }
            console.log(checkedMainFestivalList);
        });
   
        $(document).on('change', "input[name='selectedExpectFestivals']", function () {
            var value = parseInt($(this).val());
            if ($(this).is(':checked')) {
                checkedExpectFestivalList.push(value);
            } else {
                checkedExpectFestivalList = checkedExpectFestivalList.filter(item => item !== value);
            }
            console.log(checkedExpectFestivalList);
        });
      
      $("#cancel-modify-main").click(function(){
           $("#mainListDiv").empty();
           $("#mainPageNumDiv").empty();
           $("#submit-modify-main").hide();
          $("#cancel-modify-main").hide();
      });
      
      $("#cancel-modify-expect").click(function(){
           $("#expectListDiv").empty();
           $("#expectPageNumDiv").empty();
           $("#submit-modify-expect").hide();
          $("#cancel-modify-expect").hide();
      });
});
</script>
</body>
</html>