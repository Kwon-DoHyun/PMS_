const search = document.getElementById("searchBoard");

search.addEventListener("change", e => {
	searchBoard();
})

function searchBoard() {
	$("tbody").empty();
	
	$.ajax({
		"url": `../service?command=searchBoard&keyword=${search.value}`,
		"method": "GET",
		"contentType": "application/x-www-form-urlencoded; charset=UTF-8",

		"timeout": 0,
	}).done(function(response) {
		response.forEach(board => {
			console.log("시간: "+board.regDate.subString(0,10));
			$("tbody").append(
				`<tr>
					<td class=""><a href="boardDetail?boardNumber=${board.boardNumber}">${board.boardNumber}번 상세(클릭)</a></td>
					<td class="">${board.boardTitle}</td>
					<td class="">${board.boardContents}</td>
					<td class="">${board.userId}</td>
					<td class="">${board.regDate.subString(0,10)}</td>
					<td class="">${board.viewCount}</td>
					
				</tr>`
			)
		});
	});
}