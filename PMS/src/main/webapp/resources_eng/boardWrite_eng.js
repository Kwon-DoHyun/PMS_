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
		alert("You must enter a title.");
	} else if (boardContents.value === "") {
		check = false;
		alert("You must enter your details.");
	}

	if (check === flase) {
		location.href = url;
	} else {
		htmlForm.submit();
	}
}