<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spring Blog</title>

<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>

<!--  Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">

<style type="text/css">
	body {
		padding-top:70px;
		padding-bottom:30px;
	
	}

</style>

<script type="text/javascript">
	$(document).on('click', '#btnWriteForm', function(e) {
		e.preventDefault();
		location.href="${pageContext.request.contextPath}/board/boardForm";
	});
	// $는 jquery를 시작하는 명령어 - $(DOM요소) 이렇게 해서 각 요소에 접근
	// e.preventDefault는 버튼 고유의 기능을 막는 명령어()
</script>
</head>
<body>

	<h4>게시글 목록</h4>
	<hr>
	<article>
	<div class="container">
	<div class="table-respnosive">
	<table class="table table-striped table-sm">
		<colgroup>
			<col style="width: 7%;" />
			<col style="width: auto;" />
			<col style="width: 15%;" />
			<col style="width: 10%;" />
			<col style="width: 15%;" />
		</colgroup>

		<thead>
			<th>글번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>조회수</th>
			<th>작성일</th>
		</thead>

		<tbody>
			<c:choose>
				<c:when test="${empty boardList}">
					<tr>
						<td colspan="5" align="center">데이터가 없습니다.</td>
					</tr>
				</c:when>
				<c:when test="${!empty boardList }">
					<c:forEach var="list" items="${boardList}">
						<tr>
							<td><c:out value="${list.bid}" /></td>
							<td><c:out value="${list.title}" /></td>
							<td><c:out value="${list.reg_id}" /></td>
							<td><c:out value="${list.view_cnt}" /></td>
							<td><c:out value="${list.reg_dt}" /></td>
						</tr>
					</c:forEach>
				</c:when>

			</c:choose>
		</tbody>
	</table>
	</div>
	
	<div>
		<button type="button" class="btn btn-secondary my-2" id="btnWriteForm">글작성</button>
	</div>
	
	
	</div>
	</article>
</body>
</html>