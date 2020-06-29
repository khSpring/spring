<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page= "../common/menubar.jsp"/>
	
	<h1 align="center">게시글 상세보기</h1>
	<br>
	
	<!-- 게시글 작성하기(boardInsertForm에서 복사하고 수정하자!) -->
	<table align="center" cellpaddin="10" cellspacing="5" border="1" width="500">
		<tr align="center" valign="middle">
			<th colspan="2">${board.bId }번 글 상세보기</th>
		</tr>
		<tr>
			<td height="15" width="70">제목</td>
			<td>${board.bTitle }</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${board.bWriter }</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>${board.bContent }</td>
		</tr>
		<tr>
			<td>첨부파일</td>
			<td>
				<c:if test="${!empty board.originalFileName }">
					<a href="${contextPath }/resources/buploadFiles/${board.renameFileName}" download="${board.originalFileName }">${board.originalFileName }</a>
				</c:if>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<c:url var="bupView" value="bupView.do">
					<c:param name="bId" value="${board.bId }"/>
					<c:param name="page" value="${currentPage }"/>
				</c:url>
				<c:url var="bdelete" value="bdelete.do">
					<c:param name="bId" value="${board.bId }"/>
				</c:url>
				<c:url var="blist" value="blist.do">
					<c:param name="page" value="${currentPage }"/>
				</c:url>
				<!-- 수정하기 -->
				<!-- 삭제하기 -->
				<c:if test="${loginUser.id eq board.bWriter }">
					<a href="${bupView }">수정하기</a>&nbsp;
					<a href="${bdelete }">삭제하기</a>&nbsp;
				</c:if>
				
				<!-- 게시글 목록으로 돌아가기 -->
				<a href="${blist }">목록으로</a>
			</td>
		</tr>
	</table>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
</body>
</html>