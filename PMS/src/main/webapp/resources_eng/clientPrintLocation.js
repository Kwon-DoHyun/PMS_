/**
 * 
 */

 
function getResultListByMonth(locationCode, clientId) {
    console.log('월단위 배열 받아오기 함수 내 진입');

    $.ajax({
        "url": `../service?command=clientGetResult&locationCode=${locationCode}&clientId=${clientId}&cmd=month`,
        "method": "GET",
        "timeout": 0,
    }).done(function(response) {
        // 결과 데이터를 리스트에 저장
        console.log(response.list);
        var resultList = Object.values(response.list);
        console.log('0값:' + resultList[0]);
        console.log('1값:' + resultList[1]);

        console.log('배열 받아오는거 성공');
        // 결과 데이터를 HTML에 추가
               $('#result-table').empty().append(tableHtml);
        var tableHtml = '<table><thead><tr><th>월</th><th>매출</th><th>완료건수</th></tr></thead><tbody></tbody></table>';
 
        $('#result-table').append(tableHtml);
        for (var i = 0; i < resultList.length; i++) {
            var result = resultList[i];
            console.log(i);
            var rowHtml = '<tr><td>' + result[0] + '</td><td>' +  result[1] +'</td><td>' +  result[2] + '</td></tr>';
            $('#result-table tbody').append(rowHtml);
        }
    });
}
function getResultListByDay(locationCode, clientId) {
    console.log('일단위 배열 받아오기 함수 내 진입');

    $.ajax({
        "url": `../service?command=clientGetResult&locationCode=${locationCode}&clientId=${clientId}&cmd=day`,
        "method": "GET",
        "timeout": 0,
    }).done(function(response) {
        // 결과 데이터를 리스트에 저장
        console.log(response.list);
        var resultList = Object.values(response.list);
        console.log('0값:' + resultList[0]);
        console.log('1값:' + resultList[1]);

        console.log('배열 받아오는거 성공');
        // 결과 데이터를 HTML에 추가
               $('#result-table').empty().append(tableHtml);
        var tableHtml = '<table><thead><tr><th>일</th><th>매출</th><th>완료건수</th></tr></thead><tbody></tbody></table>';
 
        $('#result-table').append(tableHtml);
        for (var i = 0; i < resultList.length; i++) {
            var result = resultList[i];
            console.log(i);
            var rowHtml = '<tr><td>' + result[0] + '</td><td>' +  result[1]+ '</td><td>' +  result[2] + '</td></tr>';
            $('#result-table tbody').append(rowHtml);
        }
    });
}




	
	
	
	

 
 
 