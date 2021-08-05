/*
	https://developer.mozilla.org/ko/docs/Web/Guide/AJAX/Getting_Started

 */



function getParameterValues() {
	var name = document.getElementById("name").value;
	var kor = document.getElementById("kor").value;
	var eng = document.getElementById("eng").value;
	var math = document.getElementById("math").value;
	
	return "?name=" + encodeURIComponent(name) + "&kor=" + kor + "&eng=" + eng + "&math=" + math;
}

function load() {
	var url = "score.do" + getParameterValues();
	
	httpRequest = new XMLHttpRequest();         // XHR : 서버와 통신하기위한 객체 (javascript object) -> http를 통해 데이터 송수신 지원
	httpRequest.onreadystatechange = callback;  // callback function
	httpRequest.open("GET", url, true);         // true : 비동기 / false : 동기
	httpRequest.send();                         // get : send() / post : send(data)
}

function callback(){
	alert("readystate: " + httpRequest.readyState);
	if(httpRequest.readyState == 4) {
		alert("status: " + httpRequest.status);
		
		if(httpRequest.status == 200) {
			var obj = JSON.parse(httpRequest.responseText);
			
			document.getElementById("result").innerHTML = decodeURIComponent(obj.name) 
			+ "<br/>총점 : " + obj.sum + "<br/>평균 :" + obj.avg;
			
		} else {
			alert("통신 실패");
		}
	}
}

/*
	<onreadystatechange>
	-readystate
	 - 0 : uninitialized (실행-load-되지 않음)
     - 1 : loading (실행중)
     - 2 : loaded (실행완료)
     - 3 : interactive (통신됨)
     - 4 : complete (통신 완료)
	
	-status
     - 200 : 성공
     - 400 : bad request
     - 401 : unauthorized
     - 403 : forbidden
     - 404 : not found
     - 500 : internal server error
*/