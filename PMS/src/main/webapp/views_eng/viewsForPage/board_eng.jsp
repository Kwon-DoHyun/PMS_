<%@page import="board.Board"%>
<%@page import="board.controller.BoardDao"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="notice.Notice"%>
<%@page import="java.util.ArrayList"%>
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
	BoardDao boardDao = BoardDao.getInstance();
	int currentPage = 1;
	int pageLimit = 10;
	int totalRecord = boardDao.getBoardListCount() + 1;
	ArrayList<Board> list = boardDao.getBoardByRange(totalRecord - pageLimit, totalRecord);
	int maxPage = (int) Math.ceil((double) totalRecord / pageLimit);

	// 마지막 페이지 시작 게시글 인덱스
	int lastPageStart = (maxPage - 1) * pageLimit;

	// 마지막 페이지 게시글 리스트
	ArrayList<Board> lastPageList = boardDao.getBoardByRange(lastPageStart, totalRecord);

	if (request.getParameter("page") != null) {
		// 현재 페이지 지정
		currentPage = Integer.parseInt(request.getParameter("page"));
		System.out.println("page: " + currentPage);

		// 해당 페이지의 시작 인덱스, 종료 인덱스 계산
		int startRow = totalRecord - ((currentPage - 1) * pageLimit);
		int endRow = startRow - pageLimit;

		if (startRow > totalRecord) {
			// 현재 페이지가 범위를 초과하면 마지막 페이지로 이동
			response.sendRedirect(request.getContextPath() + "/board_eng?page=" + maxPage);
			return;
		}

		if (endRow >= totalRecord) {
			endRow = totalRecord - 1;
		}

		// 해당 페이지의 게시글 리스트 가져오기
		list = boardDao.getBoardByRange(endRow, startRow - 1);
		request.setAttribute("list", list);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("maxPage", maxPage);
	}

	String keyword = request.getParameter("keyword");
	if (keyword != null) {
		// 검색어에 대한 게시글 리스트 가져오기
		list = boardDao.getBoardSearch(keyword);
		request.setAttribute("list", list);
	}

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
				<h1>community</h1>
			</div>

			<div id="notice">
				<div id="board-menu">
					<input type="text" id="kewordInput" name="keyword"
						placeholder="Please enter your search">
					<button id="keyword" onclick="search()">search</button>

					<a id="write-menu" type="button"
						href="${sessionScope.log != null ? 'boardWrite_eng' : '#'}"
						style="${sessionScope.log == null || sessionScope.category.equals('client') ? 'display:none' : ''}">writing</a>

				</div>
				<table id="notice-table">
					<thead id="notice-thead">
						<tr>
							<td style="width: 10%; height: 50px;">NO</td>
							<td style="width: 40%;">TITLE</td>
							<td style="width: 10%;">Writer</td>
							<td style="width: 10%;">Date of issue</td>
							<td style="width: 10%;">views</td>
						</tr>
					</thead>
					<tbody>
						<%
						for (int i = list.size() - 1; i >= 0; i--) {
						%>
						<tr>
							<td><%=list.get(i).getBoardNumber()%></td>
							<td><a
								href="boardDetail_eng?boardNumber=<%=list.get(i).getBoardNumber()%>"><%=list.get(i).getBoardTitle()%></a></td>
							<td><%=list.get(i).getUserId()%></td>
							<%
							SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
							String regDate = list.get(i).getRegDate() != null ? dateFormat.format(list.get(i).getRegDate()) : "";
							%>
							<td><%=regDate%></td>
							<td><%=list.get(i).getViewCount()%></td>
						</tr>

						<%
						}
						%>
					</tbody>
				</table>
				<form id="page-form" method="get" action="your-page-url">
					<input type="hidden" name="page" id="page-input">
					<%
					for (int i = 1; i <= maxPage; i++) {
					%>
					<a id="pageBtn" href="board_eng?page=<%=i%>"><%=i%></a>
					<%
					}
					%>
				</form>
			</div>
		</div>
	</section>
</body>
<jsp:include page="/footer_eng"></jsp:include>
<script type="text/javascript" src="../resources_eng/board_eng.js"></script>
</html>