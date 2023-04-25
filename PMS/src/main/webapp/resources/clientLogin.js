


function formatClientNumber(input) {
	let clientNumber = input.value.replace(/[^0-9]/g, ''); // 입력된 값에서 숫자 이외의 문자 제거
	let formattedClientNumber = clientNumber.replace(/^(\d{3})(\d{2})(\d{5})$/, "$1-$2-$3"); // 3-4-4 형식으로 분할하고 각 부분을 하이픈으로 연결
	input.value = formattedClientNumber; // 변환된 값을 다시 input 요소에 할당하여 표시
}





function checkValues(htmlForm) {

	console.log("유효성 검사 함수 출력됨"); // htmlForm이 제대로 넘어갔는지 확인 용도


	let url = "/clientLogin?";

	let check = true;


	const clientNumber = document.getElementById("clientNumber").value;
	const clientPassword = document.getElementById("clientPassword").value;


	if (clientNumber !== "") {
		url += "clientNumber=" + clientNumber + "&";
	} if (clientPassword !== "") {
		url += "clientPassword=" + clientPassword + "&";
	}


	console.log(clientNumber);
	console.log(clientPassword);

	console.log(url);

	if (clientNumber === "") {
		alert('아이디가 입력되지 않았습니다');
		check = false;
	}
	else if (clientPassword === "") {
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

