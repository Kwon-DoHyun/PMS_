package controller.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import board.Board;
import board.controller.BoardDao;
import controller.Action;

public class SearchBoardAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String keyword = request.getParameter("keyword");
		
		BoardDao dao = BoardDao.getInstance();
		ArrayList<Board> list = dao.getBoardSearch(keyword);
		JSONArray data = new JSONArray();
		
		for(Board board : list) {
			JSONObject obj = new JSONObject();
			obj.put("boardNumber", board.getBoardNumber());
			obj.put("boardTitle", board.getBoardTitle());
			obj.put("boardContents", board.getBoardContents());
			obj.put("userId", board.getUserId());
			obj.put("regDate", board.getRegDate());
			obj.put("viewCount", board.getViewCount());
		
			data.put(obj);
		}
		PrintWriter out = response.getWriter();
		response.setContentType("application/json charset=utf8");
	    response.setHeader("Content-Type", "application/json charset=utf8");
	    
	    out.print(data);
		out.flush();
	}
		
	}


