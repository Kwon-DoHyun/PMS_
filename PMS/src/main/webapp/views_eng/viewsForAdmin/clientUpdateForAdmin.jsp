<%@page import="client.Client"%>
<%@page import="client.controller.ClientDao"%>
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
<!-- <link rel="stylesheet" href="resources/myPage.css"> -->

</head>
<jsp:include page="/adminHeader"></jsp:include>
<!-- 해당 페이지를 열 때 처리 상태를 받아와야 함 -->
<body onload="adminCodeCheck('${requestScope.adminCode}')">

	<%
	//리스트에서 사업자 id를 선택해서 오는 것이므로 파라미터로 받음
	ClientDao clientDao = ClientDao.getInstance();
	String clientId = request.getParameter("clientId");
	Client client = clientDao.findClientById(clientId);

	pageContext.setAttribute("client", client);

	//해당 코드는 현재 null이 뜨는게 정상. 차후 어드민 업데이트 후 생성 예정임(권도현)
	request.removeAttribute("adminCode");
	String adminCode = (String) request.getAttribute("adminCode");
	System.out.println("어드민코드값" + adminCode);
	%>
	<section>



		<form method="post" id="myForm" action="/service">
			<input type="hidden" name="command" value="clientUpdateForAdmin">
			<table>
				<thead>
					<tr>
						<td style="width: 10%;">아이디</td>
						<td style="width: 10%;">비밀번호</td>
						<td style="width: 10%;">이름</td>
						<td style="width: 10%;">핸드폰번호</td>
						<td style="width: 10%;">계좌번호</td>
						<td style="width: 10%;"> 사업자 번호</td>
					</tr>
				</thead>

				<tbody>
					<tr>
						<td><input class="disable" id="clientId" name="clientId"
							value="${client.clientId}" readonly></td>
						<td><input type="password" name="clientPassword"
							id="clientPassword" maxlength="20"
							value="${client.clientPassword != null ? client.clientPassword : ''}">
						</td>
						<td><input class="disable" id="clientName" name="clientName"
							value="${client.clientName != null ? client.clientName : ''}"
							readonly></td>
						<td><input type="text" name="clientName" id="clientPhone"
							oninput="formatPhone(this)" maxlength="13"
							value="${client.clientName != null ? client.clientName : ''}"></td>
						<td><select id="bankSelect" name="bankSelect">
								<option value="">-- 은행선택 --</option>
								<option value="kb">국민은행</option>
								<option value="shinhan">신한은행</option>
								<option value="woori">우리은행</option>
								<option value="keb">하나은행</option>
								<option value="nh">농협은행</option>
						</select> <input type="text" name="clientAccount" id="clientAccount"
							oninput="formatAccount(this)"
							value="${clientAccount != null ? 'clientAccount' : client.clientAccount}">
							<!-- 이전 변경처리에서 넘어오는 값이 비어있으면 현 유저의 계좌를 가져옴--></td>
						<td><input style="width: 80%;" type="text"
							name="clientNumber" id="clientNumber"
							oninput="formatClientNumber(this)" maxlength="12"
							value="${client.clientNumber != null ? client.clientNumber : ''}"></td>
					</tr>
				</tbody>
			</table>
		</form>


		<div>
			<button onclick="checkValues(document.getElementById('myForm'))">사업자정보수정</button>

			<a href="clientDeleteForAdmin">회원삭제</a>
		</div>
	</section>
</body>
<script src="../resources/clientUpdateForAdmin.js"></script>
<jsp:include page="/footer"></jsp:include>
</html>