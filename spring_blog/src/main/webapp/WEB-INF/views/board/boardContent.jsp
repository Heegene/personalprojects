<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>

<%@ include file="/WEB-INF/views/layout/header.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 조회</title>

<script type="text/javascript">

		$(document).on('click', '#btnList', function(e) {
			e.preventDefault();
			location.href="${pageContext.request.contextPath}/board/getBoardList";
		});
		
		$(document).on('click', '#btnUpdate', function() {
			var url = "${pageContext.request.contextPath}/board/editForm";
			url = url + "?bid=" + ${boardContent.bid} + "&mode=edit";
			// 글번호를 인자로 받고, mode 는 입력 폼을 신규 입력 외에 수정에서도 사용할 것이므로 필요한 인자
			// 신규 입력과 수정과의 차이를 둠 
			// (bid의 유무만으로도 판단할 수는 있지만 더 명확한 구분을 위해 따로 구분값을 둠 )
			location.href = url;
		});
		
		$(document).on('click', '#btnDelete', function() {
			var url = "${pageContext.request.contextPath}/board/deleteBoard";
			url = url + "?bid=" + ${boardContent.bid};
			location.href = url;
		});
		

</script>
</head>
<body>
	<article>
		<div class="container" role="main">
			<h2> 게시글 조회</h2>
			
			<div class="bg-white rounded shadow-sm">
				<div class="board_title">
					<c:out value="${boardContent.title}"/>
				</div>
				<div class="board_info_box">
					<span class="board_author"><c:out value="${boardContent.reg_id}" /></span>, 
					<span class="board_date"><c:out value="${boardContent.reg_dt}" /></span>
				</div>
				<div class="board_content">
					${boardContent.content}
				</div>
				<div class="board_tag"> TAG : <c:out value="${boardContent.tag}" /></div>
			</div>
			
			<div style="margin-top:20px">
				<button type="button" class="btn btn-sm btn-primary" id="btnUpdate"> 수정</button>
				<button type="button" class="btn btn-sm btn-primary" id="btnDelete"> 삭제</button>
				<button type="button" class="btn btn-sm btn-primary" id="btnList"> 목록으로</button>
			</div>
		</div>
	</article>

</body>
</html>