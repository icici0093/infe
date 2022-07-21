<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="//cdn.quilljs.com/1.3.6/quill.js"></script>
<script src="//cdn.quilljs.com/1.3.6/quill.min.js"></script>
<link href="/resources/assets/js/quill-emoji.css" rel="stylesheet" type="text/css">
<link href="//cdn.quilljs.com/1.3.6/quill.snow.css" rel="stylesheet">
<link href="//cdn.quilljs.com/1.3.6/quill.bubble.css" rel="stylesheet">
<link href="/resources/assets/css/reply.css" rel="stylesheet">

<title>Board</title>
<style>
	#top{
		padding: 15px 0;
		background: #37517e;
	}
	.page-link {
		position: relative;
		display: block;
		color: #34b7a7 !important;
		text-decoration: none;
		background-color: #e9e8e6;
		border: 1px solid #dee2e6;
		transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out, border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
	}
	@media (prefers-reduced-motion: reduce) {
		.page-link {
		  transition: none;
		}
	}
	.page-link:hover {
		z-index: 2;
		color: #0a58ca;
		background-color: #e9e8e6;
		border-color: #e9e8e6;
	}
	.page-link:focus {
		z-index: 3;
		color: #0a58ca;
		background-color: #e9e8e6 !important;
		outline: 0;
		box-shadow: 0 0 0 0.25rem rgba(13, 110, 253, 0.25);
	}
	.page-item.active .page-link {
		z-index: 3;
		color: #fff !important;
		background-color: #34b7a7 !important;
		border-color: #e9e8e6 !important;
	}
</style>
<script>
	function del(bo_no){
		var delConfirm = confirm('정말 삭제하시겠습니까?');
		if (delConfirm) {
			location.href='/board/boardDelete?boNo='+bo_no;
		}
	}
</script>
</head>
<body>
	<!-- menubar -->
	<%@ include file="../common/header.jsp" %>
	<section id="top" class="d-flex align-items-center">
		<div class="row">
			<div class="col-lg-4 align-self-center">
				<h4 class="page-title text-truncate text-dark font-weight-medium mb-1">&nbsp;</h4>
			</div>	
		</div>
	</section>
	<div id="main-wrapper" data-theme="light" data-layout="vertical"
		data-navbarbg="skin6" data-sidebartype="full"
		data-sidebar-position="fixed" data-header-position="fixed"
		data-boxed-layout="full">
		<div class="container">
			<div class="page-breadcrumb" style="margin: 2em;">
				<div class="row">
					<div class="col-lg-4 align-self-center">
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-10"> 
				</div>
				<div class="col-md-2">
					<div class="btn-group border-0" style="float: right; padding-right: 13px;">
						<c:if test="${loginUser.memNo eq thisEtt.mem_no }">
							<button onclick="location.href='/board/boardModifyView?boNo=${thisEtt.bo_no}'" class="btn btn-outline-light btn-rounded">수정</button>
							<button onclick="del(${thisEtt.bo_no})" class="btn btn-outline-light btn-rounded">삭제</button>
						</c:if>
					</div>
				</div>
			</div>
			<div class="container-fluid">
				<div class="row">
					<div class="col-12" style="margin:1em 0 1em 0;">
						<div class="card">
							<div class="card-body ">
								<h4 class="card-title">
									${ thisEtt.title }
									<input type="hidden" name="boNo">
								</h4>
								<div class="row">
									<h6 class="card-subtitle col-12" style="text-align: right">
									작성자 <b>${thisEtt.name}</b> &nbsp;&nbsp;&nbsp; 조회수 ${thisEtt.count} &nbsp;&nbsp;&nbsp; 작성일 ${ thisEtt.insert_date }</h6>
								</div>
							</div>
							<div class="card-body">
								<c:if test="${ !empty thisEtt.content }">
									<p>${ thisEtt.content }</p>
								</c:if>
							</div>
                           	<c:if test="${ !empty fileArr }">
	                            <div class="card-body file-box">
	                            	<c:forEach var="file" items="${fileArr }" varStatus="i">
	                            		<div>
	                            			<b>첨부파일${ i.count } : </b>
	                            			<input type="hidden" value="${ file.fi_no}">
	                            			<c:url var="fileDownload" value="/file/download">
												<c:param name="fiNo" value="${ file.fi_no }"></c:param>
											</c:url>
	                            			<a class="text-muted" href="${ fileDownload }" onclick="return confirm('파일을 다운로드하시겠습니까?');">${ file.fi_real_file_name }</a>
	                            		</div> 
	                            	</c:forEach>
                            	</div>
                           	</c:if>
                            <!-- 댓글 -->
                            <div class="card-body">
                            	<!-- board.js 파일과 연동하기 위해서 -->
                            	<input id="boNo" type="hidden" value="${ thisEtt.bo_no }">
                            	<input id="boMbNo" type="hidden" value="${ thisEtt.mem_no }">
                            	<input id="loginMbNo" type="hidden" value="${ loginUser.memNo }">
                            	<input id="loginMbName" type="hidden" value="${ loginUser.name }">
                            
                                <h6 class="card-subtitle float-right"><b>Comments</b> <span id="rCount"></span></h6>
                                <hr>
                                
								<!-- 댓글 등록 -->
								<div class="reply-enter">
								<c:if test="${!empty loginUser }">
      								<div id="editor"></div>
									<button id="rSubmit" class="reply-enter-btn btn" style="float: left;">등록</button>
								</c:if>
								</div>
								
                                <!-- 댓글 리스트 -->
                                <div id="rList"></div>
                                <!-- 페이징 -->
                                <nav id="rPage"></nav>
                            </div>
						</div>
					</div>
					<div class="col-md-11">&nbsp;</div>
					<div class="col-md-1">
						<button onclick="location.href='/board/boardList'" class="btn" style="float: right;">목록</button>
					</div>
				</div>
			</div>
			
		</div>
	</div>
	<div style="margin:30em;">
	</div>
	<!-- footer -->
	<%@ include file="../common/footer.jsp" %>
	<script src="/resources/assets/js/board.js"></script>
	<script src="/resources/assets/js/boardReply.js"></script>
	<script>
		var url = window.location.pathname;
		if(url == '/board/boardDetail'){
			$('.boardList').addClass('active');
		}
	</script>
</body>
</html>