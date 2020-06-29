<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- <div id="error-container" style="text-align:center;">
		<h1>Error</h1>
		<h2 style="color:red;">
			${msg }
		</h2>
		<a href="home.do">시작 페이지로 돌아가기</a>
	</div> --%>
	
	<!-- web.xml에 공용 에러 페이지 등록 후에 실행할 부분 -->
	<div id="error-container" style="text-align:center;">
		<h1>Error</h1>
		<h2 style="color:red;">
			<%-- <%=request.getAttribute("javax.servlet.error.message") %> --%>
			${requestScope['javax.servlet.error.message'] }
		</h2>
		<a href="home.do">시작 페이지로 돌아가기</a>
	</div>
</body>
</html>