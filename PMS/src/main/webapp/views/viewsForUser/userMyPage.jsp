<%@page import="java.text.DecimalFormat"%>
<%@page import="userAccount.controller.UserAccountDao"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="user.User"%>
<%@page import="client.Client"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="resources/myPage.css">
<title>거긴 내자리에 오신걸 환영합니다.</title>
</head>
<jsp:include page="/header"></jsp:include>
<body  onload="errorCheck('${requestScope.modify}')">
	<%
	
	User user = (User) session.getAttribute("log");
	pageContext.setAttribute("user", user);
	DecimalFormat df = new DecimalFormat("#,###");
	UserAccountDao userAccountDao = UserAccountDao.getInstance();
	int credit = userAccountDao.getAccountBalance(user.getUserAccount());
	// 임시 잔액
	int creditTamp = credit;
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
		<div id="myPage-form">
			<div>
				<div id="myPage-menu">
					<div id="selected">
						<a href="userMyPage">내프로필</a>
					</div>
					<div id="unselected">
						<a href="userUpdate">프로필 수정</a>
					</div>
					<div id="unselected">
						<a href="userBookingInform">예약정보</a>
					</div>
					<div id="unselected">
						<a type="button" href="/">뒤로가기</a>
					</div>
				</div>
				<div id="myPage-content" style="width:100%;">
					<table id="myPage-table">
						<thead id="myPage-thead">
							<tr>
								<td style="width: 10%;">아이디</td>
									<td style="width: 10%;">비밀번호</td>
									<td style="width: 10%;">이름</td>
									<td style="width: 10%;">핸드폰번호</td>
									<td style="width: 10%;">계좌번호</td>
									<td style="width: 10%;">계좌잔액</td>
									<td style="width: 10%;">잔액충전</td>
									<td style="width: 10%;">가입일자</td>
							</tr>
						</thead>
						<tbody id="myPage-tbody">
							<tr>
								<td>${user.userId}</td>
								<td>${user.userPassword != null ? user.userPassword : ''}</td>
								<td>${user.userName != null ? user.userName : ''}</td>
								<td>${user.userPhone != null ? user.userPhone : ''}</td>
								<td>${user.userAccount != null ? user.userAccount : ''}</td>
								<td class="credit"><%= credit > 0 ? df.format(credit) : "잔액없음"%></td>
								<td><button id="credit-btn" onclick="updateCredit(this)">충전하기</button></td>
								<!--  <%
								SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
								String regDate = user.getRegDate() != null ? dateFormat.format(user.getRegDate()) : "";
								%>
								<td><%=regDate%></td> 
								-->
								
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</section>

</body>
<script src="../resources/userMyPage.js"></script>
<jsp:include page="/footer"></jsp:include>
</html>