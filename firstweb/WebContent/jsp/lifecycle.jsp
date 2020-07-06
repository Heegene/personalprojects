<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
Hello~
<!-- out.print와는 다름 콘솔에 찍는 sysout  -->
<% System.out.println("jspService()"); %>

<!-- 서비스만 쓸 수 있냐 하면 그건 아님 %! 이렇게쓰면 해당메소드 바깥쏙에 써짐 -->

<%! 
	public void jspInit() {
		System.out.println("jspInit()수정수정 ");
}

%>

<%! 
	public void jspDestroy() {
		System.out.println("jspDestroy() 이거호출을 위한 수정");
}

%>
</body>
</html>