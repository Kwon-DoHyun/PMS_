function checkPayment(button) {
	// 해당 버튼이 속한 tr 요소의 각 셀 값을 가져오기
	const row = button.closest('tr');
	const bookingCode = row.cells[0].textContent;
	const startTime = row.cells[6].textContent;
	const totalCost = row.cells[8].textContent;

	$.ajax({
		url: `../service?command=checkPayment&bookingCode=${bookingCode}&totalCost=${totalCost}&startTime=${startTime}`, // 요청을 보낼 URL
		type: 'POST',
		data: {
			bookingCode: bookingCode,
			totalCost: totalCost,
			startTime: startTime
		},
		success: function(response) {
			console.log("response:"+response);
			let [cost, date, time] = response.trim().split(' '); // 일자,시간으로 나눠버림
			cost = cost.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");

			let formattedEndTime = date + ' ' + time.split(':').slice(0, 2).join(':'); 
			$('#' + bookingCode).text(cost);

			button.classList.remove('incomplete');
			button.classList.add('complete');
			button.textContent = 'complete';
			row.cells[7].textContent = formattedEndTime;
			alert(cost+"won has been paid.");
		},

		error: function(xhr, status, error) {
			console.log('실패:', error);
			alert("The balance of the account to be paid is insufficient.");
			window.location.href = "userMyPage_eng"; // userMyPage로 이동

		}
	});
}
