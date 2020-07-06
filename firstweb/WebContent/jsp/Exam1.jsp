<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>



id : <%= getId() %> 
<!-- 윗부분 내용 넣고 출력하면 id : u001 출력됨 출력부를 결정하는 공간  -->

<%! // 선언문 위치는 body 안이기만 하면 위치는 크게 상관 없음 
	String id = "u001"; // 멤버 변수 선언
	public String getId() {
		return id;
	}
%>

</body>
</html>