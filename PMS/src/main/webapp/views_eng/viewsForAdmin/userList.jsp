<%@page import="user.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="user.controller.UserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<jsp:include page="/adminHeader"></jsp:include>
<body>
	<%
	request.setCharacterEncoding("utf-8");

	UserDao userDao = UserDao.getInstance();

	ArrayList<User> list = userDao.getUserAll();
	pageContext.setAttribute("list", list);
	%>


	<table>

		<thead>
			<tr>

				<th>아이디</th>
				<th>비밀번호</th>
				<th>이름</th>
				<th>전화번호</th>
				<th>계좌</th>
				<th>가입일</th>
				<th>정보 수정</th>


			</tr>

		</thead>

		<c:forEach items="${pageScope.list}" var="user">


			<tr>
				<td>${user.userId}</td>
				<td>${user.userPassword}</td>
				<td>${user.userName}</td>
				<td>${user.userPhone}</td>
				<td>${user.userAccount}</td>
				<td>${user.regDate}</td>
				<td><a href="/userUpdateForAdmin?userId=${user.userId}">
						<button>수정</button>
				</a></td>
			


			</tr>



		</c:forEach>


	</table>

</body>
<jsp:include page="/footer"></jsp:include>
</html>