package controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Action;
import nonUser.NonUser;
import nonUser.controller.NonUserDao;

public class SearchBookingAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("searchBooking 액션 활성화");

		String carNumber = request.getParameter("carNumber");
		String phoneNumber = request.getParameter("phone");
		String reservationNumber = request.getParameter("reservationNumber");
		String password = request.getParameter("password");
		String keyword = "";		
		if (carNumber != "") {
			keyword = "WHERE non_user_car = '" + carNumber + "'" + "and non_user_password = '" + password +"'"; 
		}
		else if (phoneNumber != "") {
			keyword = "WHERE non_user_phone = '" + phoneNumber + "'" + "and non_user_password = '" + password +"'";
		}
		else if (reservationNumber != "") {
			keyword = "WHERE non_user_booking_code = '" + reservationNumber + "'" + "and non_user_password = '" + password +"'";
		}
		System.out.println("keyword!!!!!!   :  "+keyword);
		NonUserDao nonUserDao = NonUserDao.getInstance();
		ArrayList<NonUser> list = nonUserDao.getNonUserBookingListByKeyword(keyword);
		for(int i = 0; i< list.size();i++) {
			System.out.println(list.get(i).getLocationCode());
			System.out.println(list.get(i).getNonUserBookingCode());
			System.out.println(list.get(i).getNonUserCar());
			System.out.println(list.get(i).getNonUserPassword());
			System.out.println(list.get(i).getNonUserPhone());
			System.out.println(list.get(i).getSpotCode());
			System.out.println(list.get(i).getTotalCost());
			System.out.println(list.get(i).getStartTime());
			System.out.println();
		}
		System.out.println("ist.size()"+list.size());
		if(list.size()>0) {
			request.setAttribute("list", list);
			request.getRequestDispatcher("searchBooking").forward(request, response);
		} else {
			//request.setAttribute("noList", true);
			//response.sendRedirect("searchBooking");
			request.getRequestDispatcher("searchBooking").forward(request, response);
		}

	}

}
