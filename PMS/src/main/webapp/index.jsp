<%@page import="java.sql.Timestamp"%>
<%@page import="board.Board"%>
<%@page import="board.controller.BoardDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="notice.controller.NoticeDao"%>
<%@page import="util.DBManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/mainPage.css">
<title>PMS 주차예약시스템</title>
<jsp:include page="/header"></jsp:include>
</head>
<body onload="errorCheck('${requestScope.modify}')">
	<section>
		<div>
			<h1>
				Convenience <span>Parking</span>
			</h1>

		</div>
		<div>
			<h1>
				복잡한 주차? <span>NO!</span> 헤매는 주차? <span>NO!</span>
			</h1>
		</div>
		<div>
			<%
			String reservationDateParam = request.getParameter("reservationDate");
			String reservationTimeParam = request.getParameter("reservationTime");
			String addressInputParam = request.getParameter("addressInput");
			%>

			<form method="post" action="../service">
				<input type="hidden" name="command" value="preReservation">
				<input type="date" id="reservationDate" name="reservationDate"
					placeholder="입차일자" value="<%=reservationDateParam != null ? reservationDateParam : new Timestamp(System.currentTimeMillis()).toString().split(" ")[0]%>" onchange="checkValue()"> 
					<select id="reservationTime" name="reservationTime">
					<option value="">입차 시간</option>
					<% String hour = "";%>
					<%
					for (Integer i = 0; i < 24; i++) {
					if(i < 10){
						hour = "0"+i;
					}else{
						hour = Integer.toString(i);
					}
					%>
					<option value="<%=hour%>"><%=hour%> 시</option>
					<%
					}
					%>
				</select> <input type="text" id="addressInput" name="addressInput"
					placeholder="주차장 검색"
					value="<%=addressInputParam != null ? addressInputParam : ""%>">
				<div>
					<button type="button" onclick="checkValues(form)">주차지역
						검색하기</button>
				</div>
			</form>
		</div>
	</section>
</body>
<script>
	
</script>
<script type="text/javascript" src="resources/mainPageValidation.js"></script>
<jsp:include page="/footer"></jsp:include>

</html>
