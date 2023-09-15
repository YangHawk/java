<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>

	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>


<body>
	<!-- preloader -->
	<div id="preloader">
		<div class="spinner spinner-round"></div>
	</div>
	<!-- / preloader -->
	
	
	
	<!-- content -->

	<!-- 아이디 및 비밀번호 찾기 -->
	<section id="find">
		<div class="container">
			<div class="row">
				<div class="col-sm-6">
					<!-- 아이디 찾기 폼 코드 -->
					<div id="findIdForm">
						<h3 class="log-title">아이디 찾기</h3>
						<form action="${pageContext.request.contextPath}/account/find-id" method="post">
							<!-- 이름 입력 -->
							<div class="form-group">
								<input type="text" class="form-control" id="name_id"
								name="name" placeholder="이름" required data-error="이름을 입력해주세요.">
								<div class="help-block with-errors"></div>
							</div>
							<!-- 이메일 입력 -->
							<div class="form-group">
								<input type="email" class="form-control" id="email_id"
								name="email" placeholder="이메일" required data-error="일치하는 이메일이 없습니다.">
								<div class="help-block with-errors"></div>
							</div>
							<!-- 아이디 찾기 버튼 -->
							<div class="text-center">
								<button type="submit" class="btn btn-md btn-primary-filled btn-log btn-rounded btn-id" >아이디 찾기</button>
							</div>
						</form>
					</div>

					<!-- 비밀번호 찾기 폼 코드 -->
					<div id="findPasswordForm">
					<br>
					<br>
					<br>
						<h3 class="log-title">비밀번호 찾기</h3>
						<form action="${pageContext.request.contextPath}/account/find-password" method="post">
							<!-- 이메일 입력 -->
							<div class="form-group">
								<input type="text" class="form-control" id="name_password"
								name="name" placeholder="이름" required data-error="이름을 입력해주세요.">
								<div class="help-block with-errors"></div>
							</div>
							<div class="form-group">
								<input type="text" class="form-control" id="id_password"
								name="id" placeholder="아이디" required data-error="아이디를 입력해주세요.">
								<div class="help-block with-errors"></div>
							</div>
							<div class="form-group">
								<input type="email" class="form-control" id="email_password"
								name="email" placeholder="이메일" required data-error="일치하는 이메일이 없습니다.">
								<div class="help-block with-errors"></div>
							</div>
							<!-- 비밀번호 찾기 버튼 -->
							<div class="text-center">
								<button type="submit" class="btn btn-md btn-primary-filled btn-log btn-rounded btn-password">비밀번호 찾기</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>
	
	<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
	<script>
		//아이디 비밀번호 찾기
		var id_rule = /^[a-z]+[a-z0-9]{3,12}$/g;
		var email_rule = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	
		var nameInput, idInput, emailInput;
		
		
		function formCheck(type) {
		    if (type == 'id') {
		        nameInput = $("#name_id");
		        emailInput = $("#email_id");
		    } else if (type == 'password') {
		        nameInput = $("#name_password");
		        idInput = $("#id_password");
		        emailInput = $("#email_password");
		    }

		    if (nameInput.val() == "" || nameInput.val() == null) {
		        alert("이름을 입력해주세요.");
		        nameInput.focus();
		        return false;
		    }

		    if (type == 'password' && (idInput.val() == "" || idInput.val() == null)) {
		        alert("아이디를 입력해주세요.");
		        idInput.focus();
		        return false;
		    }

		    if (emailInput.val() == "" || emailInput.val() == null) {
		        alert("이메일을 입력해주세요.");
		        emailInput.focus();
		        return false;
		    }

		    if (!email_rule.test(emailInput.val())) {
		        alert("이메일 형식에 맞게 입력해주세요.");
		        return false;
		    }

		    var confirmationMessage = type == 'id' ? "아이디를 찾으시겠습니까?" : "비밀번호를 찾으시겠습니까?";
		    
		    if (confirm(confirmationMessage)) {
		        if (type == 'id') {
		            $("#findIdForm").submit();
		        } else if (type == 'password') {
		            $("#findPasswordForm").submit();
		        }
		        return true;
		    }
		    return false;
		}

	
		//아이디 찾기 버튼 클릭시 formCheck 함수 호출
		$(".btn-id").click(function() {
	        formCheck('id');
	    });
		
		//비밀번호 찾기 버튼 클릭시 formCheck 함수 호출
		$(".btn-password").click(function() {
	        formCheck('password');
	    });
	
		
	</script>
		

	<!-- javascript -->
	<script src="${pageContext.request.contextPath}/resources/js/jquery.easing.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
	<!-- preloader -->
	<script src="${pageContext.request.contextPath}/resources/js/preloader.js"></script>
	<!-- / preloader -->

	<!-- / javascript -->

</body>
</html>
