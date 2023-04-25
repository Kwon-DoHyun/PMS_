package controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import controller.Action;
import location.Location;
import location.controller.LocationDao;

public class SearchLocationAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("application/json");
    	response.setCharacterEncoding("utf-8");
    	
        LocationDao locationDao = LocationDao.getInstance();
        String addressName = request.getParameter("addressName");
        Location location = locationDao.getLocationByName(addressName);

        JSONObject jsonObject = new JSONObject();
        if (location != null) {
            jsonObject.put("status", "success");
            jsonObject.put("code", location.getLocationCode());
            jsonObject.put("name", location.getLocationName());
            jsonObject.put("address", location.getLocationAddress());
            jsonObject.put("currentAreaCount", location.getCurrentAreaCount());
            jsonObject.put("maxAreaCount", location.getMaxAreaCount());
        } else {
            jsonObject.put("status", "fail");
        }
        
        PrintWriter out = response.getWriter();
        out.print(jsonObject.toString());
        out.flush();
    }
}
