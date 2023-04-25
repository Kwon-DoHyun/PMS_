<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/forHeader.css">
<title>join the membership</title>
</head>
<jsp:include page="/header_eng"></jsp:include>
<body onload="duppleErrorCheck('${requestScope.dupple}')">

	<section>

		<aside>
			<div>
				<h1>
					<span>PMS</span> System
				</h1>
				<h1>A new paradigm in parking</h1>
			</div>
		</aside>
		<div id="section-form">
			<div id="box-interface">
				<div id="select-menu">
					<div id="unselected">
						<a href="userLogin">login</a>
					</div>
					<div id="selected">
						<a href="userRegist">General registration</a>
					</div>
					<div id="unselected">
						<a href="clientRegist">Client registration</a>
					</div>
				</div>
				<div id="regist-form">
					<div>
						<form class="form-size" method="post" id="form" name="myForm" onsubmit="return validateFrom()" action="/service">
							<input type="hidden" name="command" value="UserRegist_eng">
							<div>
								<p>ID</p>
								<input type="text" name="userId" id="userId" maxlength="20"
									placeholder="  ID"
									value="${param.userId != null ? param.userId : ''}">
							</div>
							<div>
								<p>PASS</p>
								<input type="password" name="userPassword" id="userPassword"
									maxlength="20" placeholder="  password"
									value="${param.userPassword != null ? param.userPassword : ''}">
							</div>
							<div>
								<p>user Name</p>
								<input type="text" name="userName" id="userName" maxlength="10"
									placeholder="  userName"
									value="${param.userName != null ? param.userName : ''}">
							</div>
							<div>
								<p>PHONE</p>
								<input type="text" name="userPhone" id="userPhone"
									maxlength="13" placeholder="phone" oninput="formatPhone(this)"
									value="${param.userPhone != null ? param.userPhone : ''}">
							</div>
							<div>
								<p>ACCOUNT</p>
								<select id="bankSelect" name="bankSelect">
									<option value="">-- BAnk selected --</option>
									<option value="kb">KB BANK</option>
									<option value="shinhan">SH BANK</option>
									<option value="woori">WR BANK</option>
									<option value="keb">HN BANK</option>
									<option value="nh">NH BANK</option>
								</select> <input type="text" name="userAccount" id="userAccount"
									placeholder="account" oninput="formatAccount(this)"
									value=${userAccount != null ? "'"+userAccount+"'" : ""}>
							</div>

							<div>
								<input id="final" type="button" value="회원가입"
									onclick="checkValues(document.getElementById('form'))">
								<br> <br>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</section>


	<script src="../resources_eng/userRegist_eng.js"></script>
</body>
<jsp:include page="/footer_eng"></jsp:include>
</html>

