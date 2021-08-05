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

	<h1>jsp:useBean</h1>
	
	<!-- Score sc = new Score(); 와 같은 뜻 -->
	<!-- jsp에서 java 객체 사용할 거라는 뜻 -->
	<!-- scope 속성을 사용하면 setAttribute() 하는 것. 하지만  id의 이름과 같은 객체가 먼저 있다면 그게 담김. -->
	<jsp:useBean id="sc" class="com.eljstl.score.Score" scope="session"></jsp:useBean>
	
	
	<!-- sc.setName("hong"); -->
	<jsp:setProperty property="name" name="sc" value="hong" />
	
	<!-- sc.getName(); -->
	<jsp:getProperty property="name" name="sc" />
	
	<br />
	
	<a href="result.jsp">scope</a>

</body>
</html>