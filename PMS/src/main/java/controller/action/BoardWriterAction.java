package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.BoardDto;
import board.controller.BoardDao;
import controller.Action;

public class BoardWriterAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String boardTitle = request.getParameter("boardTitle");
		String boardContents = request.getParameter("boardContents");
		String userId = request.getParameter("userId");
	
		BoardDao boardDao = BoardDao.getInstance();
		BoardDto boardDto = new BoardDto(boardTitle, boardContents, userId);
		boardDao.writeByNewBoard(boardDto);
		
		response.sendRedirect("board");
		
	}

}
