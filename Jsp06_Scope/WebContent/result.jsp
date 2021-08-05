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

	<h1>RESULT</h1>

	page : <%=pageContext.getAttribute("pageScope") %>    <%-- 결과 : null --%>
	<br />
	
	<%-- 결과 : null why? result의 request에서, index의 request에 담아둔 값을 찾고 있어서 --%>
	request : <%=request.getAttribute("requestScope") %>  
	<br />
	session : <%=session.getAttribute("sessionScope") %>
	<br />
	application : <%=application.getAttribute("applicationScope") %>
	<br />
	
<%
	String requestVal = request.getParameter("requestVal");
%>

	requestVal : <%=requestVal %>

</body>
</html>