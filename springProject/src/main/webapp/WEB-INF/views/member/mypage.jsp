<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.centerText table{margin:auto;}
	body{background-image:url("resources/images/background.jpg");}
</style>
</head>
<body>

<jsp:include page="../common/menubar.jsp"/>
	<h1 align="center">${loginUser.name }님의 정보 보기</h1>
	
	<div class="centerText">
		<form action="mupdate.do" method="post" id="joinForm">
			<table width="500" cellspancing="5">
				<tr>
					<td width="150">* 아이디</td>
					<td width="450">
						<input type="text" name="id" id="userId" value="${loginUser.id }" readonly>
						<!-- ajax후에 적용할 부분 -->
					</td>
				</tr>
				<tr>
					<td>* 이름</td>
					<td><input type="text" name="name" value="${loginUser.name }" readonly></td>
				</tr>
				<!-- <tr>
					<td>* 비밀번호</td>
					<td><input type="password" name="pwd"></td>
				</tr> -->
				<tr>
					<td>성별</td>
					<c:if test="${loginUser.gender eq 'M' }">
						<td>
							<input type="radio" name="gender" value="M" checked>남
							<input type="radio" name="gender" value="F">여
						</td>
					</c:if>		
					<c:if test="${loginUser.gender eq 'F' }">
						<td>
							<input type="radio" name="gender" value="M">남
							<input type="radio" name="gender" value="F" checked>여
						</td>
					</c:if>
				</tr>
				<tr>
					<td>나이</td>
					<td><input type="number" min="20" max="100" name="age" value="${loginUser.age }"></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td><input type="email" name="email" value="${loginUser.email }"></td>
				</tr>
				<tr>
					<td>전화번호</td>
					<td><input type="tel" name="phone" value="${loginUser.phone }"></td>
				</tr>
				<!-- 
					이제 주소 api를 작성 해보자.
					주소와 우편번호를 입력 할<input>들을 생성하고 적당한 name과 class를 부여
				 -->
				 
				<c:forTokens var="addr" items="${loginUser.address }" delims="," varStatus="status">
					 <c:if test="${status.index eq 0 }">
					 	<tr>
						 	<td>우편번호</td>
						 	<td>
						 		<input type="text" name="post" class="postcodify_postcode5" value="${addr }" size="6">
						 		<button type="button" id="postcodify_search_button">검색</button>
						 	</td>
					 	</tr>
					 </c:if>
					 <c:if test="${status.index eq 1 }">
						 <tr>
						 	<td>도로명 주소</td>
						 	<td><input type="text" name="address1" class="postcodify_address" value="${addr }"></td>
						 </tr>
					 </c:if>
					 <c:if test="${status.index eq 2 }">
						 <tr>
						 	<td>상세 주소</td>
						 	<td><input type="text" name="address2" class="postcodify_extra_info" value="${addr }"></td>
						 </tr>
					 </c:if>
				 </c:forTokens>
				 <!-- Postcodify를 로딩하자 -->
				 <script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>
				 <script>
				 	// 검색 단추를 누르면 팝업 레이어가 열리도록 설정한다.
				 	$(function(){
				 		$("#postcodify_search_button").postcodifyPopUp();
				 	});
				 </script>
				 
				 
				 <tr>
				 	<td colspan="2" align="center">
				 		<input type="submit" value="수정하기">
				 		&nbsp;
				 		<c:url var="mdelete" value="mdelete.do">
				 			<c:param name="id" value="${loginUser.id }"/>
				 		</c:url>
				 		<a href="${mdelete }">탈퇴하기</a>
				 	</td>
				 </tr>
			</table>
		</form>
		<br><br>
		<a href="home.do">시작 페이지로 이동</a>
	</div>
</body>
</html>