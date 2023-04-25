package controller.action;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Action;
import nonUser.NonUserDto;
import nonUser.controller.NonUserDao;

public class nonUserBookingAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("nonUserBooking 액션활성화");
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		int locationCode = Integer.parseInt(request.getParameter("locationCode"));
		int spotCode = Integer.parseInt(request.getParameter("spotCode"));
		String carNumber = request.getParameter("carNumber");
		String regDate = request.getParameter("regDate") + ":00";
		DateFormat regDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date regParsedDate;
		Timestamp regTimestamp = null;
		try {
			regParsedDate = regDateFormat.parse(regDate);
			regTimestamp = new Timestamp(regParsedDate.getTime());
			System.out.println("예약일자 포맷:: " + regTimestamp);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String timestampString = request.getParameter("startTime");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date parsedDate;
		Timestamp startTime = null;
		try {
			parsedDate = dateFormat.parse(timestampString);
			startTime = new Timestamp(parsedDate.getTime());
			System.out.println("타임스탬프: " + startTime);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(phone);
		System.out.println(carNumber);
		System.out.println(password);
		System.out.println(locationCode);
		System.out.println(spotCode);
		System.out.println(startTime);
		System.out.println(regTimestamp);
		NonUserDao nonUserDao = NonUserDao.getInstance();
		NonUserDto nonUserDto = new NonUserDto(phone, carNumber, password, locationCode, spotCode, startTime, regTimestamp);
		nonUserDao.nonUserBooking(nonUserDto);
		System.out.println("비회원예약 성공");
		
		response.sendRedirect("searchBooking");
		
	}

}
