<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
<!-- 부트스트랩 -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<!-- 폰트어썸 -->
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>

<style>
#loginBtn {
	width:100%;
}
.loginTitle {
	margin: 1em 0 1em 0;
	font-weight: bold;
}

#idMargin, #pwMargin {
	margin: 5% 0 0 0;
}
#signUp {
	margin-right: 3%;
}
.loginSide {
	color: grey;
}
.loginSide :visited{
	color: grey;
}

</style>
</head>
<body>
	<!-- menubar -->
	<%@ include file="../common/header.jsp" %>
	
	<!-- 부트스트랩 css 이 페이지에 포함되어 있음 -->
	<section class="ftco-section services-section" style="margin-top:10em;">
		<div class="container">
			<div class="row justify-content-md-center">
				<!-- row : 한 컨텐츠 단위를 나눌 때 필수? row 한 개당 12칸 제공하므로 그 안에서 나눠서 사용 -->
				<div class="col-md-12 shadow p-3 mb-5 bg-white rounded">
					<div class="text-center">
						<form id="loginForm" action="/login" method="post">
							<h1 class="loginTitle">로그인</h1>
							<div class="row justify-content-md-center">
								<div class="col-md-6">
									<div class="input-group">
								 		<span class="input-group-text"><i class="fas fa-user-alt"></i></span>
									  	<input type="text" id="userId" name="memID" class="form-control" placeholder="아이디를 입력해주세요.">
									</div>
								</div>
							</div>
							<div class="mt-2"></div>
							<div class="row justify-content-md-center">
								<div class="col-md-6">
									<div class="input-group">
										<span class="input-group-text"><i class="fas fa-key"></i></span>
										<input type="password" id="userPwd" name="memPW" class="form-control" placeholder="비밀번호를 입력해주세요.">
									</div>
								</div>
							</div>
							<div class="mt-3"></div>
							<div class="row justify-content-md-center">
								<div class="col-md-6 text-center">
									<input type="submit" class="btn" id="loginBtn" value="로그인">
								</div>
							</div>
							<div class="row justify-content-md-center">
									<div class="col-md-6 mr-4 mt-2 mb-5">
										<div class="row justify-content-md-end">
											<div class="col-md-2">
												<a id="signUp" class="loginSide" href="/signUpView">회원가입</a>
											</div>
											<div class="col-md-4">
												<a id="findIdpw" class="loginSide" href="/findIdpw">아이디/비밀번호 찾기</a>
											</div>
										</div>
									</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>
	
	<script src="/resources/assets/js/member.js"></script>
</body>
</html>