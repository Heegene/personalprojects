<<<<<<< HEAD
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
    <head>
        <title>방명록</title>
        <style>
            .guestbook {
                padding: 5px 0px 5px 5px;
                margin-bottom: 5px;
                border-bottom: 1px solid #efefef;
                font-size: 12px;
            }
        </style>
    </head>
    <body>

         <div class="guestbooks"></div>
            <c:forEach var="guestbook" items="${list}">
                <div class="guestbook">
                    <div> <label>id : </label> ${guestbook.id}</div>
                    <div> <label>name : </label> ${guestbook.name}</div>
                    <div> <p>${guestbook.content}</p></div>
                    <div> <label>regdate : </label> ${guestbook.regdate}</div>
                </div>
            </c:forEach>
        </div>

        <br><br><br>

        <form method="post" action="guestbooks/write">
            이름 : <input type="tex" name="name"><br>
            내용 :
            <textarea name="content" cols="50" rows="5"></textarea><br>
            <input type="submit" value="확인">
        </form>
    </body>
=======
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">



<title>방명록</title>
<style>
.guestbook {
	padding: 5px 0px 5px 5px;
	margin-bottom: 5px;
	border-bottom: 1px solid #efefef;
	font-size: 12px;
}

h2 {
	color: gray;
	text-align: center;
}

#button {
	padding: 7px 15px;
	font-size: 15px;
}

#name {
	width:200px;
}

#exampleFormControlTextarea1 {
	width: 40%;
}



</style>
</head>
<body>

	<h2>방명록</h2>
	<div class="guestbooks"></div>
	<c:forEach var="guestbook" items="${list}">
		<div class="guestbook">
			<div>
				<label class="text-muted">번호 : </label> ${guestbook.id}
			</div>
			<div>
				<label class="text-muted">이름 : </label> ${guestbook.name}
			</div>
			<div>
				<p>${guestbook.content}</p>
			</div>
			<div>
				<label class="text-muted">등록일 : </label> ${guestbook.regdate}
			</div>
		</div>
	</c:forEach>
	
	<br>

	<form method="post" action="guestbooks/write">
		이름<br>
		<input class="form-control" id="name" type="text" name="name"><br>
		내용<textarea class="form-control" id="exampleFormControlTextarea1" name="content" cols="50" rows="5"></textarea>
		<br>
		<button type="submit" id="button" class="btn btn-primary" >등록</button>
	</form>
</body>
>>>>>>> 6a8e7501abac37ad1a372ba49caabe1142482439
</html>
