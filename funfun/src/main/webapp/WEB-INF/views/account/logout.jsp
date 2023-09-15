<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<body>
	<!-- preloader -->
	<div id="preloader">
		<div class="spinner spinner-round"></div>
	</div>
	
	<!-- 로그아웃 기능!!!!!!!!!!!-->
		<script>
		//로그인 여부 확인 함수
		function isLoggedIn() {
			return sessionStorage.getItem("loginAccount") !== null;
		}
		
		function handleLogout() {
			/*var result = confirm("로그아웃 하시겠습니까?");
			if(result) {
				sessionStorage.removeItem("loginAccount");
				window.location.href = '${pageContext.request.contextPath}/';
			}*/
			var xhr = new XMLHttpRequest();
			xhr.open('post', '${pageContext.request.contextPath}/account/logout', true);
			
			xhr.onreadystatechange = function() {
				if(xhr.readyState == 4 ){
					if(xhr.status == 200){
					alert("로그아웃 되었습니다.");
					sessionStorage.removeItem("loginAccount");
					window.location.href = '${pageContext.request.contextPath}/';
				} else {
					alert("로그아웃에 실패했습니다.");
				}
			}
		};
			xhr.send();
		}
		
		// 초기 페이지 로드 시 실행
	    /* window.addEventListener("load", function() {
	        // 뒤로가기 이벤트 감지
	        window.addEventListener("popstate", function(event){
	            if(isLoggedIn()) {
	                // 로그인 상태인 경우 뒤로가기 이벤트에 대한 처리를 수행하지 않음
	                history.pushState(null, null, window.location.href);
	            }
	        });
	    });*/
		
	   
		//페이지를 다시 로드(뒤로가기 버튼 누를 때)
     	window.onbeforeunload = function() {
     		return null;
     	}
     	
     	window.onpageshow = function(event) {
     		if(event.persisted) {
     		alert("이미 로그인이 완료되었습니다.");
     			window.location.href="${pageContext.request.contextPath}/";
     		}
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