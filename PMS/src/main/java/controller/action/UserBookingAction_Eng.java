package controller.action;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import booking.BookingDto;
import booking.controller.BookingDao;
import controller.Action;

public class UserBookingAction_Eng implements Action {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("유저 예약 액션");
		
		String userId = request.getParameter("userId");
		int locationCode = Integer.parseInt(request.getParameter("locationCode"));
		int spotCode = Integer.parseInt(request.getParameter("spotCode"));
		String carNumber = request.getParameter("carNumber");
		
		//  예약한 시간 regDate도 timestamp 형식으로 변환
				String regDate = request.getParameter("regDate")+":00";
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
		
		System.out.println("userbooking regdate:"+regTimestamp);
		// index에서 입력받은 startTime 을 PreReserVation 에서 "reservationTime"로 받아와서
		// 입력받은  startTime (예)15 를 "yyyy-MM-dd HH:mm:ss" 형식에 맞춰 변경 후 리퀘스트 받음
		String timestampString = request.getParameter("startTime");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date parsedDate;
		
		boolean checkPayment = false;
		Timestamp startTime = null;
		try {
			parsedDate = dateFormat.parse(timestampString);
			startTime = new Timestamp(parsedDate.getTime());
			System.out.println("타임스탬프: "+startTime);
 
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BookingDao bookingDao = BookingDao.getInstance();
		
		BookingDto bookingDto = new BookingDto(userId,locationCode,spotCode,carNumber,startTime,regTimestamp, checkPayment);
		bookingDao.newSpotBooking(bookingDto);
		
		response.sendRedirect("userBookingInform_eng");

	}

}
