var data = null;
var pi = null;
var page = 1;

var toolbarOptions = {
	container : [
	  ['link', 'image', 'video', 'formula', 'code-block'],
	  ['emoji'],
	]
};
const limit = 1000; // 1000자 제한

$(function() {
	getReplyList(page);
	// Quill
	var quill = new Quill('#editor', {
		modules: {
//			imageResize: {},
//			imageUpload: {
//				url: '/file/upload/image',
//				method: 'POST',
//				name: 'uploadImage',
//				withCredentials: false,
//				callbackOK: (serverResponse, next) => {
//			    	next(serverResponse);
//			    },
//				callbackKO: serverError => {
//					alert(serverError);
//				},
//				checkBeforeSend: (file, next) => {
//			    	console.log(file);
//			    	next(file);
//			    }
//			},
//          "toolbar": toolbarOptions,
//          "emoji-toolbar": true,
		},
		placeholder: '댓글을 입력하세요.',
		theme: 'bubble'
	});
	
		// 글자수 제한
	quill.on('text-change', function (delta, old, source) {
	  if (quill.getLength() > limit) {
	    quill.deleteText(limit, quill.getLength());
	  }
	});
	
	// 등록하기
	$("#rSubmit").on("click", function() {
		var boNo = $("#boNo").val();
		var rMbNo = $("#loginMbNo").val();
		var loginMbName = $("#loginMbName").val();
		var rContent = quill.root.innerHTML;
		console.log(quill.getLength());
//		var rContent = $("#editor").val();
//		console.log(rContent);
		if(quill.getLength() > 3) {
			$.ajax({
				url : "/board/addReply",
				type : "post",
				data : {"boNo": boNo , "content" : rContent, "memNo" : rMbNo, "name" : loginMbName},
				success : function(result) {
					if(result == "success") {
						/* 댓글 불러오기 */
						getReplyList(page);
						quill.deleteText(0,100000000);
					} else if(result == "fail") {
						alert("댓글 등록 실패..");
					}
				},
				error : function() {
					alert("전송 실패!");
				}
			});
		} else {
			alert("댓글은 3자 이상 입력해주세요.");
		}
	});
});

// 리스트 통신
function getReplyList(page) {
	var boNo = $("#boNo").val();
	var loginMbNo = $("#loginMbNo").val();
	
	$.ajax({
		url : "/board/replyList",
		data : {"boNo" : boNo, "page" : page},
		type : "get",
		dataType : "json",
		async: false,
		success : function(map) {
			data = map.rList;
			pi = map.page;
			
			page = pi.maxPage;
			replyList(data, pi.listCount, loginMbNo);
			replyPage(pi);
		},
		error : function() {
			// 댓글 없을 경우 여기로 이동
			$("#rCount").text("0");
		}
	});
}

// 댓글 리스트
function replyList(data, listCount, loginMbNo) {
	var $rList = $("#rList");
	$rList.html("");
	
	var boMbNo = $("#boMbNo").val();
	
	var $div;
	var $rWriter;
	var $rContent;
	var $btnArea;
	var $btnTool = "";
	
	$("#rCount").text(listCount);
	if(data.length > 0) {
		
		/* 댓글 */
		for(var i in data) {
			// 댓글 작성자인 경우 배경색 추가
			if(loginMbNo != data[i].memNo) {
				$div = $("<div class='reply-box'>");
			} else {
				$div = $("<div class='reply-box my-reply'>");
			}
			
			$rWriter = $("<div style='margin-left: " + 50 * data[i].rIndent +"px !important;'>")
			.append("<span class='nickName'>" + data[i].name + "</span>&nbsp");
//			.append("<input id='reNo' type='hidden' value='" + data[i].reNo +"'>")
//			.append("<input id='rGroup' type='hidden' value='" + data[i].rGroup +"'>")
//			.append("<input id='rStep' type='hidden' value='" + data[i].rStep +"'>")
//			.append("<input id='rIndent' type='hidden' value='" + data[i].rIndent +"'>");
			if(boMbNo == data[i].memNo) {
				$rWriter.append("&nbsp<div class='writerTag'>작성자</div>");
			}
			$rWriter.append("<span class='insertDate'>" + data[i].insertDate + "</span>");
			
			$rContent = $("<div class='contents-box' style='margin-left: " + 50 * data[i].rIndent +"px !important;'>").append(data[i].content);
			
			$btnArea = $("<div id='button-area' style='margin-left: " + 50 * data[i].rIndent +"px !important;'>");
			$replyBtn = $("<div id='reply-div' style='margin-left: " + 50 * data[i].rIndent +"px !important;'>");
			
			if(loginMbNo != ''){
				$btnArea.append("<button class='btn btn-sm' onclick='reReply(this,"+data[i].reNo+","+data[i].rGroup+","+data[i].rStep+","+data[i].rIndent+");'>답글</button>");
			}
			
			// 수정+삭제 버튼
			if(loginMbNo == data[i].memNo) {
				$btnTool =$("<div class='btn-group'>");
				$btnTool
				.append("<button class='btn btn-sm btn-outline-light btn-rounded' onclick='modifyReply(this," + data[i].reNo + ");'>수정</button>")
				.append("<button class='btn btn-sm btn-outline-light btn-rounded' onclick='removeReply(" + data[i].reNo + ");'>삭제</button>");
				
				$btnArea.append($btnTool);
			}
			if(data[i].content != "삭제된 댓글입니다."){
				$div.append($rWriter);
			}
			$div.append($rContent);
			
			if(data[i].content != "삭제된 댓글입니다."){
				$div.append($btnArea);
				$div.append($replyBtn);
			}
			$div.attr("id", "boReply" + data[i].reNo);
			
			$rList.append($div);
		}
	}
//			getReComments();
}

// 페이징
function replyPage(pi) {
	var $rPage = $("#rPage");
	$rPage.html("");
	
	var $ul;
	var $liBefore="";
	var $liCurrent;
	var $liAfter="";
	
	$ul = $("<ul class='pagination pagination-sm justify-content-center'>");

	if(pi.currentPage > 1) {
		$liBefore = $("<li class='page-item' onclick='getReplyList(" + (pi.currentPage - 1) + ")'>");
		$spanTag = $("<span class='page-link' aria-label='Next'>")
		.append("<span>&laquo;</span>");
		$liBefore.append($spanTag);
	}
	$ul.append($liBefore);
	
	for(var p = pi.startPage; p < pi.endPage + 1; p++) {
		// onclick='getReplyList(p)'
		if(p == pi.currentPage) {
			$liCurrent = $("<li class='page-item active'>");
		} else {
			$liCurrent = $("<li class='page-item' onclick='getReplyList(" + p + ")'>");
		}
		
		$liCurrent.append("<span onclick='getReplyList("+p+")' class='page-link'>" + p + "</span>");
		
		$ul.append($liCurrent);
	}
	
	if(pi.currentPage < pi.maxPage) {
		$liAfter = $("<li class='page-item' onclick='getReplyList(" + (pi.currentPage + 1) + ")'>");
		$spanTag = $("<span class='page-link' aria-label='Next'>")
		.append("<span>&raquo;</span>");
		$liAfter.append($spanTag);
	}
	$ul.append($liAfter);
	
	$rPage.append($ul);
}
// 답글
function reReply(obj, reNo, rGroup, rStep, rIndent){
	$divReply = $(obj).parent();
	$divReply.html("");
	
	$btnArea = $divReply.next();
	$btnArea.html("");
	$btnArea.addClass("btn-group");
	$btnArea.addClass("reBtn");
	
	$text = $("<div id='editor3' style='width: 100%;'></div>");
	
	$btnArea
	.append("<button id='reply-btn' class='btn btn-sm btn-outline-light btn-rounded'>등록</button>")
	.append("<button class='btn btn-sm btn-outline-light btn-rounded' onclick='getReplyList(page);'>취소</button>");
	
	$divReply.append($text);
	
	var quill3 = new Quill('#editor3', {
		modules: {
		},
		placeholder: '댓글을 입력하세요.',
		theme: 'bubble'
	});
	
	// 글자수 제한
	quill3.on('text-change', function (delta, old, source) {
	  if (quill3.getLength() > limit) {
	    quill3.deleteText(limit, quill3.getLength());
	  }
	});
	
	$("#reply-btn").on("click", function(){
		var boNo = $("#boNo").val();
//		var reNo = $("#reNo").val();
		var rMbNo = $("#loginMbNo").val();
		var loginMbName = $("#loginMbName").val();
		var replyContent = quill3.root.innerHTML;
//		var rGroup = $("#rGroup").val();
//		var rStep = $("#rStep").val();
//		var rIndent = $("#rIndent").val();
		console.log("reNo == " + reNo);
		console.log("rIndent == " + rIndent);
		if(quill3.getLength() > 3){
			$.ajax({
				url : "/board/addReComment",
				type : "post",
				data : {"boNo" : boNo, "reNo" : reNo, "content" : replyContent, 
				"memNo" : rMbNo, "name" : loginMbName,
				"rGroup" : rGroup, "rStep" : rStep, "rIndent" : rIndent},
				success : function(result){
					if(result == "success"){
						console.log("댓글 -완-");

						getReplyList(page);
					}else{
						alert("답글 작성 실패!");
					}
				},
				error : function(){
					console.log("전송 실패. . . .");
				}
			});
		}else{
			alert("댓글 내용을 입력해주세요.");
		}
	});
}
// 수정하기
function modifyReply(obj, reNo) {
	$reply = $("#boReply" + reNo).children("div:eq(1)");
	var replyContent = $reply.html();
	
	$(obj).parent().parent().prev().html("");
	$divModify = $(obj).parent().parent();
	$divModify.html("");
	
	$btnArea = $divModify.next();
	$btnArea.html("");
	$btnArea.addClass("btn-group");
	$btnArea.addClass("reBtn");
	
	$text = $("<div id='editor2' style='width:100%'>" + replyContent + "</div>");
	$btnArea
	.append("<button id='modify-btn' class='btn btn-sm btn-outline-light btn-rounded'>수정</button>")
	.append("<button class='btn btn-sm btn-outline-light btn-rounded' onclick='getReplyList(page);'>취소</button>");
	
	$divModify.append($text);
	
	var quill2 = new Quill('#editor2', {
		modules: {
//			imageResize: {},
//			imageUpload: {
//				url: '/file/upload/image',
//				method: 'POST',
//				name: 'uploadImage',
//				withCredentials: false,
//				callbackOK: (serverResponse, next) => {
//			    	next(serverResponse);
//			    },
//				callbackKO: serverError => {
//					alert(serverError);
//				},
//				checkBeforeSend: (file, next) => {
//			    	console.log(file);
//			    	next(file);
//			    }
//			},
//	      "toolbar": toolbarOptions,
//	      "emoji-toolbar": true,
		},
		placeholder: '댓글을 입력하세요.',
		theme: 'bubble'
	});
	
	$("#modify-btn").on("click", function() {
		var mContents = quill2.root.innerHTML;
	
		if(quill2.getLength() > 3) {
			$.ajax({
				url : "/board/modifyReply",
				type : "post",
				data : { "reNo" : reNo, "content" : mContents },
				success : function(data) {
					if(data == "success") {
						getReplyList(page);
					} else {
						alert("댓글 수정 실패!");
					}
				},
				error : function() {
					console.log("전송 실패..");
				}
			});
		} else {
			alert("댓글 내용을 입력해주세요.");
		}
	});
}

// 삭제하기
function removeReply(reNo) {
	var result = confirm("댓글을 삭제하시겠습니까?");
	
	if(result) {
		$.ajax({
			url : "/board/deleteReply",
			type : "get",
			data : { "reNo" : reNo },
			success : function(data) { 
				if(data == "success") {
					getReplyList();
				} else {
					alert("댓글 삭제 실패!");
				}
			},
			error : function() {
				alert("전송 실패..");
			}
		});
	}
}