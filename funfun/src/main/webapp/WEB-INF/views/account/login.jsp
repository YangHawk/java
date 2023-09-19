<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<style>
    .naver-login-img {
        width: 50px; /* 원하는 가로 크기 */
        height: auto; /* 자동으로 세로 크기 조절하여 비율 유지 */
       	float: left;
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
							<h3 style="color: red;">${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message }</h3>
							<%-- 예외가 저장된 세션에 속성값을 삭제 --%>
							<c:remove var="SPRING_SECURITY_LAST_EXCEPTION"/>
						</c:if>
						
						<!-- 회원가입 버튼 및 아이디,비밀번호 찾기 버튼 -->
						<img class="naver-login-img" alt="네이버로그인" src="<c:url value="/resources/images/btnW_one.png"/>" 
  								  onclick="location.href='${pageContext.request.contextPath}/naver/login';">
						<br><br>
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