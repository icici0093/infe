<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign up</title>
	<!-- 부트스트랩 -->
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
  	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

	<!-- Postcodify 로딩 -->
	
	<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>
	
	<!-- 폰트어썸 -->
	<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
	
<style>
	#signUpTitle {
		margin: 1em 0 1em 0;
		font-weight: bold;
	}
	.signUpTerms {
		color: black !important;
		font-weight: bold;
	}
	
	<!-- 모달 스크롤링 설정 -->
	.modal-dialog{
	    overflow-y: initial !important
	}
	.modal-body{
	    height: 400px;
	    overflow-y: auto;
	}
	
	#signUpBtn {
		width: 100%;
	}
	
	
</style>
</head>
<body>
	<!-- menubar -->
	<%@ include file="../common/header.jsp" %>
	
	<!-- 본문 -->
	<section class="ftco-section services-section" style="margin-top:5em;">
		<div class="container bg-white rounded">
			<div class="row justify-content-md-center">
				<div class="col-md-6">
					<!-- 회원가입 타이틀 -->
					<div class="text-center">
						<h1 id="signUpTitle">회원가입</h1>
					</div>
					
					<!-- 이용약관 -->
					<div>
						<input type="checkbox" id="usingTerms">
						<label for="usingTerms"> 이용약관에 동의합니다. (필수) </label>
						<a class="signUpTerms" data-toggle="modal" data-target="#using"> 약관 보기 </a>
					</div>
					<!-- 이용약관 모달 -->
			        <div class="modal fade" id="using" role="dialog" aria-labelledby="introHeader" aria-hidden="true" tabindex="-1">
			            <div class="modal-dialog">
			                <div class="modal-content">
			                    <div class="modal-header">
			                        <h4 class="modal-title">이용 약관</h4>
			                    </div>
			                    <div class="modal-body">
			                    	없지롱~~~~~~!!
			                    </div>
			                    <div class="modal-footer">
			                        <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
			                    </div>
			                </div>
			            </div>
			        </div>
			        
			        <!-- 개인정보 처리방침 약관 -->
			        <div>
						<input id="userTerms" type="checkbox">
						<label for="userTerms">개인정보 처리방침에 동의합니다. (필수) </label>
						<a class="signUpTerms" data-toggle="modal" data-target="#user"> 약관 보기 </a>
					</div>
			        <!-- 개인정보 처리방침 약관 모달 -->
				    <div class="modal fade" id="user" role="dialog" aria-labelledby="introHeader" aria-hidden="true" tabindex="-1">
						<div class="modal-dialog">
			                <div class="modal-content">
			                    <div class="modal-header">
			                        <h4 class="modal-title">개인정보 처리방침</h4>
			                    </div>
			                    <div class="modal-body">
			                    	ㅋㅋㄹㅃㅃ
			                    </div>
			                    <div class="modal-footer">
			                        <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
			                    </div>
			                </div>
			            </div>
			        </div>
			        
			        <!-- 회원가입 form 시작 -->
			        <form id="signUpForm" name="signUpForm" action="/signUp" method="POST">
			        <div class="row">
						<div class="pb-3">
							<div> 아이디 </div> 
							<input type="text" id="userId" name="memID" class="form-control" placeholder="아이디는 영문자 5자 이상이어야 합니다." autocomplete="off">
							<div id="idResult"></div>
						</div>
						<div class="pb-3">
							<div> 비밀번호 </div>
							<input type="password" id="userPwd" name="memPW" class="form-control" placeholder="비밀번호는 영문, 숫자로 구성되며 8자 이상이어야 합니다."  autocomplete="off">
							<div id="pwdResult"></div>
						</div>
						<div class="pb-3">
							<div> 비밀번호 확인 </div>
							<input type="password" id="userPwdCheck" class="form-control" placeholder="비밀번호는 영문, 숫자로 구성되며 8자 이상이어야 합니다." autocomplete="off">
							<div id="pwdCheckResult"></div>
						</div>
						<div class="pb-3">
							<div> 이름 </div>
							<input type="text" id="userName" name="name" class="form-control" autocomplete="off" placeholder="이름은 한글만 입력해주세요.">
							<div id="nameResult"></div>
						</div>
						<div class="pb-3">
							<div> 휴대폰번호 </div>
							<input type="text" id="userPhone" name="phone" maxlength="13" class="form-control" autocomplete="off" placeholder="휴대폰번호를 입력해주세요.">
							<div id="phoneResult"></div>
						</div>
						<div class="pb-3">
							<div> 이메일 </div>
							<input type="email" id="userMail" name="mail" class="form-control" autocomplete="off" placeholder="이메일을 입력해주세요.">
							<div id="mailResult"></div>
						</div>
						<div class="pb-3">
							<div> 주소 </div>
							<!-- 주소는 api 적용 -->
							<div class="row">
								<div class="col-md-3" style="padding-right: 0;">
									<input type="text" id="postcode" name="postcode" class="form-control"> 
								</div>
								<div class="col-md-4">
									<button type="button" class="btn" onclick="code();">주소 검색</button>
								</div>
							</div>
							<div class="row input-group" style="margin-top:1em;">
								<div class="col-6 col-md-6" style="padding-right: 0;">
									<input type="text" id="addr" name="addr" class="postcodify_details form-control" value="" autocomplete="off">
								</div>
								<div class="col-6 col-md-6" style="padding-right: 0;">
									<input type="text" id="addrDetail" name="addrDt" class="postcodify_details form-control" value="" autocomplete="off" placeholder="상세주소를 입력해주세요.">
								</div>
							</div>
							<div id="addrResult"></div>
<!-- 							<div id="wrap" style="display:none;border:1px solid;width:500px;height:300px;margin:5px 0;position:relative"> -->
<!-- 								<img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" onclick="foldDaumPostcode()" alt="접기 버튼"> -->
<!-- 							</div> -->
							
<!-- 							주소 모달 -->
<!-- 							<div class="modal fade modal-address" id="modal-address" role="dialog"> -->
<!-- 								<div class="modal-dialog"> -->
<!-- 									<div class="modal-content"> -->
<!-- 										<div class="modal-header"> -->
<!-- 											<h4 class="modal-title">주소찾기</h4> -->
<!-- 										</div> -->
<!-- 										<div class="modal-body" id="popup-wrap">내용 더하기</div> -->
<!-- 										<div class="modal-footer"> -->
<!-- 											<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 							주소 모달 끝 -->
							
						</div>
						<div>
							<button type="button" id="signUpBtn" class="btn" onclick="enroll();">회원가입</button>
						</div>
			        </div>
					</form>
			        
			        
			        
			    </div>
			</div>
		</div>
		
	</section>
	
	<!-- footer -->
	<%@ include file="../common/footer.jsp" %>
	
	<script src="/resources/assets/js/member.js"></script>
	<script src="/resources/assets/js/signUp.js"></script>
	
	<!-- 다음주소 API 스크립트 시작 -->
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	
	<!-- id 중복검사 ajax, 회원가입 버튼 클릭 시 전체 검사 한번 더 하는거 코드 추가 필요 -->
</body>
</html>