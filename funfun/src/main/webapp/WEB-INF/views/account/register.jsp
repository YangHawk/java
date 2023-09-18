<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<body>

	<!-- preloader -->
	<div id="preloader">
		<div class="spinner spinner-round"></div>
	</div>
	<!-- / preloader -->

	<!-- content -->

	<!-- 로그인 -->
	<section id="login">
		<div class="container" >
			<div class="row">
				<div class="col-sm-6" style="float: none; margin: 0 auto;">
					<div id="register-form">
					
						<h3 class="log-title">회원 가입</h3>
						<form action="${pageContext.request.contextPath}/account/register" method="post">
						<!-- 아이디 -->
							<div class="form-group">
								<div class="input-group">
								<input type="text" class="form-control" id="register-id"
								name="id" placeholder="아이디"
									title="소문자와 숫자를 포함하여 12자 이하로 입력해주세요." 
									required data-error="*아이디는 소문자,숫자 포함 12자 이하로 입력해주세요."
									style= "width : 320px">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<!-- 아이디 중복체크 버튼 -->
								<button type="button" id="check-id-button" class="btn btn-primary">아이디 중복체크</button>
									
								</div>
								<div class="help-block with-errors"></div>
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
						<!-- 회원가입 구분 -->
						<div class="form-group">
							<label>회원구분</label>
							<div>
							<label class="radio-inline" style="width: 30%">
								<input type="radio" name="accountRole" value="ROLE_REGISTER">등록자				
							</label>
							<label class="radio-inline" style="width: 30%">
								<input type="radio" name="accountRole" value="ROLE_USER">일반회원
							</label>
							</div>
							<div class="help-block with-errors"></div>
							</div>
						<!-- log-line -->
						<div class="log-line reg-form-1 no-margin">
							<div class="pull-left">
								<div class="checkbox checkbox-primary space-bottom">
									<label class="hide"><input type="checkbox"></label>
									<input id="checkbox2" type="checkbox"> 
										<label for="checkbox2"><span><a href="#x">약관동의</a></span></label>
								</div>
							</div>
							<!-- 회원가입 버튼 -->
							<div class="pull-right">
								<button type="submit" id="reg-submit"
									class="btn btn-md btn-primary-filled btn-log btn-rounded">회원가입</button>
								<div id="register-msgSubmit" class="h3 text-center hidden"></div>
								<div class="clearfix"></div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- / login-register -->

	<!-- / content -->

	<!-- javascript -->
	<script
		src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
	<!-- inputmask 라이브러리 -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.inputmask/5.0.6/jquery.inputmask.min.js"></script>
	<!-- 다음 우편번호 서비스 스크립트 로딩 -->
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
		
	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/jquery.easing.min.js"></script>

	<!-- preloader -->
	<script
		src="${pageContext.request.contextPath}/resources/js/preloader.js"></script>
	<!-- / preloader -->

	<!-- inputmask 스크립트 -->
	<script>
	    $(document).ready(function() {
	        $('#register-phone').inputmask("999-9999-9999", { "placeholder": "010-____-____" });
	    });
	</script>
	
	<!-- 유효성 검사 및 회원가입 버튼 활성화(이벤트 핸들러) -->
     <script>
	   //CSRF 토큰 관련 정보를 자바스크립트 변수에 저장
	     var csrfHeaderName = "${_csrf.headerName}";
	     var csrfTokenValue = "${_csrf.token}";
	
	     // Ajax 기능을 사용하여 요청하는 모든 웹 프로그램에게 CSRF 토큰 전달 가능
	     // ▶ Ajax 요청 시 beforeSend 속성을 설정할 필요 없음
	     $(document).ajaxSend(function(e, xhr){
	     	xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
	     });
     
     	document.getElementById('reg-submit').addEventListener('click', function(event) {
            if (validateForm()) {
                alert("회원가입이 완료 되었습니다.");
            } else {
	            event.preventDefault(); // 기본 이벤트 동작 방지
            }
        });
     	
     	//페이지를 다시 로드(뒤로가기 버튼 누를 때)
     	window.onbeforeunload = function() {
     		return null;
     	}
     	
     	window.onpageshow = function(event) {
     		if(event.persisted) {
     		alert("이미 회원가입이 완료되었습니다.");
     			window.location.href="${pageContext.request.contextPath}/account/login";
     		}
     	}
    
        function validateForm() {
            var idInput = document.getElementById('register-id');
            var idValue = idInput.value;
            var idPattern = /^[a-z][a-z0-9]{3,12}$/;
            
            if (!idPattern.test(idValue)) {
                alert('아이디는 소문자로 시작하며 소문자 또는 숫자 조합 3-12자 이내여야 합니다.');
                idInput.focus(); // 아이디 입력 필드에 포커스 설정
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
            
            if (address1Value == null&&"" || address2Value == null&&"" || address3Value == null&&"") {
                alert('주소를 입력해주세요.');
                return false;
            }
            
            var statusInput = document.querySelector('input[name="accountRole"]:checked');
            if (!statusInput) {
	            alert("회원구분을 선택해주세요.");
	            return false;
	        }
            
            var isAgreed = document.getElementById('checkbox2').checked;

	        // 약관 동의 체크 여부 확인
	        if (!isAgreed) {
	            alert("약관에 동의해주세요.");
	            return false;
	        } else {
	        	return true; // 모든 유효성 검사 통과
	        }
        }
        
    </script>
	
	<!-- 주소 API 호출과 이벤트 핸들링 -->
	<script>
	    document.getElementById('search-address-button').addEventListener('click', function () {
	         new daum.Postcode({
	            oncomplete: function (data) {
	                document.getElementById('register-address1').value = data.zonecode; // 우편번호 입력
	                document.getElementById('register-address2').value = data.roadAddress; // 도로명 주소 입력
	                document.getElementById('register-address3').focus();
	               
	            }
	        }).open();
	    });
	</script>
	
	<script>
		/*document.getElementById('check-id-button').addEventListener('click', function(){
			var idInput = document.getElementById('register-id');
			var idValue = idInput.value;})*/
		
		$(document).ready(function() {
			$('#check-id-button').click(function(){
				var idValue = $('#register-id').val();
				var idInput = $('#register-id');
				var idPattern = /^[a-z][a-z0-9]{3,12}$/;
			
				if(!idPattern.test(idValue)) {
					alert("아이디를 올바른 값으로 입력해주세요.");
					idInput.focus();
					return;
				}
				
			$.ajax({
				type: 'GET',
				url: "<c:url value='/checkId'/>",
				data: {id: idValue},
				success: function(response) {
					if(idValue.trim()=='') {
						alert('아이디를 입력해주세요.');
					} else if (response.available) {
						alert('사용 가능한 아이디입니다.');
					} else {
						alert('이미 사용중인 아이디입니다.');
					}
					isIdChecked = true;
				},
				error: function() {
					alert('서버 오류가 발생하였습니다.');
				}
			});
		});
	});
		
	</script>
	<!-- / javascript -->
</body>


</html>