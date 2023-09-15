<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<!-- preloader -->
<!-- 스피너 -->
<div id="preloader">
    <div class="spinner spinner-round"></div>
</div>
<!-- preloader -->


<!-- 메인배너 -->
<div id="header-sliders">
    <!-- slider -->
    <div id="slider" class="carousel slide">  
        <div class="carousel-inner">

                <!-- slide 1 -->
                <div class="item active slide1 fashion" style="background-image: url('${pageContext.request.contextPath}/resources/upload/${indexMainFestival[0].mainImg}');">
                    <div class="container">
                        <div class="carousel-caption">
                            <div class="row">
                                <div class="col-md-12 slider-content">
                                    <h3 class="slide-title fadeInDown animated first">
                                   	<span class="label label-pill label-default-filled">마감 ${indexMainFestival[0].deadline }일 전</span>
                                    </h3>
                                    <h3 class="slide-title fadeInDown animated first">
                                    	<span class="text-primary">${indexMainFestival[0].subject }</span>
                                    </h3>
                                    <p class="slide-text flipInX animated second" style="color: white">펀딩 기간 : ${indexMainFestival[0].fundingStart } ~ ${indexMainFestival[0].fundingEnd }</p>
                                    <a href="${pageContext.request.contextPath}/donation/single-festival?idx=${indexMainFestival[0].idx}" class="page-scroll btn btn-lg btn-primary-filled btn-pill fadeInUp animated third"><span class="lnr lnr-pointer-right"></span><span>지금 후원하러 가기</span></a>
                                </div><!-- slider-content -->
                            </div><!-- / row -->
                        </div><!-- / carousel-caption -->
                    </div><!-- / container -->
                </div><!-- / slide 1 -->

                <!-- slide 2 -->
                <div class="item slide2 furniture" style="background-image: url('${pageContext.request.contextPath}/resources/upload/${indexMainFestival[1].mainImg}');">
                    <div class="container">
                        <div class="carousel-caption">
                            <div class="row">
                                <div class="col-md-12 slider-content">
                                    <h3 class="slide-title fadeInDown animated first">
                                   	<span class="label label-pill label-default-filled">마감 ${indexMainFestival[1].deadline }일 전</span>
                                   	</h3>
                                    <h3 class="slide-title fadeInDown animated first">
                                    	<span class="text-primary">${indexMainFestival[1].subject }</span>
                                   	</h3>
                                    <p class="slide-text flipInX animated second" style="color: white">펀딩 기간 : ${indexMainFestival[1].fundingStart } ~ ${indexMainFestival[1].fundingEnd }</p>
                                    <a href="${pageContext.request.contextPath}/donation/single-festival?idx=${indexMainFestival[1].idx}" class="btn btn-lg btn-primary-filled btn-pill fadeInUp animated third"><i class="lnr lnr-store"></i> <span>지금 후원하러 가기</span></a>
                                </div><!-- slider-content -->
                            </div><!-- / row -->
                        </div><!-- / carousel-caption -->
                    </div><!-- / container -->
                </div><!-- / slide 2 -->

                <!-- slide 3 -->
                <div class="item slide3 art" style="background-image: url('${pageContext.request.contextPath}/resources/upload/${indexMainFestival[2].mainImg}');">
                    <div class="container">
                        <div class="carousel-caption">
                            <div class="row">
                                <div class="col-md-12 slider-content">
                                    <h3 class="slide-title fadeInDown animated first">
                                   	<span class="label label-pill label-default-filled">마감 ${indexMainFestival[2].deadline }일 전</span>
                                    </h3>
                                    <h3 class="slide-title fadeInDown animated first">
                                    	<span class="text-primary">${indexMainFestival[2].subject }</span>
                                    </h3>
                                    <p class="slide-text flipInX animated second" style="color: white">펀딩 기간 : ${indexMainFestival[2].fundingStart } ~ ${indexMainFestival[2].fundingEnd }</p>
                                    <a href="${pageContext.request.contextPath}/donation/single-festival?idx=${indexMainFestival[2].idx}" class="btn btn-lg btn-primary-filled btn-pill fadeInUp animated third"><i class="lnr lnr-store"></i> <span>지금 후원하러 가기</span></a>
                                </div><!-- slider-content -->
                            </div><!-- / row -->
                        </div><!-- / carousel-caption -->
                    </div><!-- / container -->
                </div><!-- / slide 3 -->

            </div><!-- /carousel-inner -->

            <!-- controls -->
            <a class="left carousel-control" href="#slider" data-slide="prev"><span class="lnr lnr-chevron-left" style="color: white"></span></a>
            <a class="right carousel-control" href="#slider" data-slide="next"><span class="lnr lnr-chevron-right" style="color: white"></span></a>
            <!-- / controls -->

        </div>
        <!-- / slider-->
    </div><!-- / sliders -->
    

<!-- 진행 중인 영화제 -->
<section id="featured-products">
    <div class="page-header text-left-padding wsub">
        <h2>진행 중인 영화제</h2>
    </div><!--/ page-header -->
    <div id="products-carousel" class="owl-carousel">
      <c:forEach items="${festivalList }" var="festival">
        <!-- 진행중인 영화제 1번 -->
        <div class="item product card">
            <!-- / product-link -->
            <img class="index-mainImg card-img-top" src="<c:url value='/resources/upload/${festival.mainImg}' />" alt="">
            <!-- / product-image -->
            <!-- 하트 아이콘 작업1 -->
              <!-- product-hover-tools -->
         <!-- <div class="product-hover-tools">
            <a href="single-product" class="view-btn">
               <img src="${pageContext.request.contextPath}/resources/images/empty_heart.png" alt="">
            </a>
         </div> --><!-- / product-hover-tools -->

            <!-- product-details -->
         <div class="shop_title card-body">
         <h2 class="product-title card-title">${festival.subject}</h2>
         <h6 class="product-price card-text" style="width: 100%;">펀딩 기간 : ${festival.fundingStart} ~ ${festival.fundingEnd} 까지</h6>
         <h6 class="product-price card-text" style="width: 100%;">달성률 : ${festival.percentage} % <span style="color: #D98F8F;">(목표 금액까지 ${festival.target - festival.collected}원)</span></h6>
         <span class="card-button"><a href="${pageContext.request.contextPath}/donation/single-festival?idx=${festival.idx}" class="btn btn-primary">지금 후원하러 가기</a></span>
         </div><!-- / product-details -->
      </div><!-- / product -->
      </c:forEach>
     </div> <!-- / products-carousel -->
    
</section>
<!-- / featured-products -->

<!-- 기대평 업데이트 -->
<section id="shop">
    <div class="page-header text-center wsub">
        <h2>기대가 되는 영화제</h2>
    </div>
    <!--/ page-header -->

    <!-- 첫번째 기대평 -->
    <div class="index_expect">
        <div class="col-sm-6">
            <a href="${pageContext.request.contextPath}/donation/single-festival?idx=${indexExpectFestival[0].idx}" class="product-link">
                <img class="index-festival-mainImg" src="<c:url value='/resources/upload/${indexExpectFestival[0].mainImg}' />" alt="">
            </a>
        </div>
        <div class="index_expect_con">
            <div class="index_expect_con_block">
                <div class="index_expect_con_block_1">
                    <table>
	                    <tr>
		                    <th colspan="2" class="index_expect_con_title"><h2>${indexExpectFestival[0].subject}</h2></th>
		                    <th class="index_expect_con_title">평점</th>
		                    <th class="index_expect_con_title"><h3>
							<c:choose>
		                            <c:when test="${starAvgList[0] == 5}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i></span>
		                            </c:when>
		                            <c:when test="${starAvgList[0] < 5 and starAvgList[0] > 4}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star-half-o"></i></span>
		                            </c:when>
		                            <c:when test="${starAvgList[0] == 4}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star-o"></i></span>
		                            </c:when>
		                            <c:when test="${starAvgList[0] < 4 and starAvgList[0] > 3}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star-half-o"></i><i class="fa fa-star-o"></i></span>
		                            </c:when>
		                            <c:when test="${indexExpectFestival[0].avgstar == 3}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i></span>
		                            </c:when>
		                            <c:when test="${starAvgList[0] < 3 and starAvgList[0] > 2}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star-half-o"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i></span>
		                            </c:when>
		                            <c:when test="${starAvgList[0] == 2}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i></span>
		                            </c:when>
		                            <c:when test="${starAvgList[0] < 2 and starAvgList[0] > 1}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star-half-o"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i></span>
		                            </c:when>
		                            <c:when test="${starAvgList[0] == 1}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i></span>
		                            </c:when>
		                            <c:otherwise>
		                                <p><span class="product-rating"><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i></span></p>
		                            </c:otherwise>
		                        </c:choose>
							</h3></th>
							<th>${starAvgList[0]}</th>
	                    </tr>
	                    <c:if test="${expectListMap[indexExpectFestival[0].idx][0] != null}">
	                    <tr>
	                    	<td>${expectListMap[indexExpectFestival[0].idx][0].accountId}</td>
	                    	<td colspan="3">${expectListMap[indexExpectFestival[0].idx][0].content}</td>
	                    	<td>
			                    <c:choose>
		                            <c:when test="${expectListMap[indexExpectFestival[0].idx][0].star == 5}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i></span>
		                            </c:when>
		                            <c:when test="${expectListMap[indexExpectFestival[0].idx][0].star == 4}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star-o"></i></span>
		                            </c:when>
		                            <c:when test="${expectListMap[indexExpectFestival[0].idx][0].star == 3}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i></span>
		                            </c:when>
		                            <c:when test="${expectListMap[indexExpectFestival[0].idx][0].star == 2}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i></span>
		                            </c:when>
		                            <c:when test="${expectListMap[indexExpectFestival[0].idx][0].star == 1}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i></span>
		                            </c:when>
		                            <c:otherwise>
		                                <p>유효하지 않은 별점입니다.</p>
		                            </c:otherwise>
		                        </c:choose>
	                    	</td>
	                    </tr>
		                </c:if>
			            <c:if test="${expectListMap[indexExpectFestival[0].idx][1] != null}">
	                    <tr>
	                    	<td>${expectListMap[indexExpectFestival[0].idx][1].accountId}</td>
	                    	<td colspan="3">${expectListMap[indexExpectFestival[0].idx][1].content}</td>
	                    	<td>
			                    <c:choose>
		                            <c:when test="${expectListMap[indexExpectFestival[0].idx][1].star == 5}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i></span>
		                            </c:when>
		                            <c:when test="${expectListMap[indexExpectFestival[0].idx][1].star == 4}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star-o"></i></span>
		                            </c:when>
		                            <c:when test="${expectListMap[indexExpectFestival[0].idx][1].star == 3}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i></span>
		                            </c:when>
		                            <c:when test="${expectListMap[indexExpectFestival[0].idx][1].star == 2}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i></span>
		                            </c:when>
		                            <c:when test="${expectListMap[indexExpectFestival[0].idx][1].star == 1}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i></span>
		                            </c:when>
		                            <c:otherwise>
		                                <p>유효하지 않은 별점입니다.</p>
		                            </c:otherwise>
		                        </c:choose>
	                    	</td>
	                    </tr>
		                </c:if>
	                    <c:if test="${expectListMap[indexExpectFestival[0].idx][2] != null}">
						<tr>
	                    	<td>${expectListMap[indexExpectFestival[0].idx][2].accountId}</td>
	                    	<td colspan="3">${expectListMap[indexExpectFestival[0].idx][2].content}</td>
	                    	<td>
			                    <c:choose>
		                            <c:when test="${expectListMap[indexExpectFestival[0].idx][2].star == 5}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i></span>
		                            </c:when>
		                            <c:when test="${expectListMap[indexExpectFestival[0].idx][2].star == 4}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star-o"></i></span>
		                            </c:when>
		                            <c:when test="${expectListMap[indexExpectFestival[0].idx][2].star == 3}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i></span>
		                            </c:when>
		                            <c:when test="${expectListMap[indexExpectFestival[0].idx][2].star == 2}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i></span>
		                            </c:when>
		                            <c:when test="${expectListMap[indexExpectFestival[0].idx][2].star == 1}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i></span>
		                            </c:when>
		                            <c:otherwise>
		                                <p>유효하지 않은 별점입니다.</p>
		                            </c:otherwise>
		                        </c:choose>
	                    	</td>
	                    </tr>
		                </c:if>
                    </table>
	            </div>
	        </div>
	    </div>
    </div>
    <p>&nbsp;</p>

    <!-- 두번째 기대평 -->
    <div class="index_expect">
        <div class="index_expect_con">
            <div class="index_expect_con_block">
                <div class="index_expect_con_block_1">
                    <table>
	                    <tr>
		                    <th colspan="2" class="index_expect_con_title"><h2>${indexExpectFestival[1].subject}</h2></th>
		                    <th class="index_expect_con_title">평점</th>
		                    <th class="index_expect_con_title"><h3>
							<c:choose>
		                            <c:when test="${starAvgList[1] == 5}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i></span>
		                            </c:when>
		                            <c:when test="${starAvgList[1] < 5 and starAvgList[1] > 4}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star-half-o"></i></span>
		                            </c:when>
		                            <c:when test="${starAvgList[1] == 4}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star-o"></i></span>
		                            </c:when>
		                            <c:when test="${starAvgList[1] < 4 and starAvgList[1] > 3}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star-half-o"></i><i class="fa fa-star-o"></i></span>
		                            </c:when>
		                            <c:when test="${starAvgList[1] == 3}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i></span>
		                            </c:when>
		                            <c:when test="${starAvgList[1] < 3 and starAvgList[1] > 2}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star-half-o"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i></span>
		                            </c:when>
		                            <c:when test="${starAvgList[1] == 2}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i></span>
		                            </c:when>
		                            <c:when test="${starAvgList[1] < 2 and starAvgList[1] > 1}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star-half-o"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i></span>
		                            </c:when>
		                            <c:when test="${starAvgList[1] == 1}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i></span>
		                            </c:when>
		                            <c:otherwise>
		                                <p><span class="product-rating"><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i></span></p>
		                            </c:otherwise>
		                        </c:choose>
							</h3></th>
							<th>${starAvgList[1]}</th>
	                    </tr>
	                    <c:if test="${expectListMap[indexExpectFestival[1].idx][0] != null}">
	                    <tr>
	                    	<td>${expectListMap[indexExpectFestival[1].idx][0].accountId}</td>
	                    	<td colspan="3">${expectListMap[indexExpectFestival[1].idx][0].content}</td>
	                    	<td>
			                    <c:choose>
		                            <c:when test="${expectListMap[indexExpectFestival[1].idx][0].star == 5}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i></span>
		                            </c:when>
		                            <c:when test="${expectListMap[indexExpectFestival[1].idx][0].star == 4}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star-o"></i></span>
		                            </c:when>
		                            <c:when test="${expectListMap[indexExpectFestival[1].idx][0].star == 3}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i></span>
		                            </c:when>
		                            <c:when test="${expectListMap[indexExpectFestival[1].idx][0].star == 2}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i></span>
		                            </c:when>
		                            <c:when test="${expectListMap[indexExpectFestival[1].idx][0].star == 1}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i></span>
		                            </c:when>
		                            <c:otherwise>
		                                <p>유효하지 않은 별점입니다.</p>
		                            </c:otherwise>
		                        </c:choose>
	                    	</td>
	                    </tr>
		                </c:if>
	                    <c:if test="${expectListMap[indexExpectFestival[1].idx][1] != null}">
	                    <tr>
	                    	<td>${expectListMap[indexExpectFestival[1].idx][1].accountId}</td>
	                    	<td colspan="3">${expectListMap[indexExpectFestival[1].idx][1].content}</td>
	                    	<td>
			                    <c:choose>
		                            <c:when test="${expectListMap[indexExpectFestival[1].idx][1].star == 5}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i></span>
		                            </c:when>
		                            <c:when test="${expectListMap[indexExpectFestival[1].idx][1].star == 4}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star-o"></i></span>
		                            </c:when>
		                            <c:when test="${expectListMap[indexExpectFestival[1].idx][1].star == 3}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i></span>
		                            </c:when>
		                            <c:when test="${expectListMap[indexExpectFestival[1].idx][1].star == 2}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i></span>
		                            </c:when>
		                            <c:when test="${expectListMap[indexExpectFestival[1].idx][1].star == 1}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i></span>
		                            </c:when>
		                            <c:otherwise>
		                                <p>유효하지 않은 별점입니다.</p>
		                            </c:otherwise>
		                        </c:choose>
	                    	</td>
	                    </tr>
		                </c:if>
	                    <c:if test="${expectListMap[indexExpectFestival[1].idx][2] != null}">
						<tr>
	                    	<td>${expectListMap[indexExpectFestival[1].idx][2].accountId}</td>
	                    	<td colspan="3">${expectListMap[indexExpectFestival[1].idx][2].content}</td>
	                    	<td>
			                    <c:choose>
		                            <c:when test="${expectListMap[indexExpectFestival[1].idx][2].star == 5}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i></span>
		                            </c:when>
		                            <c:when test="${expectListMap[indexExpectFestival[1].idx][2].star == 4}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star-o"></i></span>
		                            </c:when>
		                            <c:when test="${expectListMap[indexExpectFestival[1].idx][2].star == 3}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i></span>
		                            </c:when>
		                            <c:when test="${expectListMap[indexExpectFestival[1].idx][2].star == 2}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i></span>
		                            </c:when>
		                            <c:when test="${expectListMap[indexExpectFestival[1].idx][2].star == 1}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i></span>
		                            </c:when>
		                            <c:otherwise>
		                                <p>유효하지 않은 별점입니다.</p>
		                            </c:otherwise>
		                        </c:choose>
	                    	</td>
	                    </tr>
		                </c:if>
                    </table>
	            </div>
	        </div>
	    </div>
        <div class="col-sm-6">
            <a href="${pageContext.request.contextPath}/donation/single-festival?idx=${indexExpectFestival[1].idx}" class="product-link">
                <img class="index-festival-mainImg" src="<c:url value='/resources/upload/${indexExpectFestival[1].mainImg}' />" alt="">
            </a>
        </div>
    </div>

    <p>&nbsp;</p>
    <!-- 세번째 기대평 -->
    <div class="index_expect">
        <div class="col-sm-6">
            <a href="${pageContext.request.contextPath}/donation/single-festival?idx=${indexExpectFestival[2].idx}" class="product-link">
                <img class="index-festival-mainImg" src="<c:url value='/resources/upload/${indexExpectFestival[2].mainImg}' />" alt="">
            </a>
        </div>
        <div class="index_expect_con">
            <div class="index_expect_con_block">
                <div class="index_expect_con_block_1">
                    <table>
	                    <tr>
		                    <th colspan="2" class="index_expect_con_title"><h2>${indexExpectFestival[2].subject}</h2></th>
		                    <th class="index_expect_con_title">평점</th>
		                    <th class="index_expect_con_title"><h3> 
							<c:choose>
		                            <c:when test="${starAvgList[2] == 5}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i></span>
		                            </c:when>
		                            <c:when test="${starAvgList[2] < 5 and starAvgList[2] > 4}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star-half-o"></i></span>
		                            </c:when>
		                            <c:when test="${starAvgList[2] == 4}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star-o"></i></span>
		                            </c:when>
		                            <c:when test="${starAvgList[2] < 4 and starAvgList[2] > 3}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star-half-o"></i><i class="fa fa-star-o"></i></span>
		                            </c:when>
		                            <c:when test="${starAvgList[2] == 3}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i></span>
		                            </c:when>
		                            <c:when test="${starAvgList[2] < 3 and starAvgList[2] > 2}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star-half-o"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i></span>
		                            </c:when>
		                            <c:when test="${starAvgList[2] == 2}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i></span>
		                            </c:when>
		                            <c:when test="${starAvgList[2] < 2 and starAvgList[2] > 1}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star-half-o"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i></span>
		                            </c:when>
		                            <c:when test="${starAvgList[2] == 1}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i></span>
		                            </c:when>
		                            <c:otherwise>
		                                <p><span class="product-rating"><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i></span></p>
		                            </c:otherwise>
		                        </c:choose>
							</h3></th>
							<th>${starAvgList[2]}</th>
	                    </tr>
	                    <c:if test="${expectListMap[indexExpectFestival[2].idx][0] != null}">
	                    <tr>
	                    	<td>${expectListMap[indexExpectFestival[2].idx][0].accountId}</td>
	                    	<td colspan="3">${expectListMap[indexExpectFestival[2].idx][0].content}</td>
	                    	<td>
			                    <c:choose>
		                            <c:when test="${expectListMap[indexExpectFestival[2].idx][0].star == 5}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i></span>
		                            </c:when>
		                            <c:when test="${expectListMap[indexExpectFestival[2].idx][0].star == 4}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i></span>
		                            </c:when>
		                            <c:when test="${expectListMap[indexExpectFestival[2].idx][0].star == 3}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i></span>
		                            </c:when>
		                            <c:when test="${expectListMap[indexExpectFestival[2].idx][0].star == 2}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star"></i></span>
		                            </c:when>
		                            <c:when test="${expectListMap[indexExpectFestival[2].idx][0].star == 1}">
		                                <span class="product-rating"><i class="fa fa-star"></i></span>
		                            </c:when>
		                            <c:otherwise>
		                                <p>유효하지 않은 별점입니다.</p>
		                            </c:otherwise>
		                        </c:choose>
	                    	</td>
	                    </tr>
		                </c:if>
	                    <c:if test="${expectListMap[indexExpectFestival[2].idx][1] != null}">
	                    <tr>
	                    	<td>${expectListMap[indexExpectFestival[2].idx][1].accountId}</td>
	                    	<td colspan="3">${expectListMap[indexExpectFestival[2].idx][1].content}</td>
	                    	<td> 
			                    <c:choose>
		                            <c:when test="${expectListMap[indexExpectFestival[2].idx][1].star == 5}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i></span>
		                            </c:when>
		                            <c:when test="${expectListMap[indexExpectFestival[2].idx][1].star == 4}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star-o"></i></span>
		                            </c:when>
		                            <c:when test="${expectListMap[indexExpectFestival[2].idx][1].star == 3}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i></span>
		                            </c:when>
		                            <c:when test="${expectListMap[indexExpectFestival[2].idx][1].star == 2}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i></span>
		                            </c:when>
		                            <c:when test="${expectListMap[indexExpectFestival[2].idx][1].star == 1}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i><i class="fa fa-star-o"></i></span>
		                            </c:when>
		                            <c:otherwise>
		                                <p>유효하지 않은 별점입니다.</p>
		                            </c:otherwise>
		                        </c:choose>
	                    	</td>
	                    </tr>
		                </c:if>
	                    <c:if test="${expectListMap[indexExpectFestival[2].idx][2] != null}">
						<tr>
	                    	<td>${expectListMap[indexExpectFestival[2].idx][2].accountId}</td>
	                    	<td colspan="3">${expectListMap[indexExpectFestival[2].idx][2].content}</td>
	                    	<td>
			                    <c:choose>
		                            <c:when test="${expectListMap[indexExpectFestival[2].idx][2].star == 5}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i></span>
		                            </c:when>
		                            <c:when test="${expectListMap[indexExpectFestival[2].idx][2].star == 4}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i></span>
		                            </c:when>
		                            <c:when test="${expectListMap[indexExpectFestival[2].idx][2].star == 3}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star"></i><i class="fa fa-star"></i></span>
		                            </c:when>
		                            <c:when test="${expectListMap[indexExpectFestival[2].idx][2].star == 2}">
		                                <span class="product-rating"><i class="fa fa-star"></i><i class="fa fa-star"></i></span>
		                            </c:when>
		                            <c:when test="${expectListMap[indexExpectFestival[2].idx][2].star == 1}">
		                                <span class="product-rating"><i class="fa fa-star"></i></span>
		                            </c:when>
		                            <c:otherwise>
		                                <p>유효하지 않은 별점입니다.</p>
		                            </c:otherwise>
		                        </c:choose>
	                    	</td>
	                    </tr>
		                </c:if>
                    </table>
	            </div>
	        </div>
	    </div>
    </div>
    <p>&nbsp;</p>
</section>



<!-- / content -->

<!-- javascript -->
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.easing.min.js"></script>

<!-- scrolling-nav -->
<script src="${pageContext.request.contextPath}/resources/js/scrolling-nav.js"></script>
<!-- / scrolling-nav -->

<!-- sliders -->
<script src="${pageContext.request.contextPath}/resources/js/owl.carousel.min.js"></script>
<!-- featured-products carousel -->
<script>
    $(document).ready(function() {
      $("#products-carousel").owlCarousel({
        autoPlay: 3000, //set autoPlay to 3 seconds.
        items : 4,
        itemsDesktop : [1199,3],
        itemsDesktopSmall : [979,3],
      });

    });
</script>
<!-- / featured-products carousel -->

<!-- brands carousel -->
<script>
    $(document).ready(function() {
      $("#brands-carousel").owlCarousel({
        autoPlay: 5000, //set autoPlay to 5 seconds.
        items : 5,
        itemsDesktop : [1199,3],
        itemsDesktopSmall : [979,3]
      });

    });
</script>
<!-- / brands carousel -->
<!-- / sliders -->

<!-- shuffle grid-resizer -->
<script src="${pageContext.request.contextPath}/resources/js/jquery.shuffle.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/custom.js"></script>
<!-- / shuffle grid-resizer -->

<!-- preloader -->
<script src="${pageContext.request.contextPath}/resources/js/preloader.js"></script>
<!-- / preloader -->

<!-- / javascript -->
</body>

</html>