<%@page import="notice.controller.NoticeDao"%>
<%@page import="board.controller.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/adminHeader"></jsp:include>
</head>
<body>
	<%
	BoardDao boardDao = BoardDao.getInstance();
	NoticeDao noticeDao = NoticeDao.getInstance();
	%>

	<c:set var="boardList" value="<%=boardDao.getBoardAll()%>" />
	<c:set var="size" value="${fn:length(boardList)}" />

	<table>
		<thead>
			<tr>
				<th>글제목</th>
				<th>본문</th>
				<th>아이디</th>
			</tr>
		</thead>

		<tbody>
			<c:choose>
				<c:when test="${size > 0}">
					<c:forEach var="i" begin="0" end="4" step="1">
						<tr>
							<td><a href="#" class="list-group-item">${boardList[i].boardNumber}</a></td>
							<td><a
								href="/boardDetail?boardNumber=${boardList[i].boardNumber}">${boardList[i].boardTitle}</a>
							</td>
							<td><a href="#" class="list-group-item">${boardList[i].userId}</a></td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="3">게시글이 없습니다.</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>





</body>

</html>