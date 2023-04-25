
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
<title>거긴 내자리에 오신걸 환영합니다.</title>
</head>
<jsp:include page="/header"></jsp:include>
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
				<h1>주차의 새로운 패러다임</h1>
			</div>
		</aside>
		<div id="bookingInform">
			<div>
				<div id="myPage-menu">
					<div id="unselected">
						<a href="userMyPage">내프로필</a>
					</div>
					<div id="unselected">
						<a href="userUpdate">프로필 수정</a>
					</div>
					<div id="selected">
						<a href="userBookingInform">예약정보</a>
					</div>
					<div id="unselected">
						<a type="button" href="/">뒤로가기</a>
					</div>
				</div>
				<div id="myPage-content" style="width: 100%;">
					<table id="myPage-table">
						<thead id="myPage-thead">
							<tr>
								<td style="width: 10%;">예약번호</td>
								<td style="width: 10%;">예약자</td>
								<td style="width: 15%;">주차장</td>
								<td style="width: 10%;">주차장 위치</td>
								<td style="width: 10%;">주차구역</td>
								<td style="width: 10%;">차량번호</td>
								<td style="width: 10%;">입차시간</td>
								<td style="width: 10%;">출차시간</td>
								<td style="width: 10%;">비용</td>
								<td style="width: 10%;">결제</td>

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
		: (list.get(i).getStartTime().after(new Date()) ? "입차전" : "주차중")%>
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
										<%=list.get(i).isCheckPayment() == false ? "결제하기" : "결제완료"%>
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
<jsp:include page="/footer"></jsp:include>
<script src="../resources/userBookingInform.js"></script>
</html>