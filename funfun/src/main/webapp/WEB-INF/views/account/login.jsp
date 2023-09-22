<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<style>
    .social-login-buttons {
       display: flex;
      float: left;
       margin-right: 10px; /* 이미지 사이의 간격 조정 */
   }
   
   
    .naver-login-img {
        width: 50px;
        margin-right: 10px;
       
    }
    
    .kakao-login-img {
        width: 58px;
    }
    
    /* 체크박스 커스터마이즈 스타일 */
.checkbox-primary input[type="checkbox"] {
    display: none; /* 기본 체크박스 숨기기 */
}

.checkbox-primary label {
    position: relative; /* 상대 위치 지정 */
    padding-left: 5px; /* 약간의 여백 지정 */
    cursor: pointer; /* 포인터 커서로 변경하여 클릭 가능하게 만듦 */
    display: flex;
    align-items: center; /* 텍스트와 체크박스 수직 중앙 정렬 */
}

.checkbox-primary label::before {
    content: ''; /* 체크박스를 나타내는 요소 생성 */
    position: absolute;
    left: 0; /* 수정: 텍스트와 가운데 정렬 */
    top: 50%; /* 수직 정렬을 위해 중앙으로 이동 */
    transform: translateY(-50%); /* 수직 정렬을 위한 트랜스폼 사용 */
    width: 20px; /* 체크박스 크기 조절 */
    height: 20px;
    border: 1px solid #000; /* 체크박스 테두리 색상을 검정색으로 변경 */
    border-radius: 4px; /* 체크박스 모서리 둥글게 만들기 */
    background-color: transparent; /* 초기 배경색은 투명 */
    text-align: center; /* 가운데 정렬 추가 */
    line-height: 20px; /* 가운데 정렬을 위해 높이 값 설정 */
}

.checkbox-primary input[type="checkbox"]:checked + label::before {
    background-color: #007bff; /* 체크된 상태의 배경색 변경 */
}

.checkbox-primary input[type="checkbox"]:checked + label::after {
    content: '\2713'; /* 체크마크 아이콘 (유니코드 문자) */
    font-size: 18px; /* 아이콘 크기 조절 */
    color: #fff; /* 체크된 상태의 아이콘 색상 변경 */
    position: absolute;
    left: 0; /* 수정: 텍스트와 가운데 정렬 */
    top: 50%; /* 수직 정렬을 위해 중앙으로 이동 */
    transform: translateY(-50%); /* 수직 정렬을 위한 트랜스폼 사용 */
}

</style>
<body>
	<!-- preloader -->
	<div id="preloader">
		<div class="spinner spinner-round"></div>
	</div>
	<!-- / preloader -->

	<!-- content -->

	<!-- 로그인 -->
	<section id="login">
		<div class="container">
			<div class="row">
				<div class="col-sm-6">
					<!-- 로그인 폼 코드 -->
					<div id="login-form">
						<h3 class="log-title">로그인</h3>
						<form id="loginForm" action="${pageContext.request.contextPath}/account/login" method="post">
						<!-- 아이디 입력 -->
							<div class="form-group">
								<input type="text" class="form-control" id="userId"
								name="id" placeholder="아이디" required data-error="아이디를 반드시 입력해야 합니다">
								<div class="help-block with-errors"></div>
								<c:remove var="userId"/>
							</div>
							<!-- 비밀번호 입력 -->
							<div class="form-group">
						        <input type="password" class="form-control" id="userPassword"
						        name="password" placeholder="비밀번호" required data-error="비밀번호를 반드시 입력해야 합니다">
						        <div class="help-block with-errors"></div>
						    </div>
							<!-- 자동로그인 체크 -->
							<div class="log-line">
								<div class="pull-left">
									<div class="checkbox checkbox-primary space-bottom">
										<label class="hide"><input type="checkbox"></label>
										<input id="checkbox1" type="checkbox" name="remember-me">
										<label for="checkbox1"><span>자동 로그인</span></label>
									</div>
								</div>
								<!-- 로그인 버튼 -->
								<div class="pull-right">
									<button type="submit" class="btn btn-md btn-primary-filled btn-log btn-rounded" id="loginButton">로그인</button>
									<div id="msgSubmit" class="h3 text-center hidden"></div>
									<div class="clearfix"></div>
								</div>
							</div>
							
							<sec:csrfInput/>
						</form>
						<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION }">
							<%-- <h3 style="color: red;">아이디 또는 비밀번호가 맞지 않습니다.</h3> --%>
							<h6 style="color: #dc143c;">${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message }</h6>
							<%-- 예외가 저장된 세션에 속성값을 삭제 --%>
							<c:remove var="SPRING_SECURITY_LAST_EXCEPTION"/>
						</c:if>
						<c:if test="${not empty SocialLoginErrorMessage}">
						    <h6 style="color: #dc143c;">${SocialLoginErrorMessage}</h6>
						    <c:remove var="SocialLoginErrorMessage"/>
						</c:if>
						
						<!-- 회원가입 버튼 및 아이디,비밀번호 찾기 버튼 -->
						<div class="social-login-buttons">
						<img class="naver-login-img" alt="네이버로그인" src="<c:url value="/resources/images/btnW_one.png"/>" 
  								  onclick="location.href='${pageContext.request.contextPath}/naver/login';">
						<img class="kakao-login-img" alt="카카오톡로그인" src="<c:url value="/resources/images/kakao_login2.png"/>"
						onclick="location.href='${pageContext.request.contextPath}/kakao/login';">
						<img class="google-login-img" alt="구글로그인" src="<c:url value="/resources/images/g-logo.png"/>"
						onclick="location.href='${pageContext.request.contextPath}/google/login';" width="45px" height="45px" style="padding-left: 10px; padding-top: 5px;">
						</div>
						<br><br><br>
  						<hr>
						<a href="register" class="register">회원 가입하기</a>
						<br>
						<a href="find" class="find" id="find_btn">아이디 및 비밀번호 찾기</a>
					</div>
				</div>
			</div>
		</div>
	</section>
	
		<!-- 로그인 기능!!!!!!!!! --> 
		<script>
		
		$("#loginForm").submit(function() {
			if($("#userId").val()=="") {
				alert("아이디를 입력해 주세요.");
				return false;
			}
			
			if($("#userPassword").val()=="") {
				alert("비밀번호를 입력해 주세요.");
				return false;
			}
		});
		
		<%--
		//로그인 버튼 클릭시 처리
		document.getElementById('loginButton').addEventListener('click', function() {
            var userId = document.getElementById('userId').value;
            var userPassword = document.getElementById('userPassword').value;
            var autoLogin = document.getElementById('checkbox1').checked;
     		
            if(userId == "" || userPassword ==""){
            	alert("아이디와 비밀번호를 모두 입력해주세요.");
            	return;
            }
            
     		var xhr = new XMLHttpRequest();
     		xhr.open('POST', '${pageContext.request.contextPath}/account/login', true);
     		xhr.setRequestHeader('Content-Type', 'application/json');
            
     		xhr.onreadystatechange = function() {
            	if(xhr.readyState == 4) {
            		if(xhr.status == 200) {
            			//alert("로그인 되었습니다.");
            			window.location.href= '${pageContext.request.contextPath}/';
            		} else if(xhr.status == 401 ){
            			alert("아이디 또는 비밀번호를 확인해주세요.");
            		}
            	}
            };
     	
            var jsonData = JSON.stringify ({
            	id : userId,
            	password : userPassword,
            	autoLogin : autoLogin
            });
     		xhr.send(jsonData);
        });
		--%>
		
		// 엔터 키를 눌렀을 때도 로그인 버튼을 클릭한 것과 같은 동작 수행
		document.getElementById('loginForm').addEventListener('keyup', function (event) {
		    if (event.key === "Enter") {
		        document.getElementById('loginButton').click(); // 로그인 버튼 클릭 이벤트 발생
		    }
		});
	
		// 아이디 및 비밀번호 찾기 버튼 클릭 이벤트 리스너
		var findButton = document.getElementById("find_btn");
		if(findButton) {
			findButton.addEventListener('click', function(){
				location.href = "${pageContext.request.contextPath}/account/find";
			});
		}
	    
		</script>  
		
	<!-- / login-register -->

	<!-- / content -->

	<!-- javascript -->
	
	<script
		src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/jquery.easing.min.js"></script>

	<!-- preloader -->
	<script
		src="${pageContext.request.contextPath}/resources/js/preloader.js"></script>
	<!-- / preloader -->

	<!-- / javascript -->
</body>

</html>