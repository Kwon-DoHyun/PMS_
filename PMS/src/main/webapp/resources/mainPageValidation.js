function checkValues(htmlForm) {
	let url = "/?";

	const reservationDate = document.getElementById("reservationDate").value;
	const reservationTime = document.getElementById("reservationTime").value;
	const addressInput = document.getElementById("addressInput").value;


	if (reservationDate !== "") {
		url += "reservationDate=" + reservationDate + "&";
	}
	if (reservationTime !== "") {
		url += "reservationTime=" + reservationTime + "&";
	}
	if (addressInput !== "") {
		url += "addressInput=" + addressInput + "&";
	}

	let check = true;

	if (reservationDate === "") {
		alert('예약일자가 선택되지 않았습니다.')
		check = false;
	} else if (reservationTime === "") {
		alert('예약시간이 선택되지 않았습니다.')
		check = false;
	} else if (addressInput === "") {
		alert('주차지역이 입력되지 않았습니다.')
		check = false;
	}
	if (check === false) {
		location.href = url;
	} else {
		
		htmlForm.submit();
	}

}

function checkValue() {
    const reservationDateInput = document.getElementById("reservationDate");
    const reservationDateValue = reservationDateInput.value;
    const today = new Date().getTime();
    const selectedDate = new Date(reservationDateValue).getTime();
	const maxDate = today + 7 * 24 * 60 * 60 * 1000; // 최대 7일 안쪽으로에서 예약가능
   console.log("입차:"+selectedDate);
    console.log("today+7일:"+maxDate);
    if (selectedDate < today || maxDate < selectedDate) {
        reservationDateInput.value = new Date().toISOString().split("T")[0];
        alert("예약이 불가능한 날짜입니다.");
    }
}

 function errorCheck(stringValue) {
	console.log("메세지 검사 함수 출력됨");
	let inputString = stringValue;

	console.log("코드:"+inputString);

	if (inputString !== null) {
		 if (inputString === 'deleteUser') {

			console.log("삭제 완료 문구 출력됨");
			alert('유저 탈퇴가 성공적으로 처리되었습니다.');

		}else if (inputString === 'deleteClient') {

			console.log("삭제 완료 문구 출력됨");
			alert('사업자 탈퇴가 성공적으로 처리되었습니다.');

		}
	} 
}