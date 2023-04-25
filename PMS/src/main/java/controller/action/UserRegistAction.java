package controller.action;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Action;
import user.UserDto;
import user.controller.UserDao;

public class UserRegistAction implements Action {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("유저 가입 액션 실행");
		String userId = request.getParameter("userId");
		String userPassword = request.getParameter("userPassword");
		String userName = request.getParameter("userName");
		String userPhone = request.getParameter("userPhone");
		String userAccount = request.getParameter("userAccount");

		UserDao userDao = UserDao.getInstance();
		// id 중복 체크
		if (userDao.duppleCheckedById(userId)) {
			// 전화번호 중복 체크
			if (userDao.duppleCheckedByUserPhone(userPhone)) {
				if (userDao.duppleCheckedByUserAccount(userAccount)) {
					UserDto clientDto = new UserDto(userId, userPassword, userName, userPhone, userAccount);
					userDao.createUser(clientDto);

					System.out.println("유저 가입 완료");
					response.sendRedirect("/userLogin");

				} else {
					System.out.println("계좌가 중복되어 생성 불가");
					request.setAttribute("dupple", "account");
					request.getRequestDispatcher("/userRegist").forward(request, response);
				}

			} else {
				System.out.println("전화번호가 중복되어 생성 불가");
				request.setAttribute("dupple", "phone");
				request.getRequestDispatcher("/userRegist").forward(request, response);
			}

		} else {
			System.out.println("아이디가 중복되어 생성 불가");
			request.setAttribute("dupple", "id");
			request.getRequestDispatcher("/userRegist").forward(request, response);

		}

	}

}
