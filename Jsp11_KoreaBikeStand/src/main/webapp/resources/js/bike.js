$(function() {
	getJsonData();
});

function getJsonData() {
	// $.getJSON() : AJAX의 짧은 형태, 응답되는 값은 JSON 
	$.getJSON("resources/json/bike.json", function(mydata) {
		// resources/json/bike.json을 mydata라는 변수로 가져옴
		
		// JSON.parse     : json 형태의 문자열을 json 객체(javascript object)로 변환
		// JSON.stringify : json 객체에서 json 형태의 문자열로 변환
		
		$.ajax({
			url: "bike.do",
			method: "post",
			data: { "command": "getdata", "mydata": JSON.stringify(mydata) }, 
			dataType: "json",
			success: function(msg) {
				var $tbody = $("tbody");
				var list = msg.result;
				for (var i = 0; i < list.length; i++) {
					var $tr = $("<tr>");
					$tr.append($("<td>").append(list[i].name));
					$tr.append($("<td>").append(list[i].addr));
					$tr.append($("<td>").append(list[i].latitude));
					$tr.append($("<td>").append(list[i].longitude));
					$tr.append($("<td>").append(list[i].bike_count));

					$tbody.append($tr);
				}
			},
			error: function() {
				alert("통신 실패");
			}
		});
	});
}