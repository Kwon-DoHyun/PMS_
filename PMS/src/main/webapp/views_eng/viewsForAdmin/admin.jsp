<%@page import="admin.AdminAction"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- 가능하면 관리자 페이지는 디자인 통일하지 말고 단색으로 밀거나 없애는 쪽을 권장합니다 -->
	<!--사유: 유저들이 보는 곳이 아님 -->


  <h1>Your IP address is: <span id="ip"></span></h1>


  
<%

String address = (String) request.getRemoteAddr();

AdminAction adminAction = new AdminAction(){};


String ip = adminAction.getClientIpAddr(request);

%>
    
    <span>ip:<%=ip %> </span>
    
    
    
    
    
</body>
</html>

<!-- 	<form method="post" action="/service">
		<input type="hidden" name="command" value="adminLogin"> <input
			type="password" maxlength="12">
	
	


	</form> -->