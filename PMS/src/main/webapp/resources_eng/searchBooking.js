function formatPhone(input) {
	let phoneNumber = input.value.replace(/[^0-9]/g, ''); // 입력된 값에서 숫자 이외의 문자 제거
	let formattedPhoneNumber = phoneNumber.replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, "$1-$2-$3"); // 3-4-4 형식으로 분할하고 각 부분을 하이픈으로 연결
	input.value = formattedPhoneNumber; // 변환된 값을 다시 input 요소에 할당하여 표시
}


function checkOnlyOne(element) {
	const authMethod = element.value;
	const carNumberDiv = document.querySelector('div:nth-child(3)');
	const phoneDiv = document.querySelector('div:nth-child(4)');
	const reservationNumberDiv = document.querySelector('div:nth-child(5)');

	switch (authMethod) {
		case '1':
			carNumberDiv.style.display = 'flex';
			phoneDiv.style.display = 'none';
			reservationNumberDiv.style.display = 'none';
			break;
		case '2':
			carNumberDiv.style.display = 'none';
			phoneDiv.style.display = 'flex';
			reservationNumberDiv.style.display = 'none';
			break;
		case '3':
			carNumberDiv.style.display = 'none';
			phoneDiv.style.display = 'none';
			reservationNumberDiv.style.display = 'flex';
			break;
		default:
			break;
	}
}



function searchBooking() {
	const carNumber = $('#carNumber').val();
	const phone = $('#phone').val();
	const reservationNumber = $('#reservationNumber').val();
	const password = $('#password').val();


	$.ajax({
		url: `../service?command=nonUserSearchBooking&carNumber=${carNumber}&phone=${phone}&reservationNumber=${reservationNumber}&password=${password}`, // 요청을 보낼 URL
		type: 'POST', // 요청 방식 (GET 또는 POST)
		data: { // 요청할 데이터
			carNumber: carNumber,
			phone: phone,
			reservationNumber: reservationNumber,
			password: password
		},
		success: function(response) { // 요청이 성공했을 때 호출될 함수 
			console.log('성공:', response);
			$('#nonUserSearchList').append(response); // 응답을 #search-big-box 태그 안에 삽입

		},
		error: function(xhr, status, error) { // 요청이 실패했을 때 호출될 함수
			alert('예약내역이 없습니다.');
			console.log('실패:', error);
			$('#noList').append(response); // 응답을 #search-big-box 태그 안에 삽입
		}
	});
}

