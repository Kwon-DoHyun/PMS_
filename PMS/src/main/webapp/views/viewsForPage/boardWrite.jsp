<%@page import="user.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/forFooter.css">
<title>Insert title here</title>
<jsp:include page="/header"></jsp:include>
</head>
<body>
	<%
	User user = (User) session.getAttribute("log");
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
		<div id="footer-menu-form">
			<div>
				<div>
					<h1>게시글 작성</h1>
				</div>
			</div>
			<div id="side-btn">
				<a type="button" href="board">뒤로가기</a>
			</div>
			<div style="height: fit-content;">
				<article>
					<div>
						<form id="myForm" method="post" action="../service">
							<input type="hidden" name="command" value="boardWriter">
							<input type="hidden" id="userId" name="userId" readonly
								value="<%=user.getUserId()%>">
							<textarea style="text-align: top; height: fit-content;"
								id="boardTitle" name="boardTitle" placeholder="제목을 작성하세요."
								autofocus></textarea>
							<textarea style="text-align: top;" id="boardContents"
								name="boardContents" placeholder="게시글을 작성하세요." autofocus></textarea>
							<div id="writeBtn">
								<button onclick="writerBoard(form)">등록</button>
							</div>
						</form>
					</div>
				</article>
			</div>
		</div>
	</section>
</body>
<script src="../resources/boardWrite.js"></script>
<jsp:include page="/footer"></jsp:include>
</html>