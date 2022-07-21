<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean id="CubeUtils" class="com.infe.common.CubeUtils" scope="page"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Social</title>
<style>
	body, input, textarea, select, button {
	    font-family: "Noto Sans KR", "Apple SD Gothic Neo", sans-serif;
	    font-size: 1rem;
	    font-style: normal;
	    font-weight: 400;
	    color: #2d2c2b;
	    letter-spacing: -0.05em;
	    line-height: 1em;
	    vertical-align: top;
	}

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
	.form-control:focus{
		outline:none !important;
	}
	button:focus{
		outline:none;
	}
	a:focus{
		outline:none;
	}
	
	@media (max-width: 1280px)
	.page_card-sort {
	    width: auto;
	    margin-left: 3.125vw;
	    margin-right: 3.125vw;
	    margin-top: 4.6875vw;
	    margin-bottom: 2.34375vw;
	}
	
	dl, ul, ol, li {
    	list-style: none;
	}
	
	.page_card-sort .load_box {
	    display: -webkit-box;
	    display: -ms-flexbox;
	    display: flex;
	    -ms-flex-wrap: wrap;
	    flex-wrap: wrap;
	    -webkit-box-align: start;
	    -ms-flex-align: start;
	    align-items: flex-start;
	}
	
	.page_card-sort .load_box li:nth-child(3n + 1) {
	    margin-left: 0;
	}
	
	.page_card-sort .load_box li {
	    width: 32%;
	    position: relative;
	    margin-bottom: 28px;
	    margin-left: 2%;
	}
	
	.page_card-sort .load_box li > a {
	    display: block;
	    padding: 10px;
	    border: 1px solid #d6d6d6;
	    border-radius: 6px;
	}
	
	.page_card-sort .load_box li > a img {
	    display: block;
	    width: 100%;
	    height: auto;
	}
	
	.page_card-sort .load_box li > a .txt_guard {
	    padding: 12px 4px 8px 4px;
	}
	
	.page_card-sort .load_box li > a .txt_guard em {
	    color: #34b7a7;
	    font-weight: 500;
	    margin-bottom: 10px;
	}
	
	.page_card-sort .load_box li > a .txt_guard p {
	    font-size: 1.125rem;
	    overflow: hidden;
	    white-space: nowrap;
	    text-overflow: ellipsis;
	    margin-bottom: 60px;
	    line-height: 1.2em;
	    color: #323232;
	}
	
	.page_card-sort .load_box li > a .txt_guard span {
	    font-size: 0.875rem;
	    color: #999;
	    letter-spacing: 0;
	}
	
	.layerpopup.semi_video {
	    top: 100px;
	    margin-left: -500px;
	    width: 1000px;
	}
	
	.layerpopup.center {
	    position: absolute;
	    left: 50%;
	    top: 100px;
	    margin-left: -500px;
	    width: 1000px;
	    opacity: 0;
	    transition: all 0.4s;
	    -webkit-transition: all 0.4s;
	}
	
	.layerpopup {
	    display: none;
	    position: fixed;
	    top: 0;
	    right: -100%;
	    width: 940px;
	    height: 100%;
	    z-index: 101;
	    transition: right 0.6s cubic-bezier(.76,.01,.19,.99);
	    -webkit-transition: right 0.6s cubic-bezier(.76,.01,.19,.99);
	}
	
	.layerpopup.center.open {
	    opacity: 1;
	}
	
	.layerpopup.g20 .pop_wrap {
	    overflow-y: visible;
	}
	
	.layerpopup.center .pop_wrap {
	    height: auto;
	    overflow-y: visible;
	}
	
	.layerpopup .pop_wrap {
	    position: absolute;
	    top: 0;
	    left: 0;
	    width: 100%;
	    height: 100%;
	    background-color: #fff;
	    overflow-y: auto;
	    box-sizing: border-box;
	    z-index: 3;
	}
	
	.layerpopup .pop_tit {
	    position: relative;
	    padding: 0 60px 0 50px;
	    background-color: #34b7a7;
	    color: #fff;
	}
	.layerpopup.g20 .btn_x {
	    left: -60px;
	    right: auto;
	    background: #34b7a7 url(/resources/images/btn_x-w.png) center center no-repeat;
	}

	.layerpopup.g20.center .btn_x {
	    left: auto;
	    right: -60px;
	    background: #34b7a7 url(/resources/images/btn_x-w.png) center center no-repeat;
	}
	.layerpopup.center .btn_x {
	    right: -60px;
	}
	.layerpopup .btn_x {
	    display: block;
	    position: absolute;
	    top: 0;
	    right: 0;
	    width: 60px;
	    height: 60px;
	    background: #fff url(/resources/images/btn_x-w.png) center center no-repeat;
	    font-size: 0;
	    line-height: 0;
	    z-index: 3;
	}
	a, input, button, select {
	    outline: none;
	}
	img, input, select, textarea, button {
	    vertical-align: middle;
	}
	.layerpopup.g20.center .pop_contents {
	    padding: 50px;
	    box-sizing: border-box;
	    position: relative;
	}
	.layerpopup.g20 .pop_contents {
	    padding: 0;
	    position: static;
	}
	.layerpopup.center .pop_contents {
	    padding: 0;
	}
	.layerpopup .pop_contents {
	    position: relative;
	    padding: 30px 30px 50px;
	}
	
	.layerpopup.g20.semi_video .img_box {
	    padding: 0;
	}
	.layerpopup.semi_video .img_box {
	    padding: 50px;
	}
	
	.layerpopup.semi_video .img_box .video {
	    position: relative;
	    width: 100%;
	    padding-bottom: 56%;
	    background-color: #000;
	}
	
	.layerpopup.semi_video .img_box .video iframe {
	    display: block;
	    position: absolute;
	    left: 0;
	    top: 0;
	    width: 100%;
	    height: 100%;
	}
	.layerpopup.open #backbg {
	    opacity: 1;
	}
	.layerpopup #backbg {
	    content: "";
	    display: block;
	    position: fixed;
	    top: 0;
	    left: -100%;
	    width: 200%;
	    height: 100%;
	    background-color: rgba(0, 0, 0, 0.5);
	    opacity: 0;
	    z-index: 1;
	    transition: opacity 0.6s;
	    -webkit-transition: opacity 0.6s;
	}
	#backbg {
	    opacity: 0.7;
	}
	#backbg, #clickbg {
	    position: absolute;
	    left: 0;
	    top: 0;
	    width: 100%;
	    height: 100%;
	    background-color: #000;
	    z-index: 9;
	}
</style>
<script>

	function search(){
		$("#hidden_pg").val("1");
		$("#searchForm").submit();
	}
	
	function pageFunction(pageNo){
		$("#hidden_pg").val(pageNo);
		$("#searchForm").submit();
	}
	
	var setCount = 0;
	function setReply(){
		if( setCount == 0 ) {
			
			setCount ++;
			
			$.ajax({
				
				type : 'POST',
				url : "./setReply",
				processData: false,
		        contentType: false,
		        dataType : 'json',
				success : function(data){
					location.href = './replyList';
				}, error : function(request, status, error){
					console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		        	alert("일시적인 오류가 발생했습니다.\n다시 시도해주세요.");
		        	return false;
				}
				
			});
			
		} else{
			alert("처리중입니다.\n잠시만 기다려주세요.");
			return false;
		}
	}
	function movInfo(mov_id, i){
		var target = $("#g20Video");
		
		target.load('/social/movView?mov_id='+mov_id, function(){
// 			$('#g20Video').find('p').remove();
// 			$(i).attr('data-url-utb', mov_id);
			layerPopupOpen('#g20Video', i);
		});
		
	}
	
	function layerPopupOpen(e, i) {
		var pop = $(e);

		pop.css("display","block");
		pop.append('<div id="backbg"></div>');
		pop.find(".pop_wrap").scrollTop(0);
		setTimeout(function () {
			pop.addClass("open");
		}, 100);

		var scroll = $(document).scrollTop();
	    $('body').attr("data-scroll",scroll);
	    $('html,body').scrollTop(0);

		if( pop.hasClass("center") == false ){
			$('body').css({"height":"100%","overflow-y":"hidden"});
			$('#ui_wrap').css({"height":"100vh","overflow-y":"hidden"});
		} else {
			if( $(window).width() >= 720 ){
				$('body').removeAttr("style");
				$('#ui_wrap').removeAttr("style");
			}
		}
		// youtube
		if( pop.hasClass('g20') && pop.hasClass('video') ){
			var link = $(i).attr('data-url-utb');
			$(e).find('iframe').attr('src', 'https://www.youtube.com/embed/'+link+'?rel=0&autoplay=1&mute=1');
		}

		$('#backbg').click(function(){
		    layerPopupClose(e);
		});
	}
	
	function layerPopupClose(e) {
		var pop = $(e);

		pop.removeClass("open");
		setTimeout(function () {
			pop.removeAttr("style");
			$('#backbg').remove();
		}, 600);

		if ($(".layerpopup.open").length <= 0){
			$('body').removeAttr("style");
			$('#ui_wrap').removeAttr("style");
			var scroll = $('body').attr("data-scroll");
			$('html,body').scrollTop(scroll);
			$('body').removeAttr("data-scroll");
		} else {
			$('body').css({"height":"100%","overflow-y":"hidden"});
			$('#ui_wrap').css({"height":"100vh","overflow-y":"hidden"});
		}
		if(e == '#g20Speaker'){
			g20fn.spekSlide(0);
		}
		// youtube
		if( pop.hasClass('g20') && pop.hasClass('video') ){
			$(e).find('iframe').removeAttr('src');
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
	<div id="main-wrapper" data-theme="light" data-layout="vertical" data-navbarbg="skin6" data-sideb artype="full" data-sidebar-position="fixed" data-header-position="fixed" data-boxed-layout="full">
		<div class="container">
			<div class="page-breadcrumb row" style="margin: 2em;">
			</div>
			
			<div class="row">
				<form action="/social/replyList" method="get" id="searchForm" class="form-inline">
				<input type="hidden" id="hidden_pp" name="pp" value="<c:out value="${paramdata.pp }"/>">
				<input type="hidden" id="hidden_pg" name="pg" value="<c:out value="${paramdata.pg }"/>">
				<div class="row">
					<div class="col-md-1">
						<a id="write-btn" onclick="setReply()" class="btn">
							<i class="bi bi-power" data-feather="search" id="searchIcon"></i>
						</a>
					</div>
					<div class="col-md-7"></div>
					<div class="col-md-4">
<!-- 						<div class="input-group"> -->
<!-- 							<select class="form-select bg-white border-1" name="searchCondition" style="width:10%;"> -->
<%-- 								<option value="all" <c:if test="${paramdata.searchCondition eq 'all' }">selected</c:if>>제목+내용</option> --%>
<%-- 								<option value="title" <c:if test="${paramdata.searchCondition eq 'title' }">selected</c:if>>제목</option> --%>
<%-- 								<option value="content" <c:if test="${paramdata.searchCondition eq 'content' }">selected</c:if>>내용</option> --%>
<!-- 							</select> -->
<%-- 							<input class="form-control border-1 bg-white" type="text" placeholder="" aria-label="Search" name="searchValue" value="${paramdata.searchValue }" style="width:35%"> --%>
<!-- 							<button type="button" class="btn" onclick="search();"><i class="bi bi-search" data-feather="search" id="searchIcon"></i></button> -->
<!-- 						</div> -->
					</div>
				</div>
				</form>
			</div>
			<div class="container" style="margin: 1em 0 1em 0;">
				<div class="page_card-sort">
					<ul class="load_box">
						<c:forEach items="${movList }" var="row">
							<li>
								<a href="javascript:;" onclick="movInfo('${row.mov_id}', this);" data-url-utb="${row.mov_id }">
									<img src="https://img.youtube.com/vi/${row.mov_id }/mqdefault.jpg" alt="썸네일 이미지">
									<div class="txt_guard">
										<em>${CubeUtils.cutString(row.mov_cn, 20, '..') }</em>
										<p>${row.mov_nm }</p>
										<span>${fn:replace(fn:substring(row.reg_dt,0,10),'-','.') }</span>
									</div>
								</a>
							</li>
						</c:forEach> 
					</ul>
				</div>
			</div>
<!-- 			<div class="container" style="margin: 1em 0 1em 0;"> -->
<!-- 				<div class="row"> -->
<!-- 					<table class="table table-hover"> -->
<!-- 						<thead class="thead-light"> -->
<!-- 							<tr> -->
<!-- 								<th scope="col" style="width: 7%">#</th> -->
<!-- 								<th scope="col" style="width: 40%">댓글내용</th> -->
<!-- 								<th scope="col" style="width: 10%">닉네임</th> -->
<!-- 								<th scope="col" style="width: 13%">등록일</th> -->
<!-- 							</tr> -->
<!-- 						</thead> -->
<!-- 						<tbody> -->
<%-- 							<c:if test="${ !empty thisList }"> --%>
<%-- 							<c:forEach items="${thisList }" var="row"> --%>
<!-- 								<tr> -->
<%-- 									<td scope="row">${row.numrow }</td> --%>
<%-- 									<td>${row.re_cn }</td> --%>
<%-- 									<td>${row.author_nm }</td> --%>
<%-- 									<td>${row.reg_dt }</td> --%>
<!-- 								</tr> -->
<%-- 							</c:forEach> --%>
<%-- 							</c:if> --%>
<%-- 							<c:if test="${empty thisList }"> --%>
<!-- 								<tr> -->
<!-- 									<td colspan="4">검색 결과가 없습니다.</td> -->
<!-- 								</tr> -->
<%-- 							</c:if> --%>
<!-- 						</tbody> -->
<!-- 					</table> -->
					<div class="text-center paging" style="width: 100%; margin-top: 1em;">
						<nav aria-label="Page navigation example">
								${paging }
						</nav>
					</div>
<!--  				</div> -->
<!-- 			</div> -->
		</div>
	</div>
	<div style="margin:12em;">
	</div>
	
	<div class="layerpopup center semi_video g20 video" id="g20Video">
		<div class="pop_wrap">
<!-- 			<div class="pop_tit"> -->
<!-- 				<button class="btn_x" type="button" onclick="layerPopupClose('#g20Video');">닫기</button> -->
<!-- 			</div> -->
			<div class="pop_contents">
				<div class="vdo_wrap">
					<div class="img_box">
						<div class="video"><iframe src="https://www.youtube.com/embed/sLaZ7X1ECUQ?rel=0&amp;showinfo=0" frameborder="0" allowfullscreen="true" allowtransparency="true"></iframe></div>
					</div>
					<p></p>
				</div>
			</div>
		</div>
	</div>
	<!-- footer -->
	<%@ include file="../common/footer.jsp" %>
	<script src="/resources/assets/js/board.js"></script>
	<script>
		var url = window.location.pathname;
		$('.navbar').find('a').each(function(){
			$(this).toggleClass('active', $(this).attr('href') == url);
		});
		if(url == '/board/boardSearch'){
			$('.boardList').addClass('active');
		}
	</script>
</body>
</html>