<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- 이미 session scope에 sc라는 이름의 객체가 있기 때문에 새로 생성하지 않고 값을 가져옴 -->
	<jsp:useBean id="sc" class="com.eljstl.score.Score" scope="session"></jsp:useBean>

	<jsp:getProperty property="name" name="sc"/>
	
	<br />
	
	${sc.name }
	
</body>
</html>