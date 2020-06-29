<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
   .centerText table{
      margin: auto;
   }
   body{
      background-image:url("resources/images/background.jpg");
   }
   .guide{
      display:none;
      font-size:12px;
      top:12px;
      right:10px;
   }
   span.ok{color:green;}
   span.error{color:red;}
   

</style>
</head>
<body>
	<jsp:include page="../common/menubar.jsp"/>
	<h1 align="center">회원가입</h1>
	
	<div class="centerText">
		<form action="minsert.do" method="post" id="joinForm">
			<table width="500" cellspancing="5">
				<tr>
					<td width="150">* 아이디</td>
					<td width="450">
						<input type="text" name="id" id="userId">
						<!-- ajax후에 적용할 부분 -->
					</td>
				</tr>
				<tr>
					<td>* 이름</td>
					<td><input type="text" name="name"></td>
				</tr>
				<tr>
					<td>* 비밀번호</td>
					<td><input type="password" name="pwd"></td>
				</tr>
				<tr>
					<td>성별</td>
					<td>
						<input type="radio" name="gender" value="M">남
						<input type="radio" name="gender" value="F">여
					</td>	
				</tr>
				<tr>
					<td>나이</td>
					<td><input type="number" min="20" max="100" name="age"></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td><input type="email" name="email"></td>
				</tr>
				<tr>
					<td>전화번호</td>
					<td><input type="tel" name="phone"></td>
				</tr>	
			</table>
		</form>
	</div>
</body>
</html>