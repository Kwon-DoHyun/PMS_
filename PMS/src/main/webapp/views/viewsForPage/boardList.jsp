<%@page import="user.controller.UserDao"%>
<%@page import="client.controller.ClientDao"%>
<%@page import="board.Board"%>
<%@page import="java.util.ArrayList"%>
<%@page import="board.controller.BoardDao"%>
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
request.setCharacterEncoding("utf-8");
BoardDao boardDao = BoardDao.getInstance();
ArrayList<Board> list = boardDao.getBoardAll();
pageContext.setAttribute("list", list);
UserDao userDao = UserDao.getInstance();
%>
<table>
    <thead>
        <tr>
            <th>게시글 번호</th>
            <th>제목</th>
            <th>작성자 id</th>
            <th>작성일자</th>
            <th>조회수</th>
        </tr>
    </thead>
    <c:forEach items="${pageScope.list}" var="board">
        <tr>
            <td>${board.boardNumber}</td>
            <td><a href="/boardDetail?boardNumber=${board.boardNumber}">${board.boardTitle}</a> </td>
            <!-- 차후 유저 상세 볼 수 있도록 -->
            <td><a href="#"></a> ${board.userId}</td>
            <td>${board.regDate}</td>
            <td>${board.viewCount}</td>
        </tr>
    </c:forEach>
</table>


</body>
</html>