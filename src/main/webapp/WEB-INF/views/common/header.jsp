<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
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
</head>
<body>
<header id="header" class="fixed-top">
    <div class="container-fluid d-flex justify-content-between align-items-center">

      <h1 class="logo me-auto me-lg-0"><a href="/">INFE</a></h1>
      <!-- Uncomment below if you prefer to use an image logo -->
      <!-- <a href="index.html" class="logo"><img src="assets/img/logo.png" alt="" class="img-fluid"></a>-->

      <nav id="navbar" class="navbar order-last order-lg-0">
        <ul id="navbar-ul">
          <li class=""><a class="" href="/">Home</a></li>
          <li class=""><a class="" href="about.html">About</a></li>
          <li class=""><a class="" href="/social/replyList">Video</a></li>
          <li class=""><a class="boardList" href="/board/boardList">Board</a></li>
          <li class=""><a class="" href="portfolio.html">Portfolio</a></li>
          <li class=""><a class="" href="contact.html">Contact</a></li>
        </ul>
        <i class="bi bi-list mobile-nav-toggle"></i>
      </nav><!-- .navbar -->

      <div class="header-social-links">
      	<c:if test="${empty loginUser }">
	        <a href="/loginView" class="twitter" title="login"><i class="bi bi-box-arrow-in-right" style="font-size: 1.5em;"></i></a>
      	</c:if>
      	<c:if test="${!empty loginUser }">
        	<a href="/mypage" class="linkedin" title="mypage"><i class="bi bi-person-circle" style="font-size: 1.5em;"></i></a>
        	<a href="/logout" class="facebook" title="logout"><i class="bi bi-box-arrow-right" style="font-size: 1.5em;"></i></a>
        </c:if>
      </div>

    </div>

  </header><!-- End Header -->
</body>
</html>