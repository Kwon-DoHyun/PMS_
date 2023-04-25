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
<title>Welcome to my place there.</title>
</head>
<jsp:include page="/header_eng"></jsp:include>
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
				<h1>A new paradigm in parking</h1>
			</div>
		</aside>
		<div id="myPage-form">
			<div>
				<div id="myPage-menu">
					<div id="selected">
						<a href="userMyPage_eng">my profile</a>
					</div>
					<div id="unselected">
						<a href="userUpdate_eng">edit profile</a>
					</div>
					<div id="unselected">
						<a href="userBookingInform_eng">Reservation info</a>
					</div>
					<div id="unselected">
						<a type="button" href="/index_eng">go back</a>
					</div>
				</div>
				<div id="myPage-content" style="width:100%;">
					<table id="myPage-table">
						<thead id="myPage-thead">
							<tr>
								<td style="width: 10%;">ID</td>
									<td style="width: 10%;">PASS</td>
									<td style="width: 10%;">NAME</td>
									<td style="width: 10%;">PHONE</td>
									<td style="width: 10%;">ACCOUNT</td>
									<td style="width: 10%;">balance</td>
									<td style="width: 10%;">charge</td>
									<td style="width: 10%;">Sign up date</td>
							</tr>
						</thead>
						<tbody id="myPage-tbody">
							<tr>
								<td>${user.userId}</td>
								<td>${user.userPassword != null ? user.userPassword : ''}</td>
								<td>${user.userName != null ? user.userName : ''}</td>
								<td>${user.userPhone != null ? user.userPhone : ''}</td>
								<td>${user.userAccount != null ? user.userAccount : ''}</td>
								<td class="credit"><%= credit > 0 ? df.format(credit) : "no balance"%></td>
								<td><button id="credit-btn" onclick="updateCredit(this)">charging</button></td>
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
<script src="../resources_eng/userMyPage_eng.js"></script>
<jsp:include page="/footer_eng"></jsp:include>
</html>