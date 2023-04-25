function setMaxLength() {
	const bankSelect = document.getElementById('bankSelect').value;
	const userAccount = document.getElementById('userAccount');

	switch (bankSelect) {
		case 'kb':
			userAccount.maxLength = 9;
			break;
		case 'shinhan':
			userAccount.maxLength = 12;
			break;
		case 'woori':
			userAccount.maxLength = 14;
			break;
		case 'keb':
			userAccount.maxLength = 12;
			break;
		case 'nh':
			userAccount.maxLength = 11;
			break;
		default:
			userAccount.maxLength = 12;
	}
}


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

document.addEventListener('DOMContentLoaded', function() {
	const bankSelect = document.getElementById('bankSelect');
	const clientAccount = document.getElementById('userAccount');
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
document.getElementById('userAccount').addEventListener('input', function() {
	formatAccount(this);
});

function formatPhone(input) {
	let phoneNumber = input.value.replace(/[^0-9]/g, ''); // 입력된 값에서 숫자 이외의 문자 제거
	let formattedPhoneNumber = phoneNumber.replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, "$1-$2-$3"); // 3-4-4 형식으로 분할하고 각 부분을 하이픈으로 연결
	input.value = formattedPhoneNumber; // 변환된 값을 다시 input 요소에 할당하여 표시
}


function duppleErrorCheck(stringValue) {
	console.log("중복 검사 함수 출력됨");
	let duplCheck = stringValue;

	console.log(duplCheck);

	if (duplCheck !== null) {
		if (duplCheck === 'phone') {
			console.log("전번 체크 출력됨");
			alert('이미 사용중인 전화번호입니다.');

		} else if (duplCheck === 'account') {
			console.log("계좌 체크 출력됨");
			alert('사용 불가능한 계좌번호입니다.');

		}
	}

}



function checkValues(htmlForm) {

	console.log("유효성 검사 함수 출력됨"); // htmlForm이 제대로 넘어갔는지 확인 용도


	let url = "/userUpdate?";

	let check = true;


	const userId = document.getElementById("userId").value;
	const userPassword = document.getElementById("userPassword").value;
	const userName = document.getElementById("userName").value;
	const userPhone = document.getElementById("userPhone").value;
	const userAccount = document.getElementById("userAccount").value;





	if (userId !== "") {
		url += "userId=" + userId + "&";
	} if (userPassword !== "") {
		url += "userPassword=" + userPassword + "&";
	} if (userName !== "") {
		url += "userName=" + userName + "&";
	} if (userPhone !== "") {
		url += "userPhone=" + userPhone + "&";
	} if (userAccount !== "") {
		url += "userAccount=" + userAccount + "&";
	}




	console.log(userId);
	console.log(userPassword);
	console.log(userName);
	console.log(userPhone);
	console.log(userAccount);


	if (userPassword === "") {
		alert('비밀번호가 입력되지 않았습니다');
		check = false;
	} else if (userName === "") {
		alert('이름이 입력되지 않았습니다');
		check = false;
	} else if (userPhone === "") {
		alert('핸드폰번호가 입력되지 않았습니다');
		check = false;
	} else if (userAccount === "") {
		alert('계좌번호가 입력되지 않았습니다');
		check = false;
	}


	if (check === false) {
		console.log(url);
		location.href = url; // check가 false일 경우, 해당 URL로 이동
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
		var password = document.forms["myForm"]["userPassword"].value;
		var phone = document.forms["myForm"]["userPhone"].value;
		var account = document.forms["myForm"]["userAccount"].value;

		let check = true;
		// 이메일과 비밀번호가 정해진 길이보다 짧은지 검사
		if (password.length < 5) {
			alert("비밀번호가 짧습니다. (비밀번호 5자리 이상 입력)");
			check = false;
		} else if (phone.length != 13) {
			alert("핸드폰번호가 짧습니다. (핸드폰번호 11자리)");
			check = false;
		} else if (account.length < 9) {
			alert("계좌번호는 정확한 폼으로 입력해주세요.");
			check = false;
		}
		return check;

	}

}

