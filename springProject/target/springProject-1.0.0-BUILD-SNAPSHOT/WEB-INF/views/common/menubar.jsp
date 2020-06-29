<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<style>
   .nav{
      width:780px;
      margin-left:auto;
      margin-right:auto;
   }
   .menu{
      display:inline-block;
      background:yellowgreen;
      text-align:center;
      line-height:50px;
      width:150px;
      height:50px;
      margin-left:20px;
      margin-right:20px;
      border-radius:20px;
   }
   .menu:hover{
      background:orangered;
      color:white;
      cursor:pointer;
   }
</style>
</head>
<body>
	<!-- 
		메뉴바는 어느 페이지든 포함하고 잇을 테니 예전에 index.jsp에서
		 지정했던 contextPath 변수값을 여기에 만들어 두면 어느 페이지든
		 열어도 contextPath가 동작 할 수 있다. 
	-->
	<c:set var="contextPath" value="${pageContext.servletContext.contextPath }" scope="application"/>
	
	<h1 align="center">finally Last Subject Spring!!</h1>
	<br>
	
	<!-- 기존과 동일한 개념으로 코드 작성을 할껀데 이제는 바로 로그인 유저가 잇는 경우와 없는 경우를 나눠서 시작하자 -->
	<div class="loginArea" align="right">
		<c:if test="${empty sessionScope.loginUser }">
			<form action="login.do" method="post">
				<table id="loginTable" style="text-align:center;">
					<tr>
						<td>아이디</td>
						<td><input type="text" name="id"></td>
						<td rowspan="2">
							<button id="loginBtn">로그인</button>
						</td>
					</tr>
					<tr>
						<td>비밀번호</td>
						<td><input type="password" name="pwd"></td>
					</tr>
					<tr>
						<td colspan="3">
							<a href="enrollview.do">회원가입</a>
							<!-- 로그아웃까지 완료하고 나서 하자 -->
							<a href="#">이이디/비밀번호 찾기</a>
						</td>
					</tr>
				</table>
			</form>
		</c:if>
		<c:if test="${!empty sessionScope.loginUser }">
			<!-- 여기 부분을 DB까지 길을 뚫고 만들자. -->
			<h3 align="right">
				<c:out value="${loginUser.name }님 환영합니다!!"/>
				<!-- 경로 변경시 사용할 변수 지정 -->
				<c:url var="logout" value="logout.do"/>
				<c:url var="myinfo" value="myinfo.do"/>
				
				<button onclick="location.href='${myinfo}'">정보수정</button>
				<button onclick="location.href='${logout }'">로그아웃</button>
			</h3>
			<!-- 로그인 후 이 부분을 완성하면 위에 회원 가입 부분 a태그 href 경로 바꾸자! -->
		</c:if>
	</div>
	
	<div class="menubar">
		<div class="nav">
			<div class="menu"><a href="home.do">HOME</a></div>
			<div class="menu"><a href="#">공지사항</a></div>
			<div class="menu"><a href="#">게시판</a></div>
			<div class="menu"><a href="#">etc</a></div>
		</div>
	</div>
	
	<!--
		 본격적으로 기능 구현을 하기에 앞서 패키지 폴더들을 만들어 두자 
		(member, notice, board)
		java폴더 아래의 (com.kh.springProject 폴더 아래)에 쭉쭉 만들자!~
	-->
</body>
</html>