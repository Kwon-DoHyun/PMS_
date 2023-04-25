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
	Client client = (Client) session.getAttribute("log");

	pageContext.setAttribute("client", client);

	if (client == null) {

		response.sendRedirect("/clientLogin");
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
						<a href="clientDelete">회원탈퇴</a>
					</div>
					<div id="unselected">
						<a href="clientMyPage">취소</a>
					</div>
				</div>
				<div id="regist-form">
					<div>
						<form class="form-size" method="post" id="form" action="/service">
							<input type="hidden" name="command" value="clientDelete">
							<div>
								<p>아이디</p>
								<input type="text" name="clientId" id="clientId"
									readonly="readonly" value="${client.clientId}">
							</div>
							<div>
								<p>비밀번호</p>
								<input type="password" name="clientPassword" id="clientPassword">
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
<script src="../resources/clientDelete.js"></script>
<jsp:include page="/footer"></jsp:include>
</html>