<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/forHeader.css">
<title>회원가입</title>
</head>
<jsp:include page="/header"></jsp:include>
<body onload="duppleErrorCheck('${requestScope.dupple}')">

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
					<div id="selected">
						<a href="userRegist">일반 회원가입</a>
					</div>
					<div id="unselected">
						<a href="clientRegist">사업자 회원가입</a>
					</div>
				</div>
				<div id="regist-form">
					<div>
						<form class="form-size" method="post" id="form" name="myForm" onsubmit="return validateFrom()" action="/service">
							<input type="hidden" name="command" value="UserRegist">
							<div>
								<p>아이디</p>
								<input type="text" name="userId" id="userId" maxlength="20"
									placeholder="  아이디"
									value="${param.userId != null ? param.userId : ''}">
							</div>
							<div>
								<p>비밀번호</p>
								<input type="password" name="userPassword" id="userPassword"
									maxlength="20" placeholder="  비밀번호"
									value="${param.userPassword != null ? param.userPassword : ''}">
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
									maxlength="13" placeholder="핸드폰번호" oninput="formatPhone(this)"
									value="${param.userPhone != null ? param.userPhone : ''}">
							</div>
							<div>
								<p>계좌번호</p>
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


	<script src="../resources/userRegist.js"></script>
</body>
<jsp:include page="/footer"></jsp:include>
</html>

