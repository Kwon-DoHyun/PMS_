<%@page import="client.Client"%>
<%@page import="location.Location"%>
<%@page import="java.util.ArrayList"%>
<%@page import="location.controller.LocationDao"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/myPage.css">
<title>Insert title here</title>
<jsp:include page="/header"></jsp:include>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>


</head>
<body>

	<%
	request.setCharacterEncoding("utf-8");

	LocationDao locationDao = LocationDao.getInstance();
	Client client = (Client) session.getAttribute("log");
	System.out.println("리스트 출력문 내 클라이언트:" + client);

	String clientId = client.getClientId();

	ArrayList<Location> list = locationDao.getLocationByClientId(clientId);
	pageContext.setAttribute("list", list);
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
					<div id="unselected">
						<a href="clientUpdate">프로필 수정</a>
					</div>
					<div id="selected">
						<a href="clientPrintLocation">영업장 관리</a>
					</div>
					<div id="unselected">
						<a type="button" href="/">뒤로가기</a>
					</div>
				</div>
				<div id="manager">
					<div>
						<table>
							<c:forEach items="${pageScope.list}" var="list">
								<tbody id="managerT">
									<tr>
										<!-- 주차장 이름 -->
										<td>${list.locationName}</td>
										<td><button id="location"
											onclick="getResultListByMonth('${list.locationCode}','${list.clientId}')">
												월 매출 조회</button></td>
										<td><button id="location"
											onclick="getResultListByDay('${list.locationCode}','${list.clientId}')">
												일 매출 조회</button></td>
									</tr>
								</tbody>
							</c:forEach>
						</table>
					</div>
					<div id="result-table">
						<table>
							<c:forEach items="${requestScope.resultList}" var="resultList">
							</c:forEach>
						</table>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
<script type="text/javascript" src="resources/clientPrintLocation.js"></script>
<jsp:include page="/footer"></jsp:include>
</html>