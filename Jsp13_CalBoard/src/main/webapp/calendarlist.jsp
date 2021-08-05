<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
	function allCheck(bool) {
		var chks = document.getElementsByName("chk");
		
		for(var i = 0; i < chks.length; i++) {
			chks[i].checked = bool;
		}
	}
</script>

</head>
<body>

	<jsp:useBean id="util" class="com.cal.controller.Util"></jsp:useBean>  <%-- scope에 객체가 저장되어 있지 않으면, 객체 생성 --%>

	<h1>일정 목록</h1>

	<form action="cal.do" method="post">
		<input type="hidden" name="command" value="muldel" />
		
		<table border="1">
			<col width="50" />
			<col width="50" />
			<col width="500" />
			<col width="200" />
			<col width="100" />
			
			<tr>
				<th><input type="checkbox" name="all" onclick="allCheck(this.checked);" /></th>
				<th>번호</th>
				<th>제목</th>
				<th>일정</th>
				<th>작성일</th>
			</tr>
			
			<c:choose>
				<c:when test="${empty list }">
					<tr>
						<th colspan="5">------------------------------일정이 없습니다------------------------------</th>
					</tr>
				</c:when>
				
				<c:otherwise>
					<c:forEach items="${list }" var="dto">
						<tr>
							<th><input type="checkbox" name="chk" value="${dto.seq }" /></th>
							<td>${dto.seq }</td>
							<td><a href="cal.do?command=detail&seq=${dto.seq }">${dto.title }</a></td>
							<td>
								<jsp:setProperty property="todates" name="util" value="${dto.mdate }" />  <%-- util.setTodates(dto.mdate)와 같음 --%>
								<jsp:getProperty property="todates" name="util"/> <%-- util.getTodates()와 같음 --%>
							</td>
							<td><fmt:formatDate value="${dto.regdate }" pattern="yyyy.MM.dd" /></td> <%-- 날짜 정보를 가진 객체(Date)를 특정 형식으로 변환하여 출력 --%>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			
			<tr>
				<td colspan="5" align="right">
					<input type="submit" value="선택삭제" />
					<input type="button" value="목록" onclick="location.href='cal.do?command=calendar'" />
				</td>
			</tr>
		
		</table>
			
	</form>
</body>
</html>