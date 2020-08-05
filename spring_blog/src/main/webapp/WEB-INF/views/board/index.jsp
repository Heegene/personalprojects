<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<h2> 게시글 목록 </h2>
<table>
	<colgroup>
		<col style="width:5%;"/>
		<col style="width:auto;" />
		<col style="width:15%;" />
		<col style="width:10%;" />
		<col style="width:10%;" />
	</colgroup>
	
	<thead>
		<th> 글번호 </th>
		<th> 제목 </th>
		<th> 작성자 </th>
		<th> 조회수 </th>
		<th> 작성일 </th>
	</thead>
	
	<tbody>
		<c:choose>
			<c:when test="${empty boardList}">
				<tr> 
					<td colspan="5" align="center"> 데이터가 없습니다. </td> 
				</tr>
			</c:when>
			<c:when test="${!empty boardList }">
				<c:forEach var="list" items="${boardList}">
					<tr>
						<td> <c:out value="${list.bid}"/> </td>
						<td> <c:out value="${list.title}"/> </td>
						<td> <c:out value="${list.reg_id}"/> </td>
						<td> <c:out value="${list.view_cnt}"/> </td>
						<td> <c:out value="${list.reg_dt}"/> </td>
					</tr>
				</c:forEach>				
			</c:when>
		
		</c:choose>
	</tbody>
</table>
</body>
</html>