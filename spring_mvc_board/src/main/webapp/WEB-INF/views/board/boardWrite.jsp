<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

<!-- CSS -->
<link rel="stylesheet" type="text/css" href="/css/common.css">
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">

<title>게시글 작성</title>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
      /*   $(document).ready(function() {

        });
 */
        // 게시판 목록 페이지로 이동
        function goBoardList() {
            location.href = "/board/boardList";
        }

        // 게시글 작성
        function insertBoard() {
            let boardSubject = $("#subject").val();
            let boardContent = $("#content").val();
            let boardPasswd = $("#passwd").val();

            if (boardSubject == "") {
                alert("제목을 입력해 주세요");
                $("#subject").focus();
                return;
            }

            if (boardContent == "") {
                alert("내용을 입력해 주세요");
                $("#content").focus();
                return;
            }
            
            if (boardPasswd == "") {
                alert("비밀번호를 입력해 주세요");
                $("#passwd").focus();
                return;
            }
            

            let yn = confirm("게시글을 등록하시겠습니까?");
            if (yn) {
                $.ajax({
                    url : "/board/insertBoard",
                    data : $("#boardForm").serialize(),
                    datatype: "JSON",
                    cache: false,
                    async: true,
                    type : "POST",
                    success : function(obj) {
                        insertBoardCallback(obj);
                    },
                    error : function(xhr, status, error) {}

                });
            }
        
        }

        // 게시글 작성 콜백 함수
        function insertBoardCallback(obj) {
            if (obj != null) {
                let result = obj.result;

                if (result == "SUCCESS") {
                    alert("게시글이 등록되었습니다");
                    goBoardList();
                } else {
                    alert("게시글 등록에 실패하였습니다.");
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
				<h2>게시글 작성</h2>
				<form id="boardForm" name="boardForm">
					<table width="100%" class="table02">
						<caption>
							<strong><span class="t_red">*</span>표시는 필수 입력 항목입니다.</strong>
						</caption>
						<colgroup>
							<col width="20%">
							<col width="*">
						</colgroup>
						<tbody id="tbody">
							<tr>
								<th>제목 <span class="t_red"> *</span></th>
								<td><input id="subject" name="subject" value=""
									class="tbox01"></td>
							</tr>
							<tr>
								<th>작성자 <span class="t_red"> *</span></th>
								<td><input id="writer" name="writer" value=""
									class="tbox01"></td>
							</tr>
							<tr>
								<th>내용 <span class="t_red"> * </span></th>
								<td><textarea id="content" name="content"
										cols="20" rows="10" class="textarea01"></textarea></td>
							</tr>
							<tr>
								<th>비밀번호 <span class="t_red"> *</span> </th>
								<td><input id="passwd" name="passwd" type="password" class="tbox01"> </td>
							</tr>
							

						</tbody>
					</table>
				</form>

				<div class="btn_right mt15">
					<button type="button" class="btn black mr5"
						onclick="javascript:goBoardList();">목록으로 돌아가기</button>
					<button type="button" class="btn black"
						onclick="javascript:insertBoard();">등록하기</button>
				</div>


			</div>
		</div>
	</div>

</body>

</html>