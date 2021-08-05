<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<%
	pageContext.setAttribute("pageScope", "page scope value");
	request.setAttribute("requestScope", "request scope value"); // request 객체 안에 담음
	session.setAttribute("sessionScope", "session scope value");
	application.setAttribute("applicationScope", "application scope value");
	
%>


</head>
<body>

	<h1>INDEX</h1>

	page : <%=pageContext.getAttribute("pageScope") %>
	<br />
	request : <%=request.getAttribute("requestScope") %>
	<br />
	session : <%=session.getAttribute("sessionScope") %>
	<br />
	application : <%=application.getAttribute("applicationScope") %>
	<br />
	<a href="result.jsp">result</a>
	<br />
	<a href="scope.do">servlet</a>
	<br />
	
	<form action="scope.do" method="get">
		<input type="hidden" name="requestVal" value="my request value" />
		<input type="submit" value="request" />
	</form>

</body>
</html>