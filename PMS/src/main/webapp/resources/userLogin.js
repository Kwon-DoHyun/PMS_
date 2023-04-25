function checkValues(htmlForm) {

	console.log("유효성 검사 함수 출력됨"); // htmlForm이 제대로 넘어갔는지 확인 용도


	let url = "/userLogin?";

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
		alert('아이디가 입력되지 않았습니다');
		check = false;
	}
	else if (userPassword === "") {
		alert('비밀번호가 입력되지 않았습니다');
		check = false;
	}


	if (check === false) {

		location.href = url;

	} else {
		alert('로그인 시도중...');
		htmlForm.submit();

	}


}

