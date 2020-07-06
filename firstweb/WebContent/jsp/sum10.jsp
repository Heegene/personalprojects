<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <!-- 페이지 지시문 부분
 자바 언어로 작성된 코드가 나올 것이고,
 이론적으로 jsp는 자바 외 다른언어 사용 가능
 근데 jsp는 실제로는 자바만 사용
 content type 은 서블릿 응답결과에 포함할 response 객체
 응답을 어떤형태로 보낼것인지
 인코딩은 어떤식인지를 보여줌 
  -->
<!-- jsp파일은 html 파일이랑 비슷하게 생김    --> 
<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>Insert title here</title>

</head>

<body>
	<%
	// % 안에는 자바 코드가 들어간다는 뜻
	// 이런걸 스크립트릿이라고함 (scriptlet)
	// 이렇게 자바코드 돌리더라도
	// 실행을 원하면 꼭 response의
	// out.println에 결과 넣어줘야함
		int total = 0;
		for (int i = 1; i <= 10; i++) {
			total += i;
		}
	%>
<!-- jsp는 약속된 기호에 따라서 servlet으로 바꿀때 방식 변경
jsp 자체가 동작하는 게 아니라 모든 jsp는 서블릿으로 바뀌어서 동작됨
그래서 % 이런 기호도 서블릿으로 바뀔 때 어떤식으로 바뀔건지를 표시
 -->
1부터 10까지의 합 : <%=total %>
<!-- 응답 결과는 %= 이렇게 넣어줌 -->
<!-- 이게 out.print(); 랑 같은효과   -->
</body>
</html>