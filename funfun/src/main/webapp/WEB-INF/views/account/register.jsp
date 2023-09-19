<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
								
								<div id="idError" class="text-danger" style="display: none;">
									아이디는 3~12자로 소문자로 시작해야하고 소문자와 문자로만 구성 가능합니다.
								</div>
								<div id="idDuplicationMessage" class="text-danger" style="display: none;">
									아이디 중복체크를 실행해주세요.
								</div>
						</div>
						<!-- 비밀번호 -->
						<div class="form-group">
							<input type="password" class="form-control" id="register-password"
								name="password" placeholder="비밀번호" title="비밀번호는 대소문자,숫자,특수문자 하나씩 포함해야하며 12자리 이내로 입력해주세요." 
							required data-error="*비밀번호는 8~12자, 대소문자,특수문자,숫자를 포함해야합니다.">
							<div class="help-block with-errors"></div>
							<div id="PasswordError" class="text-danger" style="display: none;">
									비밀번호는 대소문자,숫자,특수문자를 각 1개씩 포함하여 8~12자로 입력해주세요.
								</div>
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
							<div id="nameError" class="text-danger" style="display: none;">
									이름은 한글로만 입력 가능합니다.
								</div>
						</div>
						<!-- 전화번호 -->
						<div class="form-group">
							<input type="text" class="form-control" id="register-phone"
						        name="phone" placeholder="010________" title="숫자만 입력해주세요." 
								required data-error="*전화번호 값은 필수 입니다.">
						    <div class="help-block with-errors"></div>
						    <div id="phoneError" class="text-danger" style="display: none;">
									전화번호를 입력해주세요.
								</div>
						</div>
						<!-- 이메일 -->
						<div class="form-group">
							<input type="email" class="form-control" id="register-email" name="email"
								name="email" placeholder="이메일" required
								data-error="*올바른 이메일형식으로 입력해주세요.">
							<div class="help-block with-errors"></div>
							<div id="emailError" class="text-danger" style="display: none;">
									이메일은 @를 포함한 형식으로 입력해주세요.
								</div>
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
							<div id="genderError" class="text-danger" style="display: none;">
									성별을 체크해주세요.
								</div>
							</div>
						<!-- 생년월일 -->
						<div class="form-group">
							<input type="date" class="form-control" id="register-birth"
								name="birth" placeholder="생년월일" required
								data-error="*올바른 값을 입력해주세요.">
							<div class="help-block with-errors"></div>
							<div id="birthError" class="text-danger" style="display: none;">
									생년월일을 선택해주세요.
								</div>	
						</div>
						<!-- 주소 -->
						<div class="form-group">
							<div class="aaa">
								<input type="text" class="bbb" id="register-address1"
									name="address1" placeholder="우편번호">
								<button type="button" id="search-address-button" class="btn btn-primary">주소 검색</button>
							</div>
							<div class="help-block with-errors"></div>
							<div id="addressError" class="text-danger" style="display: none;">
									주소검색을 통해 주소를 올바르게 입력해주세요.
								</div>
						</div>
						<div class="form-group">
							<input type="text" class="form-control" id="register-address2"
								name="address2" placeholder="주소">
							<div class="help-block with-errors"></div>
							<div id="addressError" class="text-danger" style="display: none;">
									주소검색을 통해 주소를 올바르게 입력해주세요.
								</div>
						</div>
						<div class="form-group">
							<input type="text" class="form-control" id="register-address3"
								name="address3" placeholder="상세주소">
							<div class="help-block with-errors"></div>
							<div id="addressError" class="text-danger" style="display: none;">
									주소검색을 통해 주소를 올바르게 입력해주세요.
								</div>
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
							<div id="statusError" class="text-danger" style="display: none;">
									올바른 회원구분을 선택해주세요.
								</div>
							</div>
						<!-- log-line -->
						<div class="log-line reg-form-1 no-margin">
							<div class="pull-left">
								<div class="checkbox checkbox-primary space-bottom">
									<label class="hide"><input type="checkbox"></label>
									<input id="checkbox2" type="checkbox"> 
										<label for="checkbox2"><span><a href="#x">약관동의</a></span></label>
								</div>
								<div id="agreeError" class="text-danger" style="display: none;">
									약관동의를 체크해주세요.
								</div>
							</div>
							<!-- 회원가입 버튼 -->
							<div class="pull-right">
								<button type="submit" id="reg-submit"
									class="btn btn-md btn-primary-filled btn-log btn-rounded">회원가입</button>
								<div class="clearfix"></div>
								</div>
							</div>
							<sec:csrfInput/>
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
	
	
		<script>
		
		//아이디 중복체크 및 아이디 유효성 관련 검증 코드!!!
		document.addEventListener('DOMContentLoaded', function () {
		    var registerIdInput = document.getElementById('register-id');
		    var checkIdButton = document.getElementById('check-id-button');
	
		    // 아이디 입력 필드의 값이 변경될 때 이벤트 리스너 등록
		    registerIdInput.addEventListener('input', function () {
		    	var idError = document.getElementById('idError');
		        var idValue = registerIdInput.value;
		        var idPattern = /^[a-z][a-z0-9]{3,12}$/;
	
		        if (idPattern.test(idValue)) {
		        	idError.style.display = 'none';
		            checkIdButton.disabled = false; // 아이디가 유효하면 버튼 활성화
		        } else {
		        	idError.style.display = 'block';
		            checkIdButton.disabled = true; // 아이디가 유효하지 않으면 버튼 비활성화
		            event.preventDefault(); 
		        }
		    });
		});
		
		</script>
		<!-- 유효성 검사 및 회원가입 버튼 활성화(이벤트 핸들러) -->
	     <script>
	     document.getElementById('reg-submit').addEventListener('click', function(event) {
	     var isIdChecked = false; //ID 중복체크를 추적하기 위한 변수
	     var isValid = true; //모든 유효성 검사를 통과했는지 확인하는 변수
	     
	     //아이디 중복체크 버튼을 누르지 않으면 오류 메세지를 띄우는 코드
		 if(document.getElementById('check-id-button').disabled) {
				isIdChecked = true;
			} else {
				document.getElementById('idDuplicationMessage').style.display = 'block';
				event.preventDefault(); //양식 제출 방지
				return;
			}
	     
	     var passwordInput = document.getElementById('register-password');
	     var passwordValue = passwordInput.value;
	     var passwordConfirmInput = document.getElementById('register-password-confirm');
	     var passwordConfirmValue = passwordConfirmInput.value;
	     var passwordPattern = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,12}$/;
	     
	     if (!passwordPattern.test(passwordValue)) {
	     	document.getElementById('PasswordError').style.display = 'block'; // 비밀번호 오류 메시지 표시
	         passwordInput.focus(); 
	         event.preventDefault();//회원가입 방지
	         isValid = false;
	     } else {
	     	document.getElementById('PasswordError').style.display = 'none'; // 비밀번호 오류 메시지 제거
	     }
	     
	     
		if(passwordValue !== passwordConfirmValue) {
			document.getElementById('password-match-error').style.display = 'block';
	     	passwordConfirmInput.focus();
	     	event.preventDefault();//회원가입 방지
	     	isValid = false;
	     } else {
	     	document.getElementById('password-match-error').style.display = 'none';
	     }
			
			var nameInput = document.getElementById('register-name');
	     var nameValue = nameInput.value;
	     var namePattern = /^[가-힣]{2,6}$/;
	     
	     if (!namePattern.test(nameValue)) {
	     	document.getElementById('nameError').style.display = 'block';
	         nameInput.focus();
	         event.preventDefault();//회원가입 방지
	         isValid = false;
	     } else {
	     	document.getElementById('nameError').style.display = 'none';
	     }
			
	     var phoneInput = document.getElementById('register-phone');
	     var phoneValue = phoneInput.value;
	     var phonePattern = /^\d{3}-\d{4}-\d{4}$/;
	     
	     if (!phonePattern.test(phoneValue)) {
	     	document.getElementById('phoneError').style.display = 'block';
	         phoneInput.focus();
	         event.preventDefault();//회원가입 방지
	         isValid = false;
	     } else {
	     	document.getElementById('phoneError').style.display = 'none';
	     }
	     
	     var emailInput = document.getElementById('register-email');
	     var emailValue = emailInput.value;
	     var emailPattern = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	     
	     if (!emailPattern.test(emailValue)) {
	     	document.getElementById('emailError').style.display = 'block';
	         emailInput.focus();
	         event.preventDefault();//회원가입 방지
	         isValid = false;
	     } else {
	     	document.getElementById('emailError').style.display = 'none';
	     }
	     
	     var genderInput = document.querySelector('input[name="gender"]:checked');
	     if (!genderInput) {
	     	document.getElementById('genderError').style.display = 'block';
	     	event.preventDefault();//회원가입 방지
	     	isValid = false;
	     } else {
	     	document.getElementById('genderError').style.display = 'none';
	     }
	     
	     var birthInput = document.getElementById('register-birth');
	     var birthValue = birthInput.value;
	     
	     if (birthValue == "") {
	     	document.getElementById('birthError').style.display = 'block';
	     	event.preventDefault();//회원가입 방지
	     	isValid = false;
	     } else {
	     	document.getElementById('birthError').style.display = 'none';
	     }
	     
	     var address1Input = document.getElementById('register-address1');
	     var address2Input = document.getElementById('register-address2');
	     var address3Input = document.getElementById('register-address3');
	     var address1Value = address1Input.value;
	     var address2Value = address2Input.value;
	     var address3Value = address3Input.value;
	     
	     if (address1Value == "" || address2Value == "" || address3Value == "") {
	     	document.getElementById('addressError').style.display = 'block';
	     	event.preventDefault();//회원가입 방지
	     	isValid = false;
	     } else {
	     	document.getElementById('addressError').style.display = 'none';
	     }
	     
	     var statusInput = document.querySelector('input[name="accountRole"]:checked');
	     if (!statusInput) {
	     	document.getElementById('statusError').style.display = 'block';
	     	event.preventDefault();//회원가입 방지
	     	isValid = false;
	     } else {
	     	document.getElementById('statusError').style.display = 'none';
	     }
	     
	     var isAgreed = document.getElementById('checkbox2').checked;
	     
	
	     // 약관 동의 체크 여부 확인
	     var isAgreed = document.getElementById('checkbox2').checked;
	     
	     if (!isAgreed) {
	     	document.getElementById('agreeError').style.display = 'block';
	     	event.preventDefault();//회원가입 방지
	     	isValid = false;
	     } else {
	     	document.getElementById('agreeError').style.display = 'none';
	     }
	     
          	//ID 중복체크가 확인되었고 다른 유효성 검사가 통과되면 양식 제출을 허용
   			if (isIdChecked && isValid == true) {
             alert("회원가입이 완료 되었습니다.");
          } else if(!isIdChecked && isValid == false){
        	  event.preventDefault(); // 회원가입 방지
          }
      });
     	
	     	//아이디 중복체크 버튼 이벤트 핸들러
			$('#check-id-button').click(function(){
				var idValue = $('#register-id').val();
				var idInput = $('#register-id');
				var idPattern = /^[a-z][a-z0-9]{3,12}$/;
			
				if(!idPattern.test(idValue)) {
					document.getElementById('idDuplicationMessage').style.display = 'block';
					idInput.focus();
					return;
				}
				
				$.ajax({
					type: 'GET',
					url: "<c:url value='/checkId'/>",
					data: {id: idValue},
					success: function(response) {
						 if (response.available) {
							alert('사용 가능한 아이디입니다.');
							document.getElementById('idDuplicationMessage').style.display = 'none';
							 $('#check-id-button').prop('disabled', true);
						} else {
							alert('이미 사용중인 아이디입니다.');
							document.getElementById('idDuplicationMessage').style.display = 'block';
						}
						isIdChecked = true;
					},
					error: function() {
						alert('서버 오류가 발생하였습니다.');
					}
				});
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
    
        
    </script>
    
    
   
	
	<!-- 주소 API 호출과 이벤트 핸들링 -->
	<script>
	    document.getElementById('search-address-button').addEventListener('click', function () {
	         new daum.Postcode({
	            oncomplete: function (data) {
	                document.getElementById('register-address1').value = data.zonecode; // 우편번호 입력
	                document.getElementById('register-address2').value = data.roadAddress; // 도로명 주소 입력
	                document.getElementById('register-address3').focus();
	               
	                document.getElementById('addressError').style.display = 'none';
	                
	            }
	        }).open();
	    });
	</script>
	
	<!-- / javascript -->
</body>


</html>
