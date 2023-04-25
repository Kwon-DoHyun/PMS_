<%@page import="nonUser.NonUser"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/forHeader.css">
<jsp:include page="/header"></jsp:include>
<title>Insert title here</title>
</head>
<body>
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
					<input type="hidden" name="command" value="searchBooking">
					<div id="search-form1">
						<div style="width: 20%;">인증방법</div>
						<div>
							<label><input type="radio" name="auth-method" value="1"
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
						<button id="final-serachBooking" type="submit">예약조회</button>
					</div>
				</form>
			</div>
			<div id="search-big-box">
				<div id="search-box">
					<a type="button" href="/userLogin"><button class="no-style">로그인</button></a>
					<a id="search-box-line" type="button" href="/findPassword"><button
							class="no-style">비밀번호 찾기</button></a> <a type="button"
						href="/userRegist"><button class="no-style">회원가입</button></a>
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
<jsp:include page="/footer"></jsp:include>
</html>