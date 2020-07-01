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
						<!-------------- ajax후에 적용할 부분 -------------->
						
						<span class="guide ok">이 아이디는 사용 가능합니다.</span>
						<span class="guide error">이 아이디는 사용할 수 없습니다.</span>
						<input type="hidden" name="idDuplicateCheck" id="idDuplicateCheck" value="0">
						
						<!-- --------------------------------------- -->
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
		/* function validate(){
			$("#joinForm").submit();
		} */
		
		//ajax 이후에 추가한 것들
		$(function(){
			$("#userId").on("keyup",function(){
				var userId = $(this).val().trim();
				
				if(userId.length < 4) {
					$(".guide").hide();
					$("#idDuplicateCheck").val(0);
					
					return;
					// 에초에 4글자가 안되게 아이디를 쓰면 ajax가 실행되지 않고
					// 이벤트 핸들러 함수가 종료되게 하기 위해서 return;
				}
				
				$.ajax({
					url:"dupid.do",
					data:{id:userId},
					success:function(data){
						//1, Stream을 이용한 방식
						//if(data == "true"){
						//2. jsonView를 이용한 방식
						if(data.isUsable == true){	//보내고자 하는 자료형을 그대로 뽑아올 수 있다.
							$(".guide.error").hide();
							$(".guide.ok").show();
							$("#idDuplicateCheck").val(1);
						}
						else{
							$(".guide.error").show();
							$(".guide.ok").hide();
							$("#idDuplicateCheck").val(0);
						}
					},
					error:function(request, status, errorData){
	                    alert("error code: " + request.status + "\n"
	                          +"message: " + request.responseText
	                          +"error: " + errorData);
                  	}   
				
				})
				
			})
			
		})
		
		function validate(){
			// 아이디 중복 체크 후 회원가입 버튼 눌렀을 때
			if($("#idDuplicateCheck").val() == 0){
				alert("사용 가능한 아이디를 입력해 주세요.");
				$("#userId").focus();
			}else{
				$("#joinForm").submit();
			}
			
				
		}
		
		// 여기까지해서 아이디 중복체크를 완성하고 나면 home.jsp로 가서 게시글 top5하러 가자
	</script>
	
	
	
	
</body>
</html>