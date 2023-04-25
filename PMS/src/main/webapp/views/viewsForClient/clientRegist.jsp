<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/forHeader.css">
<title>사업자 가입</title>
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
						<a href="clientLogin">로그인</a>
					</div>
					<div id="unselected">
						<a href="userRegist">일반 회원가입</a>
					</div>
					<div id="selected">
						<a href="clientRegist">사업자 회원가입</a>
					</div>
				</div>
				<div id="regist-form">
					<div>
						<form class="form-size" method="post" id="form" name="myForm" onsubmit="return validateFrom()" action="/service">
							<input type="hidden" name="command" value="ClientRegist">
							<div>
								<p>아이디</p>
								<input type="text" name="clientId" id="clientId" maxlength="20"
									placeholder="  아이디"
									value="${param.clientId != null ? param.clientId : ''}">
							</div>
							<div>
								<p>비밀번호</p>
								<input type="password" name="clientPassword" id="clientPassword"
									maxlength="20" placeholder="  비밀번호"
									value="${param.clientPassword != null ? param.clientPassword : ''}">
							</div>
							<div>
								<p>회원 이름</p>
								<input type="text" name="clientName" id="clientName"
									maxlength="10" placeholder="회원이름"
									value="${param.clientName != null ? param.clientName : ''}">
							</div>
							<div>
								<p>핸드폰번호</p>
								<input type="text" name="clientPhone" id="clientPhone"
									maxlength="13" placeholder="핸드폰번호" oninput="formatPhone(this)"
									value="${param.clientPhone != null ? param.clientPhone : ''}">
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
								</select> <input type="text" name="clientAccount" id="clientAccount" oninput="formatAccount(this)"
									placeholder="계좌번호"
									value="${param.clientAccount != null ? param.clientAccount : ''}">
							</div>
							<div>
								<p>사업자번호</p>
								<input type="text" name="clientNumber " id="clientNumber"
									oninput="formatClientNumber(this)" maxlength="12"
									placeholder="사업자번호"
									value="${param.clientNumber != null ? param.clientNumber : ''}">
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
</body>
<script src="../resources/clientRegist.js"></script>
<jsp:include page="/footer"></jsp:include>
</html>