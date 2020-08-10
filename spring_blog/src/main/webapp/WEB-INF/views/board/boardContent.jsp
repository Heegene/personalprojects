<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/views/layout/header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 조회</title>
<script type="text/javascript">
		
		// 댓글목록 조회
		$(document).ready(function() {
			showReplyList();
		});
		
		
		// 목록 버튼 클릭
		$(document).on('click', '#btnList', function(e) {
			e.preventDefault();
			location.href="${pageContext.request.contextPath}/board/getBoardList";
		});
		
		
		// 수정버튼 클릭
		$(document).on('click', '#btnUpdate', function() {
			var url = "${pageContext.request.contextPath}/board/editForm";
			url = url + "?bid=" + ${boardContent.bid} + "&mode=edit";
			// 글번호를 인자로 받고, mode 는 입력 폼을 신규 입력 외에 수정에서도 사용할 것이므로 필요한 인자
			// 신규 입력과 수정과의 차이를 둠 
			// (bid의 유무만으로도 판단할 수는 있지만 더 명확한 구분을 위해 따로 구분값을 둠 )
			location.href = url;
		});
		
		// 삭제버튼 클릭
		$(document).on('click', '#btnDelete', function() {
			var url = "${pageContext.request.contextPath}/board/deleteBoard";
			url = url + "?bid=" + ${boardContent.bid};
			location.href = url;
		});
		
		// 댓글 저장버튼 클릭
		$(document).on('click', '#btnReplySave', function(){
			var replyContent = $('#content').val();
			var replyReg_id = $('#reg_id').val();
			
			var paramData = JSON.stringify({"content": replyContent, 
											"reg_id": replyReg_id, 
											"bid": '${boardContent.bid}'
											});
			var headers = {"Content-Type" : "application/json"
						   ,"X-HTTP-Method-Override" : "POST"};
			
			$.ajax({
					   url: "${pageContext.request.contextPath}/restBoard/saveReply"
					 , headers : headers
					 , data : paramData
					 , type : 'POST'
					 , dataType : 'text'
					 , success : function(result) {
						 showReplyList();
						 
						 $('#content').val('');
						 $('#reg_id').val('');
						 
					 }
					 , error : function(error) {
						 console.log("에러 :" + error);
					 }
			});
			
		});
		
		// 댓글 수정화면 함수
		
		function fn_editReply(rid, reg_id, content) {
			var htmls = "";
			htmls += '<div class="media text-muted pt-3" id="rid' + rid + '">';
			htmls += '<svg class="bd-placeholder-img mr-2 rounded" width="32" height="32" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder:32x32">';
			htmls += '<title>Placeholder</title>';
			htmls += '<text x="50%" fill="#007bff" dy=".3em"> 32x32 </text>';
			htmls += '</svg>';
			htmls += '<p class="media-body pb-3 mb-0 small lh-125 border-bottom horder-gray"> ';
			htmls += '<span class="d-block"> ';
			htmls += '<strong class="text-gray-dark">' + reg_id + '</strong>';
			htmls += '<span style="padding-left: 7px; font-size: 9pt">';
			htmls += '<a href="javascript:void(0)" onclick="fn_updateReply('+ rid + ',\'' + reg_id + '\')" style="padding-right: 5px"> 저장 </a>';
			htmls += '<a href="javascript:void(0)" onclick="showReplyList()"> 취소 </a>';
			htmls += '</span>';
			htmls += '</span>';
			htmls += '<textarea name="editContent" id="editContent" class="form-control" rows="3">';
			htmls += content;
			htmls += '</textarea>';
			
			htmls += '</p>';
			htmls += '</div>';
			
			// 댓글의 일련번호(rid)를 이용하여 댓글 고유의 rid 생성함 (id= rid1, rid2, ...)
			// 해당 아이디를 가진 element의 내용을 htmls의 내용으로 교체 
			$('#rid' + rid).replaceWith(htmls);
			$('#rid' + rid + '#editContent').focus();
			
		}
		
		// 댓글 수정 등록 함수
		function fn_updateReply(rid, reg_id) {
			var replyEditContent = $('#editContent').val(); // 댓글 수정창의 textarea(id=editContent 내용)
			var paramData = JSON.stringify({"content":  replyEditContent
										   , "rid" : rid});
			
			var headers =  {  "Content-Type": "application/json"
					        , "X-HTTP-Method-Override": "POST"};
			
			$.ajax({
				      url : "${pageContext.request.contextPath}/restBoard/updateReply"
				    , headers: headers
				    , data : paramData
				    , type : "POST"
				    , success: function(result) {
				    	console.log(result);
				    	showReplyList();
				    }
				    , error: function(error) {
				    	console.log("에러 -> " + error);
				    }
			});
		}
		
		// 댓글 리스트 조회 함수(ajax 통신)
		function showReplyList() {
			var url = "${pageContext.request.contextPath}/restBoard/getReplyList";
			var paramData = {"bid" : "${boardContent.bid}"}
			$.ajax({
				type: 'POST', // HTTP 방식
				url: url, // 서비스 주소
				data: paramData, // 서비스 처리에 필요한 인자값
				dataType: 'json', // return 받을 데이터 타입(json, text, ...)
				success: function(result) { // result = 결과값
					var htmls = ""; // 서비스 성공 시 처리할 내용 (결과값이 있으면 반복문으로 댓글 수만큼 댓글 리스트 작성)
					if(result.length < 1) {
						htmls += '<div> <p> 등록된 댓글이 없습니다. </p> </div>'
					} else {
						$(result).each(function() {
							htmls += '<div class="media text-muted pt-3" id="rid'+ this.rid + '">';
							htmls += '<svg class="bd-placeholder-img mr-2 rounded" width="32" height="32" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder:32x32">';
							htmls += '<title>Placeholder</title>';
							htmls += '<rect width="100%" height="100%" fill="#007bff"> </rect>';
							htmls += '<text x="50%" fill="#007bff" dy=".3em">32x32</text>';
							htmls += '</svg>';
							htmls += '<p class="media-body pb-3 mb-0 small lh-125 border-bottom horder-gray">';
							htmls += '<span class="d-block">';
							htmls += '<strong class="text-gray-dark">' + this.reg_id + '</strong>';
							htmls += '<span style="padding-left:7px; font-size: 9pt"';
							htmls += '<a href="javascript:void(0)" onclick="fn_editReply(' + this.rid + ', \'' + this.reg_id + '\', \'' + this.content + '\' )" style="padding-right:5px"> 수정 </a>';
		                    htmls += '&nbsp; &nbsp; &nbsp; <a href="javascript:void(0)" onclick="fn_deleteReply(' + this.rid + ')" > 삭제 </a>';
		                    htmls += '</span>';
		                    htmls += '</span>';
		                    htmls += this.content;
		                    htmls += '<br><br>' + this.reg_dt;
		                    htmls += '</p>';
		                    htmls += '</div>';
		                    
						}); // each 종료 
					} 
					$("#replyList").html(htmls); // 댓글 리스트 영역에 출력 
					// $선택자.html(html코드) => 선택자 내의 html 구문을 html코드로 대체하게 됨(기존의 내용은 삭제)
				} // ajax success 종료
			}); // ajax 종료
		}
		
		// 댓글 삭제 이벤트
		function fn_deleteReply(rid) {
			var paramData = {"rid" : rid};
			
			$.ajax({
					  url : "${pageContext.request.contextPath}/restBoard/deleteReply"
				    , data : paramData
				    , type : "POST"
				    , dataType : 'text'
				    , success: function(result){
				    	showReplyList();	
				    }
					, error: function(error) {
						console.log("에러 -> " + error);
					}
				
			});
			
		}

</script>
</head>
<body>
	<article>
		<div class="container" role="main">
			<h2> 게시글 조회</h2>
			
			<div class="bg-white rounded shadow-sm">
				<div class="board_title">
					<c:out value="${boardContent.title}"/>
				</div>
				<div class="board_info_box">
					<span class="board_author"><c:out value="${boardContent.reg_id}" /></span>, 
					<span class="board_date"><c:out value="${boardContent.reg_dt}" /></span>
				</div>
				<div class="board_content">
					${boardContent.content}
				</div>
				<div class="board_tag"> TAG : <c:out value="${boardContent.tag}" /></div>
			</div>
			
			<div style="margin-top:20px">
				<button type="button" class="btn btn-sm btn-primary" id="btnUpdate"> 수정</button>
				<button type="button" class="btn btn-sm btn-primary" id="btnDelete"> 삭제</button>
				<button type="button" class="btn btn-sm btn-primary" id="btnList"> 목록으로</button>
			</div>
		</div>
		
		<!-- 댓글 작성 -->
		
		<div class="my-3 p=3 bg-white rounded shadow-sm" style="padding-top:10px">
			<form:form name="form" id="form" role="form" modelAttribute="replyDto" method="post">
				<form:hidden path="bid" id="bid" />
				
				<div class="row">
					<div class="col-sm-8">
						<form:textarea path="content" id="content" class="form-control" style="padding-right: 0px; width:70%; margin-left: 320px" rows="3" placeholder="댓글을 입력해 주세요"/>
					</div>
					<div class="col-sm-2">
						<form:input path="reg_id" class="form-control" id="reg_id" placeholder="댓글 작성자" style="margin-right: 300px"/>
						<button type="button" class="btn btn-sm btn-primary" id="btnReplySave" style="width:100%; margin-top:10px; margin-bottom: 20px">저장</button>
					</div>
				</div>
			
			</form:form>
	
		</div>
		
		<!-- 댓글 목록 -->
		<div class="my-3 p-3 bg-white rounded shadow-sm" style="margin-left: 320px; padding-top: 10px; width: 50%; align-content: center">
			<h6 class="border-bottom pb-2 mb-0">댓글</h6>
			<div id="replyList"></div>
		</div>
		
		
		
	</article>

</body>
</html>