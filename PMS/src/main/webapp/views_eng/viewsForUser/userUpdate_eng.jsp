<%@page import="user.User"%>
<%@page import="user.controller.UserDao"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/myPage.css">
</head>
<jsp:include page="/header_eng"></jsp:include>
<body onload="duppleErrorCheck('${requestScope.dupple}')">

	<%
	UserDao clientDao = UserDao.getInstance();
	String userId = request.getParameter("clientId");
	User user = clientDao.findUserById(userId);

	if (user == null) {
		System.out.println("일반회원의 정보 변경");

		user = (User) session.getAttribute("log");

	}
	pageContext.setAttribute("user", user);
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
					<div id="unselected">
						<a href="userMyPage_eng">my profile</a>
					</div>
					<div id="selected">
						<a href="userUpdate_eng">edit profile</a>
					</div>
					<div id="unselected">
						<a href="userBookingInform_eng">Reservation info</a>
					</div>
					<div id="unselected">
						<a type="button" href="/index_eng">go back</a>
					</div>
				</div>
				<div id="myPage-content" style="width: 100%;">
					<form method="post" id="form" name="myForm" onsubmit="return validateFrom()" action="/service">
						<input type="hidden" name="command" value="userUpdate">
						<table id="myPage-table">
							<thead id="myPage-thead">
								<tr>
									<td style="width: 10%;">ID</td>
									<td style="width: 10%;">PASS</td>
									<td style="width: 10%;">NAME</td>
									<td style="width: 10%;">PHONE</td>
									<td style="width: 10%;">ACCOUNT</td>
									<td style="width: 10%;">Sign up date</td>
								</tr>
							</thead>

							<tbody id="myPage-tbody">
								<tr>
									<td><input class="disable" id="userId" name="userId"
										value="${user.userId}" readonly></td>
									<td><input type="password" name="userPassword"
										id="userPassword" maxlength="20"
										value="${user.userPassword != null ? user.userPassword : ''}">
									</td>
									<td><input class="disable" id="userName" name="userName"
										value="${user.userName != null ? user.userName : ''}" readonly></td>
									<td><input type="text" name="userPhone" id="userPhone"
										oninput="formatPhone(this)" maxlength="13"
										value="${user.userPhone != null ? user.userPhone : ''}"></td>
									<td>
									<select id="bankSelect" name="bankSelect">
									<option value="">-- bank selection --</option>
									<option value="kb">KB BANK</option>
									<option value="shinhan">SH BANK</option>
									<option value="woori">WR BANK</option>
									<option value="keb">HN BANK</option>
									<option value="nh">NH BANK</option>
								</select> <input type="text" name="userAccount" id="userAccount"
									placeholder="your Account" oninput="formatAccount(this)"
									value=${userAccount != null ? "'"+userAccount+"'" : ""}>
									</td>
									<%
									SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
									String regDate = user.getRegDate() != null ? dateFormat.format(user.getRegDate()) : "";
									%>
									<td><%=regDate%></td>
								</tr>
							</tbody>
						</table>

					</form>
					<div id="btn-menu">
						<div>
							<div id="modifyBtn">
								<button onclick="checkValues(document.getElementById('form'))">Update INFO</button>
							</div>
							<div id="deleteBtn">
								<a id="as" href="userDelete">Withdrawal</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
<script src="../resources_eng/userUpdate_eng.js"></script>
<jsp:include page="/footer_eng"></jsp:include>
</html>