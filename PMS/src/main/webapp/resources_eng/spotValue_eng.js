

$('#spot').on('change', e => {
	const code = $('#locationCode').val();
	const spotName = $('#spot').val();
	const startTime = $('#startTime').val();
	
	$.ajax({
		"url": `../service?command=searchSpot&locationNumber=${code}&spotNames=${spotName}&startTime=${startTime}`,
		"method": "POST",
		"timeout": 0,
	}).done(function(list) {
		$('#spot-container').empty();

		list.forEach(spot => {
			const spotCode = spot.spotCode;
			const spotName = spot.spotName;

			$('#spot-container').append(`
				<option value="${spotCode}">${spotName}</option>
			`);
		});

	});

});

/*
function changeSpotCode() {
	const locationCode = $('#locationCode').val();
	const spotContainer = $('#spot-container').val();
	const startTime = $('#startTime').val();

	$.ajax({
		url: `../service?command=duplSpotBooking&locationCode=${locationCode}&spotContainer=${spotContainer}&startTime=${startTime}`, // 요청을 보낼 URL
		type: 'POST', 
		data: { 
			locationCode: locationCode,
			spotCode: spotContainer,
			startTime: startTime
		},
		success: function(response) { 
			console.log('성공:', response);
			
		},
		error: function(xhr, status, error) { 
			console.log('실패:', error);
			
		}
	});
}


$('#spot-container').on('change', e => {
	console.log("asdasd");
	changeSpotCode();
});
*/


function checkValues(htmlForm) {

	console.log("유효성 검사 함수 출력됨"); // htmlForm이 제대로 넘어갔는지 확인 용도
	let url = "/userBookingForm_eng?";
	let check = true;

	const carNumber = document.getElementById("carNumber").value;
	const spot = document.getElementById("spot").value;
	const spotContainer = document.getElementById("spot-container").value;
	const locationCode = document.getElementById("locationCode").value;
	const locationName = document.getElementById("locationName").value;

	if (carNumber !== "") {
		url += "carNumber=" + carNumber + "&";
	} if (locationCode !== "") {
		console.log("locationCode" + locationCode);
		url += "code=" + locationCode + "&";
	} if (locationName !== "") {
		url += "locationName=" + locationName + "&";
	}

	if (carNumber === "") {
		alert('Please enter vehicle number.');
		check = false;
	} else if (spot === "") {
		alert('Choose a parking level.');
		check = false;
	} else if (spotContainer === "") {
		alert('Choose a parking area.');
		check = false;
	}


	if (check === false) {
		location.href = url;

	} else {
		alert('Member reservation success');
		htmlForm.submit();

	}

}

function checkValues2(htmlForm) {

	console.log("유효성 검사 함수 출력됨"); // htmlForm이 제대로 넘어갔는지 확인 용도
	let url = "/nonUserBookingForm_eng?";
	let check = true;

	const carNumber = document.getElementById("carNumber").value;
	const spot = document.getElementById("spot").value;
	const spotContainer = document.getElementById("spot-container").value;
	const locationCode = document.getElementById("locationCode").value;
	const locationName = document.getElementById("locationName").value;
	const phone = document.getElementById("phone").value;
	const password = document.getElementById("password").value;

	if (carNumber !== "") {
		url += "carNumber=" + carNumber + "&";
	} if (locationCode !== "") {
		console.log("locationCode" + locationCode);
		url += "code=" + locationCode + "&";
	} if (locationName !== "") {
		url += "locationName=" + locationName + "&";
	} if (password !== "") {
		url += "password=" + password + "&";
	} if (phone !== "") {
		url += "phone=" + phone + "&";
	}

	if (carNumber === "") {
		alert('Please enter vehicle number.');
		check = false;
	} else if (spot === "") {
		alert('Choose a parking level.');
		check = false;
	} else if (spotContainer === "") {
		alert('Choose a parking area.');
		check = false;
	} else if (password === "") {
		alert('Enter your password for identification.');
		check = false;
	} else if (phone === "") {
		alert('Please enter your mobile phone number.');
		check = false;
	}


	if (check === false) {
		location.href = url;

	} else {
		alert('Non-member reservation successful');
		htmlForm.submit();

	}


}