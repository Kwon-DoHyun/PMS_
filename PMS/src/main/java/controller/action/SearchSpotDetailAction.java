package controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import controller.Action;
import spot.Spot;
import spot.controller.SpotDao;

public class SearchSpotDetailAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		
		int locationNumber = Integer.parseInt(request.getParameter("locationNumber"));
		String spotNames = request.getParameter("spotNames");
		String startTime = request.getParameter("startTime"); 
		
		SpotDao dao = SpotDao.getInstance();
		ArrayList<Spot> list = dao.getSelectSpotName(locationNumber, spotNames,startTime);
		
		JSONArray result = new JSONArray(list);
		System.out.println("result:"+result);
		response.getWriter().print(result);
		response.getWriter().flush();
	
	}

}
