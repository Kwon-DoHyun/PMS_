package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardDto;
import board.controller.BoardDao;
import controller.Action;

public class BoardDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNumber = Integer.parseInt(request.getParameter("boardNumber"));
		String boardTitle = request.getParameter("boardTitle");
		String boardContents = request.getParameter("boardContents");
		
		BoardDao boardDao = BoardDao.getInstance();
		BoardDto boardDto = new BoardDto(boardNumber,boardTitle, boardContents);
		boardDao.boardDelete(boardDto);
		boardDao.boardSort();
		
		response.sendRedirect("board");
	}

}
