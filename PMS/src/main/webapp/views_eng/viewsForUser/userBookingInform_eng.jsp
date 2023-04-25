
<%@page import="java.util.Date"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="booking.Booking"%>
<%@page import="booking.controller.BookingDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="user.User"%>
<%@page import="client.Client"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<link rel="stylesheet" href="resources/myPage.css">
<title>Welcome to my place there.</title>
</head>
<jsp:include page="/header_eng"></jsp:include>
<body>
	<%
	DecimalFormat df = new DecimalFormat("#,###");
	User user = (User) session.getAttribute("log");
	pageContext.setAttribute("user", user);
	BookingDao bookingDao = BookingDao.getInstance();
	ArrayList<Booking> list = bookingDao.getBookingInfoAllById(user.getUserId());
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
		<div id="bookingInform">
			<div>
				<div id="myPage-menu">
					<div id="unselected">
						<a href="userMyPage_eng">my profile</a>
					</div>
					<div id="unselected">
						<a href="userUpdate_eng">edit profile</a>
					</div>
					<div id="selected">
						<a href="userBookingInform_eng">Reservation information</a>
					</div>
					<div id="unselected">
						<a type="button" href="/index_eng">go back</a>
					</div>
				</div>
				<div id="myPage-content" style="width: 100%;">
					<table id="myPage-table">
						<thead id="myPage-thead">
							<tr>
								<td style="width: 10%;">res_number</td>
								<td style="width: 10%;">name</td>
								<td style="width: 15%;">parking name</td>
								<td style="width: 10%;">parking lot</td>
								<td style="width: 10%;">area</td>
								<td style="width: 10%;">car's number</td>
								<td style="width: 10%;">entrance</td>
								<td style="width: 10%;">departure</td>
								<td style="width: 10%;">expense</td>
								<td style="width: 10%;">payment</td>

							</tr>
						</thead>
						<tbody id="myPage-tbody">
							<%
							for (int i = 0; i < list.size(); i++) {
							%>
							<tr id="bookingList">
								<td><%=list.get(i).getBookingCode()%></td>
								<td><%=list.get(i).getUserName()%></td>
								<td><%=list.get(i).getLocationName()%></td>
								<td><%=list.get(i).getLocationAddress()%></td>
								<td><%=list.get(i).getSpotName()%></td>
								<td><%=list.get(i).getCarNumber()%></td>
								<td><%=new SimpleDateFormat("yyyy-MM-dd HH:mm").format(list.get(i).getStartTime())%></td>

								<td><%=list.get(i).getEndTime() != null ? new SimpleDateFormat("yyyy-MM-dd HH:mm").format(list.get(i).getEndTime())
		: 							(list.get(i).getStartTime().after(new Date()) ? "non-enter" : "parking")%>
								</td>

								<td class="pay" id="<%=list.get(i).getBookingCode()%>">
									<%
									String formatted = df.format(list.get(i).getTotalCost());
									%><%=formatted%></td>
								<td>
									<button
										class="status<%=list.get(i).isCheckPayment() == false ? " incomplete" : " complete"%>"
										<%if (!list.get(i).isCheckPayment() && new Date().before(list.get(i).getStartTime())) {%>
										disabled <%}%>
										<%if (!list.get(i).isCheckPayment() && new Date().after(list.get(i).getStartTime())) {%>
										onclick="checkPayment(this)" <%}%>>
										<%=list.get(i).isCheckPayment() == false ? "incomplete" : "complete"%>
									</button>
								</td>
							</tr>
							<%
							}
							%>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</section>

</body>
<jsp:include page="/footer_eng"></jsp:include>
<script src="../resources_eng/userBookingInform_eng.js"></script>
</html>