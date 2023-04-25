<%@page import="user.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="resources/forFooter.css">
<title>Insert title here</title>
<jsp:include page="/header_eng"></jsp:include>
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
				<h1>A new paradigm in parking</h1>
			</div>
		</aside>
		<div id="footer-menu-form">
			<div>
				<div>
					<h1>write a post</h1>
				</div>
			</div>
			<div id="side-btn">
				<a type="button" href="board">go back</a>
			</div>
			<div style="height: fit-content;">
				<article>
					<div>
						<form id="myForm" method="post" action="../service">
							<input type="hidden" name="command" value="boardWriter_eng">
							<input type="hidden" id="userId" name="userId" readonly
								value="<%=user.getUserId()%>">
							<textarea style="text-align: top; height: fit-content;"
								id="boardTitle" name="boardTitle" placeholder="write the subject."
								autofocus></textarea>
							<textarea style="text-align: top;" id="boardContents"
								name="boardContents" placeholder="write a post." autofocus></textarea>
							<div id="writeBtn">
								<button onclick="writerBoard(form)">registration</button>
							</div>
						</form>
					</div>
				</article>
			</div>
		</div>
	</section>
</body>
<script src="../resources_eng/boardWrite_eng.js"></script>
<jsp:include page="/footer_eng"></jsp:include>
</html>