package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import booking.controller.BookingDao;
import controller.Action;
import user.User;
import user.controller.UserDao;

public class UserDeleteAction implements Action {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("유저 삭제 액션 실행");

		String userId = request.getParameter("userId");
		String userPassword = request.getParameter("userPassword");

		UserDao userDao = UserDao.getInstance();

		User user = userDao.findUserById(userId);

		if (userPassword.equals(user.getUserPassword())) {

			if(userDao.isPaid(userId)) {
				
				//삭제 전 예약 내역에서 해당 유저 아이디를 변경하는 메서드
				//해당 방식은 delete라는 더미 유저에게 넘기는 임시방편임.
				BookingDao bookingDao = BookingDao.getInstance();
				bookingDao.deleteUserId(userId);
				
				
				
				if (userDao.deleteUserById(userId)) {

					System.out.println("유저 삭제 액션 완료");
					HttpSession session = request.getSession();
					session.removeAttribute("log");
					session.removeAttribute("category");
					
					request.setAttribute("modify", "deleteUser");
					request.getRequestDispatcher("/index").forward(request, response);

				} else {
					System.out.println("삭제 오류가 있음. 외래 키나 테이블 오류 가능성 체크");
					request.setAttribute("errorCode", "table");
					request.getRequestDispatcher("/userDelete").forward(request, response);

				}
			}else {
				System.out.println("미결제 오류");
				request.setAttribute("errorCode", "booking");

				request.getRequestDispatcher("/userDelete").forward(request, response);
				
				
			}
			
			
		
			
			
			
			

		} else {
			request.setAttribute("errorCode", "password");

			request.getRequestDispatcher("/userDelete").forward(request, response);

		}

	}

}
