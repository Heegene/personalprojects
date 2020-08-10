<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/layout/header.jsp" %>
<!-- 데이터바인딩과 관련된 태그를 사용하도록 form 태그 라이브러리 추가 -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Board</title>

<!--  CK editor 사용을 위한 스크립트 -->
<script src="https://cdn.ckeditor.com/ckeditor5/21.0.0/classic/ckeditor.js"></script>

<script type="text/javascript">

	$(document).on('click', '#btnSave', function(e) {
		e.preventDefault();
		$("#form").submit();
	});
	
	$(document).on('click', '#btnList', function(e) {
		e.preventDefault();
		location.href="${pageContext.request.contextPath}/board/getBoardList";
	});
	
	$(document).ready(function() {
		var mode = '<c:out value="${mode}"/>';
		if (mode == 'edit') {
			// 입력 폼 세팅
			$("#reg_id").prop('readonly', true);
			$("input:hidden[name='bid']").val(<c:out value="${boardContent.bid}" />);
			$("input:hidden[name='mode']").val('<c:out value="${mode}" />');
			$("#reg_id").val('<c:out value="${boardContent.reg_id}" />');
			$("#title").val('<c:out value="${boardContent.title}" />');
			$("#content").val('<c:out value="${boardContent.content}" />');
			$("#tag").val('<c:out value="${boardContent.tag}" />');
			
		}
		
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
			<!-- 기존의 html 태그인 form> 태그를 'form:form' 으로 수정 -->
			<form:form name="form" id="form" role="form" modelAttribute="boardDto" method="post" action="${pageContext.request.contextPath}/board/saveBoard">
				<form:hidden path="bid" />
				<input type="hidden" name="mode" />
				<!-- form:form 태그 안에 있는 modelattribute boatdDto는 controller로부터 전달받는 boardDto임
				bid, mode는 보여지지는 않지만 데이터를 서버로 전송할 때 필요한 부분
				mode는 boardDto가 가지고 있지 않은 property 이므로 일반 html 태그인 input을 사용하여 전송하여야 함 -->
				
				
				<div class="mb-3">
					<label for="title"> 제목 </label>		
					<!-- 제목 부분도 form:input으로 태그 변경 -->		
					<form:input path="title" class="form-control" name="title" id="title" placeholder="제목을 입력해 주세요"/>
				</div>
				
				<div class="mb-3">
					<label for="reg_id"> 작성자 </label>
					<form:input path="reg_id" class="form-control" name="reg_id" id="reg_id" placeholder="이름을 입력해 주세요"/>
				</div>
				
				<div class="mb-3">
					<label for="content"> 내용 </label>
					<form:textarea path="content" class="form-control" rows="5" name="content" id="content" placeholder="내용을 입력해 주세요" />					
				</div>
				
				<div class="mb-3">
					<label for="tag"> 태그 </label>
					<form:input path="tag" class="form-control" name="tag" id="tag" placeholder="태그를 입력해 주세요"/>
				</div>
				
				
			</form:form>
			
			<div>
				<button type="button" class="btn btn-sm btn-primary" id="btnSave">저장</button>
				<button type="button" class="btn btn-sm btn-primary" id="btnList">목록으로</button>
			
			</div>
					
		</div>
	
	</article>

<script src="${pageContext.request.contextPath}/resources/common/js/ckeditor.js"></script>
</body>
</html>