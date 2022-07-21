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
<script>

	function search(){
		$("#hidden_pg").val("1");
		$("#searchForm").submit();
	}
	
	function pageFunction(pageNo){
		$("#hidden_pg").val(pageNo);
		$("#searchForm").submit();
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
				<form action="/board/boardList" method="get" id="searchForm" class="form-inline">
				<input type="hidden" id="hidden_pp" name="pp" value="<c:out value="${paramdata.pp }"/>">
				<input type="hidden" id="hidden_pg" name="pg" value="<c:out value="${paramdata.pg }"/>">
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
								<option value="all" <c:if test="${paramdata.searchCondition eq 'all' }">selected</c:if>>제목+내용</option>
								<option value="title" <c:if test="${paramdata.searchCondition eq 'title' }">selected</c:if>>제목</option>
								<option value="content" <c:if test="${paramdata.searchCondition eq 'content' }">selected</c:if>>내용</option>
							</select>
							<input class="form-control border-1 bg-white" type="text" placeholder="" aria-label="Search" name="searchValue" value="${paramdata.searchValue }" style="width:35%">
							<button type="button" class="btn" onclick="search();"><i class="bi bi-search" data-feather="search" id="searchIcon"></i></button>
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
											<c:if test="${board.rec eq 'NEW'}">
												<sup><b>NEW</b></sup>
											</c:if>
										</span>
									</td>
									<td>${board.name }</td>
									<td>${board.reg_dt }</td>
									<td>${board.count }</td>
								</tr>
							</c:forEach>
							</c:if>
							<c:if test="${empty thisList }">
								<tr>
									<td colspan="5">검색 결과가 없습니다.</td>
								</tr>
							</c:if>
						</tbody>
					</table>
					<div class="text-center paging" style="width: 100%; margin-top: 1em;">
						<nav aria-label="Page navigation example">
								${paging }
						</nav>
					</div>
 				</div>
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
			$('.boardList').addClass('active');
		}
	</script>
</body>
</html>