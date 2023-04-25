<%@page import="client.Client"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <link rel="stylesheet" href="resources/myPage.css">


<title>거긴 내자리에 오신걸 환영합니다.</title>
</head>
<jsp:include page="/header"></jsp:include>
<body  onload="errorCheck('${requestScope.modify}')">
	<%
	
	request.setCharacterEncoding("utf-8");
	String str = (String) session.getAttribute("modify");
	System.out.print("str:"+str);
	Client client = (Client) session.getAttribute("log");
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
					<div id="selected">
						<a href="clientMyPage">내프로필</a>
					</div>
					<div id="unselected">
						<a href="clientUpdate">프로필 수정</a>
					</div>
					<div id="unselected">
						<a href="clientPrintLocation">영업장 관리</a>
					</div>
					<div id="unselected">
						<a type="button" href="/">뒤로가기</a>
					</div>
				</div>
				<div id="myPage-content" style="width:100%;">
					<table id="myPage-table">
						<thead id="myPage-thead">
							<tr>
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
								<td>${client.clientId}</td>
								<td>${client.clientPassword != null ? client.clientPassword : ''}</td>
								<td>${client.clientName != null ? client.clientName : ''}</td>
								<td>${client.clientNumber != null ? client.clientNumber : ''}</td>
								<td>${client.clientPhone != null ? client.clientPhone : ''}</td>
								<td>${client.clientAccount != null ? client.clientAccount : ''}</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</section>

</body>
<script src="../resources/clientMyPage.js"></script>
<jsp:include page="/footer"></jsp:include>
</html>