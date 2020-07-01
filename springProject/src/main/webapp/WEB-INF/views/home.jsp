<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>Home</title>
</head>
<body>
	<jsp:include page="common/menubar.jsp"/>

	<!-- ajax 이후에 작업한 부분 -->
	<h1 align="center">게시글 TOP5 목록</h1>
	<table align="center" border="1" cellspacing="0" width="700" id="tb">
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>날짜</th>
				<th>조회수</th>
				<th>첨부파일</th>
			</tr>
		</thead>
		<tbody>
		
		</tbody>
	</table>
	
	<script>
		function topList(){
			$.ajax({
				url:"topList.do",	// BoardController에 메소드 만들자.
				dataType:"json",	
				success:function(data){
					$tableBody = $("#tb tbody");
					$tableBody.html("");
					
					for(var i in data.list){
						var $tr = $("<tr>");
						var $bId = $("<td>").text(data.list[i].bId);
						var $bTitle = $("<td>").text(data.list[i].bTitle);
						var $bWriter = $("<td>").text(data.list[i].bWriter);
						var $bCreateDate = $("<td>").text(data.list[i].bCreateDate);
						var $bCount = $("<td>").text(data.list[i].bCount);
						var $bFile = $("<td>").text(" ");
						
						if(data.list[i].originalFileName != null){
							$bFile = $("<td>").text("★");
						}
						
						$tr.append($bId);
						$tr.append($bTitle);
						$tr.append($bWriter);
						$tr.append($bCreateDate);
						$tr.append($bCount);
						$tr.append($bFile);
						
						$tableBody.append($tr);
					}
				},
				error:function(request, status, errorData){
					alert("error code: " + request.status + "\n"
							+"message: " + request.responseText
							+"error: " + errorData);
				}
				
			})
		}
		$(function(){
			topList();
			
			setInterval(function(){
				topList();
			}, 5000);
		})
		
	</script>
	



</body>
</html>
