<%@page import="user.User"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="spot.Spot"%>
<%@page import="spot.controller.SpotDao"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="location.Location"%>
<%@page import="location.controller.LocationDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/forHeader.css">
<jsp:include page="/header"></jsp:include>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<title>비회원 예약</title>
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
		ArrayList<Spot> list2 = spotDao.getSelectSpotName(code, spot.getSpotName(), startTime);
		for (Spot spot2 : list2) {
		}
	}
	User user = (User) session.getAttribute("log");
	pageContext.setAttribute("user", user);
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
		<div id="section-form">
			<div id="box-interface">
				<div id="select-menu">
					<div id="selected">
						<a>회원 예약</a>
					</div>
					<div id="unselected">
						<a href="/">뒤로가기</a>
					</div>
				</div>
				<div id="reservation-form">
					<div>
						<form class="form-size" method="post" id="form" action="/service">
							<input type="hidden" id="locationCode" name="locationCode"
								readonly value="${location.locationCode}"> <input
								type="hidden" name="command" value="userBooking">
							<table>
								<tr>
									<td>아이디</td>
									<td><input type="text" name="userId" id="userId"
										placeholder="  아이디" readonly value="${user.userId}"></td>
								</tr>

								<tr>
									<td>예약자 성함</td>
									<td><input type="text" name="userName" id="userName"
										maxlength="10" placeholder="회원이름" readonly
										value="${user.userName}"></td>
								</tr>
								<tr>
									<td class="">주차장 이름</td>
									<td class=""><input type="text" id="locationName"
										name="locationName" readonly value="${location.locationName}"></td>
								</tr>
								<!--  주차층의 name=spot 은 DB의 spotName를 과 같은 값 -->
								<tr>
									<td class="">주차 구역(층)</td>
									<td class=""><select id="spot" name="spot">
											<option value="">층을 선택하세요</option>
											<c:forEach items="${list}" var="spot">
												<option value="${spot.spotName}">${spot.spotName}</option>
											</c:forEach>
									</select></td>
								</tr>
								<!--  주차구역의 spotCode는 DB의 spotCode를 의미 -->
								<tr>
									<td class="">주차 구역(자리)</td>
									<td><select id="spot-container" name="spotCode">
											<option value="">주차구역을 선택하세요</option>

									</select></td>
								</tr>
								<tr>
									<td class="">예약 일자</td>
									<td class=""><input type="text" id=regDate name="regDate"
										readonly value="<%=nowDate%>"></td>
								</tr>
								<tr>
									<td class="">사용일자</td>
									<td class=""><input type="text" id="resDate"
										name="resDate" readonly value="<%=reservationDateParam%>"></td>
								</tr>
								<tr>
									<td class="">입차 시간</td>
									<td class=""><input type="text" id="startTime"
										name="startTime" readonly value="<%=reservationTimeParam%>"></td>
								</tr>

								<tr>
									<td>핸드폰번호</td>
									<td><input type="text" name="userPhone" id="userPhone"
										maxlength="13" placeholder="  핸드폰번호"
										oninput="formatPhone(this)" readonly
										value="${user.userPhone != null ? user.userPhone : ''}"></td>
								</tr>

								<tr>
									<td>출금 예정 계좌</td>
									<td><input type="text" name="userAccount" id="userAccount"
										placeholder="  계좌번호" readonly
										value="${user.userAccount != null ? user.userAccount : ''}"></td>
								</tr>

								<tr>
									<td>차량 번호</td>
									<td><input type="text" name="carNumber" id="carNumber"
										placeholder="  차량번호를 입력하시오" value=""></td>
								</tr>

							</table>
							<input id="final" type="button" value="예약하기"
								onclick="checkValues(document.getElementById('form'))">
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>
	<script src="../resources/spotValue.js"></script>
	<jsp:include page="/footer"></jsp:include>
</body>
</html>