function formatPhone(input) {
	let phoneNumber = input.value.replace(/[^0-9]/g, ''); // 입력된 값에서 숫자 이외의 문자 제거
	let formattedPhoneNumber = phoneNumber.replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, "$1-$2-$3"); // 3-4-4 형식으로 분할하고 각 부분을 하이픈으로 연결
	input.value = formattedPhoneNumber; // 변환된 값을 다시 input 요소에 할당하여 표시
}


function checkValues(htmlForm) {

	console.log("유효성 검사 함수 출력됨"); // htmlForm이 제대로 넘어갔는지 확인 용도


	let url = "/findId?";

	let check = true;


	const userName = document.getElementById("userName").value;
	const userPhone = document.getElementById("userPhone").value;





	if (userName !== "") {
		url += "userName=" + userName + "&";
	} if (userPhone !== "") {
		url += "userPhone=" + userPhone + "&";
	}


	console.log(userName);
	console.log(userPhone);

	console.log(url);

	if (userName === "") {
		alert('이름이 입력되지 않았습니다');
		check = false;
	} else if (userPhone === "") {
		alert('핸드폰번호가 입력되지 않았습니다');
		check = false;
	}


	if (check === false) {

		location.href = url;

	} else {
		alert('입력정보 확인중..');
		htmlForm.submit();

	}


}

