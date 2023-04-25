<%@page import="client.Client"%>
<%@page import="java.util.ArrayList"%>
<%@page import="client.controller.ClientDao"%>
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

	ClientDao clientDao = ClientDao.getInstance();

	ArrayList<Client> list = clientDao.getClientAll();
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
			
				<th>사업자 번호</th>
					<th>가입일</th>
				<th>정보 수정</th>


			</tr>

		</thead>

		<c:forEach items="${pageScope.list}" var="client">





 

			<tr>
				<td>${client.clientId}</td>
				<td>${client.clientPassword}</td>
				<td>${client.clientName}</td>
				<td>${client.clientPhone}</td>
				<td>${client.clientAccount}</td>
				<td>${client.clientNumber}</td>
				<td>${client.regDate}</td>
				<td><a href="/clientUpdateForAdmin?clientId=${client.clientId}">
						<button>수정</button>
				</a></td>
			


			</tr>



		</c:forEach>


	</table>

</body>
<jsp:include page="/footer"></jsp:include>
</html>