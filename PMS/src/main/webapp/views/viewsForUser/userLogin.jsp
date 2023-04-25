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
<jsp:include page="/header"></jsp:include>
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
			<div id="box-interface">
				<div id="select-menu">
					<div id="selected">
						<a href="userLogin">일반회원 로그인</a>
					</div>
					<div id="unselected">
						<a href="clientLogin">사업자회원 로그인</a>
					</div>
					<div id="unselected">
						<a href="findPassword">비밀번호 찾기</a>
					</div>
				</div>
				<div id="regist-form">
					<div>
						<form class="form-size" method="post" id="form" action="/service">
							<input type="hidden" name="command" value="userLogin">
							<div>
								<p>아이디</p>
								<input type="text" name="userId" id="userId" placeholder="  아이디"
									value="${param.userId != null ? param.userId : ''}">
							</div>
							<div>
								<p>비밀번호</p>
								<input type="password" id="userPassword" name="userPassword"
									maxlength="20" placeholder="  비밀번호"
									value="${param.userPassword != null ? param.userPassword : ''}">
							</div>
							<div>
								<input id="final" type="button" value="로그인"
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
<jsp:include page="/footer"></jsp:include>
<script src="../resources/userLogin.js"></script>
</html>