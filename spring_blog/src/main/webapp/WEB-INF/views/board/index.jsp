<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/layout/header.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spring Blog</title>


<script type="text/javascript">
	$(document).on('click', '#btnWriteForm', function(e) {
		e.preventDefault();
		location.href = "${pageContext.request.contextPath}/board/boardForm";
	});
	// $는 jquery를 시작하는 명령어 - $(DOM요소) 이렇게 해서 각 요소에 접근
	// e.preventDefault는 버튼 고유의 기능을 막는 명령어()
	
	function fn_contentView(bid) {
		// bid를 인자로 받아서 해당 bid를 가진 글번호 조회로 이동 
		var url = "${pageContext.request.contextPath}/board/getBoardContent";
		url = url + "?bid=" + bid;
		location.href = url;
	}
	
</script>
</head>
<body>

	<article>
	<div class="container">
	<h2>게시글 목록</h2>
		<div class="table-responsive">
			<table class="table table-striped table-sm">
				<colgroup>
					<col style="width: 7%;" />
					<col style="width: auto;" />
					<col style="width: 15%;" />
					<col style="width: 10%;" />
					<col style="width: 15%;" />
				</colgroup>

				<thead>
					<th>No.</th>
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
									<!-- 제목 클릭 시 글 조회 function 호출  -->
									<td><a href="#" onClick="fn_contentView(<c:out value="${list.bid}"/>)"><c:out value="${list.title}" /></a></td>
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