<%@page import="client.Client"%>
<%@page import="client.controller.ClientDao"%>
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
	ClientDao clientDao = ClientDao.getInstance();
	String clientId = request.getParameter("clientId");
	Client client = clientDao.findClientById(clientId);

	if (client == null) {
		System.out.println("사업자의 정보 변경");

		client = (Client) session.getAttribute("log");

	}
	pageContext.setAttribute("client", client);
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
						<a href="clientMyPage">내프로필</a>
					</div>
					<div id="selected">
						<a href="clientUpdate">프로필 수정</a>
					</div>
					<div id="unselected">
						<a href="clientPrintLocation">영업장 관리</a>
					</div>
					<div id="unselected">
						<a href="clientMyPage">예약정보</a>
					</div>
					<div id="unselected">
						<a type="button" href="/">뒤로가기</a>
					</div>
				</div>
				<div id="myPage-content" style="width: 100%;">
					<form method="post" id="form" name="myForm" onsubmit="return validateFrom()" action="/service">
						<input type="hidden" name="command" value="clientUpdate">
						<table id="myPage-table">
							<thead id="myPage-thead">
								<tr style="widht: 100%;">
									<td style="width: 10%;">아이디</td>
									<td style="width: 10%;">비밀번호</td>
									<td style="width: 10%;">사업주명</td>
									<td style="width: 10%;">사업자번호</td>
									<td style="width: 10%;">핸드폰번호</td>
									<td style="width: 10%;">계좌번호</td>
								</tr>
							</thead>

							<tbody id="myPage-tbody">
								<tr>
									<td><input class="disable" id="clientId" name="clientId"
										value="${client.clientId}" readonly></td>
									<td><input style="width: 80%;" type="password"
										name="clientPassword" id="clientPassword" maxlength="20"
										value="${client.clientPassword != null ? client.clientPassword : ''}">
									</td>

									<td><input class="disable" id="clientName"
										name="clientName"
										value="${client.clientName != null ? client.clientName : ''}"
										readonly></td>
									<td><input style="width: 80%;" type="text"
										name="clientNumber" id="clientNumber"
										oninput="formatClientNumber(this)" maxlength="12"
										value="${client.clientNumber != null ? client.clientNumber : ''}"></td>
									<td><input style="width: 80%;" type="text"
										name="clientPhone" id="clientPhone"
										oninput="formatPhone(this)" maxlength="13"
										value="${client.clientPhone != null ? client.clientPhone : ''}"></td>
									<td>
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
									</td>
								</tr>
							</tbody>
						</table>
					</form>
					<div id="btn-menu">
						<div>
							<div id="modifyBtn">
								<button onclick="checkValues(document.getElementById('form'))">정보수정</button>
							</div>
							<div id="deleteBtn">
								<a id="as" href="clientDelete">회원탈퇴</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
<script src="../resources/clientUpdate.js"></script>
<jsp:include page="/footer"></jsp:include>
</html>