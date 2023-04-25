function checkValues(htmlForm) {

	console.log("유효성 검사 함수 출력됨"); // htmlForm이 제대로 넘어갔는지 확인 용도


	let url = "/userLogin_eng?";

	let check = true;


	const userId = document.getElementById("userId").value;
	const userPassword = document.getElementById("userPassword").value;





	if (userId !== "") {
		url += "userId=" + userId + "&";
	} if (userPassword !== "") {
		url += "userPassword=" + userPassword + "&";
	}


	console.log(userId);
	console.log(userPassword);

	console.log(url);

	if (userId === "") {
		alert('ID not entered');
		check = false;
	}
	else if (userPassword === "") {
		alert('The password has not been entered');
		check = false;
	}


	if (check === false) {

		location.href = url;

	} else {
		alert('log in...');
		htmlForm.submit();

	}


}

