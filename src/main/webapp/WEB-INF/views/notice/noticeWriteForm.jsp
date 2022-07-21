<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="../resources/ckeditor/ckeditor.js"></script>

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
					<div class="col-7 align-self-center">
						<h1 class="page-title text-truncate text-dark font-weight-medium mb-1">Board</h1>
					</div>
				</div>
			</div>

			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="card">
							<div class="card-body">
								<form class="" role="form" action="/notice/noticeWrite" method="post" enctype="multipart/form-data">
									<table class="table-responsive col-md-12 ">
										<tr class="row" >
											<td class="col-md-2 text-center">제목</td>
											<td class="col-md-10" ><input type="text" class="form-control" name="noTitle"></td>
										<tr><td>&nbsp;</td></tr>
										<tr class="row">
											<td class="col-md-2 text-center">내용</td>
											<td class="col-md-10">
												<textarea class="form-control" id="p_content" name="noContent"></textarea>
											</td>
										</tr>
										<tr><td>&nbsp;</td></tr>
										<tr class="row">
											<td class="col-md-2 text-center">첨부파일</td>
											<td>&nbsp;&nbsp;&nbsp;</td>
											<td class="col-md-9">
												<!-- <input type="file" class="custom-file-input" id="customFile" name="noFileName">
          										<label class="custom-file-label" for="customFile">파일선택</label> -->
          										<button type="button" id='button-add-file' class=""><i class="bi bi-file-earmark-plus"></i>  파일 추가</button>
												<div id="my-form"></div>
											</td>
										</tr>
										<tr><td>&nbsp;</td></tr>
										<tr class="row">
											<td class="col-md-12 text-center">
												<input type="reset" class="btn" value="취소"  onclick="history.back()">
												<input type="submit" class="btn" value="등록">
											</td>
										</tr>
									</table>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div style="margin:5em;">
	</div>
	<!-- footer -->
	<%@ include file="../common/footer.jsp" %>
	
	<script src="/resources/assets/js/notice.js"></script>
	
	<script>
		CKEDITOR.replace('p_content'
            , {height: 500, width: 800
             });
		
		$('#button-add-file').click(addFileForm);
		$(document).on('click', '.button-delete-file', function(event) {
			if($("input[name=viewCheck]").val() == "m") {
				var delFileNo = $(this).next().val();
				delFList.push(delFileNo);
			}
			$(this).parent().remove();
		});
		
		var count = 0;
		function addFileForm() {
			var html = "<div id='item_"+ count +"'>";
			html += "<input id='input" + count + "' type='file' name='fList'>";
			html += "<a class='button-delete-file btn'><i class='fas fa-minus text-primary' style='cursor:pointer'></i></a></div>";
			count++;
			$("#my-form").append(html);
		}
	</script>
</body>
</html>