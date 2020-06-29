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
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
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
				<!-- 
					이제 주소 api를 작성 해보자.
					주소와 우편번호를 입력 할<input>들을 생성하고 적당한 name과 class를 부여
				 -->
				<tr>
				 	<td>우편번호</td>
				 	<td>
				 		<input type="text" name="post" class="postcodify_postcode5" value="" size="6">
				 		<button type="button" id="postcodify_search_button">검색</button>
				 	</td>
				 </tr>
				 <tr>
				 	<td>도로명 주소</td>
				 	<td><input type="text" name="address1" class="postcodify_address" value=""></td>
				 </tr>
				 <tr>
				 	<td>상세 주소</td>
				 	<td><input type="text" name="address2" class="postcodify_extra_info" value=""></td>
				 </tr>
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
				 		<button type="button" onclick="validate()">가입하기</button>
				 		<!-- 이 버튼은 버튼이지만 군디 button타입을 줘서 자동 서브밋 기능을 막았다는 걸 유념하자! -->
				 		&nbsp;
				 		<input type="reset" value="취소하기">
				 	</td>
				 </tr>
			</table>
		</form>
		<br><br>
		<a href="home.do">시작 페이지로 이동</a>
	</div>
	<!-- 작성 후 minsert.do요청을 처리하는 부분을 작성하러 MemberController로 다시 돌아가자. -->
	
	<script>
		function validate(){
			$("#joinForm").submit();
		}
	</script>
	
	
	
	
</body>
</html>