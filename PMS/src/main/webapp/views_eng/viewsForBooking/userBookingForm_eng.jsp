<%@page import="booking.Booking"%>
<%@page import="booking.controller.BookingDao"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="spot.Spot"%>
<%@page import="java.util.ArrayList"%>
<%@page import="spot.controller.SpotDao"%>
<%@page import="location.Location"%>

<%@page import="location.controller.LocationDao"%>
<%@page import="user.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<%
	request.setCharacterEncoding("UTF-8");
	String selectedLocation = request.getParameter("locationName");
	
	int code = Integer.parseInt(request.getParameter("code"));
	
	String reservationDateParam = (String) session.getAttribute("reservationDate");
	String reservationTimeParam = (String) session.getAttribute("reservationTime");
	
	String startTime = reservationTimeParam;
	Timestamp now = new Timestamp(System.currentTimeMillis());
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	String nowDate = sdf.format(now);
	// 가져온 주차장이름으로 주차장 객체 가져오기
	// 객체에서 코드 받아오기
	LocationDao locationDao = LocationDao.getInstance();
	Location location = locationDao.getLocationByName(selectedLocation);
	
	SpotDao spotDao = SpotDao.getInstance();
	ArrayList<Spot> list = spotDao.getSelectSpot(code);
	pageContext.setAttribute("location", location);
	pageContext.setAttribute("list", list);
	
	for (Spot spot : list) {
		ArrayList<Spot> list2 = spotDao.getSelectSpotName(code, spot.getSpotName(),startTime);
		for (Spot spot2 : list2) {
		}
	}
	User user = (User) session.getAttribute("log");
	pageContext.setAttribute("user", user);
	%>


	<section>
		<form class="form-size" method="post" id="form" action="/service">
			<input type="hidden" id="locationCode" name="locationCode" readonly value="${location.locationCode}">
			<input type="hidden" name="command" value="userBooking_eng">
			<table>
				<tr>
					<td>ID</td>
					<td><input type="text" name="userId" id="userId"
						placeholder="  ID" readonly value="${user.userId}"></td>
				</tr>

				<tr>
					<td>name</td>
					<td><input type="text" name="userName" id="userName"
						maxlength="10" placeholder="name" readonly value="${user.userName}"></td>
				</tr>
				<tr>
					<td class="">parking lot name</td>
					<td class=""><input type="text" id="locationName"
						name="locationName" readonly value="${location.locationName}"></td>
				</tr>
				<!--  주차층의 name=spot 은 DB의 spotName를 과 같은 값 -->
				<tr>
					<td class="">Parking area (floor)</td>
					<td class=""><select id="spot" name="spot" >
							<option value="">Choose a floor</option>
							<c:forEach items="${list}" var="spot">
								<option value="${spot.spotName}">${spot.spotName}</option>
							</c:forEach>
					</select></td>
				</tr>
				<!--  주차구역의 spotCode는 DB의 spotCode를 의미 -->
				<tr>
					<td class="">Parking area (seat)</td>
					<td><select id="spot-container" name="spotCode" >
							<option value="">Choose a parking area</option>
						
					</select></td>
				</tr>
				<tr>
					<td class="">reservation date</td>
					<td class=""><input type="text" id=regDate name="regDate"
						readonly value="<%=nowDate%>"></td>
				</tr>
				<tr>
					<td class="">start_date</td>
					<td class=""><input type="text" id="resDate" name="resDate"
						readonly value="<%=reservationDateParam%>"></td>
				</tr>
				<tr>
					<td class="">start_time</td>
					<td class=""><input type="text" id="startTime"
						name="startTime" readonly value="<%=reservationTimeParam%>"></td>
				</tr>

				<tr>
					<td>phone</td>
					<td><input type="text" name="userPhone" id="userPhone"
						maxlength="13" placeholder="  phone" oninput="formatPhone(this)"
						readonly value="${user.userPhone != null ? user.userPhone : ''}"></td>
				</tr>

				<tr>
					<td>account</td>
					<td><input type="text" name="userAccount" id="userAccount"
						placeholder="  account"
						readonly value="${user.userAccount != null ? user.userAccount : ''}"></td>
				</tr>
				
				<tr>
					<td>car_number</td>
					<td><input type="text" name="carNumber" id="carNumber"
						placeholder="  car_number"
						 value=""></td>
				</tr>

			</table>
			<input id="final" type="button" value="reservation"
				onclick="checkValues(document.getElementById('form'))">
		</form>

	</section>
<script src="../resources_eng/spotValue_eng.js"></script>
</body>
</html>