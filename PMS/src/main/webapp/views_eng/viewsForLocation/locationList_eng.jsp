<%@page import="location.controller.LocationDao"%>
<%@page import="location.Location"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/otherForm.css">
<title>parking lot list</title>
<jsp:include page="/header_eng"></jsp:include>
</head>
<body>
	<%
	request.setCharacterEncoding("UTF-8");
	String reservationDateParam = (String) session.getAttribute("reservationDate");
	String reservationTimeParam = (String) session.getAttribute("reservationTime");
	String addressInputParam = (String) session.getAttribute("addressInput");

	String locationNameParam = request.getParameter("locationName");
	System.out.println("aaaaaaa:  " + locationNameParam);
	System.out.println("addressInputParam : " + addressInputParam);
	%>
	<section>
		<aside>
			<div>
				<h1>
					<span>PMS</span> System
				</h1>
				<h1>A new paradigm in parking</h1>
			</div>
		</aside>
		<div>
			<div>
				<div id="article_1">
					<div>
						<h1>parking lot list</h1>
					</div>
					<%
					LocationDao locationDao = LocationDao.getInstance();
					ArrayList<Location> list = locationDao.getLocationListByKeyword(addressInputParam);
					System.out.println("리스트사이즈 : " + list.size());
					for (int i = 0; i < list.size(); i++) {
					%>
					<div>
						<a id="title" type="text" class="locationAddress"
							onclick="searchAddress('<%=list.get(i).getLocationAddress()%>', '<%=list.get(i).getLocationName()%>')"><%=list.get(i).getLocationName()%></a>
						<div id="address"><%=list.get(i).getLocationAddress()%></div>
					</div>
					<%
					}
					%>
				</div>
				<div id="article_2">
					<div id="map"></div>
				</div>
				<div id="article_3">

					<table>
						<thead>
							<tr>
								<td id="detail" colspan="4">Parking lot details</td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td id="location-name"></td>
							</tr>
							<tr>
								<td id="location-address"></td>
							</tr>
							<tr>
								<td id="current-count"></td>
							</tr>
							<tr>
								<td id="disabled"></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div id="btn-menu">
				<c:choose>
					<c:when test="${empty sessionScope.log }">
						<button id="nonUserBtn" onclick="checkValue()">Non-member reservation</button>
					</c:when>
					<c:otherwise>
						<button id="userBtn" onclick="checkValue2()">Member reservation</button>
					</c:otherwise>
				</c:choose>
			</div> 
		</div>
	</section>
</body>
<jsp:include page="/footer_eng"></jsp:include>
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=628bc22098488cc1de89f641964cb718&libraries=services"></script>
<script type="text/javascript" src="resources_eng/map_eng.js"></script>
</html>