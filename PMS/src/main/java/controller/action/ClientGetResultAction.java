package controller.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import booking.controller.BookingDao;
import controller.Action;
import location.Location;
import location.controller.LocationDao;

public class ClientGetResultAction implements Action {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");

		BookingDao bookingDao = BookingDao.getInstance();

		int locationCode = Integer.parseInt(request.getParameter("locationCode"));
		String clientId = request.getParameter("clientId");
		String cmd = request.getParameter("cmd");
		
		if(cmd.equals("month")) {
			System.out.println("입력받은 커맨드: "+cmd);
			ArrayList<String[]> result = bookingDao.getMonthAndTotal(clientId, locationCode);

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("list", result);

			PrintWriter out = response.getWriter();
			out.print(jsonObject.toString());
			out.flush();

			
		}else if(cmd.equals("day")) {
			
			
			System.out.println("입력받은 커맨드: "+cmd);
			
			
			System.out.println("입력받은 커맨드: "+cmd);
			ArrayList<String[]> result = bookingDao.getDayAndTotal(clientId, locationCode);

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("list", result);

			PrintWriter out = response.getWriter();
			out.print(jsonObject.toString());
			out.flush();
			
			
			
			
		}else {
			
			System.out.println("옳지 않은 커맨드: "+cmd);
		}
		
		
		
	
	}

}