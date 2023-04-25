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
			button.textContent = '결제완료';
			row.cells[7].textContent = formattedEndTime;
			alert(cost+"원이 결제 되었습니다.");
		},

		error: function(xhr, status, error) {
			console.log('실패:', error);
			alert("결제할 계좌의 잔액이 부족합니다.");
			window.location.href = "userMyPage"; // userMyPage로 이동

		}
	});
}
