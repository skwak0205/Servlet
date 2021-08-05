<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>

<c:catch var="err">
	<c:set var="weatherURL"
		value="http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=${code}" />
	
	<c:import var="weather" url="${weatherURL}" /> <%-- weatherURL에 있는 서버에 요청 해 응답 받은 document를 weather라는 변수에 저장 --%>
	
	<x:parse var="wrss" xml="${weather}" /> <%-- 응답받은 값은 문자열이니 parse tree 형태로 바꿔줌 (객체화) --%>
	
	{"pubDate":"<x:out select="$wrss/rss/channel/pubDate" />",
	
	"temp":"<x:out
		select="$wrss/rss/channel/item/description/body/data/temp" />",

	"reh":"<x:out select="$wrss/rss/channel/item/description/body/data/reh" />",
	
	"pop":"<x:out select="$wrss/rss/channel/item/description/body/data/pop" />",
	
	"x":"<x:out select="$wrss/rss/channel/item/description/header/x" />",
	
	"y":"<x:out select="$wrss/rss/channel/item/description/header/y" />",
	
	"wfKor":"<x:out
		select="$wrss/rss/channel/item/description/body/data/wfKor" />"}
</c:catch>

<c:if test="${err!=null}">
	${err}
</c:if>
