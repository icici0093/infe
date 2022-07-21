		// 우편번호 찾기 찾기 화면을 넣을 element
    	var currentScroll = Math.max(document.body.scrollTop, document.documentElement.scrollTop);
		var element_wrap = document.getElementById('popup-wrap');
		var address_modal = document.getElementById('modal-address');
	
	
	    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
	    function code() {
	    	new daum.Postcode({
	            oncomplete: function(data) {
	                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

	                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
	                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	                var roadAddr = data.roadAddress; // 도로명 주소 변수
	                var extraRoadAddr = ''; // 참고 항목 변수

	                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
	                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
	                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	                    extraRoadAddr += data.bname;
	                }
	                // 건물명이 있고, 공동주택일 경우 추가한다.
	                if(data.buildingName !== '' && data.apartment === 'Y'){
	                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                }
	                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
	                if(extraRoadAddr !== ''){
	                    extraRoadAddr = ' (' + extraRoadAddr + ')';
	                }

	                // 우편번호와 주소 정보를 해당 필드에 넣는다.
	                document.getElementById('postcode').value = data.zonecode;
	                document.getElementById('addr').value = roadAddr;
	             
	            }
	        }).open();
	    	document.getElementById('addrDetail').focus();
	    }
	    
		var idConfirm = RegExp(/^[a-z0-9_]{5,20}$/);
		var pwdConfirm = RegExp(/^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/);
		var nameConfirm = RegExp(/^[가-힣]{2,}$/);
		var mailConfirm = RegExp(/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i);
		var totalCheck = false;
		
		// 휴대폰번호 입력 시 숫자만 입력하고 - 자동으로 붙게 하기, 정규식 확인
		var phoneConfirm = $(document).on("keyup", "#userPhone", function() { 
			$(this).val( $(this).val().replace(/[^0-9]/g, "").replace(/(^02|^0505|^1[0-9]{3}|^0[0-9]{2})([0-9]+)?([0-9]{4})/,"$1-$2-$3").replace("--", "-") );
		})
		
		$(function() {
			// 아이디 keyup으로 실시간 입력 검사
			$('#userId').keyup(function(){
                if ($('#userId').val() == '') { // 아이디가 공백일 때
                    $('#idResult').html("아이디를 입력해주세요.");
                    $('#idResult').css("color", "red");
                    
                    $('#userId').css('border', "");
                    totalCheck = false;
                } else if(!idConfirm.test($('#userId').val())) { // 아이디 유효성 검사
                	$('#idResult').html("아이디는 영어 소문자, 숫자, _ 만 사용 가능합니다.");
                    $('#idResult').css("color", "red");
                    
                    $('#userId').css('border', "2px solid red");
                    totalCheck = false;
                } else {
                	$('#idResult').html(""); // 제대로 들어갔을 때
                	$('#userId').css('border', "");
                	totalCheck = true;
                }
            });
			
			// 아이디 중복검사 ajax
			$('#idCheckBtn').click(function() {
				//userid 를 param.
		        var userid =  $("#userId").val(); 
		        
		        $.ajax({
		            async: true,
		            type : 'POST',
		            data : userid,
		            url : "idcheck",
		            dataType : "json",
		            contentType: "application/json; charset=UTF-8",
		            success : function(data) {
		                if (data.cnt > 0) {
		                	$('#idResult').html("중복된 아이디입니다.");
		                    $('#idResult').css("color", "red");
		                    totalCheck = false;
		                    $("#userId").focus();
		                } else {
		                	$('#idResult').html("사용 가능한 아이디입니다.");
		                    $('#idResult').css("color", "green");
		                    totalCheck = true;
		                    $("#userPwd").focus();
		                }
		            },
		            error : function(error) {
		                
		                alert("error : " + error);
		            }
		        });
			});
			
			// 비밀번호 입력 시
			$('#userPwd').keyup(function() {
				if($('#userPwd').val() == '') {
					$('#pwdResult').html("비밀번호를 입력해주세요");
					$('#pwdResult').css("color", "red");
					
					$('#userPwd').css('border', "");
					totalCheck = false;
				} else if(!pwdConfirm.test($('#userPwd').val())) {
					$('#pwdResult').html("영문 소문자, 숫자, 특수문자를 하나 이상 포함하여 8자 이상 사용해야 합니다.");
					$('#pwdResult').css("color", "red");
					
					$('#userPwd').css('border', "2px solid red");
					totalCheck = false;
				} else {
					$('#pwdResult').html("정상 입력되었습니다.");
					$('#pwdResult').css('color', 'green');
					
					$('#userPwd').css('border', "");
					totalCheck = true;
				}
			});
			
			// 비밀번호 일치 확인
			$('#userPwdCheck').keyup(function() {
				if ($('#userPwdCheck').val() != $('#userPwd').val()) {
					$('#pwdCheckResult').html('비밀번호가 일치하지 않습니다.');
					$('#pwdCheckResult').css('color', 'red');
					
					$('#userPwdCheck').css('border', "2px solid red");
					totalCheck = false;
				} else if ($('#userPwdCheck').val() == "") {
					$('#pwdCheckResult').html('비밀번호를 입력해주세요.');
					$('#pwdCheckResult').css('color', 'red');
					
					$('#userPwdCheck').css('border', "");
					totalCheck = false;
				} else {
					$('#pwdCheckResult').html('비밀번호가 일치합니다.');
					$('#pwdCheckResult').css('color', 'green');
					
					$('#userPwdCheck').css('border', "");
					totalCheck = true;
				}
			});
			
			// 이름 한글 두글자 이상인지 확인
			$('#userName').keyup(function() {
				if($('#userName').val() == '') {
					$('#nameResult').html("이름을 입력해주세요.");
					$('#nameResult').css('color', 'red');
					
					$('#userName').css('border', "");
					totalCheck = false;
				} else if (!nameConfirm.test($('#userName').val())) {
					$('#nameResult').html("이름은 한글로 두글자 이상이어야 합니다.");
					$('#nameResult').css('color', 'red');
					
					$('#userName').css('border', "2px solid red");
					totalCheck = false;
				} else {
					$('#nameResult').html('');
					
					$('#userName').css('border', "");
					totalCheck = true;
				}
			});
			
//			// 휴대폰 번호 제대로 됐는지 확인
//			$('#userPhone').keyup(function() {
//				if($('#userPhone').val() == '') {
//					$('#phoneResult').html("휴대폰 번호를 입력해주세요.");
//					$('#phoneResult').css('color', 'red');
//					
//					$('#userPhone').css('border', "");
//					totalCheck = false;
//				} else if (!phoneConfirm.test($('#userPhone').val())) {
//					$('#phoneResult').html("올바른 휴대폰 번호를 입력해주세요.");
//					$('#phoneResult').css('color', 'red');
//					
//					$('#userPhone').css('border', "2px solid red");
//					totalCheck = false;
//				} else {
//					$('#phoneResult').html('');
//					
//					$('#userPhone').css('border', "");
//					totalCheck = true;
//				}
//			});
			// 이메일 주소 제대로 됐는지 확인
			$('#userMail').keyup(function() {
				if($('#userMail').val() == '') {
					$('#mailResult').html("이메일을 입력해주세요.");
					$('#mailResult').css('color', 'red');
					
					$('#userMail').css('border', "");
					totalCheck = false;
				} else if (!mailConfirm.test($('#userMail').val())) {
					$('#mailResult').html("올바른 메일 형식을 입력해주세요.");
					$('#mailResult').css('color', 'red');
					
					$('#userMail').css('border', "2px solid red");
					totalCheck = false;
				} else {
					$('#mailResult').html('');
					
					$('#userMail').css('border', "");
					totalCheck = true;
				}
			});
		});
		
		function enroll(){
			if(!$('#usingTerms').is(':checked')){
				alert("이용약관 동의 버튼을 체크해주세요.");
				return false;
			}
			if(!$('#userTerms').is(':checked')){
				alert("개인정보 처리방침 동의 버튼을 체크해주세요.");
				return false;
			}
			
			if(document.getElementById("userId").value ==''){
		        $('#idResult').html("아이디를 입력해주세요.");
                $('#idResult').css("color", "red");
		        document.getElementById("userId").focus();
		        return false;
		    }
			if(document.getElementById("userPwd").value ==''){
		        $('#pwdResult').html("비밀번호를 입력해주세요.");
                $('#pwdResult').css("color", "red");
		        document.getElementById("userPwd").focus();
		        return false;
		    }
			if(document.getElementById("userPwdCheck").value ==''){
		        $('#pwdCheckResult').html("비밀번호 확인을 입력해주세요.");
                $('#pwdCheckResult').css("color", "red");
		        document.getElementById("userPwdCheck").focus();
		        return false;
		    }
			if(document.getElementById("userName").value ==''){
		        $('#nameResult').html("이름을 입력해주세요.");
                $('#nameResult').css("color", "red");
		        document.getElementById("userName").focus();
		        return false;
		    }
//			if(document.getElementById("userPhone").value ==''){
//		        $('#phoneResult').html("휴대폰 번호를 입력해주세요.");
//                $('#phoneResult').css("color", "red");
//		        document.getElementById("userPhone").focus();
//		        return false;
//		    }
			if(document.getElementById("userMail").value ==''){
		        $('#mailResult').html("주소를 입력해주세요.");
                $('#mailResult').css("color", "red");
		        document.getElementById("userMail").focus();
		        return false;
		    }
			if(document.getElementById("postcode").value ==''){
		        $('#addrResult').html("주소를 입력해주세요.");
                $('#addrResult').css("color", "red");
		        document.getElementById("postcode").focus();
		        return false;
		    }
			if(document.getElementById("addr").value ==''){
		        $('#addrResult').html("주소를 입력해주세요.");
                $('#addrResult').css("color", "red");
		        document.getElementById("addr").focus();
		        return false;
		    }
			if(document.getElementById("addrDetail").value ==''){
		        $('#addrResult').html("상세주소를 입력해주세요.");
                $('#addrResult').css("color", "red");
		        document.getElementById("addrDetail").focus();
		        return false;
		    }
			
		var thisForm = document.signUpForm;
		thisForm.submit();
		}
		
		