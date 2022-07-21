<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- <div class="popup_left-slide move_movie_view open"> -->
<head>
  <!-- Favicons -->
<!--   <link href="/resources/assets/img/favicon.png" rel="icon"> -->
  <link href="/resources/assets/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- JS -->
  <script src="/resources/assets/js/jquery.min.js"></script>
  <script src="/resources/assets/js/jquery-migrate-3.0.1.min.js"></script>
  
  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Jost:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="/resources/assets/vendor/aos/aos.css" rel="stylesheet">
  <link href="/resources/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="/resources/assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="/resources/assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
  <link href="/resources/assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
<!--   <link href="/resources/assets/vendor/remixicon/remixicon.css" rel="stylesheet"> -->
  <link href="/resources/assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

  <!-- Template Main CSS File -->
  <link href="/resources/assets/css/style.css" rel="stylesheet">
  
  
  <!-- Vendor JS Files -->
  <script src="/resources/assets/vendor/aos/aos.js"></script>
  <script src="/resources/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="/resources/assets/vendor/glightbox/js/glightbox.min.js"></script>
  <script src="/resources/assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
<!--   <script src="/resources/assets/vendor/swiper/swiper-bundle.min.js"></script> -->
  <script src="/resources/assets/vendor/waypoints/noframework.waypoints.js"></script>
  <script src="/resources/assets/vendor/php-email-form/validate.js"></script>
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
</head>
<!-- 	<div class="layerpopup center semi_video g20 video" id="g20Video"> -->
		<div class="pop_wrap">
<!-- 			<div class="pop_tit"> -->
<!-- 				<button class="btn_x" type="button" onclick="layerPopupClose('#g20Video');">닫기</button> -->
<!-- 			</div> -->
			<div class="pop_contents">
				<div class="vdo_wrap">
					<div class="img_box">
						<div class="video"><iframe src="https://www.youtube.com/embed/sLaZ7X1ECUQ?rel=0&amp;showinfo=0" frameborder="0" allowfullscreen="true" allowtransparency="true"></iframe></div>
					</div>
					<c:forEach items="${thisList }" var="row">
						<em>${row.author_nm}</em>
						<p>${row.re_cn }</p>
					</c:forEach>
				</div>
			</div>
		</div>
<!-- 	</div> -->
