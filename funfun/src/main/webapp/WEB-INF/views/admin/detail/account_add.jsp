<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<body>
	<!-- content -->
	<!-- 로그인 -->
	<section id="login">
		<div class="container" >
			<div class="row">
				<div class="col-sm-6" style="float: none; margin: 0 auto;">
					<div id="register-div">
						<h3 class="log-title">관리자 계정 생성</h3>
						<!-- 아이디 -->
							<div class="form-group">
							<input type="text" class="form-control" id="register-id"
							name="id" placeholder="아이디"
								title="소문자와 숫자를 포함하여 12자 이하로 입력해주세요." 
								required data-error="*아이디는 소문자,숫자 포함 12자 이하로 입력해주세요.">
							<div class="help-block with-errors"></div>
						<!-- 아이디 중복체크 버튼 
						<button type="button" id="check-id-button" class="btn btn-primary">아이디 중복체크</button>-->
						</div>
						<!-- 비밀번호 -->
						<div class="form-group">
							<input type="password" class="form-control" id="register-password"
								name="password" placeholder="비밀번호" title="비밀번호는 대소문자,숫자,특수문자 하나씩 포함해야하며 12자리 이내로 입력해주세요." 
							required data-error="*비밀번호는 8~12자, 대소문자,특수문자,숫자를 포함해야합니다.">
							<div class="help-block with-errors"></div>
						</div>
						<div class="form-group">
							<input type="password" class="form-control" id="register-password-confirm"
								placeholder="비밀번호 확인" title="비밀번호와 동일한 값을 입력해주세요." 
								required data-error="*비밀번호를 다시 입력해주세요.">
							<div class="help-block with-errors"></div>
							<div id="password-match-error" class="text-danger" style="display: none;">
							 비밀번호와 비밀번호 확인이 일치하지 않습니다.
							 </div>
						</div>
						<!-- 이름 -->
						<div class="form-group">
							<input type="text" class="form-control" id="register-name"
								name="name" placeholder="이름" title="이름은 한글로만 입력가능합니다." 
								required data-error="*올바른 값을 입력해주세요.">
							<div class="help-block with-errors"></div>
						</div>
						<!-- 전화번호 -->
						<div class="form-group">
							<input type="text" class="form-control" id="register-phone"
						        name="phone" placeholder="010________" title="숫자만 입력해주세요." 
								required data-error="*전화번호 값은 필수 입니다.">
						    <div class="help-block with-errors"></div>
						</div>
						<!-- 이메일 -->
						<div class="form-group">
							<input type="email" class="form-control" id="register-email" name="email"
								name="email" placeholder="이메일" required
								data-error="*올바른 이메일형식으로 입력해주세요.">
							<div class="help-block with-errors"></div>
						</div>
						<!-- 성별 -->
						<div class="form-group">
							<label>성별</label>
							<div>
							<label class="radio-inline" style="width: 30%">
								<input type="radio" name="gender" value="0">남자				
							</label>
							<label class="radio-inline" style="width: 30%">
								<input type="radio" name="gender" value="1">여자
							</label>
							</div>
							<div class="help-block with-errors"></div>
							</div>
						<!-- 생년월일 -->
						<div class="form-group">
							<input type="date" class="form-control" id="register-birth"
								name="birth" placeholder="생년월일" required
								data-error="*올바른 값을 입력해주세요.">
							<div class="help-block with-errors"></div>	
						</div>
						<!-- 주소 -->
						<div class="form-group">
							<div class="aaa">
								<input type="text" class="bbb" id="register-address1"
									name="address1" placeholder="우편번호">
								<button type="button" id="search-address-button" class="btn btn-primary">주소 검색</button>
							</div>
							<div class="help-block with-errors"></div>
						</div>
						<div class="form-group">
							<input type="text" class="form-control" id="register-address2"
								name="address2" placeholder="주소">
							<div class="help-block with-errors"></div>
						</div>
						<div class="form-group">
							<input type="text" class="form-control" id="register-address3"
								name="address3" placeholder="상세주소">
							<div class="help-block with-errors"></div>
						</div>
						
						<!-- log-line -->
						<div class="log-line reg-form-1 no-margin">
							<!-- 회원가입 버튼 -->
							<div class="pull-right">
								<button type="button" id="reg-submit"
									class="btn btn-md btn-primary-filled btn-log btn-rounded">회원가입</button>
								<div id="register-msgSubmit" class="h3 text-center hidden"></div>
								<div class="clearfix"></div>
								</div>
							</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- / login-register -->

	<!-- / content -->
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
    function validateForm() {
        var idInput = document.getElementById('register-id');
        var idValue = idInput.value;
        var idPattern = /^[a-z]+[a-z0-9]{3,12}$/g;
        
        if (!idPattern.test(idValue)) {
            alert('아이디는 소문자로 시작하며 소문자 또는 숫자 조합 3-12자 이내여야 합니다.');
            idInput.focus(); 
            // 아이디 입력 필드에 포커스 설정
            return false;
        }
        
        var passwordInput = document.getElementById('register-password');
        var passwordValue = passwordInput.value;
        var passwordConfirmInput = document.getElementById('register-password-confirm');
        var passwordConfirmValue = passwordConfirmInput.value;
        var passwordPattern = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,12}$/;
        
        if (!passwordPattern.test(passwordValue)) {
            alert('비밀번호는 8-12자의 영문, 숫자, 특수문자 조합이어야 합니다.');
            passwordInput.focus(); 
            return false;
        }
        
        if(passwordValue !== passwordConfirmValue) {
			document.getElementById('password-match-error').style.display = 'block';
        	passwordConfirmInput.focus();
        	return false;
        }
        
        
        var nameInput = document.getElementById('register-name');
        var nameValue = nameInput.value;
        var namePattern = /^[가-힣]{2,6}$/;
        
        if (!namePattern.test(nameValue)) {
            alert('이름은 2~6자 한글로 입력해주세요.');
            nameInput.focus(); 
            return false;
        }
        
        var phoneInput = document.getElementById('register-phone');
        var phoneValue = phoneInput.value;
        var phonePattern = /^\d{3}-\d{4}-\d{4}$/;
        
        if (!phonePattern.test(phoneValue)) {
            alert('핸드폰 번호는 숫자로만 입력해주세요.');
            phoneInput.focus(); 
            return false;
        }
        
        var emailInput = document.getElementById('register-email');
        var emailValue = emailInput.value;
        var emailPattern = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
        
        if (!emailPattern.test(emailValue)) {
            alert('올바른 이메일 주소를 입력해주세요.');
            emailInput.focus(); 
            return false;
        }
        
        var genderInput = document.querySelector('input[name="gender"]:checked');
        if (!genderInput) {
        	alert("성별을 선택해주세요.");
	        return false;
     	}
        
        var birthInput = document.getElementById('register-birth');
        var birthValue = birthInput.value;
        
        if (birthValue == "") {
            alert('생년월일을 입력해주세요.');
            return false;
        }
        
        var address1Input = document.getElementById('register-address1');
        var address2Input = document.getElementById('register-address2');
        var address3Input = document.getElementById('register-address3');
        var address1Value = address1Input.value;
        var address2Value = address2Input.value;
        var address3Value = address3Input.value;
        
        if (address1Value.trim() === "" || address2Value.trim() === "" || address3Value.trim() === "") {
            alert('주소를 입력해주세요.');
            return false;
        }
        
        return true;
    }
    
	<!-- 주소 API 호출과 이벤트 핸들링 -->
	document.getElementById('search-address-button').addEventListener('click', function () {
	     new daum.Postcode({
	        oncomplete: function (data) {
	            document.getElementById('register-address1').value = data.zonecode; // 우편번호 입력
	            document.getElementById('register-address2').value = data.roadAddress; // 도로명 주소 입력
	            document.getElementById('register-address3').focus();
	           
	        }
	    }).open();
	});
	
    $(document).ready(function() {
    	$("#register-div").on("click", "#reg-submit", function() {
    		// "회원가입" 버튼 클릭 이벤트 핸들러
    	    var aid = $("#register-id").val();
    	    var apassword = $("#register-password").val();
    	    var aname = $("#register-name").val();
    	    var aphone = $("#register-phone").val();
    	    var aemail = $("#register-email").val();
    	    var agender = $("input[name='gender']:checked").val();
    	    var abirth = $("#register-birth").val();
    	    var aaddress1 = $("#register-address1").val();
    	    var aaddress2 = $("#register-address2").val();
    	    var aaddress3 = $("#register-address3").val();
    		
    		// 유효성 검사 후 등록 요청
    	    if (validateForm()) {
	    		$.ajax({
		            method: "POST",
		            url: "<c:url value='/admin/admin_register'/>",
		            contentType: "application/json", // 데이터 타입 설정
		            data: JSON.stringify({
		                "id": aid,
		                "password": apassword,
		                "name": aname,
		                "phone": aphone,
		                "email": aemail,
		                "gender": agender,
		                "birth": abirth,
		                "address1": aaddress1,
		                "address2": aaddress2,
		                "address3": aaddress3
		            }), // 데이터를 JSON 형식으로 변환
		            dataType: "text",
		            success: function(result) {
		            	 console.log("펑션 석세스");
		            	 console.log(result);
		                if(result == "success") {
		                    alert("새로운 관리자 계정이 생성되었습니다.");
		                    accountDetail(aid);
		                } 
		            	 alert("중복된 계정입니다.");
		            	 return;
		            },
		            error: function(xhr) {
		            	 console.log("에러!");
		            	
		                alert("새로운 관리자 계정을 생성 중에 오류가 발생했습니다. 에러 코드 = (" + xhr.status + ")");
		            }
		        });
    	    } else {
    	    	 console.log('유효성 검사 통과 못함!');
    	        event.preventDefault();
    	    }
    	});
    });
	</script>	
	<!-- / javascript -->
</body>


</html>
