
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	mapOption = {
		center: new kakao.maps.LatLng(33.450701,
			126.570667), // 지도의 중심좌표
		level: 3
		// 지도의 확대 레벨
	};

// 지도를 생성합니다    
var map = new kakao.maps.Map(mapContainer, mapOption);

// 주소-좌표 변환 객체를 생성합니다 
var geocoder = new kakao.maps.services.Geocoder();


function searchAddress(locationAddress, addressName) {
	var address = locationAddress; // 클릭한 위치의 주소를 가져옵니다

	// 주소-좌표 변환 객체를 생성합니다
	var geocoder = new kakao.maps.services.Geocoder();

	// 주소로 좌표를 검색합니다
	geocoder.addressSearch(address, function(result, status) {

		// 정상적으로 검색이 완료됐으면 
		if (status === kakao.maps.services.Status.OK) {

			var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

			// 결과값으로 받은 위치를 마커로 표시합니다
			var marker = new kakao.maps.Marker({
				map: map,
				position: coords
			});

			// 인포윈도우로 장소에 대한 설명을 표시합니다
			var infowindow = new kakao.maps.InfoWindow({
				content: '<div style="width:150px;text-align:center;padding:6px 0;">' + addressName + '</div>'
			});
			infowindow.open(map, marker);

			// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
			map.setCenter(coords);


			/*location.href = "locationList?=" + addressName;*/

			// location 객체를 JSONOject 형태로 반환하는 SearchLocationAction.java 
			// 를 만들고, 자바스크립트에서 주차장 목록 클릭이 발생하면 -> 비동기로 (ajax)
			// 페이지 갱신 없이 -> 테이블 내용만 업데이트 

			updateLocationData(addressName);
			 sessionStorage.setItem('selectedLocation', addressName);

		} else {
			alert('검색에 실패했습니다.');
		}
	});
}
let loName = "";
let code = -1;
function updateLocationData(keyword) {

	$.ajax({
		"url": `../service?command=searchLocation&addressName=${keyword}`,
		"method": "GET",
		"timeout": 0,
	}).done(function(response) {
		$('#location-name').text(response.name);
		$('#location-code').text(response.code);
		loName = response.name;
		code = response.code;
		console.log(code);
		$('#location-address').text(response.address);
		$('#current-count').text((response.maxAreaCount-response.currentAreaCount)+"/"+response.maxAreaCount);
		//$('#disabled').text(response.name);
	});
	return loName;
}

console.log(loName);
function checkValue() {
if (!loName) {
    alert('주차장을 선택해주세요.');
    window.location.href ="locationList";
  } else {
    // 주차장 선택이 된 경우, 해당 주차장의 예약 페이지로 이동
    window.location.href = 'nonUserBookingForm?locationName=' + encodeURIComponent(loName)+'&code=' + encodeURIComponent(code);
  }
}

function checkValue2() {
if (!loName) {
    alert('주차장을 선택해주세요.');
    window.location.href ="locationList";
  } else {
    // 주차장 선택이 된 경우, 해당 주차장의 예약 페이지로 이동
    window.location.href = 'userBookingForm?locationName=' + encodeURIComponent(loName)+'&code=' + encodeURIComponent(code);
  }
}

