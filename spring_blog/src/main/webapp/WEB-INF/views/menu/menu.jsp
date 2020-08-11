<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/views/layout/header.jsp" %>

<c:url var="saveURL" value="/restMenu/saveMenu"></c:url>
<c:url var="deleteURL" value="/restMenu/deleteMenu"></c:url>
<c:url var="updateURL" value="/restMenu/updateMenu"> </c:url>
<c:url var="getMenuListURL" value="/restMenu/getMenuList"> </c:url>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Menu List</title>

<script type="text/javascript">
	
	$(function() {
		fn_showList();
	});
	
	// 메뉴 조회 ajax
	function fn_showList(){
		var paramData = {};
		
		$.ajax({
				  url: "${getMenuListURL}"
				, type : "POST"
				, dataType : "json"
				, data : paramData
				, success: function(result) {
					console.log(result);
					
					if (result.status == "OK") {
						var list = result.menuList;
						var htmls = "";
						
						result.menuList.forEach(function(e) {
							htmls += "<tr>";
							htmls += "<td>" + e.mid + "</td>";
							htmls += "<td>";
							htmls += '<a href="#" onClick="fn_menuInfo(' + e.mid + ',\'' + e.code + '\',\'' + e.codename + '\',' + e.sort_num + ', \'' + e.comment + '\')" >'; 
							htmls += e.code;
							htmls += "</a> </td>";
							htmls += "<td>" + e.codename + "</td>";
							htmls += "<td>" + e.sort_num + "</td>";
							htmls += "<td>" + e.comment + "</td>";
							htmls += "</tr>";
						});
					} else {
						console.log("조회 실패");
					}
					$('#menuList').html(htmls);
				}
		});
	}
	
	// 저장버튼 클릭시 이벤트
	$(document).on('click', '#btnSave', function(e) {
		e.preventDefault();
		
		var url = "${saveURL}";
		
		// 리스트를 클릭 시 정보 세팅되어 mid값 확인 가능
		// 값이 있음 == 정보를 수정하겠다는 것을 의미하므로 수정 URL로 요청
		if ($("#mid").val() != 0) {
			var url = "${updateURL}"
		}
		
		var paramData = {
						   "code" : $("#code").val()
						 , "codename" : $("#codename").val()
						 , "sort_num" : $("#sort_num").val()
						 , "comment" : $("#comment").val()
		};
		
		$.ajax({
			      url: url
			    , type: "POST"
			    , dataType: "json"
			    , data: paramData
			    , success: function(result) {
			    	fn_showList();
			    	// 초기화 이벤트 호출
			    	$("#btnInit").trigger("click");
			    }
		});
		
	});
	
	// 삭제버튼 클릭시 이벤트
	
	$(document).on('click', '#btnDelete', function(e) {
		e.preventDefault();
		
		if ($("#code").val() == '') {
			alert("삭제할 코드를 선택해 주세요.");
			return;
		}
		
		var url = "${deleteURL}";
		
		var paramData = {
						  "code" : $("#code").val()
		};
		
		$.ajax({
				   url : url
				 , type : "POST"
				 , dataType : "json"
				 , data: paramData
				 , success : function(result) {
						fn_showList();
						// 삭제 후 초기값 세팅 
						// 초기화 이벤트 호출
				    	$("#btnInit").trigger("click");
						
				 }
		});
	});
	
	// 입력값 초기화를 위한 초기화 버튼 이벤트
	$(document).on('click', '#btnInit', function(e) {
		$('#mid').val('');
		$('#code').val('');
		$('#codename').val('');
		$('#sort_num').val('');
		$('#comment').val('');
	});
	
	
	// 메뉴 정보 셋
	
	function fn_menuInfo(mid, code, codename, sort_num, comment) {
		$('#mid').val(mid);
		$('#code').val(code);
		$('#codename').val(codename);
		$('#sort_num').val(sort_num);
		$('#comment').val(comment);
		
		// 코드부분 읽기 모드로 전환 
		$('#code').attr("readOnly", true);
	}

</script>

<style>
	#paginationBox {
		padding: 10px 0px;
	}
	
</style>

</head>
<body>
	<article>
		<div class="container">
			<!--  Menu form -->
			<h4 class="mb-3">Menu Info</h4>
			<div>
				<form:form name="form" id="form" role="form" modelAttribute="menuDto" method="POST" action="${saveURL}">
					<form:hidden path="mid" id="mid" />
					<div class="row">
						<div class="col-md-4 mb-3">
							<label for="code"> Code </label>
							<form:input path="code" id="code" class="form-control" placeholder="" value="" required="" />
							<div class="invalid-feedback">
								Valide Code is required.
							</div>
						</div>
						<div class="col-md-5 mb-3">
							<label for="codename">Code Name</label>
							<form:input path="codename" class="form-control" id="codename" placeholder="" value="" required="" />
							<div class="invalid-feedback">
								Valide codename is required.
							</div>
						</div>
						<div class="col-md-3 mb-3">
							<label for="sort_num">Sort</label>
							<form:input path="sort_num" class="form-control" id="sort_num" placeholder="" value="" required="" />
						</div>
					</div>					
					
					<div class="row">
						<div class="col-md-12 mb-3">
							<label for="comment">Comment</label>
							<form:input path="comment" class="form-control" id="comment" placeholder="" value="" required="" />
						</div>
					</div>
				</form:form>
			</div>
			<!--  Menu form 종료 -->
			
			<div>
				<button type="button" class="btn btn-sm btn-primary" id="btnSave"> 저장 </button>
				<button type="button" class="btn btn-sm btn-primary" id="btnDelete"> 삭제 </button>
				<button type="button" class="btn btn-sm btn-primary" id="btnInit"> 초기화 </button>
			</div>
			
			<h4 class="mb-3" style="padding-top:15px"> Menu List</h4>
			
			<!-- 리스트 -->
			
			<div class="table-responsive">
				<table class="table table-striped table-sm">
					<colgroup>
						<col style="width:10%;" />
						<col style="width:15%;" />
						<col style="width:15%;" />
						<col style="width:10%;" />
						<col style="width:auto;" />
					</colgroup>
					
					<thead>
						<tr>
							<th> Menu ID </th>
							<th> Code </th>
							<th> Codename </th>
							<th> Sort </th>
							<th> Comment </th>
						</tr>
					</thead>
					<tbody id="menuList">
					
					</tbody>
					
				</table>
			
			</div>
			
		</div>
	
	</article>

</body>
</html>