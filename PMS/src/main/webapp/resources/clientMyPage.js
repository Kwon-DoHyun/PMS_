/**
 * 
 */
/*
업데이트가 정상으로 돌아오면 받아서 알러트 띄우는 용도.
*/
function errorCheck(stringValue) {
	console.log("메세지 검사 함수 출력됨");
	let inputString = stringValue;

	console.log(inputString);

	if (inputString !== null) {
		if (inputString === 'sucess') {

			console.log("완료 문구 출력됨");
			alert('개인정보 변경이 성공했습니다.');

		} 



	}
}
