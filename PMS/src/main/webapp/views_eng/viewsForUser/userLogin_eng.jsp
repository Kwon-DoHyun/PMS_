<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/forHeader.css">
<title>Insert title here</title>
<style>
input {
	font-size: 25px;
}
</style>
</head>
<jsp:include page="/header_eng"></jsp:include>
<body>
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
					<div id="selected">
						<a href="userLogin_eng">General member login</a>
					</div>
					<div id="unselected">
						<a href="clientLogin_eng">Business Member Login</a>
					</div>
					<div id="unselected">
						<a href="findPassword_eng">find password</a>
					</div>
				</div>
				<div id="regist-form">
					<div>
						<form class="form-size" method="post" id="form" action="/service">
							<input type="hidden" name="command" value="userLogin_eng">
							<div>
								<p>ID</p>
								<input type="text" name="userId" id="userId" placeholder="  ID"
									value="${param.userId != null ? param.userId : ''}">
							</div>
							<div>
								<p>PASSWORD</p>
								<input type="password" id="userPassword" name="userPassword"
									maxlength="20" placeholder="  PASWORD"
									value="${param.userPassword != null ? param.userPassword : ''}">
							</div>
							<div>
								<input id="final" type="button" value="LOGIN"
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
<jsp:include page="/footer_eng"></jsp:include>
<script src="../resources_eng/userLogin_eng.js"></script>
</html>