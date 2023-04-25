function writerBoard(htmlForm) {

	let url = "/boardWrite_eng?";
	let check = true;

	if (boardTitle !== "") {
		url += "boardTitle=" + boardTitle + "&";
	} if (boardContents !== "") {
		url += "boardContents=" + boardContents + "&";
	}

	const boardTitle = document.getElementById("boardTitle");
	const boardContents = document.getElementById("boardContents");
	console.log("boardTitle:" + boardTitle);
	if (boardTitle.value === "") {
		check = false;
		alert("제목 입력하셔야 합니다.");
	} else if (boardContents.value === "") {
		check = false;
		alert("내용을 입력하셔야 합니다.");
	}

	if (check === flase) {
		location.href = url;
	} else {
		htmlForm.submit();
	}
}