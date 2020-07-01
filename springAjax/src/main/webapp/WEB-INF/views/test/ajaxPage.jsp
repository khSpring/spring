<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
	<h1 align="center">Spring에서의 ajax 사용 테스트 페이지</h1>
	<ol>
		<li>
			서버쪽 컨트롤러로 값 보내기
			<button id="test1">테스트</button>
			<div id="d1"></div>
		</li>
		<br>
		<script>
			$(function(){
				$("#test1").on("click",function(){
					$.ajax({
						url:"test1.do",
						data:{name:"신사임당", age:47},
						type:"post",
						success:function(data){
							if(data == 'ok'){
								alert("전송 선공");
							}
							else{
								alert("전송 실패");
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
		</script>
		
		<li>
			컨트롤러에서 리턴하는 JSON 객체 받아서 출력하기
			<button id="test2">테스트</button>
			<div id="d2"></div>
		</li>
		<br>
		<script>
			$(function(){
				$("#test2").on("click",function(){
					$.ajax({
						url:"test2.do",
						//1. 컨트롤러에서 json객체가 넘어올 때 처리법 1
						//dataType:"json",
						success:function(data){
							//2. 컨트롤러에서 JSON객체가 넘어올 때 처리법 2
							data = JSON.parse(data);
							$("#d2").html("번호 : " + data.no
									+ "<br>제목 : " + data.title
									+ "<br>작성자 : " + decodeURIComponent(data.writer)
									+ "<br>내용 : " + decodeURIComponent(data.content.replace(/\+/g," ")))
						},
						error:function(request, status, errorData){
		                    alert("error code: " + request.status + "\n"
		                          +"message: " + request.responseText
		                          +"error: " + errorData);
	                  	}
					})
				})
			})
		</script>
		
		<li>
			컨트롤러에서 리턴하는 JSON 객체 받아서 출력하기
			<button id="test3">테스트</button>
			<div id="d3"></div>
		</li>
		<br>
		<script>
			$(function(){
				$("#test3").on("click",function(){
					$.ajax({
						url:"test3.do",
						dataType:"json",
						success:function(data){
							$("#d3").html("번호 : " + data.no
									+ "<br>제목 : " + data.title
									+ "<br>작성자 : " + data.writer
									+ "<br>내용 : " + data.content)
						},
						error:function(request, status, errorData){
		                    alert("error code: " + request.status + "\n"
		                          +"message: " + request.responseText
		                          +"error: " + errorData);
	                  	}
					})
				})
			})
		</script>
		
		<li>
			컨트롤러에서 리턴하는 json 배열을 받아서 출력하기
			<button id="test4">테스트</button>
			<div id="d4"></div>
		</li>
		<br>
		<script>
			$(function(){
				$("#test4").on("click",function(){
					$.ajax({
						url:"test4.do",
						dataType:"json",
						success:function(data){
							var value="";
							for(var i in data.list){
								value += data.list[i].userId + ","
										+data.list[i].userPwd + ","
										+data.list[i].userName + ","
										+data.list[i].age + ","
										+data.list[i].email + ","
										+data.list[i].phone + "<br>";
										
							}
							$("#d4").html(value);
							
						},
						error:function(request, status, errorData){
		                    alert("error code: " + request.status + "\n"
		                          +"message: " + request.responseText
		                          +"error: " + errorData);
	                  	}
					})
				})
			})
		</script>
		
		
		<li>
			컨트롤러에서 리턴하는 Map 객체(JSON)를 받아서 출력하기
			<button id="test5">테스트</button>
			<div id="d5"></div>
		</li>
		<br>
		<script>
			$(function(){
				$("#test5").on("click",function(){
					$.ajax({
						url:"test5.do",
						dataType:"json",
						success:function(data){
							$("#d5").html("받을 맵 객체 안의 samp객체 정보 확인<br>"
									+ "이름 : " + data.samp.name
									+ ", 나이 : " + data.samp.age);
						},
						error:function(request, status, errorData){
		                    alert("error code: " + request.status + "\n"
		                          +"message: " + request.responseText
		                          +"error: " + errorData);
	                  	}
					})
				})
			})
		</script>
		
		<li>
			뷰에서 JSON객체를 컨트롤러로 보내기
			<button id="test6">테스트</button>
			<div id="d6"></div>
		</li>
		<br>
		<script>
			$(function(){
				$("#test6").on("click",function(){
					var obj = new Object();
					obj.name = "최상원";
					obj.age = 26;
					
					$.ajax({
						url:"test6.do",
						data:JSON.stringify(obj),	// JSON형을 String형태로 바꿔줌
						type:"post",
						contentType:"application/json;charset=utf-8",
						// 뷰에서 어떠한 타입을 보내겠다 하는 내용을 쓰는 부분으로,
						// requestBody를 쓰기 위해 이 속성을 서줘야 함
						success:function(data){
							$("#d6").html(data);
						},
						error:function(request, status, errorData){
		                    alert("error code: " + request.status + "\n"
		                          +"message: " + request.responseText
		                          +"error: " + errorData);
	                  	}
					})
				})
			})
		</script>
	</ol>
	
	<!-- ajax 연습이 끝나면 다시 우리 프로젝트로 가서 ajax를 적용하자. memberJoin.jsp로 이동(아이디 중복체크) -->
	
	
	
	
	
	
	
	
</body>
</html>