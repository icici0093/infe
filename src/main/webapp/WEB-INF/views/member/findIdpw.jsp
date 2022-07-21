<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디/비밀번호 찾기</title>

<!-- 부트스트랩 -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	  integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	  crossorigin="anonymous">

<!-- 폰트어썸 -->
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>

<style>
	#findTitle {
		margin: 1em 0 1em 0;
		font-weight: bold;
	}
	
	#findIdBtn {
		width: 100%;
	}
	
	.Result {
		font-size: 30px;
	}
	
	.idResult {
		font-weight: bold;
		font-size: 35px;
		color: black;
	}
</style>


</head>
<body>
	<!-- menubar -->
	<%@ include file="../common/header.jsp" %>
	
	<!-- 본문 -->
	<section class="ftco-section services-section" style="margin-top:10em;">
		<div class="container shadow p-3 mb-5 bg-white rounded">
		<!-- container에 그림자 테두리 넣기 -->
			<div class="row justify-content-md-center">
			<!-- row justify-content-md-center : 내 아래 들어간 div 애들은 가운데정렬이다 -->
				<div class="col-md-6">
					<div class="text-center">
						<h1 id="findTitle">아이디 / 비밀번호 찾기</h1>
					</div>
					
					<div class="row pb-5">
						<input type="button" class="col-md-6 btn" value="아이디 찾기">
						<input type="button" class="col-md-6 btn" value="비밀번호 찾기">
					</div>
					
					<!-- 아이디 찾기 클릭 시 노출될 div -->
					<div class="id_Container">
						<form id="signUpForm" action="/findId">
							<div class="pb-3">
								<div> 이름 </div> 
								<input type="text" id="userId" class="form-control" placeholder="아이디는 영문자 5자 이상이어야 합니다." autocomplete="off">
								<div id="idResult"></div>
							</div>
							<div class="pb-5">
								<div> 이메일 </div>
								<input type="password" id="userPwd" class="form-control" placeholder="비밀번호는 영문, 숫자로 구성되며 8자 이상이어야 합니다."  autocomplete="off">
								<div id="pwdResult"></div>
							</div>
							
							<div>
								<input type="button" id="findIdBtn" class="btn" value="아이디 찾기">
							</div>
						</form>
					</div>
					<div class="text-center pt-3 pb-5">
						<span class="Result">회원님의 아이디는</span> <span class="idResult">zzfQQ</span> <span class="Result">입니다.</span>
					</div>
					
					
					<!-- 비밀번호 찾기 시 노출될 div -->
					<div class="pw_Container">
						
					</div>
					
				</div>
			</div>
		</div>
	</section>
	<!-- 본문 끝 -->
	
	<script src="/resources/assets/js/member.js"></script>
</body>
</html>