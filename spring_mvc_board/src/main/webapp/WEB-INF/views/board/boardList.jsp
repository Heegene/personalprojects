<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판 목록</title>

<link rel="stylesheet" type="text/css" href="/css/common/common.css"/>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		getBoardList();
	});
	
	// 게시글 조회 페이지 이동
	function goBoardDetail(seq) {
		location.href = "/board/boardDetail?seq=" + seq;
	}
	
	
	// 게시글 작성 페이지 이동
	
	function goBoardWrite() {
		location.href = "/board/boardWrite";
	}
	
	// 게시판 목록 조회
	function getBoardList() {
		$.ajax({
			type:"GET",
			url:"/board/getBoardList",
			dataType:"JSON",
			success : function(obj) {
				getBoardListCallback(obj);
			},
			error : function(xhr, status, error) {}
		});
	}
	
	// 게시글 목록 콜백함수 
	function getBoardListCallback(obj) {
		
		let list = obj;
		let listLen = obj.length;
		console.log(list);
		console.log(listLen);
		
		let str = "";
		
		if (listLen > 0) {
			for (let i = 0; i < listLen; i++) {
				let seq = list[i].seq;
				let rereq = list[i].re_req;
				let relev = list[i].re_lev;
				let reseq= list[i].re_seq;
				let writer = list[i].writer;
				let subject = list[i].subject;
				let content = list[i].content;
				let hits = list[i].hits;
				let delyn = list[i].delyn;
				let insertid = list[i].insertid;
				let passwd = list[i].passwd;
				let regdate = list[i].regdate;
				let updateid = list[i].updateid;
				let updatedate = list[i].updatedate;
				
				
				str += "<tr>";
				str += "<td>" + seq + "</td>";
				str += "<td onclick='javascript:goBoardDetail("+ seq + ");'style='cursor:Pointer'>" + subject + "</td>";
				str += "<td>" + hits + "</td>";
				str += "<td>" + writer + "</td>";
				str += "<td>" + regdate + "</td>";
				str += "</tr>";
			}
		} else {
			
			str += "<tr colspan='4'>";
			str += "<td> 등록된 글이 존재하지 않습니다. </td>";
			str += "<tr>";
			
		}
		
		$("#tbody").html(str);
	}
</script>
</head>
<body>
<div id="wrap">
<div id="container">
<div class="inner">

<h2> 게시글 목록 </h2>

<form id="boardForm" name="boardForm">

<table width="100%" class="table01">
	<colgroup>
	<col width="10%" />
	<col width="25%" />
	<col width="10%" />
	<col width="15%" />
	<col width="20%" />
	<thead>
		<tr>
			<td> 글번호 </td>
			<td> 제목 </td>
			<td> 조회수 </td>
			<td> 작성자 </td>
			<td> 작성일 </td>
		</tr>
	</thead>
	<tbody id="tbody">
	
	</tbody>
</table>


</form>

<div class="btn_right mt15">
	<button type="button" class="btn black mr5" onclick="javascript:goBoardWrite();">글 작성</button>
</div>

</div>
</div>
</div>

</body>
</html>