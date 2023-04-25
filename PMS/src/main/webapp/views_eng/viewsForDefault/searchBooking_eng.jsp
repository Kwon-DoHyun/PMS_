<%@page import="java.text.SimpleDateFormat"%>
<%@page import="nonUser.NonUser"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/forHeader.css">
<jsp:include page="/header_eng"></jsp:include>
<title>Insert title here</title>
</head>
<body>
	<%
ArrayList<NonUser> list = (ArrayList<NonUser>) request.getAttribute("list");
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:00");
%>
	<section>
		<aside>
			<div>
				<h1>
					<span>PMS</span> System
				</h1>
				<h1>A new paradigm in parking</h1>
			</div>
		</aside>
		<div id="section-form">
			<div>
				<h1>Non-member reservation inquiry</h1>
			</div>
			<div id="searchBooking">
				<form id="search-booking-form" method="post" action="../service">
					<input type="hidden" name="command" value="nonUserSearchBooking">
					<div id="search-form1">
						<div style="width: 20%;">authentication method</div>
						<div>
							<label>
							<input type="radio" name="auth-method" value="1"
								onclick="checkOnlyOne(this)"> CAR_NUMBER</label> <label><input
								type="radio" name="auth-method" value="2"
								onclick="checkOnlyOne(this)"> PHONE</label> <label><input
								type="radio" name="auth-method" value="3"
								onclick="checkOnlyOne(this)">Reservation number</label>
						</div>
					</div>
					<div id="search-form2" style="display: none;">
						<div style="width: 20%;">CAR_NUMBER</div>
						<div>
							<input type="text" id="carNumber" name="carNumber"
								placeholder="Please enter vehicle number.">
						</div>
					</div>
					<div id="search-form2" style="display: none;">
						<div style="width: 20%;">PHONE</div>
						<div>
							<input type="text" id="phone" name="phone"
								placeholder="Please enter your mobile phone number." maxlength="13"
								oninput="formatPhone(this)">
						</div>
					</div>
					<div id="search-form2" style="display: none;">
						<div style="width: 20%;">Reservation number</div>
						<div>
							<input type="text" id="reservationNumber"
								name="reservationNumber" placeholder="Please enter your reservation number.">
						</div>
					</div>
					<div id="search-form3">
						<div style="width: 20%;">password</div>
						<div>
							<input type="password" id="password" name="password"
								placeholder="Please enter a password.">
						</div>
					</div>
					<div id="search-form4">
						<!--  <button id="final-serachBooking" type="submit">예약조회</button>-->
						<button id="final-serachBooking" onclick="searchBooking()">Reservation inquiry</button>
					</div>
				</form>
			</div>
			<div>
				<div id="nonUserSearchList" style="<%=list == null ? "display:none;" : "display: block;" %>">
					<h1>User reservation list</h1>
					<table>
						<tr>
							<th>res_number</th>
							<th>parking_code</th>
							<th>aera_code</th>
							<th>CAR_NUMBER</th>
							<th>PASSWORD</th>
							<th>PHONE</th>
							<th>enter time</th>
							<th>out time</th>
							<th>expense</th>
						</tr>
						<c:forEach items="${list}" var="nonUser">
							<tr>
								<td>${nonUser.nonUserBookingCode}</td>
								<td>${nonUser.locationCode}</td>
								<td>${nonUser.spotCode}</td>
								<td>${nonUser.nonUserCar}</td>
								<td>${nonUser.nonUserPassword}</td>
								<td>${nonUser.nonUserPhone}</td>
								<td><fmt:formatDate value="${nonUser.startTime}"
										pattern="yyyy-MM-dd HH:00" /></td>
								<td><fmt:formatDate value="${nonUser.endTime}"
										pattern="yyyy-MM-dd HH:00" /></td>
								<td>${nonUser.totalCost}</td>
							</tr>
						</c:forEach>
					</table>
				</div>

			</div>
			<div id="search-div2">
				<div>
					<h1>Non-member verification information</h1>
				</div>
				<div>
					<div>If non-member authentication fails 5 times in a row, authentication cannot be performed for 1 hour..</div>
				</div>
			</div>
		</div>
	</section>
</body>
<script type="text/javascript" src="resources_eng/searchBooking_eng.js"></script>
<jsp:include page="/footer_eng"></jsp:include>
</html>