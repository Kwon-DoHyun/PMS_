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
<jsp:include page="/header"></jsp:include>
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
				<h1>주차의 새로운 패러다임</h1>
			</div>
		</aside>
		<div id="myPage-form">
			<div>
				<div id="myPage-menu">
					<div id="unselected">
						<a href="userMyPage">내프로필</a>
					</div>
					<div id="selected">
						<a href="userUpdate">프로필 수정</a>
					</div>
					<div id="unselected">
						<a href="userBookingInform">예약정보</a>
					</div>
					<div id="unselected">
						<a type="button" href="/">뒤로가기</a>
					</div>
				</div>
				<div id="myPage-content" style="width: 100%;">
					<form method="post" id="form" name="myForm" onsubmit="return validateFrom()" action="/service">
						<input type="hidden" name="command" value="userUpdate">
						<table id="myPage-table">
							<thead id="myPage-thead">
								<tr>
									<td style="width: 10%;">아이디</td>
									<td style="width: 10%;">비밀번호</td>
									<td style="width: 10%;">이름</td>
									<td style="width: 10%;">핸드폰번호</td>
									<td style="width: 10%;">계좌번호</td>
									<td style="width: 10%;">가입일자</td>
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
									<option value="">-- 은행선택 --</option>
									<option value="kb">국민은행</option>
									<option value="shinhan">신한은행</option>
									<option value="woori">우리은행</option>
									<option value="keb">하나은행</option>
									<option value="nh">농협은행</option>
								</select> <input type="text" name="userAccount" id="userAccount"
									placeholder="계좌번호" oninput="formatAccount(this)"
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
								<button onclick="checkValues(document.getElementById('form'))">회원정보수정</button>
							</div>
							<div id="deleteBtn">
								<a id="as" href="userDelete">회원탈퇴</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
<script src="../resources/userUpdate.js"></script>
<jsp:include page="/footer"></jsp:include>
</html>