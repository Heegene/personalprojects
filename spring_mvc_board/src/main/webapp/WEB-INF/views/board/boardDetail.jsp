<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<% String seq = request.getParameter("seq"); %>
<!-- CSS -->
<link rel="stylesheet" type="text/css" href="/css/common.css">

<!--  게시글 번호  -->
<c:set var="seq" value="<%=seq %>"></c:set>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		getBoardDetail();
	});
	
	// 게시글 목록 페이지 이동
	function goBoardList() {
		location.href = "/board/boardList";
	}
	
	// 게시글 수정 페이지 이동
	function goBoardUpdate() {
		 var seq = $("#seq").val();
		location.href = "/board/boardUpdate?seq="+seq;
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
		
		$("#tbody").html(str);
	}
	
	
	// 삭제기능 함수 
	function deleteBoard() {
		let seq = $("#board_seq").val();
		let yn = confirm("게시글을 삭제하시겠습니까?");
		if (yn) {
			$.ajax({
				url : "/board/deleteBoard",
				data : $("#boardForm").serialize(),
				dateType: "JSON",
				cache: false,
				async: false,
				type: "POST",
				success: function(obj) {
					deleteBoardCallback(obj);
				},
				error: function(xhr, status, error) {}
			});
		}
	}
	
	
	// delete function 콜백함수 
	function deleteBoardCallback(obj) {
		if (obj != null) {
			let result = obj.result;
			
			if (result == "SUCCESS") {
				alert("게시글 삭제에 성공하였습니다.");
				goBoardList();
			} else {
				alert("게시글 삭제에 실패하였습니다.")
				return;
			}
			
		}
		
	}

</script>

</head>
<body>

	<div id="wrap">
		<div id="container">
			<div class="inner">
				<h2> 게시글 상세 </h2>			
				<form id="boardForm" name="boardForm">
					<table width="100%" class="table01">
						<colgroup>
							<col width="15%">
							<col width="35%">
							<col width="15%">
							<col width="*">
						</colgroup>					
						<tbody id="tbody">
						</tbody>
					
					</table>
					
					<input type="hidden" id="seq" name="seq" value="${seq}" />
					<input type="hidden" id="search_type" name="search_type" value="S" />
				
				</form>
					<div class="btn_right mt15">
						<button type="button" class="btn black mr5" onclick="javascript:goBoardList();">목록으로 돌아가기</button>
						<button type="button" class="btn black mr5" onclick="javascript:goBoardUpdate();">글 수정</button>
						<button type="button" class="btn black mr5" onclick="javascript:deleteBoard();">글 삭제</button>
						
					</div>
					
			
			</div>		
		</div>
	</div>

</body>
</html>