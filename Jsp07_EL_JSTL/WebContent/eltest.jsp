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

	<h1>EL</h1>
	
	<table border="1">
		<tr>
			<th colspan="2">${score.name } 점수</th> <!-- score.getName()과 같음 -->
		</tr>
		
		<tr>
			<th>국어</th>
			<td>${score.kor }</td>
		</tr>
		
		<tr>
			<th>영어</th>
			<td>${score.eng }</td>
		</tr>
		
		<tr>
			<th>수학</th>
			<td>${score.math }</td>
		</tr>
		
		<tr>
			<th>총점</th>
			<td>${score.sum }</td> <!-- 그냥은 null 값나옴. 생성자에 setter 호출!! -->
		</tr>
		
		<tr>
			<th>평균</th>
			<td>${score.avg }</td>
		</tr>
		
		<tr>
			<th>등급</th>
			<td>${score.grade}</td>
		</tr>
	
	</table>

</body>
</html>