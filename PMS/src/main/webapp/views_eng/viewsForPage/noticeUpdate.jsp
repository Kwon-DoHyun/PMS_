<%@page import="notice.Notice"%>
<%@page import="notice.controller.NoticeDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>


<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/adminHeader"></jsp:include>
</head>
<body>

	<%
	NoticeDao noticeDao = NoticeDao.getInstance();
	int number = Integer.parseInt(request.getParameter("noticeNumber"));
	Notice notice = noticeDao.getNoticeByNo(number);
	noticeDao.updateViewCount(number);
	pageContext.setAttribute("notice", notice);
	%>
	<h1>★공지사항 상세페이지★</h1>
	<form method="POST" ACTION="../service">
		<input type="hidden" name="command" value=noticeUpdate> 
		<input type="hidden" id="noticeNumber" name="noticeNumber" value="${notice.noticeNumber}">
		<table border=1>
			<thead>


				<tr>
					<td>작성자</td>
					<td>관리자</td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input type="text" id="noticeTitle" name="noticeTitle"
						value="${notice.noticeTitle}"></input></td>
				</tr>
				<tr>
					<td>내용</td>
					<td><textarea id="noticeContents" name="noticeContents">${notice.noticeContents}</textarea></td>
				</tr>
				<tr>
					<td>등록일자</td>
					<td>${notice.regDate}</td>
				</tr>
				<tr>
					<td>조회수</td>
					<td>${notice.viewCount}</td>
				</tr>

			</thead>
			<tbody>

			</tbody>
		</table>

		<input type="button" value="수정"
			onclick="setCommandValue('noticeUpdate'); submit()"> <input
			type="button" value="삭제"
			onclick="setCommandValue('noticeDelete'); submit()">

		<script>
			function setCommandValue(value) {
				document.getElementsByName('command')[0].value = value;
			}
		</script>
	</form>

</body>
</html>