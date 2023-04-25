package controller.action;



import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Action;
import user.User;
import user.UserDto;
import user.controller.UserDao;

public class UserUpdateForAdminAction implements Action {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		System.out.println("관리자 유저 업데이트 액션 실행");
		String userId = request.getParameter("userId");
		String userPassword = request.getParameter("userPassword");
		String userName = request.getParameter("userName");
		System.out.println("유저 비밀번호:" + userPassword);
		String userPhone = request.getParameter("userPhone");
		String userAccount = request.getParameter("userAccount");

		UserDao userDao = UserDao.getInstance();

		// 기존의 유저를 호출해 중복체크 확인
		User userForDupplecheck = userDao.findUserById(userId);

		String dupplePhone = userForDupplecheck.getUserPhone();
		String duppleAccount = userForDupplecheck.getUserAccount();

		String error;

		String link= "/userUpdateForAdmin?userId="+userId;

		error = checkUserPhone(userPhone, dupplePhone, userDao) == true ? null : "phone";

		error = checkUserAccount(userAccount, duppleAccount, userDao) == true ? error : "account";

		if (error == null) {
			System.out.println("유저 업데이트 성공");

			UserDto userDto = new UserDto(userId, userPassword, userName, userPhone, userAccount);
			userDao.UpdateUser(userDto);


	

			System.out.println("해당 유저 관리로 돌아감");
			String suc = "sucess";
			System.out.println(suc);
			request.setAttribute("adminCode", suc);
		
			
			request.getRequestDispatcher(link).forward(request, response);

		} else {
			System.out.println("에러 원인: " + error);
			request.setAttribute("adminCode", error);
			request.getRequestDispatcher(link).forward(request, response);
		}

		/// 11111

	}

	public boolean checkUserPhone(String userPhone, String dupplePhone, UserDao userDao) {

		if (!dupplePhone.equals(userPhone)) {

			if (userDao.duppleCheckedByUserPhone(userPhone)) {
				return true;
			} else {
				return false;
			}

		}

		return true;

	}

	public boolean checkUserAccount(String userAccount, String duppleAccount, UserDao userDao) {

		if (!duppleAccount.equals(userAccount)) {

			if (userDao.duppleCheckedByUserAccount(userAccount)) {
				return true;
			} else {
				return false;
			}

		}

		return true;

	}

}
