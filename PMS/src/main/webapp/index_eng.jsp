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
<title>PMS Parking Reservation System</title>
<jsp:include page="/header_eng"></jsp:include>
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
				complicated? <span>NO!</span> wandering? <span>NO!</span>
			</h1>
		</div>
		<div>
			<%
			String reservationDateParam = request.getParameter("reservationDate");
			String reservationTimeParam = request.getParameter("reservationTime");
			String addressInputParam = request.getParameter("addressInput");
			%>

			<form method="post" action="../service">
				<input type="hidden" name="command" value="preReservation_eng">
				<input type="date" id="reservationDate" name="reservationDate"
					placeholder="입차일자" value="<%=reservationDateParam != null ? reservationDateParam : new Timestamp(System.currentTimeMillis()).toString().split(" ")[0]%>" onchange="checkValue()"> 
					<select id="reservationTime" name="reservationTime">
					<option value="">Expected arrival time</option>
					<%
					for (Integer i = 0; i < 24; i++) {
					%>
					<%
					String hour = Integer.toString(i);
					%>
					<option value="<%=hour%>"><%=hour%> o'clock</option>
					<%
					}
					%>
				</select> <input type="text" id="addressInput" name="addressInput"
					placeholder="parking area"
					value="<%=addressInputParam != null ? addressInputParam : ""%>">
				<div>
					<button type="button" onclick="checkValues(form)">Search parking area</button>
				</div>
			</form>
		</div>
	</section>
</body>
<script>
	
</script>
<script type="text/javascript" src="resources_eng/mainPageValidation_eng.js"></script>
<jsp:include page="/footer_eng"></jsp:include>
</html>