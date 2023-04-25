/**
 * 
 */



function duppleErrorCheck(stringValue) {
	console.log("에러 검사 함수 출력됨");
	let errorCode = stringValue;

	console.log(errorCode);

	if (errorCode != null) {
		console.log("errorCode가 비어있지 않음");
		if (errorCode === 'password') {
			console.log("비번 체크 출력됨");
			alert('비밀번호가 틀립니다.');

		}else if (errorCode === 'table') {
			console.log("테이블 체크 출력됨"); 
			alert('해당 계정은 지금 삭제할 수 없는 계정입니다. 고객센터로 문의주세요.');

		}else if (errorCode === 'booking') {
			console.log("결제 체크 출력됨"); 
			alert('결제가 완료되지 않은 예약이 있습니다. 마이페이지를 확인해주세요');

		} 
	}

}


function checkValues(htmlForm) {

	console.log("유효성 검사 함수 출력됨"); // htmlForm이 제대로 넘어갔는지 확인 용도


	let url = "/userDelete?";

	let check = true;


	const userId = document.getElementById("userId").value;
	const userPassword = document.getElementById("userPassword").value;
	const checkPassword = document.getElementById("checkPassword").value;





	if (userId !== "") {
		url += "userId=" + userId + "&";
	} if (userPassword !== "") {
		url += "userPassword=" + userPassword + "&";
	}



	console.log(userId);
	console.log(userPassword);
	console.log(checkPassword);



	if (userId === "") {
		alert('아이디가 입력되지 않았습니다');
		check = false;
	}
	else if (userPassword === ""||checkPassword=== "") {
		alert('비밀번호가 입력되지 않았습니다');
		check = false;
	}else if (userPassword !== checkPassword) {
		alert('비밀번호가 다릅니다');
		check = false;
	}


	if (check === false) {

		location.href = url;

	} else {
		alert('삭제 시도중...');
		htmlForm.submit();

	}


}

