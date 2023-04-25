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

function formatPhone(input, bank) {

	let phoneNumber = input.value.replace(/[^0-9]/g, '');
	let formattedPhoneNumber = '';
	let maxLength = 0;

	switch (bank) {
		case "kb":
			formattedPhoneNumber = phoneNumber.replace(/^(\d{4})(\d{4})(\d{4})$/, "$1-$2-$3");
			maxLength = 12;
			break;
		case "shinhan":
			formattedPhoneNumber = phoneNumber.replace(/^(\d{3})(\d{4})(\d{4})$/, "$1-$2-$3");
			maxLength = 11;
			break;
		case "woori":
			formattedPhoneNumber = phoneNumber.replace(/^(\d{1})(\d{1})(\d{1})$/, "$1-$2-$3");
			maxLength = 3;
			break;
		default:
			formattedPhoneNumber = phoneNumber.replace(/^(\d{3})(\d{3,4})(\d{4})$/, "$1-$2-$3");
			maxLength = 13;
			break;
	}

	input.value = formattedPhoneNumber;
	input.maxLength = maxLength;
}





function formatPhone(input) {
	let phoneNumber = input.value.replace(/[^0-9]/g, ''); // 입력된 값에서 숫자 이외의 문자 제거
	let formattedPhoneNumber = phoneNumber.replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, "$1-$2-$3"); // 3-4-4 형식으로 분할하고 각 부분을 하이픈으로 연결
	input.value = formattedPhoneNumber; // 변환된 값을 다시 input 요소에 할당하여 표시
}

function duppleErrorCheck(stringValue) {
	console.log("중복 검사 함수 출력됨");
	let duplCheck = stringValue;

	console.log(duplCheck);
	console.log(userId);
	console.log(userPassword);
	console.log(userName);
	console.log(userPhone);
	console.log(userAccount);


	if (duplCheck != null) {
		if (duplCheck === 'id') {
			alert('This ID is already taken.');

		} else if (duplCheck === 'phone') {
			alert('This phone number is already in use.');

		} else if (duplCheck === 'account') {
			alert('This account number is already in use.');

		}
	}

}



function checkValues(htmlForm) {

	console.log("유효성 검사 함수 출력됨"); // htmlForm이 제대로 넘어갔는지 확인 용도


	let url = "/userRegist_eng?";

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

	console.log(url);

	if (userId === "") {
		alert('ID not entered');
		check = false;
	}
	else if (userPassword === "") {
		alert('The password has not been entered');
		check = false;
	} else if (userName === "") {
		alert('No name entered');
		check = false;
	} else if (userPhone === "") {
		alert('Cell phone number not entered');
		check = false;
	} else if (userAccount === "") {
		alert('Account number not entered');
		check = false;
	}


	if (check === false) {
		location.href = url;
	} else {

		if (validateForm()) {
			alert('join...');
			htmlForm.submit();
		} else {
			location.href = url;
		}
	}
	function validateForm() {
		// 이메일과 비밀번호 입력값 가져오기
		var id = document.forms["myForm"]["userId"].value;
		var password = document.forms["myForm"]["userPassword"].value;
		var name = document.forms["myForm"]["userName"].value;
		var phone = document.forms["myForm"]["userPhone"].value;
		var account = document.forms["myForm"]["userAccount"].value;

		let check = true;
		// 이메일과 비밀번호가 정해진 길이보다 짧은지 검사
		if (id.length < 3) {
			alert("ID is short. (Enter 3 or more digits of ID)");
			check = false;
		} else if (password.length < 5) {
			alert("Your password is short. (Enter a password of at least 5 digits)");
			check = false;
		} else if (name.length < 1) {
			alert("No name entered.");
			check = false;
		} else if (phone.length != 13) {
			alert("Cell phone number is short. (11 digit mobile phone number)");
			check = false;
		} else if (account.length < 9) {
			alert("Please enter the account number in the correct form.");
			check = false;
		}
			return check;
		
	}

}

