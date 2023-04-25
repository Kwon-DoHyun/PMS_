<%@page import="user.User"%>
<%@page import="client.Client"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/forHeader.css">
<title>Insert title here</title>
<jsp:include page="/header"></jsp:include>
</head>
<body onload="duppleErrorCheck('${requestScope.errorCode}')">
	<%
	User user = (User) session.getAttribute("log");

	pageContext.setAttribute("user", user);

	if (user == null) {

		response.sendRedirect("/userLogin");
	}
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
						<a href="userDelete">회원탈퇴</a>
					</div>
					<div id="unselected">
						<a href="userMyPage">취소</a>
					</div>
				</div>
				<div id="regist-form">
					<div>
						<form class="form-size" method="post" id="form" action="/service">
							<input type="hidden" name="command" value="userDelete">
							<div>
								<p>아이디</p>
								<input type="text" name="userId" id="userId"
									readonly="readonly" value="${user.userId}">
							</div>
							<div>
								<p>비밀번호</p>
								<input type="password" name="userPassword" id="userPassword">
							</div>
							<div>
								<p>비밀번호(재입력)</p>
								<input type="password" name="checkPassword" id="checkPassword">
							</div>
							<div>
								<input id="final" type="button" value="탈퇴하기"
									onclick="checkValues(document.getElementById('form'))">
								<br> <br>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
<script src="../resources/userDelete.js"></script>
<jsp:include page="/footer"></jsp:include>
</html>