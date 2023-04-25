<%@page import="java.text.SimpleDateFormat"%>
<%@page import="notice.Notice"%>
<%@page import="java.util.ArrayList"%>
<%@page import="notice.controller.NoticeDao"%>
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
	NoticeDao noticeDao = NoticeDao.getInstance();
	int currentPage = 1;
	int pageLimit = 10;
	int totalRecord = noticeDao.getNoticeCount()+1;
	ArrayList<Notice> list = noticeDao.getNoticeByRange(totalRecord-pageLimit, totalRecord);
	int maxPage = (int) Math.ceil((double) totalRecord / pageLimit);

	// 마지막 페이지 시작 게시글 인덱스
	int lastPageStart = (maxPage - 1) * pageLimit;

	// 마지막 페이지 게시글 리스트
	ArrayList<Notice> lastPageList = noticeDao.getNoticeByRange(lastPageStart, totalRecord);

	if (request.getParameter("page") != null) {
	    // 현재 페이지 지정
	    currentPage = Integer.parseInt(request.getParameter("page"));
	    System.out.println("page: " + currentPage);

	    // 해당 페이지의 시작 인덱스, 종료 인덱스 계산
	    int startRow = totalRecord - ((currentPage - 1) * pageLimit);
	    int endRow = startRow - pageLimit;

	    if (startRow > totalRecord) {
	        // 현재 페이지가 범위를 초과하면 마지막 페이지로 이동
	        response.sendRedirect(request.getContextPath() + "/board?page=" + maxPage);
	        return;
	    }

	    if (endRow >= totalRecord) {
	        endRow = totalRecord - 1;
	    }
	    
	    
	    // 해당 페이지의 게시글 리스트 가져오기
	    list = noticeDao.getNoticeByRange(endRow, startRow - 1);
	    request.setAttribute("list", list);
	    request.setAttribute("currentPage", currentPage);
	    request.setAttribute("maxPage", maxPage);
	}
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
				<h1>공지사항</h1>
			</div>

			<div id="notice">
				<table id="notice-table">
					<thead id="notice-thead">
						<tr>
							<td style="width: 10%; height: 50px; ">NO</td>
							<td style="width: 40%;">제목</td>
							<td style="width: 10%;">작성자</td>
							<td style="width: 10%;">작성일자</td>
							<td style="width: 10%;">조회수</td>
						</tr>
					</thead>
					<tbody>
						<%
						for (int i = list.size()-1; i>=0;i--) {
						%>
						<tr>
							<td><%=list.get(i).getNoticeNumber()%></td>
							<td><a
								href="noticeDetail?noticeNumber=<%=list.get(i).getNoticeNumber()%>"><%=list.get(i).getNoticeTitle()%></a></td>
							<td>관리자</td>
							<%
							SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
							String regDate = list.get(i).getRegDate() != null ? dateFormat.format(list.get(i).getRegDate()) : "";
							%>
							<td><%=regDate%></td>
							<td><%=list.get(i).getViewCount()%></td>
						</tr>

						<%}%>
					</tbody>
				</table>
				<form id="page-form" method="get" action="your-page-url">
					<input type="hidden" name="page" id="page-input">
					<%
					for (int i = 1; i <= maxPage; i++) {
					%>
					<a id="pageBtn" href="notice?page=<%=i%>"><%=i%></a>
					<%
}
%>
				</form>
			</div>
		</div>
	</section>
</body>
<jsp:include page="/footer"></jsp:include>
<script type="text/javascript" src="../resources/notice.js"></script>
</html>