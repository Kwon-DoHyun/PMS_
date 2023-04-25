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
<jsp:include page="/header"></jsp:include>
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
				<h1>주차의 새로운 패러다임</h1>
			</div>
		</aside>
		<div id="section-form">
			<div>
				<h1>비회원 예약 조회</h1>
			</div>
			<div id="searchBooking">
				<form id="search-booking-form" method="post" action="../service">
					<input type="hidden" name="command" value="nonUserSearchBooking">
					<div id="search-form1">
						<div style="width: 20%;">인증방법</div>
						<div>
							<label>
							<input type="radio" name="auth-method" value="1"
								onclick="checkOnlyOne(this)"> 차량번호</label> <label><input
								type="radio" name="auth-method" value="2"
								onclick="checkOnlyOne(this)"> 핸드폰번호</label> <label><input
								type="radio" name="auth-method" value="3"
								onclick="checkOnlyOne(this)"> 예약번호</label>
						</div>
					</div>
					<div id="search-form2" style="display: none;">
						<div style="width: 20%;">차량번호</div>
						<div>
							<input type="text" id="carNumber" name="carNumber"
								placeholder="차량번호를 입력하세요.">
						</div>
					</div>
					<div id="search-form2" style="display: none;">
						<div style="width: 20%;">핸드폰번호</div>
						<div>
							<input type="text" id="phone" name="phone"
								placeholder="핸드폰번호를 입력하세요." maxlength="13"
								oninput="formatPhone(this)">
						</div>
					</div>
					<div id="search-form2" style="display: none;">
						<div style="width: 20%;">예약번호</div>
						<div>
							<input type="text" id="reservationNumber"
								name="reservationNumber" placeholder="예약번호를 입력하세요.">
						</div>
					</div>
					<div id="search-form3">
						<div style="width: 20%;">비밀번호</div>
						<div>
							<input type="password" id="password" name="password"
								placeholder="비밀번호를 입력하세요.">
						</div>
					</div>
					<div id="search-form4">
						<!--  <button id="final-serachBooking" type="submit">예약조회</button>-->
						<button id="final-searchBooking" onclick="searchBooking()">예약조회</button>
					</div>
				</form>
			</div>
			<div>
				<div id="nonUserSearchList" style="<%=list == null ? "display:none;" : "display: block;" %>">
					<h1>비회원 예약조회 결과</h1>
					<table id="nonUserBookingTable">
						<tr>
							<th>예약번호</th>
							<th>주차장코드</th>
							<th>구역코드</th>
							<th>차량번호</th>
							<th>비밀번호</th>
							<th>전화번호</th>
							<th>입차시간</th>
							<th>출차시간</th>
							<th>비용</th>
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
					<h1>비회원 인증 안내</h1>
				</div>
				<div>
					<div>비회원 인증 연속 5회 실패 시 1시간 동안 인증을 하실 수 없습니다.</div>
				</div>
			</div>
		</div>
	</section>
</body>
<script type="text/javascript" src="resources/searchBooking.js"></script>
<jsp:include page="/footer"></jsp:include>
</html>