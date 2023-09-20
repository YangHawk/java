<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- preloader -->
<div id="preloader">
    <div class="spinner spinner-round"></div>
</div>
<!-- / preloader -->
<!-- content -->

<section id="pay_completion">
    <div class="container">
        <div class="text-wrap text-center">
            <div class="page-header text-center space-top-30">
                <h1><i class="lnr lnr-warning"></i>후원이 완료되었습니다.</h1>
            </div><!-- / page-header -->
            <p class="large-p">${message }</p>
            <c:choose>
            	<c:when test="${donation.payType == 0}">
		            <p class="space-top-2x"><button type="button" id="html5_inicis" class="pay"><i class="fa fa-long-arrow-left"></i><span>지금 결제하기</span></button></p>
            	</c:when>
            	<c:otherwise>
		            <p class="space-top-2x"><button type="button" id="kakaopay" class="pay"><i class="fa fa-long-arrow-left"></i><span>지금 결제하기</span></button></p>
            	</c:otherwise>
            </c:choose>
		            <p class="space-top-2x"><button type="button" id="kakaopay" class="pay"><i class="fa fa-long-arrow-left"></i><span>지금 결제하기</span></button></p>
            <p class="space-top-2x"><a href="${pageContext.request.contextPath}/" class="btn btn-default-filled btn-rounded"><i class="fa fa-long-arrow-left"></i><span>나중에 결제하기</span></a></p>
        </div><!-- / text-wrap -->
    </div><!-- / container -->
</section>

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
<script src="https://cdn.iamport.kr/v1/iamport.js"></script>
<script type="text/javascript">
	var name = "${festival.subject}";
	var idx = ${donation.idx};
	
	var buyerEmail="<sec:authentication property="principal.email"/>";
	var buyerName="<sec:authentication property="principal.name"/>";
	var buyerTel="<sec:authentication property="principal.phone"/>";
	var buyerPostcode="<sec:authentication property="principal.address1"/>";
	var buyerAddr="<sec:authentication property="principal.address2"/>+<sec:authentication property="principal.address3"/>";
	
	var buyerEmailElement = document.createElement("textarea");
	buyerEmailElement.innerHTML = buyerEmail;
	var buyerEmailDecoded = buyerEmailElement.value;

	var buyerNameElement = document.createElement("textarea");
	buyerNameElement.innerHTML = buyerName;
	var buyerNameDecoded = buyerNameElement.value;

	var buyerTelElement = document.createElement("textarea");
	buyerTelElement.innerHTML = buyerTel;
	var buyerTelDecoded = buyerTelElement.value;

	var buyerPostcodeElement = document.createElement("textarea");
	buyerPostcodeElement.innerHTML = buyerPostcode;
	var buyerPostcodeDecoded = buyerPostcodeElement.value;

	var buyerAddrElement = document.createElement("textarea");
	buyerAddrElement.innerHTML = buyerAddr;
	var buyerAddrDecoded = buyerAddrElement.value;
	
	var csrfHeaderName="${_csrf.headerName}"
	var csrfTokenValue="${_csrf.token}";
	$(document).ajaxSend(function(e, xhr) {
		xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
	});

	$(".pay").click(function() {
		var pg=$(this).attr("id");
		
		var IMP=window.IMP;
		//IMP 객체 초기화 - 가맹점 식별코드
		IMP.init("imp16048620");
		
		//주문번호 - 주문테이블에서 제공된 값 사용
		var merchantUid="merchant_"+new Date().getTime();
		//결제금액 - 주문테이블에서 제공된 값 사용
		var money = ${donation.money};
		
		//결제 전 결제 고유값과 결제 금액을 세션에 저장하기 위한 페이지 요청
		// => 결재 후 결제 정보와 비교하여 검증하기 위해 세션에 저장 
		$.ajax({
			type: "post",
			url: "<c:url value="/donation/real_pay"/>",
			contentType: "application/json",
			data: JSON.stringify({"merchantUid":merchantUid, "money":money}),
			dataType: "text",
			success: function(result) {
				if(result=="ok") {
					//결제를 요청하는 메소드 호출
					console.log(result);
					IMP.request_pay({
						// 결제 대행사 : kakaopay, html5_inicis, nice, jtnet, uplus, danal, payco 등
						pg : pg,
						// 결제 방식 : card(카드), samsung(삼성페이), trans(실시간계좌이체), vbank(가상계좌), phone(휴대폰소액결제)
						pay_method : "card",
						//주문번호
						merchant_uid : merchantUid,
						//결제금액
						amount : money,
						//결제창에 보여질 제품명
						name: name,
						//결제 사용자의 이메일 주소 
			            buyer_email: buyerEmailDecoded,
			            buyer_name: buyerNameDecoded,//결제 사용자 이름
			            buyer_tel: buyerTelDecoded,//결제 사용자 전화번호
			            buyer_postcode: buyerPostcodeDecoded,//결제 사용자 우편번호
			            buyer_addr: buyerAddrDecoded,//결제 사용자 주소
					}, function(response) {//결제 관련 응답 결과를 제공받아 처리하는 함수
						console.log(response);
						// response: 응답 결과를 저장한 Object 객체
						if (response.success) { // 결제를 성공한 경우
							// 결제 금액을 검증하기 위한 페이지를 요청
							$.ajax({
								type: "post",
								url: "<c:url value="/donation/real_complete"/>",
								contentType: "application/json",
								data: JSON.stringify({"impUid":response.imp_uid, "merchantUid":response.merchant_uid, "idx":idx}),
								dataType: "text",
								success: function(result) {
									console.log(result);
									if(result == "success") {
										// 결제 성공 페이지로 이동
										alert("결제 성공");
									} else {
										// 결제 취소 페이지로 이동
										alert("결제 취소");
									}	
								}
							});
						}
					});
				}
			}, 
			error: function(xhr) {
				alert("에러 = "+xhr.status);
			}
		});
	});
</script>
