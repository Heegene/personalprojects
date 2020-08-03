<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<%
	String seq = request.getParameter("seq");
%>
<c:set var="seq" value="<%=seq%>"></c:set>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		getBoardDetail();
	});
	
	// 게시판 목록 페이지 요청
	function goBoardList() {
		location.href="/board/boardList";
	}
	
	// 게시글 상세 조회 
	
	function getBoardDetail(seq) {
		var seq = $("#seq").val();
		
		if (seq != "") {
			$.ajax({
				url : "/board/getBoardDetail",
				data : $("#boardForm").serialize(),
				datatype : "JSON",
				cache: false,
				async: true,
				type: "POST",
				success: function(obj) {
					getBoardDetailCallback(obj);					
				},
				error: function(xhr, status, error) {}
			});
		} else {
			alert("오류가 발생하였습니다. \n 관리자에게 문의해 주세요.")
			
		}
	}
	
	function getBoardDetailCallback(obj) {
		let str = "";
		
		if (obj != null) {
			let seq = obj.seq;
			let re_ref = obj.re_ref;
			let re_lev = obj.re_lev;
			let re_seq = obj.re_seq;
			let writer = obj.writer;
			let subject = obj.subject;
			let content = obj.content;
			let hits = obj.hits;
			let del_yn = obj.del_yn;
			let insertid = obj.insertid;
			let passwd = obj.passwd;
			let regdate = obj.regdate;
			let updateid = obj.updateid;
			let updatedate = obj.updatedate;
			
			str += "<tr>";
			str += "<th> 제목 </th>";
			str += "<td>" + subject + "</td>";
			str += "<th> 조회수 </th>";
			str += "<td>" + hits + "</td>";
			str += "</tr>";
			
			str += "<tr>";
			str += "<th> 작성자 </th>";
			str += "<td>" + writer + "</td>";
			str += "<th> 작성일시 </th>";
			str += "<td>" + regdate + "</td>";
			str += "</tr>";
			
			
			str += "<tr>";
			str += "<th> 내용 </th>";
			str += "<td colspan='3'>" + content + "</td>";
			str += "</tr>";
			
			
		} else {
			alert("등록된 글이 존재하지 않습니다.");
			return;
		}
		
		$("#tbody").html(str); //이부분 불필요하면 빼기 
	}
	
	// 게시글 수정
	function updateBoard() {
		var subject = $("#board_subject").val();
		var content = $("#board_content").val();
		
		if (subject == "") {
			alert("제목을 입력해 주세요.");
			$("#board_subject").focus();
			return;
		}
		if (content == "") {
			alert("제목을 입력해 주세요.");
			$("#board_content").focus();
			return;
		}
		
		var yn = confirm("수정을 완료하시겠습니까?");
		
		if (yn) {
			$.ajax({
				url : "/board/updateBoard",
				data: $("#boardForm").serialize(),
				dataType: "JSON",
				cache: false,
				async: true,
				type: "POST",
				success: function(obj) {
					updateBoardCallback(obj);
				},
				error: function(xhr, status, error) {}
			});
			
		}
		
	}
	
	// 수정 callback 함수
	function updateBoardCallback(obj) {
		if (obj != null) {
			var result = obj.result;
			
			if (result == "SUCCESS") {
				alert("게시글 수정에 성공하였습니다.");
				goBoardList();
			} else {
				alert("게시글 수정에 실패하였습니다. ");
				return;
			}
			
		}
		
	}

</script>
</head>


<div id="wrap">
	<div id="container">
		<div class="inner">
			<h2> 게시글 상세</h2>		
			<form id="boardForm" name="boardForm">
				<table width="100%" class="table02">
					<caption> <strong><span class="t_red">*</span> 표시는 필수 입력 항목입니다. </strong> </caption>
					<colgroup>
						<col width="20%">
						<col width="*">
					</colgroup>
					
					<tbody id="tbody">
						<tr>
							<th> 제목 </th>
							<td> <input id="board_subject" name="board_subject" value="" clas="tbox01"> </td>
						</tr>
						
						<tr>
							<th> 작성자 </th>
							<td id="board_writer"> </td>
						</tr>
						
						<tr>
							<th> 내용 <span class="t_red">*</span> </th>
							<td colspan="5"> <textarea rows="40" cols="10" id="board_content" name="board_content"></textarea>
						</tr>
					</tbody>
				</table>
				
				
				<input type="hidden" id="board_seq" name="board_seq" value="${seq }" />
				<input type="hidden" id="search_type" name="search_type" value="U" />
				<!--  조회타입: 상세 S 수정 U -->
				
			</form>
		
		<div class="btn_right mt15">
			<button type="button" class="btn black mr5" onclick="javascript:goBoardList();"> 목록으로 </button>
			<button type="button" class="btn black mr5" onclick="javascript:updateBoard();"> 수정하기</button>
		
		</div>
		</div>
	</div>

</div>

<body>
</body>
</html>