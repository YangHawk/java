<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!-- preloader -->
<div id="preloader">
    <div class="spinner spinner-round"></div>
</div>
<!-- / preloader -->

 <div id="page-header" class="my-account">
        <div class="container">
            <div class="page-header-content text-center">
                <div class="page-header wsub">
                    <h1 class="page-title fadeInDown animated first">후원하기</h1>
                </div><!-- / page-header -->
                <p class="slide-text fadeInUp animated second">결제정보를 입력해주세요</p>
            </div><!-- / page-header-content -->
        </div><!-- / container -->
    </div><!-- / page-header -->
<!-- content -->


<!-- shopping-cart -->
<div id="shopping-cart">
<form id="payForm" action="<c:url value='/donation/pay_update/'/>" method="POST" >
    <div class="container">
       <div class="coupon" style="display: flex; align-content: center;">
            <div class="input-group col-sm-9">
             <h4>후원 상품 정보</h4>
            </div>
          <div class="update-cart col-sm-6" style="margin-left: 40px;">
                <div class="input-group" style="display: flex; align-content: center; vertical-align: middle;">
                   <p style="margin-right: 5px;"><input type="text" class="form-control rounded" id="changemoneyinput" placeholder="후원금액 입력"></p>
                    <span class="input-group-btn">
                    <a class="btn btn-primary-filled btn-rounded" id="changemoney" type="button"><i class="lnr lnr-tag"></i><span>후원금액변경</span></a>
                    </span>
                </div>
            </div>
         </div><!-- / update-cart -->
            <p>&nbsp;</p>
        <!-- shopping cart table -->
        <table class="shopping-cart">
            <thead>
                <tr>
                    <th class="image">&nbsp;</th>
                    <th>영화제</th>
                    <th>후원금액</th>
                    <th>결제예정일</th>
                    
                </tr>
            </thead>
            <tbody>
                <tr class="cart-item">
                    <td class="image"><a href="${pageContext.request.contextPath}/donation/single-festival?idx=${festivalinfo.idx}">
                       <img src="<c:url value='/resources/upload/${festivalinfo.mainImg}' />" width="50px" height="50px" alt=""></a>
                    </td>
                    <td><a href="${pageContext.request.contextPath}/donation/single-festival?idx=${festivalinfo.idx}">${festivalinfo.subject}</a></td>
                    <td><input type="text" id="moneyInput" value="${money }" class="form-control" readonly></td>
                    <td>${festivalinfo.fundingEnd}로 부터 2일 뒤</td>
                    <td class="remove"><a href="#x" class="btn btn-danger-filled x-remove">×</a></td>
                </tr>
            </tbody>
        </table>
        <!-- / shopping cart table -->

        <div class="row cart-footer">
            <div class="cart-total">
                <h4>결제정보입력</h4>
                <input type="hidden" name="festivalIdx" value="${festivalinfo.idx}">
                <p><span class="font-bold">후원자명: </span><span>${sessionScope.loginAccount.name}</span><input type="hidden" name="accountId" value="${sessionScope.loginAccount.id}"></p>
                <p><span class="font-bold">후원금액: </span><span><input type="text" name="money" id="moneypay" value="${money }" class="form-control" readonly></span></p>
                <p><span class="font-bold">결제방법: </span>
                    <input type="radio" id="card" name="payType" value="0">
                      <label for="card"> 신용카드&nbsp;&nbsp;</label>
                      <input type="radio" id="bankbook" name="payType" value="1">
                      <label for="bankbook"> 무통장결제&nbsp;&nbsp;</label>
                   <input type="radio" id="kakaopay" name="payType" value="2">
                      <label for="kakaopay"> 카카오페이&nbsp;&nbsp;</label>
                   <input type="radio" id="naverpay" name="payType" value="3">
                      <label for="naverpay"> 네이버페이&nbsp;&nbsp;</label>
                     <span style="color: red; display: none;" id="payTypeMsg">결제방법을 선택해주세요.</span>
            </p>
            <div id="bankTransferInfo" style="display: none;">
                <!-- 무통장입금 선택시 보여질 내용 -->
                <p class="font-bold">환불계좌 입력: <input type="text" name="refundAccount"></p>
                <p class="font-bold">환불받을 은행: <select class="form-control" id="bank" name="refundBank">
                                                   <option value="신한은행">신한은행</option>
                                                   <option value="기업은행">기업은행</option>
                                                   <option value="토스뱅크">토스뱅크</option>
                                                   <option value="KB국민은행">KB국민은행</option>
                                                   <option value="하나은행">하나은행</option>
                                                   <option value="NH농협은행">NH농협은행</option>
                                                   <option value="수협은행">수협은행</option>
                                             </select>
                    </p>
            </div>
            <div class="panel">
                <div class="panel-heading faq-panel-heading">
                    <label class="panel-title collapsed" for="agreedCheckbox" data-toggle="collapse" data-target="#agreecontent">
                        <input id="agreedCheckbox" type="checkbox" >
                        <span class="font-bold"> &nbsp;결제 약관 동의(필수)&nbsp;&nbsp;▼</span>
                    </label>
                </div>
                <div id="agreecontent" class="panel-collapse collapse collapse">
                    <div class="panel-body text-gray">
                        결제할 후원상품의 상품명, 후원가격 등을 확인하였으며 결제 진행에 동의하시겠습니까?
                    </div>
                </div>
            </div>
            <div class="col-sm-6"></div>
            <div class="col-sm-6 cart-checkout">
                   <button type="submit" class="btn btn-primary-filled btn-rounded"><span>${money }원 결제하기</span></button>
               </div><!-- / cart-checkout -->
            </div><!-- / cart-total -->

            

        </div><!-- / row -->
    </div><!-- / container -->
</form>
</div>
<!-- / shopping-cart -->

<!-- / content -->

            
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

<!-- / javascript -->
<script type="text/javascript">
$("#changemoney").click(function() {
   var result = true;
   var change = $("#changemoneyinput").val();
   var moneyPattern = /^(?:10000|[1-9]\d{4,6}|5000000)$/;
   
   if(change == "" || !moneyPattern.test(change)){
      alert("10,000원 ~ 5,000,000원 사이 값만 입력 가능합니다.");
   }else{
      $("#moneyInput").val(change);
      $("#moneypay").val(change);
   }
   
   return result;
});

//DOMContentLoaded : 페이지가 완전히 로드되기 전에 자바스크립트 코드가 실행되는 것을 방지
document.addEventListener('DOMContentLoaded', function () {
   var radioButton = document.querySelectorAll('input[type="radio"]');
   for (var i = 0; i < radioButton.length; i++) {
        radioButton[i].addEventListener('change', function () {
            var selectedOptionId = this.id;
            //라디오 버튼 클릭 전엔 [환불계좌] 및 [환불은행] 입력란 미출력
            $("#bankTransferInfo").css("display", "none");

            // 선택된 라디오 버튼에 맞는 입력란 및 [후원하기]버튼 출력
            if (selectedOptionId == "bankbook") {
               $("#bankTransferInfo").css("display", "block");
            } 
          });
   };
});


$("#payForm").submit(function() {
   var resultSubmit = true;
   
   var radioButtons = document.querySelectorAll('input[type="radio"]');
   var checkCount = 0;
   var refundaccountPattern = /([0-9,\-]{3,6}\-[0-9,\-]{2,6}\-[0-9,\-])/;
   
   
   for (var i = 0; i < radioButtons.length; i++){
      if(radioButtons[i].checked == true){
         if(radioButtons[i].id == "bnadbook"){
            if($("#refundAccount").val() == "" || refundaccountPattern.test($("#refundAccount").val())){
               resultSubmit = false;
            }
            var refundBank = $("#bank option:selected").val();
            if(refundBank == ""){
               resultSubmit = false;
            }
         }
         checkCount++;
      }
   }
   
   if(checkCount<1){
      $("#payTypeMsg").css("display", "block");
      resultSubmit = false;
   }
   
   if ($("#agreedCheckbox").prop("checked")) {
       $("#agreeMsg").css("display", "none");
   } else {
       $("#agreeMsg").css("display", "block");
       resultSubmit = false;
   }

   
   return resultSubmit;
});
</script>