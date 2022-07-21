<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
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
	.form-control:focus{
		outline:none !important;
	}
	button:focus{
		outline:none;
	}
	a:focus{
		outline:none;
	}
</style>
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
				<form action="/board/boardSearch" method="get" id="searchForm" class="form-inline">
				<div class="row">
					<div class="col-md-1">
						<c:if test="${!empty loginUser }">
							<a id="write-btn" onclick="location.href='/board/boardWriteView'" class="btn">
								<i class="bi bi-pencil-square" data-feather="search" id="searchIcon"></i>
							</a>
						</c:if>
					</div>
					<div class="col-md-7"></div>
					<div class="col-md-4">
						<div class="input-group">
							<select class="form-select bg-white border-1" name="searchCondition" style="width:10%;">
								<option value="all" <c:if test="${search.searchCondition eq 'all' }">selected</c:if>>제목+내용</option>
								<option value="title" <c:if test="${search.searchCondition eq 'title' }">selected</c:if>>제목</option>
								<option value="content" <c:if test="${search.searchCondition eq 'content' }">selected</c:if>>내용</option>
							</select>
							<input class="form-control border-1 bg-white" type="text" placeholder="" aria-label="Search" name="searchValue" value="${search.searchValue }" style="width:35%">
							<button type="submit" class="btn"><i class="bi bi-search" data-feather="search" id="searchIcon"></i></button>
						</div>
					</div>
				</div>
				</form>
			</div>
			
			<div class="container" style="margin: 1em 0 1em 0;">
				<div class="row">
					<table class="table table-hover">
						<thead class="thead-light">
							<tr>
								<th scope="col" style="width: 7%">#</th>
								<th scope="col" style="width: 40%">제목</th>
								<th scope="col" style="width: 10%">작성자</th>
								<th scope="col" style="width: 13%">작성일</th>
								<th scope="col" style="width: 10%">조회수</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${ !empty thisList }">
							<c:forEach items="${thisList }" var="board">
								<tr>
									<td scope="row">
										<input type="hidden" name="boNo" value="${board.bo_no }">
										${board.rowNum }
									</td>
									<td>
										<c:url var="bDetail" value="/board/boardDetail">
											<c:param name="boNo" value="${board.bo_no }"></c:param>
										</c:url>
										<a href="${bDetail }" class="title">${board.title }</a>
										<span style="color: red;">
											<c:if test="${board.insertDate >= nowDay }">
												<sup><b>NEW</b></sup>
											</c:if>
										</span>
									</td>
									<td>${board.name }</td>
									<td>${board.insertDate }</td>
									<td>${board.count }</td>
								</tr>
							</c:forEach>
							</c:if>
							<c:if test="${empty bList }">
								
							</c:if>
						</tbody>
					</table>
					<div class="text-center paging" style="width: 100%; margin-top: 1em;">
						<nav aria-label="Page navigation example">
								${paging }
						</nav>
					</div>
					<!-- 페이징 -->
					<c:if test="${ !empty bList }">
 					<div class="text-center paging" style="width: 100%; margin-top: 1em;">	
						<nav aria-label="Page navigation example">
							<c:if test="${ search.searchValue eq null}">
								<ul class="pagination" style="justify-content: center;">
									<c:url var="before" value="/board/boardList2">
										<c:param name="page" value="${pi.currentPage - 1 }"></c:param>
									</c:url>
									<c:if test="${pi.currentPage <= 1 }">
										<li class="page-item disabled">
											<a href="#" class="page-link" aria-label="Previous"> 
												<span aria-hidden="true">&laquo;</span> 
												<span class="sr-only"></span>
											</a>
										</li>
									</c:if>
									<c:if test="${pi.currentPage > 1 }">
										<li class="page-item">
											<a href="${before }" class="page-link" aria-label="Previous"> 
												<span aria-hidden="true">&laquo;</span> 
												<span class="sr-only"></span>
											</a>
										</li>
									</c:if>

									<c:forEach var="p" begin="${pi.startPage }" end="${pi.endPage }">
										<c:url var="pagination" value="/board/boardList2">
											<c:param name="page" value="${p }"></c:param>
										</c:url>
										<c:if test="${p eq pi.currentPage }">
											<li class="page-item active">
												<a href="${pagination }" class="page-link" id="currentPage">${p }</a>
											</li>
										</c:if>
										<c:if test="${p ne pi.currentPage }">
											<li class="page-item">
												<a href="${pagination }" class="page-link">${p }</a>
											</li>
										</c:if>
									</c:forEach>

									<c:url var="after" value="/board/boardList2">
										<c:param name="page" value="${pi.currentPage + 1 }"></c:param>
									</c:url>
									<c:if test="${pi.currentPage >= pi.maxPage }">
										<li class="page-item disabled">
											<a href="#" class="page-link" aria-label="Next"> 
												<span aria-hidden="true">&raquo;</span> 
												<span class="sr-only"></span>
											</a>
										</li>
									</c:if>
									<c:if test="${pi.currentPage < pi.maxPage }">
										<li class="page-item">
											<a href="${after }" class="page-link" aria-label="Next"> 
												<span aria-hidden="true">&raquo;</span> 
												<span class="sr-only"></span>
											</a>
										</li>
									</c:if>
								</ul>
							</c:if>

							<c:if test="${search.searchValue ne null }">
								<ul class="pagination" style="justify-content: center;">
									<c:if test="${pi.currentPage <= 1 }">
										<li class="page-item disabled">
											<a href="#" class="page-link" aria-label="Previous"> 
												<span aria-hidden="true"><<</span> 
												<span class="sr-only"></span>
											</a>
										</li>
									</c:if>
									<c:if test="${pi.currentPage > 1 }">
										<li class="page-item">
											<a href="/board/boardSearch?searchValue=${search.searchValue }&searchCondition=${search.searchCondition}&page=${pi.currentPage -1}" class="page-link" aria-label="Previous"> 
												<span aria-hidden="true"><<</span> 
												<span class="sr-only"></span>
											</a>
										</li>
									</c:if>

									<c:forEach var="p" begin="${pi.startPage }" end="${pi.endPage }">
										<c:if test="${p eq pi.currentPage }">
											<li class="page-item active">
												<a href="${pagination }" class="page-link" id="currentPage">${p }</a>
											</li>
										</c:if>
										<c:if test="${p ne pi.currentPage }">
											<li class="page-item">
												<a href="/board/boardSearch?searchValue=${search.searchValue }&searchCondition=${search.searchCondition}&page=${p}" class="page-link">${p }</a>
											</li>
										</c:if>
									</c:forEach>

									<c:if test="${pi.currentPage >= pi.maxPage }">
										<li class="page-item disabled">
											<a href="#" class="page-link" aria-label="Next"> 
												<span aria-hidden="true">>></span> 
												<span class="sr-only"></span>
											</a>
										</li>
									</c:if>
									<c:if test="${pi.currentPage < pi.maxPage }">
										<li class="page-item">
											<a href="/board/boardSearch?searchValue=${search.searchValue }&searchCondition=${search.searchCondition}&page=${pi.currentPage +1}" class="page-link" aria-label="Next"> 
												<span aria-hidden="true">>></span> 
												<span class="sr-only"></span>
											</a>
										</li>
									</c:if>
								</ul>
							</c:if>
						</nav>
					</div>
					</c:if>
 				</div>
<!-- 				<div class="col-md-1 float-right"> -->
<!-- 					<button id="write-btn" onclick="location.href='/board/boardWriteView'" class="btn"> -->
<!-- 						<i class="bi bi-pencil-square" data-feather="search" id="searchIcon"></i> -->
<!-- 					</button> -->
<!-- 				</div> -->
			</div>
		</div>
	</div>
	<div style="margin:12em;">
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
			$('.boardList2').addClass('active');
		}
	</script>
</body>
</html>