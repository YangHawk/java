<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
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
                    <h1 class="page-title fadeInDown animated first">나의 영화제</h1>
                </div><!-- / page-header -->
                <sec:authentication property="principal" var="pinfo"/>
                <p class="slide-text fadeInUp animated second">${pinfo.name } 님의 영화제 등록 현황입니다.</p>
            </div><!-- / page-header-content -->
        </div><!-- / container -->
    </div><!-- / page-header -->
<!-- content -->

<!-- my-account -->
<section id="my-festival">
    <div class="container">
        <div class="row">

            <div class="col-sm-2 account-sidebar">
                <p><a href="#approvedFestival" class="page-scroll">승인된 영화제</a></p>
            <p><a href="#pendingFestival" class="page-scroll">승인 보류 중 영화제</a></p>
            <p><a href="#rejectedFestival" class="page-scroll">승인 거부된 영화제</a></p>
            </div><!-- / account-sidebar -->


         <!-- 사용자 정보 -->
            <div class="col-sm-10 account-info">
                <div id="approvedFestival" class="account-info-content">
                    <h4>승인된 영화제</h4>
                    <p class="space-bottom"></p>
                    <div id="approvedListDiv"></div>
                </div><!-- / shipping-info -->
                        

                <div id="pendingFestival" class="account-info-content">
                    <h4>승인 보류 중 영화제</h4>
                    <p class="space-bottom"></p>
                    <div id="pendingListDiv"></div>
                </div><!-- / shipping-info -->


                <div id="rejectedFestival" class="account-info-content">
                    <h4>승인 거부된 영화제</h4>
                    <p class="space-bottom"></p>
                    <div id="rejectedListDiv"></div>
                </div><!-- / my-orders -->

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
      
   //CSRF 토큰 관련 정보를 자바스트립트 변수에 저장 
   var csrfHeaderName="${_csrf.headerName}";
   var csrfTokenValue="${_csrf.token}";
   
   <sec:authorize access="isAuthenticated()">
      var loginId="<sec:authentication property="principal.id"/>";
   </sec:authorize>
      
   //ajaxSend() 메소드를 호출하여 페이지에서 Ajax 기능으로 요청하는 모든 웹프로그램에게 CSRF 토큰 전달
   // => Ajax 요청시 beforeSend 속성을 설정 불필요
   $(document).ajaxSend(function(e, xhr) {
      xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
   });

   var festivalUrl = "${pageContext.request.contextPath}/my_festival/"

   function getMyFestivalsData(loginId) {
       $.ajax({
           method: "GET",
           url: festivalUrl + loginId,
           data: { "id": loginId },
           dataType: "json",
           success: function (result) {
               // result 객체에서 approvedFestival, pendingFestival, rejectedFestival 데이터를 추출
               var approvedFestivalData = result.approvedFestival;
               var pendingFestivalData = result.pendingFestival;
               var rejectedFestivalData = result.rejectedFestival;
   
               displayApprovedData(approvedFestivalData);
               displayPendingData(pendingFestivalData);
               displayRejectedData(rejectedFestivalData);
               
           },
           error: function (xhr) {
               alert("데이터를 불러오는 중에 오류가 발생했습니다. (에러 코드 = " + xhr.status + ")");
           }
       });
   }
   
   //승인된 영화제 목록을 보여주는 함수
   function displayApprovedData(approvedFestivalData) {
      $("#approvedListDiv").empty();
       
       var table = $("<table>").attr("id", "approvedTable");
       var thead = $("<thead>").append(
           "<tr>" +
           "<th class='image' style='width: 10%; text-align: center;'></th>" +
           "<th style='width: 10%;'>영화제 제목</th>" +
           "<th>목표금액</th>" +
           "<th>모인금액</th>" +
           "<th>펀딩 기간</th>" +
           "<th></th>" +
           "<th></th>" +
           "<th></th>" +
           "</tr>"
       );
   
       var tbody = $("<tbody>");
   
       if (approvedFestivalData.length == 0) { // 검색된 영화제가 없을 때
           var row = "<tr>" +
               "<td colspan='5'>승인된 영화제가 없습니다.</td>" +
               "</tr>";
           tbody.append(row);
       }
       for (var i = 0; i < approvedFestivalData.length; i++) {
           var approved = approvedFestivalData[i];
           var imagePath = '${pageContext.request.contextPath}/resources/upload/' + approved.mainImg;
            var row = "<tr id='festivalListTr'>" +
               "<td class='image'><a href='${pageContext.request.contextPath}/donation/single-festival?idx=" + approved.idx + "'><img src='" + imagePath + "' style='width:50px; height:50px; object-fit: cover;'/></a></td>" +
               "<td>" + approved.subject + "</td>" +
               "<td>" + approved.target + "</td>" +
               "<td>" + approved.collected + "</td>" +
               "<td>" + approved.fundingStart +" ~ "+ approved.fundingEnd + "</td>" +
               "<td><a href='${pageContext.request.contextPath}/donation/festival_update?idx="+approved.idx+"&id="+approved.accountId+"' class='btn btn-sm btn-default btn-rounded no-margin'><span>수정</span></a></td>"+
               "<td><a class='btn btn-sm btn-default btn-rounded no-margin removeFestival' data-idx='"+approved.idx+"' data-account-id='"+approved.accountId+"'><span>삭제</span></a></td>"+ 
               "<td><a class='btn btn-sm btn-default btn-rounded no-margin showFilmList' data-idx='"+approved.idx+ "'><span>출품영화</span></a></td>"+ 
               "</tr>"+
              "<tr><td class='filmListTd' data-idx='"+approved.idx+ "' colspan = '8' style='display: none;'></td></tr>";
           tbody.append(row);
       }
   
       table.append(thead, tbody);
       
       $("#approvedListDiv").append(table);
   }
   
   //승인 보류 중인 영화제 목록을 보여주는 함수
   function displayPendingData(pendingFestivalData) {
      $("#pendingListDiv").empty();
       
       var table = $("<table>").attr("id", "pendingTable");
       var thead = $("<thead>").append(
           "<tr>" +
           "<th class='image' style='width: 20%; text-align: center;'></th>" +
           "<th>영화제 제목</th>" +
           "<th>목표금액</th>" +
           "<th>펀딩 기간</th>" +
           "</tr>"
       );
   
       var tbody = $("<tbody>");
   
       if (pendingFestivalData.length == 0) { // 검색된 영화제가 없을 때
           var row = "<tr>" +
               "<td colspan='5'>승인 보류 중인 영화제가 없습니다.</td>" +
               "</tr>";
           tbody.append(row);
       }
       for (var i = 0; i < pendingFestivalData.length; i++) {
           var pending = pendingFestivalData[i];
          var imagePath = '${pageContext.request.contextPath}/resources/upload/' + pending.mainImg;
           var row = "<tr>" +
               "<td class='image'><a href='${pageContext.request.contextPath}/donation/single-festival?idx=" + pending.idx + "'><img src='" + imagePath + "' style='width:50px; height:50px; object-fit: cover;'/></a></td>" +
               "<td>" + pending.subject + "</td>" +
               "<td>" + pending.target + "</td>" +
               "<td>" + pending.fundingStart +" ~ "+ pending.fundingEnd + "</td>" +
               "</tr>";
           tbody.append(row);
         }
   
       table.append(thead, tbody);
       
       $("#pendingListDiv").append(table);
   }
   
   //승인 거부된 영화제 목록을 보여주는 함수
   function displayRejectedData(rejectedFestivalData) {
      $("#rejectedListDiv").empty();
       
       var table = $("<table>").attr("id", "rejectedTable");
       var thead = $("<thead>").append(
           "<tr>" +
           "<th class='image' style='text-align: center;'></th>" +
           "<th>영화제 제목</th>" +
           "<th>목표금액</th>" +
           "<th>펀딩 기간</th>" +
           "</tr>"
       );
   
       var tbody = $("<tbody>");
   
       if (rejectedFestivalData.length == 0) { // 검색된 영화제가 없을 때
           var row = "<tr>" +
               "<td colspan='5'>승인 거부된 영화제가 없습니다.</td>" +
               "</tr>";
           tbody.append(row);
       }
       for (var i = 0; i < rejectedFestivalData.length; i++) {
           var rejected = rejectedFestivalData[i];
          var imagePath = '${pageContext.request.contextPath}/resources/upload/' + rejected.mainImg;
           var row = "<tr>" +
               "<td class='image'><a href='${pageContext.request.contextPath}/donation/single-festival?idx=" + rejected.idx + "'><img src='" + imagePath + "' style='width:50px; height:50px; object-fit: cover;'/></a></td>" +
               "<td>" + rejected.subject + "</td>" +
               "<td>" + rejected.target + "</td>" +
               "<td>" + rejected.fundingStart +" ~ "+ rejected.fundingEnd + "</td>" +
               "</tr>";
           tbody.append(row);
         }
   
       table.append(thead, tbody);
       
       $("#rejectedListDiv").append(table);
   }
   
   
   $(document).ready(function () {   
      getMyFestivalsData(loginId);
      
      $(document).on("click", ".removeFestival", function () {
         var idx = $(this).data("idx");
         var accountId = $(this).data("accountId")
         if (confirm("정말로 삭제하시겠습니까?")) {
            $.ajax({
               type: "get",
               url: "${pageContext.request.contextPath}/remove_festival",
               data: { "idx": idx, "accountId": accountId},
               contentType: "application/json",
               dataType: "text",
               success: function (result) {
                  alert("영화제가 삭제되었습니다.");
                  getMyFestivalsData(loginId);
               },
               error: function (xhr) {
                  alert("에러코드(영화제 삭제) = " + xhr.status);
               },
            });
         }
      });
      
      $(document).on("click", ".showFilmList", function () {
           var idx = $(this).data("idx");
           var filmListTd = $(".filmListTd[data-idx='" + idx + "']");

           if (filmListTd.is(":visible")) {
               filmListTd.slideUp();
           } else {
               $.ajax({
                   type: "GET",
                   url: "${pageContext.request.contextPath}/get_film_list/" + idx,
                   data: { "idx": idx },
                   dataType: "json",
                   success: function (result) {
                       filmListTd.empty();

                       var table = $("<table>").attr("id", "filmListTable");
                       var thead = $("<thead>").append(
                           "<th class='image' style='text-align: center;'></th>" +
                           "<th>제목</th>" +
                           "<th>감독</th>" +
                           "<th>상영날짜</th>" +
                           "<th>상영시간</th>"+
                           "<th colspan='2'><a href='${pageContext.request.contextPath}/donation/film_register?festivalIdx="+idx+"' class='btn btn-rounded' style='background:#BFBFBF; color:black; font-size: 12px;'><span>영화 추가</span></a></th>"
                       );
                       var tbody = $("<tbody>");
                       
                       if (result.length == 0) { // 검색된 영화제가 없을 때
                          var row = "<tr>" +
                              "<td colspan='7'>출품 영화가 없습니다.</td>" +
                              "</tr>";
                          tbody.append(row);
                      }

                       for (var i = 0; i < result.length; i++) {
                           var film = result[i];
                           var filmImagePath = '${pageContext.request.contextPath}/resources/upload/' + film.img;
                           var row = "<tr>" +
                               "<td><img src='" + filmImagePath + "' style='width:50px; height:50px; object-fit: cover;'/></td>" +
                               "<td>" + film.title + "</td>" +
                               "<td>" + film.director + "</td>" +
                               "<td>" + film.day + "</td>" +
                               "<td>" + film.time + "</td>" +
                               "<td><a href='${pageContext.request.contextPath}/donation/film_update?idx="+film.idx+"&accountId="+film.accountId+"' class='btn btn-rounded' style='background:#BFBFBF; color:black;'><span>수정</span></a></td>"+
                               "<td><a class='btn btn-rounded removeFilm' style='background:#BFBFBF; color:black;' data-idx='"+film.idx+"' data-account-id='"+film.accountId+"'><span>삭제</span></a></td>"+
                               "</tr>";
                           tbody.append(row);
                       }

                       table.append(thead, tbody);
                       filmListTd.append(table);
                       filmListTd.slideDown();
                   },
                   error: function (xhr) {
                       alert("에러코드(영화 목록 가져오기) = " + xhr.status);
                   },
               });
           }
       });
      
      $(document).on("click", ".removeFilm", function () {
         var idx = $(this).data("idx");
         var accountId = $(this).data("accountId");
         if (confirm("정말로 삭제하시겠습니까?")) {
            $.ajax({
               type: "get",
               url: "${pageContext.request.contextPath}/remove_film",
               data: { "idx": idx, "accountId" : accountId },
               contentType: "application/json",
               dataType: "text",
               success: function (result) {
                  alert("영화가 삭제되었습니다.");
                  getMyFestivalsData(loginId);
               },
               error: function (xhr) {
                  alert("에러코드(게시글 삽입) = " + xhr.status);
               },
            });
         }
      });
});
</script>

<!-- / javascript -->
</body>

</html>