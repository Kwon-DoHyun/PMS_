package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.Action;

public class PreReserVation implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("시간선택액션 활성화");
		
		String reservationDateParam = request.getParameter("reservationDate");
		String reservationTimeParam = request.getParameter("reservationTime");
		String addressInputParam = request.getParameter("addressInput");
		
		String formatTime = reservationDateParam+" "+reservationTimeParam+":00:00";
	
		HttpSession session = request.getSession();
		session.setAttribute("reservationDate", reservationDateParam);
		// 시간만 (14)시 의 형식으로 받는 reservationTimeParam을 년 월 일 시 :00 으로 변경후 formatTime에 넣음
		session.setAttribute("reservationTime", formatTime);
		session.setAttribute("addressInput", addressInputParam);
		response.sendRedirect("locationList");
		
	}

}
