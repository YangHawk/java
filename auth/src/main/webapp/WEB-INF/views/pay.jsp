<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SPRING</title>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://cdn.iamport.kr/v1/iamport.js"></script>
</head>
<body>
	<h1>결제 페이지</h1>
	<hr>
	<h3>결제 관련 제품 정보 출력</h3>
	<hr>
	<button type="button" id="html5_inicis">일반 결제(KG 이니시스)</button>
	<button type="button" id="kakaopay">간편 결제(카카오페이)</button>

	<script type="text/javascript">
		var csrfHeaderName = "${_csrf.headerName}";
		var csrfTokenValue = "${_csrf.token}";
		$(document).ajaxSend(function(e, xhr) {
			xhr.setRequestHeader(csrfheaderName, csrfTokenValue);
		});

		$(".pay").click(function() {
			var pg = $(this).attr("id");
			alert(pg);

			var IMP = window.IMP;
			// IMP 객체를 초기화 - 가맹점 식별 코드
			IMP.init("imp16048620");

			var merchantUid = "merchant_" + new Date().getTime();
			var amount = 10;

			// 결제 전 결제 고유값과 결제 금액을 세션에 저장하기 위한 페이지 요청 
			// ▶ 결제 후 결제 정보와 비교하여 검증하기 위하여 세션에 저장 
			$.ajax({
				type : "POST",
				url : "<c:url value='/payment/pay'/>",
				contentType : "application/json",
				data : JSON.stringify({
					"merchantUid" : merchantUid,
					"amount" : amount
				}),
				dataType : "text",
				success : function(result) {
					if (result == "ok") {
						// 결제 요청
						IMP.reques_pay({
							/* 결제 대행사: kakaopay / html5_inicis / nice / jtnet / uplus / danal / payco ...etc */
							pg : pg
							/* 결제 방식 : card / samsung(삼성페이) / trans(실시간 계좌 이체) / vbank(가상 계좌) / phone(휴대폰 소액 결제) ...etc */
							pay_method : "card",
							merchant_Uid : merchantUid,
							amount : amount,
							name : "콤퓨타",
							buyer_email : "jinlee0063@naver.com"
						}, function(respones) {
							if(response.success) {
								
							}
						});
					}
				},
				error : function(xhr) {
					alert("에러 = " + xhr.status);
				}
			});
		});
	</script>
</body>
</html>