<%@page import="board.Board"%>
<%@page import="board.controller.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	int number = Integer.parseInt(request.getParameter("boardNumber"));
	BoardDao boardDao = BoardDao.getInstance();
	Board board = boardDao.getBoardByNo(number);
	boardDao.updateViewCount(number);
	pageContext.setAttribute("board", board);
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
				<h1>Detail</h1>
			</div>
			<div id="notice">
				<table id="notice-table">
					<thead id="notice-thead">
						<tr>
							<td style="width: 10%;">NO</td>
							<td style="width: 40%;">TITLE</td>
							<td style="width: 10%;">Writer</td>
							<td style="width: 15%;">Date of issue</td>
							<td style="width: 5%;">Views</td>
						</tr>
						<tr>
							<td>${param.number}</td>
							<td>${board.boardTitle}</td>
							<td>${board.userId}</td>
							<td>${board.regDate}</td>
							<td>${board.viewCount}</td>
						</tr>
					</thead>
					<tbody id="noticeDetail-tbody">
						<tr>
							<td colspan="5"><textarea id="boardContents"name="boardContents">${board.boardContents}</textarea></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</section>
	<script src="../resources_eng/boardUpdate_eng.js"></script>
</body>
<jsp:include page="/footer_eng"></jsp:include>
</html>