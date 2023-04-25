package controller.action;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import booking.Booking;
import booking.BookingDto;
import booking.controller.BookingDao;
import client.ClientDto;
import client.controller.ClientDao;
import controller.Action;
import location.Location;
import location.controller.LocationDao;
import spot.Spot;
import spot.controller.SpotDao;
import user.User;
import userAccount.UserAccount;
import userAccount.controller.UserAccountDao;

public class CheckPaymentAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("log");
		int bookingCode = Integer.parseInt(request.getParameter("bookingCode"));
		int totalCost = Integer.parseInt(request.getParameter("totalCost"));
		String startTime = request.getParameter("startTime");
		
		Timestamp now = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String nowDate = sdf.format(now);
		
		BookingDao bookingDao = BookingDao.getInstance();
		int spotCode = bookingDao.getSpotCodeByBookingCode(bookingCode);
		
		int locationCodeTamp = bookingDao.getLocationCode(bookingCode);
		String locationCode = String.valueOf(locationCodeTamp);
		
		LocationDao locationDao = LocationDao.getInstance();
		Location locationInfo =  locationDao.getLocationByCode(locationCode);
		ClientDao clientDao = ClientDao.getInstance();

		SpotDao spotDao = SpotDao.getInstance();
		Spot spot = spotDao.getSpotBySpotCode(spotCode);
		
		
		int hours = 0;
		
		try {
			// 예약일과 예약시간을 timestamp로 변환합니다.
			Timestamp startTimestamp = new Timestamp(sdf.parse(startTime).getTime());

			// 반납일과 반납시간을 timestamp로 변환합니다.
			Timestamp endTimestamp = new Timestamp(sdf.parse(nowDate).getTime());
			long diffInMillis = endTimestamp.getTime() - startTimestamp.getTime();


			// millisecond를 시간으로 변환합니다.
			hours = (int) (diffInMillis / (60 * 60 * 1000));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		BookingDao dao = BookingDao.getInstance();
		BookingDto bookingDto = new BookingDto(bookingCode,totalCost);
		int cost = hours * spot.getSpotCost();
		if(hours >= 10) {
			cost *= 0.8;
		} else if( hours >= 20) {
			cost *= 0.6;
		} else if (hours >= 30) {
			cost *= 0.5;
		}
		 UserAccountDao userAccountDao = UserAccountDao.getInstance();
		 
		 int userCredit = userAccountDao.getAccountBalance(user.getUserAccount()); 
		 System.out.println("userCredit:"+userCredit);
		 System.out.println("cost:"+cost);
		 if(userCredit>=cost) {
			 int lastCost = userCredit-cost;
			 userAccountDao.paymentCost(lastCost, user.getUserId());
			 dao.bookingUpdate(bookingDto);
			 dao.payment(cost, bookingCode);
			 String endTime = bookingDao.getBookingEndTime(bookingCode);
			 response.getWriter().write(String.valueOf(cost) + " " + endTime);
		 } else {
			 response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		 }
		 
		 
	}

}
