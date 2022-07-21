<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board</title>
<style>
	#top{
		padding: 15px 0;
		background: #37517e;
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
	<div id="main-wrapper" data-theme="light" data-layout="vertical"
		data-navbarbg="skin6" data-sidebartype="full"
		data-sidebar-position="fixed" data-header-position="fixed"
		data-boxed-layout="full">
		<div class="container">
			<div class="page-breadcrumb" style="margin: 2em;">
				<div class="row">
					<div class="col-lg-4 align-self-center">
						<h1 class="page-title text-truncate text-dark font-weight-medium mb-1">Board</h1>
					</div>
				</div>
			</div>
			<div class="col-lg-12 align-self-center" style="margin-top: 1em;">
				<div class="float-right">
						<div class="btn-group">
							<button
								onclick="location.href='/notice/noticeModifyView?noNo=${notice.noNo}'"
								class="btn btn-secondary">수정</button>
							<button
								onclick="location.href='/notice/noticeDelete?noNo=${notice.noNo}'"
								class="btn btn-secondary">삭제</button>
						</div>
					<button onclick="location.href='/notice/noticeList'"
						class="btn">목록</button>
				</div>
			</div>
			<div class="container-fluid">
				<div class="row">
					<div class="col-12">
						<div class="card">
							<div class="card-body ">
								<h4 class="card-title">
									${ notice.noTitle }
									<input type="hidden" name="noNo">
								</h4>
								<div class="row">
									<h6 class="card-subtitle col-12" style="text-align: right">
									조회수 ${notice.noCount} &nbsp;&nbsp;&nbsp; 작성일 ${ notice.noInsertDate }</h6>
								</div>
							</div>
							<div class="card-body">
								<c:if test="${ !empty notice.noContent }">
									<p>${ notice.noContent }</p>
								</c:if>
							</div>
                            <div class="card-body file-box">
                            	<c:if test="${ !empty notice.noFiles }">
	                            	<c:forEach var="file" items="${notice.noFiles }" varStatus="i">
	                            		<div>
	                            			<b>첨부파일${ i.count } : </b>
	                            			<input type="hidden" value="file.fiNo">
	                            			<c:url var="fileDownload" value="/file/download">
												<c:param name="fiNo" value="${ file.fiNo }"></c:param>
											</c:url>
	                            			<a class="text-muted" href="${ fileDownload }" onclick="return confirm('파일을 다운로드하시겠습니까?');">${ file.fiRealFileName }</a>
	                            		</div> 
	                            	</c:forEach>
                            	</c:if>
                            </div>
						</div>
					</div>
				</div>
			</div>
			
		</div>
	</div>
	<div style="margin:30em;">
	</div>
	<!-- footer -->
	<%@ include file="../common/footer.jsp" %>
	<script src="/resources/assets/js/notice.js"></script>
</body>
</html>