<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/layout/header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Board</title>



<script type="text/javascript">
	$(document).on('click', '#btnSave', function(e) {
		e.preventDefault();
		
		$("#form").submit();
	});
	
	$(document).on('click', '#btnList', function(e) {
		e.preventDefault();
		location.href="${pageContext.request.contextPath}/board/getBoardList";
	});

</script>

<style type="text/css">
	body {
		padding-top:70px;
		padding-botton:30px;
	}

</style>
</head>
<body>
	<article>
		<div class="container" role="main">
			<h2> 글쓰기</h2>
			<form name="form" id="form" role="form" method="post" action="${pageContext.request.contextPath}/board/saveBoard">
				<div class="mb-3">
					<label for="title"> 제목 </label>				
					<input type="text" class="form-control" name="title" id="title" placeholder="제목을 입력해 주세요">
				</div>
				
				<div class="mb-3">
					<label for="reg_id"> 작성자 </label>
					<input type="text" class="form-control" name="reg_id" id="reg_id" placeholder="이름을 입력해 주세요">
				</div>
				
				<div class="mb-3">
					<label for="content"> 내용 </label>
					<textarea class="form-control" rows="5" name="content" id="content" placeholder="내용을 입력해 주세요"></textarea>
				</div>
				
				<div class="mb-3">
					<label for="tag"> 태그 </label>
					<input type="text" class="form-control" name="tag" id="tag" placeholder="태그를 입력해 주세요">
				</div>
				
				
			</form>
			
			<div>
				<button type="button" class="btn btn-sm btn-primary" id="btnSave">저장</button>
				<button type="button" class="btn btn-sm btn-primary" id="btnList">목록으로</button>
			
			</div>
					
		</div>
	
	</article>


</body>
</html>