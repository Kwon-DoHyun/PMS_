package controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Action;
import user.User;
import user.UserDto;
import user.controller.UserDao;

public class FindPasswordAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("findPassword 액션 활성화");
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String userPhone = request.getParameter("userPhone");
		
		UserDao userDao = UserDao.getInstance(); 
		User user = userDao.findUserById(userId);
		
		if(user!= null && user.getUserPhone().equals(userPhone) && user.getUserName().equals(userName)) {
	        	String tempPassword ="1111";
	        	UserDto tempUser = new UserDto(user.getUserId(), tempPassword, user.getUserName(), user.getUserPhone(), user.getUserAccount());
	        	 userDao.UpdateUser(tempUser);
	        	response.setContentType("text/html;charset=UTF-8");
				  PrintWriter out = response.getWriter();
				  out.println("<script>");
				  out.println("alert('임시비밀번호 1111로 변경되었습니다. 로그인 후 비밀번호를 변경하신 다음 이용하세요.');");
				  out.println("window.location.href='/userLogin';");
				  out.println("</script>");
				  
		} else {
			 response.setContentType("text/html;charset=UTF-8");
			  PrintWriter out = response.getWriter();
			  out.println("<script>");
			  out.println("alert('입력하신 정보가 일치하지 않습니다.');");
			  out.println("window.location.href='findPassword';");
			  out.println("</script>");
		}
	}

}
