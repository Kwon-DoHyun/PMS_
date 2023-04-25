<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<title>PMS : parking reservation manager</title>
</head>
<body>
	<header>
		<div>
			<div>
				<a type="button" href="/index_eng"><img src="resources/img/PMS_logo.png"></a>
				<h3>
					<a type="button" href="/index_eng">PMS parking reservation manager</a>
				</h3>
			</div>
		</div>
		<div>
			<div>
			<a id="language" type="button" href="/">한국어</a> <a type="button"
					href="index_eng">English</a>
				<a type="button"
					href="/${sessionScope.log != null ? 'service?command=logout_eng' : 'userLogin_eng'}">
					${sessionScope.log != null ? 'logout' : 'login'}</a> 
					
					
					<a type="button"
					href="${sessionScope.category != 'client' ? 'userMyPage_eng' : 'clientMyPage_eng'}"
					style="${sessionScope.log == null ? 'display:none' : ''}"> myPage </a>
					
					<a type="button"
					href="board_eng">community</a>
					
					<a type="button"
					href="${sessionScope.log == null ? 'userRegist_eng' : '#'}"
					style="${sessionScope.log != null ? 'display:none' : ''}">join the membership</a>
					<a type="button" href="${sessionScope.log == null ? 'searchBooking_eng' : '#'}"
					style="${sessionScope.log != null ? 'display:none' : ''}">Reservation info/change/cancel</a>
			</div>
		</div>
	</header>
</body>
</html>