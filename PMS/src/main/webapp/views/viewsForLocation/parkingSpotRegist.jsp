<%@page import="location.Location"%>
<%@page import="java.util.ArrayList"%>
<%@page import="spot.controller.SpotDao"%>
<%@page import="client.Client"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/otherForm.css">
<title>주차구역 등록</title>
</head>
<jsp:include page="/header"></jsp:include>
<body>
	<%
	Client client = (Client) session.getAttribute("log");
	SpotDao spotDao = SpotDao.getInstance();
	String id = client.getClientId();
	System.out.println("아아아아아: " + id);


	pageContext.setAttribute("client", client);
	
	%>
	<section>
		<h3 style="text-align: center">게시글 등록</h3>
		<form method="POST" action="../service">
			<input type="hidden" name="command" value="spotRegist">
			<div class="TEXTAREA">

				<table>

					<tr>
						<td>주차장</td>
						<td><input type="text" id="locationCode" name="locationCode"
							value=""></td>
					</tr>
					<tr>
						<td>비용</td>
						<td><input type="text" id="spotCost" name="spotCost" value=""></td>
					</tr>
					<tr>
						<td>장애인구역 여부</td>
						<td><select id="disabledSpot" name="disabledSpot">
								<option value="1">예</option>
								<option value="0" disabled selected>아니오</option>
						</select></td>
					</tr>


				</table>
			</div>
			<input type="button" value="등록" onclick="spotRegist(form)">
		</form>
	</section>
	<script src="../resources/spotRegist.js"></script>
</body>
<jsp:include page="/footer"></jsp:include>
</html>