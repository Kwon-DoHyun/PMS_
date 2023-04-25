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
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	

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



	<table>

		<thead>
			<tr>
				<td>
					<!-- 해당 사이드를 클릭할 때 그래프 값이 변동하도록 -->
					<p>옆에서 출력할 사이드</p>
				</td>
			</tr>
		</thead>
		<c:forEach items="${pageScope.list}" var="list">

			<tbody>
				<tr>
					<!-- 주차장 이름 -->
					<td> ${list.locationName}</td>
					<td><a id="location" onclick="getResultListByMonth('${list.locationCode}','${list.clientId}')"> 월단위</a></td>
					<td><a id="location" onclick="getResultListByDay('${list.locationCode}','${list.clientId}')"> 일단위</a></td>
				</tr>
				<tr>
					<!-- 주차장 주소(필요없으면 작게하거나 없애기) -->
					<td>${list.locationAddress}</td>
				</tr>
	
			</tbody>


		</c:forEach>


	</table>



<div id="result-table">
	<table>


		<c:forEach items="${requestScope.resultList}" var="resultList">


		
			
		

		</c:forEach>

	</table>
	</div>

</body>
<script type="text/javascript" src="resources/clientPrintLocation.js"></script>

</html>