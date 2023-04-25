<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/forHeader.css">
<jsp:include page="/header"></jsp:include>
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
			<div id="box-interface">
				<div id="select-menu">
					<div id="unselected">
						<a href="userLogin">로그인</a>
					</div>
					<div id="unselected">
						<a href="findId">아이디 찾기</a>
					</div>
					<div id="selected">
						<a href="findPassword">비밀번호 찾기</a>
					</div>
				</div>
				<div id="regist-form">
					<div>
						<form class="form-size" method="post" id="form" action="/service">
							<input type="hidden" name="command" value="findPassword">
							<div>
								<p>아이디</p>
								<input type="text" name="userId" id="userId" maxlength="20"
									placeholder="  아이디"
									value="${param.userId != null ? param.userId : ''}">
							</div>
							<div>
								<p>회원 이름</p>
								<input type="text" name="userName" id="userName" maxlength="10"
									placeholder="  회원이름"
									value="${param.userName != null ? param.userName : ''}">
							</div>
							<div>
								<p>핸드폰번호</p>
								<input type="text" name="userPhone" id="userPhone"
									maxlength="13" placeholder="  핸드폰번호"
									oninput="formatPhone(this)"
									value="${param.userPhone != null ? param.userPhone : ''}">
							</div>
							<div>
								<input id="final" type="button" value="인증하기"
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
<script src="../resources/findPassword.js"></script>
</html>