/**
 * 
 */ 
 function duppleErrorCheck(stringValue) {
	console.log("에러 검사 함수 출력됨"); 
	let errorCode = stringValue;

	console.log(errorCode); 

	if (errorCode != null) {
		if (errorCode === 'password') {
			console.log("비번 체크 출력됨"); 
			alert('비밀번호가 틀립니다.');

		}else if (errorCode === 'table') {
			console.log("테이블 체크 출력됨"); 
			alert('해당 계정은 지금 삭제할 수 없는 계정입니다. 고객센터로 문의주세요.');

		}
	}

}
 function checkValues(htmlForm) {

	console.log("유효성 검사 함수 출력됨"); // htmlForm이 제대로 넘어갔는지 확인 용도


	let url = "/clientDelete?";

	let check = true;


	const clientId = document.getElementById("clientId").value;
	const clientPassword = document.getElementById("clientPassword").value;
	const checkPassword = document.getElementById("checkPassword").value;






	if (clientId !== "") {
		url += "clientId=" + clientId + "&";
	} if (clientPassword !== "") {
		url += "clientPassword=" + clientPassword + "&";
	} 



	console.log(clientId);
	console.log(clientPassword);
	console.log(checkPassword);




	if (clientId === "") {
		alert('아이디가 입력되지 않았습니다');
		check = false;
	}
	else if (clientPassword === ""||checkPassword=== "") {
		alert('비밀번호가 입력되지 않았습니다');
		check = false;
	}else if (clientPassword !== checkPassword) {
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
