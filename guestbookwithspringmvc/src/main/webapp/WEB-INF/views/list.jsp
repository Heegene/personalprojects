<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>방명록 목록</title>
<style type="text/css">
	hr {
	color: blue;
	}
	
	h2 {
	color: gray;
	text-align: center;
	}

</style>
</head>
<body>

	<h2>방명록</h2>
	<br> 방명록 전체 수 : ${count }
	<br>
	<br>

	<!--  방명록 목록 출력  -->
	<c:forEach items="${list }" var="guestbook">
	<br>
	${guestbook.id } <br>
	${guestbook.name } <br>
	${guestbook.content } <br>
	${guestbook.regdate } <br>
	<br>
	<hr>
	</c:forEach>

	<br>
	
	<!--  페이지 링크가 나오는 부분 -->
	<c:forEach items="${pageStartList }" var="pageIndex" varStatus="status">
		<a href="list?start=${pageIndex }">${status.index + 1}</a> &nbsp;&nbsp;		
	</c:forEach>

	<br>
	<br>
	
	<form action="write" method="post">
		name : <input type="text" name="name" required="required"> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; <button type="submit">등록</button>
		<br>
		<br>
		<textarea rows="6" cols="60" name="content"></textarea> <br>
	</form>

</body>
</html>