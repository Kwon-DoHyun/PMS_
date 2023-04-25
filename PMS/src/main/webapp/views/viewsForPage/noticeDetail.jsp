<%@page import="java.text.SimpleDateFormat"%>
<%@page import="client.Client"%>
<%@page import="notice.Notice"%>
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
	int number = Integer.parseInt(request.getParameter("noticeNumber"));
	NoticeDao noticeDao = NoticeDao.getInstance();
	Notice notice = noticeDao.getNoticeByNo(number);
	noticeDao.updateViewCount(number);
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	String regDate = notice.getRegDate() != null ? dateFormat.format(notice.getRegDate()) : "";
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
				<h1>상세내용</h1>
			</div>

			<div id="notice">
				<table id="notice-table">
					<thead id="notice-thead">
						<tr>
							<td style="width: 10%;">NO</td>
							<td style="width: 40%;">제목</td>
							<td style="width: 10%;">작성자</td>
							<td style="width: 10%;">작성일자</td>
							<td style="width: 10%;">조회수</td>
						</tr>
						<tr>
							<td><%=notice.getNoticeNumber()%></td>
							<td><%=notice.getNoticeTitle()%></td>
							<td>관리자</td>
							<td><%=regDate%></td>
							<td><%=notice.getViewCount()%></td>
						</tr>
					</thead>
					<tbody id="noticeDetail-tbody">
						<tr>
							<td colspan="5"><%=notice.getNoticeContents()%></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</section>
</body>
<jsp:include page="/footer"></jsp:include>
</html>