function checkValues(htmlForm) {
	let url = "/index_eng?";

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
		alert('Reservation date not selected.')
		check = false;
	} else if (reservationTime === "") {
		alert('Reservation time not selected.')
		check = false;
	} else if (addressInput === "") {
		alert('Parking area not entered.')
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
        alert("This date is not available for reservation.");
    }
}

 function errorCheck(stringValue) {
	console.log("메세지 검사 함수 출력됨");
	let inputString = stringValue;

	console.log("코드:"+inputString);

	if (inputString !== null) {
		 if (inputString === 'deleteUser') {

			console.log("삭제 완료 문구 출력됨");
			alert('User withdrawal has been successfully processed.');

		}else if (inputString === 'deleteClient') {

			console.log("삭제 완료 문구 출력됨");
			alert('Client withdrawal has been successfully processed.');

		}
	} 
}