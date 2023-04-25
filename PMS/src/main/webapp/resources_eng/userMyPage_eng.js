/**
 * 
 */

function errorCheck(stringValue) {
	console.log("메세지 검사 함수 출력됨");
	let inputString = stringValue;

	console.log("dd:" + inputString);

	if (inputString !== null) {
		if (inputString === 'sucess') {

			console.log("완료 문구 출력됨");
			alert('Personal information change was successful.');

		}
	}
}


function updateCredit(button) {

	const row = button.closest('tr');
	const userAccount = row.cells[4].textContent;
	let credit = row.cells[5].textContent;
	console.log("credit:"+credit);
	if(credit === '잔액없음'){
		credit = 0;
	}
	console.log("credit:"+credit);
	const plusCredit = prompt("Enter the amount to recharge.", "");
	$.ajax({
		url: `../service?command=updateCredit&userAccount=${userAccount}&credit=${credit}&plusCredit=${plusCredit}`,
		type: "POST",
		data: {
			userAccount: userAccount,
			credit: credit,
			plusCredit: plusCredit
		},
		success: function(response) {
			alert("Your account balance has been replenished.");
			console.log(response);
			response = response.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
			$('.credit')[0].innerText = response;
		},
		error: function() {
			alert("Failed to top up account balance.");
		}
	});
}

