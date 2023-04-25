function setMaxLength() {
	const bankSelect = document.getElementById('bankSelect').value;
	const clientAccount = document.getElementById('clientAccount');

	switch (bankSelect) {
		case 'kb':
			clientAccount.maxLength = 9;
			break;
		case 'shinhan':
			clientAccount.maxLength = 12;
			break;
		case 'woori':
			clientAccount.maxLength = 14;
			break;
		case 'keb':
			clientAccount.maxLength = 12;
			break;
		case 'nh':
			clientAccount.maxLength = 11;
			break;
		default:
			clientAccount.maxLength = 12;
	}
}

// 계좌번호 입력란에 대한 포맷 변환 함수
function formatAccount(input) {
	const bankSelect = document.getElementById('bankSelect').value;
	let account = input.value.replace(/[^0-9]/g, ''); // 입력된 값에서 숫자 이외의 문자 제거
	let formattedAccount = "";
	if (bankSelect === "kb") {
		formattedAccount = account.replace(/(\d{2})(\d{6})(\d{1})/, '$1-$2-$3');
	} else if (bankSelect === "shinhan") {
		formattedAccount = account.replace(/(\d{3})(\d{2})(\d{6})(\d{1})/, '$1-$2-$3-$4');
	} else if (bankSelect === "woori") {
		formattedAccount = account.replace(/(\d{3})(\d{2})(\d{6})(\d{3})/, '$1-$2-$3-$4');
	} else if (bankSelect === "keb") {
		formattedAccount = account.replace(/(\d{3})(\d{2})(\d{6})(\d{1})/, '$1-$2-$3-$4');
	} else if (bankSelect === "nh") {
		formattedAccount = account.replace(/(\d{3})(\d{2})(\d{5})(\d{1})/, '$1-$2-$3-$4');
	}
	input.value = formattedAccount;
}

// 계좌번호 입력란과 셀렉트 요소에 대한 이벤트 리스너 등록
document.addEventListener('DOMContentLoaded', function() {
	const bankSelect = document.getElementById('bankSelect');
	const clientAccount = document.getElementById('clientAccount');
	bankSelect.addEventListener('change', function() {
		clientAccount.value = "";
	});
	clientAccount.addEventListener('input', function() {
		formatAccount(this);
	});
});





// bankSelect 값 변경 시 maxlength 동적 설정
document.getElementById('bankSelect').addEventListener('change', function() {
	setMaxLength();
});

// 초기 maxlength 설정
setMaxLength();

// 입력 값 포맷팅
document.getElementById('clientAccount').addEventListener('input', function() {
	formatAccount(this);
});

function formatPhone(input) {
	let phoneNumber = input.value.replace(/[^0-9]/g, ''); // 입력된 값에서 숫자 이외의 문자 제거
	let formattedPhoneNumber = phoneNumber.replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, "$1-$2-$3"); // 3-4-4 형식으로 분할하고 각 부분을 하이픈으로 연결
	input.value = formattedPhoneNumber; // 변환된 값을 다시 input 요소에 할당하여 표시
}


function formatClientNumber(input) {
	let clientNumber = input.value.replace(/[^0-9]/g, ''); // 입력된 값에서 숫자 이외의 문자 제거
	let formattedClientNumber = clientNumber.replace(/^(\d{3})(\d{2})(\d{5})$/, "$1-$2-$3"); // 3-4-4 형식으로 분할하고 각 부분을 하이픈으로 연결
	input.value = formattedClientNumber; // 변환된 값을 다시 input 요소에 할당하여 표시
}

function duppleErrorCheck(stringValue) {
	console.log("중복 검사 함수 출력됨");
	let duplCheck = stringValue;

	console.log(duplCheck);

	if (duplCheck != null) {
		if (duplCheck === 'id') {
			alert('이미 사용중인 아이디입니다.');

		} else if (duplCheck === 'phone') {
			alert('이미 사용중인 전화번호입니다.');

		} else if (duplCheck === 'account') {
			alert('이미 사용중인 계좌번호입니다.');

		} else if (duplCheck === 'clientNumber') {
			alert('이미 사용중인 사업자번호입니다.');

		}
	}

}



function checkValues(htmlForm) {

	console.log("유효성 검사 함수 출력됨"); // htmlForm이 제대로 넘어갔는지 확인 용도


	let url = "/clientRegist?";

	let check = true;


	const clientId = document.getElementById("clientId").value;
	const clientPassword = document.getElementById("clientPassword").value;
	const clientName = document.getElementById("clientName").value;
	const clientPhone = document.getElementById("clientPhone").value;
	const clientAccount = document.getElementById("clientAccount").value;
	const clientNumber = document.getElementById("clientNumber").value;






	if (clientId !== "") {
		url += "clientId=" + clientId + "&";
	} if (clientPassword !== "") {
		url += "clientPassword=" + clientPassword + "&";
	} if (clientName !== "") {
		url += "clientName=" + clientName + "&";
	} if (clientPhone !== "") {
		url += "clientPhone=" + clientPhone + "&";
	} if (clientAccount !== "") {
		url += "clientAccount=" + clientAccount + "&";
	} if (clientNumber !== "") {
		url += "clientNumber=" + clientNumber + "&";
	}






	console.log(clientId);
	console.log(clientPassword);
	console.log(clientName);
	console.log(clientPhone);
	console.log(clientAccount);
	console.log(clientNumber);




	if (clientId === "") {
		alert('아이디가 입력되지 않았습니다');
		check = false;
	}
	else if (clientPassword === "") {
		alert('비밀번호가 입력되지 않았습니다');
		check = false;
	} else if (clientName === "") {
		alert('이름이 입력되지 않았습니다');
		check = false;
	} else if (clientPhone === "") {
		alert('핸드폰번호가 입력되지 않았습니다');
		check = false;
	} else if (clientAccount === "") {
		alert('계좌번호가 입력되지 않았습니다');
		check = false;
	}
	else if (clientNumber === "") {
		alert('사업자번호가 입력되지 않았습니다');
		check = false;
	}


	if (check === false) {

		location.href = url;

	} else {

		if (validateForm()) {
			alert('가입 시도중...');
			htmlForm.submit();
		} else {
			location.href = url;
		}
	}
	function validateForm() {
		// 이메일과 비밀번호 입력값 가져오기
		var id = document.forms["myForm"]["clientId"].value;
		var password = document.forms["myForm"]["clientPassword"].value;
		var name = document.forms["myForm"]["clientName"].value;
		var phone = document.forms["myForm"]["clientPhone"].value;
		var account = document.forms["myForm"]["clientAccount"].value;
		var number = document.forms["myForm"]["clientNumber"].value;
		console.log(phone);
		let check = true;
		// 이메일과 비밀번호가 정해진 길이보다 짧은지 검사
		if (id.length < 3) {
			alert("아이디가 짧습니다. (아이디 3자리 이상 입력)");
			check = false;
		} else if (password.length < 5) {
			alert("비밀번호가 짧습니다. (비밀번호 5자리 이상 입력)");
			check = false;
		} else if (name.length < 1) {
			alert("이름이 입력되지 않았습니다.");
			check = false;
		} else if (phone.length != 13) {
			alert("핸드폰번호가 짧습니다. (핸드폰번호 11자리)");
			check = false;
		} else if (number.length != 12) {
			alert("사업자번호를 다시 확인하세요.");
			check = false;
		} else if (account.length < 9) {
			alert("계좌번호는 정확한 폼으로 입력해주세요.");
			check = false;
		}
		return check;

	}

}

