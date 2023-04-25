<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<title>PMS : 주차 예약 매니저</title>
</head>
<body>
	<header>
		<div>
			<div>
				<a type="button" href="/"><img src="resources/img/PMS_logo.png"></a>
				<h3>
					<a type="button" href="/">PMS 주차예약매니저</a>
				</h3>
			</div>
		</div>
		<div>
			<div>
				<a id="language" type="button" href="/">한국어</a> <a type="button"
					href="index_eng">English</a> <a type="button"
					href="/${sessionScope.log != null ? 'service?command=logout' : 'userLogin'}">
					${sessionScope.log != null ? '로그아웃' : '로그인'}</a> <a type="button"
					href="${sessionScope.category != 'client' ? 'userMyPage' : 'clientMyPage'}"
					style="${sessionScope.log == null ? 'display:none' : ''}">
					마이페이지 </a> <a type="button" href="board">커뮤니티</a> <a type="button"
					href="${sessionScope.log == null ? 'userRegist' : '#'}"
					style="${sessionScope.log != null ? 'display:none' : ''}">회원가입</a>
				<a type="button"
					href="${sessionScope.log == null ? 'searchBooking' : '#'}"
					style="${sessionScope.log != null ? 'display:none' : ''}">예약조회/변경/취소</a>
			</div>
		</div>
	</header>
</body>
</html>